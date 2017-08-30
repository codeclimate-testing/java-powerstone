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

import org.powerstone.ca.service.RoleManager;
import org.powerstone.ca.model.Role;
import org.powerstone.ca.model.RoleRight;
import java.util.List;
import java.util.ArrayList;
import org.powerstone.ca.dao.RoleDAO;
import org.powerstone.ca.model.User;
import org.powerstone.ca.service.UserManager;
import org.powerstone.ca.service.ResourceManager;
import org.powerstone.ca.model.GivenRight;

public class RoleManagerImpl
    implements RoleManager {
  private RoleDAO roleDAO = null;
  private UserManager userManager;
  private ResourceManager resourceManager;

  public Role findByPrimaryKey(String roleID) {
    return roleDAO.findByPrimaryKey(new Long(roleID));
  }

  public void giveRight(String roleID, String rsID) {
    Role role = this.findByPrimaryKey(roleID);
    RoleRight roleRight = new RoleRight();
    roleRight.setPrivilege(GivenRight.COMMON_FUNCTION_RIGHT);
    roleRight.setResource(resourceManager.findResource(rsID));
    role.addRight(roleRight);
    roleDAO.saveRole(role);
  }

  public List findRightsByRoleResource(String roleID, String resourceID) {
    return new ArrayList();
  }

  public List findRolesByResource(String resourceID) {
    return new ArrayList();
  }

  public void removeRightsByRoleResource(String roleID, String resourceID) {
  }

  public List findAllRoles() {
    return roleDAO.findAllRoles();
  }

  public List findRoleMembersByPage(String roleID, int pageNum, int pageSize) {
    return roleDAO.findRoleMembersByPage(new Long(roleID), pageNum, pageSize);
  }

  public void removeRoleMember(String roleID, String userID) {
    User user = userManager.findUser(userID);
    Role role = this.findByPrimaryKey(roleID);
    user.leaveRole(role);
    this.roleDAO.saveRole(role);
  }

  public void addUserToRole(String userID, String roleID) {
    User user = userManager.findUser(userID);
    Role role = this.findByPrimaryKey(roleID);
    user.joinRole(role);
    roleDAO.saveRole(role);
  }

  public void removeRole(String roleID) {
    roleDAO.removeRole(new Long(roleID));
  }

  public void setRoleDAO(RoleDAO roleDAO) {
    this.roleDAO = roleDAO;
  }

  public Role createRole(Role role) {
    roleDAO.saveRole(role);
    return role;
  }

  public Role updateRole(Role role) {
    Role result = findByPrimaryKey(role.getRoleID().toString());
    result.setRoleName(role.getRoleName());
    result.setMemo(role.getMemo());
    roleDAO.saveRole(result);
    return result;
  }

  public void setUserManager(UserManager userManager) {
    this.userManager = userManager;
  }

  public void updateRoleRights(String roleID, String[] resourceIDs) {
    Role role = this.findByPrimaryKey(roleID);
    role.clearRights();
    if (resourceIDs != null && resourceIDs.length > 0) {
      for (int i = 0; i < resourceIDs.length; i++) {
        RoleRight roleRight = new RoleRight();
        roleRight.setPrivilege(GivenRight.COMMON_FUNCTION_RIGHT);
        roleRight.setResource(resourceManager.findResource(resourceIDs[i]));
        role.addRight(roleRight);
      }
    }
    roleDAO.saveRole(role);
  }

  public void setResourceManager(ResourceManager resourceManager) {
    this.resourceManager = resourceManager;
  }
}
