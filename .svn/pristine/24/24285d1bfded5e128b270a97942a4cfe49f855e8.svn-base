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

import org.powerstone.*;
import java.util.*;
import org.powerstone.ca.model.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GroupDAOTest
    extends AbstractSpringTestCase {
  private static Log log = LogFactory.getLog(GroupDAOTest.class);
  private GroupDAO groupDAO = null;
  Group _group = null;

  protected void onSetUpInTransaction() throws Exception {
    _group = new Group();
    _group.setGroupName("setGroupName");
    _group.setMemo("setMemo");
    groupDAO.saveGroup(_group);
  }

  public void testFindAllGroups() {
    List actualReturn = groupDAO.findAllGroups();
    assertEquals("number of all groups", 1, actualReturn.size());
    assertEquals("the only group", _group, actualReturn.get(0));
  }

  public void testFindByPrimaryKey() {
    Group actualReturn = groupDAO.findByPrimaryKey(_group.getGroupID());
    assertEquals("the group", _group, actualReturn);
  }

  public void testFindGroupMembersByPage() {
    int pageNum = 1;
    int pageSize = 20;
    List actualReturn = groupDAO.findGroupMembersByPage(_group.getGroupID(),
        pageNum, pageSize);
    assertEquals("a page of group members", 0, actualReturn.size());
  }

  public void testFindGroupsOfResource() {
    List actualReturn = groupDAO.findGroupsOfResource(new Long(1));
    assertEquals("groups having relevant rights of resource", 0,
                 actualReturn.size());
  }

  public void testFindRightsByCombineKeys() {
    String privilege = "";
    GroupRight actualReturn =
        groupDAO.findRightsByCombineKeys(_group.getGroupID(), new Long(1),
                                         privilege);
    assertEquals("RightsByCombineKeys", null, actualReturn);
  }

  public void testRemoveGroup() {
    this.assertEquals("number of groups", 1,
                      jdbcTemplate.queryForInt("select count(*) from CA_GROUP"));
    groupDAO.removeGroup(_group.getGroupID());
    this.assertEquals("number of groups", 0,
                      jdbcTemplate.queryForInt("select count(*) from CA_GROUP"));
  }

  public void testSaveGroup() {
    log.info("before save:" + _group);
    _group.setMemo("_group_memo");
    _group.setGroupName("_group_name");
    groupDAO.saveGroup(_group);
    this.assertEquals("_group memo", "_group_memo",
                      jdbcTemplate.queryForObject(
        "select VC_GROUP_MEMO from CA_GROUP", String.class));
    this.assertEquals("number of groups", "_group_name",
                      jdbcTemplate.queryForObject(
        "select VC_GROUP_NAME from CA_GROUP", String.class));
    log.info("after save:" + _group);
  }

  public void setGroupDAO(GroupDAO groupDAO) {
    this.groupDAO = groupDAO;
  }

}
