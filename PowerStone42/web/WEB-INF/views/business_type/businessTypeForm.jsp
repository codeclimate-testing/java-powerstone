<%@page  pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://www.springmodules.org/tags/commons-validator" prefix="v" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>BusinessType</title>
<LINK href="<%=request.getContextPath()%>/img/PowerStone.css" type="text/css" rel="stylesheet">
</head>

<body >
<table class="tableFrame" cellspacing="1">
  <tr>
     <td class="tdTitle">工作流管理&gt;&gt;业务类型</td>
  </tr>
<td class="tdContent">
  <table class="tableNoBorderCenter"  align="center">

<spring:bind path="businessType.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">
        <c:forEach var="error" items="${status.errorMessages}">
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>

<form method="post" action="<%=request.getContextPath()%>/wf/edit_bt.bt"
    onsubmit="return validateBusinessType(this)" name="businessTypeForm">
<spring:bind path="businessType.typeID">
        <input type="hidden" name="typeID" value="<c:out value="${status.value}"/>"/>
</spring:bind>

<table class="tableNoBorderCenter" align="center">
<tr>
    <th><label for="typeName">
        TypeName:</label></th>
    <td>
        <spring:bind path="businessType.typeName">
        <input type="text" class="input1" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>"/>
        <span class="fieldError"><c:out value="${status.errorMessage}"/></span>
        </spring:bind>
    </td>
</tr>

<tr>
    <td></td>
    <td>
        <input type="submit" class="button_default" name="save" value="Save"/>
<c:if test="${not empty typeID}">
        <input type="submit" class="button_default" name="remove" value="Remove"/>
</c:if>
        <input type="submit" class="button_default" name="cancel" value="Cancel"/>
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
    document.forms["businessTypeForm"].elements["typeName"].focus();
</script>
<v:javascript formName="businessType" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/validator.jsp"></script>
