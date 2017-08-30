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

import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.powerstone.workflow.service.WorkflowDriverManager;
import org.powerstone.workflow.model.WFDriverInputParam;
import org.powerstone.util.ResponseWriter;

public class DriverInputFormController
    extends SimpleFormController {
  private final Log log = LogFactory.getLog(DriverInputFormController.class);
  private WorkflowDriverManager workflowDriverManager = null;
  public void setWorkflowDriverManager(WorkflowDriverManager
                                       workflowDriverManager) {
    this.workflowDriverManager = workflowDriverManager;
  }

  public ModelAndView processFormSubmission(HttpServletRequest request,
                                            HttpServletResponse response,
                                            Object command,
                                            BindException errors) throws
      Exception {
    if (request.getParameter("cancel") != null) {
      return new ModelAndView(getSuccessView());
    }
    return super.processFormSubmission(request, response, command, errors);
  }

  public ModelAndView onSubmit(HttpServletRequest request,
                               HttpServletResponse response, Object command,
                               BindException errors) throws Exception {
    WFDriverInputParam driverInputParam = (WFDriverInputParam) command;
    if (log.isDebugEnabled()) {
      log.debug("entering 'onSubmit' method..." + driverInputParam);
    }
    workflowDriverManager.addDriverInputParam(request.getParameter("driverID"),
                                              driverInputParam);
    //打印刷新父窗口的代码到客户端
    ResponseWriter.openerReloadClose(response);
    return null;
  }

  protected Object formBackingObject(HttpServletRequest request) throws
      ServletException {
    String driverID = request.getParameter("driverID");
    request.setAttribute("driverID", driverID);
    String driverInputParamID = request.getParameter("driverInputParamID");
    if (log.isDebugEnabled()) {
      log.debug("entering 'formBackingObject' method[driverID=" + driverID +
                "|driverInputParamID=" + driverInputParamID + "]");
    }
    return new WFDriverInputParam();
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
}
