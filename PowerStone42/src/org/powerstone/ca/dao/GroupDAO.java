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
  //查找所有分组，返回 Group
  public List findAllGroups();

  //新建一个
  public void saveGroup(Group group);

  //按ID查找分组的信息
  public Group findByPrimaryKey(Long groupID);

  //查找分组的某一页成员
  public List findGroupMembersByPage(Long groupID, int pageNum, int pageSize);

  //删除一个分组
  public void removeGroup(Long groupID);

  //根据三个关键因素查找权限
  public GroupRight findRightsByCombineKeys(Long groupID, Long resourceID,
                                            String privilege);

  //查找某个资源授权的所有分组
  public List findGroupsOfResource(Long resourceID);
}
