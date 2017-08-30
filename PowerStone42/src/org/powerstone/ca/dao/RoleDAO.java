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
package org.powerstone.ca.dao;

import java.util.List;
import org.powerstone.ca.model.Role;
import org.powerstone.ca.model.RoleRight;

public interface RoleDAO {
  //查找所有角色，返回 RoleForms
  public List findAllRoles();

  public void saveRole(Role role);

  public Role findByPrimaryKey(Long roleID);

  //查找角色的某一页成员
  public List findRoleMembersByPage(Long roleID, int pageNum, int pageSize);

  public void removeRole(Long roleID);

  //根据三个关键因素查找权限
  public RoleRight findRightsByCombineKeys(Long roleID, Long resourceID,
                                           String privilege);

  //查找资源授权的角色 return Roles
  public List findRolesOfResource(Long resourceID);
}
