package ps_dreambike.struts;

import org.apache.struts.action.*;
import javax.servlet.http.*;
import org.powerstone.workflow.flowdriver.*;
import java.util.HashMap;

public class RecieveOrderAction extends WorkflowDriverWriteAction {
  /**
   * doBusiness
   *
   * @param actionMapping ActionMapping
   * @param actionForm ActionForm
   * @param httpServletRequest HttpServletRequest
   * @param httpServletResponse HttpServletResponse
   * @return ActionForward
   */
  public ActionForward doBusiness(ActionMapping actionMapping,
                                  ActionForm actionForm,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse) {
    httpServletRequest.setAttribute("message","RecieveOrderAction");
    return actionMapping.findForward("write");
  }

  /**
   * getDriverOutputParameters
   *
   * @param actionForm ActionForm
   * @param httpServletRequest HttpServletRequest
   * @return HashMap
   */
  public HashMap getDriverOutputParameters(ActionForm actionForm,
                                           HttpServletRequest
                                           httpServletRequest) {
    return null;
  }

}
