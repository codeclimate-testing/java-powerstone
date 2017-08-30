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

import org.powerstone.workflow.model.FlowProc;
import org.powerstone.workflow.model.FlowProcRelativeData;
import org.powerstone.workflow.model.FlowProcTransaction;
import org.powerstone.workflow.model.FlowProcTransition;
import org.powerstone.workflow.model.FlowDeploy;
import java.util.HashMap;
import org.powerstone.workflow.model.WorkflowDriver;
import java.util.List;

public interface FlowProcManager {
  public FlowProc getFlowProc(String flowProcID);

  public FlowProc saveFlowProc(FlowProc flowProc);

  public void removeFlowProc(String flowProcID);

  public FlowProcRelativeData getFlowProcRelativeData(String procRelativeDataID);

  public FlowProcTransaction getFlowProcTransaction(String procTransactionID);

  public FlowProcTransaction saveFlowProcTransaction(FlowProcTransaction
      procTransaction);

  public FlowProcTransition saveFlowProcTransition(FlowProcTransition
      procTransition);

  public FlowProcTransition getFlowProcTransition(String procTransitionID);

  public FlowProc createFlowProc(String flowDeployID, String linkFlowProcID,
                                 String starterUserID);

  public FlowProc startAFlow(FlowDeploy flowDeploy, FlowProc linkFlowProc,
                             String startUser);

  public void completeTransaction(FlowProcTransaction procTransaction);

  public FlowProcTransaction createProcTransaction(FlowProc flowProc);

  public FlowProcTransaction mergeProcTransaction(FlowProcTransaction
                                                  newTransaction,
                                                  FlowProcTransaction
                                                  oldTransaction);

  public FlowProcTransition createFlowProcTransition(String fromNodeID,
      String toNodeID,
      String workflowTransitionID,
      FlowProcTransaction procTransaction);

  public void updateProcState(FlowProc flowProc, WorkflowDriver flowDriver,
                              HashMap hashMap);

  /**
   *
   * @param flowDeployID String
   * @return List
   */
  public List getActiveFlowProcsByDeploy(String flowDeployID);

}
