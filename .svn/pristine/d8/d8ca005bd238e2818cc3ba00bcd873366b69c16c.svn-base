/*
 * Copyright 2004-2005 the original author or authors.
 *
 * Licensed under the LGPL license, Version 2.1 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.gnu.org/copyleft/lesser.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author daquanda(liyingquan@gmail.com)
 * @author kevin(diamond_china@msn.com)
 */
package org.powerstone.ca.web;

import java.lang.reflect.InvocationTargetException;

import org.springframework.web.servlet.mvc.SimpleFormController;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.powerstone.ca.service.UserManager;
import org.powerstone.ca.model.User;
import javax.servlet.ServletException;
import org.powerstone.ca.service.GroupManager;

public class UserFormController extends SimpleFormController {
	private final Log log = LogFactory.getLog(UserFormController.class);

	private UserManager userManager;

	private GroupManager groupManager = null;

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public void setGroupManager(GroupManager groupManager) {
		this.groupManager = groupManager;
	}

	public ModelAndView processFormSubmission(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		if (request.getParameter("cancel") != null) {
			return new ModelAndView(getSuccessView() + "?groupID="
					+ request.getParameter("groupID") + "&pageTo="
					+ request.getParameter("pageTo"));
		}
		return super.processFormSubmission(request, response, command, errors);
	}

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		User user = (User) command;
		String pass = user.getPassword();
		String groupID = request.getParameter("groupID");
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method..." + user + "group["
					+ groupID + "]");
		}
		// try {
		if (user.getId().longValue() == -1) {
			userManager.registerUser(user);
			if (groupID != null) {
				groupManager.addUserToGroup(user.getId().toString(), groupID);
			}
		} else {
			userManager.updateUser(user);
		}
		return new ModelAndView(getSuccessView() + "?groupID=" + groupID
				+ "&pageTo=" + request.getParameter("pageTo"));
		// }

		// catch (DataIntegrityViolationException e) {
		// log.error(e);
		// User userWithSameUserName = userManager.findUserByUserName(user.
		// getUserName());
		// User userWithSameEmail =
		// userManager.findUserByEmail(user.getEmail());
		//
		// if (userWithSameUserName != null &&
		// !userWithSameUserName.getId().equals(user.getId())
		// ) {
		// errors.rejectValue("userName", "duplicate_userName",
		// getText("error.ca.duplicate_userName",
		// user.getUserName()));
		// }
		// else if (userWithSameEmail != null &&
		// !userWithSameEmail.getId().equals(user.getId())) {
		// errors.rejectValue("email", "duplicate_email",
		// getText("error.ca.duplicate_email", user.getEmail()));
		// }
		// else {
		// throw e;
		// }
		// user.setPassword(pass);
		// user.setConfirmPass(pass);
		// if (log.isDebugEnabled()) {
		// log.debug(user);
		// }
		// return showForm(request, response, errors);
		// }
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws ServletException {
		String userID = request.getParameter("id");
		String groupID = request.getParameter("groupID");
		if (log.isDebugEnabled()) {
			log.debug("entering 'formBackingObject' user[" + userID + "]group["
					+ groupID + "]");
		}
		if (groupID != null) {
			request.setAttribute("groupID", groupID);
		}

		request.setAttribute("pageTo", request.getParameter("pageTo"));

		if ((userID != null) && !userID.equals("-1")) {
			request.setAttribute("id", userID);
			User user = userManager.findUser(userID);
			User result = new User();
			try {
				BeanUtils.copyProperties(result, user);
			} catch (IllegalAccessException e) {
				log.error(e);
			} catch (InvocationTargetException e) {
				log.error(e);
			}
			result.setConfirmPass(result.getPassword());
			return result;
		} else {
			return new User();
		}
	}

	public String getText(String msgKey) {
		return getMessageSourceAccessor().getMessage(msgKey);
	}

	public String getText(String msgKey, String arg) {
		return getText(msgKey, new Object[] { arg });
	}

	public String getText(String msgKey, Object[] args) {
		return getMessageSourceAccessor().getMessage(msgKey, args);
	}
}
