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
package org.powerstone.ca.service.impl;

import org.powerstone.ca.service.ResourceManager;
import java.util.List;
import org.powerstone.ca.model.WebModule;
import org.powerstone.ca.model.Resource;
import org.powerstone.ca.dao.ResourceDAO;

public class ResourceManagerImpl
    implements ResourceManager {

  private ResourceDAO resourceDAO;
  public String[][] getWebModules() {
    return null;
  }

  public List getAllModules() {
    return resourceDAO.findAllWebModules();
  }

  public WebModule createWebModule(WebModule webModule) {
    resourceDAO.saveWebModule(webModule);
    return webModule;
  }

  public Resource createResource(Resource resource) {
    resourceDAO.saveResource(resource);
    return resource;
  }

  public void removeWebModule(String webModuleID) {
    resourceDAO.removeWebModule(new Long(webModuleID));
  }

  public void removeResource(String resourceID) {
    Resource resource = findResource(resourceID);
    WebModule webModule = resource.getWebModule();
    webModule.removeResource(resource);
    resourceDAO.saveWebModule(webModule);
  }

  public WebModule findWebModule(String webModuleID) {
    return resourceDAO.findWebModuleByPrimaryKey(new Long(webModuleID));
  }

  public Resource findResource(String resourceID) {
    return resourceDAO.findResourceByPrimaryKey(new Long(resourceID));
  }

  public Resource findResourceByName(String resourceName) {
    return resourceDAO.findResourceByName(resourceName);
  }

  public Resource findResourceByResourceID(String resourceID) {
    return resourceDAO.findResourceByResourceID(resourceID);
  }

  public WebModule updateWebModule(WebModule webModule) {
    WebModule result = findWebModule(webModule.getWebModuleID().toString());
    result.setFacePage(webModule.getFacePage());
    result.setStatus(webModule.getStatus());
    result.setWebModuleName(webModule.getWebModuleName());
    resourceDAO.saveWebModule(result);
    return result;
  }

  public Resource updateResource(Resource resource) {
    Resource result = findResource(resource.getId().toString());
    result.setActionURL(resource.getActionURL());
    result.setResourceID(resource.getResourceID());
    result.setResourceName(resource.getResourceName());
    resourceDAO.saveResource(result);
    return result;
  }

  public List getAllResources() {
    return resourceDAO.findAllResources();
  }

  public void addResourceToWebModule(String rsID, String webModuleID) {
    Resource resource = findResource(rsID);
    WebModule webModule = findWebModule(webModuleID);
    webModule.addResource(resource);
    resourceDAO.saveWebModule(webModule);
  }

  public void setResourceDAO(ResourceDAO resourceDAO) {
    this.resourceDAO = resourceDAO;
  }
}
