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

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * @hibernate.class table="WF_DRIVER_OUT_PARAM"
 * <p>Title: PowerStone</p>
 */

public class WFDriverOutputParam
    extends BaseObject {
  private Long driverOutputParamID = new Long( -1);
  private String paramName;
  private String paramAlias;
  private WorkflowDriver workflowDriver;
  private List driverOutputParamEnumes = new ArrayList();

  /**
   * @hibernate.id column="PK_DRIVER_OUTPUT_PARAM_ID"
   * 		   unsaved-value="-1"
   *               generator-class="native"
   * @return Long
   */
  public Long getDriverOutputParamID() {
    return driverOutputParamID;
  }

  public void setDriverOutputParamID(Long driverOutputParamID) {
    this.driverOutputParamID = driverOutputParamID;
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

  /**
   * @hibernate.bag name="driverOutputParamEnumes"
   * cascade="all-delete-orphan"
   * lazy="true"
   * inverse="true"
   * @hibernate.collection-key
   * column="FK_DRIVER_OUTPUT_PARAM_ID"
   * @hibernate.collection-one-to-many
   * class="org.powerstone.workflow.model.WFDriverOutputParamEnume"
   * @return List
   */
  public List getDriverOutputParamEnumes() {
    return driverOutputParamEnumes;
  }

  public void setDriverOutputParamEnumes(List driverOutputParamEnumes) {
    this.driverOutputParamEnumes = driverOutputParamEnumes;
  }

  public void addParamEnume(WFDriverOutputParamEnume wdOutParamEnume) {
    this.getDriverOutputParamEnumes().add(wdOutParamEnume);
    wdOutParamEnume.setWfDriverOutputParam(this);
  }

  public void removeParamEnume(WFDriverOutputParamEnume wdOutParamEnume) {
    this.getDriverOutputParamEnumes().remove(wdOutParamEnume);
    wdOutParamEnume.setWfDriverOutputParam(null);
  }

  public void removeAllWorkflowMetas() {
    if (getDriverOutputParamEnumes().size() > 0) {
      for (Iterator it = getDriverOutputParamEnumes().iterator(); it.hasNext(); ) {
        WFDriverOutputParamEnume wdOutParamEnume =
            (WFDriverOutputParamEnume) it.next();
        wdOutParamEnume.setWfDriverOutputParam(null);
      }
      getDriverOutputParamEnumes().clear();
    }
  }

//------------------------------------------------------------------------------
  public boolean equals(Object object) {
    if (! (object instanceof WFDriverOutputParam)) {
      return false;
    }
    WFDriverOutputParam fdp = (WFDriverOutputParam) object;
    return new EqualsBuilder().
        append(this.getDriverOutputParamID().toString(),fdp.getDriverOutputParamID().toString())
        .append(this.getParamName(), fdp.getParamName())
        .append(this.getParamAlias(), fdp.getParamAlias())
        .isEquals();
  }

  public int hashCode() {
    //随机选择两个奇数，每个类不同
    return new HashCodeBuilder(1656335803, 167569255)
        .append(this.getDriverOutputParamID().toString())
        .append(this.getParamName())
        .append(this.getParamAlias())
        .toHashCode();
  }

  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("driverOutputParamID", this.getDriverOutputParamID().toString())
        .append("paramName", this.getParamName())
        .append("paramAlias", this.getParamAlias())
        .toString();
  }

}
