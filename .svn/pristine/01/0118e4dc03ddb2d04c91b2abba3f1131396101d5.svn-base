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

import org.powerstone.ca.model.User;
import java.util.List;
import org.powerstone.ca.model.UserRight;

public interface UserDAO {
  //新建用户
  public void saveUser(User user);

  //查找所有人员 返回Users
  public List findAllUsers();

  //按状态查找用户
  public List findUsersByState(String state);

  //查找不属于任何分组的人员 返回Users
  public List findUsersHaveNoGroup();

  //按ID查找人员 返回User
  public User findByPrimaryKey(Long userID);

  //按用户名查找用户
  public User findByUserName(String userName);

  public User findUserByEmail(String email);

  //查找资源授权的用户
  //return Users
  public List findUsersOfResource(Long resourceID);

  //根据三个关键因素查找权限
  public UserRight findRightsByCombineKeys(Long userID, Long resourceID,String privilege);

}
