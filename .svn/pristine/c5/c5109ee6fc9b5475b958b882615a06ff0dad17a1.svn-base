package org.powerstone.sample;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.powerstone.web.paging.PagingController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author vli
 * 
 */
public class UserPagingController extends PagingController {

	private final Logger logger = Logger.getLogger(this.getClass());

	private UserDAO userDAO;

	protected ModelAndView doHandleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map map = super.getSearchCriteria(request);
		int beginNo = super.computeRecordsBeginNo(request);
		int recordsNumber = super.computeRecordsNumberToRead(request);
		List usersList = userDAO.findUsers(map, beginNo, recordsNumber);
		logger.debug(usersList);
		return new ModelAndView("usersList", "usersList", usersList);
	}

	protected int getTotalRecordsNumber(HttpServletRequest request) {
		int result = userDAO.countUsers(super.getSearchCriteria(request));
		logger.debug("users number================:" + result);
		return result;
	}

	protected Map makeSearchCriteria(HttpServletRequest request) {
		HashMap map = new HashMap();
		String firstName = request.getParameter("firstName");
		if (firstName != null && firstName.trim().length() > 0) {
			map.put("firstName", firstName);
		}
		logger.debug(map);
		return map;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
}