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
 */
package org.powerstone.ca.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.powerstone.ca.model.User;
import org.powerstone.ca.service.UserManager;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserFormValidator implements Validator {
	protected final Log log = LogFactory.getLog(getClass());

	private UserManager userManager;

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public boolean supports(Class clazz) {
		return true;
	}

	public void validate(Object obj, Errors errors) {
		User user = (User) obj;
		log.info("Validating " + user);
		User userWithSameUserName = userManager.findUserByUserName(user
				.getUserName());
		User userWithSameEmail = userManager.findUserByEmail(user.getEmail());
		log.info(userWithSameUserName + "|||" + userWithSameEmail);

		if (userWithSameUserName != null
				&& !userWithSameUserName.getId().equals(user.getId())) {
			errors.rejectValue("userName", "error.ca.duplicate_userName",
					new Object[] { user.getUserName() }, "duplicate_userName");
		} else if (userWithSameEmail != null
				&& !userWithSameEmail.getId().equals(user.getId())) {
			errors.rejectValue("email", "error.ca.duplicate_email",
					new Object[] { user.getEmail() }, "duplicate_email");
		}
	}
}