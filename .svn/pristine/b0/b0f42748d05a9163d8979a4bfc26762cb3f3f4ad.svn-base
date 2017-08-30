package ps_dreambike.springmvc;

import org.powerstone.workflow.flowdriver.AbstractWorkflowDriver;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;

import ps_dreambike.DreambikeManager;
import ps_dreambike.Purchase;

import java.util.HashMap;

public class PlanPurchFormController
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
  protected ModelAndView doSubmit(HttpServletRequest request,
                                  HttpServletResponse response,
                                  Object command, BindException bindException) throws
      Exception {
    Purchase purchase = (Purchase) command;
    if (purchase.getPurchasePlan() == null ||
        purchase.getPurchasePlan().trim().length() == 0) {
      bindException.rejectValue("purchasePlan", "purchaseplan_required",
                                getText("error.purchaseplan.required"));
      return this.showForm(request, response, bindException);
    }
    if (log.isDebugEnabled()) {
      log.debug(request.getParameter("orderID") + "---" +
                purchase.getPurchasePlan());
    }
    dreambikeManager.planPurch(request.getParameter("orderID"), purchase);
    if (request.getParameter("submitTask") != null) {
      super.driveWorkflow(request);
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
      return new Produce();
    }
    else {
      httpServletRequest.setAttribute("orderID", orderID);
      BikeOrder order = dreambikeManager.getOrder(orderID);
      if (order.getPurchase() != null) {
        return order.getPurchase();
      }
      else {
        return new Purchase();
      }
    }
  }
}
