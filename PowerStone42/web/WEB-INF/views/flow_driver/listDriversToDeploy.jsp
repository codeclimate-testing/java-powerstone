<%@page  pageEncoding="GBK"  %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%
    response.setHeader("Pragma","No-cache");
    response.setHeader("Cache-Control","no-cache");
    response.setDateHeader("Expires", 0);
%>
<html>
<head>
<title>
listRSActivity
</title>
</head>
<body bgcolor="#ffffff">
<label>
</label>
<c:if test="${!empty allDrivers}">
<form action="<%=request.getContextPath()%>/wf/update_flow_node_binding.dpl"
  target="_parent">
  <input type="hidden" name="currentdriverID" value="<%=request.getAttribute("currentdriverID")%>" />
  <input type="hidden" name="flowDeployID" value="<%=request.getAttribute("flowDeployID")%>" />
  <input type="hidden" name="flowNodeID" value="<%=request.getAttribute("flowNodeID")%>" />

<c:forEach items="${allDrivers}" var="theDriver">

  <p align="center">
    <input type="radio" name="flowDriverID"
      value='<c:out value="${theDriver.flowDriverID}"/>' />
      <c:out value="${theDriver.flowDriverName}"/>
</p>
</c:forEach>
  <p align="center"><input type="submit" value='<fmt:message key="global.ok"/>' /></p>
</form>
</c:if>
</body>
</html>
