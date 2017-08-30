<%@page  pageEncoding="GBK"  %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://www.springmodules.org/tags/commons-validator" prefix="v" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
<title>
<fmt:message key="newParamEnume.title"/>
</title>
<LINK href="<%=request.getContextPath()%>/img/PowerStone.css" type="text/css" rel="stylesheet">
</head>
<body >
<table class="tableFrame" cellspacing="1">
  <tr>
     <td class="tdTitle">��������&gt;&gt;�����������ע��</td>
  </tr>
<td class="tdContent">
  <table class="tableNoBorderCenter"  align="center">
<spring:bind path="driverOutputEnume.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">
        <c:forEach var="error" items="${status.errorMessages}">
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>

<form method="post" action="<%=request.getContextPath()%>/wf/edit_param_enume.fd"
    onsubmit="return validateWFDriverOutputParamEnume(this)" name="wFDriverOutputParamEnumeForm">
<spring:bind path="driverOutputEnume.driverOutputParamEnumeID">
   <input type="hidden" name="<c:out value="${status.expression}"/>"
    value="<c:out value="${status.value}"/>"/>
</spring:bind>

<c:if test="${not empty driverOutputParamID}">
<input type="hidden" name="driverOutputParamID" value="<c:out value="${driverOutputParamID}"/>"/>
</c:if>

<tr>
    <th><fmt:message key="driver.paramEnumeValue"/>:</th>
    <td>
        <spring:bind path="driverOutputEnume.driverOutputParamEnumeValue">
        <input type="text" class="input1" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>"/>
        <span class="fieldError"><c:out value="${status.errorMessage}"/></span>
        </spring:bind>
    </td>
</tr>
<tr>
    <td></td>
    <td>
        <input type="submit" class="button_default" value='<fmt:message key="global.ok"/>'/>
        <input type="button" class="button_default" value='<fmt:message key="global.cancel"/>' onclick="window.close();" />
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
    document.forms["wFDriverOutputParamEnumeForm"].elements["driverOutputParamEnumeValue"].focus();
</script>
<v:javascript formName="wFDriverOutputParamEnume" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/validator.jsp"></script>