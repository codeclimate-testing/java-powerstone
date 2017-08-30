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

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class ResourceManageController
    extends MultiActionController {
  private static Log log = LogFactory.getLog(ResourceManageController.class);
  private org.powerstone.ca.service.ResourceManager resourceManager;
  public void setResourceManager(org.powerstone.ca.service.ResourceManager
                                 resourceManager) {
    this.resourceManager = resourceManager;
  }

  /**
   <prop key="/resource_manage/success_submit.resource">successSubmit</prop>
   <prop key="/resource_manage/list_web_modules.resource">listWebModules</prop>
   <prop key="/resource_manage/list_web_modules_tree.resource">listWebModulesTree</prop>
   <prop key="/resource_manage/list_web_module_resources.resource">listWebModuleResources</prop>
   <prop key="/resource_manage/remove_web_module.resource">removeWebModule</prop>
   <prop key="/resource_manage/remove_resource.resource">removeResource</prop>
   */
  public ModelAndView successSubmit(HttpServletRequest request,
                                    HttpServletResponse response) throws
      Exception {
    return new ModelAndView("successSubmit");
  }

  public ModelAndView listWebModules(HttpServletRequest request,
                                     HttpServletResponse response) throws
      Exception {
    return new ModelAndView("listWebModules");
  }

  public ModelAndView listWebModulesTree(HttpServletRequest request,
                                         HttpServletResponse response) throws
      Exception {
    return new ModelAndView("listWebModulesTree", "allModules",
                            resourceManager.getAllModules());
  }

  public ModelAndView listWebModuleResources(HttpServletRequest request,
                                             HttpServletResponse response) throws
      Exception {
    String webModuleID = request.getParameter("webModuleID");
    HashMap model = new HashMap();
    model.put("webModuleID", webModuleID);
    model.put("resources",
              resourceManager.findWebModule(webModuleID).getResources());
    return new ModelAndView("listWebModuleResources", model);
  }

  public ModelAndView removeWebModule(HttpServletRequest request,
                                      HttpServletResponse response) throws
      Exception {
    String webModuleID = request.getParameter("webModuleID");
//    if (resourceManager.findWebModule(webModuleID).getStatus().equals("")) {
    resourceManager.removeWebModule(webModuleID);
//    }
    return listWebModules(request, response);
  }

  public ModelAndView removeResource(HttpServletRequest request,
                                     HttpServletResponse response) throws
      Exception {
    String[] toDo = request.getParameterValues("toDo");
    for (int i = 0; i < toDo.length; i++) {
//    if (resourceManager.findResource(resourceID).getActionURL().equals("")) {
      resourceManager.removeResource(toDo[i]);
//    }
    }
    return listWebModuleResources(request, response);
  }

}
