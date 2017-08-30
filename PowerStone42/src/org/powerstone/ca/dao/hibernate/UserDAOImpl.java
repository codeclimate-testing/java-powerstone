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
package org.powerstone.ca.dao.hibernate;

import org.powerstone.ca.dao.UserDAO;
import org.powerstone.ca.model.User;
import org.powerstone.ca.model.UserRight;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import java.util.List;

public class UserDAOImpl
    extends HibernateDaoSupport
    implements UserDAO {

  public void saveUser(User user) {
    this.getHibernateTemplate().saveOrUpdate(user);
  }

  public List findAllUsers() {
    return getHibernateTemplate().findByNamedQuery("AllUsers");
  }

  public List findUsersByState(String state) {
    return getHibernateTemplate().findByNamedQuery("UsersByState", state);
  }

  public List findUsersHaveNoGroup() {
    return getHibernateTemplate().findByNamedQuery("UsersHaveNoGroup");
  }

  public User findByPrimaryKey(Long userID) {
    return (User) getHibernateTemplate().load(User.class, userID);
  }

  public User findByUserName(String userName) {
    List result = getHibernateTemplate().findByNamedQuery("ByUserName",
        userName);
    if (result.size() == 0) {
      return null;
    }
    return (User) result.get(0);
  }

  public User findUserByEmail(String email) {
    List result = getHibernateTemplate().findByNamedQuery("ByEmail",
        email);
    if (result.size() == 0) {
      return null;
    }
    return (User) result.get(0);
  }

  public List findUsersOfResource(Long resourceID) {
    return getHibernateTemplate().findByNamedQuery("UsersOfResource",
        resourceID);
  }

  public UserRight findRightsByCombineKeys(Long userID, Long resourceID,
                                           String privilege) {
    List result = getHibernateTemplate().findByNamedQuery(
        "UserRightsByCombineKeys",
        new Object[] {userID, resourceID, privilege});
    if (result.size() == 0) {
      return null;
    }
    return (UserRight) result.get(0);
  }
}
