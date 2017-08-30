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
package org.powerstone.workflow.service.impl;

import org.powerstone.workflow.service.FlowTaskManager;
import org.powerstone.workflow.model.FlowTask;
import org.powerstone.workflow.model.FlowProcTransaction;
import org.powerstone.workflow.model.FlowNodeBinding;
import org.powerstone.workflow.dao.FlowTaskDAO;
import org.powerstone.workflow.service.FlowProcManager;

import java.util.List;
import org.powerstone.ca.CADelegater;

import java.util.Iterator;
import org.powerstone.workflow.service.FlowDeployManager;
import org.powerstone.workflow.service.BusinessTypeManager;

import java.util.ArrayList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.HashMap;
import org.powerstone.workflow.model.BusinessType;
import org.powerstone.workflow.util.TimeUtil;
import org.powerstone.workflow.service.FlowMetaManager;
import org.powerstone.workflow.model.WorkflowMeta;
import org.powerstone.workflow.model.WorkflowNode;
import org.powerstone.workflow.util.FlowDataField;
import org.powerstone.workflow.exception.FlowTaskException;
import org.powerstone.workflow.exception.ExceptionMessage;
import org.powerstone.workflow.model.FlowProcTransition;

public class FlowTaskManagerImpl
    implements FlowTaskManager {
  private static Log log = LogFactory.getLog(FlowTaskManagerImpl.class);
  private FlowTaskDAO flowTaskDAO;
  private FlowProcManager flowProcManager;
  private CADelegater caDelegater;
  private FlowDeployManager deployManager;
  private BusinessTypeManager businessTypeManager;
  private FlowMetaManager flowMetaManager;

  public boolean isTaskOwner(String userID, String taskID) {
    return flowTaskDAO.isTaskOwner(userID, new Long(taskID));
  }

  public boolean isTaskAssigner(String userID, String taskID) {
    return flowTaskDAO.isTaskAssigner(userID, new Long(taskID));
  }

  public FlowTask getFlowTask(String taskID) {
    return flowTaskDAO.getFlowTask(new Long(taskID));
  }

  public FlowTask createFlowTask(FlowProcTransaction procTransaction,
                                 FlowNodeBinding flowNodeBinding) {
    FlowTask flowTask = new FlowTask();
    flowTask.setCreateTime(TimeUtil.getTimeStamp());
    flowTask.setFlowNodeBinding(flowNodeBinding);
    flowTask.free();

    procTransaction.addFlowTask(flowTask);
    flowTaskDAO.saveFlowTask(flowTask);
    flowProcManager.saveFlowProcTransaction(procTransaction);

    if (log.isDebugEnabled()) {
      log.debug("---------------------" + flowTask.getCreateTime());
    }
    return flowTask;
  }

  public void checkOutTask(String userID, String taskID) {
    FlowTask flowTask = getFlowTask(taskID);
    if (!flowTask.getTaskState().equals(flowTask.TASK_STATE_FREE)) {
      log.warn("用户[" + userID + "]试图领取的任务[" + taskID + "]已经被别人抢先领走！");
      throw new FlowTaskException(ExceptionMessage.ERROR_FLOWTASK_INVALID_STATE);
    }

    flowTask.checkOutTask(userID);
    flowTaskDAO.saveFlowTask(flowTask);
  }

  public void assignTask(String userID, String taskID) {
    FlowTask flowTask = getFlowTask(taskID);
    flowTask.assignToUser(userID);
    flowTaskDAO.saveFlowTask(flowTask);
    if (log.isDebugEnabled()) {
      log.debug("任务[" + taskID + "]被assign给了用户[" + userID + "]");
    }
  }

  /**
   * needAsssign
   *
   * @param userID String
   * @param taskID String
   */
  public void needAsssign(String userID, String taskID) {
    FlowTask flowTask = getFlowTask(taskID);
    flowTask.needAsssign(userID);
    flowTaskDAO.saveFlowTask(flowTask);
    if (log.isDebugEnabled()) {
      log.debug("任务[" + taskID + "]需要由用户[" + userID + "]指派");
    }
  }

  public void distributeTask(String userID, String taskID,
                             String userToDistribute) {
    FlowTask flowTask = getFlowTask(taskID);
    abortTask(userID, taskID);

    flowTask.checkOutTask(userToDistribute);
    flowTaskDAO.saveFlowTask(flowTask);
  }

  public void fenfaNewTask(String userID, String taskID) {
    FlowTask flowTask = getFlowTask(taskID);
    flowTask.addTaskCandidate(userID);
    flowTaskDAO.saveFlowTask(flowTask);
  }

  public void finishTask(String taskID) {
    FlowTask flowTask = getFlowTask(taskID);
    flowTask.finish();
    flowTaskDAO.saveFlowTask(flowTask);
  }

  public void emailTask(String taskID) {
    FlowTask flowTask = getFlowTask(taskID);
    if (flowTask.isEmailed() ||
        !flowTask.getTaskState().equals(FlowTask.TASK_STATE_FREE)) {
      log.warn("任务[" + taskID + "]不是新的或已经通知过了!");
      return;
    }
    flowTask.emailTask();
    flowTaskDAO.saveFlowTask(flowTask);
    if (log.isDebugEnabled()) {
      log.debug("flowTask[" + flowTask + "]");
    }
  }

  public void abortTask(String userID, String taskID) {
    FlowTask flowTask = getFlowTask(taskID);
    flowTask.abort(userID);
    flowTaskDAO.saveFlowTask(flowTask);
  }

  public List findAllMyNewTasks(String userID) {
    List coll =
        deployManager.calcAllMyPerformingNodes(userID);
    List myPerformingNodes = new ArrayList();
    HashMap myPerformingNodesMap = new HashMap();
    for (Iterator it = coll.iterator(); it.hasNext(); ) {
      FlowNodeBinding nodeBinding = (FlowNodeBinding) it.next();
      myPerformingNodesMap.put(nodeBinding.getNodeBindingID().toString(),
                               nodeBinding);
    }
    for (Iterator it = myPerformingNodesMap.keySet().iterator(); it.hasNext(); ) {
      myPerformingNodes.add(myPerformingNodesMap.get(it.next()));
    }
    List result = flowTaskDAO.findAllMyNewTasks(myPerformingNodes);
    this.taskPreviewProcess(result);

    return result;
  }

  public List findMyExecutingTasksKinds(String userID) {
    List result = new ArrayList();
    for (Iterator it = flowTaskDAO.findMyExecutingTasksKinds(userID).iterator();
         it.hasNext(); ) {
      Object[] obj = (Object[]) it.next();
      if (log.isDebugEnabled()) {
        log.debug("[" + obj[1].getClass() + "]");
      }

      if (obj.length == 2) {
        BusinessType bt =
            businessTypeManager.getBusinessType( ( (Long) obj[0]).toString());
        //克隆一个,因为.setTasksNum( (Integer) obj[1]);对其进行了修改
        BusinessType currBusinessType = new BusinessType();
        currBusinessType.setTypeID(bt.getTypeID());
        currBusinessType.setTypeName(bt.getTypeName());
        currBusinessType.setTasksNum( (Integer) obj[1]);
        result.add(currBusinessType);
      }
    }
    BusinessType bt = new BusinessType();
    bt.otherType();
    bt.setTasksNum(flowTaskDAO.findMyOtherExecutingTasksNum(userID));
    if (bt.getTasksNum().intValue() > 0) {
      result.add(bt);
    }

    return result;
  }

  public List findMyRefusedTasks(String userID) {
    return flowTaskDAO.findMyRefusedTasks(userID);
  }

  public List findNewTasksNotEmailed() {
    return flowTaskDAO.findNewTasksNotEmailed();
  }

  public List findMyFinishedTasksKinds(String userID) {
    List result = new ArrayList();
    for (Iterator it = flowTaskDAO.findMyFinishedTasksKinds(userID).iterator();
         it.hasNext(); ) {
      Object[] obj = (Object[]) it.next();
      if (log.isDebugEnabled()) {
        log.debug("[" + obj.length + "]");
      }

      if (obj.length == 2) {
        BusinessType bt =
            businessTypeManager.getBusinessType( ( (Long) obj[0]).toString());
        //克隆一个,因为.setTasksNum( (Integer) obj[1]);对其进行了修改
        BusinessType currBusinessType = new BusinessType();
        currBusinessType.setTypeID(bt.getTypeID());
        currBusinessType.setTypeName(bt.getTypeName());
        currBusinessType.setTasksNum( (Integer) obj[1]);
        result.add(currBusinessType);
      }
    }
    BusinessType bt = new BusinessType();
    bt.otherType();
    bt.setTasksNum(flowTaskDAO.findMyOtherFinishedTasksNum(userID));

    if (bt.getTasksNum().intValue() > 0) {
      result.add(bt);
    }

    return result;
  }

  public Integer findMyNewTasksNum(String userID) {
    return flowTaskDAO.findMyNewTasksNum(userID);
  }

  public List findMyNewTasksKinds(String userID) {
    List result = new ArrayList();
    for (Iterator it = flowTaskDAO.findMyNewTasksKinds(userID).iterator();
         it.hasNext(); ) {
      Object[] obj = (Object[]) it.next();
      if (log.isDebugEnabled()) {
        log.debug("[" + obj[1].getClass() + "]");
      }

      if (obj.length == 2) {
        BusinessType bt =
            businessTypeManager.getBusinessType( ( (Long) obj[0]).toString());
        //克隆一个,因为.setTasksNum( (Integer) obj[1]);对其进行了修改
        BusinessType currBusinessType = new BusinessType();
        currBusinessType.setTypeID(bt.getTypeID());
        currBusinessType.setTypeName(bt.getTypeName());
        currBusinessType.setTasksNum( (Integer) obj[1]);
        result.add(currBusinessType);
      }
    }
    BusinessType bt = new BusinessType();
    bt.otherType();
    bt.setTasksNum(flowTaskDAO.findMyOtherNewTasksNum(userID));
    if (bt.getTasksNum().intValue() > 0) {
      result.add(bt);
    }

    return result;
  }

  public Integer findMyNewTasksNumByType(String userID, String typeID) {
    return flowTaskDAO.findMyNewTasksNumByType(userID, new Long(typeID));
  }

  public List findMyNewTasksByType(String userID, String typeID, int pageNum,
                                   int pageSize) {
    List result =
        flowTaskDAO.findMyNewTasksByType(userID, new Long(typeID),
                                         pageNum, pageSize);

    taskPreviewProcess(result);
    return result;
  }

  public Integer findMyExecutingTasksNum(String userID) {
    return flowTaskDAO.findMyExecutingTasksNum(userID);
  }

  public Integer findMyExecutingTasksNumByType(String userID, String typeID) {
    return flowTaskDAO.findMyExecutingTasksNumByType(userID, new Long(typeID));
  }

  public List findMyExecutingTasksByType(String userID, String typeID,
                                         int pageNum, int pageSize) {
    List result = flowTaskDAO.findMyExecutingTasksByType(userID,
        new Long(typeID),
        pageNum, pageSize);

    taskPreviewProcess(result);
    return result;
  }

  public Integer findMyRefusedTasksNum(String userID) {
    return flowTaskDAO.findMyRefusedTasksNum(userID);
  }

  public Integer findMyFinishedTasksNum(String userID) {
    return flowTaskDAO.findMyFinishedTasksNum(userID);
  }

  public Integer findMyFinishedTasksNumByType(String userID, String typeID) {
    return flowTaskDAO.findMyFinishedTasksNumByType(userID, new Long(typeID));
  }

  public List findMyFinishedTasksByType(String userID, String typeID,
                                        int pageNum, int pageSize) {
    List result =
        flowTaskDAO.findMyFinishedTasksByType(userID, new Long(typeID),
                                              pageNum, pageSize);

    taskPreviewProcess(result);
    return result;
  }

  public FlowTask findTaskByNodeAndProc(String flowNodeID,
                                        String flowProcID) {
    return flowTaskDAO.findTaskByNodeAndProc(flowNodeID,
                                             new Long(flowProcID));
  }

  /**
   * 增加任务预览内容
   * @param result List
   */
  public void taskPreviewProcess(List result) {
    for (Iterator it = result.iterator(); it.hasNext(); ) {
      FlowTask task = (FlowTask) it.next();
      WorkflowMeta flowMetaWithFile =
          flowMetaManager.getWorkflowMetaWithFile(task.getFlowNodeBinding().
                                                  getFlowDeploy().
                                                  getWorkflowMeta().
                                                  getFlowMetaID().
                                                  toString());
      WorkflowNode workflowNode =
          flowMetaWithFile.findWorkflowNodeByID(task.getFlowNodeBinding().
                                                getFlowNodeID());
      String[] nodeVariablesToPreview = workflowNode.getVariableToPreview();
      HashMap dataFields = flowMetaWithFile.getDataFields();
      HashMap procState = task.getFlowProc().getProcState();
      String taskPreviewStr = "";
      if (procState != null &&
          procState.size() > 0 &&
          nodeVariablesToPreview != null &&
          nodeVariablesToPreview.length > 0 &&
          dataFields != null &&
          dataFields.size() > 0) {
        taskPreviewStr = "(";
        for (int i = 0; i < nodeVariablesToPreview.length; i++) {
          FlowDataField fdField = (FlowDataField) dataFields.get(
              nodeVariablesToPreview[i]);
          if (fdField == null) {
            taskPreviewStr += ("预览变量" + nodeVariablesToPreview[i] + "不存在!");
            continue;
          }
          else {
            taskPreviewStr += (fdField.getFieldName() + ":"
                               + "<font color='red'>" +
                               (procState.get(nodeVariablesToPreview[i]) != null
                                ? procState.get(nodeVariablesToPreview[i])
                                : "&nbsp"
                                ) + "</font>"
                               );
            if ( (i + 1) < nodeVariablesToPreview.length) {
              taskPreviewStr += "|";
            }
          }
        }
        taskPreviewStr += ")";
      }
//      if (log.isDebugEnabled()) {
//        log.debug("任务预览[" + taskPreviewStr + "]");
//      }
      task.setPreviewText(taskPreviewStr);
    }
  }

  public Integer findMyTasksToAssignNum(String userID) {
    return flowTaskDAO.findMyTasksToAssignNum(userID);
  }

  public List findMyTasksToAssign(String userID) {
    List result = flowTaskDAO.findMyTasksToAssign(userID);
    taskPreviewProcess(result);
    return result;
  }

  public List findTasksByProc(String flowProcID) {
    List result =
        flowTaskDAO.findTasksByProc(new Long(flowProcID));
    for (Iterator it = result.iterator(); it.hasNext(); ) {
      FlowTask ft = (FlowTask) it.next();
      String driverName =
          ft.getFlowNodeBinding().getWorkflowDriver().getFlowDriverName();
      int newTasksNum = ft.getNewTasks().size();
      int taskUsersNum = ft.getTaskUsers().size();
      if (log.isDebugEnabled()) {
        log.debug("driverName=" + driverName + "|newTasksNum=" + newTasksNum +
                  "|taskUsersNum=" + taskUsersNum + "|TaskState=" +
                  ft.getTaskState());
      }
    }
    return result;
  }

//------------------------------------------------------------------------------
  /**
   计算出此人务节点在后置事务中的可及范围postRange；
   确定是否能回滚:
      如果postRange中存在非free的任务,则不能回滚
   回滚：
      删除postRange中的任务和路径,返回true;
   不能回滚:返回false;
   */
  private boolean taskRollBack(FlowTask flowTask) {
    String currNodeID = flowTask.getFlowNodeBinding().getFlowNodeID();
    FlowProcTransaction postTransaction =
        flowTask.getFlowProc().getPostTransactionOfNode(currNodeID);
    if (postTransaction == null) {
      log.warn("~~~~~~Task(" + flowTask +
               ")没有postTransaction,流程叶子节点不能回滚！~~~~~~");
      return false;
    }

    HashMap postRange = postTransaction.getRangeOfNode(currNodeID);
    boolean canRollBack = true;
    for (Iterator it = postRange.keySet().iterator(); it.hasNext(); ) {
      Object obj = it.next();
      if (obj instanceof FlowTask) {
        FlowTask ft = (FlowTask) obj;
        if (ft.getTaskState().equals(FlowTask.TASK_STATE_FREE)) {
          continue;
        }
        else {
          if (log.isDebugEnabled()) {
            log.debug("节点[" + currNodeID + "]的'postRange'中的任务(" + ft + ")状态为[" +
                      ft.getTaskState() + "]，导致其不能会滚！");
          }
          canRollBack = false;
          break;
        }
      }
    }

    if (canRollBack) {
      for (Iterator it = postRange.keySet().iterator(); it.hasNext(); ) {
        Object obj = it.next();
        if (obj instanceof FlowProcTransition) {
          FlowProcTransition fpt = (FlowProcTransition) obj;
          postTransaction.removeFlowProcTransition(fpt);
          //flowProcManager.saveFlowProcTransition(fpt);
        }
        if (obj instanceof FlowTask) {
          FlowTask ft = (FlowTask) obj;
          postTransaction.removeFlowTask(ft);
          //flowTaskDAO.saveFlowTask(ft);
        }
      }
      this.flowProcManager.saveFlowProcTransaction(postTransaction);
      if (log.isDebugEnabled()) {
        log.debug("节点[" + currNodeID + "]对应任务[" + flowTask.getTaskID() +
                  "]成功回滚");
      }
      return true;
    }
    else {
      if (log.isDebugEnabled()) {
        log.debug("节点[" + currNodeID + "]对应任务[" + flowTask.getTaskID() +
                  "]没有回滚");
      }
      return false;
    }
  }

  /**
   * 设置任务状态为refused,并记录驳回理由和人员
   * @param taskID String
   * @param refuseFor String
   * @param refUserID String
   * @return boolean
   */
  private boolean refuseTask(String taskID, String refuseFor,
                             String refUserID) {
    FlowTask flowTask = getFlowTask(taskID);
    if (this.taskRollBack(flowTask)) {
      flowTask.refuse(refuseFor, refUserID);
      flowTaskDAO.saveFlowTask(flowTask);
      return true;
    }

    return false;
  }

//------------------------------------------------------------------------------
  /**
   * 驳回任务
   * @param taskID String
   * @param tasksToRefuse String[]
   * @param refuseFor String
   * @param refUserID String
   * @return int
   */
  public int refuseTasks(String taskID, String[] tasksToRefuse,
                         String refuseFor, String refUserID) {
    if (!this.isTaskOwner(refUserID, taskID)) {
      log.warn("用户[" + refUserID + "]试图拒绝不属于他的任务[" + taskID + "]");
      throw new FlowTaskException(ExceptionMessage.
                                  ERROR_FLOWTASK_NOT_TASKOWNER);
    }
    int refuseNum = 0;
    FlowTask flowTask = getFlowTask(taskID);
    List tasksCanRefuse = this.findTasksToRefuse(taskID);
    //驳回其前置任务前,先把该任务的状态置为free
    String oldState = flowTask.getTaskState();
    flowTask.free();
    flowTaskDAO.saveFlowTask(flowTask);
    if (log.isDebugEnabled()) {
      log.debug("驳回其前置任务前,先把该任务[" + taskID + "]状态置为free(否则前置任务铁定无法回滚!)");
    }

    for (int i = 0; i < tasksToRefuse.length; i++) {
      for (Iterator it = tasksCanRefuse.iterator(); it.hasNext(); ) {
        FlowTask ft = (FlowTask) it.next();
        if (ft.getTaskID().toString().equals(tasksToRefuse[i])) {
          if (this.refuseTask(tasksToRefuse[i], refuseFor, refUserID)) {
            refuseNum++;
          }
          break;
        }
      }
    }
    if (refuseNum == 0) { //一个任务也没有驳回
      flowTask.setTaskState(oldState);
      flowTaskDAO.saveFlowTask(flowTask);
      if (log.isDebugEnabled()) {
        log.debug("一个前置任务也没有驳回,把任务[" + taskID + "]置回原来的状态[" + oldState + "]!");
      }
    }

    return refuseNum;
  }

  /**
   * 查找taskID的前置任务（用来驳回）
   * @param taskID String
   * @return List
   */
  public List findTasksToRefuse(String taskID) {
    return this.getFlowTask(taskID).getFlowProcTransaction().getEntranceTasks();
  }

//------------------------------------------------------------------------------
  /**
   * 取回任务:回滚任务，然后checkOut之
   * @param userID String
   * @param taskID String
   * @return boolean
   */
  public boolean getBackFlowTask(String userID, String taskID) {
    FlowTask flowTask = getFlowTask(taskID);
    if (!this.isTaskOwner(userID, taskID)) {
      log.warn("用户[" + userID + "]试图取回不属于他的任务[" + taskID + "]");
      throw new FlowTaskException(ExceptionMessage.
                                  ERROR_FLOWTASK_NOT_TASKOWNER);
    }
    if (!flowTask.getTaskState().equals(FlowTask.TASK_STATE_FINISHED)) {
      log.warn("用户[" + userID + "]试图取回状态为[" + flowTask.getTaskState() + "]的任务");
      throw new FlowTaskException(ExceptionMessage.ERROR_FLOWTASK_INVALID_STATE);
    }
    if (this.taskRollBack(flowTask)) {
      if (flowTask.getFlowNodeBinding().isStatic()) { //防止出现放弃任务后没人看得见的现象
        flowTask.checkOutTask(userID);
      }
      else {
        flowTask.assignToUser(userID);
      }
      this.flowTaskDAO.saveFlowTask(flowTask);
      return true;
    }
    else {
      log.warn("用户[" + userID + "]的任务[" + taskID + "]已经不能取回！");
      return false;
    }
  }

//------------------------------------------------------------------------------
  public void setFlowTaskDAO(FlowTaskDAO flowTaskDAO) {
    this.flowTaskDAO = flowTaskDAO;
  }

  public void setFlowProcManager(FlowProcManager flowProcManager) {
    this.flowProcManager = flowProcManager;
  }

  public void setCaDelegater(CADelegater caDelegater) {
    this.caDelegater = caDelegater;
  }

  public void setDeployManager(FlowDeployManager deployManager) {
    this.deployManager = deployManager;
  }

  public void setBusinessTypeManager(BusinessTypeManager businessTypeManager) {
    this.businessTypeManager = businessTypeManager;
  }

  public void setFlowMetaManager(FlowMetaManager flowMetaManager) {
    this.flowMetaManager = flowMetaManager;
  }

}
