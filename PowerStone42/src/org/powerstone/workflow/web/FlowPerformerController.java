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
import org.powerstone.ca.CADelegater;
import org.powerstone.workflow.model.FlowDeploy;
import org.powerstone.workflow.model.FlowNodeBinding;
import java.util.Map;
import java.util.HashMap;

import org.powerstone.util.TreeMaker;
import org.powerstone.util.ResponseWriter;
import org.powerstone.workflow.model.WorkflowMeta;
import org.powerstone.workflow.service.FlowMetaManager;
import java.util.List;

public class FlowPerformerController
    extends MultiActionController {
  private static Log log = LogFactory.getLog(FlowPerformerController.class);
  private FlowDeployManager flowDeployManager;
  private CADelegater caDelegater;
  private FlowMetaManager flowMetaManager;

  public void setFlowDeployManager(FlowDeployManager flowDeployManager) {
    this.flowDeployManager = flowDeployManager;
  }

  public void setCaDelegater(CADelegater caDelegater) {
    this.caDelegater = caDelegater;
  }

  public void setFlowMetaManager(FlowMetaManager flowMetaManager) {
    this.flowMetaManager = flowMetaManager;
  }

//------------------------------------------------------------------------------
  public ModelAndView seeNodePerformer(HttpServletRequest request,
                                       HttpServletResponse response) throws
      Exception {
    String flowDeployID = request.getParameter("flowDeployID");
    String flowNodeID = request.getParameter("flowNodeID");
    String nodeBindingID = request.getParameter("nodeBindingID");
    if (nodeBindingID != null) {
      FlowNodeBinding flowNodeBinding =
          flowDeployManager.getFlowNodeBinding(nodeBindingID);
      flowDeployID = flowNodeBinding.getFlowDeploy().getFlowDeployID().toString();
      flowNodeID = flowNodeBinding.getFlowNodeID();
    }
    if (flowDeployID != null) {
      FlowDeploy flowDeploy = flowDeployManager.getFlowDeploy(flowDeployID);
      if (flowDeploy.getFlowNodeBindingByNodeID(flowNodeID) != null) {
        FlowNodeBinding nodeBinding =
            flowDeploy.getFlowNodeBindingByNodeID(flowNodeID);
        WorkflowMeta workflowMeta =
            flowMetaManager.getWorkflowMetaWithFile(
            nodeBinding.getFlowDeploy().getWorkflowMeta().getFlowMetaID().
            toString());

        Map model = new HashMap();
        model.put("nodeBinding", nodeBinding);
        model.put("workflowMeta", workflowMeta);
        model.put("allUsers",
                  flowDeployManager.findUsersByNodeBinding(nodeBinding.
            getNodeBindingID().toString()));
        model.put("allRoles",
                  flowDeployManager.findRolesByNodeBinding(nodeBinding.
            getNodeBindingID().toString()));

        return new ModelAndView("configNodePerformer", model);
      }
    }
    return new ModelAndView("configNodePerformer");
  }

  public ModelAndView removeUserPerformer(HttpServletRequest request,
                                          HttpServletResponse response) throws
      Exception {
    String nodeBindingID = request.getParameter("nodeBindingID");
    FlowNodeBinding flowNodeBinding =
        flowDeployManager.getFlowNodeBinding(nodeBindingID);

    String[] users = request.getParameterValues("user_items");
    if (users != null && users.length > 0) {
      for (int i = 0; i < users.length; i++) {
        flowDeployManager.removeUserPerformer(users[i], flowNodeBinding.getNodeBindingID().toString());
      }
    }
    return new ModelAndView(new RedirectView(request.getContextPath() +
                                             "/wf/see_node_performer.pfm?nodeBindingID=" +
                                             nodeBindingID));

    //return seeNodePerformer(request, response);
  }

  public ModelAndView removeRolePerformer(HttpServletRequest request,
                                          HttpServletResponse response) throws
      Exception {
    return new ModelAndView("main");
  }

  public ModelAndView userOrganize(HttpServletRequest request,
                                   HttpServletResponse response) throws
      Exception {
    String nodeBindingID = request.getParameter("nodeBindingID");
    return new ModelAndView("userOrganize", "nodeBindingID", nodeBindingID);
  }

  public ModelAndView updatePerformerLogic(HttpServletRequest request,
                                           HttpServletResponse response) throws
      Exception {
    String nodeBindingID = request.getParameter("nodeBindingID");
    String performerLogic = request.getParameter("performerLogic");

    if (performerLogic != null) {
      if (performerLogic.equals("static")) {
        flowDeployManager.enableStatic(nodeBindingID);
      }
      else if (performerLogic.equals("founder")) {
        flowDeployManager.enableFounder(nodeBindingID);
      }
      else if (performerLogic.equals("otherPerformer")) {
        flowDeployManager.updateOtherPerformer(nodeBindingID,
                                               request.getParameter(
            "other_performer_detail"));
      }
      else if (performerLogic.equals("assign")) {
        flowDeployManager.updateAssign(nodeBindingID,
                                       request.getParameter(
            "assign_detail"));
      }
      else if (performerLogic.equals("variable")) {
        flowDeployManager.updateVariable(nodeBindingID,
                                         request.getParameter(
            "variable_detail"));
      }
      else if (performerLogic.equals("rule")) {
        flowDeployManager.updateRule(nodeBindingID,
                                     request.getParameter(
            "rule_detail"));
      }
    }
    return new ModelAndView(new RedirectView(request.getContextPath() +
                                             "/wf/see_node_performer.pfm?nodeBindingID=" +
                                             nodeBindingID));
    //return seeNodePerformer(request, response);
  }

  public ModelAndView listUserOrganize(HttpServletRequest request,
                                       HttpServletResponse response) throws
      Exception {
    String nodeBindingID = request.getParameter("nodeBindingID");
    List groups = caDelegater.findAllGroups();
    if (groups != null && groups.size() > 0) {
//      ArrayList groupTreeNodes = new ArrayList();
//      for (Iterator it = groups.iterator(); it.hasNext(); ) {
//        Group g = (Group) it.next();
//        groupTreeNodes.add(new GroupTreeNode(g));
//      }

      TreeMaker tm =
          new TreeMaker(request.getContextPath() +
                        "/wf/list_members_to_add_performer.pfm?nodeBindingID=" +
                        nodeBindingID + "&groupID=",
                        request.getContextPath(),
                        "Sys_Content",
                        null, //<---checkboxName
                        groups);
      Map model = new HashMap();
      model.put("groupTree", tm.makeTree());
      return new ModelAndView("listUserOrganize", model);
    }

    return new ModelAndView("listUserOrganize");
  }

  public ModelAndView addUserPerformer(HttpServletRequest request,
                                       HttpServletResponse response) throws
      Exception {
    String nodeBindingID = request.getParameter("nodeBindingID");
    FlowNodeBinding flowNodeBinding =
        flowDeployManager.getFlowNodeBinding(nodeBindingID);
    String[] userToAdd =
        request.getParameterValues("userToAdd");
    if (userToAdd != null && userToAdd.length > 0) {
      for (int i = 0; i < userToAdd.length; i++) {
        flowDeployManager.addUserPerformer(userToAdd[i], flowNodeBinding.getNodeBindingID().toString());
      }
    }
    //打印关闭窗口的代码到客户端
    ResponseWriter.openerReloadClose(response);
    return null;
  }

  public ModelAndView listMembersToAddPerformer(HttpServletRequest request,
                                                HttpServletResponse response) throws
      Exception {
    String nodeBindingID = request.getParameter("nodeBindingID");
    String groupID = request.getParameter("groupID");
    if (groupID != null) {
      Map model = new HashMap();
      model.put("groupMembers", caDelegater.findGroupMembers(groupID));
      model.put("nodeBindingID", nodeBindingID);
      return new ModelAndView("listMembersToAddPerformer", model);
    }
    else {
      return new ModelAndView("listMembersToAddPerformer");
    }
  }

  public ModelAndView listRoles(HttpServletRequest request,
                                HttpServletResponse response) throws
      Exception {
    return new ModelAndView("main");
  }

  public ModelAndView addRolePerformer(HttpServletRequest request,
                                       HttpServletResponse response) throws
      Exception {
    return new ModelAndView("main");
  }

}
