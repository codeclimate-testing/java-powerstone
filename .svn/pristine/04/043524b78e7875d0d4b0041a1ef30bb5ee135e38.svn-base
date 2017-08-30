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
package org.powerstone.workflow.dao.hibernate;

import org.powerstone.workflow.dao.BusinessTypeDAO;
import java.util.List;
import org.powerstone.workflow.model.BusinessType;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * <p>Title: PowerStone</p>
 */
public class BusinessTypeDAOImpl
    extends HibernateDaoSupport
    implements BusinessTypeDAO {
  public List getAllBusinessTypes() {
    return getHibernateTemplate().findByNamedQuery("AllBusinessTypes");
  }

  public BusinessType getBusinessType(Long businessTypeID) {
    return (BusinessType) getHibernateTemplate().load(BusinessType.class,
        businessTypeID);
  }

  public void saveBusinessType(BusinessType businessType) {
    getHibernateTemplate().saveOrUpdate(businessType);
    getHibernateTemplate().flush();
  }

  public void removeBusinessType(Long businessTypeID) {
    Object businessType = getBusinessType(businessTypeID);
    getHibernateTemplate().delete(businessType);
    getHibernateTemplate().flush();
  }
}
