<%@page  pageEncoding="GBK"  %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%
    response.setHeader("Pragma","No-cache");
    response.setHeader("Cache-Control","no-cache");
    response.setDateHeader("Expires", 0);
%>

<html>
<link href="<%=request.getContextPath()%>/img/PowerStone.css" rel="stylesheet" type="text/css">
<body bgcolor="#ffffff">
<form action='<%=request.getContextPath()%>/wf/add_user_performer.pfm?nodeBindingID=<c:out value="${nodeBindingID}"/>'
      name="membersList" target="_top"
      method="POST">
<c:if test="${!empty groupMembers}">
<font class="ldh-12-20"><b><fmt:message key="performer.group_users"/></b></font>
<table width="99%" border="1" align="center" class="ldh-12-20"
  cellpadding="1" cellspacing="0" bordercolordark="#A9C9E2"
  bordercolorlight="#ffffff">
  <tr>
    <td width="10%" bgcolor="#F1F7FF"> <div align="center">&nbsp;</div></td>
    <td width="25%" bgcolor="#F1F7FF"> <div align="center"><fmt:message key="performer.userID"/></div></td>
    <td width="25%" bgcolor="#F1F7FF"> <div align="center"><fmt:message key="performer.email"/></div></td>
  </tr>

<c:forEach items="${groupMembers}" var="currMember">
<tr align="center">
<td height="25">
<input type="checkbox"
  value='<c:out value="${currMember.id}"/>' name="userToAdd">
</td><td height="25">
&nbsp;<c:out value="${currMember.userName}"/>
</td><td height="25">
&nbsp;<c:out value="${currMember.email}"/>
</td>
</tr>
</c:forEach>

</table>
<input type="button" value='<fmt:message key="global.ok"/>' onclick="add()" class="button-7">
</c:if>

<c:if test="${empty groupMembers}">
  <font class="ldh-12-20"><b><fmt:message key="performer.group_users"/></b></font>
</c:if>

</form>

</body>
</html>
<SCRIPT LANGUAGE="JavaScript">
<!--
function add(){
     document.membersList.submit();
}
//-->
</SCRIPT>
