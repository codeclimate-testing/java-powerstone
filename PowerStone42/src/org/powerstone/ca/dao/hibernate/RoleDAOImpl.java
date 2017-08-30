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

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.powerstone.ca.dao.RoleDAO;
import java.util.List;
import org.powerstone.ca.model.Role;
import org.powerstone.ca.model.RoleRight;
import org.hibernate.Query;
import org.hibernate.HibernateException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RoleDAOImpl
    extends HibernateDaoSupport
    implements RoleDAO {
  private static Log log = LogFactory.getLog(RoleDAOImpl.class);
  public List findAllRoles() {
    return getHibernateTemplate().findByNamedQuery("AllRoles");
  }

  public void saveRole(Role role) {
    getHibernateTemplate().saveOrUpdate(role);
    getHibernateTemplate().flush();
  }

  public Role findByPrimaryKey(Long roleID) {
    return (Role) getHibernateTemplate().load(Role.class, roleID);
  }

  public List findRoleMembersByPage(Long roleID, int pageNum, int pageSize) {
    try {
      Query q = getSession().getNamedQuery("RoleMembersByPage");
      q.setLong(0, roleID.longValue());
      q.setMaxResults(pageSize);
      q.setFirstResult( (pageNum - 1) * pageSize);
      return q.list();
    }
    catch (HibernateException ex) {
      log.info(ex);
    }
    return null;
  }

  public void removeRole(Long roleID) {
    Role role = findByPrimaryKey(roleID);
    getHibernateTemplate().delete(role);
    getHibernateTemplate().flush();
  }

  public RoleRight findRightsByCombineKeys(Long roleID, Long resourceID,
                                           String privilege) {
    List result = getHibernateTemplate().findByNamedQuery(
        "RoleRightsByCombineKeys",
        new Object[] {roleID, resourceID, privilege});
    if (result.size() == 0) {
      return null;
    }
    return (RoleRight) result.get(0);
  }

  public List findRolesOfResource(Long resourceID) {
    return getHibernateTemplate().findByNamedQuery("RolesOfResource",
        resourceID);
  }
}
