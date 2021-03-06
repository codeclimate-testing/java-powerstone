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
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.powerstone.ca.service.UserManager;
import org.powerstone.ca.model.User;
import javax.servlet.ServletException;
import org.powerstone.ca.CADelegater;
import org.powerstone.util.StringUtil;

public class MyPassFormController
    extends SimpleFormController {
  private final Log log = LogFactory.getLog(PassFormController.class);
  private UserManager userManager;
  private CADelegater caDelegater;

  public void setUserManager(UserManager userManager) {
    this.userManager = userManager;
  }

  public void setCaDelegater(CADelegater caDelegater) {
    this.caDelegater = caDelegater;
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
    User user = (User) command;
    if (log.isDebugEnabled()) {
      log.debug("entering 'onSubmit' method..." + user);
    }
    User target = userManager.findUser(caDelegater.getRemoteUser(request));
    if (target.getPassword().equals(StringUtil.hash(user.getOldPass()))) {
      user.setId(target.getId());
      userManager.changePass(user);
      return new ModelAndView(getSuccessView());
    }
    else {
      errors.rejectValue("oldPass", "invalid_old_pass",
                         getText("error.ca.invalid_old_pass"));
      return showForm(request, response, errors);
    }
  }

  protected Object formBackingObject(HttpServletRequest request) throws
      ServletException {
    request.setAttribute("id", caDelegater.getRemoteUser(request));
    if (log.isDebugEnabled()) {
      log.debug("entering 'formBackingObject'");
    }
    //return userManager.findUser(request.getParameter("userID"));
    return new User();
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
