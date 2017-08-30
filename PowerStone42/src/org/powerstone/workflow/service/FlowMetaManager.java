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
package org.powerstone.workflow.service;

import java.io.*;
import java.util.*;

import org.powerstone.workflow.dao.*;
import org.powerstone.workflow.model.*;

public interface FlowMetaManager {
  public void setFlowMetaDAO(WorkflowMetaDAO dao);

  public void setFlowMetaFileDAO(FlowMetaFileDAO dao);

  public void setFlowMetaFileStoreDAO(FlowMetaFileDAO dao);

  public void setBusinessTypeDAO(BusinessTypeDAO dao);

  public List getAllWorkflowMetas();

  public List getWorkflowMetasNoBusinessType();

  public WorkflowMeta getWorkflowMeta(String flowMetaID);

  public WorkflowMeta getWorkflowMetaWithFile(String flowMetaID);

  public WorkflowMeta saveWorkflowMeta(WorkflowMeta workflowMeta);

  public void removeWorkflowMeta(String flowMetaID);

  public WorkflowMeta getWorkflowMetaByProcess(String flowProcessID);

  public void updateFlowFileInUse(String flowMetaID, String flowFileID);

  public void updateBusinessType(String flowMetaID, String businessTypeID);

  public FlowMetaFile getFlowMetaFile(String flowFileID);

  public FlowMetaFile saveFlowMetaFile(FlowMetaFile flowMetaFile);

  public WorkflowMeta uploadFlowMetaFile(InputStream flowInput,
                                         InputStream flowInputTest,
                                         InputStream previewInput,
                                         Long flowSize,
                                         Long previewSize);

  public void removeFlowMetaFile(String flowFileID);

  public void addFlowFileVersion(String flowMetaID, String flowFileID);

  public void addFlowDeploy(String flowMetaID, FlowDeploy flowDeploy);

  public void removeFlowFileVersion(String flowMetaID, String flowFileID);

  /**
   * updatePreviewImage
   *
   * @param flowMetaID String
   * @param fileInputStream InputStream
   */
  public void updatePreviewImage(String flowMetaID,
                                 InputStream fileInputStream,
                                 Long previewSize);

}
