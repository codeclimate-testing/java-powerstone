<%@page  pageEncoding="GBK"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>

<title><fmt:message key="userForm.title"/></title>

<table width="540" border="0" cellspacing="1" cellpadding="1" align=center>
<tr bordercolor="#F4F9FF" bgcolor="#F4F9FF">
	<td width="10%" align=center>id</td>
	<td width="45%" align=center>firstName</td>
	<td width="45%" align=center>lastName</td>
</tr>
<logic:iterate id="user" name="users" type="org.appfuse.model.User">
	<tr>
		<td align=center><a href='<%=request.getContextPath()%>/editUser.html?id=<%=user.getId()%>'><%=user.getId()%></a></td>
		<td align=center><%=user.getFirstName()!=null?user.getFirstName():""%></td>
		<td align=center><%=user.getLastName()!=null?user.getLastName():""%></td>
	</tr>
</logic:iterate>
<tr><td align=right>
<button onclick="location.href='editUser.html'">Add User</button>
</td>
</tr>
</table>