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

import java.util.*;
import org.powerstone.workflow.model.*;
import org.apache.commons.logging.LogFactory;
import org.powerstone.AbstractSpringTestCase;

/**
 * <p>Title: PowerStone</p>
 */

public class FlowMetaFileDAOTest
    extends AbstractSpringTestCase {
  private FlowMetaFileDAO dao = null;
  FlowMetaFile fmf;

  protected void onSetUpInTransaction() throws Exception {
    log = LogFactory.getLog(FlowMetaFileDAOTest.class);
    dao=(FlowMetaFileDAO)super.applicationContext.getBean("flowMetaFileDAO");

    fmf=new FlowMetaFile();
    fmf.setFlowMetaName("FlowMetaName");
    dao.saveFlowMetaFile(fmf);
  }

  public void testGetAllFlowMetaFiles() {
    List actualReturn = dao.getAllFlowMetaFiles();
    assertEquals("return value", 1, actualReturn.size());
    log.info(new Integer(actualReturn.size()));
  }

  public void testGetFlowMetaFile() {
    FlowMetaFile actualReturn = dao.getFlowMetaFile(fmf.getFlowFileID());
    this.assertNotNull(actualReturn);
    assertEquals("return value", fmf.getFlowMetaName(),
                 actualReturn.getFlowMetaName());
    log.info(actualReturn.getFlowMetaName());
  }

//  public void testRemoveFlowMetaFile() {
//    dao.removeFlowMetaFile(fmf.getFlowFileID());
//    try{
//      fmf= dao.getFlowMetaFile(fmf.getFlowFileID());
//      fail("查找一个不存在的FlowMetaFile didn't throw DataAccessException");
//    }catch(DataAccessException d){
//      assertNotNull(d);
//      log.info(d);
//    }
//    log.info(fmf.getFlowFileID());
//  }

  public void testSaveFlowMetaFile() {
    fmf.setFlowMetaName("FlowMetaName2");
    dao.saveFlowMetaFile(fmf);
    assertEquals("return value",
                 "FlowMetaName2",
                 dao.getFlowMetaFile(fmf.getFlowFileID()).getFlowMetaName());
    log.info("FlowMetaName2");
  }
}
