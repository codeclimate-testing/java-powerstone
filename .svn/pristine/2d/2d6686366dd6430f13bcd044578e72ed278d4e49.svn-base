package org.appfuse.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.appfuse.model.User;
import org.appfuse.service.UserManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class UserFormController extends SimpleFormController {
	private final Log log = LogFactory.getLog(UserFormController.class);

	private UserManager mgr = null;

	public void setUserManager(UserManager userManager) {
		this.mgr = userManager;
	}

	public ModelAndView processFormSubmission(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		log.info((User) command);
		if (request.getParameter("cancel") != null) {
			if (request.getParameter("from") != null) {
				return new ModelAndView(getSuccessView() + "?from="
						+ request.getParameter("from"));
			} else {
				return new ModelAndView(getSuccessView());
			}
		}

		return super.processFormSubmission(request, response, command, errors);
	}

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		log.info("========entering 'onSubmit' method...");

		User user = (User) command;

		if (request.getParameter("delete") != null) {
			mgr.removeUser(user.getId().toString());
			request.getSession().setAttribute("message",
					getText("user.deleted", user.getFullName()));
		} else {
			mgr.saveUser(user);
			request.getSession().setAttribute("message",
					getText("user.saved", user.getFullName()));
		}

		if (request.getParameter("from") != null) {
			return new ModelAndView(getSuccessView() + "?from="
					+ request.getParameter("from"));
		} else {
			return new ModelAndView(getSuccessView());
		}
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws ServletException {
		String userId = request.getParameter("id");

		if ((userId != null) && !userId.equals("")) {
			User user = mgr.getUser(userId);

			if (user == null) {
				return new User();
			}

			return user;
		} else {
			return new User();
		}
	}

	/**
	 * Convenience method for getting a i18n key's value. Calling
	 * getMessageSourceAccessor() is used because the RequestContext variable is
	 * not set in unit tests b/c there's no DispatchServlet Request.
	 * 
	 * @param msgKey
	 * @return
	 */
	public String getText(String msgKey) {
		return getMessageSourceAccessor().getMessage(msgKey);
	}

	/**
	 * Convenient method for getting a i18n key's value with a single string
	 * argument.
	 * 
	 * @param msgKey
	 * @param arg
	 * @return
	 */
	public String getText(String msgKey, String arg) {
		return getText(msgKey, new Object[] { arg });
	}

	/**
	 * Convenience method for getting a i18n key's value with arguments.
	 * 
	 * @param msgKey
	 * @param args
	 * @return
	 */
	public String getText(String msgKey, Object[] args) {
		return getMessageSourceAccessor().getMessage(msgKey, args);
	}
}
