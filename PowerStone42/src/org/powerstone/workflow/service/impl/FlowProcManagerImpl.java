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

import org.powerstone.workflow.service.FlowProcManager;
import org.powerstone.workflow.model.FlowProcTransaction;
import org.powerstone.workflow.model.FlowProc;
import org.powerstone.workflow.model.FlowProcRelativeData;
import org.powerstone.workflow.model.FlowProcTransition;
import org.powerstone.workflow.model.FlowDeploy;
import java.util.HashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.powerstone.workflow.dao.FlowProcDAO;
import org.powerstone.workflow.service.FlowDeployManager;
import java.util.Iterator;
import org.powerstone.workflow.model.WorkflowDriver;
import org.powerstone.workflow.util.TimeUtil;
import java.util.List;
import org.powerstone.workflow.model.WorkflowMeta;
import org.powerstone.workflow.util.FlowDataField;
import org.powerstone.workflow.service.FlowMetaManager;
import org.powerstone.workflow.model.FlowTask;

public class FlowProcManagerImpl
    implements FlowProcManager {
  private static Log log = LogFactory.getLog(FlowProcManagerImpl.class);
  private FlowProcDAO flowProcDAO;
  private FlowDeployManager deployManager;
  private FlowMetaManager flowMetaManager;

  public FlowProc getFlowProc(String flowProcID) {
    return flowProcDAO.getFlowProc(new Long(flowProcID));
  }

  public FlowProc saveFlowProc(FlowProc flowProc) {
    flowProcDAO.saveFlowProc(flowProc);
    return flowProc;
  }

  public void removeFlowProc(String flowProcID) {
    FlowProc flowProc = getFlowProc(flowProcID);
    //变成孤儿（以便于删除）
    //flowProc.toOrphan();
    //
    flowProc.clear();
    this.saveFlowProc(flowProc);

    flowProcDAO.removeFlowProc(new Long(flowProcID));
  }

  public FlowProcRelativeData getFlowProcRelativeData(String procRelativeDataID) {
    return flowProcDAO.getFlowProcRelativeData(new Long(procRelativeDataID));
  }

  public FlowProcTransaction getFlowProcTransaction(String procTransactionID) {
    return flowProcDAO.getFlowProcTransaction(new Long(procTransactionID));
  }

  public FlowProcTransaction saveFlowProcTransaction(FlowProcTransaction
      procTransaction) {
    flowProcDAO.saveFlowProcTransaction(procTransaction);
    return procTransaction;
  }

  public FlowProcTransition saveFlowProcTransition(FlowProcTransition
      procTransition) {
    flowProcDAO.saveFlowProcTransition(procTransition);
    return procTransition;
  }

  public FlowProcTransition getFlowProcTransition(String procTransitionID) {
    return flowProcDAO.getFlowProcTransition(new Long(procTransitionID));
  }

  public FlowProc createFlowProc(String flowDeployID, String linkFlowProcID,
                                 String starterUserID) {
    FlowDeploy flowDeploy = deployManager.getFlowDeploy(flowDeployID);
    FlowProc linkFlowProc = this.getFlowProc(linkFlowProcID);
    return this.startAFlow(flowDeploy, linkFlowProc, starterUserID);
  }

  public FlowProc startAFlow(FlowDeploy flowDeploy, FlowProc linkFlowProc,
                             String startUser) {
    FlowProc flowProc = new FlowProc();
    flowProc.setStarterUserID(startUser);
    flowProc.setStartTime(TimeUtil.getTimeStamp().toString());
    if (linkFlowProc != null) {
      linkFlowProc.addLinkedFlowProc(flowProc);
    }
    else {
      flowProc.setLinkFlowProc(null);
    }

    flowDeploy.addFlowProc(flowProc);

    deployManager.saveFlowDeploy(flowDeploy);
    return flowProc;
  }

  public void completeTransaction(FlowProcTransaction procTransaction) {
    procTransaction.completeTransaction();
    flowProcDAO.saveFlowProcTransaction(procTransaction);
  }

  public FlowProcTransaction createProcTransaction(FlowProc flowProc) {
    FlowProcTransaction procTransaction = new FlowProcTransaction();
    flowProc.addProcTransaction(procTransaction);
    this.saveFlowProcTransaction(procTransaction);

    return procTransaction;
  }

  /**
   * mergeProcTransaction
   * 把oldTransaction合并到newTransaction，oldTransaction会被删除
   * @param newTransaction FlowProcTransaction
   * @param oldTransaction FlowProcTransaction
   * @return FlowProcTransaction
   */
  public FlowProcTransaction mergeProcTransaction(FlowProcTransaction
                                                  newTransaction,
                                                  FlowProcTransaction
                                                  oldTransaction) {
    FlowProc fp = newTransaction.getFlowProc();
//    newTransaction = flowProcDAO.getFlowProcTransaction(newTransaction.
//        getTransactionID());
//    oldTransaction = flowProcDAO.getFlowProcTransaction(oldTransaction.
//        getTransactionID());
    List oldFlowProcTransitions = oldTransaction.getFlowProcTransitions();
    List oldFlowTasks = oldTransaction.getFlowTasks();

    if (log.isDebugEnabled()) {
      log.debug("" + oldFlowProcTransitions.size());
    }

    for (Iterator it = oldFlowProcTransitions.iterator(); it.hasNext(); ) {
      FlowProcTransition ft=(FlowProcTransition) it.next();
//      FlowProcTransition ftClone=new FlowProcTransition();
//      ftClone.setFromNodeID(ft.getFromNodeID());
//      ftClone.setToNodeID(ft.getToNodeID());
//      ftClone.setWorkflowTransitionID(ft.getWorkflowTransitionID());
      newTransaction.addFlowProcTransition( ft);
    }
    for (Iterator it = oldFlowTasks.iterator(); it.hasNext(); ) {
      newTransaction.addFlowTask( (FlowTask) it.next());
    }

    this.saveFlowProcTransaction(newTransaction);

    //如果不设为空,会导致重复保存错误:deleted object would be re-saved by cascade
    oldTransaction.setFlowProcTransitions(null);
    oldTransaction.setFlowTasks(null);
    //saveFlowProcTransaction(oldTransaction);
    fp.removeProcTransaction(oldTransaction);
    this.saveFlowProc(fp);

    return newTransaction;
  }

  public FlowProcTransition createFlowProcTransition(String fromNodeID,
      String toNodeID,
      String workflowTransitionID,
      FlowProcTransaction procTransaction) {
    FlowProcTransition procTransition = new FlowProcTransition();
    procTransition.setFromNodeID(fromNodeID);
    procTransition.setToNodeID(toNodeID);
    procTransition.setWorkflowTransitionID(workflowTransitionID);

    procTransaction.addFlowProcTransition(procTransition);
    flowProcDAO.saveFlowProcTransaction(procTransaction);

    return procTransition;
  }

  public void updateProcState(FlowProc flowProc, WorkflowDriver flowDriver,
                              HashMap hashMap) {
    if (hashMap == null || hashMap.size() == 0) {
      log.warn("驱动[" + flowDriver.getFlowDriverName() + "]没有输出任何参数！");
      return;
    }
    for (Iterator it = hashMap.keySet().iterator();
         it.hasNext(); ) {
      String driverParamName = (String) it.next();
      String driverParamValue = hashMap.get(driverParamName).toString();
      FlowProcRelativeData procRelativeData =
          flowProc.findProcRelativeDataByDriverParamName(driverParamName);
      if (procRelativeData != null) {
        procRelativeData.setDriverParamValue(driverParamValue);
      }
      else {
        procRelativeData = new FlowProcRelativeData();
        procRelativeData.setDriverParamValue(driverParamValue);
        procRelativeData.setFlowNodeOutputParamBinding(
            flowProc.getFlowDeploy().findNodeOutputParamBindingByDriver(
            flowDriver, driverParamName));
        flowProc.addFlowProcRelativeData(procRelativeData);
      }
    }
    this.saveFlowProc(flowProc);
  }

  public void setFlowProcDAO(FlowProcDAO flowProcDAO) {
    this.flowProcDAO = flowProcDAO;
  }

  public void setDeployManager(FlowDeployManager deployManager) {
    this.deployManager = deployManager;
  }

  /**
   * getActiveFlowProcsByDeploy
   *
   * @param flowDeployID String
   * @return List
   */
  public List getActiveFlowProcsByDeploy(String flowDeployID) {
    List result = flowProcDAO.getActiveFlowProcsByDeploy(new Long(flowDeployID));
    this.procPreviewProcess(result);

    return result;
  }

  /**
   * 增加任务预览内容
   * @param result List
   */
  public void procPreviewProcess(List result) {
    for (Iterator it = result.iterator(); it.hasNext(); ) {
      FlowProc fp = (FlowProc) it.next();
      WorkflowMeta flowMetaWithFile =
          flowMetaManager.getWorkflowMetaWithFile(fp.getFlowDeploy().
                                                  getWorkflowMeta().
                                                  getFlowMetaID().
                                                  toString());

      HashMap dataFields = flowMetaWithFile.getDataFields();
      HashMap procState = fp.getProcState();
      if (log.isDebugEnabled()) {
        log.debug("流程状态[" + procState + "]");
        log.debug("流程数据[" + fp.getFlowProcRelativeDatas() + "]");
        log.debug("流程变量[" + dataFields + "]");
      }

      String taskPreviewStr = "";
      if (procState != null &&
          procState.size() > 0 &&
          dataFields != null &&
          dataFields.size() > 0) {
        taskPreviewStr = "(";
        int i = 0;
        for (Iterator it2 = procState.keySet().iterator(); it2.hasNext(); ) {
          String varToPreview = (String) it2.next();
          i++;
          FlowDataField fdField = (FlowDataField) dataFields.get(varToPreview);
          if (fdField == null) {
            taskPreviewStr += ("预览变量" + varToPreview + "不存在!");
            continue;
          }
          else {
            taskPreviewStr += (fdField.getFieldName() + ":"
                               + "<font color='red'>" +
                               (procState.get(varToPreview) != null
                                ? procState.get(varToPreview)
                                : "&nbsp"
                                ) + "</font>"
                               );
            if ( (i + 1) < procState.size()) {
              taskPreviewStr += "|";
            }
          }
        }
        taskPreviewStr += ")";
      }
      if (log.isDebugEnabled()) {
        log.debug("流程预览[" + taskPreviewStr + "]");
      }
      fp.setPreviewText(taskPreviewStr);
    }
  }

  public void setFlowMetaManager(FlowMetaManager flowMetaManager) {
    this.flowMetaManager = flowMetaManager;
  }

}
