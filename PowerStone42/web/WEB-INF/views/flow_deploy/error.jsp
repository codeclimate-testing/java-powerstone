<%@page  pageEncoding="GBK"  %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<html>
<head>
<title>
oh
</title>
</head>
<body bgcolor="#ffffff">
<fmt:message key="<%=(String)request.getAttribute("message")%>" />
</body>
</html>
