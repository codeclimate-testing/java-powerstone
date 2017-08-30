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
package org.powerstone.ca;

import java.util.*;
import javax.servlet.http.*;

import org.powerstone.ca.model.*;

public interface CADelegater {
  public String getRemoteUser(HttpServletRequest request);

  public boolean authenticate(String userName, String pass,
                              HttpServletRequest request,
                              HttpServletResponse response);

  public User findUserByUserID(String userID);

  //public User findUserByUserName(String userName);

  public boolean hasRightToDo(String userID, String actionPath,
                              String webModuleID);

  public List findAllRoles();

  public List findAllGroups();

  public List findAllUsers();

  public List findGroupMembers(String groupID);

  /**
   * findRoleByRoleID
   *
   * @param roleID String
   * @return Role
   */
  public Role findRoleByRoleID(String roleID);
}
