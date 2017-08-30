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
import org.powerstone.ca.service.UserManager;
import org.powerstone.ca.service.GroupManager;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import org.powerstone.util.TreeMaker;
import org.powerstone.ca.model.Group;
import java.util.Iterator;
import org.powerstone.ca.service.AuthenticateManager;
import org.springframework.web.servlet.view.RedirectView;
import org.powerstone.ca.CAFilter;

public class UserGroupController
    extends MultiActionController {
  private static Log log = LogFactory.getLog(UserGroupController.class);
  private UserManager userManager;
  private GroupManager groupManager;
  private AuthenticateManager authenticate;
  private Integer pageSize = new Integer(20); //页大小
  public void setUserManager(UserManager userManager) {
    this.userManager = userManager;
  }

  public void setGroupManager(GroupManager groupManager) {
    this.groupManager = groupManager;
  }

  /**
   <prop key="/user_group/success_submit.ca">successSubmit</prop>
   <prop key="/user_group/list_groups.ca">listGroups</prop>
   <prop key="/user_group/list_groups_tree.ca">listGroupsTree</prop>
   <prop key="/user_group/user_group/list_group_members.ca">listGroupMembers</prop>
   <prop key="/user_group/list_users_no_group.ca">listUsersNoGroup</prop>
   <prop key="/user_group/remove_group.ca">removeGroup</prop>
   <prop key="/user_group/add_group_members.ca">addGroupMembers</prop>
   <prop key="/user_group/group_tree_to_add_member.ca">groupTreeToAddMember</prop>
   <prop key="/user_group/list_users_to_add_member.ca">listUsersToAddMember</prop>
   <prop key="/user_group/add_users_to_group.ca">addUsersToGroup</prop>
   <prop key="/user_group/remove_group_members.ca">removeGroupMembers</prop>
   <prop key="/user_group/login.ca">login</prop>
   <prop key="/logout.ca">logout</prop>
   */
  public ModelAndView login(HttpServletRequest request,
                            HttpServletResponse response) throws
      Exception {
    if (authenticate.authenticate(request.getParameter("userID"),
                                  request.getParameter("pass"),
                                  request,
                                  response)) {
      Object url = request.getSession().getAttribute(CAFilter.DESTINATION_URL);
      if (url != null) {
        request.getSession().removeAttribute(CAFilter.DESTINATION_URL);
        if (log.isDebugEnabled()) {
          log.debug(CAFilter.DESTINATION_URL + "[" + url + "]");
        }
        return new ModelAndView(new RedirectView(request.getContextPath() + url));
      }
    }
    return new ModelAndView(new RedirectView(request.getContextPath() + "/"));
  }

  public ModelAndView logout(HttpServletRequest request,
                             HttpServletResponse response) throws
      Exception {
    authenticate.leaveLine(response);
    return new ModelAndView(new RedirectView(request.getContextPath() + "/"));
  }

  public ModelAndView successSubmit(HttpServletRequest request,
                                    HttpServletResponse response) throws
      Exception {
    return new ModelAndView("successSubmit");
  }

  public ModelAndView listGroups(HttpServletRequest request,
                                 HttpServletResponse response) throws
      Exception {
    return new ModelAndView("listGroups");
  }

  public ModelAndView listGroupsTree(HttpServletRequest request,
                                     HttpServletResponse response) throws
      Exception {
    HashMap map = new HashMap();
    List allGroups = groupManager.findAllGroups();
    TreeMaker tm =
        new TreeMaker(request.getContextPath() +
                      "/user_group/list_group_members.ca?groupID=",
                      request.getContextPath(),
                      "mainFrame",
                      null, //<---checkboxName
                      allGroups);

    map.put("groupTree", tm.makeTree());
    return new ModelAndView("listGroupsTree", map);
  }

  public ModelAndView removeGroup(HttpServletRequest request,
                                  HttpServletResponse response) throws
      Exception {
    String groupID = request.getParameter("groupID");
    groupManager.removeGroup(groupID);
    return listGroups(request, response);
  }

  public ModelAndView listGroupMembers(HttpServletRequest request,
                                       HttpServletResponse response) throws
      Exception {
    HashMap model = new HashMap();
    String groupID = request.getParameter("groupID");
    Group group = null;
    try {
      group = groupManager.findGroup(groupID);
    }
    catch (Exception e) { //found no group with the groupID
      log.error(e);
      return listUsersNoGroup(request, response);
    }

    String currPageNo = request.getParameter("currPageNo"); //当前处在第几页
    Integer pageNum =
        (currPageNo != null ? new Integer(currPageNo) : new Integer("0"));
    String strTo = request.getParameter("pageTo"); //去第几页
    Integer pageTo = (strTo != null ? new Integer(strTo) : null);
    String first = request.getParameter("first");
    String last = request.getParameter("last");
    String next = request.getParameter("next");
    String end = request.getParameter("end");
    int iPage = 0; //当前第几页
    int pageCount = 0; //共多少页

    //共有多少人
    int groupMembersNum = group.getUsers().size();

    if (groupMembersNum % pageSize.intValue() == 0) {
      pageCount = groupMembersNum / pageSize.intValue();
    }
    else {
      pageCount = groupMembersNum / pageSize.intValue() + 1;
    }
    if (pageCount > 1) {
      model.put("totalPageNum", new Integer(pageCount));
    }

    if (pageTo != null) {
      iPage = pageTo.intValue();
    }
    else if (first == null && last == null && next == null && end == null) {
      iPage = 1;
    }
    else if (first != null) {
      iPage = 1;
    }
    else if (last != null) {
      iPage = pageNum.intValue() - 1;
    }
    else if (next != null) {
      iPage = pageNum.intValue() + 1;
    }
    else if (end != null) {
      iPage = pageCount;
    }
    if (iPage < 1) {
      iPage = 1;
    }
    if (iPage > pageCount) {
      iPage = pageCount;
    }
    model.put("currPageNo", new Integer(iPage));
    List aPageMembers = groupManager.findGroupMembersByPage(groupID, iPage,
        pageSize.intValue());
    model.put("aPageMembers", aPageMembers);
    model.put("groupID", groupID);
    model.put("groupName", group.getGroupName());
    model.put("pageSize", pageSize);
    model.put("groupMembersNum", new Integer(groupMembersNum));
    return new ModelAndView("listGroupMembers", model);
  }

  public ModelAndView listUsersNoGroup(HttpServletRequest request,
                                       HttpServletResponse response) throws
      Exception {
    HashMap model = new HashMap();
    model.put("usersHaveNoGroup", userManager.findUsersHaveNoGroup());

    List allGroups = groupManager.findAllGroups();
    String optionsTree = "";
    for (Iterator it = allGroups.iterator(); it.hasNext(); ) {
      Group group = (Group) it.next();
      if (group.getParentGroup() == null) {
        optionsTree = GroupSelectTreeMaker.printSelectTreeMaker(optionsTree,
            group, "");
      }
    }
    model.put("optionsTree", optionsTree);
    if (log.isDebugEnabled()) {
      log.debug(optionsTree);
    }
    return new ModelAndView("listUsersNoGroup", model);
  }

  public ModelAndView addUsersToGroup(HttpServletRequest request,
                                      HttpServletResponse response) throws
      Exception {
    String[] toAdds = request.getParameterValues("toDo");
    String groupID = request.getParameter("groupID");
    for (int i = 0; i < toAdds.length; i++) {
      groupManager.addUserToGroup(toAdds[i], groupID);
    }

    return listGroupMembers(request, response);
  }

  public ModelAndView removeGroupMembers(HttpServletRequest request,
                                         HttpServletResponse response) throws
      Exception {
    String[] toDels = request.getParameterValues("toDo");
    String groupID = request.getParameter("groupID");

    for (int i = 0; i < toDels.length; i++) {
      groupManager.removeUserFromGroup(groupID, toDels[i]);
    }

    return listGroupMembers(request, response);
  }

  public ModelAndView changePass(HttpServletRequest request,
                                 HttpServletResponse response) throws
      Exception {
    request.setAttribute("userID", request.getParameter("userID"));
    request.setAttribute("groupID", request.getParameter("groupID"));
    request.setAttribute("pageTo", request.getParameter("pageTo"));

    return new ModelAndView("changePass");
  }

  public ModelAndView updatePass(HttpServletRequest request,
                                 HttpServletResponse response) throws
      Exception {

    return new ModelAndView(new RedirectView("user_group/edit_user.ca?userID=" +
                                             request.getParameter("userID") +
                                             "&groupID=" +
                                             request.getParameter("groupID") +
                                             "&pageTo=" +
                                             request.getParameter("pageTo")));
  }

  public void setAuthenticate(AuthenticateManager authenticate) {
    this.authenticate = authenticate;
  }
  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

}
