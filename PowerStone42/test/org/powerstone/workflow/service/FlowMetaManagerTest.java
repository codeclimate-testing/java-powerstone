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

import org.powerstone.workflow.model.*;
import java.util.*;
import org.apache.commons.logging.LogFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.springframework.dao.DataAccessException;

import java.io.FileOutputStream;
import java.io.*;
import org.springframework.util.FileCopyUtils;
import org.powerstone.workflow.exception.FlowMetaException;
import org.powerstone.AbstractSpringTestCase;

/**
 * <p>Title: PowerStone</p>
 */

public class FlowMetaManagerTest
    extends AbstractSpringTestCase {
  private FlowMetaManager flowMetaManager = null;
  private BusinessTypeManager businessTypeManager = null;
  WorkflowMeta wm;
  BusinessType bm;
  FlowMetaFile fmInuse;

  protected void onSetUpInTransaction() throws Exception {
    log = LogFactory.getLog(FlowMetaManagerTest.class);

    bm = new BusinessType();
    bm.setTypeName("TypeName1");
    businessTypeManager.createBusinessType(bm);
    log.info(bm);

    try {
      String fileName = "dreambike-cycle.xpdl";
      wm=flowMetaManager.uploadFlowMetaFile(
          new FileInputStream(new File(fileName)),
          new FileInputStream(new File(fileName)),
          new FileInputStream(new File(fileName)),
          new Long(new File(fileName).length()),
          new Long(new File(fileName).length()));
      fmInuse = (FlowMetaFile) wm.getFlowFileVersions().get(0);
    }
    catch (FileNotFoundException ex) {
      log.error(ex);
    }
    log.info(new Integer(wm.getFlowFileVersions().size()));
  }

  public void testRemoveWorkflowMeta() {
    String flowMetaID = "4000";
    try {
      flowMetaManager.removeWorkflowMeta(flowMetaID);
      fail("删除一个不存在的WorkflowMeta却没有报错");
    }
    catch (Exception e) {
      log.info(e);
    }
  }

  public void testGetWorkflowMetaByProcess() {
    WorkflowMeta actualReturn =
        flowMetaManager.getWorkflowMetaByProcess(wm.getFlowProcessID());
    assertEquals("return value",
                 wm.getFlowMetaID(),
                 actualReturn.getFlowMetaID());
  }

  public void testRemoveFlowFileVersionInUse() {
    try {
      flowMetaManager.removeFlowFileVersion(wm.getFlowMetaID().toString(),
                                            fmInuse.getFlowFileID().
                                            toString());
      fail("删除了使用中的FlowFileVersion");
    }
    catch (Exception e) {
      assertNotNull(e);
    }
  }

  public void testSaveWorkflowMeta() {
    wm.setFlowProcessID("FlowProcessID2");
    flowMetaManager.saveWorkflowMeta(wm);
    assertEquals("return value",
                 "FlowProcessID2",
                 flowMetaManager.getWorkflowMeta(wm.getFlowMetaID().toString()).
                 getFlowProcessID());
    log.info("FlowProcessID2");
  }

  public void testGetFlowMetaFile() {
    FlowMetaFile actualReturn =
        flowMetaManager.getFlowMetaFile(fmInuse.getFlowFileID().toString());
    this.assertNotNull(actualReturn);
    assertEquals("return value", fmInuse.getFlowMetaName(),
                 actualReturn.getFlowMetaName());
    log.info(actualReturn.getFlowMetaName());
  }

  public void testUpdateBusinessType() {
    flowMetaManager.updateBusinessType(wm.getFlowMetaID().toString(),
                                       bm.getTypeID().toString());
    assertEquals("return value", bm, wm.getBusinessType());
    log.info(wm.getBusinessType().getTypeID());
  }

  public void testGetWorkflowMeta() {
    WorkflowMeta actualReturn =
        flowMetaManager.getWorkflowMeta(wm.getFlowMetaID().toString());
    this.assertNotNull(actualReturn);
    assertEquals("return value", wm.getFlowProcessID(),
                 actualReturn.getFlowProcessID());
    log.info(actualReturn.getFlowProcessID());
    log.info(actualReturn.getFlowFileInUse().getCreatedTime());
  }

  public void testGetWorkflowMetaWithFile() throws FlowMetaException {
    WorkflowMeta actualReturn =
        flowMetaManager.getWorkflowMetaWithFile(wm.getFlowMetaID().toString());
    this.assertNotNull(actualReturn);
    this.assertNotNull(actualReturn.getFlowFileInUse().getWorkflowFileInput());
    this.assertNotNull(actualReturn.findWorkflowNodeByID("dreambike_Wor1_Act2").
                       getVariableToProcessIN());
    this.assertNotNull(actualReturn.findWorkflowNodeByID("dreambike_Wor1_Act2").
                       getVariableToProcessOUT());

    assertEquals("return value", wm.getFlowProcessID(),
                 actualReturn.getFlowProcessID());
    log.info(actualReturn.getFlowProcessID());
    log.info(actualReturn.getFlowModelDAO().getAllNodeIDs(actualReturn.
        getFlowProcessID())[0]);
  }

  public void testGetAllWorkflowMetas() {
    List actualReturn = flowMetaManager.getAllWorkflowMetas();
    assertEquals("return value", 1, actualReturn.size());
    log.info(new Integer(actualReturn.size()));
  }

  public void testGetWorkflowMetasNoBusinessType() {
    List actualReturn = flowMetaManager.getWorkflowMetasNoBusinessType();
    assertEquals("return value", 1, actualReturn.size());
    log.info(new Integer(actualReturn.size()));
  }

  public void testSaveFlowMetaFile() {
    FlowMetaFile ffToSave =
        flowMetaManager.getFlowMetaFile(fmInuse.getFlowFileID().toString());
    ffToSave.setFlowMetaName("FlowMetaName2");

    flowMetaManager.saveFlowMetaFile(fmInuse);
    assertEquals("return value",
                 "FlowMetaName2",
                 flowMetaManager.getFlowMetaFile(fmInuse.getFlowFileID().
                                                 toString()).getFlowMetaName());
    log.info("FlowMetaName2");
    FlowMetaFile ff =
        flowMetaManager.getFlowMetaFile(fmInuse.getFlowFileID().toString());
    FileOutputStream outFile;
    try {
      outFile = new FileOutputStream(new File("./daquan.txt"));
      FileCopyUtils.copy(ff.getPreviewImageInput(), outFile);
    }
    catch (Exception ex1) {
      ex1.printStackTrace();
      log.info(ex1);
    }
  }

  public void testUpdatePreviewImage() {
    try {
      flowMetaManager.updatePreviewImage(
          fmInuse.getWorkflowMeta().getFlowMetaID().toString(),
          new FileInputStream(new File("build.properties")),
          new Long(new File("build.properties").length()));
    }
    catch (FileNotFoundException ex) {
    }
    log.info("更新预览图片:build.properties");

    FlowMetaFile ff =
        flowMetaManager.getWorkflowMetaWithFile(fmInuse.getWorkflowMeta().
                                                getFlowMetaID().
                                                toString()).getFlowFileInUse();
    FileOutputStream outFile;
    try {
      outFile = new FileOutputStream(new File("./daquan_PreviewImage.txt"));
      FileCopyUtils.copy(ff.getPreviewImageInput(), outFile);
    }
    catch (Exception ex1) {
      ex1.printStackTrace();
      log.info(ex1);
    }

  }

  public void testRemoveFlowMetaFile() {
    try {
      flowMetaManager.removeFlowMetaFile("8000");
      fail("删除一个不存在的FlowMetaFile didn't throw DataAccessException");
    }
    catch (DataAccessException d) {
      assertNotNull(d);
      log.info(d);
    }
  }

//  public void testUpdateFlowFileInUse() {
//    flowMetaManager.updateFlowFileInUse(wm.getFlowMetaID().toString(),
//                                        fmNotInuse.getFlowFileID().toString());
//    WorkflowMeta theMeta =
//        flowMetaManager.getWorkflowMeta(wm.getFlowMetaID().toString());
//    FlowMetaFile theFile =
//        flowMetaManager.getFlowMetaFile(fmNotInuse.getFlowFileID().toString());
//
//    assertEquals("return value",
//                 theMeta.getFlowFileInUse().getFlowFileID(),
//                 theFile.getFlowFileID());
//    assertEquals("return value",
//                 theMeta.getFlowMetaID(),
//                 theFile.getWorkflowMeta().getFlowMetaID());
//    log.info(theMeta.getFlowFileInUse().getFlowFileID());
//  }

//  public void testAddFlowFileVersion() {
//    flowMetaManager.addFlowFileVersion(wm.getFlowMetaID().toString(),
//                                       fmNotInuse.getFlowFileID().toString());
//    WorkflowMeta theMeta =
//        flowMetaManager.getWorkflowMeta(wm.getFlowMetaID().toString());
//    FlowMetaFile theFile =
//        flowMetaManager.getFlowMetaFile(fmNotInuse.getFlowFileID().toString());
//
//    assertEquals("return value",
//                 theMeta.getFlowMetaID(),
//                 theFile.getWorkflowMeta().getFlowMetaID());
//    assertEquals("return value",
//                 fmNotInuse.getFlowFileID(),
//                 theFile.getFlowFileID());
//    log.info(new Integer(theMeta.getFlowFileVersions().size()));
//  }

  public void testAddFlowDeploy() {
    FlowDeploy flowDeploy = new FlowDeploy();
    flowDeploy.setCreateTime("-------CreateTime----------");
    flowDeploy.setCurrentState("CurrentState");
    flowDeploy.setFlowDeployName("FlowDeployName");
    flowDeploy.setMemo("Memo");
    flowMetaManager.addFlowDeploy(wm.getFlowMetaID().toString(), flowDeploy);
    WorkflowMeta theMeta =
        flowMetaManager.getWorkflowMeta(wm.getFlowMetaID().toString());
    assertEquals("return value",
                 1,
                 theMeta.getFlowDeploies().size());

    log.info( ( (FlowDeploy) theMeta.getFlowDeploies().get(0)).getCreateTime());
  }
  public void setBusinessTypeManager(BusinessTypeManager businessTypeManager) {
    this.businessTypeManager = businessTypeManager;
  }
  public void setFlowMetaManager(FlowMetaManager flowMetaManager) {
    this.flowMetaManager = flowMetaManager;
  }
}
