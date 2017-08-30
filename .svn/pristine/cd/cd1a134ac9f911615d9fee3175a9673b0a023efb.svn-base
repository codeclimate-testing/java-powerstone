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

import org.powerstone.workflow.*;
import java.util.*;
import org.powerstone.workflow.model.*;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.powerstone.AbstractSpringTestCase;

/**
 * <p>Title: PowerStone</p>
 */

public class BusinessTypeDAOTest
    extends AbstractSpringTestCase {
  private BusinessTypeDAO dao = null;
  BusinessType bt = null;

  protected void onSetUpInTransaction() throws Exception {
    log = LogFactory.getLog(FlowMetaFileDAOTest.class);

    bt = new BusinessType();
    bt.setTypeName("bt1");
    dao.saveBusinessType(bt);
    log.info("new BusinessType[" + bt.getTypeID() + "]");
  }

  public void testGetBusinessType() {
    BusinessType actualReturn = dao.getBusinessType(bt.getTypeID());
    assertNotNull(actualReturn);
    assertEquals("return value", "bt1", actualReturn.getTypeName());
    log.info(actualReturn.getTypeName());
  }

  public void testRemoveBusinessType() {
    try {
      dao.removeBusinessType(new Long(90000));
      fail("删除一个不存在的BusinessType didn't throw DataAccessException");
    }
    catch (DataAccessException d) {
      assertNotNull(d);
      log.info(d);
    }
    log.info("hahaha"+bt.getTypeID());
  }

  public void testGetAllBusinessTypes() {
    List actualReturn = dao.getAllBusinessTypes();

    assertEquals("return value", 1, actualReturn.size());
    log.info(new Integer(actualReturn.size()));
  }

  public void testSaveBusinessType() {
    bt.setTypeName("bt2");
    dao.saveBusinessType(bt);
    assertEquals("return value",
                 "bt2",
                 dao.getBusinessType(bt.getTypeID()).getTypeName());
    log.info(">>>>" + new Integer(dao.getAllBusinessTypes().size()));
    log.info("bt2");
  }
  public void setDao(BusinessTypeDAO dao) {
    this.dao = dao;
  }
}
