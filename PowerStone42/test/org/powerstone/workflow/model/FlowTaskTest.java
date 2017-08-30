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

import junit.framework.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FlowTaskTest
    extends TestCase {
  private FlowTask flowTask = null;
  protected static Log log = LogFactory.getLog(FlowTaskTest.class);
  protected void setUp() throws Exception {
    super.setUp();
    flowTask = new FlowTask();
    flowTask.setTaskID(new Long(100));
  }

  protected void tearDown() throws Exception {
    flowTask = null;
    super.tearDown();
  }

  public void testAddTaskCandidate() {
    String userID = "11";
    flowTask.addTaskCandidate(userID);
    flowTask.addTaskCandidate(userID);
    assertEquals("TaskCandidate数量", 1, flowTask.getNewTasks().size());
    log.info("TaskCandidate数量=1");
  }

  public void testNeedAssign() {
    String userID = "11";
    flowTask.needAsssign(userID);
    flowTask.needAsssign(userID);
    assertEquals("TaskAssigner数量", 1, flowTask.getTaskAssigners().size());
    log.info("TaskAssigner数量=1");
  }
}
