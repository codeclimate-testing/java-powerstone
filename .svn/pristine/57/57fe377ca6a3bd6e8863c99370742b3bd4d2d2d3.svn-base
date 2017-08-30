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
package org.powerstone.workflow.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.view.RedirectView;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.powerstone.workflow.service.FlowMetaManager;
import org.powerstone.workflow.service.BusinessTypeManager;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import org.powerstone.workflow.model.WorkflowMeta;
import org.powerstone.util.ResponseWriter;

public class BusinessTypeController extends MultiActionController {
	private static Log log =
		LogFactory.getLog(BusinessTypeController.class);

	private FlowMetaManager flowMetaManager;

	private BusinessTypeManager businessTypeManager;

	public ModelAndView main(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("main");
	}

	public ModelAndView successSubmit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("successSubmit");
	}

	public ModelAndView listAllBusinessTypes(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Collection allBusinessTypes = businessTypeManager.getAllBusinessTypes();
		Collection flowMetasNoBusinessType = flowMetaManager
				.getWorkflowMetasNoBusinessType();
		Map map = new HashMap();
		map.put("allBusinessTypes", allBusinessTypes);
		if (flowMetasNoBusinessType.size() > 0) {
			map.put("flowMetasNoBusinessType", flowMetasNoBusinessType);
		}
		return new ModelAndView("listAllBusinessTypes", map);
	}

	public ModelAndView listBusinessTypesToJoin(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Collection allBusinessTypes = businessTypeManager.getAllBusinessTypes();
		String flowMetaID = request.getParameter("flowMetaID");
		WorkflowMeta wm = flowMetaManager.getWorkflowMeta(flowMetaID);

		Map map = new HashMap();
		map.put("allBusinessTypes", allBusinessTypes);
		map.put("flowMeta", wm);

		return new ModelAndView("listBusinessTypesToJoin", map);
	}

	public void setBusinessTypeManager(
			org.powerstone.workflow.service.BusinessTypeManager businessTypeManager) {
		this.businessTypeManager = businessTypeManager;
	}

	public void setFlowMetaManager(FlowMetaManager flowMetaManager) {
		this.flowMetaManager = flowMetaManager;
	}
}
