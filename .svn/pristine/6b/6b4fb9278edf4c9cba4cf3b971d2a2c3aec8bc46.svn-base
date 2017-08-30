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

public interface UserManager {
  //新建一个用户，不属于任何分组
  public User registerUser(User user);

  //与registerUser的区别在于不涉及密码
  public User updateUser(User user);

  //change password
  public User changePass(User user);

  //userID——主键，not userName——登录系统的用户名
  //以下皆同
  public User findUser(String userID);

  public User findUserByUserName(String userName);

  public User findUserByEmail(String email);

  public boolean hasRight(String userID, String rsID, String privilege);

  public boolean hasRightToDo(String userID, String actionURL,
                              String webModuleID);

  //授予普通的功能型权限，采用默认的privilege
  public void giveCommonFunctionRight(String userID, String rsID);

  //用于工作流部署
  //public void giveCommonRight(String userID, String rsID, String privilege);

  //查找授予了该资源权限（直接的）的用户
  //public List findUsersOfResource(String resourceID);

  //查找不属于任何部门的用户
  public List findUsersHaveNoGroup();

  //计算直接和间接获得的权限
  //public List calcAllMyRights(String userID);

  //删除用户userID关于resourceID的权限
  //public void removeRightsByUserResource(String userID, String resourceID);

  public List findAllUsers();

  //删除所有用户关于resourceID的权限（例如删除一个流程部署时）
  //public void removeRightsByResource(String resourceID);

  //用户userID是否具有关于resourceID的权限
  //public boolean hasRightsAboutResource(String userID, String resourceID);

  public void updateUserRights(String userID, String[] resourceIDs);
}
