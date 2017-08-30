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

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.HashMap;
import java.util.List;
import org.powerstone.workflow.model.WorkflowDriver;
import org.powerstone.workflow.model.ActivityReport;
import javax.servlet.http.Cookie;
import org.powerstone.workflow.model.FlowTask;

public abstract class WorkflowDriverWriteAction
    extends Action
    implements WorkFlowDriverTransactionable {
  private static Log log = LogFactory.getLog(WorkflowDriverWriteAction.class);

  public final ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws
      Exception {
    try {
      WorkflowExecuter wfExecuter = WorkflowFactoryPlugin.getWfExecuter();
      if (wfExecuter != null) {
        //ʵ�������ڻص��Լ���doTransactionExecute()
        return wfExecuter.doExecute(this, mapping, form, request, response);
      }
      else {
        log.warn("û��ʹ��WorkflowExecuter������һ����û�б�֤��debug:[" +
                 WorkflowFactoryPlugin.isDebug() + "]");
        return doTransactionExecute(mapping, form, request, response);
      }
    }
    catch (Exception e) {
      log.warn(e);
      e.printStackTrace();
      return mapping.findForward("driver_error_forward");
    }
  }

  /**
   * ���������ҪJTA����֤�����ԣ�������WorkflowExecuter����
   * @throws Exception
   * @return ActionForward
   */
  public final ActionForward doTransactionExecute(ActionMapping mapping,
                                                  ActionForm form,
                                                  HttpServletRequest request,
                                                  HttpServletResponse response
                                                  ) throws
      Exception {
    //���ͻͨ��
    if (!WorkflowFactoryPlugin.isDebug()) { //������ǵ���ģʽ
      sendActivityReport(form, request, response);
    }

    //ִ��ҵ���߼�(�ڼ���ܵ���driveWorkflow()���ύ����)
    ActionForward forward = doBusiness(mapping, form, request, response);

    //ɾ��cookie
    this.removeCookie(request, response);
    if (log.isDebugEnabled()) {
      log.debug("after doBusiness:ActionForward[" +
                (forward != null ? forward.getPath() : "null") + "]");
    }
    if (forward == null) {
      //�رո����ڣ�parent.submitTask();

    }
    return forward;
  }

  /**
   * �ύ����,��������������
   */
  protected void driveWorkflow(HttpServletRequest request,
                               HttpServletResponse response) {
    if (WorkflowFactoryPlugin.isDebug()) { //����ǵ���ģʽ
      log.debug("Ŀǰ���ڹ���������ģʽ��");
      return;
    }
    String userID = WorkflowFactoryPlugin.getRemoteUser(request);
    String taskID = processCookie(request, response);

    if (log.isDebugEnabled()) {
      log.debug("�û�[" + userID + "]�ύ����[" + taskID + "]����������");
    }
    WorkflowFactoryPlugin.getFlowEngine().processSubmitTask(userID, taskID);
  }

  /**
   * ��cookie��ȡtaskID��
   * �����uri��Ӧ������ƥ��,�ͷ���ActivityReport(taskID��driverOutputData),
   * ������ActivityReport(driverID��driverOutputData)
   * ���ͻͨ��
   */
  private void sendActivityReport(ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
    if (log.isDebugEnabled()) {
      log.debug("���ͻͨ��ǰ--------------" + request);
    }
    String uri = request.getServletPath();
    HashMap driverOutputParameters = getDriverOutputParameters(form, request);
    if (log.isDebugEnabled()) {
      log.debug("���ͻͨ��[" + driverOutputParameters + "]");
    }

    List writeDrivers =
        WorkflowFactoryPlugin.getDriverManager().findDriverByWriteDO(request.
        getContextPath(), uri);

    WorkflowDriver flowDriver = null;
    if (writeDrivers.size() > 0) {
      flowDriver = (WorkflowDriver) writeDrivers.get(0);
      if (writeDrivers.size() > 1) {
        log.warn("����[" + request.getContextPath() + "|" + uri + "]��ע��Ϊ���������");
      }
    }
    else {
      log.warn("����[" + request.getContextPath() + "|" + uri + "]û�б�ע��Ϊ�κ�������");
      return;
    }

    String taskIDInCookie = processCookie(request, response);
    if (log.isDebugEnabled()) {
      log.debug("��cookie�л�ȡtaskID[" + taskIDInCookie + "]");
    }

    FlowTask task = null;
    if (taskIDInCookie != null) {
      task = WorkflowFactoryPlugin.getTaskManager().getFlowTask(taskIDInCookie);
    }

    ActivityReport report = new ActivityReport();

    if (task != null) {
      //���task��uri��Ӧ������ƥ��,�ͷ���ActivityReport(taskID��driverOutputData)
      if (task.getFlowNodeBinding().getWorkflowDriver().equals(flowDriver)) {
        report.setFlowTaskID(taskIDInCookie);
        if (log.isDebugEnabled()) {
          log.debug("����[" + taskIDInCookie + "]��һ�α���");
        }
      }
      else {
        log.warn("����[" + taskIDInCookie + "]������[" +
                 flowDriver.getFlowDriverName() + "]��ƥ�䣡");
        return;
      }
    }
    //������ActivityReport(driverID��driverOutputData)
    else {
      report.setDriverID(flowDriver.getFlowDriverID().toString());
      if (log.isDebugEnabled()) {
        log.debug("����[" + flowDriver.getFlowDriverName() + "]����������");
      }
    }

    report.setDriverOutputData(driverOutputParameters);
    if (log.isDebugEnabled()) {
      log.debug("�������������" + driverOutputParameters);
    }

    WorkflowFactoryPlugin.getFlowEngine().processActivityReport(
        WorkflowFactoryPlugin.getRemoteUser(request), report);
  }

  /**
   * ɾ��cookie
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   */
  private void removeCookie(HttpServletRequest request,
                            HttpServletResponse response) {
    Cookie[] cks = request.getCookies();
    if (cks != null) {
      for (int i = 0; i < cks.length; i++) {
        Cookie tok = cks[i];
        if (tok.getName().equals(IFlowDriver.COOKIE_NAME)) {
          //ɾ��cookie
          tok.setMaxAge(0);
          tok.setPath("/");
          response.addCookie(tok);
          break;
        }
      }
    }
  }

  /**
   * ��cookie��ȡtaskID
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return String
   */
  private String processCookie(HttpServletRequest request,
                               HttpServletResponse response) {
    String taskIDInCookie = null;
    Cookie[] cks = request.getCookies();

    if (cks != null) {
      for (int i = 0; i < cks.length; i++) {
        Cookie tok = cks[i];
        if (tok.getName().equals(IFlowDriver.COOKIE_NAME)) {
          taskIDInCookie = tok.getValue();
          if (taskIDInCookie.indexOf(request.getServletPath()) >= 0) {
            if (log.isDebugEnabled()) {
              log.debug("cookie[" + taskIDInCookie + "]");
            }

            taskIDInCookie =
                taskIDInCookie.substring(0,
                                         taskIDInCookie.indexOf(IFlowDriver.
                COOKIE_DIVIDER));
            System.out.println("��cookie��ȡ����[" + taskIDInCookie + "]");
          }
          else {
            log.warn("cookie��ƥ�䣡������");
            taskIDInCookie = null;
            break;
          }

          break;
        }
      }
    }
    return taskIDInCookie;
  }

  /**
   * ʵ��������ҵ���߼�
   */
  public abstract ActionForward doBusiness(ActionMapping mapping,
                                           ActionForm form,
                                           HttpServletRequest request,
                                           HttpServletResponse response) throws
      Exception;

  /**
   * ��������������:�˴�������ע�������������
   * @return HashMap
   */
  public abstract HashMap getDriverOutputParameters(ActionForm form,
      HttpServletRequest request);

}
