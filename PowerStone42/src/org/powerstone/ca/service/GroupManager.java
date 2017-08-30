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
import org.powerstone.ca.model.Group;

public interface GroupManager {
  //��ID���ҷ������Ϣ
  public Group findGroup(String groupID);

  public List findAllGroups();

  public List findGroupMembers(String groupID);

  //���ҷ����ĳһҳ��Ա
  public List findGroupMembersByPage(String groupID, int pageNum, int pageSize);

  //�����������Ѵ��ڵ��û�
  public void addUserToGroup(String userID,String groupID);

  //�½�һ��
  public Group createGroup(Group group);

  //�½�һ���Ӳ���
  public Group createSubGroup(String groupID,Group sonGroup);

  //ɾ��һ������
  public void removeGroup(String groupID);

  //ɾ�������һ����Ա
  public void removeUserFromGroup(String groupID, String userID);

  //���·���
  public Group updateGroup(Group group);

}
