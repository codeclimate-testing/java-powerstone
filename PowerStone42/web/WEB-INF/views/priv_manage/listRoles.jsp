<%@page  pageEncoding="GBK"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title></title>
<LINK href="<%=request.getContextPath()%>/img/PowerStone.css" type="text/css" rel="stylesheet">
<script language="JavaScript">
var roleSelected="";
//选中变色
function changeColor(hrefID){
	roleSelected=hrefID;
	divHref = document.all.tags("font");
	for (i=0; i<divHref.length; i++) {
		if(divHref(i).id!="cons") {
			divHref(i).color = "#000000";
			if(divHref(i).id==hrefID){
				divHref(i).color = "red";
				curChannelID = divHref(i).id;
			}
		}
	}
}
</script>
</head>

<body>
<table class="tableFrame" cellspacing="1">
<tr>
    <td class="tdTitle">角色列表</td>
</tr>
<tr>
    <td class="tdContent" valign="top">
      <table class="tableNoBorder">

<logic:iterate id="currRole" name="allRoles" type="org.powerstone.ca.model.Role">
<div class='tdTreeNode' valign="top" style="display:block">
  <img src="<%=request.getContextPath()%>/img/spacer.gif" border="0" align="absmiddle">
  <img src="<%=request.getContextPath()%>/img/blank.gif" border="0" align="absmiddle">&nbsp;
    <a href="<%=request.getContextPath()%>/priv_manage/show_role_privileges.priv?roleID=<%=currRole.getRoleID()%>"
      target="mainFrame" onclick="changeColor('<%=currRole.getRoleID()%>')">
      <font style='font-weight: bold;' id="<%=currRole.getRoleID()%>">
        <%=currRole.getRoleName()%>
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
