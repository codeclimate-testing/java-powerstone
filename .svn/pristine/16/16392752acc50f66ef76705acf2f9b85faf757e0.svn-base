<%@page  pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>success</title>
<LINK href="<%=request.getContextPath()%>/img/PowerStone.css" type="text/css" rel="stylesheet">
<script>
parent.leftFrame.location.replace("<%=request.getContextPath()%>/resource_manage/list_web_modules.resource");
</script>
</head>

<body>
<table class="tableFrame" cellspacing="1">
  <tr>
    <td class="tdTitle">成功</td>
  </tr>

  <tr>
   <td class="tdContent">
     <div align="center" class="SUCCESS">
       <c:if test="${not empty message}">
         <c:out value="${message}" escapeXml="false"/>
         <%request.getSession().removeAttribute("message");%>
       </c:if>
       <c:if test="${empty message}">
         未做修改！
       </c:if>
     </div>
    </td>
  </tr>

</table>
</body>
</html>
