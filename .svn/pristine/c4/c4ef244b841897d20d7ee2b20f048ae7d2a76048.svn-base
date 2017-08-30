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
import org.powerstone.workflow.service.WorkflowDriverManager;

import java.util.Map;
import java.util.HashMap;

public class WorkflowDriverController extends MultiActionController {
    private static Log log = LogFactory.getLog(WorkflowDriverController.class);
    private WorkflowDriverManager workflowDriverManager;

    public ModelAndView homePage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("main", "allContextPath", workflowDriverManager.getAllDriverContextPath());
    }

    public ModelAndView listDrivers(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String contextPath = request.getParameter("contextPath");
        if (contextPath != null) {
            Map model = new HashMap();
            model.put("allDrivers", workflowDriverManager.getFlowDriversByContextPath(contextPath));
            model.put("contextPath", contextPath);
            return new ModelAndView("listDrivers", model);
        } else {
            return new ModelAndView("listDrivers", "allDrivers", workflowDriverManager.getAllWorkflowDrivers());
        }
    }

    public ModelAndView listDriversToDeploy(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String contextPath = request.getParameter("contextPath");
        String flowDeployID = request.getParameter("flowDeployID");
        String flowNodeID = request.getParameter("flowNodeID");
        String currentdriverID = request.getParameter("currentdriverID");
        Map model = new HashMap();
        model.put("flowDeployID", flowDeployID);
        model.put("flowNodeID", flowNodeID);
        model.put("currentdriverID", currentdriverID);
        if (contextPath != null) {
            model.put("allDrivers", workflowDriverManager.getFlowDriversByContextPath(contextPath));
        } else {
            model.put("allDrivers", workflowDriverManager.getAllWorkflowDrivers());
        }
        return new ModelAndView("listDriversToDeploy", model);
    }

    public ModelAndView homePageToDeploy(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String flowDeployID = request.getParameter("flowDeployID");
        String flowNodeID = request.getParameter("flowNodeID");
        String currentdriverID = request.getParameter("currentdriverID");
        Map model = new HashMap();
        model.put("flowDeployID", flowDeployID);
        model.put("flowNodeID", flowNodeID);
        model.put("currentdriverID", currentdriverID);
        model.put("allContextPath", workflowDriverManager.getAllDriverContextPath());

        return new ModelAndView("mainToDeploy", model);
    }

    public ModelAndView seeDriverDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String driverID = request.getParameter("driverID");
        if (driverID != null) {
            return new ModelAndView("seeDriverDetail", "theDriver", workflowDriverManager.getWorkflowDriver(driverID));
        } else {
            return null;
        }
    }

    public ModelAndView removeDriver(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String driverToRemove[] = request.getParameterValues("driverToRemove");
        if (driverToRemove != null && driverToRemove.length > 0) {
            for (int i = 0; i < driverToRemove.length; i++) {
                workflowDriverManager.removeWorkflowDriver(driverToRemove[i]);
            }
        }
        //打印刷新父窗口的代码到客户端
        //    ResponseWriter.parentReload(response);
        //    return null;
        return new ModelAndView(new RedirectView(request.getContextPath() + "/wf/home.fd"));
    }

    public ModelAndView removeDriverParamEnume(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String driverID = request.getParameter("driverID");
        String paramEnumeToDel[] = request.getParameterValues("paramEnumeToDel");
        if (paramEnumeToDel != null && paramEnumeToDel.length > 0) {
            for (int i = 0; i < paramEnumeToDel.length; i++) {
                workflowDriverManager.removeDriverOutputParamEnume(paramEnumeToDel[i]);
            }
        }
        return new ModelAndView(new RedirectView(request.getContextPath() + "/wf/see_driver_detail.fd?driverID=" + driverID));
    }

    public ModelAndView removeDriverInputParam(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String driverID = request.getParameter("driverID");
        String paramID = request.getParameter("paramID");
        workflowDriverManager.removeDriverInputParam(paramID);
        return new ModelAndView(new RedirectView(request.getContextPath() + "/wf/see_driver_detail.fd?driverID=" + driverID));
    }

    public ModelAndView removeDriverOutputParam(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String driverID = request.getParameter("driverID");
        String paramID = request.getParameter("paramID");
        workflowDriverManager.removeDriverOutputParam(paramID);
        return new ModelAndView(new RedirectView(request.getContextPath() + "/wf/see_driver_detail.fd?driverID=" + driverID));
    }

    //------------------------------------------------------------------------------
    public void setWorkflowDriverManager(WorkflowDriverManager workflowDriverManager) {
        this.workflowDriverManager = workflowDriverManager;
    }
}