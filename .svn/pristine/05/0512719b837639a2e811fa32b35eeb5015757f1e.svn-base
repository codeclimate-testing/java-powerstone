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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.powerstone.workflow.service.FlowDeployManager;
import org.powerstone.workflow.service.FlowMetaManager;
import org.springframework.web.servlet.mvc.SimpleFormController;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.powerstone.workflow.model.FlowDeploy;
import java.util.GregorianCalendar;
import javax.servlet.ServletException;

public class FlowDeployFormController
    extends SimpleFormController {
  private static Log log = LogFactory.getLog(FlowDeployFormController.class);
  private FlowDeployManager flowDeployManager;
  private FlowMetaManager flowMetaManager;
  public ModelAndView processFormSubmission(HttpServletRequest request,
                                            HttpServletResponse response,
                                            Object command,
                                            BindException errors) throws
      Exception {
    if (request.getParameter("cancel") != null) {
      return new ModelAndView(getSuccessView() + "?flowMetaID=" +
                              request.getParameter("workflowMetaID"));
    }
    return super.processFormSubmission(request, response, command, errors);
  }

  public ModelAndView onSubmit(HttpServletRequest request,
                               HttpServletResponse response, Object command,
                               BindException errors) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug("entering 'onSubmit' method...");
    }
    String flowMetaID = request.getParameter("workflowMetaID");
    FlowDeploy fd = (FlowDeploy) command;
    if (fd.getFlowDeployID().longValue() == -1) {
      fd.setCreateTime( (new GregorianCalendar()).getTime().toString());
      fd.setCurrentState(FlowDeploy.DEPLOY_STATE_PREPARING);
      flowMetaManager.addFlowDeploy(flowMetaID, fd);
    }
    else {
      flowDeployManager.updateFlowDeploy(fd);
    }
    return new ModelAndView(getSuccessView() + "?flowMetaID=" + flowMetaID);
  }

  protected Object formBackingObject(HttpServletRequest request) throws
      ServletException {
    String deployID = request.getParameter("deployID");
    request.setAttribute("workflowMetaID",
                         request.getParameter("workflowMetaID"));
    if ( (deployID != null) && !deployID.equals("-1")) {
      request.setAttribute("deployID", deployID);
      return flowDeployManager.getFlowDeploy(deployID);
    }
    else {
      FlowDeploy fd = new FlowDeploy();
      if (log.isDebugEnabled()) {
        log.debug("entering 'formBackingObject' method..." + fd);
      }
      return fd;
    }
  }

  public String getText(String msgKey) {
    return getMessageSourceAccessor().getMessage(msgKey);
  }

  public String getText(String msgKey, String arg) {
    return getText(msgKey, new Object[] {arg});
  }

  public String getText(String msgKey, Object[] args) {
    return getMessageSourceAccessor().getMessage(msgKey, args);
  }

  public void setFlowDeployManager(FlowDeployManager flowDeployManager) {
    this.flowDeployManager = flowDeployManager;
  }

  public void setFlowMetaManager(FlowMetaManager flowMetaManager) {
    this.flowMetaManager = flowMetaManager;
  }
}
