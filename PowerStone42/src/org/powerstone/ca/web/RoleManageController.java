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
import org.powerstone.ca.service.UserManager;
import org.powerstone.ca.service.RoleManager;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import org.powerstone.ca.model.Role;
import java.util.List;
import org.powerstone.util.TreeMaker;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.powerstone.ca.service.GroupManager;

public class RoleManageController
    extends MultiActionController {
  private static final Log log = LogFactory.getLog(RoleManageController.class);
  private UserManager userManager;
  private RoleManager roleManager;
  private GroupManager groupManager;
  private Integer pageSize = new Integer(20); //页大小
  public RoleManageController() {
  }

  public void setUserManager(UserManager userManager) {
    this.userManager = userManager;
  }

  public void setRoleManager(RoleManager roleManager) {
    this.roleManager = roleManager;
  }

  /**
   <prop key="/role_manage/success_submit.role">successSubmit</prop>
   <prop key="/role_manage/list_roles.role">listRoles</prop>
   <prop key="/role_manage/list_roles_tree.role">listRolesTree</prop>
   <prop key="/role_manage/list_role_members.role">listRoleMembers</prop>
   <prop key="/role_manage/remove_role.role">removeRole</prop>
   <prop key="/role_manage/add_role_members.role">addRoleMembers</prop>
   <prop key="/role_manage/groups_tree_to_add_role.role">groupsTreeToAddRole</prop>
   <prop key="/role_manage/group_members_to_add_role.role">groupMembersToAddRole</prop>
   <prop key="/role_manage/add_users_to_role.role">addUsersToRole</prop>
   <prop key="/role_manage/remove_role_members.role">removeRoleMembers</prop>
   */
  public ModelAndView successSubmit(HttpServletRequest request,
                                    HttpServletResponse response) throws
      Exception {
    return new ModelAndView("successSubmit");
  }

  public ModelAndView listRoles(HttpServletRequest request,
                                HttpServletResponse response) throws
      Exception {
    return new ModelAndView("listRoles");
  }

  public ModelAndView listRolesTree(HttpServletRequest request,
                                    HttpServletResponse response) throws
      Exception {
    return new ModelAndView("listRolesTree", "allRoles",
                            roleManager.findAllRoles());
  }

  public ModelAndView listRoleMembers(HttpServletRequest request,
                                      HttpServletResponse response) throws
      Exception {
    HashMap model = new HashMap();
    String roleID = request.getParameter("roleID");
    Role role = roleManager.findByPrimaryKey(roleID);

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
    int roleMembersNum = role.getUsers().size();

    if (roleMembersNum % pageSize.intValue() == 0) {
      pageCount = roleMembersNum / pageSize.intValue();
    }
    else {
      pageCount = roleMembersNum / pageSize.intValue() + 1;
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

    if(log.isDebugEnabled()){
      log.debug("findRoleMembersByPage("+roleID+","+iPage+","+pageSize+")");
    }
    List aPageMembers = roleManager.findRoleMembersByPage(roleID, iPage,
        pageSize.intValue());
    model.put("aPageMembers", aPageMembers);
    model.put("roleID", roleID);
    model.put("pageSize", pageSize);
    model.put("roleMembersNum", new Integer(roleMembersNum));
    return new ModelAndView("listRoleMembers", model);
  }

  public ModelAndView removeRoleMembers(HttpServletRequest request,
                                        HttpServletResponse response) throws
      Exception {
    String roleID = request.getParameter("roleID");
    String[] toAdds = request.getParameterValues("toDo");
    for (int i = 0; i < toAdds.length; i++) {
      roleManager.removeRoleMember(roleID, toAdds[i]);
    }
    return listRoleMembers(request, response);
  }

  public ModelAndView removeRole(HttpServletRequest request,
                                 HttpServletResponse response) throws
      Exception {
    String roleID = request.getParameter("roleID");
    roleManager.removeRole(roleID);
    return listRoles(request, response);
  }

  public ModelAndView addRoleMembers(HttpServletRequest request,
                                     HttpServletResponse response) throws
      Exception {
    return new ModelAndView("addRoleMembers", "roleID",
                            request.getParameter("roleID"));
  }

  public ModelAndView groupsTreeToAddRole(HttpServletRequest request,
                                          HttpServletResponse response) throws
      Exception {
    String roleID = request.getParameter("roleID");
    HashMap map = new HashMap();
    List allGroups = groupManager.findAllGroups();
    TreeMaker tm =
        new TreeMaker(request.getContextPath() +
                      "/role_manage/group_members_to_add_role.role?roleID=" +
                      roleID + "&groupID=",
                      request.getContextPath(),
                      "tree-right",
                      null, //<---checkboxName
                      allGroups);
    map.put("groupTree", tm.makeTree());
    map.put("roleID",roleID);
    return new ModelAndView("groupsTreeToAddRole", map);
  }

  public ModelAndView groupMembersToAddRole(HttpServletRequest request,
                                            HttpServletResponse response) throws
      Exception {
    String roleID = request.getParameter("roleID");
    String groupID = request.getParameter("groupID");
    HashMap map = new HashMap();
    if (groupID != null && groupID.trim().length() > 0) {
      map.put("groupMembers", groupManager.findGroupMembers(groupID));
    }else{
      map.put("groupMembers", userManager.findUsersHaveNoGroup());
    }
    map.put("roleID", roleID);
    return new ModelAndView("groupMembersToAddRole", map);
  }

  public ModelAndView addUsersToRole(HttpServletRequest request,
                                     HttpServletResponse response) throws
      Exception {
    String roleID = request.getParameter("roleID");
    String[] toAdds = request.getParameterValues("toDo");
    for (int i = 0; i < toAdds.length; i++) {
      roleManager.addUserToRole(toAdds[i], roleID);
    }
    //ResponseWriter.openerReloadClose(response);
    return listRoleMembers(request, response);
  }

  public void setGroupManager(org.powerstone.ca.service.GroupManager
                              groupManager) {
    this.groupManager = groupManager;
  }
  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

}
