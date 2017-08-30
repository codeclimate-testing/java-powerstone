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
import org.powerstone.ca.service.GroupManager;
import org.powerstone.ca.model.Group;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import javax.servlet.ServletException;

public class GroupFormController
    extends SimpleFormController {
  private final Log log = LogFactory.getLog(GroupFormController.class);
  private GroupManager groupManager = null;

  public void setGroupManager(GroupManager groupManager) {
    this.groupManager = groupManager;
  }

//  public GroupFormController() {
//    setCommandName("group");
//    setCommandClass(Group.class);
//  }

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
    String parentGroupID = request.getParameter("parentGroupID");
    if (log.isDebugEnabled()) {
      log.debug("entering 'onSubmit' method..." + parentGroupID);
    }
    Group group = (Group) command;

    if (group.getGroupID().longValue() == -1) {
      if (parentGroupID != null) {
        groupManager.createSubGroup(parentGroupID, group);
      }
      else {
        groupManager.createGroup(group);
      }
    }
    else {
      groupManager.updateGroup(group);
    }

    request.getSession().setAttribute("message",
                                      getText("ca.group.saved",
                                              group.getGroupName()));
    return new ModelAndView(getSuccessView());
  }

  protected Object formBackingObject(HttpServletRequest request) throws
      ServletException {
    String groupID = request.getParameter("groupID");
    String parentGroupID = request.getParameter("parentGroupID");
    if (parentGroupID != null) {
      request.setAttribute("parentGroupID", parentGroupID);
    }

    if ( (groupID != null) && !groupID.equals("-1")) {
      return groupManager.findGroup(groupID);
    }
    else {
      Group group = new Group();
      if (log.isDebugEnabled()) {
        log.debug("entering 'formBackingObject' method..." + group);
      }
      return group;
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
