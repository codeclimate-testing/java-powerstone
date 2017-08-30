<%@page  pageEncoding="GBK"  %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<h3>Data Access Failure</h3>
<p>
  <c:out value="${requestScope.exception}"/>|
    <c:out value="${requestScope.exception.message}"/>
</p>

<!--
<%
Exception ex = (Exception) request.getAttribute("exception");
ex.printStackTrace(new java.io.PrintWriter(out));
%>
-->

<a href="<c:url value='/'/>">&#171; Home</a>
