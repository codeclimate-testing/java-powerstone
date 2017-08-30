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

import org.powerstone.acegi.util.AuthenticationUtil;
import org.powerstone.ca.CADelegater;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import javax.servlet.jsp.PageContext;

public class CATagSupport extends TagSupport {

    private static final long serialVersionUID = 1L;
    protected static Log log = LogFactory.getLog(CATagSupport.class);
    public static final String DEFAULT_CA_DELEGATER_BEAN_NAME = "caDelegater";
    private String caDelegaterBeanName = DEFAULT_CA_DELEGATER_BEAN_NAME;
    private static CADelegater caDelegater;
    
    private static AuthenticationUtil authenticationUtil;
    private String authenticationUtilBeanName = "authenticationUtil";

    private static WebApplicationContext wac;

    public CATagSupport() {
        super();
    }

    protected CADelegater getCaDelegater(PageContext pageContext) {
        if (pageContext.getServletContext().getInitParameter("caDelegaterBeanName") != null) {
            this.caDelegaterBeanName = pageContext.getServletContext().getInitParameter("caDelegaterBeanName");
        }
        if (wac == null) {
            wac = WebApplicationContextUtils.getRequiredWebApplicationContext(pageContext.getServletContext());
        }
        if (caDelegater == null) {
            caDelegater = (CADelegater) wac.getBean(caDelegaterBeanName);
            if (log.isDebugEnabled()) {
                log.debug("CATagSupport[caDelegater=" + caDelegater + "]");
            }
        }
        return caDelegater;
    }

    protected AuthenticationUtil getAuthenticationUtil(PageContext pageContext) {
        if (pageContext.getServletContext().getInitParameter("authenticationUtilBeanName") != null) {
            this.authenticationUtilBeanName = pageContext.getServletContext().getInitParameter("authenticationUtilBeanName");
        }
        if (wac == null) {
            wac = WebApplicationContextUtils.getRequiredWebApplicationContext(pageContext.getServletContext());
        }
        if (authenticationUtil == null) {
            authenticationUtil = (AuthenticationUtil) wac.getBean(authenticationUtilBeanName);
            if (log.isDebugEnabled()) {
                log.debug("CATagSupport[authenticationUtil=" + authenticationUtil + "]");
            }
        }
        return authenticationUtil;
    }

    protected String getWebModuleID(PageContext pageContext) {
        return pageContext.getServletContext().getInitParameter("webModuleID");
    }
}
