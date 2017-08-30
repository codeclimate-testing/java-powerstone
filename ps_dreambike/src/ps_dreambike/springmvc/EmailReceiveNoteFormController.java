package ps_dreambike.springmvc;

import org.powerstone.workflow.flowdriver.AbstractWorkflowDriver;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;

import ps_dreambike.BikeOrder;
import ps_dreambike.DreambikeManager;

import java.util.HashMap;

public class EmailReceiveNoteFormController
    extends AbstractWorkflowDriver {
  private DreambikeManager dreambikeManager;
  public void setDreambikeManager(DreambikeManager dreambikeManager) {
    this.dreambikeManager = dreambikeManager;
  }

  /**
   * doSubmit
   *
   * @param httpServletRequest HttpServletRequest
   * @param httpServletResponse HttpServletResponse
   * @param object Object
   * @param bindException BindException
   * @return ModelAndView
   */
  protected ModelAndView doSubmit(HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse,
                                  Object command, BindException bindException) {
    BikeOrder order = (BikeOrder) command;
    dreambikeManager.emailReceiveNote(order);
    if (httpServletRequest.getParameter("submitTask") != null) {
      super.driveWorkflow(httpServletRequest);
    }
    return null;
  }

  /**
   * getOutputParameters
   *
   * @param httpServletRequest HttpServletRequest
   * @param httpServletResponse HttpServletResponse
   * @param object Object
   * @param bindException BindException
   * @return HashMap
   */
  protected HashMap getOutputParameters(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Object object,
                                        BindException bindException) {
    //确实没有输出参数
    return null;
  }

  /**
   * doFormBackingObject
   *
   * @param httpServletRequest HttpServletRequest
   * @param hashMap HashMap
   * @return Object
   */
  protected Object doFormBackingObject(HttpServletRequest httpServletRequest,
                                       HashMap hashMap) {
    String orderID = (String) hashMap.get("_orderID");
    if (orderID == null) {
      log.warn("input parameter 'orderID' is unavailable!");
      return new BikeOrder();
    }
    else {
      return dreambikeManager.getOrder(orderID);
    }
  }
}
