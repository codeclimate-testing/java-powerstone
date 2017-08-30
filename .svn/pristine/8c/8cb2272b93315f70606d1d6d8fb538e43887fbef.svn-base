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
package org.powerstone.workflow.service.impl;

import org.powerstone.workflow.service.BusinessTypeManager;
import org.powerstone.workflow.dao.BusinessTypeDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.List;
import org.powerstone.workflow.model.BusinessType;

public class BusinessTypeManagerImpl implements BusinessTypeManager {
	private static Log log = LogFactory.getLog(BusinessTypeManagerImpl.class);

	private BusinessTypeDAO btDAO;

	public void setBusinessTypeDAO(BusinessTypeDAO dao) {
		this.btDAO = dao;
	}

	public List getAllBusinessTypes() {
		return btDAO.getAllBusinessTypes();
	}

	public BusinessType getBusinessType(String businessTypeID) {
		return btDAO.getBusinessType(new Long(businessTypeID));
	}

	public BusinessType createBusinessType(BusinessType businessType) {
		btDAO.saveBusinessType(businessType);
		return businessType;
	}

	public void removeBusinessType(String businessTypeID) {
		// BusinessType bt=this.getBusinessType(businessTypeID);
		// bt.removeAllWorkflowMetas();

		btDAO.removeBusinessType(new Long(businessTypeID));
	}

	public void renameBusinessType(String businessTypeID,
			String businessTypeName) {
		BusinessType bt = btDAO.getBusinessType(new Long(businessTypeID));
		bt.setTypeName(businessTypeName);
		btDAO.saveBusinessType(bt);
	}

}
