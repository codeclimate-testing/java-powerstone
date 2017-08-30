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
 * @hibernate.class table="WF_DRIVER_IN_PARAM"
 * <p>Title: PowerStone</p>
 */

public class WFDriverInputParam
    extends BaseObject {
  private Long driverInputParamID = new Long( -1);
  private String paramName;
  private WorkflowDriver workflowDriver;
  private String paramAlias;

  /**
   * @hibernate.id column="PK_DRIVER_INPUT_PARAM_ID"
   * 		   unsaved-value="-1"
   *               generator-class="native"
   * @return Long
   */
  public Long getDriverInputParamID() {
    return driverInputParamID;
  }

  public void setDriverInputParamID(Long driverInputParamID) {
    this.driverInputParamID = driverInputParamID;
  }

  /**
   * @hibernate.property
   * 		column="VC_PARAM_NAME"
   * 		length="255"
   * 		type="string"
   *            not-null="true"
   * @return String
   */
  public String getParamName() {
    return paramName;
  }

  public void setParamName(String paramName) {
    this.paramName = paramName;
  }

  /**
   * @hibernate.many-to-one
   * column="FK_FLOW_DRIVER_ID"
   * class="org.powerstone.workflow.model.WorkflowDriver"
   * @return WorkflowDriver
   */
  public WorkflowDriver getWorkflowDriver() {
    return workflowDriver;
  }

  public void setWorkflowDriver(WorkflowDriver workflowDriver) {
    this.workflowDriver = workflowDriver;
  }

  /**参数的中文名
   * @hibernate.property
   * 		column="VC_PARAM_ALIAS"
   * 		length="255"
   * 		type="string"
   *            not-null="true"
   * @return String
   */
  public String getParamAlias() {
    return paramAlias;
  }

  public void setParamAlias(String paramAlias) {
    this.paramAlias = paramAlias;
  }

//------------------------------------------------------------------------------
  public boolean equals(Object object) {
    if (! (object instanceof WFDriverInputParam)) {
      return false;
    }
    WFDriverInputParam fdp = (WFDriverInputParam) object;
    return new EqualsBuilder().
        append(this.getDriverInputParamID().toString(),fdp.getDriverInputParamID().toString())
        .append(this.getParamName(), fdp.getParamName())
        .append(this.getParamAlias(), fdp.getParamAlias())
        .isEquals();
  }

  public int hashCode() {//随机选择两个奇数，每个类不同
    return new HashCodeBuilder(1556335803, 157569255)
        .append(this.getDriverInputParamID().toString())
        .append(this.getParamName())
        .append(this.getParamAlias())
        .toHashCode();
  }

  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("driverInputParamID", this.getDriverInputParamID().toString())
        .append("paramName", this.getParamName())
        .append("paramAlias", this.getParamAlias())
        .toString();
  }

}
