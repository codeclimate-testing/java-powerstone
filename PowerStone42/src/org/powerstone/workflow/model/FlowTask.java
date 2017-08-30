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
package org.powerstone.workflow.model;

import java.util.*;

import org.apache.commons.lang.builder.*;
import org.apache.commons.logging.*;
import org.powerstone.workflow.exception.*;
import org.powerstone.workflow.util.*;

/**
 * @hibernate.class table="WF_FLOW_TASK"
 * @hibernate.query name="NewTaskByNode"
 *  query="select task from FlowTask task join task.flowNodeBinding node
 *  where node.nodeBindingID = ? and task.taskState = ?"

 * @hibernate.query name="MyNewTasksKinds"
 *  query="select bt.typeID,count(*) from NewTask nt join nt.flowTask task join
 *  task.flowNodeBinding.flowDeploy.workflowMeta.businessType bt
 *  where nt.taskCandidateUserID = ? and task.taskState in (?) group by bt"

 * @hibernate.query name="MyExecutingTasksKinds"
 *  query="select bt.typeID,count(*) from FlowTaskUser tu join tu.flowTask task join
 *  tu.flowTask.flowNodeBinding.flowDeploy.workflowMeta.businessType bt
 *  where tu.userID = ? and task.taskState in (?,?) group by bt"

 * @hibernate.query name="TasksToAssign"
 *  query="select task from FlowTask task where task.taskState in (?)"

 * @hibernate.query name="MyTasksToAssign"
 *  query="select task from FlowTaskAssigner fta join fta.flowTask task
 *  where fta.userID = ? and task.taskState in (?)"

 * @hibernate.query name="MyTasksToAssignNum"
 *  query="select count(*) from FlowTaskAssigner fta join fta.flowTask task
 *  where fta.userID = ? and task.taskState in (?)"

 * @hibernate.query name="MyRefusedTasks"
 *  query="select task from FlowTaskUser tu join tu.flowTask task where
 *  task.taskState in (?) and tu.userID = ?"

 * @hibernate.query name="NewTasksNotEmailed"
 *  query="select task from FlowTask task where task.taskState in (?) and
 *  task.sendEmail = ?"

 * @hibernate.query name="MyFinishedTasksKinds"
 *  query="select bt.typeID,count(*) from FlowTaskUser tu join tu.flowTask task join
 *  tu.flowTask.flowNodeBinding.flowDeploy.workflowMeta.businessType bt
 *  where tu.userID = ? and task.taskState= ? group by bt"

 * @hibernate.query name="MyNewTasksNumByType"
 *  query="select count(*) from NewTask nt join nt.flowTask task join
 *  task.flowNodeBinding.flowDeploy.workflowMeta.businessType bt
 *  where nt.taskCandidateUserID = ? and task.taskState in(?) and bt.typeID = ?"

 * @hibernate.query name="MyExecutingTasksNumByType"
 *  query="select count(*) from FlowTaskUser tu join tu.flowTask task join
 *  tu.flowTask.flowNodeBinding.flowDeploy.workflowMeta.businessType bt
 *  where tu.userID = ? and task.taskState in(?,?) and bt.typeID = ?"

 * @hibernate.query name="MyNewTasksByType"
 *  query="select task from NewTask nt join nt.flowTask task join
 *  task.flowNodeBinding.flowDeploy.workflowMeta.businessType bt
 *  where nt.taskCandidateUserID = ? and bt.typeID = ? and task.taskState in(?)
 *  order by task.createTime desc"

 * @hibernate.query name="MyExecutingTasksByType"
 *  query="select task from FlowTaskUser tu join tu.flowTask task join
 *  tu.flowTask.flowNodeBinding.flowDeploy.workflowMeta.businessType bt
 *  where tu.userID = ? and bt.typeID = ? and task.taskState in(?,?)
 *  order by task.startTime desc"

 * @hibernate.query name="MyFinishedTasksNumByType"
 *  query="select count(*) from FlowTaskUser tu join tu.flowTask task join
 *  tu.flowTask.flowNodeBinding.flowDeploy.workflowMeta.businessType bt
 *  where tu.userID = ? and task.taskState in(?) and bt.typeID = ?"

 * @hibernate.query name="MyFinishedTasksByType"
 *  query="select task from FlowTaskUser tu join tu.flowTask task join
 *  tu.flowTask.flowNodeBinding.flowDeploy.workflowMeta.businessType bt
 *  where tu.userID = ? and bt.typeID = ? and task.taskState in(?)
 *  order by task.overTime desc"

 * @hibernate.query name="TaskByNodeAndProc"
 *  query="select task from FlowTask task join task.flowNodeBinding node
 *  join task.flowProcTransaction.flowProc flowProc
 *  where node.flowNodeID = ? and flowProc.flowProcID = ?"

 * @hibernate.query name="TasksByProc"
 *  query="select task from FlowTask task
 *  join task.flowProcTransaction.flowProc flowProc
 *  where flowProc.flowProcID = ?"

 * <p>Title: PowerStone</p>
 */

public class FlowTask
    extends BaseObject {
  protected static Log log = LogFactory.getLog(FlowTask.class);
  public static final String TASK_STATE_FREE = "free";
  public static final String TASK_STATE_LOCKED = "locked";
  public static final String TASK_STATE_FINISHED = "finished";
  public static final String TASK_STATE_ASSIGNED = "assigned";

  //任务正在等待某人指派
  public static final String TASK_STATE_NEED_TO_ASSIGN = "need_to_assign";
  public static final String TASK_STATE_REFUSED = "refused";
  public static final Integer NEW_TASK_NOT_EMAILED = new Integer(0);

  private Long taskID = new Long( -1);
  private String taskState = TASK_STATE_FREE;
  private String createTime;
  private String startTime;
  private String overTime;
  private String previewText;

  private FlowProcTransaction flowProcTransaction;
  private FlowNodeBinding flowNodeBinding;

  private List taskUsers = new ArrayList();
  private List taskRoles = new ArrayList();
  private List newTasks = new ArrayList();
  private List taskAssigners = new ArrayList();
  private List taskRefuses = new ArrayList();

  private Integer sendEmail = NEW_TASK_NOT_EMAILED;

  /**
   * @hibernate.id column="PK_FLOW_TASK_ID"
   * 		   unsaved-value="-1"
   *               generator-class="native"
   * @return Long
   */
  public Long getTaskID() {
    return taskID;
  }

  public void setTaskID(Long taskID) {
    this.taskID = taskID;
  }

  /**
   * @hibernate.property
   * 		column="VC_TASK_STATE"
   * 		length="255"
   * 		type="string"
   *            not-null="true"
   * @return String
   */
  public String getTaskState() {
    return taskState;
  }

  public void free() {
    setTaskState(this.TASK_STATE_FREE);
  }

  public void lock() {
    setTaskState(this.TASK_STATE_LOCKED);
  }

  /**
   * 修改任务状态为TASK_STATE_FINISHED；
   * 删除所有TaskCandidate
   */
  public void finish() {
    setTaskState(this.TASK_STATE_FINISHED);
    this.setOverTime(TimeUtil.getTimeStamp());

    for (Iterator it = this.getNewTasks().iterator(); it.hasNext(); ) {
      NewTask nt = (NewTask) it.next();
      nt.setFlowTask(null);
    }
    getNewTasks().clear();
  }

  /**
   * needAsssign
   *
   * @param userID String
   */
  public void needAsssign(String userID) {
    FlowTaskAssigner flowTaskAssigner = new FlowTaskAssigner();
    flowTaskAssigner.setFlowTask(this);
    flowTaskAssigner.setUserID(userID);
    if (this.getTaskAssigners().indexOf(flowTaskAssigner) == -1) {
      this.getTaskAssigners().add(flowTaskAssigner);
      this.setTaskState(FlowTask.TASK_STATE_NEED_TO_ASSIGN);
    }
  }

  public void refuse(String refuseFor,String refUserID) {
    setTaskState(this.TASK_STATE_REFUSED);
    FlowTaskRefuse ftr=new FlowTaskRefuse();
    ftr.setFlowTask(this);
    ftr.setRefuseFor(refuseFor);
    ftr.setRefuseUser(refUserID);
    this.getTaskRefuses().add(ftr);
  }

  public void abort(String userID) {
    if (this.getTaskUsers().size() == 1) {
      free();
    }
    FlowTaskUser flowTaskUser = null;
    for (Iterator it = getTaskUsers().iterator(); it.hasNext(); ) {
      FlowTaskUser ftu = (FlowTaskUser) it.next();
      if (ftu.getUserID().equals(userID)) {
        flowTaskUser = ftu;
        break;
      }
    }
    if (flowTaskUser != null) {
      getTaskUsers().remove(flowTaskUser);
      flowTaskUser.setFlowTask(null);
    }
    else {
      log.error("用户[" + userID + "]不是任务[" + this.getTaskID()
                + "]的持有人，无权放弃！？");
      throw new RuntimeException(ExceptionMessage.ERROR_FLOWTASK_INVALID_STATE);
    }
  }

  public void assignToUser(String userID) {
    this.checkOutTask(userID);
    setTaskState(this.TASK_STATE_ASSIGNED);
  }

  public void setTaskState(String taskState) {
    this.taskState = taskState;
  }

  /**
   * @hibernate.property
   * 		column="VC_CREATE_TIME"
   * 		length="255"
   * 		type="string"
   *            not-null="true"
   * @return String
   */
  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  /**
   * @hibernate.property
   * 		column="VC_START_TIME"
   * 		length="255"
   * 		type="string"
   * @return String
   */
  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  /**
   * @hibernate.property
   * 		column="VC_OVER_TIME"
   * 		length="255"
   * 		type="string"
   * @return String
   */
  public String getOverTime() {
    return overTime;
  }

  public void setOverTime(String overTime) {
    this.overTime = overTime;
  }

  /**
   * @hibernate.many-to-one
   * column="FK_FLOW_PROC_TRANSACTION_ID"
   * class="org.powerstone.workflow.model.FlowProcTransaction"
   * @return FlowProcTransaction
   */
  public FlowProcTransaction getFlowProcTransaction() {
    return flowProcTransaction;
  }

  public void setFlowProcTransaction(FlowProcTransaction flowProcTransaction) {
    this.flowProcTransaction = flowProcTransaction;
  }

  public FlowProc getFlowProc() {
    return this.getFlowProcTransaction().getFlowProc();
  }

  /**
   * @hibernate.many-to-one
   * column="FK_FLOW_NODE_BINDING_ID"
   * class="org.powerstone.workflow.model.FlowNodeBinding"
   * @return FlowNodeBinding
   */
  public FlowNodeBinding getFlowNodeBinding() {
    return flowNodeBinding;
  }

  public void setFlowNodeBinding(FlowNodeBinding flowNodeBinding) {
    this.flowNodeBinding = flowNodeBinding;
  }

  /**
   * @hibernate.bag name="taskUsers"
   * cascade="all-delete-orphan"
   * lazy="true"
   * inverse="true"
   * @hibernate.collection-key
   * column="FK_FLOW_TASK_ID"
   * @hibernate.collection-one-to-many
   * class="org.powerstone.workflow.model.FlowTaskUser"
   * @return List
   */
  public List getTaskUsers() {
    return taskUsers;
  }

  public void checkOutTask(String userID) {
    this.lock();
    this.setStartTime(TimeUtil.getTimeStamp());

    for (Iterator it = getTaskUsers().iterator(); it.hasNext(); ) {
      FlowTaskUser taskUser = (FlowTaskUser) it.next();
      if (taskUser.getUserID().equals(userID)) {
        return;
      }
    }
    FlowTaskUser flowTaskUser = new FlowTaskUser();
    flowTaskUser.setFlowTask(this);
    flowTaskUser.setUserID(userID);
    getTaskUsers().add(flowTaskUser);
  }

  public void setTaskUsers(List taskUsers) {
    this.taskUsers = taskUsers;
  }

  /**
   * @hibernate.bag name="taskRoles"
   * cascade="all-delete-orphan"
   * lazy="true"
   * inverse="true"
   * @hibernate.collection-key
   * column="FK_FLOW_TASK_ID"
   * @hibernate.collection-one-to-many
   * class="org.powerstone.workflow.model.FlowTaskRole"
   * @return List
   */
  public List getTaskRoles() {
    return taskRoles;
  }

  public void setTaskRoles(List taskRoles) {
    this.taskRoles = taskRoles;
  }

  /**
   * @hibernate.bag name="taskAssigners"
   * cascade="all-delete-orphan"
   * lazy="true"
   * inverse="true"
   * @hibernate.collection-key
   * column="FK_FLOW_TASK_ID"
   * @hibernate.collection-one-to-many
   * class="org.powerstone.workflow.model.FlowTaskAssigner"
   * @return List
   */
  public List getTaskAssigners() {
    return taskAssigners;
  }

  public void setTaskAssigners(List taskAssigners) {
    this.taskAssigners = taskAssigners;
  }

  /**
   * @hibernate.bag name="newTasks"
   * cascade="all-delete-orphan"
   * lazy="true"
   * inverse="true"
   * @hibernate.collection-key
   * column="FK_FLOW_TASK_ID"
   * @hibernate.collection-one-to-many
   * class="org.powerstone.workflow.model.NewTask"
   * @return List
   */
  public List getNewTasks() {
    return newTasks;
  }

  public void setNewTasks(List newTasks) {
    this.newTasks = newTasks;
  }

  /**
   * @hibernate.bag name="taskRefuses"
   * cascade="all-delete-orphan"
   * lazy="true"
   * inverse="true"
   * @hibernate.collection-key
   * column="FK_FLOW_TASK_ID"
   * @hibernate.collection-one-to-many
   * class="org.powerstone.workflow.model.FlowTaskRefuse"
   * @return List
   */
  public List getTaskRefuses() {
    return taskRefuses;
  }

  public void setTaskRefuses(List taskRefuses) {
    this.taskRefuses = taskRefuses;
  }

  /**
   * 给任务增加一个候选人
   * @param userID String
   */
  public void addTaskCandidate(String userID) {
    NewTask nt = new NewTask();
    nt.setFlowTask(this);
    nt.setTaskCandidateUserID(userID);
    if (getNewTasks().indexOf(nt) == -1) {
      this.getNewTasks().add(nt);
    }
  }

  //作为新任务，能够被领取
  public boolean hasTaskCandidate() {
    if (log.isDebugEnabled()) {
      log.debug("任务[" + this.getTaskID() + "]hasTaskCandidate[" +
                getNewTasks().size() + "]");
    }
    return getNewTasks().size() > 0;
  }

  /**
   * @hibernate.property
   * 		column="VC_SEND_EMAIL"
   * 		type="int"
   *            not-null="true"
   * @return Integer
   */
  public Integer getSendEmail() {
    return sendEmail;
  }

  public void setSendEmail(Integer sendEmail) {
    this.sendEmail = sendEmail;
  }

  public String getPreviewText() {
    return previewText;
  }

  public void setPreviewText(String previewText) {
    this.previewText = previewText;
  }

  public void emailTask() {
    this.setSendEmail(new Integer(1));
  }

  public boolean isEmailed() {
    return this.getSendEmail().intValue() == 1;
  }

//------------------------------------------------------------------------------
  public boolean equals(Object object) {
    if (! (object instanceof FlowTask)) {
      return false;
    }
    FlowTask ft = (FlowTask) object;
    return new EqualsBuilder()
        .append(this.getFlowNodeBinding(), ft.getFlowNodeBinding())
        .append(this.getTaskState(), ft.getTaskState())
        .append(this.getCreateTime(), ft.getCreateTime())
        .isEquals();
  }

  public int hashCode() {
    //随机选择两个奇数，每个类不同
    return new HashCodeBuilder(216335803, 217569255)
        .append(this.getFlowNodeBinding().hashCode())
        .append(this.getTaskState())
        .append(this.getCreateTime())
        .toHashCode();
  }

  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("NodeBinding", this.getFlowNodeBinding().toString())
        .append("TaskState", this.getTaskState())
        .append("SendEmail", this.getSendEmail())
        .append("CreateTime", this.getCreateTime())
        .toString();
  }

}
