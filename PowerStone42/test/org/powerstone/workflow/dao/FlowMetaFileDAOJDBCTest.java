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
package org.powerstone.workflow.dao;

import org.powerstone.workflow.dao.jdbc.*;
import java.util.*;
import org.powerstone.workflow.model.*;
import org.apache.commons.logging.LogFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.powerstone.AbstractSpringTestCase;

/**
 * <p>Title: PowerStone</p>
 */

public class FlowMetaFileDAOJDBCTest extends AbstractSpringTestCase {
  private FlowMetaFileDAOJDBC fileStoreDAO = null;
  private FlowMetaFileDAO fileDAO = null;
  private FlowMetaFile flowMetaFile;

  protected void onSetUpInTransaction() throws Exception {
    log = LogFactory.getLog(FlowMetaFileDAOTest.class);
    log.info("-----------------------------");
    fileStoreDAO = (FlowMetaFileDAOJDBC) applicationContext.getBean("flowMetaFileStoreDAO");
    fileDAO = (FlowMetaFileDAO) applicationContext.getBean("flowMetaFileDAO");

    flowMetaFile = new FlowMetaFile();
    flowMetaFile.setFlowMetaName("FlowMetaName2");
    fileDAO.saveFlowMetaFile(flowMetaFile);
    log.info(new File("dreambike.xpdl").getName());
    try {
      flowMetaFile.setPreviewImageInput(new FileInputStream(new File("dreambike.xpdl")));
      flowMetaFile.setPreviewImageSize(
         new Long(new File("dreambike.xpdl").length()));
      flowMetaFile.setWorkflowFileInput(new FileInputStream(new File("dreambike.xpdl")));
      flowMetaFile.setWorkflowFileSize(
         new Long(new File("dreambike.xpdl").length()));
    }
    catch (FileNotFoundException ex) {
    }
    fileStoreDAO.saveFlowMetaFile(flowMetaFile);
    log.info("=============================");
  }

  public void testGetAllFlowMetaFiles() {
    try{
      List actualReturn = fileStoreDAO.getAllFlowMetaFiles();
      fail("方法未实现");
    }catch(Exception e){
      log.info(e);
    }
  }

  public void testGetFlowMetaFile() {
    FlowMetaFile actualReturn =
        fileStoreDAO.getFlowMetaFile(flowMetaFile.getFlowFileID());
    log.info(actualReturn.getWorkflowFileInput());
    log.info(actualReturn.getPreviewImageInput());
  }

  public void testRemoveFlowMetaFile() {
    Long flowFileID = new Long(400);
    try{
      fileStoreDAO.removeFlowMetaFile(flowFileID);
      fail("删除一个不存在的流程文件却没有报错");
    }catch(Exception e){
      log.info(e);
    }
  }

  public void testSaveFlowMetaFile() {
    try {
      flowMetaFile.setPreviewImageInput(new FileInputStream(new File("dreambike.xpdl")));
      flowMetaFile.setPreviewImageSize(
         new Long(new File("dreambike.xpdl").length()));
      flowMetaFile.setWorkflowFileInput(new FileInputStream(new File("dreambike.xpdl")));
      flowMetaFile.setWorkflowFileSize(
         new Long(new File("dreambike.xpdl").length()));
    }
    catch (FileNotFoundException ex) {
    }

    fileStoreDAO.saveFlowMetaFile(flowMetaFile);
    log.info(new File("dreambike.xpdl").getName());
    log.info(flowMetaFile.getPreviewImageSize());
  }
}
