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
package org.powerstone.workflow.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.view.RedirectView;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.powerstone.ca.CADelegater;
import org.powerstone.workflow.service.FlowTaskManager;
import java.util.Map;
import java.util.HashMap;

import java.util.List;
import java.util.Iterator;
import org.powerstone.workflow.model.FlowTask;
import java.util.Collection;
import org.powerstone.util.ResponseWriter;
import org.powerstone.workflow.flowdriver.IFlowDriver;
import org.powerstone.workflow.model.WorkflowMeta;
import org.powerstone.workflow.service.FlowMetaManager;
import org.powerstone.workflow.model.WorkflowNode;
import org.dom4j.Document;
import org.dom4j.io.XMLWriter;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.powerstone.ca.model.User;
import org.powerstone.workflow.exception.ExceptionMessage;
import org.powerstone.workflow.exception.FlowTaskException;
import org.powerstone.workflow.service.FlowDeployManager;
import java.util.Locale;

public class WorkListController
    extends MultiActionController {
  private static Log log = LogFactory.getLog(WorkListController.class);
  private FlowTaskManager flowTaskManager;
  private CADelegater caDelegater;
  private FlowMetaManager flowMetaManager;
  private FlowDeployManager flowDeployManager;
  private Integer pageSize = new Integer(20); //页大小

//------------------------------------------------------------------------------
  public ModelAndView main(HttpServletRequest request,
                           HttpServletResponse response) throws
      Exception {
    return new ModelAndView("main");
  }

//------------------------------------------------------------------------------
  public ModelAndView listExecutingTasks(HttpServletRequest request,
                                         HttpServletResponse response) throws
      Exception {
    Map model = new HashMap();

    String userID = caDelegater.getRemoteUser(request);
    String typeID = request.getParameter("typeID");
    model.put("typeID", typeID);

    String currPageNo = request.getParameter("currPageNo"); //当前处在第几页
    Integer pageNum =
        (currPageNo != null ? new Integer(currPageNo) : new Integer("0"));

    String strTo = request.getParameter("pageTo"); //去第几页
    Integer pageTo = (strTo != null ? new Integer(strTo) : null);

    String first = request.getParameter("first");
    String last = request.getParameter("last");
    String next = request.getParameter("next");
    String end = request.getParameter("end");
    int iPage = 0; //当前第几页
    int pageCount = 0; //共多少页

    //共有多少任务
    int tasksNum =
        flowTaskManager.findMyExecutingTasksNumByType(userID, typeID).intValue();

    if (tasksNum % pageSize.intValue() == 0) {
      pageCount = tasksNum / pageSize.intValue();
    }
    else {
      pageCount = tasksNum / pageSize.intValue() + 1;
    }
    if (pageCount > 1) {
      model.put("totalPageNum", new Integer(pageCount));
    }

    if (pageTo != null) {
      iPage = pageTo.intValue();
    }
    else if (first == null && last == null && next == null && end == null) {
      iPage = 1;
    }
    else if (first != null) {
      iPage = 1;
    }
    else if (last != null) {
      iPage = pageNum.intValue() - 1;
    }
    else if (next != null) {
      iPage = pageNum.intValue() + 1;
    }
    else if (end != null) {
      iPage = pageCount;
    }
    if (iPage < 1) {
      iPage = 1;
    }
    if (iPage > pageCount) {
      iPage = pageCount;
    }
    model.put("currPageNo", new Integer(iPage));

    List aPageExeTasks =
        flowTaskManager.findMyExecutingTasksByType(userID,
        typeID,
        iPage,
        pageSize.intValue());
    model.put("aPageExeTasks", aPageExeTasks);
    model.put("pageSize", pageSize);
    if (log.isDebugEnabled()) {
      log.debug("aPageExeTasks.size()[" + aPageExeTasks.size() + "]");
    }

    return new ModelAndView("listExecutingTasks", model);
  }

//------------------------------------------------------------------------------
  public ModelAndView listFinishedTasks(HttpServletRequest request,
                                        HttpServletResponse response) throws
      Exception {
    Map model = new HashMap();

    String userID = caDelegater.getRemoteUser(request);
    String typeID = request.getParameter("typeID");
    model.put("typeID", typeID);

    String currPageNo = request.getParameter("currPageNo"); //当前处在第几页
    Integer pageNum =
        (currPageNo != null ? new Integer(currPageNo) : new Integer("0"));

    String strTo = request.getParameter("pageTo"); //去第几页
    Integer pageTo = (strTo != null ? new Integer(strTo) : null);

    String first = request.getParameter("first");
    String last = request.getParameter("last");
    String next = request.getParameter("next");
    String end = request.getParameter("end");
    int iPage = 0; //当前第几页
    int pageCount = 0; //共多少页

    //共有多少任务
    int tasksNum =
        flowTaskManager.findMyFinishedTasksNumByType(userID, typeID).intValue();

    if (tasksNum % pageSize.intValue() == 0) {
      pageCount = tasksNum / pageSize.intValue();
    }
    else {
      pageCount = tasksNum / pageSize.intValue() + 1;
    }
    if (pageCount > 1) {
      model.put("totalPageNum", new Integer(pageCount));
    }

    if (pageTo != null) {
      iPage = pageTo.intValue();
    }
    else if (first == null && last == null && next == null && end == null) {
      iPage = 1;
    }
    else if (first != null) {
      iPage = 1;
    }
    else if (last != null) {
      iPage = pageNum.intValue() - 1;
    }
    else if (next != null) {
      iPage = pageNum.intValue() + 1;
    }
    else if (end != null) {
      iPage = pageCount;
    }
    if (iPage < 1) {
      iPage = 1;
    }
    if (iPage > pageCount) {
      iPage = pageCount;
    }
    model.put("currPageNo", new Integer(iPage));
    model.put("pageSize", pageSize);

    List aPageFinishedTasks =
        flowTaskManager.findMyFinishedTasksByType(userID,
                                                  typeID,
                                                  iPage,
                                                  pageSize.intValue());
    model.put("aPageFinishedTasks", aPageFinishedTasks);

    return new ModelAndView("listFinishedTasks", model);
  }

//------------------------------------------------------------------------------
  public ModelAndView listTasksKinds(HttpServletRequest request,
                                     HttpServletResponse response) throws
      Exception {
    String userID = caDelegater.getRemoteUser(request);
    Map model = new HashMap();

    //查找新任务
    List myNewTasks =
        flowTaskManager.findMyNewTasksKinds(userID);
    Integer myNewTasksNum =
        flowTaskManager.findMyNewTasksNum(userID);
    if (myNewTasksNum.intValue() > 0) {
      model.put("myNewTasksKinds", myNewTasks);
    }
    model.put("myNewTasksNum", myNewTasksNum);

    //需要指派的任务
    Integer myTasksToAssignNum = flowTaskManager.findMyTasksToAssignNum(userID);
    if (log.isDebugEnabled()) {
      log.debug("需要[" + userID + "]指派的任务[" + myTasksToAssignNum + "]");
    }
    if (myTasksToAssignNum.intValue() > 0) {
      model.put("myTasksToAssignNum", myTasksToAssignNum);
    }

    //执行中的任务
    List myExecutingTasks =
        flowTaskManager.findMyExecutingTasksKinds(userID);
    Integer myExecutingTasksNum =
        flowTaskManager.findMyExecutingTasksNum(userID);
    if (myExecutingTasksNum.intValue() > 0) {
      model.put("myExecutingTasks", myExecutingTasks);
    }
    model.put("myExecutingTasksNum", myExecutingTasksNum);

    //被拒绝的任务
    Integer myRefusedTasksNum =
        flowTaskManager.findMyRefusedTasksNum(userID);
    if (myRefusedTasksNum.intValue() > 0) {
      model.put("myRefusedTasksNum", myRefusedTasksNum);
    }

    //已完成任务
    List myFinishedTasksKinds =
        flowTaskManager.findMyFinishedTasksKinds(userID);
    Integer myFinishedTasksNum =
        flowTaskManager.findMyFinishedTasksNum(userID);
    if (myFinishedTasksNum.intValue() > 0) {
      model.put("myFinishedTasksKinds", myFinishedTasksKinds);
    }
    model.put("myFinishedTasksNum", myFinishedTasksNum);

    if (log.isDebugEnabled()) {
      log.debug(model);
    }

    return new ModelAndView("listTaskKinds", model);
  }

//------------------------------------------------------------------------------
  /**
   * 欢迎访问任务列表
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @throws Exception
   * @return ModelAndView
   */
  public ModelAndView tasksPannel(HttpServletRequest request,
                                  HttpServletResponse response) throws
      Exception {
    return new ModelAndView("tasksPannel");
  }

//------------------------------------------------------------------------------
  public ModelAndView listNewTasks(HttpServletRequest request,
                                   HttpServletResponse response) throws
      Exception {
    Map model = new HashMap();

    String userID = caDelegater.getRemoteUser(request);
    String typeID = request.getParameter("typeID");
    model.put("typeID", typeID);

    String currPageNo = request.getParameter("currPageNo"); //当前处在第几页
    Integer pageNum =
        (currPageNo != null ? new Integer(currPageNo) : new Integer("0"));

    String strTo = request.getParameter("pageTo"); //去第几页
    Integer pageTo = (strTo != null ? new Integer(strTo) : null);

    String first = request.getParameter("first");
    String last = request.getParameter("last");
    String next = request.getParameter("next");
    String end = request.getParameter("end");
    int iPage = 0; //当前第几页
    int pageCount = 0; //共多少页

    //共有多少任务
    int tasksNum =
        flowTaskManager.findMyNewTasksNumByType(userID, typeID).intValue();
    if (log.isDebugEnabled()) {
      log.debug("共有任务[" + tasksNum + "]");
    }

    if (tasksNum % pageSize.intValue() == 0) {
      pageCount = tasksNum / pageSize.intValue();
    }
    else {
      pageCount = tasksNum / pageSize.intValue() + 1;
    }
    if (pageCount > 1) {
      model.put("totalPages", new Integer(pageCount));
    }

    if (pageTo != null) {
      iPage = pageTo.intValue();
    }
    else if (first == null && last == null && next == null && end == null) {
      iPage = 1;
    }
    else if (first != null) {
      iPage = 1;
    }
    else if (last != null) {
      iPage = pageNum.intValue() - 1;
    }
    else if (next != null) {
      iPage = pageNum.intValue() + 1;
    }
    else if (end != null) {
      iPage = pageCount;
    }
    if (iPage < 1) {
      iPage = 1;
    }
    if (iPage > pageCount) {
      iPage = pageCount;
    }
    model.put("currPageNo", new Integer(iPage));

    List aPageNewTasks =
        flowTaskManager.findMyNewTasksByType(userID,
                                             typeID,
                                             iPage,
                                             pageSize.intValue());
    model.put("aPageNewTasks", aPageNewTasks);
    model.put("pageSize", pageSize);

    return new ModelAndView("listNewTasks", model);
  }

//------------------------------------------------------------------------------
  public ModelAndView checkOutTasks(HttpServletRequest request,
                                    HttpServletResponse response) throws
      Exception {
    String[] taskID = request.getParameterValues("taskID");
    String userID = caDelegater.getRemoteUser(request);
    if (taskID != null && taskID.length > 0) {
      for (int i = 0; i < taskID.length; i++) {
        FlowTask task = flowTaskManager.getFlowTask(taskID[i]);
        if (flowDeployManager.isNodePerformer(userID,
                                              task.getFlowNodeBinding().
                                              getNodeBindingID().toString())) {
          flowTaskManager.checkOutTask(userID, taskID[i]);
        }
      }
    }

    ResponseWriter.parentReload(response);
    return null;
  }

//------------------------------------------------------------------------------
  public ModelAndView getBackTask(HttpServletRequest request,
                                  HttpServletResponse response) throws
      Exception {
    String taskID = request.getParameter("taskID");
    String userID = caDelegater.getRemoteUser(request);
    if (flowTaskManager.getBackFlowTask(userID, taskID)) {
      if (log.isDebugEnabled()) {
        log.debug("用户[" + userID + "]取回任务[" + taskID + "]");
      }
    }
    else {
      try {
        java.io.PrintWriter out = (java.io.PrintWriter) response.getWriter();
        out.print("<script language=\"JavaScript\">");
        out.print("alert('" +
                  this.getText("task.get_back_fail", taskID, request.getLocale()) +
                  "');");
        out.print("</script>");
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
    ResponseWriter.parentReload(response);
    return null;
  }

//------------------------------------------------------------------------------
  public ModelAndView listTasksToAssign(HttpServletRequest request,
                                        HttpServletResponse response) throws
      Exception {
    Map model = new HashMap();
    String userID = caDelegater.getRemoteUser(request);
    model.put("tasksToAssign", flowTaskManager.findMyTasksToAssign(userID));
    return new ModelAndView("listTasksToAssign", model);
  }

//------------------------------------------------------------------------------
  public ModelAndView listTasksToRefuse(HttpServletRequest request,
                                        HttpServletResponse response) throws
      Exception {
    String taskID = request.getParameter("taskID");
    List tasksToRefuse = flowTaskManager.findTasksToRefuse(taskID);
    flowTaskManager.taskPreviewProcess(tasksToRefuse);

    Map model = new HashMap();
    model.put("tasksToRefuse", tasksToRefuse);
    model.put("taskID", taskID);
    return new ModelAndView("listTasksToRefuse", model);
  }

//------------------------------------------------------------------------------
  public ModelAndView listMyRefusedTasks(HttpServletRequest request,
                                         HttpServletResponse response) throws
      Exception {
    Map model = new HashMap();
    String userID = caDelegater.getRemoteUser(request);
    model.put("myRefusedTasks", flowTaskManager.findMyRefusedTasks(userID));
    return new ModelAndView("listMyRefusedTasks", model);
  }

//------------------------------------------------------------------------------
  public ModelAndView refuseTask(HttpServletRequest request,
                                 HttpServletResponse response) throws
      Exception {
    String taskID = request.getParameter("taskID");
    String tasksToRefuse[] = request.getParameterValues("tasksToRefuse");
    String userID = caDelegater.getRemoteUser(request);
    String refusedFor = request.getParameter("refusedFor");
    int numRefused = 0;
    if (tasksToRefuse != null && tasksToRefuse.length > 0) {
      numRefused = flowTaskManager.refuseTasks(taskID, tasksToRefuse,
                                               refusedFor,
                                               userID);
    }
    //打印关闭窗口的代码到客户端
    try {
      java.io.PrintWriter out = (java.io.PrintWriter) response.getWriter();
      out.print("<script language=\"JavaScript\">");
      out.print("try{");
      if (numRefused > 0) {
        String outString = "alert('" +
            this.getText("task.refuse_ok",
                         new Integer(numRefused).toString(), request.getLocale()) +
            "');";
        out.print(outString);
        out.print("window.history.back();");
        if (log.isDebugEnabled()) {
          log.debug(outString);
        }
      }
      else {
        String outString = "alert('" +
            this.getText("task.refuse_fail", request.getLocale()) + "');";
        out.print(outString);
        if (log.isDebugEnabled()) {
          log.debug(outString);
        }
      }
      out.print("opener.parent.location.reload();window.close();");
      out.print("}catch(e){window.close();}</script>");
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

//------------------------------------------------------------------------------
  public ModelAndView abortTask(HttpServletRequest request,
                                HttpServletResponse response) throws
      Exception {
    String taskID = request.getParameter("taskID");
    FlowTask task = flowTaskManager.getFlowTask(taskID);
    if (!task.getTaskState().equals(FlowTask.TASK_STATE_LOCKED)) {
      log.warn("任务[" + taskID + "]状态为[" + task.getTaskState() + "]，不能放弃！");
      throw new FlowTaskException(ExceptionMessage.
                                  ERROR_FLOWTASK_ABORT_INVALID_STATE);
    }
    String userID = caDelegater.getRemoteUser(request);

    if (flowTaskManager.isTaskOwner(userID, taskID)) {
      flowTaskManager.abortTask(userID, taskID);
      if (log.isDebugEnabled()) {
        log.debug("用户[" + userID + "]放弃任务[" + taskID + "]");
      }
    }
    else {
      log.warn("用户[" + userID + "]试图放弃不属于它的任务[" + taskID + "]");
    }
    //刷新父窗口
    ResponseWriter.openerParentReloadClose(response);
    return null;
  }

//------------------------------------------------------------------------------
  public ModelAndView showExecutingTask(HttpServletRequest request,
                                        HttpServletResponse response) throws
      Exception {
    String taskID = request.getParameter("taskID");
    String userID = caDelegater.getRemoteUser(request);
    if (log.isDebugEnabled()) {
      log.debug("用户[" + userID + "]查看执行中的任务[" + taskID + "]");
    }
    if (!flowTaskManager.isTaskOwner(userID, taskID)) {
      return new ModelAndView("showExecutingTaskError", "message",
                              ExceptionMessage.ERROR_FLOWTASK_NOT_TASKOWNER);
    }

    Map model = new HashMap();
    FlowTask task = flowTaskManager.getFlowTask(taskID);
    model.put("taskID", task.getTaskID());
    model.put("taskIDParamName", IFlowDriver.DEFAULT_FLOW_TASK_PARAM_NAME);
    model.put("flowDriver", task.getFlowNodeBinding().getWorkflowDriver());

    WorkflowMeta flowMetaWithFile =
        flowMetaManager.getWorkflowMetaWithFile(task.getFlowNodeBinding().
                                                getFlowDeploy().
                                                getWorkflowMeta().getFlowMetaID().
                                                toString());
    //任务是否可以分配
    WorkflowNode workflowNode =
        flowMetaWithFile.findWorkflowNodeByID(task.getFlowNodeBinding().
                                              getFlowNodeID());
    if (workflowNode != null && workflowNode.isDistributable()) {
      if (log.isDebugEnabled()) {
        log.debug("任务[" + task.getTaskID() + "]对应节点[" +
                  workflowNode.getNodeName() + "]可以分配");
      }
      model.put("distributeTask", "##");
    }

    return new ModelAndView("showExecutingTask", model);
  }

//------------------------------------------------------------------------------
  public ModelAndView searchToDistribute(HttpServletRequest request,
                                         HttpServletResponse response) throws
      Exception {
    return new ModelAndView("searchToDistribute", "taskID",
                            request.getParameter("taskID"));
  }

//------------------------------------------------------------------------------
  public ModelAndView searchToAssign(HttpServletRequest request,
                                     HttpServletResponse response) throws
      Exception {
    return new ModelAndView("searchToAssign", "taskID",
                            request.getParameter("taskID"));
  }

//------------------------------------------------------------------------------
  public ModelAndView assignTask(HttpServletRequest request,
                                 HttpServletResponse response) throws
      Exception {
    String taskID = request.getParameter("taskID");
    String userToAssign = request.getParameter("userID");
    String userID = caDelegater.getRemoteUser(request);

    if (flowTaskManager.isTaskAssigner(userID, taskID)) {
      flowTaskManager.assignTask(userToAssign, taskID);
      if (log.isDebugEnabled()) {
        log.debug("用户[" + userID + "]指派任务[" + taskID + "]给了[" + userToAssign +
                  "]");
      }
    }
    else {
      log.warn("用户[" + userID + "]不是任务[" + taskID + "]的合法指派者");
    }

    //打印关闭窗口的代码到客户端
    ResponseWriter.openerParentReloadClose(response);
    return null;
  }

//------------------------------------------------------------------------------
  public ModelAndView distributeTask(HttpServletRequest request,
                                     HttpServletResponse response) throws
      Exception {
    String taskID = request.getParameter("taskID");
    String userToDistribute = request.getParameter("userID");
    String userID = caDelegater.getRemoteUser(request);

    if (flowTaskManager.isTaskOwner(userID, taskID)) {
      flowTaskManager.distributeTask(userID, taskID, userToDistribute);
      if (log.isDebugEnabled()) {
        log.debug("用户[" + userID + "]分配任务[" + taskID + "]");
      }
    }

    //打印关闭窗口的代码到客户端
    ResponseWriter.openerParentReloadClose(response);
    return null;
  }

//------------------------------------------------------------------------------
  public ModelAndView generatePersonDict(HttpServletRequest request,
                                         HttpServletResponse response) throws
      Exception {
    //查询所有的用户
    try {
      Document doc = null;
      Collection allUsers = caDelegater.findAllUsers();
      doc = DocumentHelper.createDocument();

      Element root = doc.addElement("root");
      Element parent = root;
      Element tmpElement = null;

      if (allUsers != null && allUsers.size() > 0) {
        //遍历所有的结果,组织成Document对像
        for (Iterator it = allUsers.iterator(); it.hasNext(); ) {
          User user = (User) it.next();
          tmpElement = parent.addElement("r");
          tmpElement.addAttribute("USERID", user.getId().toString());
          tmpElement.addAttribute("USERNAME", user.getUserName());
          tmpElement.addAttribute("EMAIL", user.getEmail());
          if (log.isDebugEnabled()) {
            log.debug("tmpElement[" + user.getId() + "|"
                      + user.getEmail() + "|" + user.getUserName() + "]");
          }
        }
      }
      if (doc != null) {
        if (log.isDebugEnabled()) {
          log.debug("发送人员信息字典[" + doc + "]");
        }
        XMLWriter writer = new XMLWriter(response.getWriter());
        writer.write(doc);
        writer.flush();
      }
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }

//------------------------------------------------------------------------------
  public ModelAndView login(HttpServletRequest request,
                            HttpServletResponse response) throws
      Exception {
    caDelegater.authenticate(request.getParameter("userID"),
                             request.getParameter("pass"),
                             request,
                             response);
    return null;
  }

//------------------------------------------------------------------------------
  public void setFlowTaskManager(FlowTaskManager flowTaskManager) {
    this.flowTaskManager = flowTaskManager;
  }

  public void setCaDelegater(CADelegater caDelegater) {
    this.caDelegater = caDelegater;
  }

  public void setFlowMetaManager(FlowMetaManager flowMetaManager) {
    this.flowMetaManager = flowMetaManager;
  }

  public String getText(String msgKey, Locale local) {
    return getMessageSourceAccessor().getMessage(msgKey, local);
  }

  public String getText(String msgKey, String arg, Locale local) {
    return getText(msgKey, new Object[] {arg}
                   , local);
  }

  public String getText(String msgKey, Object[] args, Locale local) {
    return getMessageSourceAccessor().getMessage(msgKey, args, local);
  }

  public void setFlowDeployManager(FlowDeployManager flowDeployManager) {
    this.flowDeployManager = flowDeployManager;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

}