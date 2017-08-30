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
package org.powerstone.ca.service.impl;

import org.powerstone.ca.service.GroupManager;
import java.util.List;
import java.util.ArrayList;
import org.powerstone.ca.model.Group;
import org.powerstone.ca.dao.GroupDAO;
import org.powerstone.ca.service.UserManager;
import org.powerstone.ca.model.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GroupManagerImpl
    implements GroupManager {
  private static Log log = LogFactory.getLog(GroupManagerImpl.class);
  private GroupDAO groupDAO;
  private UserManager userManager;
  public List findAllGroups() {
    return groupDAO.findAllGroups();
  }

  public List findGroupMembers(String groupID) {
    return findGroup(groupID).getUsers();
  }

  public Group findGroup(String groupID) {
    return groupDAO.findByPrimaryKey(new Long(groupID));
  }

  public List findGroupMembersByPage(String groupID, int pageNum, int pageSize) {
    return groupDAO.findGroupMembersByPage(new Long(groupID), pageNum, pageSize);
  }

  public void addUserToGroup(String userID, String groupID) {
    User user = userManager.findUser(userID);
    Group group = findGroup(groupID);
    user.joinGroup(group);
    groupDAO.saveGroup(group);
  }

  public Group createGroup(Group group) {
    groupDAO.saveGroup(group);
    return group;
  }

  public Group createSubGroup(String groupID, Group sonGroup) {
    Group group = findGroup(groupID);
    group.addChildGroup(sonGroup);
    groupDAO.saveGroup(group);
    return sonGroup;
  }

  public void removeGroup(String groupID) {
    Group group = findGroup(groupID);
    group.prepareToBeRemove();
    groupDAO.removeGroup(new Long(groupID));
  }

  public void removeUserFromGroup(String groupID, String userID) {
    User user = userManager.findUser(userID);
    Group group = findGroup(groupID);
    user.leaveGroup(group);
    groupDAO.saveGroup(group);
  }

  public Group updateGroup(Group group) {
    Group result = findGroup(group.getGroupID().toString());
    result.setGroupName(group.getGroupName());
    result.setMemo(group.getMemo());
    groupDAO.saveGroup(result);
    return result;
  }

  public void setGroupDAO(GroupDAO groupDAO) {
    this.groupDAO = groupDAO;
  }

  public void setUserManager(UserManager userManager) {
    this.userManager = userManager;
  }
}
