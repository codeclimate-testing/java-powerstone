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

import org.powerstone.ca.dao.GroupDAO;
import java.util.List;
import org.powerstone.ca.model.Group;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.HibernateException;
import org.powerstone.ca.model.GroupRight;
import java.util.ArrayList;

public class GroupDAOImpl
    extends HibernateDaoSupport
    implements GroupDAO {
  private static Log log = LogFactory.getLog(GroupDAOImpl.class);
  public List findAllGroups() {
    return getHibernateTemplate().findByNamedQuery("AllGroups");
  }

  public void saveGroup(Group group) {
    getHibernateTemplate().saveOrUpdate(group);
    getHibernateTemplate().flush();
  }

  public Group findByPrimaryKey(Long groupID) {
    return (Group) getHibernateTemplate().load(Group.class, groupID);
  }

  public List findGroupMembersByPage(Long groupID, int pageNum, int pageSize) {
    try {
      Query q = getSession().getNamedQuery("GroupMembersByPage");
      q.setLong(0, groupID.longValue());
      q.setMaxResults(pageSize);
      q.setFirstResult( (pageNum - 1) * pageSize);
      return q.list();
    }
    catch (HibernateException ex) {
      log.info(ex);
    }
    return new ArrayList();
  }

  public void removeGroup(Long groupID) {
    Group group = findByPrimaryKey(groupID);
    getHibernateTemplate().delete(group);
    getHibernateTemplate().flush();
  }

  public GroupRight findRightsByCombineKeys(Long groupID, Long resourceID,
                                            String privilege) {
    List result = getHibernateTemplate().findByNamedQuery(
        "GroupRightsByCombineKeys",
        new Object[] {groupID, resourceID, privilege});
    if (result.size() == 0) {
      return null;
    }
    return (GroupRight) result.get(0);
  }

  public List findGroupsOfResource(Long resourceID) {
    return getHibernateTemplate().findByNamedQuery("GroupsOfResource",
        resourceID);
  }
}
