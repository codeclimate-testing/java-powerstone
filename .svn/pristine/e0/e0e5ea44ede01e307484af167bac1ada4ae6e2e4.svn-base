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
package org.powerstone.ca.dao;

import org.powerstone.*;
import org.powerstone.ca.model.*;
import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ResourceDAOTest
    extends AbstractSpringTestCase {
  private static Log log = LogFactory.getLog(ResourceDAOTest.class);
  Resource _resource = null;
  WebModule _webModule = null;
  private ResourceDAO resourceDAO = null;

  protected void onSetUpInTransaction() throws Exception {
    _webModule = new WebModule();
    _webModule.setFacePage("setFacePage");
    _webModule.setStatus("setStatus");
    _webModule.setWebModuleName("setWebModuleName");
    _resource = new Resource();
    _resource.setActionURL("setActionURL");
    _resource.setResourceID("setResourceID");
    _resource.setResourceName("setResourceName");
    _webModule.addResource(_resource);
    resourceDAO.saveWebModule(_webModule);
  }

  public void testSaveResource() {
    this.assertTrue("_resource.getId()!=null", _resource.getId() != null);
    log.info("before save:" + _resource);
    _resource.setResourceID("_res_ID");
    _resource.setActionURL("_act_URL");
    resourceDAO.saveResource(_resource);

    this.assertEquals("_resource ID", "_res_ID",
                      jdbcTemplate.queryForObject(
        "select VC_RESOURCE_ID from CA_RESOURCE", String.class));
    this.assertEquals("_resource ActionURL", "_act_URL",
                      jdbcTemplate.queryForObject(
        "select VC_ACTION_URL from CA_RESOURCE", String.class));
    log.info("after save:" + _resource);
  }

  public void testSaveWebModule() {
    this.assertTrue("_webModule.getId()!=null", _webModule.getWebModuleID() != null);
    log.info("before save:" + _webModule);
    _webModule.setFacePage("face page");
    _webModule.setWebModuleName("module name");
    resourceDAO.saveWebModule(_webModule);

    this.assertEquals("face page", "face page",
                      jdbcTemplate.queryForObject(
        "select VC_FACE_PAGE from CA_WEB_MODULE", String.class));
    this.assertEquals("module name", "module name",
                      jdbcTemplate.queryForObject(
        "select VC_WEB_MODULE_NAME from CA_WEB_MODULE", String.class));
    log.info("after save:" + _webModule);
  }

  public void testFindAllResources() {
    List actualReturn = resourceDAO.findAllResources();
    assertEquals("number of all resources", 1, actualReturn.size());
    assertEquals("the only resource", _resource, actualReturn.get(0));
  }

  public void testFindAllWebModules() {
    List actualReturn = resourceDAO.findAllWebModules();
    assertEquals("number of all webModules", 1, actualReturn.size());
    assertEquals("the only group", _webModule, actualReturn.get(0));
  }

  public void testFindResourceByPrimaryKey() {
    Resource actualReturn = resourceDAO.findResourceByPrimaryKey(_resource.
        getId());
    assertEquals("the resource", _resource, actualReturn);
  }

  public void testFindResourceByResourceID() {
    Resource actualReturn = resourceDAO.findResourceByResourceID(_resource.
        getResourceID());
    assertNotNull("findResourceByResourceID",actualReturn);
    assertEquals("the resource with ResourceID" + _resource.getResourceID(),
                 _resource, actualReturn);
  }

  public void testFindWebModuleByPrimaryKey() {
    WebModule actualReturn = resourceDAO.findWebModuleByPrimaryKey(_webModule.
        getWebModuleID());
    assertEquals("the webModule", _webModule, actualReturn);
  }

  public void testRemoveWebModule() {
    this.assertEquals("number of webModules", 1,
                      jdbcTemplate.queryForInt("select count(*) from CA_WEB_MODULE"));
    resourceDAO.removeWebModule(_webModule.getWebModuleID());
    this.assertEquals("number of groups", 0,
                      jdbcTemplate.queryForInt("select count(*) from CA_WEB_MODULE"));
  }

  public void setResourceDAO(ResourceDAO resourceDAO) {
    this.resourceDAO = resourceDAO;
  }

}
