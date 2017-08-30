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
 * @hibernate.class table="WF_FLOW_TASK_REFUSE"
 * <p>Title: PowerStone</p>
 * <p>Description: workflow powered by Spring</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: PowerStone Co., Ltd</p>
 * @author daquan
 * @version 1.0
 */
public class FlowTaskRefuse
    extends BaseObject {
  private Long id = new Long( -1);
  private String refuseFor;
  private String refuseUser;
  private FlowTask flowTask;

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
   * 		column="VC_REFUSE_FOR"
   * 		length="1024"
   * 		type="string"
   *            not-null="true"
   * @return String
   */

  public String getRefuseFor() {
    return refuseFor;
  }

  public void setRefuseFor(String refuseFor) {
    this.refuseFor = refuseFor;
  }

  /**
   * @hibernate.property
   * 		column="VC_REFUSE_USER"
   * 		length="255"
   * 		type="string"
   *            not-null="true"
   * @return String
   */

  public String getRefuseUser() {
    return refuseUser;
  }

  public void setRefuseUser(String refuseUser) {
    this.refuseUser = refuseUser;
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
    if (! (object instanceof FlowTaskRefuse)) {
      return false;
    }
    FlowTaskRefuse ftu = (FlowTaskRefuse) object;
    return new EqualsBuilder()
        .append(this.getRefuseFor(), ftu.getRefuseFor())
        .append(this.getFlowTask(), ftu.getFlowTask())
        .append(this.getRefuseUser(), ftu.getRefuseUser())
        .isEquals();
  }

  public int hashCode() { //随机选择两个奇数，每个类不同
    return new HashCodeBuilder(8835803, 88759255)
        .append(this.getId().toString())
        .append(this.getRefuseFor())
        .append(this.getRefuseUser())
        .toHashCode();
  }

  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("ID", this.getId().toString())
        .append("RefuseFor", this.getRefuseFor())
        .append("RefuseUser", this.getRefuseUser())
        .toString();
  }
}
