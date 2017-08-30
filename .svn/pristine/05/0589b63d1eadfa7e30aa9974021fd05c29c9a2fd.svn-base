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
 * @hibernate.class table="WF_DRIVER_OUT_PARAM_ENUME"
 * <p>Title: PowerStone</p>
 */

public class WFDriverOutputParamEnume
    extends BaseObject {
  private Long driverOutputParamEnumeID = new Long( -1);
  private WFDriverOutputParam wfDriverOutputParam;
  private String driverOutputParamEnumeValue;

  /**
   * @hibernate.id column="PKDRIVEROUTPUTPARAMENUMEID"
   * 		   unsaved-value="-1"
   *               generator-class="native"
   * @return Long
   */
  public Long getDriverOutputParamEnumeID() {
    return driverOutputParamEnumeID;
  }

  public void setDriverOutputParamEnumeID(Long driverOutputParamEnumeID) {
    this.driverOutputParamEnumeID = driverOutputParamEnumeID;
  }

  /**
   * @hibernate.many-to-one
   * column="FK_DRIVER_OUTPUT_PARAM_ID"
   * class="org.powerstone.workflow.model.WFDriverOutputParam"
   * @return WFDriverOutputParam
   */
  public WFDriverOutputParam getWfDriverOutputParam() {
    return wfDriverOutputParam;
  }

  public void setWfDriverOutputParam(WFDriverOutputParam wfDriverOutputParam) {
    this.wfDriverOutputParam = wfDriverOutputParam;
  }

  /**参数的枚举值
   * @hibernate.property
   * 		column="DRIVEROUTPUTPARAMENUMEVALUE"
   * 		length="255"
   * 		type="string"
   *            not-null="true"
   * @return String
   */
  public String getDriverOutputParamEnumeValue() {
    return driverOutputParamEnumeValue;
  }

  public void setDriverOutputParamEnumeValue(String driverOutputParamEnumeValue) {
    this.driverOutputParamEnumeValue = driverOutputParamEnumeValue;
  }

//------------------------------------------------------------------------------
  public boolean equals(Object object) {
    if (! (object instanceof WFDriverOutputParamEnume)) {
      return false;
    }
    WFDriverOutputParamEnume fdp = (WFDriverOutputParamEnume) object;
    return new EqualsBuilder().
        append(this.getDriverOutputParamEnumeID().toString(),
               fdp.getDriverOutputParamEnumeID().toString())
        .append(this.getDriverOutputParamEnumeValue(),
                fdp.getDriverOutputParamEnumeValue())
        .isEquals();
  }

  public int hashCode() {
    //随机选择两个奇数，每个类不同
    return new HashCodeBuilder(1756335803, 177569255)
        .append(this.getDriverOutputParamEnumeID().toString())
        .append(this.getDriverOutputParamEnumeValue())
        .toHashCode();
  }

  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("driverOutputParamEnumeID",
                this.getDriverOutputParamEnumeID().toString())
        .append("driverOutputParamEnumeValue",
                this.getDriverOutputParamEnumeValue())
        .toString();
  }

}
