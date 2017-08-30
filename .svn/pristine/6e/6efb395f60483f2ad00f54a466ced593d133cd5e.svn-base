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
package org.powerstone.workflow.flowdriver;

import org.apache.struts.action.PlugIn;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.config.ModuleConfig;
import javax.servlet.ServletException;
import org.powerstone.workflow.service.WorkflowEngine;
import org.powerstone.workflow.service.WorkflowDriverManager;
import org.powerstone.ca.CADelegater;
import org.powerstone.workflow.service.FlowTaskManager;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.servlet.http.HttpServletResponse;

/**
 * Spring工厂
 * <p>Title: </p>
 * <p>Description: </p>
 * @author daquan
 * @version 1.0
 */
public class WorkflowFactoryPlugin
    implements PlugIn {
  private static Log log = LogFactory.getLog(WorkflowFactoryPlugin.class);

  public static final String DEFAULT_FLOW_ENGINE_BEAN_NAME = "workflowEngine";
  private String flowEngineBeanName = DEFAULT_FLOW_ENGINE_BEAN_NAME;

  public static final String DEFAULT_DRIVER_MANAGER_BEAN_NAME =
      "workflowDriverManager";
  private String driverManagerBeanName = DEFAULT_DRIVER_MANAGER_BEAN_NAME;

  public static final String DEFAULT_CA_DELEGATER_BEAN_NAME = "caDelegater";
  private String caDelegaterBeanName = DEFAULT_CA_DELEGATER_BEAN_NAME;

  public static final String DEFAULT_TASK_MANAGER_BEAN_NAME = "flowTaskManager";
  private String taskManagerBeanName = DEFAULT_TASK_MANAGER_BEAN_NAME;

  public static final String DEFAULT_FLOW_EXECUTER_BEAN_NAME =
      "workflowService";
  private String flowExecuterBeanName = DEFAULT_FLOW_EXECUTER_BEAN_NAME;

  private static WorkflowEngine flowEngine;
  private static CADelegater caDelegater;
  private static WorkflowDriverManager driverManager;
  private static FlowTaskManager taskManager;
  private static WorkflowExecuter wfExecuter;
  private static boolean debug = false;

  public WorkflowFactoryPlugin() {
  }

  public void destroy() {
    flowEngine = null;
    caDelegater = null;
    driverManager = null;
    taskManager = null;
    wfExecuter = null;
  }

  public void init(ActionServlet servlet, ModuleConfig config) throws javax.
      servlet.ServletException {
    try {
      WebApplicationContext wac =
          WebApplicationContextUtils.getRequiredWebApplicationContext(servlet.
          getServletContext());
      if (wac != null) {
        flowEngine = (WorkflowEngine) wac.getBean(getFlowEngineBeanName());
        caDelegater = (CADelegater) wac.getBean(getCaDelegaterBeanName());
        driverManager =
            (WorkflowDriverManager) wac.getBean(getDriverManagerBeanName());
        taskManager =
            (FlowTaskManager) wac.getBean(getTaskManagerBeanName());
//      wfExecuter =
//          (WorkflowExecuter) wac.getBean(getFlowExecuterBeanName());

        log.info("初始化WorkflowFactoryPlugin");
      }
      else {
        log.warn("没有得到WebApplicationContext");
      }
    }
    catch (Exception e) {
      log.warn(e);
    }
  }

  public static CADelegater getCaDelegater() {
    return caDelegater;
  }

  public static String getRemoteUser(HttpServletRequest request) {
    return getCaDelegater().getRemoteUser(request);
  }

  public static boolean authenticate(String userID, String pass,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {
    return getCaDelegater().authenticate(userID, pass, request, response);
  }

  public static WorkflowDriverManager getDriverManager() {
    return driverManager;
  }

  public static WorkflowEngine getFlowEngine() {
    return flowEngine;
  }

  public static FlowTaskManager getTaskManager() {
    return taskManager;
  }

  public static WorkflowExecuter getWfExecuter() {
    return wfExecuter;
  }

//------------------------------------------------------------------------------
  public String getFlowEngineBeanName() {
    return flowEngineBeanName;
  }

  public void setFlowEngineBeanName(String flowEngineBeanName) {
    this.flowEngineBeanName = flowEngineBeanName;
  }

  public String getCaDelegaterBeanName() {
    return caDelegaterBeanName;
  }

  public void setCaDelegaterBeanName(String caDelegaterBeanName) {
    this.caDelegaterBeanName = caDelegaterBeanName;
  }

  public String getDriverManagerBeanName() {
    return driverManagerBeanName;
  }

  public void setDriverManagerBeanName(String driverManagerBeanName) {
    this.driverManagerBeanName = driverManagerBeanName;
  }

  public String getTaskManagerBeanName() {
    return taskManagerBeanName;
  }

  public void setTaskManagerBeanName(String taskManagerBeanName) {
    this.taskManagerBeanName = taskManagerBeanName;
  }

  public String getFlowExecuterBeanName() {
    return flowExecuterBeanName;
  }

  public void setFlowExecuterBeanName(String flowExecuterBeanName) {
    this.flowExecuterBeanName = flowExecuterBeanName;
  }

  public static boolean isDebug() {
    return debug;
  }

  public void setDebug(boolean debug2) {
    debug = debug2;
  }
}
