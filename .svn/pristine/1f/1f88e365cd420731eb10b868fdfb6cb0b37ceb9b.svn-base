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
import org.springframework.dao.DataIntegrityViolationException;
import org.powerstone.util.ResponseWriter;
import org.powerstone.ca.CADelegater;

public class MyInfoFormController
    extends SimpleFormController {
  private final Log log = LogFactory.getLog(MyInfoFormController.class);
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
    return super.processFormSubmission(request, response, command, errors);
  }

  public ModelAndView onSubmit(HttpServletRequest request,
                               HttpServletResponse response, Object command,
                               BindException errors) throws Exception {
    User user = (User) command;
    if (log.isDebugEnabled()) {
      log.debug("entering 'onSubmit' method..." + user);
    }
    try {
      User confirmUser=userManager.findUser(caDelegater.getRemoteUser(request));
      user.setId(confirmUser.getId());
      user.setPassword(confirmUser.getPassword());
      userManager.updateUser(user);
      //打印刷新父窗口的代码到客户端
      ResponseWriter.openerParentReloadClose(response);
      return null;
    }
    catch (DataIntegrityViolationException e) {
      log.error(e);
      User userWithSameEmail = userManager.findUserByEmail(user.getEmail());
      if (userWithSameEmail != null &&
          !userWithSameEmail.getId().toString().equals(caDelegater.
          getRemoteUser(request))) {
        errors.rejectValue("email", "duplicate_email",
                           getText("error.ca.duplicate_email", user.getEmail()));
      }
      else {
        throw e;
      }
      if (log.isDebugEnabled()) {
        log.debug(user);
      }
      return showForm(request, response, errors);
    }
  }

  protected Object formBackingObject(HttpServletRequest request) throws
      ServletException {
    User user =userManager.findUser(caDelegater.getRemoteUser(request));
    if (log.isDebugEnabled()) {
      log.debug("entering 'formBackingObject' user[" + user+"]");
    }
    /**
     * 如果直接返回userManager.findUser()，会出现以外情况：密码被修改
     * 推测原因：因为返回的userManager.findUser()是容器（hibernate容器）内对象即PO，
     * 所以spring进行数据绑定时会发生意外更新
     */
    User result=new User();
    result.setEmail(user.getEmail());
    result.setMemo(user.getMemo());
    result.setRealName(user.getRealName());
    result.setSex(user.getSex());
    result.setUserName(user.getUserName());
    return result;
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
