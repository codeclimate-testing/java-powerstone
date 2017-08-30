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
package org.powerstone.ca.model;

import java.util.List;
import java.util.ArrayList;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.Iterator;
import org.powerstone.util.TreeNode;

/**
 * @hibernate.class table="CA_GROUP"
 * @hibernate.query name="AllGroups" query="select g from Group as g order by g.groupID"

 * @hibernate.query name="GroupMembersByPage"
 *  query="select user from User as user left join user.groups as groups
 *  where groups.groupID = ?"

 * @hibernate.query name="GroupRightsByCombineKeys"
 *  query="select gr from GroupRight as gr where gr.group.groupID = ?
 *  and gr.resource.id = ? and gr.privilege = ?"

 * @hibernate.query name="GroupsOfResource"
 *  query="select g from Group as g left join g.rights as gr where gr.resource.id = ?"

 * <p>Title: PowerStone</p>
 */
public class Group
    extends BaseObject
    implements TreeNode {
  private Long groupID = new Long( -1);
  private String groupName;
  private String memo;
  private Group parentGroup;
  private List users = new ArrayList();
  private List rights = new ArrayList();
  private List childGroups = new ArrayList();
  private static Log log = LogFactory.getLog(Group.class);

  /**
   * @hibernate.id column="PK_ID"
   * 		   unsaved-value="-1"
   *               generator-class="native"
   * @return Long
   */
  public Long getGroupID() {
    return groupID;
  }

  /**
   * @hibernate.property
   * 		column="VC_GROUP_NAME"
   * 		length="255"
   * 		type="string"
   *            not-null="true"
   * @return String
   */
  public String getGroupName() {
    return groupName;
  }

  /**
   * @hibernate.property
   * 		column="VC_GROUP_MEMO"
   * 		length="255"
   * 		type="string"
   * @return String
   */
  public String getMemo() {
    return memo;
  }

  /**
   * @hibernate.many-to-one
   * column="FK_PARENT_GROUP_ID"
   * class="org.powerstone.ca.model.Group"
   * @return Group
   */
  public Group getParentGroup() {
    return parentGroup;
  }

  public Long getParentGroupID() {
    if (this.getGroupID().longValue() != -1) {
      return getParentGroup().getGroupID();
    }
    else {
      return null;
    }
  }

  /**
   * @hibernate.bag name="users" table="CA_JOIN_USER_GROUP" cascade="save-update" lazy="true"
   * @hibernate.collection-key column="FK_GROUP_ID"
   * @hibernate.collection-many-to-many class="org.powerstone.ca.model.User"
   *  column="FK_USER_ID"
   * @return List
   */
  public List getUsers() {
    return users;
  }

  /**
   * @hibernate.bag name="rights" cascade="all-delete-orphan" lazy="true" inverse="true"
   * @hibernate.collection-key column="FK_GROUP_ID"
   * @hibernate.collection-one-to-many class="org.powerstone.ca.model.GroupRight"
   * @return List
   */
  public List getRights() {
    return rights;
  }

  /**
   * @hibernate.bag name="childGroups" cascade="all" lazy="true" inverse="true"
   * @hibernate.collection-key column="FK_PARENT_GROUP_ID"
   * @hibernate.collection-one-to-many class="org.powerstone.ca.model.Group"
   * @return List
   */
  public List getChildGroups() {
    return childGroups;
  }

  public void addChildGroup(Group child) {
    if (child != null) {
      child.setParentGroup(this);
      getChildGroups().add(child);
    }
    else {
      log.warn("group{" + this +"} is adding a null child group");
    }
  }

  /**
   * clear relative data,to prepare to be remove
   */
  public void prepareToBeRemove() {
    if (this.getParentGroup() != null) {
      this.getParentGroup().getChildGroups().remove(this);
    }
    for (Iterator it = this.getUsers().iterator(); it.hasNext(); ) {
      User theUser = (User) it.next();
      theUser.getGroups().remove(this);
    }
    this.setParentGroup(null);
  }

  public void setChildGroups(List childGroups) {
    this.childGroups = childGroups;
  }

  public void setGroupID(Long groupID) {
    this.groupID = groupID;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  public void setParentGroup(Group parentGroup) {
    this.parentGroup = parentGroup;
  }

  public void setRights(List rights) {
    this.rights = rights;
  }

  public void setUsers(List users) {
    this.users = users;
  }

//------------------------------------------------------------------------------
  public boolean equals(Object object) {
    if (! (object instanceof Group)) {
      return false;
    }
    Group group = (Group) object;
    return new EqualsBuilder()
        .append(this.getGroupID().toString(), group.getGroupID().toString())
        .append(this.getGroupName(), group.getGroupName())
        .append(this.getParentGroup(), group.getParentGroup())
        .append(this.getMemo(), group.getMemo()).isEquals();
  }

  public int hashCode() {
    //随机选择两个奇数，每个类不同
    return new HashCodeBuilder(1167893, 1167895).append(
        this.getGroupID().toString())
        .append(this.getGroupName())
        .append(this.getMemo()).toHashCode();
  }

  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("groupID", this.getGroupID().toString())
        .append("groupName", this.getGroupName())
        .append("memo", this.getMemo()).toString();
  }

//------------------------------------------------------------------------------
  public String getNodeID() {
    return this.getGroupID().toString();
  }

  public String getNodeName() {
    return this.getGroupName();
  }

  public String getParentID() {
    if (getParentGroup() != null) {
      return this.getParentGroup().getNodeID();
    }
    else {
      return null;
    }
  }
}
