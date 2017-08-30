package ps_dreambike.struts;

import org.apache.struts.action.*;
import javax.servlet.http.*;
import org.powerstone.workflow.flowdriver.*;

public class ConfirmOrderReadAction extends WorkflowDriverReadAction {
  /**
   * doExecute
   *
   * @param actionMapping ActionMapping
   * @param actionForm ActionForm
   * @param httpServletRequest HttpServletRequest
   * @param httpServletResponse HttpServletResponse
   * @return ActionForward
   */
  public ActionForward doExecute(ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse) {
    httpServletRequest.setAttribute("message","ConfirmOrderReadAction");
    httpServletRequest.setAttribute("action","ConfirmOrderWriteAction");
    super.getDriverInputParameters(httpServletRequest,httpServletResponse);
    return actionMapping.findForward("read");

  }

}
