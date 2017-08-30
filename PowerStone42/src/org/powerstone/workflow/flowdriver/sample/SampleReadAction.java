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

public class SampleReadAction
    extends WorkflowDriverReadAction {
  /**
   * doExecute
   * 执行业务逻辑（显示相应的表单），
   * 如果此驱动有输入参数，可以调用super.getDriverInputParameters()获得
   * @param mapping ActionMapping
   * @param form ActionForm
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return ActionForward
   */
  public ActionForward doExecute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    //（如果需要输入参数）获得此驱动de输入参数HashMap(参数ID-参数值)
    HashMap driverInputParameters =
        super.getDriverInputParameters(request,response);

    //todo:调用相应的BO的方法查找表单数据

    return null;
  }
}
