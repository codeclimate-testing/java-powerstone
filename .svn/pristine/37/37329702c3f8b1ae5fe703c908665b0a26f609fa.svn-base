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

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class ResponseWriter {
  public static void parentReload(HttpServletResponse response) {
    try {
      PrintWriter out = (PrintWriter) response.getWriter();
      out.print("<script language=\"JavaScript\">");
      out.print("parent.location.reload();");
      out.print("</script>");
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void parentLeftReload(HttpServletResponse response) {
    try {
      PrintWriter out = (PrintWriter) response.getWriter();
      out.print("<script language=\"JavaScript\">");
      out.print("parent.leftFrame.location.reload();");
      out.print("</script>");
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }


  public static void openerReloadClose(HttpServletResponse response) {
    try {
      PrintWriter out = (PrintWriter) response.getWriter();
      out.print("<script language=\"JavaScript\">");
      out.print("opener.location.reload();window.close();");
      out.print("</script>");
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void openerParentReloadClose(HttpServletResponse response) {
    try {
      PrintWriter out = (PrintWriter) response.getWriter();
      out.print("<script language=\"JavaScript\">");
      out.print("opener.parent.location.reload();window.close();");
      out.print("</script>");
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
