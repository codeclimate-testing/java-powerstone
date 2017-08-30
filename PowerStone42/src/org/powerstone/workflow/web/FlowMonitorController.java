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

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.powerstone.workflow.service.FlowProcManager;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import org.powerstone.workflow.service.FlowTaskManager;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import org.powerstone.workflow.model.FlowTask;
import java.util.HashMap;

public class FlowMonitorController
    extends MultiActionController {
  private static Log log = LogFactory.getLog(FlowMonitorController.class);
  private FlowProcManager flowProcManager;
  private FlowTaskManager flowTaskManager;
  public void setFlowProcManager(FlowProcManager flowProcManager) {
    this.flowProcManager = flowProcManager;
  }

  public void setFlowTaskManager(FlowTaskManager flowTaskManager) {
    this.flowTaskManager = flowTaskManager;
  }

//------------------------------------------------------------------------------
  public ModelAndView flowMonitor(HttpServletRequest request,
                                  HttpServletResponse response) throws
      Exception {
    String flowDeployID = request.getParameter("flowDeployID");

    return new ModelAndView("flowMonitor", "activeFlowProcs",
                            flowProcManager.
                            getActiveFlowProcsByDeploy(flowDeployID));
  }

  public ModelAndView flowDetail(HttpServletRequest request,
                                 HttpServletResponse response) throws
      Exception {
    String flowProcID = request.getParameter("flowProcID");
    List allTasks = flowTaskManager.findTasksByProc(flowProcID);
    HashMap model = new HashMap();

    if (allTasks != null && allTasks.size() > 0) {
      ArrayList freeTasks = new ArrayList();
      ArrayList executingTasks = new ArrayList();
      ArrayList finishedTasks = new ArrayList();
      for (Iterator it = allTasks.iterator(); it.hasNext(); ) {
        FlowTask ft = (FlowTask) it.next();
        if (ft.getTaskState().equals(FlowTask.TASK_STATE_FREE)) {
          freeTasks.add(ft);
          continue;
        }
        if (ft.getTaskState().equals(FlowTask.TASK_STATE_LOCKED) ||
            ft.getTaskState().equals(FlowTask.TASK_STATE_ASSIGNED) ||
            ft.getTaskState().equals(FlowTask.TASK_STATE_REFUSED)) {
          executingTasks.add(ft);
          continue;
        }
        if (ft.getTaskState().equals(FlowTask.TASK_STATE_FINISHED)) {
          finishedTasks.add(ft);
          continue;
        }
      }
      model.put("freeTasks", freeTasks);
      model.put("executingTasks", executingTasks);
      model.put("finishedTasks", finishedTasks);
    }

    return new ModelAndView("flowDetail", model);
  }
}
