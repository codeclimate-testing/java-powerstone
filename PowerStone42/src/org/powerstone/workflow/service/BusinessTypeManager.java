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
package org.powerstone.workflow.service;

import java.util.List;
import org.powerstone.workflow.model.BusinessType;
import org.powerstone.workflow.dao.BusinessTypeDAO;

public interface BusinessTypeManager {

  public void setBusinessTypeDAO(BusinessTypeDAO dao);

  public List getAllBusinessTypes();

  public BusinessType getBusinessType(String businessTypeID);

  public BusinessType createBusinessType(BusinessType businessType);

  public void removeBusinessType(String businessTypeID);

  public void renameBusinessType(String businessTypeID,String businessTypeName);

}
