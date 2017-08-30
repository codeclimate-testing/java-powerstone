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
import org.powerstone.workflow.model.WorkflowDriver;
import org.powerstone.util.ResponseWriter;
import org.springframework.dao.DataIntegrityViolationException;
import java.util.List;

public class DriverFormController
    extends SimpleFormController {
  private final Log log = LogFactory.getLog(DriverFormController.class);
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
    WorkflowDriver driver = (WorkflowDriver) command;
    if (log.isDebugEnabled()) {
      log.debug("entering 'onSubmit' method..." + driver);
    }
    WorkflowDriver driverWithSameWriteDO = null;
    List drivers =
        workflowDriverManager.findDriverByWriteDO(driver.getContextPath(),
                                                  driver.getWriteURL());
    if (drivers.size() > 0) {
      driverWithSameWriteDO = (WorkflowDriver) drivers.get(0);
    }

    if (driver.getFlowDriverID().longValue() == -1) {
      if (driverWithSameWriteDO != null) {
        errors.rejectValue("writeURL", "duplicate_driver_writeURL",
                           getText("error.flow.duplicate_driver_writeURL",
                                   new Object[] {
                                   driverWithSameWriteDO.getWriteURL(),
                                   driverWithSameWriteDO.getContextPath()}));
        return showForm(request, response, errors);

      }
      workflowDriverManager.saveWorkflowDriver(driver);
    }
    else {
      if (driverWithSameWriteDO != null &&
          !driverWithSameWriteDO.getFlowDriverID().equals(driver.
          getFlowDriverID())) {
        errors.rejectValue("writeURL", "duplicate_driver_writeURL",
                           getText("error.flow.duplicate_driver_writeURL",
                                   new Object[] {
                                   driverWithSameWriteDO.getWriteURL(),
                                   driverWithSameWriteDO.getContextPath()}));
        return showForm(request, response, errors);
      }
      workflowDriverManager.updateWorkflowDriver(driver);
    }
//    request.getSession().setAttribute("message",
//                                      getText("driver.saved",
//                                              driver.getFlowDriverName()));
    //��ӡˢ�¸����ڵĴ��뵽�ͻ���
    ResponseWriter.openerParentReloadClose(response);
    return null;
  }

  protected Object formBackingObject(HttpServletRequest request) throws
      ServletException {
    String driverID = request.getParameter("driverID");
    String contextPath = request.getParameter("contextPath");
    if (log.isDebugEnabled()) {
      log.debug("entering 'formBackingObject' method[driverID=" + driverID +
                "|contextPath=" + contextPath + "]");
    }

    if ( (driverID != null) && !driverID.equals("-1")) {
      return workflowDriverManager.getWorkflowDriver(driverID);
    }
    else {
      WorkflowDriver driver = new WorkflowDriver();
      driver.setContextPath(contextPath);
      return driver;
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
}
