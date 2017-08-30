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

import org.powerstone.ca.dao.ResourceDAO;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import java.util.List;
import org.powerstone.ca.model.Resource;
import org.powerstone.ca.model.WebModule;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ResourceDAOImpl
    extends HibernateDaoSupport
    implements ResourceDAO {
  private static Log log = LogFactory.getLog(ResourceDAOImpl.class);
  public List findAllResources() {
    return getHibernateTemplate().findByNamedQuery("AllResources");
  }

  public List findAllWebModules() {
    return getHibernateTemplate().findByNamedQuery("AllWebModules");
  }

  public Resource findResourceByPrimaryKey(Long rsID) {
    return (Resource) getHibernateTemplate().load(Resource.class, rsID);
  }

  public Resource findResourceByResourceID(String resourceID) {
    List result = getHibernateTemplate().findByNamedQuery(
        "ResourceByResourceID",
        resourceID);
    if (result.size() == 0) {
      return null;
    }
    return (Resource) result.get(0);
  }

  public Resource findResourceByName(String resourceName) {
    List result = getHibernateTemplate().findByNamedQuery("ResourceByName",
        resourceName);
    if (result.size() == 0) {
      return null;
    }
    return (Resource) result.get(0);
  }

  public WebModule findWebModuleByPrimaryKey(Long webModuleID) {
    return (WebModule) getHibernateTemplate().load(WebModule.class, webModuleID);
  }

  public void removeWebModule(Long webModuleID) {
    WebModule webModule = findWebModuleByPrimaryKey(webModuleID);
    getHibernateTemplate().delete(webModule);
    getHibernateTemplate().flush();
  }

  public void saveResource(Resource resource) {
    getHibernateTemplate().saveOrUpdate(resource);
    getHibernateTemplate().flush();
  }

  public void saveWebModule(WebModule webModule) {
    getHibernateTemplate().saveOrUpdate(webModule);
    getHibernateTemplate().flush();
  }
}
