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
package org.powerstone.ca;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.powerstone.ca.CADelegater;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import javax.servlet.http.HttpServletRequest;
import org.powerstone.ca.model.User;

public class CAFilter
    implements Filter {
  private static Log log = LogFactory.getLog(CAFilter.class);
  private String WEB_MODULE_ID;
  private String LOG_IN_URL;
  private String ERROR_URL;
  public static final String DESTINATION_URL = "destination_url";
  public static final String DEFAULT_CA_DELEGATER_BEAN_NAME = "caDelegater";
  private String caDelegaterBeanName = DEFAULT_CA_DELEGATER_BEAN_NAME;

  private static CADelegater caDelegater;

  private static FilterConfig theConfig;

  public void init(FilterConfig config) throws javax.servlet.ServletException {
    theConfig = config;
    try {
      this.WEB_MODULE_ID =
          config.getServletContext().getInitParameter("webModuleID");

      this.LOG_IN_URL = config.getInitParameter("LOG_IN_URL");
      this.ERROR_URL = config.getInitParameter("ERROR_URL");

      if (config.getServletContext().getInitParameter("caDelegaterBeanName") != null) {
        this.caDelegaterBeanName =
            config.getServletContext().getInitParameter("caDelegaterBeanName");
      }

      WebApplicationContext wac =
          WebApplicationContextUtils.getRequiredWebApplicationContext(config.
          getServletContext());

      if (wac != null) {
        caDelegater = (CADelegater) wac.getBean(caDelegaterBeanName);
        if (log.isDebugEnabled()) {
          log.debug("CAFilter initialized successfully[caDelegater=" + caDelegater +
                    "|webModuleID=" + WEB_MODULE_ID + "]");
        }
      }
      else {
        log.warn("can not get WebApplicationContext");
      }
    }
    catch (Exception e) {
      log.warn(e);
    }
  }

  public void doFilter(ServletRequest request,
                       ServletResponse response,
                       FilterChain chain) throws java.io.IOException,
      javax.servlet.ServletException {
    if (caDelegater == null) {
      this.init(theConfig);
    }

    HttpServletRequest hrequest = (HttpServletRequest) request;
    String uri = hrequest.getServletPath();
    String userID = caDelegater.getRemoteUser(hrequest);

    if (log.isDebugEnabled()) {
      log.debug("User[" + userID + "]is requesting[" + uri + "]");
    }

    if (uri.startsWith(LOG_IN_URL)) {
      if (log.isDebugEnabled()) {
        log.debug("User[" + userID + "]wants to log in");
      }
      chain.doFilter(request, response);
      return;
    }
    if (!userID.equals(User.ANONYMOUS_USER_ID) &&
        caDelegater.hasRightToDo(userID, uri, WEB_MODULE_ID)) {
      chain.doFilter(request, response);
      hrequest.getSession(true).removeAttribute(this.DESTINATION_URL);

      return;
    }
    else {
      hrequest.getSession().setAttribute(this.DESTINATION_URL,uri);
      log.warn("User[" + userID + "]'s request for[" + uri + "]was refused!");
      hrequest.getRequestDispatcher(ERROR_URL).forward(request, response);
      return;
    }
  }

  public void destroy() {
    caDelegater = null;
  }
}
