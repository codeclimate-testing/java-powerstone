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

import org.powerstone.ca.CADelegater;
import org.powerstone.ca.service.UserManager;
import org.powerstone.ca.service.GroupManager;
import org.powerstone.ca.service.RoleManager;
import org.powerstone.ca.service.AuthenticateManager;
import org.powerstone.ca.model.User;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import java.util.List;
import org.powerstone.ca.model.Role;

public class CADelegaterImpl
    implements CADelegater {
  private static Log log = LogFactory.getLog(CADelegaterImpl.class);
  private UserManager userManager;
  private GroupManager groupManager;
  private RoleManager roleManager;
  private AuthenticateManager authenticate;

  public void setUserManager(UserManager userManager) {
    this.userManager = userManager;
  }

  public void setGroupManager(GroupManager groupManager) {
    this.groupManager = groupManager;
  }

  public void setRoleManager(RoleManager roleManager) {
    this.roleManager = roleManager;
  }

  public void setAuthenticate(AuthenticateManager authenticate) {
    this.authenticate = authenticate;
  }

//------------------------------------------------------------------------------
  /**
   * @param request HttpServletRequest
   * @return String userID (not userName)
   */
  public String getRemoteUser(HttpServletRequest request) {
    if (log.isDebugEnabled()) {
      log.debug(authenticate);
    }
    return authenticate.getRemoteUser(request);
  }

  public boolean authenticate(String userName, String pass,
                              HttpServletRequest request,
                              HttpServletResponse response) {
    return authenticate.authenticate(userName, pass, request, response);
  }

  public boolean hasRightToDo(String userID, String actionURL,
                              String webModuleID) {
    return userManager.hasRightToDo(userID, actionURL, webModuleID);
  }

  public List findAllRoles() {
    return roleManager.findAllRoles();
  }

  public List findAllGroups() {
    return groupManager.findAllGroups();
  }

  public List findGroupMembers(String groupID) {
    return (List) groupManager.findGroupMembers(groupID);
  }

  public User findUserByUserID(String userID) {
    return userManager.findUser(userID);
  }

  public List findAllUsers() {
    return userManager.findAllUsers();
  }

  public Role findRoleByRoleID(String roleID) {
    return roleManager.findByPrimaryKey(roleID);
  }

//  public User findUserByUserName(String userName) {
//    return userManager.findUserByUserName(userName);
//  }
}
