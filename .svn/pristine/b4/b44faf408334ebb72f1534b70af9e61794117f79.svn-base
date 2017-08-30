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
package org.powerstone.workflow.dao.hibernate;

import org.powerstone.workflow.dao.FlowTaskDAO;
import org.powerstone.workflow.model.FlowTask;
import org.powerstone.workflow.model.FlowTaskRole;
import org.powerstone.workflow.model.FlowTaskUser;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Iterator;
import org.powerstone.workflow.model.FlowNodeBinding;
import java.util.ArrayList;
import org.hibernate.*;

public class FlowTaskDAOImpl
    extends HibernateDaoSupport
    implements FlowTaskDAO {
  private static Log log = LogFactory.getLog(FlowTaskDAOImpl.class);

  public FlowTask getFlowTask(Long taskID) {
    return (FlowTask) getHibernateTemplate().load(FlowTask.class,
                                                  taskID);
  }

  public void saveFlowTask(FlowTask flowTask) {
    getHibernateTemplate().saveOrUpdate(flowTask);
    getHibernateTemplate().flush();
  }

  public boolean isTaskOwner(String userID, Long taskID) {
    return (getHibernateTemplate().findByNamedQuery("IsTaskOwner",
        new Object[] {userID, taskID}).size() > 0
            );
  }

  public boolean isTaskAssigner(String userID, Long taskID) {
    return (getHibernateTemplate().findByNamedQuery("IsTaskAssigner",
        new Object[] {userID, taskID}).size() > 0
            );
  }

  public void removeFlowTask(Long taskID) {
    FlowTask flowTask = getFlowTask(taskID);
    getHibernateTemplate().delete(flowTask);
    getHibernateTemplate().flush();
  }

  public FlowTaskUser getFlowTaskUser(Long id) {
    return (FlowTaskUser) getHibernateTemplate().load(FlowTaskUser.class,
        id);
  }

  public FlowTaskRole getFlowTaskRole(Long id) {
    return (FlowTaskRole) getHibernateTemplate().load(FlowTaskRole.class,
        id);
  }

  public List findAllMyNewTasks(List myPerformingNodes) {
    List result = new ArrayList();
    for (Iterator it = myPerformingNodes.iterator(); it.hasNext(); ) {
      FlowNodeBinding nodeBinding = (FlowNodeBinding) it.next();
      List tasks =
          getHibernateTemplate().findByNamedQuery("NewTaskByNode",
                                                  new Object[] {nodeBinding.
                                                  getNodeBindingID(),
                                                  FlowTask.TASK_STATE_FREE}
                                                  );
      result.addAll(tasks);
    }
    return result;
  }

  public List findMyNewTasksByType(String userID, Long typeID, int pageNum,
                                   int pageSize) {
    try {
      Query q = getSession().getNamedQuery("MyNewTasksByType");
      q.setString(0, userID);
      q.setLong(1, typeID.longValue());
      q.setString(2, FlowTask.TASK_STATE_FREE);

      q.setFirstResult( (pageNum - 1) * pageSize);
      q.setMaxResults(pageSize);

      return q.list();
    }
    catch (HibernateException ex) {
      log.info(ex);
    }
    return null;
  }

  public List findMyNewTasksKinds(String userID) {
    return getHibernateTemplate().findByNamedQuery("MyNewTasksKinds",
        new Object[] {userID, FlowTask.TASK_STATE_FREE});
  }

  public Integer findMyNewTasksNum(String userID) {
    return (Integer) getHibernateTemplate().iterate(
        "select count(*) from NewTask nt join nt.flowTask task " +
        " where nt.taskCandidateUserID = '" + userID +
        "' and task.taskState = '" +
        FlowTask.TASK_STATE_FREE + "'").next();
  }

  public Integer findMyNewTasksNumByType(String userID, Long typeID) {
    return (Integer) getHibernateTemplate().findByNamedQuery(
        "MyNewTasksNumByType",
        new Object[] {userID, FlowTask.TASK_STATE_FREE, typeID}).get(0);
  }

  public List findMyExecutingTasksKinds(String userID) {
    return getHibernateTemplate().findByNamedQuery("MyExecutingTasksKinds",
        new Object[] {userID, FlowTask.TASK_STATE_LOCKED,
        FlowTask.TASK_STATE_ASSIGNED});
  }

  public FlowTask findTaskByNodeAndProc(String flowNodeID,
                                        Long flowProcID) {
    List tasks = getHibernateTemplate().findByNamedQuery(
        "TaskByNodeAndProc",
        new Object[] {flowNodeID, flowProcID});
    if (tasks != null && tasks.size() > 0) {
      return (FlowTask) tasks.get(0);
    }
    log.warn("节点[" + flowNodeID + "]没有对应的任务");
    return null;
  }

  public List findMyTasksToAssign(String userID) {
    return getHibernateTemplate().findByNamedQuery("MyTasksToAssign",
        new Object[] {userID, FlowTask.TASK_STATE_NEED_TO_ASSIGN});
  }

  public Integer findMyTasksToAssignNum(String userID) {
    try {
      Query q = getSession().getNamedQuery("MyTasksToAssignNum");
      q.setString(0, userID);
      q.setString(1, FlowTask.TASK_STATE_NEED_TO_ASSIGN);

      return (Integer) q.iterate().next();
    }
    catch (HibernateException ex) {
      log.info(ex);
    }
    return null;
  }

  public List findMyRefusedTasks(String userID) {
    return getHibernateTemplate().findByNamedQuery("MyRefusedTasks",
        new Object[] {FlowTask.TASK_STATE_REFUSED, userID});
  }

  public Integer findMyRefusedTasksNum(String userID) {
    return (Integer) getHibernateTemplate().iterate(
        "select count(*) from FlowTaskUser tu where tu.flowTask.taskState='"
        + FlowTask.TASK_STATE_REFUSED + "' and tu.userID = '" + userID + "'").
        next();
  }

  public List findNewTasksNotEmailed() {
    return getHibernateTemplate().findByNamedQuery("NewTasksNotEmailed",
        new Object[] {FlowTask.TASK_STATE_FREE, FlowTask.NEW_TASK_NOT_EMAILED});
  }

  public List findMyFinishedTasksKinds(String userID) {
    return getHibernateTemplate().findByNamedQuery("MyFinishedTasksKinds",
        new Object[] {userID, FlowTask.TASK_STATE_FINISHED});
  }

  public Integer findMyExecutingTasksNum(String userID) {
    return (Integer) getHibernateTemplate().iterate(
        "select count(*) from FlowTaskUser tu join tu.flowTask task " +
        " where tu.userID = '" + userID + "' and task.taskState in ('" +
        FlowTask.TASK_STATE_LOCKED + "','" + FlowTask.TASK_STATE_ASSIGNED +
        "')").next();
  }

  public Integer findMyOtherExecutingTasksNum(String userID) {
    return (Integer) getHibernateTemplate().iterate(
        "select count(*) from FlowTaskUser tu join tu.flowTask task " +
        " where tu.userID = '" + userID + "' and " +
        " task.flowNodeBinding.flowDeploy.workflowMeta.businessType is null " +
        " and task.taskState in ('" +
        FlowTask.TASK_STATE_LOCKED + "','" + FlowTask.TASK_STATE_ASSIGNED +
        "')").next();
  }

  public Integer findMyExecutingTasksNumByType(String userID, Long typeID) {
    return (Integer) getHibernateTemplate().findByNamedQuery(
        "MyExecutingTasksNumByType",
        new Object[] {userID, FlowTask.TASK_STATE_LOCKED,
        FlowTask.TASK_STATE_ASSIGNED, typeID}).get(0);
  }

  public List findMyExecutingTasksByType(String userID, Long typeID,
                                         int pageNum, int pageSize) {
    try {
      Query q = getSession().getNamedQuery("MyExecutingTasksByType");
      q.setString(0, userID);
      q.setLong(1, typeID.longValue());
      q.setString(2, FlowTask.TASK_STATE_LOCKED);
      q.setString(3, FlowTask.TASK_STATE_ASSIGNED);

      q.setMaxResults(pageSize);
      q.setFirstResult( (pageNum - 1) * pageSize);

      return q.list();
    }
    catch (HibernateException ex) {
      log.info(ex);
    }
    return null;
  }

  public Integer findMyFinishedTasksNum(String userID) {
    return (Integer) getHibernateTemplate().iterate(
        "select count(*) from FlowTaskUser tu join tu.flowTask task " +
        " where tu.userID = '" + userID + "' and task.taskState='" +
        FlowTask.TASK_STATE_FINISHED + "'").next();
  }

  public Integer findMyFinishedTasksNumByType(String userID, Long typeID) {
    return (Integer) getHibernateTemplate().findByNamedQuery(
        "MyFinishedTasksNumByType",
        new Object[] {userID, FlowTask.TASK_STATE_FINISHED, typeID}).get(0);
  }

  public List findMyFinishedTasksByType(String userID, Long typeID, int pageNum,
                                        int pageSize) {
    try {
      Query q = getSession().getNamedQuery("MyFinishedTasksByType");
      q.setString(0, userID);
      q.setLong(1, typeID.longValue());
      q.setString(2, FlowTask.TASK_STATE_FINISHED);

      q.setMaxResults(pageSize);
      q.setFirstResult( (pageNum - 1) * pageSize);

      return q.list();
    }
    catch (HibernateException ex) {
      log.info(ex);
    }
    return null;
  }

  public Integer findMyOtherFinishedTasksNum(String userID) {
    return (Integer) getHibernateTemplate().iterate(
        "select count(*) from FlowTaskUser tu join tu.flowTask task " +
        " where tu.userID = '" + userID + "' and " +
        " task.flowNodeBinding.flowDeploy.workflowMeta.businessType is null " +
        " and task.taskState='" +
        FlowTask.TASK_STATE_FINISHED + "'").next();
  }

  public Integer findMyOtherNewTasksNum(String userID) {
    return (Integer) getHibernateTemplate().iterate(
        "select count(*) from NewTask nt join nt.flowTask task " +
        " where nt.taskCandidateUserID = '" + userID + "' and " +
        " task.flowNodeBinding.flowDeploy.workflowMeta.businessType is null " +
        " and task.taskState='" +
        FlowTask.TASK_STATE_FREE + "'").next();
  }

  /**
   * findTasksByProc
   *
   * @param flowNodeID String
   * @return FlowTask
   */
  public List findTasksByProc(Long flowProcID) {
    return getHibernateTemplate().findByNamedQuery("TasksByProc",
        new Object[] {flowProcID});
  }

}
