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
package org.powerstone.workflow.util;

import org.powerstone.ca.model.Group;

public class GroupTreeNode
    implements TreeNode {
  private Group group;
  public GroupTreeNode(Group g) {
    this.group = g;
  }

  public String getNodeID() {
    return group.getGroupID().toString();
  }

  public String getNodeName() {
    return group.getGroupName();
  }

  public String getParentID() {
    return group.getParentGroup() == null ? null :
        (group.getParentGroup().getGroupID().toString());
  }

}
