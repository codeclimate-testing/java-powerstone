/*
 * Copyright 2004-2005 the original author or authors.
 *
 * Licensed under the LGPL license, Version 2.1 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.gnu.org/copyleft/lesser.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author daquanda(liyingquan@gmail.com)
 * @author kevin(diamond_china@msn.com)
 */
package org.powerstone.workflow.flowdriver;

import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.List;

import org.powerstone.ca.CADelegater;
import org.powerstone.workflow.model.WorkflowDriver;
import org.powerstone.workflow.model.ActivityReport;
import org.powerstone.workflow.model.FlowTask;
import org.powerstone.workflow.service.WorkflowEngine;
import org.powerstone.workflow.service.WorkflowDriverManager;
import org.powerstone.workflow.service.FlowTaskManager;
import java.io.PrintWriter;

abstract public class AbstractWorkflowDriver
    extends SimpleFormController {
  //由具体(子类型)的应用设置此标志,表示要驱动工作流引擎
  private final static String FLAG_DRIVE_FLOW = "powerstone_flag_driver_flow";

  //在处理POST请求的过程中出了问题会进入showForm()，设置标志不允许触发流程引擎
  private final static String FLAG_CAN_NOT_DRIVE_FLOW =
      "powerstone_flag_can_not_driver_flow";
  protected final static Log log = LogFactory.getLog(AbstractWorkflowDriver.class);
  protected static CADelegater caDelegater;

  private static WorkflowEngine flowEngine;
  private static WorkflowDriverManager driverManager;
  private static FlowTaskManager taskManager;
  private static WorkflowExecuter wfExecuter;
  private static boolean debug = false;

  public AbstractWorkflowDriver() {
    //免得在表单提交时调用formBackingObject()
    super.setSessionForm(true);
  }

  public void setCaDelegater(CADelegater caDelegater) {
    this.caDelegater = caDelegater;
  }

  public void setDebug(boolean debug) {
    this.debug = debug;
  }

  public void setDriverManager(WorkflowDriverManager driverManager) {
    this.driverManager = driverManager;
  }

  public void setFlowEngine(WorkflowEngine flowEngine) {
    this.flowEngine = flowEngine;
  }

  public void setTaskManager(FlowTaskManager taskManager) {
    this.taskManager = taskManager;
  }

  public void setWfExecuter(WorkflowExecuter wfExecuter) {
    this.wfExecuter = wfExecuter;
  }

//------------------------------------------------------------------------------
  /**
   * WriteDo的切入点(invoked by springmvc framework)
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @param command Object
   * @param errors BindException
   * @throws Exception
   * @return ModelAndView
   */
  public final ModelAndView onSubmit(HttpServletRequest request,
                                     HttpServletResponse response,
                                     Object command,
                                     BindException errors) throws Exception {
    //执行子类的业务逻辑(期间子类可能调用driveWorkflow()来提交任务)
    //call doSubmit() of subclass to execute business logic(during which,subclass may call driveWorkflow() to submit a task,on demand)
    ModelAndView result = doSubmit(request, response, command, errors);

    doDriveWorkflow(request, response, command, errors);

    if (log.isDebugEnabled()) {
      log.debug("after doBusiness:ActionForward[" +
                (result != null ? result.getViewName() : "null") + "]");
    }

    if (result == null) {
      //关闭任务窗口，刷新任务列表：parent.submitTask();
      //close the task window and refresh the worklist(by calling javascript function submitTask in the parent window)
      try {
        PrintWriter out = (PrintWriter) response.getWriter();
        out.print("<script language=\"JavaScript\">");
        out.print("parent.submitTask();");
        out.print("</script>");
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
    return result;
  }

  /**
   * 设置此标志表示要驱动工作流引擎
   * set a flag in request,indicating that the relative task should submit to drive workflow engine
   * @param request HttpServletRequest
   */
  protected void driveWorkflow(HttpServletRequest request) {
    request.setAttribute(FLAG_DRIVE_FLOW, "##");
  }

  /**
   * 实现应用的业务逻辑
   * For subclass to implement bisiness logic
   */
  protected abstract ModelAndView doSubmit(HttpServletRequest request,
                                           HttpServletResponse response,
                                           Object command,
                                           BindException errors) throws
      Exception;

  /**
   * 获得应用输出参数,也可以在此处放置业务逻辑(此处决定了注册应用的输出参数)
   * subclass will return the output parameters,can also implement bisiness logic here
   * @return HashMap
   */
  protected abstract HashMap getOutputParameters(HttpServletRequest request,
                                                 HttpServletResponse response,
                                                 Object command,
                                                 BindException errors) throws
      Exception;

//------------------------------------------------------------------------------
  /**
   * ReadDo的切入点(pointcut for ReadDO)
   * @param request HttpServletRequest
   * @throws ServletException
   * @return Object
   */
  protected final Object formBackingObject(HttpServletRequest request) throws
      ServletException {
    Object result = doFormBackingObject(request,
                                        getDriverInputParameters(request));
    return result;
  }

  /**
   * 向客户端写cookie供WriteDo使用(send cookie to browser,for WriteDo to use)
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @param errors BindException
   * @throws Exception
   * @return ModelAndView
   */
  protected final ModelAndView showForm(HttpServletRequest request,
                                        HttpServletResponse response,
                                        BindException errors) throws Exception {
    //很可能是在处理POST请求的过程中出了问题，设置标志不允许触发流程引擎
    //if this method is called,it's very possible that there were validate errors during processing a POST request
    canNotDriveFlow(request);

    String url = request.getServletPath(); //获取请求
    if (log.isDebugEnabled()) {
      log.debug("WorkflowDriver read:[" + url + "]");
    }
    String flowTaskID =
        request.getParameter(IFlowDriver.DEFAULT_FLOW_TASK_PARAM_NAME);
    if (flowTaskID == null) {
      log.warn("ReadDO[" + request.getContextPath() + "|" + url +
               "]后面缺少关键参数[" + IFlowDriver.DEFAULT_FLOW_TASK_PARAM_NAME + "]！");
    }
    else {
      List readDrivers =
          driverManager.findDriverByReadDO(request.getContextPath(), url);
      WorkflowDriver flowDriver = null;
      if (readDrivers.size() > 0) {
        flowDriver = (WorkflowDriver) readDrivers.get(0);
        //设置cookie供WriteDO使用
        this.processCookieOnFormBacking(flowTaskID, flowDriver.getWriteURL(),
                                        response);
      }
    }

    ModelAndView result = super.showForm(request, response, errors);
    //把输入参数传送到表单页面(save input parameters in request,for view(jsp) to use)
    result.addAllObjects(getDriverInputParameters(request));
    return result;
  }

  protected abstract Object doFormBackingObject(HttpServletRequest request,
                                                HashMap inputParameters) throws
      ServletException;

//------------------------------------------------------------------------------
  /**
   * 从cookie获取taskID，
   * 如果与uri对应的应用匹配,就发送ActivityReport(taskID，driverOutputData),
   * 否则发送ActivityReport(driverID，driverOutputData)
   * obtain taskID from cookie,send ActivityReport(with taskIDand driverOutputData)
   * if it matches the request url,otherwise,send ActivityReport(with driverID and driverOutputData)
   */
  private void sendActivityReport(HttpServletRequest request,
                                  HttpServletResponse response,
                                  Object command,
                                  BindException errors) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug("发送活动通报前--------------" + request);
    }
    String url = request.getServletPath();

    //从子类获取实际数据
    HashMap outputParameters =
        getOutputParameters(request, response, command, errors);

    if (log.isDebugEnabled()) {
      log.debug("sending ActivityReport[" + outputParameters + "]");
    }

    List writeDrivers =
        driverManager.findDriverByWriteDO(request.getContextPath(), url);

    WorkflowDriver flowDriver = null;
    if (writeDrivers.size() > 0) {
      flowDriver = (WorkflowDriver) writeDrivers.get(0);
      if (writeDrivers.size() > 1) {
        log.warn("request[" + request.getContextPath() + "|" + url +
                 "]was registered as more than one application!");
      }
    }
    else {
      log.warn("request[" + request.getContextPath() + "|" + url +
               "]has not beed registered as any application!");
      return;
    }

    String taskIDInCookie = processCookieOnSubmit(request);
    if (log.isDebugEnabled()) {
      log.debug("obtain taskID[" + taskIDInCookie + "]from cookie");
    }

    FlowTask task = null;
    if (taskIDInCookie != null) {
      task = taskManager.getFlowTask(taskIDInCookie);
    }

    ActivityReport report = new ActivityReport();

    if (task != null) {
      //如果task与uri对应的应用匹配,就发送ActivityReport(taskID，driverOutputData)
      if (task.getFlowNodeBinding().getWorkflowDriver().equals(flowDriver)) {
        report.setFlowTaskID(taskIDInCookie);
        if (log.isDebugEnabled()) {
          log.debug("A saving of task[" + taskIDInCookie + "]");
        }
      }
      else {
        log.warn("Task[" + taskIDInCookie + "]doesn't match with app[" +
                 flowDriver.getFlowDriverName() + "]!");
        return;
      }
    }
    //否则发送ActivityReport(driverID，driverOutputData)
    else {
      report.setDriverID(flowDriver.getFlowDriverID().toString());
      if (log.isDebugEnabled()) {
        log.debug("App[" + flowDriver.getFlowDriverName() +
                  "]triggers workflow");
      }
    }

    report.setDriverOutputData(outputParameters);
    if (log.isDebugEnabled()) {
      log.debug("outputParameters" + outputParameters);
    }

    flowEngine.processActivityReport(caDelegater.getRemoteUser(request), report);
  }

  /**
   * 删除cookie
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   */
  private void removeCookie(HttpServletRequest request,
                            HttpServletResponse response) {
    Cookie[] cks = request.getCookies();
    if (cks != null) {
      for (int i = 0; i < cks.length; i++) {
        Cookie tok = cks[i];
        if (tok.getName().equals(IFlowDriver.COOKIE_NAME)) {
          //remove cookie
          tok.setMaxAge(0);
          tok.setPath("/");
          response.addCookie(tok);
          break;
        }
      }
    }
  }

  /**
   * 从cookie获取taskID
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return String
   */
  private String processCookieOnSubmit(HttpServletRequest request) {
    String taskIDInCookie = null;
    Cookie[] cks = request.getCookies();

    if (cks != null) {
      for (int i = 0; i < cks.length; i++) {
        Cookie tok = cks[i];
        if (tok.getName().equals(IFlowDriver.COOKIE_NAME)) {
          taskIDInCookie = tok.getValue();
          if (taskIDInCookie.indexOf(request.getServletPath()) >= 0) {
            if (log.isDebugEnabled()) {
              log.debug("cookie[" + taskIDInCookie + "]");
            }

            taskIDInCookie =
                taskIDInCookie.substring(0,
                                         taskIDInCookie.indexOf(IFlowDriver.
                COOKIE_DIVIDER));
            System.out.println("obtain cookie[" + taskIDInCookie + "]");
          }
          else {
            log.warn("The cookie doesn't match！！！！");
            taskIDInCookie = null;
            break;
          }

          break;
        }
      }
    }
    return taskIDInCookie;
  }

  /**
   * 设置cookie供WriteDO使用
   * @param flowTaskID String
   * @param writeURL String
   * @param response HttpServletResponse
   */
  private void processCookieOnFormBacking(String flowTaskID, String writeURL,
                                          HttpServletResponse response) {
    String cookieValue =
        flowTaskID + IFlowDriver.COOKIE_DIVIDER + writeURL;
    Cookie ck = new Cookie(IFlowDriver.COOKIE_NAME, cookieValue);
    ck.setPath("/");
    response.addCookie(ck);
    if (log.isDebugEnabled()) {
      log.debug("set cookie:" + cookieValue);
    }
  }

  /**
   * 获取输入参数
   * @return HashMap
   */
  private HashMap getDriverInputParameters(HttpServletRequest request) {
    //获取请求
    String uri = request.getServletPath();
    if (log.isDebugEnabled()) {
      log.debug(uri);
    }
    //给应用注入参数
    String flowTaskID =
        request.getParameter(IFlowDriver.DEFAULT_FLOW_TASK_PARAM_NAME);
    if (flowTaskID == null) {
      log.warn("ReadDO[" + request.getContextPath() + "|" + uri +
               "]lacks key parameter[" +
               IFlowDriver.DEFAULT_FLOW_TASK_PARAM_NAME + "]！");
      return new HashMap();
    }
    else {
      List readDrivers =
          driverManager.findDriverByReadDO(request.getContextPath(), uri);
      if (readDrivers.size() > 1) {
        log.warn("Request[" + request.getContextPath() + "|" + uri +
                 "]was registered as more than one application!");
      }
      if (readDrivers.size() <= 0) {
        log.warn("Request[" + request.getContextPath() + "|" + uri +
                 "]hasn't been registered as any application!");
        return new HashMap();
      }

      //给应用注入参数
      FlowTask flowTask = taskManager.getFlowTask(flowTaskID);
      //flowTask.getFlowNodeBinding()
      HashMap procState =
          flowTask.getFlowProc().generateProcStateForDriver(flowTask.
          getFlowNodeBinding());

      if (log.isDebugEnabled()) {
        log.debug("Inject parameters" +
                  procState + "into app.");
      }
      return procState;
    }
  }

  /**
   * 提交任务,驱动工作流引擎
   */
  private void doDriveWorkflow(HttpServletRequest request,
                               HttpServletResponse response,
                               Object command,
                               BindException errors) throws Exception {
    if (debug) { //如果是调试模式
      log.debug("The system is in debug state,just now");
      return;
    }
    if (request.getAttribute(FLAG_CAN_NOT_DRIVE_FLOW) != null) {
      log.warn("Found flag[" + FLAG_CAN_NOT_DRIVE_FLOW +
               "],indicating there were validate errors during processing a POST request(and showForm was called),wont driver workflow engine!");
      return;
    }

    //发送活动通报
    sendActivityReport(request, response, command, errors); //期间调用子类的getOutputParameters()

    //根据子类设置的flag决定是否驱动工作流引擎
    if (request.getAttribute(FLAG_DRIVE_FLOW) != null) {
      String userID = caDelegater.getRemoteUser(request);
      String taskID = processCookieOnSubmit(request);
      if (log.isDebugEnabled()) {
        log.debug("User[" + userID + "]submited task[" + taskID +
                  "]to drive workflow engine");
      }
      flowEngine.processSubmitTask(userID, taskID);
    }

    //删除垃圾cookie
    this.removeCookie(request, response);
  }

  //很可能是在处理POST请求的过程中出了问题，设置标志不允许触发流程引擎
  private void canNotDriveFlow(HttpServletRequest request) {
    request.setAttribute(FLAG_CAN_NOT_DRIVE_FLOW, "XX");
  }

//--------------Convenience methods for getting a i18n key's value--------------
  public String getText(String msgKey) {
    return getMessageSourceAccessor().getMessage(msgKey);
  }

  public String getText(String msgKey, String arg) {
    return getText(msgKey, new Object[] {arg});
  }

  public String getText(String msgKey, Object[] args) {
    return getMessageSourceAccessor().getMessage(msgKey, args);
  }
}