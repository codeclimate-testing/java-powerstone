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
import org.powerstone.ca.model.Group;
import org.powerstone.ca.model.GroupRight;

public interface GroupDAO {
  //�������з��飬���� Group
  public List findAllGroups();

  //�½�һ��
  public void saveGroup(Group group);

  //��ID���ҷ������Ϣ
  public Group findByPrimaryKey(Long groupID);

  //���ҷ����ĳһҳ��Ա
  public List findGroupMembersByPage(Long groupID, int pageNum, int pageSize);

  //ɾ��һ������
  public void removeGroup(Long groupID);

  //���������ؼ����ز���Ȩ��
  public GroupRight findRightsByCombineKeys(Long groupID, Long resourceID,
                                            String privilege);

  //����ĳ����Դ��Ȩ�����з���
  public List findGroupsOfResource(Long resourceID);
}
