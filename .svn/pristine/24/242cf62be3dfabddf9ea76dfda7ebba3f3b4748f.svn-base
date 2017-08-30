<%@page pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://www.springmodules.org/tags/commons-validator" prefix="v" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
<title>
techForm
</title>
<LINK href="<%=request.getContextPath()%>/img/PowerStone.css" type="text/css" rel="stylesheet">
</head>
<!--body bgcolor="#ffffff">
<%
//java.util.Enumeration e=request.getAttributeNames();
//while(e.hasMoreElements()){
//  Object eleName=e.nextElement();
//  if(eleName instanceof String){
//    out.println("<b>"+eleName+"</b>>>>"+request.getAttribute((String)eleName)+"<br>");
//  }
//}
%>
</body-->
<table class="tableFrame" cellspacing="1">
  <tr>
     <td class="tdTitle">检查技术可行性</td>
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
<form method="post" action="<%=request.getContextPath()%>/dreambike/edit_tech.do" name="orderForm">
<table class="tableNoBorderCenter" align="center">
  <spring:bind path="order.orderID">
        <input type="hidden" name="orderID" value="<c:out value="${status.value}"/>"/>
  </spring:bind>
<tr>
    <th><label for="techState">
        techState:</label></th>
    <td>
        <spring:bind path="order.techState">
<select name="techState" class="input1">
  <option value="Y"
<%if(request.getAttribute("order")!=null&&
"Y".equals(((ps_dreambike.BikeOrder)request.getAttribute("order")).getTechState())){%>
selected="selected"
<%}%>>技术可行</option>
  <option value="N"
<%if(request.getAttribute("order")!=null&&
"N".equals(((ps_dreambike.BikeOrder)request.getAttribute("order")).getTechState())){%>
selected="selected"
<%}%>>技术不可行</option>
</select>
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
    document.forms["orderForm"].elements["techState"].focus();
</script>
