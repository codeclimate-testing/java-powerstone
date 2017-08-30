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
package org.powerstone.ca.service;

import org.powerstone.*;
import org.powerstone.ca.model.*;
import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GroupManagerTest
    extends AbstractSpringTestCase {
  private static Log log = LogFactory.getLog(GroupManagerTest.class);

  private Group _group = null;
  private GroupManager groupManager = null;
  private UserManager userManager = null;
  private User _user = null;

  protected void onSetUpInTransaction() throws Exception {

    _group = new Group();
    _group.setGroupName("setGroupName");
    _group.setMemo("setMemo");
    _group = groupManager.createGroup(_group);
    log.debug(_group);
    _user = new User();
    _user.setEmail("email");
    _user.setMemo("userMemo");
    _user.setPassword("plan text password");
    _user.setRealName("real name");
    _user.setSex("male");
    _user.setUserName("log in with it");
    User user = userManager.registerUser(_user);
    log.debug(user);
    groupManager.addUserToGroup(_user.getId().toString(),
                                _group.getGroupID().toString());

  }

  public void testCreateSubGroup() {
    Group sonGroup = new Group();
    sonGroup.setGroupName("son_GroupName");
    sonGroup.setMemo("son_Memo");
    groupManager.createSubGroup(_group.getGroupID().toString(),sonGroup);
    Group actualReturn = groupManager.findGroup(_group.getGroupID().toString());
    Group actualReturnSon = groupManager.findGroup(sonGroup.getGroupID().toString());

    assertEquals("AllGroups number", 1, actualReturn.getChildGroups().size());
    log.debug(actualReturn.getChildGroups());
    assertEquals("the _group", _group, actualReturnSon.getParentGroup());
    log.debug(actualReturnSon);
  }
  public void testAddUserToGroup() {
    _group = groupManager.findGroup( //"1"
        _group.getGroupID().toString()
        );
    _user = userManager.findUser( //"1"
        _user.getId().toString()
        );
    log.debug("group.getUsers[" + _group.getUsers().size() +
              "]user.getGroups()[" + _user.getGroups().size() + "]");

    assertTrue("_user isInGroup{" + _group + "}", _user.isInGroup(_group));
  }

  public void testFindAllGroups() {
    List actualReturn = groupManager.findAllGroups();
    assertEquals("AllGroups number", 1, actualReturn.size());
    assertEquals("the _group", _group, actualReturn.get(0));
  }

  public void testFindGroup() {
    Group actualReturn = groupManager.findGroup(_group.getGroupID().toString());
    assertEquals("return value", _group, actualReturn);
  }

  public void testFindGroupMembers() {
    List actualReturn = groupManager.findGroupMembers(_group.getGroupID().
        toString());
    assertEquals("GroupMembers number", 1, actualReturn.size());
    assertEquals("the member", _user, actualReturn.get(0));
    log.debug(actualReturn);
  }

  public void testFindGroupMembersByPage() {
    List actualReturn =
        groupManager.findGroupMembersByPage(_group.getGroupID().toString(), 1,
                                            15);
    log.debug(actualReturn);
    log.debug(actualReturn.get(0));
    assertEquals("GroupMembers number", 1, actualReturn.size());
    assertEquals("the member", _user, actualReturn.get(0));
  }

  public void testRemoveGroup() {
    log.debug(userManager.findUser(_user.getId().toString()).getGroups());
    groupManager.removeGroup(_group.getGroupID().toString());
    assertEquals("AllGroups number", 0, groupManager.findAllGroups().size());
    assertEquals("_user getGroups size", 0,
                 userManager.findUser(_user.getId().toString()).getGroups().
                 size());
  }

  public void testRemoveUserFromGroup() {
    log.debug(userManager.findUser(_user.getId().toString()).getGroups());
    log.debug(groupManager.findGroup(_group.getGroupID().toString()).getUsers());
    groupManager.removeUserFromGroup(_group.getGroupID().toString(),
                                     _user.getId().toString());
    assertEquals("_user getGroups size", 0,
                 userManager.findUser(_user.getId().toString()).getGroups().
                 size());
    assertEquals("_group getUsers size", 0,
                 groupManager.findGroup(_group.getGroupID().toString()).
                 getUsers().
                 size());
  }

  public void testUpdateGroup() {
    _group.setGroupName("renameGroup");_group.setMemo("groupmemo");
    Group actualReturn = groupManager.updateGroup(_group);
    assertEquals("return value", "renameGroup",
                 groupManager.findGroup(_group.getGroupID().toString()).
                 getGroupName());
    log.debug(actualReturn);
  }

  public void setGroupManager(GroupManager groupManager) {
    this.groupManager = groupManager;
  }

  public void setUserManager(UserManager userManager) {
    this.userManager = userManager;
  }

}
