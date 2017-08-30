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

import java.util.*;

import org.powerstone.ca.model.*;

public interface RoleManager {

  public Role findByPrimaryKey(String roleID);

  public void giveRight(String roleID, String rsID);

  public List findRightsByRoleResource(String roleID, String resourceID);

  public List findRolesByResource(String resourceID);

  public void removeRightsByRoleResource(String roleID, String resourceID);

  public List findAllRoles();

  public List findRoleMembersByPage(String roleID, int pageNum, int pageSize);

  public void removeRole(String roleID);

  public Role createRole(Role role);

  public Role updateRole(Role role);

  public void removeRoleMember(String roleID, String userID);

  public void addUserToRole(String userID, String roleID);

  public void updateRoleRights(String roleID, String[] resourceIDs);
}
