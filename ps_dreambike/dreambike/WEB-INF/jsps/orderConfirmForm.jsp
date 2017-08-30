<%@page pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://www.springmodules.org/tags/commons-validator" prefix="v" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
<title>
orderConfirmForm
</title>
<LINK href="<%=request.getContextPath()%>/img/PowerStone.css" type="text/css" rel="stylesheet">
</head>
<table class="tableFrame" cellspacing="1">
  <tr>
     <td class="tdTitle">最后确认</td>
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

<form method="post" action="<%=request.getContextPath()%>/dreambike/edit_order_confirm.do" name="orderForm">
<table class="tableNoBorderCenter" align="center">
  <spring:bind path="order.orderID">
        <input type="hidden" name="orderID" value="<c:out value="${status.value}"/>"/>
    </spring:bind>

<tr>
    <th><label for="productID">
        productID:</label></th>
    <td>
        <spring:bind path="order.productID">
        <input type="text" class="input1" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>"/>
        <span class="fieldError"><c:out value="${status.errorMessage}"/></span>
        </spring:bind>
    </td>
</tr>
<tr>
    <th><label for="customeID">
        customeID:</label></th>
    <td>
        <spring:bind path="order.customeID">
        <input type="text" class="input1" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>"/>
        <span class="fieldError"><c:out value="${status.errorMessage}"/></span>
        </spring:bind>
    </td>
</tr>
<tr>
    <th><label for="customeEmail">
        customeEmail:</label></th>
    <td>
        <spring:bind path="order.customeEmail">
        <input type="text" class="input1" id="memo" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>"/>
        <span class="fieldError"><c:out value="${status.errorMessage}"/></span>
        </spring:bind>
    </td>
</tr>
<tr>
    <th><label for="price">
        price:</label></th>
    <td>
        <spring:bind path="order.price">
        <input type="text" class="input1" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>"/>
        <span class="fieldError"><c:out value="${status.errorMessage}"/></span>
        </spring:bind>
    </td>
</tr>
<tr>
    <th><label for="priceDetail">
        priceDetail:</label></th>
    <td>
        <spring:bind path="order.priceDetail">
<textarea name="<c:out value="${status.expression}"/>"
cols="15" rows="5"><c:out value="${status.value}"/></textarea>
        <span class="fieldError"><c:out value="${status.errorMessage}"/></span>
        </spring:bind>
    </td>
</tr>
<tr>
    <th><label for="techState">
        techState:</label></th>
    <td>
        <spring:bind path="order.techState">
        <input type="text" class="input1" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>"/>
        <span class="fieldError"><c:out value="${status.errorMessage}"/></span>
        </spring:bind>
    </td>
</tr>
<tr>
    <th><label for="techDetail">
        techDetail:</label></th>
    <td>
        <spring:bind path="order.techDetail">
<textarea name="<c:out value="${status.expression}"/>"
cols="15" rows="5"><c:out value="${status.value}"/></textarea>
        <span class="fieldError"><c:out value="${status.errorMessage}"/></span>
        </spring:bind>
    </td>
</tr>
<tr>
    <th><label for="stockState">
        stockState:</label></th>
    <td>
        <spring:bind path="order.stockState">
        <input type="text" class="input1" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>"/>
        <span class="fieldError"><c:out value="${status.errorMessage}"/></span>
        </spring:bind>
    </td>
</tr>
<tr>
    <th><label for="stockDetail">
        stockDetail:</label></th>
    <td>
        <spring:bind path="order.stockDetail">
<textarea name="<c:out value="${status.expression}"/>"
cols="15" rows="5"><c:out value="${status.value}"/></textarea>
        <span class="fieldError"><c:out value="${status.errorMessage}"/></span>
        </spring:bind>
    </td>
</tr>
<tr>
    <th><label for="emailRefuse">
        emailRefuse:</label></th>
    <td>
        <spring:bind path="order.emailRefuse">
<textarea name="<c:out value="${status.expression}"/>"
cols="15" rows="5"><c:out value="${status.value}"/></textarea>
        <span class="fieldError"><c:out value="${status.errorMessage}"/></span>
        </spring:bind>
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

<c:if test="${not empty order.produce}">
<tr>
    <th><label for="producePlan">
        producePlan:</label></th>
    <td>
        <spring:bind path="order.produce.producePlan">
<textarea name="<c:out value="${status.expression}"/>"
cols="15" rows="5"><c:out value="${status.value}"/></textarea>
        <span class="fieldError"><c:out value="${status.errorMessage}"/></span>
        </spring:bind>
    </td>
</tr>
</c:if>

<c:if test="${not empty order.purchase}">
<tr>
    <th><label for="purchasePlan">
        purchasePlan:</label></th>
    <td>
        <spring:bind path="order.purchase.purchasePlan">
<textarea name="<c:out value="${status.expression}"/>"
cols="15" rows="5"><c:out value="${status.value}"/></textarea>
        <span class="fieldError"><c:out value="${status.errorMessage}"/></span>
        </spring:bind>
    </td>
</tr>
</c:if>
<tr>
    <td></td>
    <td>
        <input type="submit" class="button_default" name="save" value="confirm"/>
    </td>
</tr>
</table>
</form>
  </table>
 </td>
</table>
</body>
</html>
