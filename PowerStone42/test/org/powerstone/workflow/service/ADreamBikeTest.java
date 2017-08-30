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

import org.powerstone.workflow.model.*;
import java.io.FileInputStream;
import org.apache.commons.logging.LogFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;

import junit.framework.Assert;

import org.powerstone.AbstractSpringTestCase;
import org.powerstone.ca.model.WebModule;
import org.powerstone.ca.service.ResourceManager;
import org.powerstone.ca.service.UserManager;
import org.powerstone.ca.model.User;
import org.powerstone.ca.model.Resource;

public class ADreamBikeTest
    extends AbstractSpringTestCase {
  protected WorkflowEngine workflowEngine = null;
  protected FlowMetaManager flowMetaManager = null;
  protected BusinessTypeManager businessTypeManager = null;
  protected FlowDeployManager flowDeployManager = null;
  protected WorkflowDriverManager workflowDriverManager = null;
  protected FlowTaskManager taskManager = null;
  protected FlowProcManager procManager = null;

  protected ResourceManager resourceManager = null;
  protected UserManager userManager = null;

  WorkflowDriver wd1 = null;
  WorkflowDriver wd2 = null;
  WorkflowDriver wd3 = null;
  WorkflowDriver wd4 = null;
  WorkflowDriver wd5 = null;
  WorkflowDriver wd6 = null;
  WorkflowDriver wd7 = null;
  WorkflowDriver wd8 = null;
  WorkflowDriver wd9 = null;

  WorkflowMeta wm;
  BusinessType bm;

  WFDriverOutputParam wdOutParam1;
  WFDriverOutputParam wdOutParam2;
  WFDriverOutputParam wdOutParam3;
  WFDriverOutputParam wdOutParam4;
  WFDriverOutputParam wdOutParam5;
  WFDriverOutputParam wdOutParam6;

  FlowDeploy fd;
  User user;

  protected void onSetUpInTransaction() {
    log = LogFactory.getLog(ADreamBikeTest.class);

    log.info("`````````````````````````");
    WebModule webModule = new WebModule();
    webModule.setWebModuleName("系统管理");
    resourceManager.createWebModule(webModule);

    user = new User();
    user.setUserName("admin@powerstone.org");
    user.setPassword("111");
    user.setEmail("admin@powerstone.org");
    user.setMemo("");
    user.setRealName(user.getId().toString());
    user.setSex("m");

    userManager.registerUser(user);

    Resource res1 = new Resource(); //角色管理
    res1.setActionURL("/rolemanage/");
    res1.setResourceID("function_rolemanage");
    res1.setResourceName("角色管理");
    resourceManager.createResource(res1);
    resourceManager.addResourceToWebModule(res1.getId().toString(),
                                           webModule.getWebModuleID().toString());

    Resource res2 = new Resource(); //用户管理
    res2.setActionURL("/function_usermanage/");
    res2.setResourceID("function_usermanage");
    res2.setResourceName("用户管理");
    resourceManager.createResource(res2);
    resourceManager.addResourceToWebModule(res2.getId().toString(),
                                           webModule.getWebModuleID().toString());

    Resource res3 = new Resource(); //授权管理
    res3.setActionURL("/function_privmanage/");
    res3.setResourceID("function_privmanage");
    res3.setResourceName("授权管理");
    resourceManager.createResource(res3);
    resourceManager.addResourceToWebModule(res3.getId().toString(),
                                           webModule.getWebModuleID().toString());

    Resource res4 = new Resource(); //资源管理
    res4.setActionURL("/function_resourcemanage/");
    res4.setResourceID("function_resourcemanage");
    res4.setResourceName("资源管理");
    resourceManager.createResource(res4);
    resourceManager.addResourceToWebModule(res4.getId().toString(),
                                           webModule.getWebModuleID().toString());

    Resource res6 = new Resource(); //流程管理
    res6.setActionURL("/function_flowmanage/");
    res6.setResourceID("function_flowmanage");
    res6.setResourceName("流程管理");
    resourceManager.createResource(res6);
    resourceManager.addResourceToWebModule(res6.getId().toString(),
                                           webModule.getWebModuleID().toString());

    userManager.giveCommonFunctionRight(user.getId().toString(),
                                        res1.getId().toString());
    userManager.giveCommonFunctionRight(user.getId().toString(),
                                        res2.getId().toString());
    userManager.giveCommonFunctionRight(user.getId().toString(),
                                        res3.getId().toString());
    userManager.giveCommonFunctionRight(user.getId().toString(),
                                        res4.getId().toString());
    userManager.giveCommonFunctionRight(user.getId().toString(),
                                        res6.getId().toString());

    log.info("````````````````````");

    WFDriverOutputParamEnume techStateY = new WFDriverOutputParamEnume();
    techStateY.setDriverOutputParamEnumeValue("Y");
    WFDriverOutputParamEnume techStateN = new WFDriverOutputParamEnume();
    techStateN.setDriverOutputParamEnumeValue("N");
    WFDriverOutputParamEnume stockStateY = new WFDriverOutputParamEnume();
    stockStateY.setDriverOutputParamEnumeValue("Y");
    WFDriverOutputParamEnume stockStateN = new WFDriverOutputParamEnume();
    stockStateN.setDriverOutputParamEnumeValue("N");

    log = LogFactory.getLog(ADreamBikeTest.class);
    log.info("------------------------------------");
    wdOutParam1 = new WFDriverOutputParam();
    wdOutParam1.setParamAlias("customeID");
    wdOutParam1.setParamName("customeID");

    wdOutParam2 = new WFDriverOutputParam();
    wdOutParam2.setParamAlias("email");
    wdOutParam2.setParamName("email");

    wdOutParam3 = new WFDriverOutputParam();
    wdOutParam3.setParamAlias("productID");
    wdOutParam3.setParamName("productID");

    wdOutParam4 = new WFDriverOutputParam();
    wdOutParam4.setParamAlias("price");
    wdOutParam4.setParamName("price");

    wdOutParam5 = new WFDriverOutputParam();
    wdOutParam5.setParamAlias("stockState");
    wdOutParam5.setParamName("stockState");
    wdOutParam5.addParamEnume(stockStateY);
    wdOutParam5.addParamEnume(stockStateN);

    wdOutParam6 = new WFDriverOutputParam();
    wdOutParam6.setParamAlias("techSstate");
    wdOutParam6.setParamName("techSstate");
    wdOutParam6.addParamEnume(techStateY);
    wdOutParam6.addParamEnume(techStateN);

    log.info(">>>>>>>>>>>>>>>" + wdOutParam1.getDriverOutputParamID());

    bm = new BusinessType();
    bm.setTypeName("TypeName110");
    businessTypeManager.createBusinessType(bm);

    log.info("上传流程");
    try {
      String fileName = "dreambike.xpdl";
      wm = flowMetaManager.uploadFlowMetaFile(
          new FileInputStream(new File(fileName)),
          new FileInputStream(new File(fileName)),
          new FileInputStream(new File(fileName)),
          new Long(new File(fileName).length()),
          new Long(new File(fileName).length()));
    }
    catch (FileNotFoundException ex) {
      log.error(ex);
    }
    log.info("FileVersions" + new Integer(wm.getFlowFileVersions().size()));
    log.info("改变businessType");
    bm.addWorkflowMeta(wm);
    businessTypeManager.createBusinessType(bm);

    log.info("新建部署");
    fd = new FlowDeploy();
    fd.setCreateTime( (new GregorianCalendar()).getTime().toString());
    fd.setCurrentState(FlowDeploy.DEPLOY_STATE_PREPARING);
    fd.setFlowDeployName("deployName");
    fd.setMemo("memo");
    flowMetaManager.addFlowDeploy(wm.getFlowMetaID().toString(), fd);

    log.info("启用流程");
    flowDeployManager.enableFlowDeploy(fd.getFlowDeployID().toString());

    log.info("注册驱动");
    wd1 = new WorkflowDriver();
    wd1.setContextPath("/DreamBike");
    wd1.setFlowDriverName("接收订单");
    wd1.setMemo("memo");
    wd1.setWriteURL("/RecieveOrder.do");
    wd1.addOutputParam(wdOutParam1);
    wd1.addOutputParam(wdOutParam2);
    wd1.addOutputParam(wdOutParam3);

    wd2 = new WorkflowDriver();
    wd2.setContextPath("/DreamBike");
    wd2.setFlowDriverName("计算价格");
    wd2.setMemo("memo");
    wd2.setReadURL("/CalculatePriceReadAction.do");
    wd2.setWriteURL("/CalculatePriceWriteAction.do");
    wd2.addOutputParam(wdOutParam4);

    wd3 = new WorkflowDriver();
    wd3.setContextPath("/DreamBike");
    wd3.setFlowDriverName("检查库存");
    wd3.setMemo("memo3");
    wd3.setReadURL("/CheckStoreReadAction.do");
    wd3.setWriteURL("/CheckStoreWriteAction.do");
    wd3.addOutputParam(wdOutParam5);

    wd4 = new WorkflowDriver();
    wd4.setContextPath("/DreamBike");
    wd4.setFlowDriverName("技术可行性验证");
    wd4.setMemo("memo");
    wd4.setReadURL("/CheckTecReadAction.do");
    wd4.setWriteURL("/CheckTecWriteAction.do");
    wd4.addOutputParam(wdOutParam6);

    wd5 = new WorkflowDriver();
    wd5.setContextPath("/DreamBike");
    wd5.setFlowDriverName("准备拒绝函(库存不足)");
    wd5.setMemo("memo");
    wd5.setReadURL("/RefuseForStoreReadAction.do");
    wd5.setWriteURL("/RefuseForStoreWriteAction.do");

    wd6 = new WorkflowDriver();
    wd6.setContextPath("/DreamBike");
    wd6.setFlowDriverName("准备拒绝函(技术不可行)");
    wd6.setMemo("memo");
    wd6.setReadURL("/RefuseForTecReadAction.do");
    wd6.setWriteURL("/RefuseForTecWriteAction.do");

    wd7 = new WorkflowDriver();
    wd7.setContextPath("/DreamBike");
    wd7.setFlowDriverName("准备接收函");
    wd7.setMemo("memo7");
    wd7.setReadURL("/PrepareLetterReadAction.do");
    wd7.setWriteURL("/PrepareLetterWriteAction.do");

    wd8 = new WorkflowDriver();
    wd8.setContextPath("/DreamBike");
    wd8.setFlowDriverName("生产并送货");
    wd8.setMemo("memo");
    wd8.setReadURL("/ProduceAndShipReadAction.do");
    wd8.setWriteURL("/ProduceAndShipWriteAction.do");

    wd9 = new WorkflowDriver();
    wd9.setContextPath("/DreamBike");
    wd9.setFlowDriverName("给客户回函");
    wd9.setMemo("memo9");
    wd9.setReadURL("/SendEmailReadAction.do");
    wd9.setWriteURL("/SendEmailWriteAction.do");

    workflowDriverManager.saveWorkflowDriver(wd1);
    workflowDriverManager.saveWorkflowDriver(wd2);
    workflowDriverManager.saveWorkflowDriver(wd3);
    workflowDriverManager.saveWorkflowDriver(wd4);
    workflowDriverManager.saveWorkflowDriver(wd5);
    workflowDriverManager.saveWorkflowDriver(wd6);
    workflowDriverManager.saveWorkflowDriver(wd7);
    workflowDriverManager.saveWorkflowDriver(wd8);
    workflowDriverManager.saveWorkflowDriver(wd9);

    log.info("部署流程" + wd2.getFlowDriverID());
    FlowNodeBinding nodeBinding1 =
        flowDeployManager.updateFlowNodeBinding(fd.getFlowDeployID().toString(),
                                                "dreambike_Wor1_Act1",
                                                wd1.getFlowDriverID().toString());
    FlowNodeBinding nodeBinding2 =
        flowDeployManager.updateFlowNodeBinding(fd.getFlowDeployID().toString(),
                                                "dreambike_Wor1_Act2",
                                                wd2.getFlowDriverID().toString());
    FlowNodeBinding nodeBinding3 =
        flowDeployManager.updateFlowNodeBinding(fd.getFlowDeployID().toString(),
                                                "dreambike_Wor1_Act3",
                                                wd3.getFlowDriverID().toString());
    FlowNodeBinding nodeBinding4 =
        flowDeployManager.updateFlowNodeBinding(fd.getFlowDeployID().toString(),
                                                "dreambike_Wor1_Act4",
                                                wd4.getFlowDriverID().toString());
    FlowNodeBinding nodeBinding5 =
        flowDeployManager.updateFlowNodeBinding(fd.getFlowDeployID().toString(),
                                                "dreambike_Wor1_Act5",
                                                wd5.getFlowDriverID().toString());
    FlowNodeBinding nodeBinding6 =
        flowDeployManager.updateFlowNodeBinding(fd.getFlowDeployID().toString(),
                                                "dreambike_Wor1_Act6",
                                                wd6.getFlowDriverID().toString());
    FlowNodeBinding nodeBinding7 =
        flowDeployManager.updateFlowNodeBinding(fd.getFlowDeployID().toString(),
                                                "dreambike_Wor1_Act7",
                                                wd7.getFlowDriverID().toString());
    FlowNodeBinding nodeBinding8 =
        flowDeployManager.updateFlowNodeBinding(fd.getFlowDeployID().toString(),
                                                "dreambike_Wor1_Act8",
                                                wd8.getFlowDriverID().toString());
    FlowNodeBinding nodeBinding9 =
        flowDeployManager.updateFlowNodeBinding(fd.getFlowDeployID().toString(),
                                                "dreambike_Wor1_Act9",
                                                wd9.getFlowDriverID().toString());
    log.info("绑定流程参数");
    HashMap inputParamMap = new HashMap();
    HashMap outputParamMap = new HashMap();
    outputParamMap.put("custome_ID",
                       wdOutParam1.getDriverOutputParamID().toString());
    outputParamMap.put("productID",
                       wdOutParam2.getDriverOutputParamID().toString());
    outputParamMap.put("custome_email",
                       wdOutParam3.getDriverOutputParamID().toString());
    HashMap outputParamEnumeMap = new HashMap();
    flowDeployManager.updateFlowNodeParamBinding(
        fd.getFlowNodeBindingByNodeID("dreambike_Wor1_Act1").getNodeBindingID().
        toString(),
        inputParamMap, outputParamMap, outputParamEnumeMap);

    inputParamMap = new HashMap();
    outputParamMap = new HashMap();
    outputParamMap.put("price",
                       wdOutParam4.getDriverOutputParamID().toString());
    outputParamEnumeMap = new HashMap();
    flowDeployManager.updateFlowNodeParamBinding(
        nodeBinding2.getNodeBindingID().toString(),
        inputParamMap, outputParamMap, outputParamEnumeMap);

    inputParamMap = new HashMap();
    outputParamMap = new HashMap();
    outputParamMap.put("stock_state",
                       wdOutParam5.getDriverOutputParamID().toString());
    outputParamEnumeMap = new HashMap();
    outputParamEnumeMap.put("Y",
                            stockStateY.getDriverOutputParamEnumeID().toString());
    outputParamEnumeMap.put("N",
                            stockStateN.getDriverOutputParamEnumeID().toString());
    flowDeployManager.updateFlowNodeParamBinding(
        nodeBinding3.getNodeBindingID().toString(),
        inputParamMap, outputParamMap, outputParamEnumeMap);

    inputParamMap = new HashMap();
    outputParamMap = new HashMap();
    outputParamMap.put("tech_state",
                       wdOutParam6.getDriverOutputParamID().toString());
    outputParamEnumeMap = new HashMap();
    outputParamEnumeMap.put("Y",
                            techStateY.getDriverOutputParamEnumeID().toString());
    outputParamEnumeMap.put("N",
                            techStateN.getDriverOutputParamEnumeID().toString());
    flowDeployManager.updateFlowNodeParamBinding(
        nodeBinding4.getNodeBindingID().toString(),
        inputParamMap, outputParamMap, outputParamEnumeMap);

    log.info("指定参与者");
    flowDeployManager.addUserPerformer(user.getId().toString(), nodeBinding1.getNodeBindingID().toString());
    flowDeployManager.addUserPerformer(user.getId().toString(),nodeBinding2.getNodeBindingID().toString());
    flowDeployManager.addUserPerformer(user.getId().toString(),nodeBinding3.getNodeBindingID().toString());
    flowDeployManager.addUserPerformer(user.getId().toString(),nodeBinding4.getNodeBindingID().toString());
    flowDeployManager.addUserPerformer(user.getId().toString(),nodeBinding5.getNodeBindingID().toString());
    flowDeployManager.addUserPerformer(user.getId().toString(),nodeBinding6.getNodeBindingID().toString());
    flowDeployManager.addUserPerformer(user.getId().toString(),nodeBinding7.getNodeBindingID().toString());
    flowDeployManager.addUserPerformer(user.getId().toString(),nodeBinding8.getNodeBindingID().toString());
    flowDeployManager.addUserPerformer(user.getId().toString(),nodeBinding9.getNodeBindingID().toString());
//    flowDeployManager.enableFounder(user.getId().toString(),nodeBinding2.getNodeBindingID().toString());
//    flowDeployManager.enableFounder(nodeBinding6.getNodeBindingID().toString());
//    flowDeployManager.enableFounder(nodeBinding8.getNodeBindingID().toString());
//    flowDeployManager.enableFounder(nodeBinding9.getNodeBindingID().toString());
//    flowDeployManager.enableFounder(nodeBinding10.getNodeBindingID().toString());
//    flowDeployManager.enableFounder(nodeBinding11.getNodeBindingID().toString());

    log.info("触发流程");
    ActivityReport report = new ActivityReport();
    report.setDriverID(wd1.getFlowDriverID().toString());
    HashMap output = new HashMap();
    output.put("customeID", "value1");
    output.put("productID", "value2");
    output.put("email", "value3");

    report.setDriverOutputData(output);
    for (int i = 0; i < 1; i++) {
      workflowEngine.processActivityReport(user.getId().toString(), report);
      log.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
    }
  }

  /**
   * 测试通常的任务领取
   */
  public void testProcessActivityReport() {
//    prepareData();
    while (true) {
      List allMyNewTasks = taskManager.findAllMyNewTasks(user.getId().toString());
      log.info("@@@@@@@@@@新任务：" + allMyNewTasks.size());
      Iterator it = allMyNewTasks.iterator();
      if (it.hasNext()) {
        while (it.hasNext()) {
          FlowTask aTask = (FlowTask) it.next();
          taskManager.checkOutTask(user.getId().toString(), aTask.getTaskID().toString());
          workflowEngine.processSubmitTask(user.getId().toString(), aTask.getTaskID().toString());
        }
      }
      else {
        log.info("---------------------新任务全部领取完成");
        break;
      }
    }

    while (true) {
      List allMyExecutingTasks =
          taskManager.findMyExecutingTasksByType(user.getId().toString(), bm.getTypeID().toString(),
                                                 1, 1000);
      Iterator it = allMyExecutingTasks.iterator();
      if (it.hasNext()) {
        while (it.hasNext()) {
          FlowTask aTask = (FlowTask) it.next();
          workflowEngine.processSubmitTask(user.getId().toString(), aTask.getTaskID().toString());
        }
      }
      else {
        log.info("---------------------Executing任务全部完成");
        break;
      }
    }
    log.info("Finished任务：" + taskManager.findMyFinishedTasksKinds(user.getId().toString()).size());
  }

  /**
   * 测试流程监控
   */
  public void testFlowMonitor() {
//    prepareData();
    List activeFlowProcs =
        procManager.getActiveFlowProcsByDeploy(fd.getFlowDeployID().toString());
    Assert.assertTrue("活动的进程个数：", activeFlowProcs.size() == 1);
    log.info("-------------" + activeFlowProcs.get(0));
  }

  /**
   * 测试驳回任务
   */
  public void testRefuseTask() {
//    prepareData();
    List allMyNewTasks =
        taskManager.findMyNewTasksByType(user.getId().toString(), bm.getTypeID().toString(),
                                               1, 1000);
    log.info("持有任务[" + allMyNewTasks.size() + "]个");
    for (Iterator it = allMyNewTasks.iterator(); it.hasNext(); ) {
      FlowTask ft = (FlowTask) it.next();
      taskManager.checkOutTask(user.getId().toString(),ft.getTaskID().toString());
      workflowEngine.processSubmitTask(user.getId().toString(), ft.getTaskID().toString());
    }
    allMyNewTasks =
        taskManager.findMyNewTasksByType(user.getId().toString(), bm.getTypeID().toString(),
                                               1, 1000);
    for (Iterator it = allMyNewTasks.iterator(); it.hasNext(); ) {
      FlowTask ft = (FlowTask) it.next();
      taskManager.checkOutTask(user.getId().toString(),ft.getTaskID().toString());
      break;
    }

    List allMyExecutingTasks =
        taskManager.findMyExecutingTasksByType(user.getId().toString(), bm.getTypeID().toString(),
                                               1, 1000);
    log.info("持有任务[" + allMyExecutingTasks.size() + "]个");
    this.assertEquals("持有任务数", 1, allMyExecutingTasks.size());

    FlowTask aTask = (FlowTask) allMyExecutingTasks.get(0);
    List tasksToRefuse =
        taskManager.findTasksToRefuse(aTask.getTaskID().toString());

    log.info("$$$$$$$可以驳回[" + tasksToRefuse.size() + "]个任务");
    this.assertEquals("可以驳回任务数", 3, tasksToRefuse.size());

    String[] taskIDs = new String[tasksToRefuse.size()];
    int i = 0;
    for (Iterator itt = tasksToRefuse.iterator(); itt.hasNext(); ) {
      taskIDs[i] = ( (FlowTask) itt.next()).getTaskID().toString();
      i++;
    }
    int refuseNum = taskManager.refuseTasks(aTask.getTaskID().toString(),
                                            taskIDs,
                                            "refuse for", user.getId().toString());
    this.assertEquals("成功驳回任务数量", 3, refuseNum);
  }

  /**
   * 取回任务
   */
  public void testGetBackTask() {
//    prepareData();
    List allMyNewTasks =
        taskManager.findMyNewTasksByType(user.getId().toString(), bm.getTypeID().toString(),
                                         1, 1000);
    log.info("新任务[" + allMyNewTasks.size() + "]个");
    Iterator it = allMyNewTasks.iterator();
    while (it.hasNext()) {
      FlowTask aTask = (FlowTask) it.next();
      taskManager.checkOutTask(user.getId().toString(), aTask.getTaskID().toString());
      workflowEngine.processSubmitTask(user.getId().toString(), aTask.getTaskID().toString());
    }

    List allMyTasks =
        taskManager.findMyExecutingTasksByType(user.getId().toString(), bm.getTypeID().toString(),
                                               1, 1000);
    log.info("my任务[" + allMyTasks.size() + "]个");
    Iterator it2 = allMyTasks.iterator();
    while (it2.hasNext()) {
      FlowTask aTask = (FlowTask) it2.next();
      workflowEngine.processSubmitTask(user.getId().toString(), aTask.getTaskID().toString());
    }

    List myFinishedTasks =
        taskManager.findMyFinishedTasksByType(user.getId().toString(), bm.getTypeID().toString(),
                                              1, 1000);
    log.info("已完成任务[" + myFinishedTasks.size() + "]个");
    FlowTask aTask = (FlowTask) myFinishedTasks.get(0);
    boolean getSuccess = taskManager.getBackFlowTask(user.getId().toString(),
        aTask.getTaskID().toString());

    String taskState =
        taskManager.getFlowTask(aTask.getTaskID().toString()).getTaskState();
    log.info("TaskState[" + taskState + "]");
    List myFinishedTasks2 =
        taskManager.findMyFinishedTasksByType(user.getId().toString(), bm.getTypeID().toString(),
                                              1, 1000);

    if (getSuccess) {
      this.assertTrue("任务状态=locked or assigned",
                      FlowTask.TASK_STATE_LOCKED.equals(taskState) ||
                      FlowTask.TASK_STATE_ASSIGNED.equals(taskState));
      log.info("$$$$$$$取回任务[" + aTask.getTaskID() +
               "|state=" + taskState + "]");
      this.assertEquals("已完成任务数量减少一个", myFinishedTasks.size() - 1,
                      myFinishedTasks2.size());
    }
    else {
      this.assertTrue("任务状态=FINISHED",
                      FlowTask.TASK_STATE_FINISHED.equals(taskState));
      this.assertEquals("已完成任务数量不变", myFinishedTasks.size(),
                      myFinishedTasks2.size());
    }
  }

  public void setWorkflowEngine(WorkflowEngine workflowEngine) {
    this.workflowEngine = workflowEngine;
  }
  public void setWorkflowDriverManager(WorkflowDriverManager workflowDriverManager) {
    this.workflowDriverManager = workflowDriverManager;
  }
  public void setTaskManager(FlowTaskManager taskManager) {
    this.taskManager = taskManager;
  }
  public void setProcManager(FlowProcManager procManager) {
    this.procManager = procManager;
  }
  public void setFlowMetaManager(FlowMetaManager flowMetaManager) {
    this.flowMetaManager = flowMetaManager;
  }
  public void setFlowDeployManager(FlowDeployManager flowDeployManager) {
    this.flowDeployManager = flowDeployManager;
  }

  public void setBusinessTypeManager(BusinessTypeManager businessTypeManager) {
    this.businessTypeManager = businessTypeManager;
  }
  public void setResourceManager(ResourceManager resourceManager) {
    this.resourceManager = resourceManager;
  }
  public void setUserManager(UserManager userManager) {
    this.userManager = userManager;
  }
}
