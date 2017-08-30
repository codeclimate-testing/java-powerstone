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

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.HashMap;
import java.util.List;
import org.powerstone.workflow.model.WorkflowDriver;
import org.powerstone.workflow.model.ActivityReport;
import javax.servlet.http.Cookie;
import org.powerstone.workflow.model.FlowTask;

public abstract class WorkflowDriverWriteAction
    extends Action
    implements WorkFlowDriverTransactionable {
  private static Log log = LogFactory.getLog(WorkflowDriverWriteAction.class);

  public final ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws
      Exception {
    try {
      WorkflowExecuter wfExecuter = WorkflowFactoryPlugin.getWfExecuter();
      if (wfExecuter != null) {
        //实际上是在回调自己的doTransactionExecute()
        return wfExecuter.doExecute(this, mapping, form, request, response);
      }
      else {
        log.warn("没有使用WorkflowExecuter，数据一致性没有保证！debug:[" +
                 WorkflowFactoryPlugin.isDebug() + "]");
        return doTransactionExecute(mapping, form, request, response);
      }
    }
    catch (Exception e) {
      log.warn(e);
      e.printStackTrace();
      return mapping.findForward("driver_error_forward");
    }
  }

  /**
   * 这个方法需要JTA来保证事务性，它将被WorkflowExecuter调用
   * @throws Exception
   * @return ActionForward
   */
  public final ActionForward doTransactionExecute(ActionMapping mapping,
                                                  ActionForm form,
                                                  HttpServletRequest request,
                                                  HttpServletResponse response
                                                  ) throws
      Exception {
    //发送活动通报
    if (!WorkflowFactoryPlugin.isDebug()) { //如果不是调试模式
      sendActivityReport(form, request, response);
    }

    //执行业务逻辑(期间可能调用driveWorkflow()来提交任务)
    ActionForward forward = doBusiness(mapping, form, request, response);

    //删除cookie
    this.removeCookie(request, response);
    if (log.isDebugEnabled()) {
      log.debug("after doBusiness:ActionForward[" +
                (forward != null ? forward.getPath() : "null") + "]");
    }
    if (forward == null) {
      //关闭父窗口：parent.submitTask();

    }
    return forward;
  }

  /**
   * 提交任务,驱动工作流引擎
   */
  protected void driveWorkflow(HttpServletRequest request,
                               HttpServletResponse response) {
    if (WorkflowFactoryPlugin.isDebug()) { //如果是调试模式
      log.debug("目前处于工作流调试模式！");
      return;
    }
    String userID = WorkflowFactoryPlugin.getRemoteUser(request);
    String taskID = processCookie(request, response);

    if (log.isDebugEnabled()) {
      log.debug("用户[" + userID + "]提交任务[" + taskID + "]驱动工作流");
    }
    WorkflowFactoryPlugin.getFlowEngine().processSubmitTask(userID, taskID);
  }

  /**
   * 从cookie获取taskID，
   * 如果与uri对应的驱动匹配,就发送ActivityReport(taskID，driverOutputData),
   * 否则发送ActivityReport(driverID，driverOutputData)
   * 发送活动通报
   */
  private void sendActivityReport(ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
    if (log.isDebugEnabled()) {
      log.debug("发送活动通报前--------------" + request);
    }
    String uri = request.getServletPath();
    HashMap driverOutputParameters = getDriverOutputParameters(form, request);
    if (log.isDebugEnabled()) {
      log.debug("发送活动通报[" + driverOutputParameters + "]");
    }

    List writeDrivers =
        WorkflowFactoryPlugin.getDriverManager().findDriverByWriteDO(request.
        getContextPath(), uri);

    WorkflowDriver flowDriver = null;
    if (writeDrivers.size() > 0) {
      flowDriver = (WorkflowDriver) writeDrivers.get(0);
      if (writeDrivers.size() > 1) {
        log.warn("请求[" + request.getContextPath() + "|" + uri + "]被注册为多个驱动！");
      }
    }
    else {
      log.warn("请求[" + request.getContextPath() + "|" + uri + "]没有被注册为任何驱动！");
      return;
    }

    String taskIDInCookie = processCookie(request, response);
    if (log.isDebugEnabled()) {
      log.debug("从cookie中获取taskID[" + taskIDInCookie + "]");
    }

    FlowTask task = null;
    if (taskIDInCookie != null) {
      task = WorkflowFactoryPlugin.getTaskManager().getFlowTask(taskIDInCookie);
    }

    ActivityReport report = new ActivityReport();

    if (task != null) {
      //如果task与uri对应的驱动匹配,就发送ActivityReport(taskID，driverOutputData)
      if (task.getFlowNodeBinding().getWorkflowDriver().equals(flowDriver)) {
        report.setFlowTaskID(taskIDInCookie);
        if (log.isDebugEnabled()) {
          log.debug("任务[" + taskIDInCookie + "]的一次保存");
        }
      }
      else {
        log.warn("任务[" + taskIDInCookie + "]与驱动[" +
                 flowDriver.getFlowDriverName() + "]不匹配！");
        return;
      }
    }
    //否则发送ActivityReport(driverID，driverOutputData)
    else {
      report.setDriverID(flowDriver.getFlowDriverID().toString());
      if (log.isDebugEnabled()) {
        log.debug("驱动[" + flowDriver.getFlowDriverName() + "]触发工作流");
      }
    }

    report.setDriverOutputData(driverOutputParameters);
    if (log.isDebugEnabled()) {
      log.debug("驱动输出参数：" + driverOutputParameters);
    }

    WorkflowFactoryPlugin.getFlowEngine().processActivityReport(
        WorkflowFactoryPlugin.getRemoteUser(request), report);
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
          //删除cookie
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
  private String processCookie(HttpServletRequest request,
                               HttpServletResponse response) {
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
            System.out.println("从cookie获取令牌[" + taskIDInCookie + "]");
          }
          else {
            log.warn("cookie不匹配！！！！");
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
   * 实现驱动的业务逻辑
   */
  public abstract ActionForward doBusiness(ActionMapping mapping,
                                           ActionForm form,
                                           HttpServletRequest request,
                                           HttpServletResponse response) throws
      Exception;

  /**
   * 获得驱动输出参数:此处决定了注册驱动输出参数
   * @return HashMap
   */
  public abstract HashMap getDriverOutputParameters(ActionForm form,
      HttpServletRequest request);

}
