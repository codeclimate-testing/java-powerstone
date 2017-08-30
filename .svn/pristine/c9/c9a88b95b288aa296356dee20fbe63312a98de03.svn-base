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

import org.powerstone.workflow.flowdriver.AbstractWorkflowDriver;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import java.util.HashMap;

public class SampleDriverController extends AbstractWorkflowDriver {
  /**
   * doSubmit
   * 和你从前在onSubmit()中做的一样——实现应用的业务逻辑
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @param command Object
   * @param errors BindException
   * @return ModelAndView
   */
  protected ModelAndView doSubmit(HttpServletRequest request,
                                  HttpServletResponse response, Object command,
                                  BindException errors) {
    if (request.getParameter("submitTask") != null) {//如果必要，提交任务来驱动流程引擎
      super.driveWorkflow(request);
    }
    return null;
  }

  /**
   * getOutputParameters
   * 获得应用输出参数:此处决定了注册应用的输出参数
   * 也可以在此处放置业务逻辑
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @param command Object
   * @param errors BindException
   * @return HashMap
   */
  protected HashMap getOutputParameters(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Object command, BindException errors) {
    return null;
  }

  /**
   * doFormBackingObject
   * 和你从前在formBackingObject()中做的一样——从后端获取数据
   * 区别是现在多了一个HashMap inputParameters，里面装着你为此应用注册的输入参数
   * @param request HttpServletRequest
   * @param inputParameters HashMap
   * @return Object
   */
  protected Object doFormBackingObject(HttpServletRequest request,
                                       HashMap inputParameters) {
    log.debug(inputParameters);
    return "";
  }

}