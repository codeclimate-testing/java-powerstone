<%@page pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://www.springmodules.org/tags/commons-validator" prefix="v" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
<title>
emailReceiveNoteForm
</title>
<LINK href="<%=request.getContextPath()%>/img/PowerStone.css" type="text/css" rel="stylesheet">
</head>
<table class="tableFrame" cellspacing="1">
  <tr>
     <td class="tdTitle">通知客户准备收货</td>
  </tr>
<td class="tdContent">
  <table class="tableNoBorderCenter"  align="center">
<spring:bind path="order.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">
        <c:forEach var="error" items="${status.errorMessages}">
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>

<form method="post" action="<%=request.getContextPath()%>/dreambike/edit_email_receive_note.do" name="orderForm">
<table class="tableNoBorderCenter" align="center">
  <spring:bind path="order.orderID">
        <input type="hidden" name="orderID" value="<c:out value="${status.value}"/>"/>
    </spring:bind>
<tr>
    <th><label for="reference">
        Some reference:</label></th>
    <td>
customeID[<c:out value="${_customeID}"/>]<br />
customeEmail[<c:out value="${_customeEmail}"/>]<br />
price[<c:out value="${_price}"/>]<br />
priceDetail[<c:out value="${_pricedetail}"/>]<br />
orderID[<c:out value="${_orderID}"/>]
</td>
</tr>
<tr>
    <th><label for="emailReceiveNote">
        emailReceiveNote:</label></th>
    <td>
        <spring:bind path="order.emailReceiveNote">
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
    document.forms["orderForm"].elements["emailReceiveNote"].focus();
</script>
