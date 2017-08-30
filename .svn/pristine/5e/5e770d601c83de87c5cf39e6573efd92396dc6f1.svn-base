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
import java.util.*;
import org.powerstone.ca.model.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.powerstone.util.StringUtil;

public class UserManagerTest
    extends AbstractSpringTestCase {
  private UserManager userManager = null;
  private static Log log = LogFactory.getLog(UserManagerTest.class);

  private User _user = null;

  protected void onSetUpInTransaction() {
    _user = new User();
    _user.setEmail("email");
    _user.setMemo("userMemo");
    _user.setPassword("plan text password");
    _user.setRealName("real name");
    _user.setSex("male");
    _user.setUserName("log in with it");
    User user = userManager.registerUser(_user);
    log.debug(user);
    log.debug(userManager.findAllUsers());
  }

//  public void testCalcAllMyRights() {
//    List actualReturn = userManager.calcAllMyRights(_user.getId().toString());
//    assertEquals("CalcAllMyRights .size",0, actualReturn.size());
//  }

  public void testFindAllUsers() {
    List actualReturn = userManager.findAllUsers();
    this.assertEquals("number of all users", 1, actualReturn.size());
    this.assertEquals("the only user", _user, (User) actualReturn.get(0));
  }

  public void testFindUser() {
    log.debug(StringUtil.hash("plan text password"));
    User actualReturn = userManager.findUser(_user.getId().toString());
    assertEquals("the user", _user, actualReturn);
    assertEquals("the user", StringUtil.hash("plan text password"),
                 actualReturn.getPassword());
  }

//  public void testFindUsersOfResource() {
//    List actualReturn = userManager.findUsersOfResource("resourceID");
//    assertEquals("FindUsersOfResource .size", 1, actualReturn.size());
//  }

  public void testFindUsersHaveNoGroup() {
    List actualReturn = userManager.findUsersHaveNoGroup();
    this.assertEquals("number of users haveNoGroup", 1, actualReturn.size());
    log.info("~~~~~~~~~~~~>>number of users haveNoGroup[" + actualReturn.size() +
             "]");
  }

//  public void testGiveCommonFunctionRight() {
//    userManager.giveCommonFunctionRight(_user.getId().toString(), "resourceID");
//  }
//
//  public void testGiveCommonRight() {
//    userManager.giveCommonRight(_user.getId().toString(), "resourceID", "");
//  }

  public void testHasRight() {

    String privilege = "";
    boolean actualReturn = userManager.hasRight(_user.getId().toString(),
                                                "resourceID", privilege);
    assertEquals("HasRight", false, actualReturn);
  }

//  public void testHasRightToDo() {
//    boolean actualReturn =
//        userManager.hasRightToDo(_user.getId().toString(), "actionURL",
//                                 "webModuleID");
//    assertEquals("HasRightToDo", true, actualReturn);
//  }

//  public void testHasRightsAboutResource() {
//    boolean actualReturn =
//        userManager.hasRightsAboutResource(_user.getId().toString(),
//                                           "resourceID");
//    assertEquals("HasRightsAboutResource", true, actualReturn);
//  }

  public void testRegisterUser() {
    log.debug("--------------testRegisterUser--------------");
    User _user2 = new User();
    _user2.setEmail("email");
    _user2.setMemo("userMemo");
    _user2.setPassword("plan text password");
    _user2.setRealName("real name");
    _user2.setSex("male");
    _user2.setUserName("log in with it");
    try {
      _user2 = userManager.registerUser(_user2);
      log.debug(userManager.findAllUsers());
      fail(
          "did not throw exception when insert Duplicate entry for column Email");
    }
    catch (Exception e) {
      log.debug(e);
      log.debug(_user2);
    }
  }

//  public void testRemoveRightsByResource() {
//    String resourceID = "";
//    userManager.removeRightsByResource(resourceID);
//    /**@todo fill in the test code*/
//  }
//
//  public void testRemoveRightsByUserResource() {
//    String userID = "";
//    String resourceID = "";
//    userManager.removeRightsByUserResource(userID, resourceID);
//    /**@todo fill in the test code*/
//  }

  public void testUpdateUser() {
    User _user2 = new User();
    _user2.setId(_user.getId());
    _user2.setEmail("email2");
    _user2.setMemo("userMemo2");
    _user2.setPassword("plan text password");
    _user2.setRealName("real name2");
    _user2.setSex("male");
    _user2.setUserName("log in with it");

    _user2 = userManager.updateUser(_user2);
    log.debug(_user2);
    assertEquals("the user", userManager.findUser(_user.getId().toString()),
                 _user2);
    assertEquals("the user Password", StringUtil.hash("plan text password"),
                 _user2.getPassword());
  }

  public void setUserManager(UserManager userManager) {
    this.userManager = userManager;
  }

}
