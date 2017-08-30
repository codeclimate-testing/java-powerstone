<%@page  pageEncoding="GBK"  %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>

<html>
<head>
<title>success</title>
<LINK href="<%=request.getContextPath()%>/img/PowerStone.css" type="text/css" rel="stylesheet">
<script>
parent.leftFrame.location.replace("<%=request.getContextPath()%>/role_manage/list_roles.role");
</script>
</head>
<body >
<table width="100%">
  <tr>
     <td colspan="2">
<c:if test="${not empty message}">
    <div class="message"><c:out value="${message}" escapeXml="false"/></div>
    <%request.getSession().removeAttribute("message");%>
</c:if>
  </td>
  </tr>
</table>
</body>
</html>
