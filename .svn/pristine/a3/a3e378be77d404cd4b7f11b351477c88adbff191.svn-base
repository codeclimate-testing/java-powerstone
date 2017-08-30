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
import org.apache.commons.logging.LogFactory;
import java.util.List;
import org.powerstone.AbstractSpringTestCase;

public class FlowDeployManagerTest extends AbstractSpringTestCase {
	private FlowDeployManager flowDeployManager = null;

	private WorkflowDriverManager workflowDriverManager = null;

	FlowDeploy flowDeploy;

	protected void onSetUpInTransaction() throws Exception {
		log = LogFactory.getLog(FlowDeployManagerTest.class);

		flowDeploy = new FlowDeploy();
		flowDeploy.setCreateTime("-------CreateTime----------");
		flowDeploy.setCurrentState("CurrentState");
		flowDeploy.setFlowDeployName("FlowDeployName");
		flowDeploy.setMemo("Memo");

		FlowNodeBinding flowNodeBinding = new FlowNodeBinding();
		flowNodeBinding.setFlowNodeID("FlowNodeID-------------");
		flowDeploy.addFlowNodeBinding(flowNodeBinding);

		flowDeployManager.saveFlowDeploy(flowDeploy);

		log.info(flowDeploy.getFlowDeployID() + "==================");
	}

	public void testSaveFlowNodeBinding() {
		FlowNodeBinding flowNodeBinding = (FlowNodeBinding) flowDeployManager
				.getFlowDeploy(flowDeploy.getFlowDeployID().toString())
				.getFlowNodeBindings().get(0);
		flowNodeBinding.setFlowNodeID("-----FlowNodeID-------");

		flowDeployManager.saveFlowNodeBinding(flowNodeBinding);

		FlowNodeBinding fnb = (FlowNodeBinding) flowDeployManager
				.getFlowDeploy(flowDeploy.getFlowDeployID().toString())
				.getFlowNodeBindings().get(0);
		assertEquals("return value", "-----FlowNodeID-------", fnb
				.getFlowNodeID());
		log.info(fnb.getFlowNodeID());
	}

	public void testGetFlowDeploy() {
		assertEquals("return value", "Memo", flowDeployManager.getFlowDeploy(
				flowDeploy.getFlowDeployID().toString()).getMemo());
		log.info("Memo");
	}

	public void testSaveFlowDeploy() {
		flowDeploy.setMemo("-----Memo--------");
		flowDeployManager.saveFlowDeploy(flowDeploy);

		assertEquals("return value", "-----Memo--------", flowDeployManager
				.getFlowDeploy(flowDeploy.getFlowDeployID().toString())
				.getMemo());
		log.info("-----Memo--------");
	}

	public void testEnableFlowDeploy() {
		flowDeployManager.enableFlowDeploy(flowDeploy.getFlowDeployID()
				.toString());

		assertEquals("return value", true, flowDeployManager.getFlowDeploy(
				flowDeploy.getFlowDeployID().toString()).isReady());
		log.info(flowDeploy.getCurrentState());
	}

	public void testDisableFlowDeploy() {
		flowDeployManager.disableFlowDeploy(flowDeploy.getFlowDeployID()
				.toString());

		assertEquals("return value", false, flowDeployManager.getFlowDeploy(
				flowDeploy.getFlowDeployID().toString()).isReady());
		log.info(flowDeploy.getCurrentState());
	}

	public void testUpdateFlowNodeBinding() {

		WorkflowDriver wd = new WorkflowDriver();
		wd.setContextPath("/context");
		wd.setFlowDriverName("FlowDriverName");
		wd.setMemo("memo");
		wd.setReadURL("ReadURL");
		wd.setWriteURL("WriteURL");
		WFDriverOutputParam wdOutParam = new WFDriverOutputParam();
		wdOutParam.setParamAlias("ParamAlias");
		wdOutParam.setParamName("ParamName");
		WFDriverInputParam wdInParam = new WFDriverInputParam();
		wdInParam.setParamAlias("ParamAlias");
		wdInParam.setParamName("ParamName");
		WFDriverOutputParamEnume wdOutParamEnume = new WFDriverOutputParamEnume();
		wdOutParamEnume.setDriverOutputParamEnumeValue("OutputParamEnumeValue");
		workflowDriverManager.saveWorkflowDriver(wd);

		FlowNodeBinding actualReturn = flowDeployManager.updateFlowNodeBinding(
				flowDeploy.getFlowDeployID().toString(),
				"FlowNodeID-------------", wd.getFlowDriverID().toString());

		assertEquals("return value", 1, flowDeployManager.getFlowDeploy(
				flowDeploy.getFlowDeployID().toString()).getFlowNodeBindings()
				.size());

		FlowNodeBinding fnb = (FlowNodeBinding) flowDeployManager
				.getFlowDeploy(flowDeploy.getFlowDeployID().toString())
				.getFlowNodeBindings().get(0);
		assertEquals("return value", actualReturn.getFlowNodeID(), fnb
				.getFlowNodeID());

		log.info(fnb.getFlowNodeID());
	}

	public void testFindFlowNodeBindingsByDriver() {
		WorkflowDriver wd = new WorkflowDriver();
		wd.setContextPath("/context2");
		wd.setFlowDriverName("FlowDriverName2");
		wd.setMemo("memo");
		wd.setReadURL("ReadURL2");
		wd.setWriteURL("WriteURL2");
		WFDriverOutputParam wdOutParam = new WFDriverOutputParam();
		wdOutParam.setParamAlias("ParamAlias2");
		wdOutParam.setParamName("ParamName2");
		WFDriverInputParam wdInParam = new WFDriverInputParam();
		wdInParam.setParamAlias("ParamAlias2");
		wdInParam.setParamName("ParamName2");
		WFDriverOutputParamEnume wdOutParamEnume = new WFDriverOutputParamEnume();
		wdOutParamEnume.setDriverOutputParamEnumeValue("OutputParamEnumeValue");
		workflowDriverManager.saveWorkflowDriver(wd);

		FlowNodeBinding actualReturn = flowDeployManager.updateFlowNodeBinding(
				flowDeploy.getFlowDeployID().toString(),
				"FlowNodeID222-------------", wd.getFlowDriverID().toString());
		List nodeBindings = flowDeployManager.findFlowNodeBindingsByDriver(wd
				.getFlowDriverID().toString());
		assertEquals("return value", 1, nodeBindings.size());

		Object obj = nodeBindings.get(0);
		log.info(obj.getClass());

		FlowNodeBinding fnb = (FlowNodeBinding) obj;

		assertEquals("return value", actualReturn.getFlowNodeID(), fnb
				.getFlowNodeID());

		log.info(fnb.getFlowNodeID());
	}

	public void testRemoveFlowDeploy() {
		String flowDeployID = "4000";
		try {
			flowDeployManager.removeFlowDeploy(flowDeployID);
			fail("删除一个不存在的FlowDeploy");
		} catch (Exception e) {
			assertNotNull(e);
		}
	}

	public void setFlowDeployManager(FlowDeployManager flowDeployManager) {
		this.flowDeployManager = flowDeployManager;
	}

	public void setWorkflowDriverManager(
			WorkflowDriverManager workflowDriverManager) {
		this.workflowDriverManager = workflowDriverManager;
	}

}
