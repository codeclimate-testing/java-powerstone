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

import org.powerstone.ca.model.Group;
import java.util.Iterator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GroupSelectTreeMaker {
  private static Log log = LogFactory.getLog(GroupSelectTreeMaker.class);
  public static String printSelectTreeMaker(String result, Group theGroup,
                                            String space) {
    result += "<option value=\"" + theGroup.getGroupID() + "\">" +
        space + theGroup.getGroupName() + "</option>";
    if (log.isDebugEnabled()) {
      log.debug(theGroup.getGroupName()+"|"+theGroup.getChildGroups().size()+"->["+result+"]");
    }

    //<option value="<%=theGroup.getGroupID()%>"><%=space+theGroup.getGroupName()%></option>
    if (theGroup.getChildGroups().size() > 0) {
      for (Iterator its = theGroup.getChildGroups().iterator(); its.hasNext(); ) {
        Group sonGroup = (Group) its.next();
        result =printSelectTreeMaker(result, sonGroup, space + "&nbsp;" + "&nbsp;");
      }
    }

    return result;
  }
}
