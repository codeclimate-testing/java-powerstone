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
  //�½�һ���û����������κη���
  public User registerUser(User user);

  //��registerUser���������ڲ��漰����
  public User updateUser(User user);

  //change password
  public User changePass(User user);

  //userID����������not userName������¼ϵͳ���û���
  //���½�ͬ
  public User findUser(String userID);

  public User findUserByUserName(String userName);

  public User findUserByEmail(String email);

  public boolean hasRight(String userID, String rsID, String privilege);

  public boolean hasRightToDo(String userID, String actionURL,
                              String webModuleID);

  //������ͨ�Ĺ�����Ȩ�ޣ�����Ĭ�ϵ�privilege
  public void giveCommonFunctionRight(String userID, String rsID);

  //���ڹ���������
  //public void giveCommonRight(String userID, String rsID, String privilege);

  //���������˸���ԴȨ�ޣ�ֱ�ӵģ����û�
  //public List findUsersOfResource(String resourceID);

  //���Ҳ������κβ��ŵ��û�
  public List findUsersHaveNoGroup();

  //����ֱ�Ӻͼ�ӻ�õ�Ȩ��
  //public List calcAllMyRights(String userID);

  //ɾ���û�userID����resourceID��Ȩ��
  //public void removeRightsByUserResource(String userID, String resourceID);

  public List findAllUsers();

  //ɾ�������û�����resourceID��Ȩ�ޣ�����ɾ��һ�����̲���ʱ��
  //public void removeRightsByResource(String resourceID);

  //�û�userID�Ƿ���й���resourceID��Ȩ��
  //public boolean hasRightsAboutResource(String userID, String resourceID);

  public void updateUserRights(String userID, String[] resourceIDs);
}
