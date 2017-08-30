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
import java.io.File;
import java.io.FileNotFoundException;
import org.powerstone.AbstractSpringTestCase;
import org.powerstone.ca.model.User;
import org.powerstone.ca.model.WebModule;
import org.powerstone.ca.service.ResourceManager;
import org.powerstone.ca.service.UserManager;
import org.powerstone.ca.model.Resource;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * ant test -Dtestcase=InitDreamBikeDriver to register apps for DreamBike demo
 * application
 * <p>
 * Title: PowerStone
 * </p>
 */
public class InitDreamBikeDriver extends AbstractSpringTestCase {
	private BusinessTypeManager businessTypeManager = null;

	private FlowMetaManager flowMetaManager = null;

	private WorkflowDriverManager workflowDriverManager = null;

	private ResourceManager resourceManager = null;

	private UserManager userManager = null;

	private FlowDeployManager flowDeployManager = null;

	private WorkflowEngine workflowEngine = null;

	// ------------------------------------------------------------------------------
	protected void onSetUpInTransaction() throws Exception {
	}

	public void test1() {
		log.info("---------------------Init CA data-----------------------");
		WebModule wm1 = new WebModule();
		wm1.setWebModuleName("System Manage");
		resourceManager.createWebModule(wm1);
		WebModule wm2 = new WebModule();
		wm2.setWebModuleName("DreamBike");
		resourceManager.createWebModule(wm2);
		User user = new User();
		user.setUserName("admin");
		user.setPassword("admin");
		user.setEmail("admin@powerstone.org");
		user.setMemo("memo");
		user.setRealName("daquanda");
		user.setSex("male");
		userManager.registerUser(user);
		Resource res1 = new Resource();
		res1.setActionURL("/role_manage/");
		res1.setResourceID("function_rolemanage");
		res1.setResourceName("Role Manage");
		resourceManager.createResource(res1);
		resourceManager.addResourceToWebModule(res1.getId().toString(), wm1
				.getWebModuleID().toString());
		Resource res2 = new Resource();
		res2.setActionURL("/user_group/");
		res2.setResourceID("function_usermanage");
		res2.setResourceName("User Manage");
		resourceManager.createResource(res2);
		resourceManager.addResourceToWebModule(res2.getId().toString(), wm1
				.getWebModuleID().toString());
		Resource res3 = new Resource();
		res3.setActionURL("/priv_manage/");
		res3.setResourceID("function_privmanage");
		res3.setResourceName("Privilege Manage");
		resourceManager.createResource(res3);
		resourceManager.addResourceToWebModule(res3.getId().toString(), wm1
				.getWebModuleID().toString());
		Resource res4 = new Resource();
		res4.setActionURL("/resource_manage/");
		res4.setResourceID("function_resourcemanage");
		res4.setResourceName("Resource Manage");
		resourceManager.createResource(res4);
		resourceManager.addResourceToWebModule(res4.getId().toString(), wm1
				.getWebModuleID().toString());
		Resource res6 = new Resource();
		res6.setActionURL("/wf/");
		res6.setResourceID("function_flowmanage");
		res6.setResourceName("Workflow Manage");
		resourceManager.createResource(res6);
		resourceManager.addResourceToWebModule(res6.getId().toString(), wm1
				.getWebModuleID().toString());
		userManager.giveCommonFunctionRight(user.getId().toString(), res1
				.getId().toString());
		userManager.giveCommonFunctionRight(user.getId().toString(), res2
				.getId().toString());
		userManager.giveCommonFunctionRight(user.getId().toString(), res3
				.getId().toString());
		userManager.giveCommonFunctionRight(user.getId().toString(), res4
				.getId().toString());
		userManager.giveCommonFunctionRight(user.getId().toString(), res6
				.getId().toString());

		log.info("--------------------upload a flow-----------------------");
		WorkflowMeta wm = null;
		try {
			String fileName = "dreambike.xpdl";
			wm = flowMetaManager.uploadFlowMetaFile(new FileInputStream(
					new File(fileName)),
					new FileInputStream(new File(fileName)),
					new FileInputStream(new File("DreamBike.JPG")), new Long(
							new File(fileName).length()), new Long(new File(
							"DreamBike.JPG").length()));
		} catch (FileNotFoundException ex) {
			log.error(ex);
		}
		log.info("change the business type of ");
		BusinessType bm = new BusinessType();
		bm.setTypeName("DreamBike");
		businessTypeManager.createBusinessType(bm);
		bm.addWorkflowMeta(wm);
		businessTypeManager.createBusinessType(bm);

		log.info("---------------------register apps-----------------------");
		WFDriverOutputParamEnume techStateY = new WFDriverOutputParamEnume();
		techStateY.setDriverOutputParamEnumeValue("Y");
		WFDriverOutputParamEnume techStateN = new WFDriverOutputParamEnume();
		techStateN.setDriverOutputParamEnumeValue("N");
		WFDriverOutputParamEnume stockStateY = new WFDriverOutputParamEnume();
		stockStateY.setDriverOutputParamEnumeValue("Y");
		WFDriverOutputParamEnume stockStateN = new WFDriverOutputParamEnume();
		stockStateN.setDriverOutputParamEnumeValue("N");

		WFDriverOutputParam wdOutParam1 = new WFDriverOutputParam();
		wdOutParam1.setParamAlias("customeID");
		wdOutParam1.setParamName("customeID");
		WFDriverOutputParam wdOutParam2 = new WFDriverOutputParam();
		wdOutParam2.setParamAlias("customeEmail");
		wdOutParam2.setParamName("customeEmail");
		WFDriverOutputParam wdOutParam3 = new WFDriverOutputParam();
		wdOutParam3.setParamAlias("productID");
		wdOutParam3.setParamName("productID");
		WFDriverOutputParam wdOutParam4 = new WFDriverOutputParam();
		wdOutParam4.setParamAlias("price");
		wdOutParam4.setParamName("price");
		WFDriverOutputParam wdOutParam5 = new WFDriverOutputParam();
		wdOutParam5.setParamAlias("stockState");
		wdOutParam5.setParamName("stockState");
		wdOutParam5.addParamEnume(stockStateY);
		wdOutParam5.addParamEnume(stockStateN);
		WFDriverOutputParam wdOutParam6 = new WFDriverOutputParam();
		wdOutParam6.setParamAlias("techState");
		wdOutParam6.setParamName("techState");
		wdOutParam6.addParamEnume(techStateY);
		wdOutParam6.addParamEnume(techStateN);
		WFDriverOutputParam wdOutParam7 = new WFDriverOutputParam();
		wdOutParam7.setParamAlias("orderID");
		wdOutParam7.setParamName("orderID");
		WFDriverOutputParam wdOutParam8 = new WFDriverOutputParam();
		wdOutParam8.setParamAlias("priceDetail");
		wdOutParam8.setParamName("priceDetail");
		WFDriverOutputParam wdOutParam9 = new WFDriverOutputParam();
		wdOutParam9.setParamAlias("techDetail");
		wdOutParam9.setParamName("techDetail");
		WFDriverOutputParam wdOutParam10 = new WFDriverOutputParam();
		wdOutParam10.setParamAlias("stockDetail");
		wdOutParam10.setParamName("stockDetail");

		WorkflowDriver wd1 = new WorkflowDriver();
		wd1.setContextPath("/DreamBike");
		wd1.setFlowDriverName("fill order");
		wd1.setWriteURL("/dreambike/edit_order.do");
		workflowDriverManager.saveWorkflowDriver(wd1);
		wd1.addOutputParam(wdOutParam1);
		wd1.addOutputParam(wdOutParam2);
		wd1.addOutputParam(wdOutParam3);
		wd1.addOutputParam(wdOutParam7);
		workflowDriverManager.saveWorkflowDriver(wd1);

		WorkflowDriver wd2 = new WorkflowDriver();
		wd2.setContextPath("/DreamBike");
		wd2.setFlowDriverName("calculate price");
		wd2.setMemo("analyse price structure and give the total price");
		wd2.setReadURL("/dreambike/edit_price.do");
		wd2.setWriteURL("/dreambike/edit_price.do");
		workflowDriverManager.saveWorkflowDriver(wd2);
		wd2.addOutputParam(wdOutParam4);
		wd2.addOutputParam(wdOutParam8);
		WFDriverInputParam wdInParam1 = new WFDriverInputParam();
		wdInParam1.setParamAlias("_orderID");
		wdInParam1.setParamName("_orderID");
		wd2.addInputParam(wdInParam1);
		workflowDriverManager.saveWorkflowDriver(wd2);

		WorkflowDriver wd3 = new WorkflowDriver();
		wd3.setContextPath("/DreamBike");
		wd3.setFlowDriverName("check the state of stock");
		wd3
				.setMemo("analyse the structureof the bike and give the conclusion of stock state");
		wd3.setReadURL("/dreambike/edit_stock.do");
		wd3.setWriteURL("/dreambike/edit_stock.do");
		workflowDriverManager.saveWorkflowDriver(wd3);
		wd3.addOutputParam(wdOutParam5);
		wd3.addOutputParam(wdOutParam10);
		WFDriverInputParam wdInParam2 = new WFDriverInputParam();
		wdInParam2.setParamAlias("_orderID");
		wdInParam2.setParamName("_orderID");
		wd3.addInputParam(wdInParam2);
		workflowDriverManager.saveWorkflowDriver(wd3);

		WorkflowDriver wd4 = new WorkflowDriver();
		wd4.setContextPath("/DreamBike");
		wd4.setFlowDriverName("check technical feasibility");
		wd4.setMemo("check technical feasibility and give a conclusion");
		wd4.setReadURL("/dreambike/edit_tech.do");
		wd4.setWriteURL("/dreambike/edit_tech.do");
		workflowDriverManager.saveWorkflowDriver(wd4);
		wd4.addOutputParam(wdOutParam6);
		wd4.addOutputParam(wdOutParam9);
		WFDriverInputParam wdInParam3 = new WFDriverInputParam();
		wdInParam3.setParamAlias("_orderID");
		wdInParam3.setParamName("_orderID");
		wd4.addInputParam(wdInParam3);
		workflowDriverManager.saveWorkflowDriver(wd4);

		WorkflowDriver wd5 = new WorkflowDriver();
		wd5.setContextPath("/DreamBike");
		wd5.setFlowDriverName("make a purchase plan");
		wd5.setMemo("make a purchase plan based on the state of stock");
		wd5.setReadURL("/dreambike/edit_plan_purch.do");
		wd5.setWriteURL("/dreambike/edit_plan_purch.do");
		workflowDriverManager.saveWorkflowDriver(wd5);
		WFDriverInputParam wdInParam4 = new WFDriverInputParam();
		wdInParam4.setParamAlias("_orderID");
		wdInParam4.setParamName("_orderID");
		wd5.addInputParam(wdInParam4);
		WFDriverInputParam wdInParam5 = new WFDriverInputParam();
		wdInParam5.setParamAlias("_stockDetail");
		wdInParam5.setParamName("_stockDetail");
		wd5.addInputParam(wdInParam5);
		workflowDriverManager.saveWorkflowDriver(wd5);

		WorkflowDriver wd6 = new WorkflowDriver();
		wd6.setContextPath("/DreamBike");
		wd6
				.setFlowDriverName("make a produce plan(will trigger another workflow)");
		wd6.setMemo("make a produce plan based on orders");
		wd6.setReadURL("/dreambike/edit_plan_produce.do");
		wd6.setWriteURL("/dreambike/edit_plan_produce.do");
		workflowDriverManager.saveWorkflowDriver(wd6);
		WFDriverInputParam wdInParam6 = new WFDriverInputParam();
		wdInParam6.setParamAlias("_orderID");
		wdInParam6.setParamName("_orderID");
		wd6.addInputParam(wdInParam6);
		WFDriverInputParam wdInParam7 = new WFDriverInputParam();
		wdInParam7.setParamAlias("_productID");
		wdInParam7.setParamName("_productID");
		wd6.addInputParam(wdInParam7);
		workflowDriverManager.saveWorkflowDriver(wd6);

		WorkflowDriver wd7 = new WorkflowDriver();
		wd7.setContextPath("/DreamBike");
		wd7.setFlowDriverName("refuse the client by email");
		wd7.setMemo("and give a reasonable explanation");
		wd7.setReadURL("/dreambike/edit_email_refuse.do");
		wd7.setWriteURL("/dreambike/edit_email_refuse.do");
		workflowDriverManager.saveWorkflowDriver(wd7);
		WFDriverInputParam wdInParam8 = new WFDriverInputParam();
		wdInParam8.setParamAlias("_customeEmail");
		wdInParam8.setParamName("_customeEmail");
		wd7.addInputParam(wdInParam8);
		WFDriverInputParam wdInParam9 = new WFDriverInputParam();
		wdInParam9.setParamAlias("_customeID");
		wdInParam9.setParamName("_customeID");
		wd7.addInputParam(wdInParam9);
		WFDriverInputParam wdInParam16 = new WFDriverInputParam();
		wdInParam16.setParamAlias("_orderID");
		wdInParam16.setParamName("_orderID");
		wd7.addInputParam(wdInParam16);

		workflowDriverManager.saveWorkflowDriver(wd7);

		WorkflowDriver wd8 = new WorkflowDriver();
		wd8.setContextPath("/DreamBike");
		wd8.setFlowDriverName("email the client to prepare receive the bike");
		wd8.setMemo("include all details about it");
		wd8.setReadURL("/dreambike/edit_email_receive_note.do");
		wd8.setWriteURL("/dreambike/edit_email_receive_note.do");
		workflowDriverManager.saveWorkflowDriver(wd8);
		WFDriverInputParam wdInParam10 = new WFDriverInputParam();
		wdInParam10.setParamAlias("_orderID");
		wdInParam10.setParamName("_orderID");
		wd8.addInputParam(wdInParam10);
		WFDriverInputParam wdInParam11 = new WFDriverInputParam();
		wdInParam11.setParamAlias("_customeEmail");
		wdInParam11.setParamName("_customeEmail");
		wd8.addInputParam(wdInParam11);
		WFDriverInputParam wdInParam12 = new WFDriverInputParam();
		wdInParam12.setParamAlias("_customeID");
		wdInParam12.setParamName("_customeID");
		wd8.addInputParam(wdInParam12);
		WFDriverInputParam wdInParam13 = new WFDriverInputParam();
		wdInParam13.setParamAlias("_price");
		wdInParam13.setParamName("_price");
		wd8.addInputParam(wdInParam13);
		WFDriverInputParam wdInParam14 = new WFDriverInputParam();
		wdInParam14.setParamAlias("_pricedetail");
		wdInParam14.setParamName("_pricedetail");
		wd8.addInputParam(wdInParam14);
		workflowDriverManager.saveWorkflowDriver(wd8);

		WorkflowDriver wd9 = new WorkflowDriver();
		wd9.setContextPath("/DreamBike");
		wd9.setFlowDriverName("confirm the order");
		wd9.setMemo("check errors,and then,put on records");
		wd9.setReadURL("/dreambike/edit_order_confirm.do");
		wd9.setWriteURL("/dreambike/edit_order_confirm.do");
		workflowDriverManager.saveWorkflowDriver(wd9);
		WFDriverInputParam wdInParam15 = new WFDriverInputParam();
		wdInParam15.setParamAlias("_orderID");
		wdInParam15.setParamName("_orderID");
		wd9.addInputParam(wdInParam15);
		workflowDriverManager.saveWorkflowDriver(wd9);

		log.info("---------------------new deploy-----------------------");
		FlowDeploy fd = new FlowDeploy();
		fd.setCreateTime((new GregorianCalendar()).getTime().toString());
		fd.setCurrentState(FlowDeploy.DEPLOY_STATE_PREPARING);
		fd.setFlowDeployName("DreamBike order processing flow");
		fd.setMemo("test");
		flowMetaManager.addFlowDeploy(wm.getFlowMetaID().toString(), fd);

		FlowNodeBinding nodeBinding1 = flowDeployManager.updateFlowNodeBinding(
				fd.getFlowDeployID().toString(), "dreambike_Wor1_Act1", wd1
						.getFlowDriverID().toString());
		FlowNodeBinding nodeBinding2 = flowDeployManager.updateFlowNodeBinding(
				fd.getFlowDeployID().toString(), "dreambike_Wor1_Act2", wd2
						.getFlowDriverID().toString());
		FlowNodeBinding nodeBinding3 = flowDeployManager.updateFlowNodeBinding(
				fd.getFlowDeployID().toString(), "dreambike_Wor1_Act3", wd3
						.getFlowDriverID().toString());
		FlowNodeBinding nodeBinding4 = flowDeployManager.updateFlowNodeBinding(
				fd.getFlowDeployID().toString(), "dreambike_Wor1_Act4", wd4
						.getFlowDriverID().toString());
		FlowNodeBinding nodeBinding5 = flowDeployManager.updateFlowNodeBinding(
				fd.getFlowDeployID().toString(), "dreambike_Wor1_Act5", wd5
						.getFlowDriverID().toString());
		FlowNodeBinding nodeBinding6 = flowDeployManager.updateFlowNodeBinding(
				fd.getFlowDeployID().toString(), "dreambike_Wor1_Act6", wd6
						.getFlowDriverID().toString());
		FlowNodeBinding nodeBinding7 = flowDeployManager.updateFlowNodeBinding(
				fd.getFlowDeployID().toString(), "dreambike_Wor1_Act7", wd7
						.getFlowDriverID().toString());
		FlowNodeBinding nodeBinding8 = flowDeployManager.updateFlowNodeBinding(
				fd.getFlowDeployID().toString(), "dreambike_Wor1_Act8", wd8
						.getFlowDriverID().toString());
		FlowNodeBinding nodeBinding9 = flowDeployManager.updateFlowNodeBinding(
				fd.getFlowDeployID().toString(), "dreambike_Wor1_Act9", wd9
						.getFlowDriverID().toString());
		log.info("bind parameters");
		HashMap inputParamMap = new HashMap();
		HashMap outputParamMap = new HashMap();
		outputParamMap.put("customeID", wdOutParam1.getDriverOutputParamID()
				.toString());
		outputParamMap.put("customeEmail", wdOutParam2.getDriverOutputParamID()
				.toString());
		outputParamMap.put("productID", wdOutParam3.getDriverOutputParamID()
				.toString());
		outputParamMap.put("orderID", wdOutParam7.getDriverOutputParamID()
				.toString());
		HashMap outputParamEnumeMap = new HashMap();
		flowDeployManager.updateFlowNodeParamBinding(nodeBinding1
				.getNodeBindingID().toString(), inputParamMap, outputParamMap,
				outputParamEnumeMap);

		inputParamMap = new HashMap();
		inputParamMap.put("orderID", wdInParam1.getDriverInputParamID()
				.toString());
		outputParamMap = new HashMap();
		outputParamMap.put("price", wdOutParam4.getDriverOutputParamID()
				.toString());
		outputParamMap.put("priceDetail", wdOutParam8.getDriverOutputParamID()
				.toString());
		outputParamEnumeMap = new HashMap();
		flowDeployManager.updateFlowNodeParamBinding(nodeBinding2
				.getNodeBindingID().toString(), inputParamMap, outputParamMap,
				outputParamEnumeMap);

		inputParamMap = new HashMap();
		inputParamMap.put("orderID", wdInParam2.getDriverInputParamID()
				.toString());
		outputParamMap = new HashMap();
		outputParamMap.put("stockState", wdOutParam5.getDriverOutputParamID()
				.toString());
		outputParamMap.put("stockDetail", wdOutParam10.getDriverOutputParamID()
				.toString());
		outputParamEnumeMap = new HashMap();
		outputParamEnumeMap.put("Y", stockStateY.getDriverOutputParamEnumeID()
				.toString());
		outputParamEnumeMap.put("N", stockStateN.getDriverOutputParamEnumeID()
				.toString());
		flowDeployManager.updateFlowNodeParamBinding(nodeBinding3
				.getNodeBindingID().toString(), inputParamMap, outputParamMap,
				outputParamEnumeMap);

		inputParamMap = new HashMap();
		inputParamMap.put("orderID", wdInParam3.getDriverInputParamID()
				.toString());
		outputParamMap = new HashMap();
		outputParamMap.put("techState", wdOutParam6.getDriverOutputParamID()
				.toString());
		outputParamMap.put("techDetail", wdOutParam9.getDriverOutputParamID()
				.toString());
		outputParamEnumeMap = new HashMap();
		outputParamEnumeMap.put("Y", techStateY.getDriverOutputParamEnumeID()
				.toString());
		outputParamEnumeMap.put("N", techStateN.getDriverOutputParamEnumeID()
				.toString());
		flowDeployManager.updateFlowNodeParamBinding(nodeBinding4
				.getNodeBindingID().toString(), inputParamMap, outputParamMap,
				outputParamEnumeMap);

		inputParamMap = new HashMap();
		inputParamMap.put("orderID", wdInParam4.getDriverInputParamID()
				.toString());
		inputParamMap.put("stockDetail", wdInParam5.getDriverInputParamID()
				.toString());
		flowDeployManager.updateFlowNodeParamBinding(nodeBinding5
				.getNodeBindingID().toString(), inputParamMap, new HashMap(),
				new HashMap());

		inputParamMap = new HashMap();
		inputParamMap.put("orderID", wdInParam6.getDriverInputParamID()
				.toString());
		inputParamMap.put("productID", wdInParam7.getDriverInputParamID()
				.toString());
		flowDeployManager.updateFlowNodeParamBinding(nodeBinding6
				.getNodeBindingID().toString(), inputParamMap, new HashMap(),
				new HashMap());

		inputParamMap = new HashMap();
		inputParamMap.put("orderID", wdInParam16.getDriverInputParamID()
				.toString());
		inputParamMap.put("customeEmail", wdInParam8.getDriverInputParamID()
				.toString());
		inputParamMap.put("customeID", wdInParam9.getDriverInputParamID()
				.toString());
		flowDeployManager.updateFlowNodeParamBinding(nodeBinding7
				.getNodeBindingID().toString(), inputParamMap, new HashMap(),
				new HashMap());

		inputParamMap = new HashMap();
		inputParamMap.put("orderID", wdInParam10.getDriverInputParamID()
				.toString());
		inputParamMap.put("customeEmail", wdInParam11.getDriverInputParamID()
				.toString());
		inputParamMap.put("customeID", wdInParam12.getDriverInputParamID()
				.toString());
		inputParamMap.put("price", wdInParam13.getDriverInputParamID()
				.toString());
		inputParamMap.put("priceDetail", wdInParam14.getDriverInputParamID()
				.toString());
		flowDeployManager.updateFlowNodeParamBinding(nodeBinding8
				.getNodeBindingID().toString(), inputParamMap, new HashMap(),
				new HashMap());

		inputParamMap = new HashMap();
		inputParamMap.put("orderID", wdInParam15.getDriverInputParamID()
				.toString());
		flowDeployManager.updateFlowNodeParamBinding(nodeBinding9
				.getNodeBindingID().toString(), inputParamMap, new HashMap(),
				new HashMap());

		log.info("assign flow performer");
		flowDeployManager.addUserPerformer(user.getId().toString(),
				nodeBinding1.getNodeBindingID().toString());
		flowDeployManager.addUserPerformer(user.getId().toString(),
				nodeBinding2.getNodeBindingID().toString());
		flowDeployManager.addUserPerformer(user.getId().toString(),
				nodeBinding3.getNodeBindingID().toString());
		flowDeployManager.addUserPerformer(user.getId().toString(),
				nodeBinding4.getNodeBindingID().toString());
		flowDeployManager.updateOtherPerformer(nodeBinding5.getNodeBindingID()
				.toString(), "dreambike_Wor1_Act2");
		flowDeployManager.updateOtherPerformer(nodeBinding6.getNodeBindingID()
				.toString(), "dreambike_Wor1_Act2");
		flowDeployManager.updateOtherPerformer(nodeBinding7.getNodeBindingID()
				.toString(), "dreambike_Wor1_Act2");
		flowDeployManager.updateOtherPerformer(nodeBinding8.getNodeBindingID()
				.toString(), "dreambike_Wor1_Act2");
		flowDeployManager.updateOtherPerformer(nodeBinding9.getNodeBindingID()
				.toString(), "dreambike_Wor1_Act2");
		// start the flow
		flowDeployManager.enableFlowDeploy(fd.getFlowDeployID().toString());

		log.info("---------------------START-----------------------");

		for (int i = 1; i < 11; i++) {
			ActivityReport report = new ActivityReport();
			report.setDriverID(wd1.getFlowDriverID().toString());
			HashMap output = new HashMap();
			output.put("customeID", "value1");
			output.put("productID", "value2");
			output.put("customeEmail", "value3");
			output.put("orderID", "" + i);
			report.setDriverOutputData(output);

			workflowEngine.processActivityReport(user.getId().toString(),
					report);
			log.info("Flow" + i + "-----------------------");
		}

		// This will cause the transaction to commit instead of roll back
		// --to populate the database!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		super.setComplete();
	}

	// ------------------------------------------------------------------------------
	public void setFlowMetaManager(FlowMetaManager flowMetaManager) {
		this.flowMetaManager = flowMetaManager;
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

	public void setWorkflowDriverManager(
			WorkflowDriverManager workflowDriverManager) {
		this.workflowDriverManager = workflowDriverManager;
	}

	public void setFlowDeployManager(FlowDeployManager flowDeployManager) {
		this.flowDeployManager = flowDeployManager;
	}

	public void setWorkflowEngine(WorkflowEngine workflowEngine) {
		this.workflowEngine = workflowEngine;
	}
}
