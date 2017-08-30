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

import org.powerstone.ca.service.UserManager;
import org.powerstone.ca.model.User;
import org.powerstone.ca.model.UserRight;
import java.util.List;
import java.util.ArrayList;
import org.powerstone.ca.dao.UserDAO;
import org.powerstone.util.StringUtil;
import java.util.Iterator;
import org.powerstone.ca.model.GivenRight;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.powerstone.ca.model.Role;
import org.powerstone.ca.service.ResourceManager;
import org.powerstone.ca.model.WebModule;
import org.powerstone.ca.model.Resource;

public class UserManagerImpl
    implements UserManager {
  private static Log log = LogFactory.getLog(UserManagerImpl.class);
  private UserDAO userDAO;
  private ResourceManager resourceManager;
  public User registerUser(User user) {
    user.setPassword(StringUtil.hash(user.getPassword())); //encrypt
    userDAO.saveUser(user);
    return user;
  }

  public User updateUser(User user) {
    User target = userDAO.findByPrimaryKey(user.getId());
    target.setEmail(user.getEmail());
    target.setMemo(user.getMemo());
    target.setRealName(user.getRealName());
    target.setSex(user.getSex());
    target.setState(user.getState());
    target.setUserName(user.getUserName());

    userDAO.saveUser(target);
    return target;
  }

  public User changePass(User user) {
    User target = userDAO.findByPrimaryKey(user.getId());
    target.setPassword(StringUtil.hash(user.getPassword())); //encrypt
    userDAO.saveUser(target);
    return target;
  }

  public User findUser(String userID) {
    return userDAO.findByPrimaryKey(new Long(userID));
  }

  public User findUserByUserName(String userName) {
    return userDAO.findByUserName(userName);
  }

  public User findUserByEmail(String email) {
    return userDAO.findUserByEmail(email);
  }

  public List findAllUsers() {
    return userDAO.findAllUsers();
  }

  public List findUsersHaveNoGroup() {
    return userDAO.findUsersHaveNoGroup();
  }

  public boolean hasRight(String userID, String rsID, String privilege) {
    User user = this.findUser(userID);
    //检查直接授权
    for (Iterator it = user.getRights().iterator(); it.hasNext(); ) {
      GivenRight gr = (GivenRight) it.next();
      if (gr.getResource().getId().toString().equals(rsID) &&
          gr.getPrivilege().equals(privilege)) {
        if (log.isDebugEnabled()) {
          log.debug("user[" + userID + "]hasRight[rsID=" + rsID + "|privilege=" +
                    privilege +
                    "]");
        }
        return true;
      }
    }
    //检查角色授权
    for (Iterator it = user.getRoles().iterator(); it.hasNext(); ) {
      Role role = (Role) it.next();
      for (Iterator itr = role.getRights().iterator(); itr.hasNext(); ) {
        GivenRight gr = (GivenRight) itr.next();
        if (gr.getResource().getId().toString().equals(rsID) &&
            gr.getPrivilege().equals(privilege)) {
          if (log.isDebugEnabled()) {
            log.debug("user[" + userID + "]hasRight[rsID=" + rsID +
                      "|privilege=" + privilege +
                      "] through role" + role + "");
          }
          return true;
        }
      }
    }

    if (log.isDebugEnabled()) {
      log.debug("user[" + userID + "]doesn't haveRight[rsID=" + rsID +
                "|privilege=" +
                privilege + "]!");
    }
    return false;
  }

  public boolean hasRightToDo(String userID, String actionURL,
                              String webModuleID) {
    WebModule webModule = resourceManager.findWebModule(webModuleID);
    for (Iterator it = webModule.getResources().iterator(); it.hasNext(); ) {
      Resource resource = (Resource) it.next();
      if (actionURL != null && actionURL.startsWith(resource.getActionURL())) {
        if (log.isDebugEnabled()) {
          log.debug("checking user[" + userID + "] hasRight[rsID=" +
                    resource.getId() +
                    "|privilege=" + GivenRight.COMMON_FUNCTION_RIGHT +
                    "]--->[actionURL=" + actionURL +
                    "|resource.getActionURL()=" + resource.getActionURL() + "]");
        }
        return this.hasRight(userID, resource.getId().toString(),
                             GivenRight.COMMON_FUNCTION_RIGHT);
      }
    }
    if (log.isDebugEnabled()) {
      log.debug("No matching actionURL when checking" +
                " hasRightToDo(userID=" + userID + "|actionURL=" + actionURL +
                "|webModuleID=" + webModuleID + "),will return true!");
    }
    return true;
  }

//  public List findUsersOfResource(String resourceID) {
//    User _user = new User();
//    _user.setEmail("email");
//    _user.setMemo("userMemo");
//    _user.setPassword("plan text password");
//    _user.setRealName("real name");
//    _user.setSex("male");
//    _user.setUserName("log in with it");
//    ArrayList result = new ArrayList();
//    result.add(_user);
//    return result;
//  }

//  public List calcAllMyRights(String userID) {
//    return new ArrayList();
//  }

//  public void removeRightsByUserResource(String userID, String resourceID) {
//  }
//
//  public void removeRightsByResource(String resourceID) {
//  }
//
//  public boolean hasRightsAboutResource(String userID, String resourceID) {
//    return true;
//  }
//
//  public void giveCommonFunctionRight(String userID, String rsID) {
//
//  }
//
//  public void giveCommonRight(String userID, String rsID, String privilege) {
//  }

  public void setUserDAO(UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  public void setResourceManager(ResourceManager resourceManager) {
    this.resourceManager = resourceManager;
  }

  /**
   * giveCommonFunctionRight
   *
   * @param userID String
   * @param rsID String
   */
  public void giveCommonFunctionRight(String userID, String rsID) {
    User user = this.findUser(userID);
    UserRight userRight = new UserRight();
    userRight.setPrivilege(GivenRight.COMMON_FUNCTION_RIGHT);
    userRight.setResource(resourceManager.findResource(rsID));
    user.addRight(userRight);
    userDAO.saveUser(user);
  }

  public void updateUserRights(String userID, String[] resourceIDs) {
    User user = this.findUser(userID);
    user.clearRights();
    if (resourceIDs != null && resourceIDs.length > 0) {
      for (int i = 0; i < resourceIDs.length; i++) {
        UserRight userRight = new UserRight();
        userRight.setPrivilege(GivenRight.COMMON_FUNCTION_RIGHT);
        userRight.setResource(resourceManager.findResource(resourceIDs[i]));
        user.addRight(userRight);
      }
    }
    userDAO.saveUser(user);
  }

}
