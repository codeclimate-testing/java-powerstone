package org.powerstone.web.paging;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author liyingquan@gmail.com
 * 
 */
abstract public class PagingAction extends Action {
	private Logger log = Logger.getLogger(getClass());

	public static int defaultPageSize = PagingController.DEFAULT_PAGE_SIZE;

	public void setDefaultPageSize(int thePageSize) {
		defaultPageSize = thePageSize;
	}

	/**
	 * 分页控制器入口
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm aForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.debug("=======================================");
		ActionForward view = null;
		PageModel pm = getPageModel(request);
		if (pm == null) {
			pm = new PageModel();
			// trim string to clean blank
			pm
					.setPageTo(request
							.getParameter(PagingController.TO_PAGE_NO_PARAM_NAME) != null ? (request
							.getParameter(PagingController.TO_PAGE_NO_PARAM_NAME)
							.trim())
							: null);
			pm
					.setBeFirst(request
							.getParameter(PagingController.TO_FIRST_PARAM_NAME) != null);
			pm.setBeEnd(request
					.getParameter(PagingController.TO_END_PARAM_NAME) != null);
			pm.setBeNext(request
					.getParameter(PagingController.TO_NEXT_PARAM_NAME) != null);
			pm.setBeLast(request
					.getParameter(PagingController.TO_LAST_PARAM_NAME) != null);
			pm.setCurrPageNo(request
					.getParameter(PagingController.CURR_PAGE_PARAM_NAME));

			request.setAttribute(PagingController.DEFAULT_PAGE_MODEL_NAME, pm);

			int pageSize = getPageSize(request);
			if (pageSize > 0) {
				pm.setPageSize(pageSize);
			} else {
				pm.setPageSize(defaultPageSize);
			}

			pm.setTotalRecordsNumber(getTotalRecordsNumber(request));
		}
		log.debug("PageModel:" + pm);
		request.setAttribute(PagingController.DEFAULT_PAGE_MODEL_NAME, pm);
		view = doExecute(mapping, aForm, request, response);

		return view;
	}

	/**
	 * 供具体子类调用，获取查询结果集的起始索引
	 * 
	 * @param request
	 * @return
	 */
	public final int computeRecordsBeginNo(HttpServletRequest request) {
		PageModel pm = (PageModel) request
				.getAttribute(PagingController.DEFAULT_PAGE_MODEL_NAME);
		return pm.computeRecordsBeginNo();
	}

	/**
	 * 供具体子类调用，获取该页查询结果集的大小
	 * 
	 * @param request
	 * @return
	 */
	public final int computeRecordsNumberToRead(HttpServletRequest request) {
		PageModel pm = (PageModel) request
				.getAttribute(PagingController.DEFAULT_PAGE_MODEL_NAME);
		return pm.getPageSize();
	}

	/**
	 * 供具体子类调用，跳转到某一页（例如更新一条记录后，直接回到该记录所在页）
	 * 必须先于computeRecordsBeginNo和computeRecordsNumberToRead调用
	 * 
	 * @param request
	 * @param toPageNo
	 */
	public final void goToPage(HttpServletRequest request, int toPageNo) {
		PageModel pm = (PageModel) request
				.getAttribute(PagingController.DEFAULT_PAGE_MODEL_NAME);
		pm.setPageTo(new Integer(toPageNo).toString());
	}

	/**
	 * 供具体子类调用，获取缓存的查询criteria
	 * 
	 * @param request
	 * @return
	 */
	public final Map getSearchCriteria(HttpServletRequest request) {
		PageModel pm = (PageModel) request
				.getAttribute(PagingController.DEFAULT_PAGE_MODEL_NAME);
		log.debug(pm);
		Map criteria = null;
		// On first access,build criteria and store in session
		if (pm != null && pm.getPageTo() == null && !pm.isBeEnd()
				&& !pm.isBeFirst() && !pm.isBeNext() && !pm.isBeLast()) {
			log.info("On first access,makeSearchCriteria and put into session");

			criteria = makeSearchCriteria(request);
			request.getSession().setAttribute(
					PagingController.CRITERIA_IN_SESSION, criteria);
		} else {
			criteria = (Map) request.getSession().getAttribute(
					PagingController.CRITERIA_IN_SESSION);
			log.debug("CRITERIA_IN_SESSION>>>>>>>>>:" + criteria);
			if (criteria == null) {
				criteria = makeSearchCriteria(request);
			}
		}
		log.debug("CRITERIA>>>>>>>>>:" + criteria);
		return criteria;
	}

	/**
	 * 由子类重写，处理请求
	 * 
	 * @throws Exception
	 */
	abstract protected ActionForward doExecute(ActionMapping mapping,
			ActionForm aForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception;

	/**
	 * 由子类重写，返回总记录数，供父类计算分页
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	abstract protected int getTotalRecordsNumber(HttpServletRequest request);

	/**
	 * 由子类重写，返回分页信息（基本没用）
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected PageModel getPageModel(HttpServletRequest request) {
		return null;
	}

	/**
	 * 由子类重写，返回每页显示记录数（用来实现客户订制的分页大小） 默认返回-1，则分页大小采用defaultPageSize
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected int getPageSize(HttpServletRequest request) {
		return -1;
	}

	/**
	 * 由子类重写，构造查询条件（由父类保存在session中）
	 * 
	 * @param request
	 * @return
	 */
	abstract protected Map makeSearchCriteria(HttpServletRequest request);// {
	// log.debug("didn't overwrite makeSearchCriteria,return null bydefault!");
	// return null;
	// }

}