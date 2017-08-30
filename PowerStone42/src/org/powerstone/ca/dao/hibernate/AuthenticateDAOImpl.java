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

import org.powerstone.ca.dao.AuthenticateDAO;
import org.powerstone.ca.model.UserToken;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import java.util.List;

public class AuthenticateDAOImpl
    extends HibernateDaoSupport
    implements AuthenticateDAO {
  public void saveUserToken(UserToken ut) {
    getHibernateTemplate().saveOrUpdate(ut);
    getHibernateTemplate().flush();
  }

  public UserToken findByTokenAndIp(String token, String ipAdd) {
    List result = getHibernateTemplate().findByNamedQuery(
        "ByTokenAndIp",
        new Object[] {token, ipAdd});
    if (result.size() == 0) {
      return null;
    }
    return (UserToken) result.get(0);
  }

  public void clearDataOverdue(String timeOverdue) {
    List target = getHibernateTemplate().findByNamedQuery(
        "DataOverdue", timeOverdue);
    getHibernateTemplate().deleteAll(target);
    getHibernateTemplate().flush();
  }

  public void removeUserData(Long userID, String ipAdd) {
    List target = getHibernateTemplate().findByNamedQuery("UserData",
        new Object[] {userID, ipAdd});
    getHibernateTemplate().deleteAll(target);
    getHibernateTemplate().flush();
  }
}
