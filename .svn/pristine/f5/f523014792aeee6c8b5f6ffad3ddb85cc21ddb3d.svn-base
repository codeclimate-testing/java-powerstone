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
import org.apache.commons.logging.*;

/**
 * @hibernate.class table="CA_USER"

 * @hibernate.query name="AllUsers" query="select u from User as u order by u.id"

 * @hibernate.query name="UsersByState" query="select u from User as u where u.state = ?"

 * @hibernate.query name="UsersHaveNoGroup"
 *  query="select u from User as u where u.id not in(select g.users.id from Group g)"
 * 怀疑这是hibernate3的bug，如果用where u.groups.size=0，产生的sql有语法错误

 * @hibernate.query name="ByUserName" query="select u from User as u where u.userName = ?"

 * @hibernate.query name="ByEmail" query="select u from User as u where u.email = ?"

 * @hibernate.query name="UsersOfResource"
 *  query="select u from User as u left join u.rights as ur where ur.resource.id = ?"

 * @hibernate.query name="UserRightsByCombineKeys"
 *  query="select ur from UserRight as ur where ur.user.id = ? and ur.resource.id = ? and ur.privilege = ?"

 * <p>Title: PowerStone</p>
 */
public class User
    extends BaseObject {
  public static final String ANONYMOUS_USER_NAME = "Anonymous"; //匿名(未登录)用户的userName
  public static final String ANONYMOUS_USER_ID = "-1"; //匿名(未登录)用户的userID
  private Long id = new Long( -1);
  private String userName;
  private String password;
  private String realName;
  private String sex;
  private String email;
  private List groups = new ArrayList();
  private List roles = new ArrayList();
  private List rights = new ArrayList();
  private List webModules = new ArrayList();
  private String memo;
  private String state = USER_STATE_NORMAL;
  public static final String USER_STATE_NORMAL = "1";
  public static final String USER_STATE_REMOVED = "2";
  public static final String USER_STATE_DISABLED = "3";
  private static Log log = LogFactory.getLog(User.class);
  private String confirmPass;
  private String oldPass;

  /**
   * @hibernate.id column="PK_ID"
   * 		   unsaved-value="-1"
   *               generator-class="native"
   * @return Long
   */
  public Long getId() {
    return id;
  }

  /**
   * @hibernate.property
   * 		column="VC_USER_NAME"
   * 		length="255"
   * 		type="string"
   *            not-null="true"
   *            unique="true"
   * @return String
   */
  public String getUserName() {
    return userName;
  }

  /**
   * @hibernate.property
   * 		column="VC_PASSWORD"
   * 		length="255"
   * 		type="string"
   *            not-null="true"
   * @return String
   */
  public String getPassword() {
    return password;
  }

  public String getConfirmPass() {
    return confirmPass;
  }

  public void setConfirmPass(String confirmPass) {
    this.confirmPass = confirmPass;
  }

  /**
   * @hibernate.property
   * 		column="VC_REAL_NAME"
   * 		length="255"
   * 		type="string"
   *            not-null="true"
   * @return String
   */
  public String getRealName() {
    return realName;
  }

  /**
   * @hibernate.property
   * 		column="VC_SEX"
   * 		length="50"
   * 		type="string"
   * @return String
   */
  public String getSex() {
    return sex;
  }

  /**
   * @hibernate.property
   * 		column="VC_EMAIL"
   * 		length="255"
   * 		type="string"
   *            not-null="true"
   *            unique="true"
   * @return String
   */
  public String getEmail() {
    return email;
  }

  /**
   * @hibernate.property
   * 		column="VC_MEMO"
   * 		length="255"
   * 		type="string"
   * @return String
   */
  public String getMemo() {
    return memo;
  }

  /**
   * @hibernate.property
   * 		column="VC_STATE"
   * 		length="50"
   * 		type="string"
   *            not-null="true"
   * @return String
   */
  public String getState() {
    return state;
  }

  public void remove() {
    this.setState(User.USER_STATE_REMOVED);
  }

  public boolean isRemoved() {
    return (getState() != null && getState().equals(User.USER_STATE_REMOVED));
  }

  /**
   * @hibernate.bag name="groups" table="CA_JOIN_USER_GROUP" cascade="save-update" lazy="true"
   * @hibernate.collection-key column="FK_USER_ID"
   * @hibernate.collection-many-to-many class="org.powerstone.ca.model.Group"
   *  column="FK_GROUP_ID"
   * @return List
   */
  public List getGroups() {
    return groups;
  }

  public boolean isInGroup(Group group) {
    return group != null &&
//        this.getGroups().indexOf(group) >= 0
//        &&
        group.getUsers().indexOf(this) >= 0
        ;
  }

  public void joinGroup(Group group) {
    if (group == null) {
      log.warn("user" + this +"is joining a null group");
      return;
    }
    if (this.isInGroup(group)) {
      log.warn("user{" + this +"}has already been in group{" + group + "}");
      return;
    }
    group.getUsers().add(this);
//    this.getGroups().add(group);//xxx（will result in insert two rows in CA_JOIN_USER_GROUP）xxx
    return;
  }

  public void leaveGroup(Group group) {
    if (group == null) {
      log.warn("user{" + this +"}is leaving a null group");
      return;
    }
    if (!this.isInGroup(group)) {
      log.warn("user{" + this +"}isn't in group{" + group + "}");
      return;
    }

    group.getUsers().remove(this);
    this.getGroups().remove(group);
    return;
  }

//------------------------------------------------------------------------------
  /**
   * @hibernate.bag name="roles" cascade="save-update" lazy="true"
   *  outer-join="auto" table="CA_JOIN_USER_ROLE"
   * @hibernate.collection-key column="FK_USER_ID"
   * @hibernate.collection-many-to-many class="org.powerstone.ca.model.Role" column="FK_ROLE_ID"
   * @return List
   */
  public List getRoles() {
    return roles;
  }

  public void joinRole(Role role) {
    if (role == null) {
      log.warn("user" + this +"is joining a null role");
      return;
    }
    if (this.isInRole(role)) {
      log.warn("user{" + this +"}has already been in role{" + role + "}");
      return;
    }
    role.getUsers().add(this);
    return;
  }

  public void leaveRole(Role role) {
    if (role == null) {
      log.warn("user{" + this +"}is leaving a null role");
      return;
    }
    if (!this.isInRole(role)) {
      log.warn("user{" + this +"}isn't in role{" + role + "}");
      return;
    }
    role.getUsers().remove(this);
    this.getRoles().remove(role);
    return;
  }

  public boolean isInRole(Role role) {
    return role != null && role.getUsers().indexOf(this) >= 0;
  }

  /**
   * @hibernate.bag name="rights" cascade="all-delete-orphan" lazy="true" inverse="true"
   * @hibernate.collection-key column="FK_USER_ID"
   * @hibernate.collection-one-to-many class="org.powerstone.ca.model.UserRight"
   * @return List
   */
  public List getRights() {
    return rights;
  }

  /**
   * @hibernate.bag name="webModules" cascade="all" lazy="true" inverse="true" table="CA_JOIN_USER_WEBMODULE"
   * @hibernate.collection-key column="FK_USER_ID"
   * @hibernate.collection-many-to-many class="org.powerstone.ca.model.WebModule" column="FK_WEB_MODULE_ID"
   * @return List
   */
  public List getWebModules() {
    return webModules;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setGroups(List groups) {
    this.groups = groups;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setRealName(String realName) {
    this.realName = realName;
  }

  public void setRights(List rights) {
    this.rights = rights;
  }

  public void setRoles(List roles) {
    this.roles = roles;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setWebModules(List webModules) {
    this.webModules = webModules;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  public void setState(String state) {
    this.state = state;
  }

//------------------------------------------------------------------------------
  public boolean equals(Object object) {
    if (! (object instanceof User)) {
      return false;
    }
    User user = (User) object;
    return new EqualsBuilder()
        .append(this.getUserName(), user.getUserName())
        .append(this.getEmail(), user.getEmail())
        .append(this.getRealName(), user.getRealName()).isEquals();
  }

  public int hashCode() {
    //随机选择两个奇数，每个类不同
    return new HashCodeBuilder(1667893, 1667895).append(
        this.getId().toString())
        .append(this.getUserName())
        .append(this.getEmail())
        .append(this.getRealName()).toHashCode();
  }

  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", this.getId().toString())
        .append("userName", this.getUserName())
        .append("email", this.getEmail())
        .append("realName", this.getRealName()).toString();
  }


  public void addRight(UserRight userRight) {
    if (userRight != null) {
      userRight.setUser(this);
      getRights().add(userRight);
    }
    else {
      log.warn("tempt to add null right to user" + this);
    }
  }
  public void clearRights() {
    for(Iterator it=getRights().iterator();it.hasNext();){
      UserRight ur=(UserRight)it.next();
      ur.setUser(null);
    }
    getRights().clear();
  }

  public String getOldPass() {
    return oldPass;
  }
  public void setOldPass(String oldPass) {
    this.oldPass = oldPass;
  }
}
