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
package org.powerstone.workflow.service.impl;

import org.powerstone.workflow.service.FlowMetaManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.powerstone.workflow.dao.WorkflowMetaDAO;
import org.powerstone.workflow.dao.FlowMetaFileDAO;
import java.util.List;
import org.powerstone.workflow.model.WorkflowMeta;
import org.powerstone.workflow.model.FlowMetaFile;
import org.powerstone.workflow.dao.BusinessTypeDAO;
import org.powerstone.workflow.model.BusinessType;

import java.io.InputStream;
import org.jdom.input.SAXBuilder;
import org.jdom.Document;
import org.powerstone.workflow.util.FlowModelDAO;
import java.io.*;
import org.jdom.*;
import org.powerstone.workflow.exception.FlowMetaException;
import org.powerstone.workflow.model.FlowDeploy;
import org.powerstone.workflow.exception.ExceptionMessage;

/**
 * <p>Title: PowerStone</p>
 * <p>Description: workflow powered by Spring</p>
 * @author daquan
 * @version 1.0
 */

public class FlowMetaManagerImpl
    implements FlowMetaManager {
  private static Log log = LogFactory.getLog(FlowMetaManagerImpl.class);
  private WorkflowMetaDAO flowMetaDAO;
  private FlowMetaFileDAO flowMetaFileDAO;
  private FlowMetaFileDAO flowMetaStoreDAO;
  private BusinessTypeDAO btDAO;

  public void setFlowMetaDAO(WorkflowMetaDAO dao) {
    flowMetaDAO = dao;
  }

  public void setFlowMetaFileDAO(FlowMetaFileDAO dao) {
    flowMetaFileDAO = dao;
  }

  public void setFlowMetaFileStoreDAO(FlowMetaFileDAO dao) {
    flowMetaStoreDAO = dao;
  }

  public void setBusinessTypeDAO(BusinessTypeDAO dao) {
    this.btDAO = dao;
  }

  public List getAllWorkflowMetas() {
    List result = flowMetaDAO.getAllWorkflowMetas();
    if (log.isDebugEnabled()) {
      log.debug("查找到WorkflowMeta[" + new Integer(result.size()) + "]个");
    }
    return result;
  }

  public WorkflowMeta getWorkflowMeta(String flowMetaID) {
    return flowMetaDAO.getWorkflowMeta(new Long(flowMetaID));
  }

  public WorkflowMeta getWorkflowMetaWithFile(String flowMetaID) {
    WorkflowMeta workflowMeta = getWorkflowMeta(flowMetaID);
    FlowMetaFile flowMetaFile =
        getFlowMetaFile(workflowMeta.getFlowFileInUse().getFlowFileID().
                        toString());
    workflowMeta.setFlowFileInUse(flowMetaFile);

    return workflowMeta;
  }

  public WorkflowMeta saveWorkflowMeta(WorkflowMeta workflowMeta) {
    flowMetaDAO.saveWorkflowMeta(workflowMeta);
    return workflowMeta;
  }

  public void removeWorkflowMeta(String flowMetaID) {
//    WorkflowMeta wm = this.getWorkflowMeta(flowMetaID);
//    if (wm.getFlowFileVersions().size() > 0) {
//      for (Iterator it = wm.getFlowFileVersions().iterator(); it.hasNext(); ) {
//        FlowMetaFile fmf = (FlowMetaFile) it.next();
//        flowMetaStoreDAO.removeFlowMetaFile(fmf.getFlowFileID());
//      }
//    }
//    BusinessType bt = wm.getBusinessType();
//    if (bt != null) {
//      bt.removeWorkflowMeta(wm);
//    }

    flowMetaDAO.removeWorkflowMeta(new Long(flowMetaID));
  }

  public WorkflowMeta getWorkflowMetaByProcess(String flowProcessID) {
    return flowMetaDAO.getWorkflowMetaByProcess(flowProcessID);
  }

  public void updateFlowFileInUse(String flowMetaID, String flowFileID) {
    WorkflowMeta wm = this.getWorkflowMeta(flowMetaID);
    //flowMetaDAO.getWorkflowMeta(new Long(flowMetaID));
    FlowMetaFile fmFile = this.getFlowMetaFile(flowFileID);
    //this.flowMetaFileDAO.getFlowMetaFile(new Long(flowFileID));

    if (fmFile.getWorkflowMeta() == null ||
        ! ( (fmFile.getWorkflowMeta().getFlowMetaID().toString()).equals(
        flowMetaID))) {
      String mess = "试图把一个不属于他的FlowMetaFile[" + flowFileID +
          "]设为WorkflowMeta[" + flowMetaID + "]的当前版本！";
      log.debug(mess);
      throw new RuntimeException(mess);
    }
    wm.setFlowFileInUse(fmFile);

    //flowMetaFileDAO.saveFlowMetaFile(fmFile);
    this.saveWorkflowMeta(wm);
  }

  public void updateBusinessType(String flowMetaID, String businessTypeID) {
    WorkflowMeta wm = flowMetaDAO.getWorkflowMeta(new Long(flowMetaID));
    BusinessType bt = btDAO.getBusinessType(new Long(businessTypeID));
    wm.updateBusinessType(bt);
    btDAO.saveBusinessType(bt);
  }

  public FlowMetaFile getFlowMetaFile(String flowFileID) {
    FlowMetaFile fmFile =
        this.flowMetaFileDAO.getFlowMetaFile(new Long(flowFileID));
    FlowMetaFile fmFileStore =
        this.flowMetaStoreDAO.getFlowMetaFile(new Long(flowFileID));
    fmFile.setPreviewImageInput(fmFileStore.getPreviewImageInput());
    fmFile.setWorkflowFileInput(fmFileStore.getWorkflowFileInput());

    return fmFile;
  }

  public FlowMetaFile saveFlowMetaFile(FlowMetaFile flowMetaFile) {
    this.flowMetaFileDAO.saveFlowMetaFile(flowMetaFile);
    this.flowMetaStoreDAO.saveFlowMetaFile(flowMetaFile);

    return flowMetaFile;
  }

  public void removeFlowMetaFile(String flowFileID) {
    FlowMetaFile fmFile = this.getFlowMetaFile(flowFileID);
    WorkflowMeta wm = fmFile.getWorkflowMeta();
    if (wm.getFlowFileInUse() != null &&
        wm.getFlowFileInUse().getFlowFileID().toString().equals(flowFileID)) {
      String mess = "试图删除WorkflowMeta[" + wm.getFlowMetaID()
          + "]的当前版本FlowFile[" + flowFileID + "]！";
      log.debug(mess);
      throw new RuntimeException(mess);
    }

    wm.removeFlowFileVersion(fmFile);
    flowMetaFileDAO.removeFlowMetaFile(new Long(flowFileID));
    flowMetaStoreDAO.removeFlowMetaFile(new Long(flowFileID));
  }

  public void addFlowFileVersion(String flowMetaID, String flowFileID) {
    WorkflowMeta wm = this.getWorkflowMeta(flowMetaID);
    FlowMetaFile fmFile = this.getFlowMetaFile(flowFileID);
    wm.addFlowFileVersion(fmFile);
    flowMetaDAO.saveWorkflowMeta(wm);
  }

  public void removeFlowFileVersion(String flowMetaID, String flowFileID) {
//    WorkflowMeta wm = flowMetaDAO.getWorkflowMeta(new Long(flowMetaID));
//    FlowMetaFile fmFile =wm.getFlowFileInUse();
//    if(fmFile!=null&&
//       fmFile.getFlowFileID().toString().equals(flowFileID)){
//      String mess = "试图删除WorkflowMeta["+flowMetaID
//          +"]的当前版本FlowFile["+flowFileID+"]！";
//      log.debug(mess);
//      throw new RuntimeException(mess);
//    }
    this.removeFlowMetaFile(flowFileID);
  }

  /**
   * getWorkflowMetasNoBusinessType
   *
   * @return List
   */
  public List getWorkflowMetasNoBusinessType() {
    return flowMetaDAO.getWorkflowMetasNoBusinessType();
  }

  /**
   *
   * @param flowInput InputStream
   * @param flowInputTest InputStream
   * @param previewInput InputStream
   * @param flowSize Long
   * @param previewSize Long
   * @return WorkflowMeta
   */
  public WorkflowMeta uploadFlowMetaFile(InputStream flowInput,
                                         InputStream flowInputTest,
                                         InputStream previewInput,
                                         Long flowSize,
                                         Long previewSize) {
    SAXBuilder saxBuilder = new SAXBuilder(false);
    Document doc = null;
    try {
      doc = saxBuilder.build(flowInputTest);
    }
    catch (Exception ex) {
      log.debug(ex.getMessage());
      throw new FlowMetaException(ExceptionMessage.ERROR_FLOWMETAFILE_DIGESTER);
    }
    FlowModelDAO fmDAO = new FlowModelDAO(doc);
    FlowMetaFile[] flowMetaFile = fmDAO.getAllFlowMetaFiles();
    if (flowMetaFile == null || flowMetaFile.length == 0) {
      throw new FlowMetaException(ExceptionMessage.ERROR_FLOWMETAFILE_NO_METAS);
    }
    else if (flowMetaFile.length > 1) {
      throw new FlowMetaException(ExceptionMessage.
                                  ERROR_FLOWMETAFILE_MULTI_METAS);
    }

    FlowMetaFile fmf = flowMetaFile[0];
    WorkflowMeta wm = this.getWorkflowMetaByProcess(fmf.getFlowProcessID());
    if (wm != null) {
      throw new FlowMetaException(ExceptionMessage.
                                  ERROR_FLOWMETA_DUPLICATE_PROCESS_ID);
    }
    fmf.setWorkflowFileInput(flowInput);
    fmf.setPreviewImageInput(previewInput);
    fmf.setPreviewImageSize(previewSize);
    fmf.setWorkflowFileSize(flowSize);
    this.saveFlowMetaFile(fmf);

    wm = new WorkflowMeta();
    wm.setFlowProcessID(fmf.getFlowProcessID());
    wm.addFlowFileVersion(fmf);
    wm.setFlowFileInUse(fmf);
    return this.saveWorkflowMeta(wm);
  }

  public void addFlowDeploy(String flowMetaID, FlowDeploy flowDeploy) {
    WorkflowMeta wm = this.getWorkflowMeta(flowMetaID);
    wm.addFlowDeploy(flowDeploy);
    this.saveWorkflowMeta(wm);
  }

  public void updatePreviewImage(String flowMetaID,
                                 InputStream fileInputStream,
                                 Long previewSize) {
    WorkflowMeta wm = this.getWorkflowMetaWithFile(flowMetaID);
    FlowMetaFile fmFile = wm.getFlowFileInUse();
    fmFile.setPreviewImageInput(fileInputStream);
    fmFile.setPreviewImageSize(previewSize);
    this.flowMetaStoreDAO.updatePreviewImage(fmFile);
  }

}
