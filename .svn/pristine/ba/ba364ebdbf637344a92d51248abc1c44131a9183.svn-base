package ps_dreambike.springmvc;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.powerstone.ca.CADelegater;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.RedirectView;

public class DreambikeController
    extends MultiActionController {
  private static Log log = LogFactory.getLog(DreambikeController.class);
  private CADelegater caDelegater;
  public void setCaDelegater(CADelegater caDelegater) {
    this.caDelegater = caDelegater;
  }

  public ModelAndView login(HttpServletRequest request,
                            HttpServletResponse response) throws
      Exception {
    if (caDelegater.authenticate(request.getParameter("userID"),
                                 request.getParameter("pass"),
                                 request,
                                 response)) {
    }
    return new ModelAndView(new RedirectView(request.getContextPath() + "/"));
  }
}
