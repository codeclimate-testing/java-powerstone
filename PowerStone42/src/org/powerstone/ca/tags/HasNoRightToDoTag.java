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
package org.powerstone.ca.tags;

import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.JspException;
import javax.servlet.http.HttpServletRequest;

import org.powerstone.acegi.util.AuthenticationUtil;
import org.powerstone.ca.model.User;

public class HasNoRightToDoTag extends CATagSupport {
    private String url;

    public int doStartTag() throws JspException {
        AuthenticationUtil authenticationUtil = getAuthenticationUtil(pageContext);
        String userName = authenticationUtil.getCurrentUser();
        if (log.isDebugEnabled()) {
            log.debug("[userName=" + userName + "|url=" + url + "]");
        }
        if (url == null) {
            return TagSupport.EVAL_BODY_INCLUDE;
        }

        String[] dd = url.split("&amp;");
        for (int i = 0; i < dd.length; i++) {
            if (authenticationUtil.isAccessableTo(dd[i])) {
                return TagSupport.SKIP_BODY;
            }
        }

        if (log.isDebugEnabled()) {
            log.debug("User[" + userName + "]hasNoRightToDo with anyone of url(s)[" + url + "]");
        }
        return TagSupport.EVAL_BODY_INCLUDE;
    }

    public int doEndTag() throws JspException {
        return TagSupport.EVAL_PAGE;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
