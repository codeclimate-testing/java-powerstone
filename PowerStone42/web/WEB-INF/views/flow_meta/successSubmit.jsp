<%@page  pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>success</title>
<LINK href="<%=request.getContextPath()%>/img/PowerStone.css" type="text/css" rel="stylesheet">
<script>
parent.leftFrame.location.replace("<%=request.getContextPath()%>/wf/main.bt");
</script>
</head>

<body>
<table class="tableFrame" cellspacing="1">
  <tr>
    <td class="tdTitle">成功</td>
  </tr>
  <tr>
   <td class="tdContent">
<c:if test="${not empty message}">
    <div align="center" class="SUCCESS"><c:out value="${message}" escapeXml="false"/></div>
    <%request.getSession().removeAttribute("message");%>
</c:if>
    </td>
  </tr>
</table>
</body>
</html>
