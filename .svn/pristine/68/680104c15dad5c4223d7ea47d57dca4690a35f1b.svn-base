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
package org.powerstone.util;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class EncodingFilter
    implements Filter {
  public EncodingFilter() {
  }

  public void init(FilterConfig parm1) throws javax.servlet.ServletException {
  }

  public void doFilter(ServletRequest request,
                       ServletResponse response,
                       FilterChain chain) throws java.io.IOException,
      javax.servlet.ServletException {
    request.setCharacterEncoding("gb2312");

    HttpServletResponse hresponse = (HttpServletResponse) response;
    hresponse.setHeader("Pragma", "No-cache");
    hresponse.setHeader("Cache-Control", "no-cache");
    hresponse.setDateHeader("Expires", 0);

    chain.doFilter(request, response);
  }

  public void destroy() {
  }

}
