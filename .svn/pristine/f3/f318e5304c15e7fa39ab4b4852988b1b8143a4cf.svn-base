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
package org.powerstone.ca.model;

import junit.framework.*;

public class UserTest
    extends TestCase {
  private User user = null;

  protected void setUp() throws Exception {
    super.setUp();
    user = new User();
  }

  protected void tearDown() throws Exception {
    user = null;
    super.tearDown();
  }

  public void testIsInGroup() {
    Group group = new Group();
    assertEquals("before join,isInGroup", false, user.isInGroup(group));
    user.joinGroup(group);
    assertEquals("after join,isInGroup", true, user.isInGroup(group));
  }

  public void testMultyJoinGroup() {
    Group group = new Group();
    assertEquals("before join,isInGroup", false, user.isInGroup(group));
    user.joinGroup(group);
    user.joinGroup(group);
    assertEquals("after join,isInGroup", true, user.isInGroup(group));
    assertEquals("group.getUsers", 1, group.getUsers().size());
  }

  public void testLeaveGroup() {
    Group group = new Group();
    user.leaveGroup(group);
    user.joinGroup(group);
    assertEquals("after join,isInGroup", true, user.isInGroup(group));
    user.leaveGroup(group);
    assertEquals("before join,isInGroup", false, user.isInGroup(group));
  }

  public void testRemove() {
    assertEquals("before remove,isRemoved", false, user.isRemoved());
    user.remove();
    assertEquals("after remove,isRemoved", true, user.isRemoved());
  }

}
