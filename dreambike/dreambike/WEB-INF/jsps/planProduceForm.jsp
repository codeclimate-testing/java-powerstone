<%@page pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://www.springmodules.org/tags/commons-validator" prefix="v" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
<title>
planProduceForm
</title>
<LINK href="<%=request.getContextPath()%>/img/PowerStone.css" type="text/css" rel="stylesheet">
</head>
<table class="tableFrame" cellspacing="1">
  <tr>
     <td class="tdTitle">生产计划</td>
  </tr>
<td class="tdContent">
  <table class="tableNoBorderCenter"  align="center">
<spring:bind path="produce.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">
        <c:forEach var="error" items="${status.errorMessages}">
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>

<form method="post" action="<%=request.getContextPath()%>/dreambike/edit_plan_produce.do" name="produceForm">
    <spring:bind path="produce.produceID">
<table class="tableNoBorderCenter" align="center">
<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>"/>
    </spring:bind>
<c:if test="${not empty orderID}">
<input type="hidden" name="orderID" value="<c:out value="${orderID}"/>"/>
</c:if>

<tr>
    <th><label for="producePlan">
        producePlan:</label></th>
    <td>
        <spring:bind path="produce.producePlan">
<textarea name="<c:out value="${status.expression}"/>"
cols="15" rows="5"><c:out value="${status.value}"/></textarea>
        <span class="fieldError"><c:out value="${status.errorMessage}"/></span>
        </spring:bind>
    </td>
</tr>
<tr>
    <td></td>
    <td>
        <input type="submit" class="button_default" name="save" value="Save"/>
        <input type="submit" class="button_default" name="submitTask" value="submitTask"/>
    </td>
</tr>
</table>
</form>
  </table>
 </td>
</table>
</body>
</html>

<script type="text/javascript">
    document.forms["produceForm"].elements["producePlan"].focus();
</script>
