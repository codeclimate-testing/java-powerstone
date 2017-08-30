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

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang.builder.HashCodeBuilder;
import java.util.Iterator;

/**
 * @hibernate.class table="WF_BISINESS_TYPE"
 * @hibernate.query name="AllBusinessTypes" query="from BusinessType bt order by bt.typeID asc"
 * <p>Title: PowerStone</p>
 */

public class BusinessType
    extends BaseObject {
  public static final String OTHER_BUSINESS_TYPE_NAME = "其它";
  public static final Long OTHER_BUSINESS_TYPE_ID = new Long( -1);
  private Long typeID = new Long( -1);
  private String typeName;
  private List workflowMetas = new ArrayList();
  private Integer tasksNum;

  /**
   * @hibernate.id column="PK_TYPE_ID"
   * 		   unsaved-value="-1"
   *               generator-class="native"
   * @return Long
   */
  public Long getTypeID() {
    return typeID;
  }

  public void setTypeID(Long typeID) {
    this.typeID = typeID;
  }

  /**
   * @hibernate.property
   * 		column="VC_TYPE_NAME"
   * 		length="255"
   * 		type="string"
   *            not-null="true"
   *            unique="true"
   * @return String
   */
  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  /**
   * @hibernate.bag name="workflowMetas"
   * cascade="save-update"
   * lazy="true"
   * inverse="true"
   * @hibernate.collection-key
   * column="FK_BUSINESS_TYPE_ID"
   * @hibernate.collection-one-to-many
   * class="org.powerstone.workflow.model.WorkflowMeta"
   * @return List
   */
  public List getWorkflowMetas() {
    return workflowMetas;
  }

  public void setWorkflowMetas(List workflowMetas) {
    this.workflowMetas = workflowMetas;
  }

  public int getFlowMetasNum() {
    return this.workflowMetas.size();
  }

  public void addWorkflowMeta(WorkflowMeta wm) {
    workflowMetas.add(wm);
    wm.setBusinessType(this);
  }

  public void removeWorkflowMeta(WorkflowMeta fm) {
    this.getWorkflowMetas().remove(fm);
    fm.setBusinessType(null);
  }

  public void removeAllWorkflowMetas() {
    if (getWorkflowMetas().size() > 0) {
      for (Iterator it = getWorkflowMetas().iterator(); it.hasNext(); ) {
        WorkflowMeta wm = (WorkflowMeta) it.next();
        wm.setBusinessType(null);
      }
      getWorkflowMetas().clear();
    }
  }

  public Integer getTasksNum() {
    return tasksNum;
  }

  public void setTasksNum(Integer tasksNum) {
    this.tasksNum = tasksNum;
  }

  public void otherType() {
    this.setTypeID(this.OTHER_BUSINESS_TYPE_ID);
    this.setTypeName(this.OTHER_BUSINESS_TYPE_NAME);
  }

//------------------------------------------------------------------------------
  public boolean equals(Object object) {
    if (! (object instanceof BusinessType)) {
      return false;
    }
    BusinessType bt = (BusinessType) object;
    return new EqualsBuilder()
        .append(this.getTypeID().toString(), bt.getTypeID().toString())
        .append(this.getTypeName(), bt.getTypeName()).isEquals();
  }

  public int hashCode() {
    //随机选择两个奇数，每个类不同
    return new HashCodeBuilder(1056335803, 107569255).append(
        this.getTypeID().toString())
        .append(this.getTypeName()).toHashCode();
  }

  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("typeID", this.getTypeID().toString()).append("typeName",
        this.getTypeName()).toString();
  }

}
