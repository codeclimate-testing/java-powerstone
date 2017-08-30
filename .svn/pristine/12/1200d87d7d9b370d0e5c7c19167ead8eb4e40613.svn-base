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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.powerstone.ca.service.RoleManager;
import org.powerstone.ca.model.Role;

public class RoleFormController
    extends SimpleFormController {
  private final Log log = LogFactory.getLog(RoleFormController.class);
  private RoleManager roleManager = null;
  public void setRoleManager(RoleManager roleManager) {
    this.roleManager = roleManager;
  }

  public ModelAndView processFormSubmission(HttpServletRequest request,
                                            HttpServletResponse response,
                                            Object command,
                                            BindException errors) throws
      Exception {
    if (request.getParameter("cancel") != null) {
      return new ModelAndView(getSuccessView());
    }
    return super.processFormSubmission(request, response, command, errors);
  }

  public ModelAndView onSubmit(HttpServletRequest request,
                               HttpServletResponse response, Object command,
                               BindException errors) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug("entering 'onSubmit' method...");
    }
    Role role = (Role) command;

    if (role.getRoleID().longValue() == -1) {
      roleManager.createRole(role);
    }
    else {
      roleManager.updateRole(role);
    }
    request.getSession().setAttribute("message",
                                      getText("ca.role.saved",
                                              role.getRoleName()));
    return new ModelAndView(getSuccessView());
  }

  protected Object formBackingObject(HttpServletRequest request) throws
      ServletException {
    String roleID = request.getParameter("roleID");
    if ( (roleID != null) && !roleID.equals("-1")) {
      return roleManager.findByPrimaryKey(roleID);
    }
    else {
      Role role = new Role();
      if (log.isDebugEnabled()) {
        log.debug("entering 'formBackingObject' method..." + role);
      }
      return role;
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
