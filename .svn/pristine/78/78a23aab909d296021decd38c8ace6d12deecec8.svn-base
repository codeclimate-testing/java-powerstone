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

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import org.powerstone.ca.model.GivenRight;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TreeMaker {
  private static Log log = LogFactory.getLog(TreeMaker.class);
  private String _hrefLink;
  private String _contextPath;
  private String _targetFrame;
  private String _checkboxName;
  List _nodesList;
  public TreeMaker(String href, String contextPath,
                   String targetFrame,
                   String checkboxName,
                   List nodesList) {
    this._hrefLink = href;
    this._contextPath = contextPath;
    this._targetFrame = targetFrame;
    this._checkboxName = checkboxName;
    this._nodesList = nodesList;
  }

//------------------------------------------------------------------------------
  public List makeTree() {
    return this.printTree(this.processNodes(this._nodesList));
  }

//------------------------------------------------------------------------------
  public boolean hasSon(String nodeID) {
    Iterator it = _nodesList.iterator();
    while (it.hasNext()) {
      TreeNode node = (TreeNode) it.next();
      if (nodeID.equals(node.getParentID())) {
        return true;
      }
    }
    return false;
  }

//------------------------------------------------------------------------------
  /**
   * 给每个节点编号，并排序
   * make number for all nodes, and sort order of all nodes
   * @param nodeColl Collection
   * @return ArrayList
   */
  public List processNodes(List nodesList) {
    ArrayList resultTree = new ArrayList();
    Iterator it = nodesList.iterator();
    int i = 0;
    while (it.hasNext()) {
      TreeNode anode = (TreeNode) it.next();
      /**
       * has no father
       * add id, add node, add all son
       * nextid++,
       */
      if (anode.getParentID() == null || anode.getParentID().trim().equals("")) {
        resultTree.add(new Integer(i).toString());
        resultTree.add(anode);
        if (this.hasSon(anode.getNodeID())) {
          makeTree(nodesList, resultTree, anode, (new Integer(i)).toString());
        }
        i++;
      }
    }
    if(log.isDebugEnabled()){
      log.debug(resultTree);
    }
    return resultTree;
  }

//------------------------------------------------------------------------------
  private void makeTree(List source, List target,
                        TreeNode node, String head) {
    Iterator it = source.iterator();
    int i = 0;
    while (it.hasNext()) {
      TreeNode anode = (TreeNode) it.next();
      if (anode.getParentID() != null &&
          anode.getParentID().equals(node.getNodeID())) {
        String me = head + "-" + (new Integer(i)).toString();
        target.add(me);
        target.add(anode);
        makeTree(source, target, anode, me);
        i++;
      }
    }
  }

//------------------------------------------------------------------------------
  private void makeTree(List source, List target,
                        TreeNode node, String head, boolean forRole) {
    Iterator it = source.iterator();
    int i = 0;
    while (it.hasNext()) {
      TreeNode anode = (TreeNode) it.next();
      if (anode.getParentID() != null &&
          anode.getParentID().equals(node.getNodeID())) {
        String me = head + "-" + (new Integer(i)).toString();
        target.add(me);
        target.add(anode);
        makeTree(source, target, anode, me, forRole);
        i++;
      }
    }
  }

//------------------------------------------------------------------------------
  /**
   * 把编号并排序的节点组合成为table
   * make processNodes to table , and print table
   * @param source ArrayList
   * @return Collection
   */
  private List printTree(List source) {
    ArrayList outPut = new ArrayList();
    Iterator it = source.iterator();
    while (it.hasNext()) {
      String id = (String) it.next();
      TreeNode node = (TreeNode) it.next();
      String img = "";
      /**
       * has son : show plus
       * no son : show minus
       */
      if (this.hasSon(node.getNodeID())) {
        img = "<img align=absmiddle src=\"" + _contextPath + "/img/plus.gif\" border=0 id=" +
            id
            + " onClick=\"expandIt('" + id + "'); return false\">";
      }
      else {
        img = "<img align=absmiddle src=\"" + _contextPath + "/img/blank.gif\" border=0 id=" +
            id + ">";
      }
      /**
       * make a line from parent number to itselt number
       * 1,        1 lever
       * 1-1,      2 lever
       * 1-1-1,    3 lever
       */
      String nbsp = "";
      int level = this.countCharInString(id, '-');
      /**
       * lever++, 空格++
       */
      for (int i = 0; i < level; i++) {
        nbsp += " &nbsp; ";
      }
      String tableStr = "";
      String url = this._hrefLink + node.getNodeID();
      String imgSpacer =
          "<img align=absmiddle src=\"" + _contextPath + "/img/spacer.gif\" border=0 alt=\"\">";
      if (node.getParentID() == null ||
          node.getParentID().trim().equals("")) { //-------顶级分组no parent
        if (this._checkboxName == null ||
            this._checkboxName.trim().equals("")) { //no checkbox
          tableStr = "<div class='tdTreeNode' valign=\"top\" " +
              " id=" + id + " style=\"display:block\">" +
              nbsp + imgSpacer +
              img + "&nbsp;<a href=" + url + " target=" + _targetFrame +
              " onclick=changeColor('" + id + "','" + node.getNodeID() +
              "')><font style='font-weight: bold;' id=" + id + ">" + node.getNodeName() +
              "</font></a>" + "</div>";
        }
        else { //has checkbox
          tableStr = "<div class='tdTreeNode' valign=\"top\" " +
              " id=" + id + " style=\"display:block\">" +
              nbsp + imgSpacer +
              img + "<input type=checkbox name=" + _checkboxName + " value=" +
              node.getNodeID() + "><a href=" + url + " target=" + _targetFrame +
              " onclick=changeColor('" + id + "','" + node.getNodeID() +
              "')><font id=" + id + ">" + node.getNodeName() +
              "</font></a>" + "</div>";
        }
      }
      else { //has parent
        if (this._checkboxName == null ||
            this._checkboxName.trim().equals("")) { //no checkbox
          tableStr = "<div class='tdTreeNode' valign=\"top\" " +
              " id=" + id + " style=\"display:none\">" +
              nbsp + imgSpacer +
              img + "&nbsp;<a href=" + url + " target=" + _targetFrame +
              " onclick=changeColor('" + id + "','" + node.getNodeID() +
              "')><font id=" + id + ">" + node.getNodeName() +
              "</font></a>" + "</div>";
        }
        else { //has checkbox
          tableStr = "<div class='tdTreeNode' valign=\"top\" " +
              " id=" + id + " style=\"display:none\">" +
              nbsp + imgSpacer +
              img + "<input type=checkbox name=" + _checkboxName + " value=" +
              node.getNodeID() + "><a href=" + url + " target=" + _targetFrame +
              " onclick=changeColor('" + id + "','" + node.getNodeID() +
              "')><font id=" + id + ">" + node.getNodeName() +
              "</font></a>" + "</div>";
        }
      }
      outPut.add(tableStr);
      if(log.isDebugEnabled()){
        log.debug(tableStr);
      }
    }
    return outPut;
  }
//------------------------------------------------------------------------------
  private int countCharInString(String str, char target) {
    int i = 0;
    for (int j = 0; j < str.length(); j++) {
      if (str.charAt(j) == target) {
        i++;
      }
    }
    return i;
  }
}
