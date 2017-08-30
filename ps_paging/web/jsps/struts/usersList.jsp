<%@ page contentType="text/html;charset=GBK" language="java" 
import="org.powerstone.web.paging.PagingTag" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/ps-paging.tld" prefix="page" %>

<link rel="stylesheet" href="<%=request.getContextPath()%>/scripts/index.css"
 type="text/css">
<title>用户列表</title>

<table width="540" border="0" cellspacing="1" cellpadding="1" align=center>
<tr bordercolor="#F4F9FF" bgcolor="#F4F9FF">
	<td width="10%" align=center>id</td>
	<td width="45%" align=center>firstName</td>
	<td width="40%" align=center>lastName</td>
	<td width="5%" align=center>No</td>
</tr>
<logic:iterate id="user" name="usersList" type="org.powerstone.sample.User" indexId="ie">
	<tr>
		<td align=center><%=user.getId()%></td>
		<td align=center><%=user.getFirstName()!=null?user.getFirstName():""%></td>
		<td align=center><%=user.getLastName()!=null?user.getLastName():""%></td>
		<td align=center><%=PagingTag.computeRowNo(request)+ie.intValue()%></td>
	</tr>
</logic:iterate>
<br/>
<page:pagebar url="/user_query.do?" bordercolor="#F4F9FF" bgcolor="#F4F9FF"/>
<tr><td align=center>
<button onclick="location.href='<%=request.getContextPath()%>/'">返回</button>
</td>
</tr>
</table>