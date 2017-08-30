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

import org.powerstone.*;
import java.util.*;
import org.powerstone.ca.model.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RoleDAOTest
    extends AbstractSpringTestCase {
  private static Log log = LogFactory.getLog(RoleDAOTest.class);
  private RoleDAO roleDAO = null;
  Role _role = null;

  protected void onSetUpInTransaction() throws Exception {
    _role = new Role();
    _role.setRoleName("role_name");
    _role.setMemo("");
    roleDAO.saveRole(_role);
  }

  public void testFindAllRoles() {
    List actualReturn = roleDAO.findAllRoles();
    assertEquals("number of all roles", 1, actualReturn.size());
    assertEquals("the only role", _role, actualReturn.get(0));
  }

  public void testFindByPrimaryKey() {
    Role actualReturn = roleDAO.findByPrimaryKey(_role.getRoleID());
    assertEquals("the role", _role, actualReturn);
  }

  public void testFindRightsByCombineKeys() {
    String privilege = "";
    RoleRight actualReturn =
        roleDAO.findRightsByCombineKeys(_role.getRoleID(), new Long(1),
                                        privilege);
    assertEquals("RightsByCombineKeys", null, actualReturn);
  }

  public void testFindRoleMembersByPage() {
    int pageNum = 1;
    int pageSize = 20;
    List actualReturn = roleDAO.findRoleMembersByPage(_role.getRoleID(),
        pageNum, pageSize);
    assertEquals("a page of role members", 0, actualReturn.size());
  }

  public void testFindRolesOfResource() {
    List actualReturn = roleDAO.findRolesOfResource(new Long(1));
    assertEquals("roles having relevant rights of resource", 0,
                 actualReturn.size());
  }

  public void testRemoveRole() {
    this.assertEquals("roles of users", 1,
                      jdbcTemplate.queryForInt("select count(*) from CA_ROLE"));
    roleDAO.removeRole(_role.getRoleID());
    this.assertEquals("number of roles", 0,
                      jdbcTemplate.queryForInt("select count(*) from CA_ROLE"));
  }

  public void testSaveRole() {
    log.info("before save:" + _role);
    _role.setMemo("_role_memo");
    _role.setRoleName("_role_name");
    roleDAO.saveRole(_role);
    this.assertEquals("number of roles", "_role_memo",
                      jdbcTemplate.queryForObject(
        "select VC_ROLE_MEMO from CA_ROLE", String.class));
    this.assertEquals("number of roles", "_role_name",
                      jdbcTemplate.queryForObject(
        "select VC_ROLE_NAME from CA_ROLE", String.class));
    log.info("after save:" + _role);
  }

  public void setRoleDAO(RoleDAO roleDAO) {
    this.roleDAO = roleDAO;
  }

}
