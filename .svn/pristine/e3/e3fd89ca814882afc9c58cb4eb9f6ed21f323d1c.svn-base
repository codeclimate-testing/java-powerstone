<%@page pageEncoding="GBK"%>
<%@ taglib uri="http://www.powerstone.org/powerstone/ca" prefix="ca"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<head>
<title>PowerStone</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
</head>
<ca:hasNoRightToDoTag
	url="/role_manage/**&amp;/user_group/**&amp;/priv_manage/**&amp;/wf/**">
	<c:redirect url="/login.jsp"></c:redirect>
</ca:hasNoRightToDoTag>
<frameset rows="66,*" cols="*" frameborder="NO" border="0"
	framespacing="0">
	<frame name="topFrame" scrolling="NO" noresize
		src="<%=request.getContextPath()%>/top.jsp">
	<frameset cols="230,*" frameborder="NO" border="1" framespacing="1"
		rows="*">
		<frame name="leftFrame" scrolling="no"
			src="<%=request.getContextPath()%>/user_group/list_groups.ca">
		<frame name="mainFrame"
			src="<%=request.getContextPath()%>/construction.jsp?fun_no=1">
	</frameset>
</frameset>
<noframes>
<body>
</body>
</noframes>
</html>
