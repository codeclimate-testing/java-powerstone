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
import org.acegisecurity.Authentication;
import org.acegisecurity.providers.dao.cache.EhCacheBasedUserCache;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.powerstone.acegi.cache.AcegiCacheManager;
import org.powerstone.acegi.cache.EhCacheBasedResourceCache;
import org.powerstone.acegi.util.AuthenticationUtil;
import org.powerstone.ca.service.ResourceManager;
import org.powerstone.ca.service.RoleManager;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import org.powerstone.ca.model.GivenRight;
import org.powerstone.ca.service.UserManager;
import org.powerstone.ca.model.User;
import org.powerstone.ca.model.Role;
import org.springframework.web.servlet.view.RedirectView;

public class PrivilegeManageController extends MultiActionController {
    private static final Log log = LogFactory.getLog(PrivilegeManageController.class);
    private ResourceManager resourceManager;
    private RoleManager roleManager;
    private UserManager userManager;

    private AcegiCacheManager acegiCacheManager;
    AuthenticationUtil authenticationUtil;

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public void setResourceManager(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    public void setRoleManager(RoleManager roleManager) {
        this.roleManager = roleManager;
    }

    /**
     <prop key="/priv_manage/list_roles.priv">listRoles</prop>
     <prop key="/priv_manage/show_role_privileges.priv">showRolePrivileges</prop>
     <prop key="/priv_manage/update_role_privileges.priv">updateRolePrivileges</prop>
     <prop key="/priv_manage/show_user_privileges.priv">showUserPrivileges</prop>
     <prop key="/priv_manage/update_user_privileges.priv">updateUserPrivileges</prop>
     */
    public ModelAndView listRoles(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("listRoles", "allRoles", roleManager.findAllRoles());
    }

    public ModelAndView showRolePrivileges(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String roleID = request.getParameter("roleID");
        HashMap map = new HashMap();
        List roleRights = roleManager.findByPrimaryKey(roleID).getRights();
        HashMap roleRightsMap = new HashMap();
        for (Iterator it = roleRights.iterator(); it.hasNext();) {
            GivenRight gr = (GivenRight) it.next();
            roleRightsMap.put(gr.getResource().getId(), "##");
        }

        map.put("roleRightsMap", roleRightsMap);
        map.put("allModules", resourceManager.getAllModules());
        map.put("roleID", roleID);
        return new ModelAndView("showRolePrivileges", map);
    }

    public ModelAndView updateRolePrivileges(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String roleID = request.getParameter("roleID");
        String resourceIDs[] = request.getParameterValues("resourceIDs");
        roleManager.updateRoleRights(roleID, resourceIDs);
        return new ModelAndView("successSubmit");
    }

    public ModelAndView showUserPrivileges(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userID = request.getParameter("userID");
        HashMap map = new HashMap();
        User user = userManager.findUser(userID);
        HashMap userRightsMap = new HashMap();
        for (Iterator it = user.getRights().iterator(); it.hasNext();) {
            GivenRight gr = (GivenRight) it.next();
            userRightsMap.put(gr.getResource().getId(), "##");
        }

        HashMap roleRightsMap = new HashMap();
        for (Iterator it = user.getRoles().iterator(); it.hasNext();) {
            Role role = (Role) it.next();
            for (Iterator itr = role.getRights().iterator(); itr.hasNext();) {
                GivenRight gr = (GivenRight) itr.next();
                roleRightsMap.put(gr.getResource().getId(), "##");
            }
        }

        map.put("userRightsMap", userRightsMap);
        map.put("roleRightsMap", roleRightsMap);
        map.put("allModules", resourceManager.getAllModules());
        map.put("userID", userID);
        map.put("groupID", request.getParameter("groupID"));
        map.put("pageTo", request.getParameter("pageTo"));
        return new ModelAndView("showUserPrivileges", map);
    }

    public ModelAndView updateUserPrivileges(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userID = request.getParameter("userID");
        String resourceIDs[] = request.getParameterValues("resourceIDs");
        userManager.updateUserRights(userID, resourceIDs);

        return new ModelAndView(new RedirectView(request.getContextPath() + "/user_group/edit_user.ca?id=" + request.getParameter("userID") + "&groupID="
                + request.getParameter("groupID") + "&pageTo=" + request.getParameter("pageTo")));
    }

    public ModelAndView refresh(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Authentication a=authenticationUtil.getCurrentUserAuthentication();
        System.out.println(">>>>>>>>>>>"+a);
        acegiCacheManager.init();

        return new ModelAndView(new RedirectView(request.getContextPath() + "/"));
    }

    public void setAcegiCacheManager(AcegiCacheManager acegiCacheManager) {
        this.acegiCacheManager = acegiCacheManager;
    }

    public void setAuthenticationUtil(AuthenticationUtil authenticationUtil) {
        this.authenticationUtil = authenticationUtil;
    }

}