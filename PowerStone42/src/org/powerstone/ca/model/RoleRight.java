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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @hibernate.class table="CA_ROLE_RIGHT"
 * <p>Title: PowerStone</p>
 */
public class RoleRight
    extends BaseObject implements GivenRight{
  private Long rightID = new Long( -1);
  private String inheritable;
  private Role role;
  private String privilege;
  private Resource resource;

  /**
   * @hibernate.id column="PK_RIGHT_ID"
   * 		   unsaved-value="-1"
   *               generator-class="native"
   * @return Long
   */
  public Long getRightID() {
    return rightID;
  }

  /**
   * @hibernate.property
   * 		column="VC_PRIVILEGE"
   * 		length="50"
   * 		type="string"
   *            not-null="true"
   * @return String
   */
  public String getPrivilege() {
    return privilege;
  }

  /**
   * @hibernate.property
   * 		column="VC_INHERITABLE"
   * 		length="10"
   * 		type="string"
   * @return String
   */
  public String getInheritable() {
    return inheritable;
  }

  /**
   * @hibernate.many-to-one
   * column="FK_ROLE_ID"
   * class="org.powerstone.ca.model.Role"
   * @return Role
   */
  public Role getRole() {
    return role;
  }

  /**
   * @hibernate.many-to-one
   * column="FK_RESOURCE_ID"
   * class="org.powerstone.ca.model.Resource"
   * @return Resource
   */
  public Resource getResource() {
    return resource;
  }

  public void setInheritable(String inheritable) {
    this.inheritable = inheritable;
  }

  public void setPrivilege(String privilege) {
    this.privilege = privilege;
  }

  public void setResource(Resource resource) {
    this.resource = resource;
  }

  public void setRightID(Long rightID) {
    this.rightID = rightID;
  }

  public void setRole(Role role) {
    this.role = role;
  }
//------------------------------------------------------------------------------
  public boolean equals(Object object) {
    if (! (object instanceof RoleRight)) {
      return false;
    }
    RoleRight roleRight = (RoleRight) object;
    return new EqualsBuilder()
        .append(this.getResource(), roleRight.getResource())
        .append(this.getRole(), roleRight.getRole())
        .append(this.getPrivilege(), roleRight.getPrivilege()).isEquals();
  }

  public int hashCode() {
    //随机选择两个奇数，每个类不同
    return new HashCodeBuilder(1567893, 1567895).append(
        this.getResource().toString())
        .append(this.getPrivilege())
        .append(this.getRightID()).toHashCode();
  }

  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("rightID", this.getRightID().toString())
        .append("privilege", this.getPrivilege())
        .append("role", this.getRole()).toString();
  }
}
