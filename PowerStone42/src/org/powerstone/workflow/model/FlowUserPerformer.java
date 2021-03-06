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
 * @hibernate.class table="WF_FLOW_USER_PERFORMER"
 * <p>Title: PowerStone</p>
 */
public class FlowUserPerformer
    extends BaseObject {
  private FlowNodeBinding flowNodeBinding;
  private Long userID;
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
   * @hibernate.many-to-one
   * column="FK_FLOW_NODE_BINDING_ID"
   * class="org.powerstone.workflow.model.FlowNodeBinding"
   * @return FlowNodeBinding
   */
  public FlowNodeBinding getFlowNodeBinding() {
    return flowNodeBinding;
  }

  public void setFlowNodeBinding(FlowNodeBinding flowNodeBinding) {
    this.flowNodeBinding = flowNodeBinding;
  }

  /**
   * @hibernate.property
   * 		column="VC_PERFORMER_USER_ID"
   * 		type="long"
   *            not-null="true"
   * @return String
   */
  public Long getUserID() {
    return userID;
  }

  public void setUserID(Long userID) {
    this.userID = userID;
  }

  public boolean equals(Object object) {
    if (! (object instanceof FlowUserPerformer)) {
      return false;
    }
    FlowUserPerformer fup = (FlowUserPerformer) object;
    return new EqualsBuilder().
        append(this.getFlowNodeBinding(), fup.getFlowNodeBinding())
        .append(this.getUserID(), fup.getUserID()).isEquals();
  }

  public int hashCode() {
    //随机选择两个奇数，每个类不同
    return new HashCodeBuilder(87635803, 87669255).append(
        this.getFlowNodeBinding().toString()).append(this.getUserID())
        .toHashCode();
  }

  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", this.getId().toString())
        .append("NodeBinding", this.getFlowNodeBinding())
        .append("UserID", this.getUserID())
        .toString();
  }

}
