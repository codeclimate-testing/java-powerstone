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

import org.powerstone.workflow.service.FlowDeployManager;
import org.powerstone.workflow.service.FlowMetaManager;
import java.util.Map;
import java.util.HashMap;
import org.powerstone.workflow.model.WorkflowMeta;
import org.powerstone.workflow.model.FlowDeploy;
import java.util.GregorianCalendar;
import java.util.Enumeration;

public class FlowDeployController
    extends MultiActionController {
  private static Log log = LogFactory.getLog(FlowDeployController.class);
  private FlowDeployManager flowDeployManager;
  private FlowMetaManager flowMetaManager;

  public ModelAndView seeFlowDeploy(HttpServletRequest request,
                                    HttpServletResponse response) throws
      Exception {
    String flowDeployID = request.getParameter("flowDeployID");

    WorkflowMeta workflowMeta =
        flowMetaManager.getWorkflowMetaWithFile(flowDeployManager.getFlowDeploy(
        flowDeployID).getWorkflowMeta().getFlowMetaID().toString());
    Map model = new HashMap();
    model.put("workflowMeta", workflowMeta);
    model.put("flowDeployID", flowDeployID);

    return new ModelAndView("listFlowNodes", model);
  }

  public ModelAndView seeFlowNodeBinding(HttpServletRequest request,
                                         HttpServletResponse response) throws
      Exception {
    String flowDeployID = request.getParameter("flowDeployID");
    String flowNodeID = request.getParameter("flowNodeID");
    if (flowDeployID == null) {
      return null;
    }
    FlowDeploy flowDeploy = flowDeployManager.getFlowDeploy(flowDeployID);
    WorkflowMeta workflowMeta =
        flowMetaManager.getWorkflowMetaWithFile(flowDeploy.getWorkflowMeta().
                                                getFlowMetaID().toString());
    Map model = new HashMap();
    model.put("flowNode", workflowMeta.findWorkflowNodeByID(flowNodeID));
    model.put("flowNodeBinding",
              flowDeploy.getFlowNodeBindingByNodeID(flowNodeID));
    model.put("dataFields", workflowMeta.getDataFields());
    model.put("flowDeploy", flowDeploy);

    return new ModelAndView("seeFlowNodeBinding", model);
  }

  public ModelAndView updateFlowNodeBinding(HttpServletRequest request,
                                            HttpServletResponse response) throws
      Exception {
    String flowDeployID = request.getParameter("flowDeployID");
    String flowNodeID = request.getParameter("flowNodeID");
    String flowDriverID = request.getParameter("flowDriverID");
    this.flowDeployManager.updateFlowNodeBinding(flowDeployID,
                                                 flowNodeID,
                                                 flowDriverID);

    return this.seeFlowNodeBinding(request, response);
  }

  public ModelAndView updateFlowNodeParam(HttpServletRequest request,
                                          HttpServletResponse response) throws
      Exception {
    String flowNodeBindingID = request.getParameter("flowNodeBindingID");
    Enumeration params = request.getParameterNames();

    if (flowNodeBindingID != null && params != null) {
      HashMap inputParamMap = new HashMap();
      HashMap outputParamMap = new HashMap();
      HashMap outputParamEnumeMap = new HashMap();
      while (params.hasMoreElements()) {
        String paramName = (String) params.nextElement();
        if (paramName.startsWith("IN_")) {
          inputParamMap.put(paramName.substring(3),
                            request.getParameter(paramName));
        }
        else
        if (paramName.startsWith("OUT_")
            && paramName.indexOf("_ENUME_") < 0) {
          outputParamMap.put(paramName.substring(4),
                             request.getParameter(paramName));
        }
      }
      if (outputParamMap.size() > 0) {
        params = request.getParameterNames();
        while (params.hasMoreElements()) {
          String paramName = (String) params.nextElement();
          if (paramName.startsWith("OUT_")
              && paramName.indexOf("_ENUME_") > 0) {
            outputParamEnumeMap.put(
                paramName.substring(paramName.indexOf("_ENUME_") + 7),
                request.getParameter(paramName)
                );
          }
        }
      }
      flowDeployManager.updateFlowNodeParamBinding(flowNodeBindingID,
          inputParamMap, outputParamMap, outputParamEnumeMap);
    }

    return this.seeFlowNodeBinding(request, response);
  }

  public ModelAndView newFlowDeploy(HttpServletRequest request,
                                    HttpServletResponse response) throws
      Exception {
    String workflowMetaID = request.getParameter("workflowMetaID");
    return new ModelAndView("newFlowDeploy", "workflowMetaID", workflowMetaID);
  }

  public ModelAndView createFlowDeploy(HttpServletRequest request,
                                       HttpServletResponse response) throws
      Exception {
    String workflowMetaID = request.getParameter("workflowMetaID");
    String deployName = request.getParameter("deployName");
    String memo = request.getParameter("memo");
    FlowDeploy fd = new FlowDeploy();
    fd.setCreateTime( (new GregorianCalendar()).getTime().toString());
    fd.setCurrentState(FlowDeploy.DEPLOY_STATE_PREPARING);
    fd.setFlowDeployName(deployName);
    fd.setMemo(memo);

    flowMetaManager.addFlowDeploy(workflowMetaID, fd);

    //打印关闭窗口的代码到客户端
    try {
      java.io.PrintWriter out = (java.io.PrintWriter) response.getWriter();
      out.print("<script language=\"JavaScript\">");
      out.print("window.returnValue='ok';window.close();");
      out.print("</script>");
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public ModelAndView removeFlowDeploy(HttpServletRequest request,
                                       HttpServletResponse response) throws
      Exception {
    String flowDeployID = request.getParameter("flowDeployID");
    FlowDeploy flowDeploy = flowDeployManager.getFlowDeploy(flowDeployID);
    String flowMetaID = flowDeploy.getWorkflowMeta().getFlowMetaID().toString();
//    if (flowDeploy.getFlowProcs().size() > 0) {
//      return new ModelAndView("error", "message", ExceptionMessage.ERROR_FLOWDEPLOY_WITH_PROCS);
//    }
//    if (flowDeploy.isReady()) {
//      return new ModelAndView("error", "message", ExceptionMessage.ERROR_FLOWDEPLOY_READY);
//    }

    flowDeployManager.removeFlowDeploy(flowDeployID);
    return new ModelAndView(new RedirectView(request.getContextPath() +
                                             "/wf/see_flow_meta.fm?flowMetaID=" +
                                             flowMetaID));
  }

  public ModelAndView enableFlowDeploy(HttpServletRequest request,
                                       HttpServletResponse response) throws
      Exception {
    String flowDeployID = request.getParameter("flowDeployID");
    FlowDeploy flowDeploy = flowDeployManager.enableFlowDeploy(flowDeployID);

    return new ModelAndView(new RedirectView(request.getContextPath() +
                                             "/wf/see_flow_meta.fm?flowMetaID=" +
                                             flowDeploy.getWorkflowMeta().
                                             getFlowMetaID()));
  }

  public ModelAndView disableFlowDeploy(HttpServletRequest request,
                                        HttpServletResponse response) throws
      Exception {
    String flowDeployID = request.getParameter("flowDeployID");
    FlowDeploy flowDeploy = flowDeployManager.disableFlowDeploy(flowDeployID);

    return new ModelAndView(new RedirectView(request.getContextPath() +
                                             "/wf/see_flow_meta.fm?flowMetaID=" +
                                             flowDeploy.getWorkflowMeta().
                                             getFlowMetaID()));
  }
//------------------------------------------------------------------------------
  public void setFlowDeployManager(FlowDeployManager flowDeployManager) {
    this.flowDeployManager = flowDeployManager;
  }

  public void setFlowMetaManager(FlowMetaManager flowMetaManager) {
    this.flowMetaManager = flowMetaManager;
  }
}
