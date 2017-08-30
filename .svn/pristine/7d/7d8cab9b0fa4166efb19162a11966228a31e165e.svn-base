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

import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.powerstone.ca.model.*;
import org.powerstone.AbstractSpringTestCase;

/**
 * <p>Title: PowerStone</p>
 */
public class UserDAOTest
    extends AbstractSpringTestCase {
  private static Log log = LogFactory.getLog(UserDAOTest.class);
  private UserDAO userDAO;
  private User _user = null;

  protected void onSetUpInTransaction() {
    _user = new User();
    _user.setEmail("email");
    _user.setMemo("userMemo");
    _user.setPassword("plan text password");
    _user.setRealName("real name");
    _user.setSex("male");
    _user.setUserName("log in with it");
    userDAO.saveUser(_user);
    log.debug(userDAO.findAllUsers());
  }

  public void testSaveUser() {
    this.assertTrue("user.getId()!=null", _user.getId() != null);
    log.info(_user);
    this.assertEquals("number of users", 1,
                      jdbcTemplate.queryForInt("select count(*) from CA_USER"));
  }

  public void testFindUsersHaveNoGroup() {
    List actualReturn = userDAO.findUsersHaveNoGroup();
    this.assertEquals("number of users haveNoGroup", 1, actualReturn.size());
    log.info("~~~~~~~~~~~~>>number of users haveNoGroup[" + actualReturn.size() +
             "]");
  }

  public void testFindUsersOfResource() {
    List actualReturn = userDAO.findUsersOfResource(new Long(1));
    assertEquals("users having relevant rights of resource", 0,
                 actualReturn.size());
  }

  public void testFindAllUsers() {
    List actualReturn = userDAO.findAllUsers();
    this.assertEquals("number of all users", 1, actualReturn.size());
    this.assertEquals("the only user", _user, (User) actualReturn.get(0));
  }

  public void testFindByPrimaryKey() {
    User actualReturn = userDAO.findByPrimaryKey(_user.getId());
    assertEquals("the user", _user, actualReturn);
  }

  public void testFindByUserName() {
    User actualReturn = userDAO.findByUserName(_user.getUserName());
    assertEquals("the user", _user, actualReturn);
  }

  public void testFindRightsByCombineKeys() {
    String privilege = "";
    UserRight actualReturn =
        userDAO.findRightsByCombineKeys(_user.getId(), new Long(1), privilege);
    assertEquals("RightsByCombineKeys", null, actualReturn);
  }

  public void setUserDAO(UserDAO userDAO) {
    this.userDAO = userDAO;
  }
}
