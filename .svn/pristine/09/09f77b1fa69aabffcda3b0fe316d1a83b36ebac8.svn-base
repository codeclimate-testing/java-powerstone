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

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @hibernate.class table="WF_NODE_OUT_PARAM_ENUME_BIND"
 * <p>Title: PowerStone</p>
 */

public class FlowNodeOutputParamEnumeBinding
    extends BaseObject {
  private Long paramEnumeBindingID = new Long( -1);
  private FlowNodeOutputParamBinding flowNodeOutputParamBinding;
  private String nodeOutputParamEnume;
  private WFDriverOutputParamEnume wfDriverOutputParamEnume;

  /**
   * @hibernate.id column="PK_PARAM_ENUME_BINDING_ID"
   * 		   unsaved-value="-1"
   *               generator-class="native"
   * @return Long
   */
  public Long getParamEnumeBindingID() {
    return paramEnumeBindingID;
  }

  public void setParamEnumeBindingID(Long paramEnumeBindingID) {
    this.paramEnumeBindingID = paramEnumeBindingID;
  }

  /**
   * @hibernate.many-to-one
   * column="FK_PARAM_BINDING_ID"
   * class="org.powerstone.workflow.model.FlowNodeOutputParamBinding"
   * @return FlowNodeOutputParamBinding
   */
  public FlowNodeOutputParamBinding getFlowNodeOutputParamBinding() {
    return flowNodeOutputParamBinding;
  }

  public void setFlowNodeOutputParamBinding(FlowNodeOutputParamBinding
                                            flowNodeOutputParamBinding) {
    this.flowNodeOutputParamBinding = flowNodeOutputParamBinding;
  }

  /**
   * @hibernate.property
   * 		column="VC_NODE_OUTPUT_PARAM_ENUME_ID"
   * 		length="255"
   * 		type="string"
   *            not-null="true"
   * @return String
   */
  public String getNodeOutputParamEnume() {
    return nodeOutputParamEnume;
  }

  public void setNodeOutputParamEnume(String nodeOutputParamEnume) {
    this.nodeOutputParamEnume = nodeOutputParamEnume;
  }

  /**
   * @hibernate.many-to-one
   * column="FKDRIVEROUTPUTPARAMENUMEID"
   * class="org.powerstone.workflow.model.WFDriverOutputParamEnume"
   * @return WFDriverOutputParamEnume
   */
  public WFDriverOutputParamEnume getWfDriverOutputParamEnume() {
    return wfDriverOutputParamEnume;
  }

  public void setWfDriverOutputParamEnume(WFDriverOutputParamEnume
                                          wfDriverOutputParamEnume) {
    this.wfDriverOutputParamEnume = wfDriverOutputParamEnume;
  }

//------------------------------------------------------------------------------
  public boolean equals(Object object) {
    if (! (object instanceof FlowNodeOutputParamEnumeBinding)) {
      return false;
    }
    FlowNodeOutputParamEnumeBinding fnb =
        (FlowNodeOutputParamEnumeBinding) object;
    return new EqualsBuilder().
        append(this.getParamEnumeBindingID().toString(),fnb.getParamEnumeBindingID().toString())
        .append(this.getNodeOutputParamEnume(), fnb.getNodeOutputParamEnume()).isEquals();
  }

  public int hashCode() {//随机选择两个奇数，每个类不同
    return new HashCodeBuilder(276335803, 277569255)
        .append(this.getParamEnumeBindingID().toString())
        .append(this.getNodeOutputParamEnume())
        .toHashCode();
  }

  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("paramEnumeBindingID", this.getParamEnumeBindingID().toString())
        .append("nodeOutputParamEnume", this.getNodeOutputParamEnume())
        .toString();
  }

}
