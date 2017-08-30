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
  //按ID查找分组的信息
  public Group findGroup(String groupID);

  public List findAllGroups();

  public List findGroupMembers(String groupID);

  //查找分组的某一页成员
  public List findGroupMembersByPage(String groupID, int pageNum, int pageSize);

  //向分组中添加已存在的用户
  public void addUserToGroup(String userID,String groupID);

  //新建一个
  public Group createGroup(Group group);

  //新建一个子部门
  public Group createSubGroup(String groupID,Group sonGroup);

  //删除一个分组
  public void removeGroup(String groupID);

  //删除分组的一个成员
  public void removeUserFromGroup(String groupID, String userID);

  //更新分组
  public Group updateGroup(Group group);

}
