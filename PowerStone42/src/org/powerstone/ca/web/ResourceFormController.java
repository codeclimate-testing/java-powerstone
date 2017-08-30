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
package org.powerstone.ca.web;

import org.springframework.web.servlet.mvc.SimpleFormController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import javax.servlet.ServletException;
import org.powerstone.ca.model.Resource;
import org.powerstone.ca.service.ResourceManager;
import org.springframework.dao.DataIntegrityViolationException;

public class ResourceFormController
    extends SimpleFormController {
  private final Log log = LogFactory.getLog(ResourceFormController.class);

  private ResourceManager resourceManager;
  public void setResourceManager(ResourceManager resourceManager) {
    this.resourceManager = resourceManager;
  }

  public ModelAndView processFormSubmission(HttpServletRequest request,
                                            HttpServletResponse response,
                                            Object command,
                                            BindException errors) throws
      Exception {
    if (request.getParameter("cancel") != null) {
      return new ModelAndView(getSuccessView() + "?webModuleID=" +
                              request.getParameter("webModuleID"));
    }
    return super.processFormSubmission(request, response, command, errors);
  }

  public ModelAndView onSubmit(HttpServletRequest request,
                               HttpServletResponse response, Object command,
                               BindException errors) throws Exception {
    Resource resource = (Resource) command;

    String webModuleID = request.getParameter("webModuleID");
    if (log.isDebugEnabled()) {
      log.debug("entering 'onSubmit' method..." + resource + "webModule[" +
                webModuleID +
                "]");
    }
    try {
      if (resource.getId().longValue() == -1) {
        resourceManager.createResource(resource);
        if (webModuleID != null) {
          resourceManager.addResourceToWebModule(resource.getId().toString(),
                                                 webModuleID);
        }
        else {

        }
      }
      else {
        resourceManager.updateResource(resource);
      }
      return new ModelAndView(getSuccessView() + "?webModuleID=" + webModuleID);
    }
    catch (DataIntegrityViolationException e) {
      log.error(e);
      Resource resourceWithSameResourceID =
          resourceManager.findResourceByResourceID(resource.getResourceID());
      Resource resourceWithSameResourceName =
          resourceManager.findResourceByName(resource.getResourceName());

      if ( (resource.getId().longValue() == -1 && resourceWithSameResourceID != null) ||
          (resource.getId().longValue() != -1 && resourceWithSameResourceID != null &&
           !resourceWithSameResourceID.getId().equals(resource.getId()))
          ) {
        errors.rejectValue("resourceID", "duplicate_resource_id",
                           getText("error.ca.duplicate_resource_id",
                                   resource.getResourceID()));
      }
      else if (resourceWithSameResourceName != null &&
               !resourceWithSameResourceName.getId().equals(resource.getId())) {
        errors.rejectValue("resourceName", "duplicate_resource_name",
                           getText("error.ca.duplicate_resource_name",
                                   resource.getResourceName()));
      }
      else {
        log.error(e);
        throw e;
      }
      if (log.isDebugEnabled()) {
        log.debug(resource);
      }
      return showForm(request, response, errors);
    }
  }

  protected Object formBackingObject(HttpServletRequest request) throws
      ServletException {
    String rsID = request.getParameter("rsID");
    String webModuleID = request.getParameter("webModuleID");
    if (log.isDebugEnabled()) {
      log.debug("entering 'formBackingObject' resource[" + rsID + "]webModule[" +
                webModuleID + "]");
    }
    if (webModuleID != null) {
      request.setAttribute("webModuleID", webModuleID);
    }
    if ( (rsID != null) && !rsID.equals("-1")) {
      request.setAttribute("rsID", rsID);
      Resource result = resourceManager.findResource(rsID);
      return result;
    }
    else {
      return new Resource();
    }
  }

  public String getText(String msgKey) {
    return getMessageSourceAccessor().getMessage(msgKey);
  }

  public String getText(String msgKey, String arg) {
    return getText(msgKey, new Object[] {arg});
  }

  public String getText(String msgKey, Object[] args) {
    return getMessageSourceAccessor().getMessage(msgKey, args);
  }

}
