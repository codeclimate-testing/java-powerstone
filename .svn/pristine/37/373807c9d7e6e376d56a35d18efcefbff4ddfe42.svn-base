package org.appfuse.web.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

abstract public class PagingController implements Controller {
	private static Log log = LogFactory.getLog(PagingController.class);

	public final static String DEFAULT_PAGE_MODEL_NAME = "com.ema.web.pagemodel";

	public final static String TO_PAGE_NO_PARAM_NAME = "toPageNo";

	public final static String TO_FIRST_PARAM_NAME = "toFirst";

	public final static String TO_END_PARAM_NAME = "toEnd";

	public final static String TO_NEXT_PARAM_NAME = "toNext";

	public final static String TO_LAST_PARAM_NAME = "toLast";

	public final static String CURR_PAGE_PARAM_NAME = "currPage";

	private final static int DEFAULT_PAGE_SIZE = 10;

	public static int defaultPageSize = DEFAULT_PAGE_SIZE;

	public void setDefaultPageSize(int thePageSize) {
		PagingController.defaultPageSize = thePageSize;
	}

	public final ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PageModel pm = getPageModel(request);
		if (pm == null) {
			pm = new PageModel();
			pm.setPageTo(request.getParameter(TO_PAGE_NO_PARAM_NAME));
			pm.setBeFirst(request.getParameter(TO_FIRST_PARAM_NAME) != null);
			pm.setBeEnd(request.getParameter(TO_END_PARAM_NAME) != null);
			pm.setBeNext(request.getParameter(TO_NEXT_PARAM_NAME) != null);
			pm.setBeLast(request.getParameter(TO_LAST_PARAM_NAME) != null);
			pm.setCurrPageNo(request.getParameter(CURR_PAGE_PARAM_NAME));

			int pageSize = getPageSize(request);
			if (pageSize > 0) {
				pm.setPageSize(pageSize);
			} else {
				pm.setPageSize(defaultPageSize);
			}

			pm.setTotalRecordsNumber(getTotalRecordsNumber(request));
		}
		log.debug(pm);
		request.setAttribute(DEFAULT_PAGE_MODEL_NAME, pm);
		return doHandleRequest(request, response);
	}

	public final int computeRecordsBeginNo(HttpServletRequest request) {
		PageModel pm = (PageModel) request
				.getAttribute(DEFAULT_PAGE_MODEL_NAME);
		return pm.computeRecordsBeginNo();
	}

	public final int computeRecordsNumberToRead(HttpServletRequest request) {
		PageModel pm = (PageModel) request
				.getAttribute(DEFAULT_PAGE_MODEL_NAME);
		return pm.getPageSize();
	}

	public final void goToPage(HttpServletRequest request, int toPageNo) {
		PageModel pm = (PageModel) request
				.getAttribute(DEFAULT_PAGE_MODEL_NAME);
		pm.setPageTo(new Integer(toPageNo).toString());
	}

	abstract protected ModelAndView doHandleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception;

	abstract protected int getTotalRecordsNumber(HttpServletRequest request);

	protected PageModel getPageModel(HttpServletRequest request) {
		return null;
	}

	protected int getPageSize(HttpServletRequest request) {
		return -1;
	}

}