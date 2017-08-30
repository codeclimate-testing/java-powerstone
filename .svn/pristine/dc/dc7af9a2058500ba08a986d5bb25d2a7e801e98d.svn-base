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
package org.powerstone.workflow.service;

import java.util.*;

import org.powerstone.workflow.model.*;

public interface FlowTaskManager {

  public boolean isTaskOwner(String userID, String taskID);

  public boolean isTaskAssigner(String userID, String taskID);

  public FlowTask getFlowTask(String taskID);

  public FlowTask createFlowTask(FlowProcTransaction procTransaction,
                                 FlowNodeBinding flowNodeBinding);

  public void checkOutTask(String userID, String taskID);

  public void finishTask(String taskID);

  public void emailTask(String taskID);

  public void abortTask(String userID, String taskID);

  public void assignTask(String userID, String taskID);

  public List findAllMyNewTasks(String userID);

  public List findMyExecutingTasksKinds(String userID);

  public List findMyTasksToAssign(String userID);

  public List findMyRefusedTasks(String userID);

  public List findNewTasksNotEmailed();

  public List findMyFinishedTasksKinds(String userID);

  public Integer findMyNewTasksNum(String userID);

  public List findMyNewTasksKinds(String userID);

  public Integer findMyNewTasksNumByType(String userID, String typeID);

  public List findMyNewTasksByType(String userID, String typeID,
                                   int pageNum, int pageSize);

  public Integer findMyExecutingTasksNum(String userID);

  public Integer findMyExecutingTasksNumByType(String userID, String typeID);

  public List findMyExecutingTasksByType(String userID, String typeID,
                                         int pageNum, int pageSize);

  public Integer findMyRefusedTasksNum(String userID);

  public Integer findMyFinishedTasksNum(String userID);

  public Integer findMyFinishedTasksNumByType(String userID, String typeID);

  public List findMyFinishedTasksByType(String userID, String typeID,
                                        int pageNum, int pageSize);

  public FlowTask findTaskByNodeAndProc(String flowNodeID,
                                        String flowProcID);

  public Integer findMyTasksToAssignNum(String userID);

  public void distributeTask(String userID, String taskID,
                             String userToDistribute);

  public void taskPreviewProcess(List result);

  /**
   * 更新新任务列表
   * @param userID String
   * @param taskID String
   */
  public void fenfaNewTask(String userID, String taskID);

  public List findTasksByProc(String flowProcID);

  public void needAsssign(String userID, String taskID);

  public int refuseTasks(String taskID, String[] tasksToRefuse,
                         String refuseFor, String refUserID);

  public List findTasksToRefuse(String taskID);

  public boolean getBackFlowTask( String userID,String taskID);
}
