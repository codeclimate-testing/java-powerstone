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

import java.util.*;

import org.apache.commons.lang.builder.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @hibernate.class table="CA_ROLE"
 * @hibernate.query name="AllRoles" query="select r from Role as r order by r.roleID"

 * @hibernate.query name="RoleMembersByPage"
 *  query="select r.users from Role as r where r.roleID = ?"

 * @hibernate.query name="RoleRightsByCombineKeys"
 *  query="select rr from RoleRight as rr where rr.role.roleID = ?
 *  and rr.resource.id = ? and rr.privilege = ?"

 * @hibernate.query name="RolesOfResource"
 *  query="select r from Role as r left join r.rights as rr where rr.resource.id = ?"
 * <p>Title: PowerStone</p>
 */
public class Role
    extends BaseObject {
  private static final Log log = LogFactory.getLog(Role.class);
  private Long roleID = new Long( -1);
  private String roleName;
  private String memo;
  private List rights = new ArrayList();
  private List users = new ArrayList();

  /**
   * @hibernate.id column="PK_ID"
   * 		   unsaved-value="-1"
   *               generator-class="native"
   * @return Long
   */
  public Long getRoleID() {
    return roleID;
  }

  /**
   * @hibernate.property
   * 		column="VC_ROLE_NAME"
   * 		length="255"
   * 		type="string"
   *            not-null="true"
   * @return String
   */
  public String getRoleName() {
    return roleName;
  }

  /**
   * @hibernate.property
   * 		column="VC_ROLE_MEMO"
   * 		length="255"
   * 		type="string"
   * @return String
   */
  public String getMemo() {
    return memo;
  }

  /**
   * @hibernate.bag name="users" cascade="save-update" lazy="true" table="CA_JOIN_USER_ROLE"
   * @hibernate.collection-key column="FK_ROLE_ID"
   * @hibernate.collection-many-to-many class="org.powerstone.ca.model.User" column="FK_USER_ID"
   * @return List
   */
  public List getUsers() {
    return users;
  }

  /**
   * @hibernate.bag name="rights" cascade="all-delete-orphan" lazy="true" inverse="true"
   * @hibernate.collection-key column="FK_ROLE_ID"
   * @hibernate.collection-one-to-many class="org.powerstone.ca.model.RoleRight"
   * @return List
   */
  public List getRights() {
    return rights;
  }

  public void clearRights() {
    for(Iterator it=getRights().iterator();it.hasNext();){
      RoleRight rr=(RoleRight)it.next();
      rr.setRole(null);
    }
    getRights().clear();
  }

  public void addRight(RoleRight roleRight) {
    if(roleRight!=null){
      roleRight.setRole(this);
      getRights().add(roleRight);
    }else{
      log.warn("tempt to add null right to role"+this);
    }
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  public void setRights(List rights) {
    this.rights = rights;
  }

  public void setRoleID(Long roleID) {
    this.roleID = roleID;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public void setUsers(List users) {
    this.users = users;
  }

//------------------------------------------------------------------------------
  public boolean equals(Object object) {
    if (! (object instanceof Role)) {
      return false;
    }
    Role role = (Role) object;
    return new EqualsBuilder()
        .append(this.getRoleName(), role.getRoleName())
        .append(this.getMemo(), role.getMemo()).isEquals();
  }

  public int hashCode() {
    //随机选择两个奇数，每个类不同
    return new HashCodeBuilder(1467893, 1467895).append(
        this.getRoleID().toString())
        .append(this.getRoleName())
        .append(this.getMemo()).toHashCode();
  }

  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("roleID", this.getRoleID().toString())
        .append("roleName", this.getRoleName())
        .append("memo", this.getMemo()).toString();
  }
}
