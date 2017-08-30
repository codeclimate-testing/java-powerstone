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
package org.powerstone.workflow.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @hibernate.class table="WF_FLOW_TASK_ROLE"
 * <p>Title: PowerStone</p>
 */
public class FlowTaskRole
    extends BaseObject {
  private String roleID;
  private FlowTask flowTask;
  private Long id = new Long( -1);

  /**
   * @hibernate.id column="PK_ID"
   * 		   unsaved-value="-1"
   *               generator-class="native"
   * @return Long
   */
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  /**
   * @hibernate.property
   * 		column="VC_USER_ID"
   * 		length="255"
   * 		type="string"
   *            not-null="true"
   * @return String
   */
  public String getRoleID() {
    return roleID;
  }

  public void setRoleID(String roleID) {
    this.roleID = roleID;
  }

  /**
   * @hibernate.many-to-one
   * column="FK_FLOW_TASK_ID"
   * class="org.powerstone.workflow.model.FlowTask"
   * @return FlowTask
   */
  public FlowTask getFlowTask() {
    return flowTask;
  }

  public void setFlowTask(FlowTask flowTask) {
    this.flowTask = flowTask;
  }

//------------------------------------------------------------------------------
  public boolean equals(Object object) {
    if (! (object instanceof FlowTaskRole)) {
      return false;
    }
    FlowTaskRole ftr = (FlowTaskRole) object;
    return new EqualsBuilder()
        .append(this.getId().toString(), ftr.getId().toString())
        .append(this.getRoleID(), ftr.getRoleID())
        .append(this.getFlowTask(), ftr.getFlowTask())
        .isEquals();
  }

  public int hashCode() {//随机选择两个奇数，每个类不同
    return new HashCodeBuilder(236335803, 237569255)
        .append(this.getId().toString())
        .append(this.getRoleID())
        .toHashCode();
  }

  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("ID", this.getId().toString())
        .append("RoleID", this.getRoleID())
        .toString();
  }

}
