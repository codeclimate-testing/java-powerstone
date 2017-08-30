package ps_dreambike.springmvc;

import org.powerstone.workflow.flowdriver.AbstractWorkflowDriver;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;

import ps_dreambike.BikeOrder;
import ps_dreambike.DreambikeManager;

import java.util.HashMap;

public class StockFormController  extends AbstractWorkflowDriver{
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
                                  Object command, BindException bindException) throws
      Exception {
    BikeOrder order = (BikeOrder) command;
    if (order.getStockState() == null || order.getStockState().trim().length() == 0) {
      bindException.rejectValue("price", "stock_required",
                                getText("error.stock.required"));
      return this.showForm(httpServletRequest, httpServletResponse,
                           bindException);
    }

    dreambikeManager.stock(order);
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
                                        Object command,
                                        BindException bindException) {
    BikeOrder order = (BikeOrder) command;
    HashMap result = new HashMap();
    result.put("stockState", order.getStockState());
    result.put("stockDetail", order.getStockDetail());
    return result;
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
