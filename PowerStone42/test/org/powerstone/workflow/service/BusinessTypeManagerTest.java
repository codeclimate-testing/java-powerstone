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

import org.powerstone.workflow.*;
import java.util.*;
import org.powerstone.workflow.model.*;
import org.apache.commons.logging.LogFactory;
import org.powerstone.AbstractSpringTestCase;

/**
 * <p>Title: PowerStone</p>
 */

public class BusinessTypeManagerTest
    extends AbstractSpringTestCase {
  private BusinessTypeManager businessTypeManager = null;
  BusinessType bt;

  protected void onSetUpInTransaction() throws Exception {
    log = LogFactory.getLog(BusinessTypeManagerTest.class);
    bt = new BusinessType();
    bt.setTypeName("TypeName1");
    businessTypeManager.createBusinessType(bt);

    log.info(bt.getTypeID() + "=============================" +
             bt.getTypeName());
  }

  public void testGetAllBusinessTypes() {
    List actualReturn = businessTypeManager.getAllBusinessTypes();
    assertEquals("return value", 1, actualReturn.size());
    assertEquals("return value",
                 "TypeName1",
                 ((BusinessType)actualReturn.get(0)).getTypeName());

    log.info(new Integer(actualReturn.size()));
  }

  public void testGetBusinessType() {
    assertEquals("return value",
                 "TypeName1",
                 businessTypeManager.getBusinessType(bt.getTypeID().toString()).
                 getTypeName());

  }

  public void testRemoveBusinessType() {
    String businessTypeID = "4000";
    try{
      businessTypeManager.removeBusinessType(businessTypeID);
      fail("删除一个不存在的BusinessType didn't throw DataAccessException");
    }catch(Exception e){
      assertNotNull(e);
    }
  }

  public void testRenameBusinessType() {
    businessTypeManager.renameBusinessType(bt.getTypeID().toString(),
                                           "daquan");
    assertEquals("new TypeName",
                 "daquan",
                 businessTypeManager.getBusinessType(bt.getTypeID().toString()).
                 getTypeName());
    log.info("daquan");
  }

  public void testSaveBusinessType() {
    bt.setTypeName("daquan2");
    businessTypeManager.createBusinessType(bt);
    assertEquals("new TypeName",
                 "daquan2",
                 businessTypeManager.getBusinessType(bt.getTypeID().toString()).
                 getTypeName());
    log.info("daquan2");
  }
  public void setBusinessTypeManager(BusinessTypeManager businessTypeManager) {
    this.businessTypeManager = businessTypeManager;
  }

}