<%@page  pageEncoding="GBK"  %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title><fmt:message key="userOrgnize.title"/></title>
<link href="<%=request.getContextPath()%>/img/PowerStone.css" rel="stylesheet" type="text/css">
</head>

<frameset rows="*" cols="200,*" frameborder="NO" border="0" framespacing="0">

<frame src='<%=request.getContextPath()%>/wf/list_user_organize.pfm?nodeBindingID=<c:out value="${nodeBindingID}"/>'
       name="Sys_Navi" scrolling="YES">
<frame src="<%=request.getContextPath()%>/wf/list_members_to_add_performer.pfm" name="Sys_Content" id="bottomFrame"
       scrolling="YES" >

</frameset>
<noframes><body>

</body></noframes>
