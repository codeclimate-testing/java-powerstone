package org.powerstone.workflow.service;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easymock.MockControl;
import org.powerstone.workflow.dao.FlowTaskDAO;
import org.powerstone.workflow.service.impl.FlowTaskManagerImpl;

public class FlowTaskManagerMockTest extends TestCase {
	private static Log log = LogFactory.getLog(FlowTaskManagerMockTest.class);

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/*
	 * Test method for
	 * 'org.powerstone.workflow.service.FlowTaskManager.isTaskOwner(String,
	 * String)'
	 */
	public void testIsTaskOwner() {
		MockControl ftControl = MockControl.createControl(FlowTaskDAO.class);
		final FlowTaskDAO flowTaskDAO = (FlowTaskDAO) ftControl.getMock();
		FlowTaskManagerImpl ftManager = new FlowTaskManagerImpl();
		ftManager.setFlowTaskDAO(flowTaskDAO);

		flowTaskDAO.isTaskOwner("wwww", new Long(123));
		ftControl.setReturnValue(true, 1);
		ftControl.replay();

		assertTrue("Is Task Owner", ftManager.isTaskOwner("wwww", "123"));
		
		log.debug("ftManager.isTaskOwner(wwww, 123)");
		ftControl.verify();
	}

	/*
	 * Test method for
	 * 'org.powerstone.workflow.service.FlowTaskManager.isTaskAssigner(String,
	 * String)'
	 */
	public void testIsTaskAssigner() {
		MockControl ftControl = MockControl.createControl(FlowTaskDAO.class);
		final FlowTaskDAO flowTaskDAO = (FlowTaskDAO) ftControl.getMock();
		FlowTaskManagerImpl ftManager = new FlowTaskManagerImpl();
		ftManager.setFlowTaskDAO(flowTaskDAO);

		flowTaskDAO.isTaskAssigner("wwww", new Long(123));
		ftControl.setReturnValue(true, 1);
		ftControl.replay();

		assertTrue("isTaskAssigner", ftManager.isTaskAssigner("wwww", "123"));
		ftControl.verify();
	}

	/*
	 * Test method for
	 * 'org.powerstone.workflow.service.FlowTaskManager.getFlowTask(String)'
	 */
	public void testGetFlowTask() {

	}

	/*
	 * Test method for
	 * 'org.powerstone.workflow.service.FlowTaskManager.createFlowTask(FlowProcTransaction,
	 * FlowNodeBinding)'
	 */
	public void testCreateFlowTask() {

	}

	/*
	 * Test method for
	 * 'org.powerstone.workflow.service.FlowTaskManager.checkOutTask(String,
	 * String)'
	 */
	public void testCheckOutTask() {

	}

	/*
	 * Test method for
	 * 'org.powerstone.workflow.service.FlowTaskManager.finishTask(String)'
	 */
	public void testFinishTask() {

	}

	/*
	 * Test method for
	 * 'org.powerstone.workflow.service.FlowTaskManager.emailTask(String)'
	 */
	public void testEmailTask() {

	}

	/*
	 * Test method for
	 * 'org.powerstone.workflow.service.FlowTaskManager.abortTask(String,
	 * String)'
	 */
	public void testAbortTask() {

	}

	/*
	 * Test method for
	 * 'org.powerstone.workflow.service.FlowTaskManager.assignTask(String,
	 * String)'
	 */
	public void testAssignTask() {

	}

	/*
	 * Test method for
	 * 'org.powerstone.workflow.service.FlowTaskManager.findAllMyNewTasks(String)'
	 */
	public void testFindAllMyNewTasks() {

	}

	/*
	 * Test method for
	 * 'org.powerstone.workflow.service.FlowTaskManager.findMyExecutingTasksKinds(String)'
	 */
	public void testFindMyExecutingTasksKinds() {

	}

	/*
	 * Test method for
	 * 'org.powerstone.workflow.service.FlowTaskManager.findMyTasksToAssign(String)'
	 */
	public void testFindMyTasksToAssign() {

	}

	/*
	 * Test method for
	 * 'org.powerstone.workflow.service.FlowTaskManager.findMyRefusedTasks(String)'
	 */
	public void testFindMyRefusedTasks() {

	}

	/*
	 * Test method for
	 * 'org.powerstone.workflow.service.FlowTaskManager.findNewTasksNotEmailed()'
	 */
	public void testFindNewTasksNotEmailed() {

	}

	/*
	 * Test method for
	 * 'org.powerstone.workflow.service.FlowTaskManager.findMyFinishedTasksKinds(String)'
	 */
	public void testFindMyFinishedTasksKinds() {

	}

	/*
	 * Test method for
	 * 'org.powerstone.workflow.service.FlowTaskManager.findMyNewTasksNum(String)'
	 */
	public void testFindMyNewTasksNum() {

	}

	/*
	 * Test method for
	 * 'org.powerstone.workflow.service.FlowTaskManager.findMyNewTasksKinds(String)'
	 */
	public void testFindMyNewTasksKinds() {

	}

	/*
	 * Test method for
	 * 'org.powerstone.workflow.service.FlowTaskManager.findMyNewTasksNumByType(String,
	 * String)'
	 */
	public void testFindMyNewTasksNumByType() {

	}

	/*
	 * Test method for
	 * 'org.powerstone.workflow.service.FlowTaskManager.findMyNewTasksByType(String,
	 * String, int, int)'
	 */
	public void testFindMyNewTasksByType() {

	}

	/*
	 * Test method for
	 * 'org.powerstone.workflow.service.FlowTaskManager.findMyExecutingTasksNum(String)'
	 */
	public void testFindMyExecutingTasksNum() {

	}

	/*
	 * Test method for
	 * 'org.powerstone.workflow.service.FlowTaskManager.findMyExecutingTasksNumByType(String,
	 * String)'
	 */
	public void testFindMyExecutingTasksNumByType() {

	}

	/*
	 * Test method for
	 * 'org.powerstone.workflow.service.FlowTaskManager.findMyExecutingTasksByType(String,
	 * String, int, int)'
	 */
	public void testFindMyExecutingTasksByType() {

	}

	/*
	 * Test method for
	 * 'org.powerstone.workflow.service.FlowTaskManager.findMyRefusedTasksNum(String)'
	 */
	public void testFindMyRefusedTasksNum() {

	}

	/*
	 * Test method for
	 * 'org.powerstone.workflow.service.FlowTaskManager.findMyFinishedTasksNum(String)'
	 */
	public void testFindMyFinishedTasksNum() {

	}

	/*
	 * Test method for
	 * 'org.powerstone.workflow.service.FlowTaskManager.findMyFinishedTasksNumByType(String,
	 * String)'
	 */
	public void testFindMyFinishedTasksNumByType() {

	}

	/*
	 * Test method for
	 * 'org.powerstone.workflow.service.FlowTaskManager.findMyFinishedTasksByType(String,
	 * String, int, int)'
	 */
	public void testFindMyFinishedTasksByType() {

	}

	/*
	 * Test method for
	 * 'org.powerstone.workflow.service.FlowTaskManager.findTaskByNodeAndProc(String,
	 * String)'
	 */
	public void testFindTaskByNodeAndProc() {

	}

	/*
	 * Test method for
	 * 'org.powerstone.workflow.service.FlowTaskManager.findMyTasksToAssignNum(String)'
	 */
	public void testFindMyTasksToAssignNum() {

	}

	/*
	 * Test method for
	 * 'org.powerstone.workflow.service.FlowTaskManager.distributeTask(String,
	 * String, String)'
	 */
	public void testDistributeTask() {

	}

	/*
	 * Test method for
	 * 'org.powerstone.workflow.service.FlowTaskManager.taskPreviewProcess(List)'
	 */
	public void testTaskPreviewProcess() {

	}

	/*
	 * Test method for
	 * 'org.powerstone.workflow.service.FlowTaskManager.fenfaNewTask(String,
	 * String)'
	 */
	public void testFenfaNewTask() {

	}

	/*
	 * Test method for
	 * 'org.powerstone.workflow.service.FlowTaskManager.findTasksByProc(String)'
	 */
	public void testFindTasksByProc() {

	}

	/*
	 * Test method for
	 * 'org.powerstone.workflow.service.FlowTaskManager.needAsssign(String,
	 * String)'
	 */
	public void testNeedAsssign() {

	}

	/*
	 * Test method for
	 * 'org.powerstone.workflow.service.FlowTaskManager.refuseTasks(String,
	 * String[], String, String)'
	 */
	public void testRefuseTasks() {

	}

	/*
	 * Test method for
	 * 'org.powerstone.workflow.service.FlowTaskManager.findTasksToRefuse(String)'
	 */
	public void testFindTasksToRefuse() {

	}

	/*
	 * Test method for
	 * 'org.powerstone.workflow.service.FlowTaskManager.getBackFlowTask(String,
	 * String)'
	 */
	public void testGetBackFlowTask() {

	}

}
