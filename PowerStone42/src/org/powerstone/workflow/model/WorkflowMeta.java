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
import org.powerstone.workflow.util.FlowModelDAO;
import org.powerstone.workflow.exception.FlowMetaException;
import org.jdom.input.SAXBuilder;
import org.jdom.Document;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.HashMap;
import java.util.Collection;
import org.powerstone.workflow.exception.ExceptionMessage;

/**
 * @hibernate.class table="WF_META"
 * @hibernate.query name="AllWorkflowMetas" query="from WorkflowMeta wm order by wm.flowMetaID asc"
 * @hibernate.query name="WorkflowMetaByProcess" query="from WorkflowMeta wm WHERE wm.flowProcessID = ?"
 * @hibernate.query name="WorkflowMetasNoBusinessType" query="from WorkflowMeta wm WHERE wm.businessType is null"
 * 工作流元数据——包含多个工作流文件版本，其中有一个当前版本
 * <p>Title: PowerStone</p>
 */

public class WorkflowMeta
    extends BaseObject {
  private Long flowMetaID = new Long( -1);
  private FlowMetaFile flowFileInUse;
  private List flowFileVersions = new ArrayList();
  private String flowProcessID;
  private BusinessType businessType;
  private List flowDeploies = new ArrayList();
  private FlowModelDAO flowModelDAO;
  private static Log log = LogFactory.getLog(WorkflowMeta.class);

  /**
   * @hibernate.id column="PK_FLOW_META_ID"
   * 		   unsaved-value="-1"
   *               generator-class="native"
   * @return Long
   */
  public Long getFlowMetaID() {
    return flowMetaID;
  }

  public void setFlowMetaID(Long flowMetaID) {
    this.flowMetaID = flowMetaID;
  }

  /**
   * @hibernate.many-to-one
   * column="FK_FLOW_FILE_IN_USE"
   * class="org.powerstone.workflow.model.FlowMetaFile"
   * unique="true"
   * @return FlowMetaFile
   */
  public FlowMetaFile getFlowFileInUse() {
    return flowFileInUse;
  }

  public void setFlowFileInUse(FlowMetaFile flowFileInUse) {
    this.flowFileInUse = flowFileInUse;
  }

  /**
   * @hibernate.bag name="flowFileVersions"
   * cascade="all-delete-orphan"
   * lazy="true"
   * inverse="true"
   * @hibernate.collection-key
   * column="FK_WORKFLOW_META_ID"
   * @hibernate.collection-one-to-many
   * class="org.powerstone.workflow.model.FlowMetaFile"
   * @return List
   */
  public java.util.List getFlowFileVersions() {
    return flowFileVersions;
  }

  public void setFlowFileVersions(java.util.List flowFileVersions) {
    this.flowFileVersions = flowFileVersions;
  }

  public int getFileVersionNum() {
    return this.flowFileVersions.size();
  }

  /**
   * @hibernate.bag name="flowDeploies"
   * cascade="all-delete-orphan"
   * lazy="true"
   * inverse="true"
   * @hibernate.collection-key
   * column="FK_FLOW_META_ID"
   * @hibernate.collection-one-to-many
   * class="org.powerstone.workflow.model.FlowDeploy"
   * @return List
   */
  public java.util.List getFlowDeploies() {
    return flowDeploies;
  }

  public void setFlowDeploies(java.util.List flowDeploies) {
    this.flowDeploies = flowDeploies;
  }

  /**
   * @hibernate.property 
   * 		column="VC_FLOW_PROCESS_ID"
   * 		length="255"
   * 		type="string"
   *        not-null="true"
   *        unique="true"
   * @return String
   */
  public String getFlowProcessID() {
    return flowProcessID;
  }

  public void setFlowProcessID(String flowProcessID) {
    this.flowProcessID = flowProcessID;
  }

  /**
   * @hibernate.many-to-one
   * column="FK_BUSINESS_TYPE_ID"
   * class="org.powerstone.workflow.model.BusinessType"
   */
  public BusinessType getBusinessType() {
    return businessType;
  }

  public void setBusinessType(BusinessType bt) {
    this.businessType = bt;
  }

  public void joinBusinessType(BusinessType bt) {
    this.businessType = bt;
    bt.getWorkflowMetas().add(this);
  }

  public void updateBusinessType(BusinessType bt) {
    if (this.getBusinessType() != null) {
      this.getBusinessType().removeWorkflowMeta(this);
    }
    this.setBusinessType(bt);
    bt.addWorkflowMeta(this);
  }

  public void addFlowFileVersion(FlowMetaFile flowMetaFile) {
    if (flowMetaFile.getWorkflowMeta() == null) {
      flowMetaFile.setWorkflowMeta(this);
      this.getFlowFileVersions().add(flowMetaFile);
    }
  }

  public void removeFlowFileVersion(FlowMetaFile flowMetaFile) {
    flowMetaFile.setWorkflowMeta(null);
    this.getFlowFileVersions().remove(flowMetaFile);
  }

  public void addFlowDeploy(FlowDeploy flowDeploy) {
    flowDeploy.setWorkflowMeta(this);
    this.getFlowDeploies().add(flowDeploy);
  }

  public void removeFlowDeploy(FlowDeploy flowDeploy) {
    flowDeploy.setWorkflowMeta(null);
    this.getFlowDeploies().remove(flowDeploy);
  }

  public FlowModelDAO getFlowModelDAO() {
    if (flowModelDAO == null) {
      if (this.getFlowFileInUse().getWorkflowFileInput() == null) {
        throw new FlowMetaException(ExceptionMessage.ERROR_FLOWMETAFILE_NULL);
      }

      SAXBuilder saxBuilder = new SAXBuilder(false);
      Document doc = null;
      try {
        doc = saxBuilder.build(this.getFlowFileInUse().getWorkflowFileInput());
      }
      catch (Exception ex) {
        log.warn("!!!!!!!!!!!!!!!!!!!!" + ex.getMessage());
        ex.printStackTrace();
        throw new FlowMetaException(ExceptionMessage.
                                    ERROR_FLOWMETAFILE_DIGESTER);
      }
      flowModelDAO = new FlowModelDAO(doc);
    }
    return flowModelDAO;
  }

  public String[] getAllNodeIDs() {
    return getFlowModelDAO().getAllNodeIDs(getFlowProcessID());
  }

  public Collection getAllActivityNodesList() {
    String[] nodeIDs = getFlowModelDAO().getAllNodeIDs(getFlowProcessID());
    if (nodeIDs != null && nodeIDs.length > 0) {
      ArrayList list = new ArrayList();
      for (int i = 0; i < nodeIDs.length; i++) {
        WorkflowNode theNode = findWorkflowNodeByID(nodeIDs[i]);
        if (!theNode.isRouteNode()) {
          list.add(theNode);
        }
      }
      return list;
    }
    else {
      return null;
    }
  }

  public WorkflowNode findWorkflowNodeByID(String nodeID) {
    return getFlowModelDAO().findWorkflowNodeByID(getFlowProcessID(),
                                                  nodeID);
  }

  public HashMap getDataFields() {
    return getFlowModelDAO().getDataFields(getFlowProcessID());
  }

  public String[] getEndNodeIDs() {
    return getFlowModelDAO().getEndNodeIDs(getFlowProcessID());
  }

  public String getStartNodeID() {
    return getFlowModelDAO().getStartNodeID(getFlowProcessID());
  }

  public String[] getFlowVariablesToPreview() {
    return getFlowModelDAO().getFlowVariablesToPreview(getFlowProcessID());
  }

  public FlowProcTransition getTransitionByID(String transitionID) {
    return getFlowModelDAO().getTransitionByID(getFlowProcessID(),
                                               transitionID);
  }

  public String[] getTransitionIDsFrom(String fromNodeID) {
    return getFlowModelDAO().getTransitionIDsFrom(getFlowProcessID(),
                                                  fromNodeID);
  }

  public String[] getTransitionIDsTo(String toNodeID) {
    return getFlowModelDAO().getTransitionIDsTo(getFlowProcessID(),
                                                toNodeID);
  }

  public HashMap getTypeDeclarations() {
    return getFlowModelDAO().getTypeDeclarations();
  }
//------------------------------------------------------------------------------
  public boolean equals(Object object) {
    if (! (object instanceof WorkflowMeta)) {
      return false;
    }
    WorkflowMeta wm = (WorkflowMeta) object;
    return new EqualsBuilder().
        append(this.getFlowMetaID().toString(), wm.getFlowMetaID().toString())
        .append(this.getFlowProcessID(), wm.getFlowProcessID()).isEquals();
  }

  public int hashCode() {
    //随机选择两个奇数，每个类不同
    return new HashCodeBuilder(1956335803, 197569255)
        .append(this.getFlowMetaID().toString())
        .append(this.getFlowProcessID())
        .toHashCode();
  }

  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("flowMetaID", this.getFlowMetaID().toString())
        .append("flowProcessID", this.getFlowProcessID())
        .toString();
  }

}
