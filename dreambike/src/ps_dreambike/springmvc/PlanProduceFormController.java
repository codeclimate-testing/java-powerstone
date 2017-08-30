package ps_dreambike.springmvc;

import org.powerstone.workflow.flowdriver.AbstractWorkflowDriver;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;

import ps_dreambike.BikeOrder;
import ps_dreambike.DreambikeManager;
import ps_dreambike.Produce;

import java.util.HashMap;

public class PlanProduceFormController
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
    Produce produce = (Produce) command;
    if (produce.getProducePlan() == null ||
        produce.getProducePlan().trim().length() == 0) {
      bindException.rejectValue("producePlan", "produceplan_required",
                                getText("error.produceplan.required"));
      return this.showForm(request, response, bindException);
    }
    if(produce.getProduceID().longValue()==-1){
      dreambikeManager.planProduce(request.getParameter("orderID"), produce);
    }else{
      dreambikeManager.saveProduce(produce);
    }

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
      httpServletRequest.setAttribute("orderID",orderID);
      BikeOrder order = dreambikeManager.getOrder(orderID);
      if (order.getProduce() != null) {
        return order.getProduce();
      }
      else {
        return new Produce();
      }
    }
  }
}
