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

import java.io.InputStream;

/**工作流模型——存储流程定义的xpdl文档及基本属性
 * @hibernate.class table="WF_FLOW_META_FILE"
 * @hibernate.query name="AllFlowMetaFiles" query="from FlowMetaFile fmf order by fmf.flowFileID asc"
 * <p>Title: PowerStone</p>
 */

public class FlowMetaFile
    extends BaseObject {
  private Long flowFileID = new Long( -1);
  private WorkflowMeta workflowMeta;

  private InputStream workflowFileInput;
  private InputStream previewImageInput;
  private String flowMetaName;
  private Long workflowFileSize;
  private Long previewImageSize;
  private String flowProcessID;
  private String accessLevel;
  private String createdTime;

  /**
   * @hibernate.id column="PK_FLOW_FILE_ID"
   * 		   unsaved-value="-1"
   *               generator-class="native"
   * @return Long
   */
  public Long getFlowFileID() {
    return flowFileID;
  }

  public void setFlowFileID(Long flowFileID) {
    this.flowFileID = flowFileID;
  }

  /**
   * @hibernate.many-to-one
   * column="FK_WORKFLOW_META_ID"
   * class="org.powerstone.workflow.model.WorkflowMeta"
   * @return WorkflowMeta
   */
  public WorkflowMeta getWorkflowMeta() {
    return workflowMeta;
  }

  public void setWorkflowMeta(WorkflowMeta workflowMeta) {
    this.workflowMeta = workflowMeta;
  }

  /**
   * @hibernate.property
   * 		column="VC_FLOW_META_NAME"
   * 		length="255"
   * 		type="string"
   *            not-null="true"
   * @return String
   */
  public String getFlowMetaName() {
    return flowMetaName;
  }

  public void setFlowMetaName(String flowMetaName) {
    this.flowMetaName = flowMetaName;
  }

  /**
   * @hibernate.property
   * 		column="VC_FLOW_CREATED_TIME"
   * 		length="255"
   * 		type="string"
   *            not-null="false"
   * @return String
   */
  public String getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(String createdTime) {
    this.createdTime = createdTime;
  }

  public InputStream getPreviewImageInput() {
    return previewImageInput;
  }

  public void setPreviewImageInput(InputStream previewImageInput) {
    this.previewImageInput = previewImageInput;
  }

  public void setWorkflowFileInput(InputStream workflowFileInput) {
    this.workflowFileInput = workflowFileInput;
  }

  public InputStream getWorkflowFileInput() {
    return workflowFileInput;
  }

  public Long getWorkflowFileSize() {
    return workflowFileSize;
  }

  public void setWorkflowFileSize(Long workflowFileSize) {
    this.workflowFileSize = workflowFileSize;
  }

  public Long getPreviewImageSize() {
    return previewImageSize;
  }

  public void setPreviewImageSize(Long previewImageSize) {
    this.previewImageSize = previewImageSize;
  }

  public String getFlowProcessID() {
    return flowProcessID;
  }

  public void setFlowProcessID(String flowProcessID) {
    this.flowProcessID = flowProcessID;
  }

  public String getAccessLevel() {
    return accessLevel;
  }

  public void setAccessLevel(String accessLevel) {
    this.accessLevel = accessLevel;
  }

//------------------------------------------------------------------------------
  public boolean equals(Object object) {
    if (! (object instanceof FlowMetaFile)) {
      return false;
    }
    FlowMetaFile fmf = (FlowMetaFile) object;
    return new EqualsBuilder().append(this.getFlowFileID(), fmf.getFlowFileID())
        .append(this.getFlowMetaName(), fmf.getFlowMetaName()).isEquals();
  }

  public int hashCode() {
    //随机选择两个奇数，每个类不同
    return new HashCodeBuilder(1256335803, 127569255).append(
        this.getFlowFileID()).append(this.getFlowMetaName()).toHashCode();
  }

  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("FlowFileID", this.getFlowFileID()).append("FlowMetaName",
        this.getFlowMetaName()).toString();
  }

}
