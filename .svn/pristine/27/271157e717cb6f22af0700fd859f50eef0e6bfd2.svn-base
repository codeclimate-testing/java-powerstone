package ps_dreambike.springmvc;

import org.powerstone.workflow.flowdriver.AbstractWorkflowDriver;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;

import ps_dreambike.BikeOrder;
import ps_dreambike.DreambikeManager;

import java.util.HashMap;

public class OrderFormController
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
    BikeOrder order = (BikeOrder) command;
    if (order.getProductID() == null ||
        order.getProductID().trim().length() == 0) {
      bindException.rejectValue("productID", "product_id_required",
                                getText("error.product_id.required"));
      return this.showForm(request, response, bindException);
    }
    if (order.getCustomeEmail() == null ||
        order.getCustomeEmail().trim().length() == 0) {
      bindException.rejectValue("customeEmail", "custome_email_required",
                                getText("error.custome_email.required"));
      return this.showForm(request, response, bindException);
    }

    return new ModelAndView(this.getSuccessView());
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
  protected HashMap getOutputParameters(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Object command,
                                        BindException bindException) throws
      Exception {
    BikeOrder order = (BikeOrder) command;
    //业务逻辑通常放置在doSubmit函数内
    //但是，因为输出参数orderID是数据库生成的自增字段，所以要在此处放置业务逻辑
    if (order.getProductID() != null && order.getCustomeEmail() != null) {
      order = dreambikeManager.order(order);
    }

    HashMap result = new HashMap();
    result.put("orderID", order.getOrderID().toString());
    result.put("customeID", order.getCustomeID());
    result.put("customeEmail", order.getCustomeEmail());
    result.put("productID", order.getProductID());
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
    return new BikeOrder();
  }
}