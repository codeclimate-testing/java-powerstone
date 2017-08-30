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
/**
 * @hibernate.class table="WF_FLOW_META_FILE_STORE"
 * <p>Title: PowerStone</p>
 */
public class FlowMetaFileStore {
  private Long flowFileID;
  private java.sql.Blob previewImage;
  private java.sql.Blob workflowFile;
  /**
   * @hibernate.id column="PK_FLOW_FILE_ID"
   * 		   unsaved-value="-1"
   *               generator-class="assigned"
   * @return Long
   */
  public Long getFlowFileID() {
    return flowFileID;
  }
  public void setFlowFileID(Long flowFileID) {
    this.flowFileID = flowFileID;
  }

  /**
   * @hibernate.property
   * 		column="VC_PREVIEW_IMAGE"
   * 		length="16"
   * 		type="blob"
   * @return Blob
   */
  public java.sql.Blob getPreviewImage() {
    return previewImage;
  }
  public void setPreviewImage(java.sql.Blob previewImage) {
    this.previewImage = previewImage;
  }
  /**
   * @hibernate.property
   * 		column="VC_WORKFLOW_FILE"
   * 		length="16"
   * 		type="blob"
   *            not-null="true"
   * @return Blob
   */
  public java.sql.Blob getWorkflowFile() {
    return workflowFile;
  }
  public void setWorkflowFile(java.sql.Blob workflowFile) {
    this.workflowFile = workflowFile;
  }

}
