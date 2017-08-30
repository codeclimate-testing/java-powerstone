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
package org.powerstone.workflow.flowdriver.sample;

import org.powerstone.workflow.flowdriver.*;
import org.apache.struts.action.*;
import javax.servlet.http.*;
import java.util.HashMap;

public class SampleWriteAction extends WorkflowDriverWriteAction {
  /**
   * doBusiness
   * 执行业务逻辑（保存表单提交的数据），
   * 如果是一个提交动作，可以调用super.driveWorkflow()
   * @param mapping ActionMapping
   * @param form ActionForm
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return ActionForward
   */
  public ActionForward doBusiness(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
    //todo:调用相应的BO的方法保存表单数据

    //(如果是一个提交动作)提交任务
    super.driveWorkflow(request,response);

    return null;
  }

  /**
   * getDriverOutputParameters
   * 从参数form、request或其他途径得到所有要输出给工作流引擎的参数及其值，
   * 存储在HashMap返回,其中的参数名要和驱动注册的参数名保持一致！！！！！
   * @param form ActionForm
   * @param request HttpServletRequest
   * @return HashMap
   */
  public HashMap getDriverOutputParameters(ActionForm form,
                                           HttpServletRequest request) {
    //todo:从form、request或其他途径得到所有输出参数及其值
    return null;
  }

}
