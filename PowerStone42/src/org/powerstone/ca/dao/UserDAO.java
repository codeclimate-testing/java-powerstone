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
  //�½��û�
  public void saveUser(User user);

  //����������Ա ����Users
  public List findAllUsers();

  //��״̬�����û�
  public List findUsersByState(String state);

  //���Ҳ������κη������Ա ����Users
  public List findUsersHaveNoGroup();

  //��ID������Ա ����User
  public User findByPrimaryKey(Long userID);

  //���û��������û�
  public User findByUserName(String userName);

  public User findUserByEmail(String email);

  //������Դ��Ȩ���û�
  //return Users
  public List findUsersOfResource(Long resourceID);

  //���������ؼ����ز���Ȩ��
  public UserRight findRightsByCombineKeys(Long userID, Long resourceID,String privilege);

}
