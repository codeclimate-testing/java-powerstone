package ps_dreambike.struts;

import org.apache.struts.action.*;
import javax.servlet.http.*;
import org.powerstone.workflow.flowdriver.*;

public class SendEmailReadAction extends WorkflowDriverReadAction {
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
    httpServletRequest.setAttribute("message","SendEmailReadAction");
    httpServletRequest.setAttribute("action","SendEmailWriteAction");
    super.getDriverInputParameters(httpServletRequest,httpServletResponse);
    return actionMapping.findForward("read");
  }

}
