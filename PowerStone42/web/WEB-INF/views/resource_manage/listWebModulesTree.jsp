<%@page  pageEncoding="GBK"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title></title>
<LINK href="<%=request.getContextPath()%>/img/PowerStone.css" type="text/css" rel="stylesheet">
<script language="JavaScript">
var webModuleSelected="";
//ѡ�б�ɫ
function changeColor(hrefID){
	webModuleSelected=hrefID;
	divHref = document.all.tags("font");
        for (i=0; i<divHref.length; i++) {
          divHref(i).parentNode.parentNode.className = "tdTreeNode";
          if(divHref(i).id==hrefID){
             divHref(i).parentNode.parentNode.className = "tdTreeNodeASelected";
             curChannelID = divHref(i).id;
          }
        }
}
</script>
</head>

<body >
 <table class="tableNoBorder">
  <tr>
    <td valign="top">
 <table class="tableNoBorder">
<logic:iterate id="currWebModule" name="allModules" type="org.powerstone.ca.model.WebModule">
<div class='tdTreeNode' valign="top" style="display:block">
  <img src="<%=request.getContextPath()%>/img/spacer.gif" border="0" align="absmiddle">
  <img src="<%=request.getContextPath()%>/img/blank.gif" border="0" align="absmiddle">&nbsp;
<a href="<%=request.getContextPath()%>/resource_manage/list_web_module_resources.resource?webModuleID=<%=currWebModule.getWebModuleID()%>"
          target="mainFrame" onclick="changeColor('<%=currWebModule.getWebModuleID()%>')">
      <font style='font-weight: bold;' id="<%=currWebModule.getWebModuleID()%>">
          <%=currWebModule.getWebModuleName()%>
      </font>
</a>
</div>
</logic:iterate>
</table>
   </td>
  </tr>
</table>
</body>

</html>
