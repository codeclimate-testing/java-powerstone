<%@page  pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://www.springmodules.org/tags/commons-validator" prefix="v" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%  response.setHeader("Pragma","No-cache");
    response.setHeader("Cache-Control","no-cache");
    response.setDateHeader("Expires", 0);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>新建资源</title>
<LINK href="<%=request.getContextPath()%>/img/PowerStone.css" type="text/css" rel="stylesheet">
</head>

<body >
<table class="tableFrame" cellspacing="1">
  <tr>
     <td class="tdTitle">权限注册&gt;&gt;新建资源:资源ID要唯一，并以"function_"开头，资源名称也要唯一</td>
  </tr>
<td class="tdContent">
  <table class="tableNoBorderCenter"  align="center">

<spring:bind path="resource.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">
        <c:forEach var="error" items="${status.errorMessages}">
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>

<form method="post" action="<%=request.getContextPath()%>/resource_manage/edit_resource.resource"
    onsubmit="return validateResource(this)" name="resourceForm">
    <c:if test="${not empty rsID}">
    <input type="hidden" name="rsID" value="<c:out value="${rsID}"/>"/>
    </c:if>

    <c:if test="${not empty webModuleID}">
    <input type="hidden" name="webModuleID" value="<c:out value="${webModuleID}"/>"/>
    </c:if>

<table class="tableNoBorderCenter" align="center">
<tr>
    <th><label for="resourceID">
        resourceID:</label></th>
    <td>
        <spring:bind path="resource.resourceID">
        <input type="text" class="input1" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>"/>
        <span class="fieldError"><c:out value="${status.errorMessage}"/></span>
        </spring:bind>
    </td>
</tr>
<tr>
    <th><label for="resourceName">
        resourceName:</label></th>
    <td>
        <spring:bind path="resource.resourceName">
        <input type="text" class="input1" name="<c:out value="${status.expression}"/>"
        value="<c:out value="${status.value}"/>"/>
        <span class="fieldError"><c:out value="${status.errorMessage}"/></span>
        </spring:bind>
    </td>
</tr>
<tr>
    <th><label for="actionURL">
        actionURL:</label></th>
    <td>
        <spring:bind path="resource.actionURL">
        <input type="text" class="input1" name="<c:out value="${status.expression}"/>"
        value="<c:out value="${status.value}"/>"/>
        <span class="fieldError"><c:out value="${status.errorMessage}"/></span>
        </spring:bind>
    </td>
</tr>

<tr>
    <td></td>
    <td>
        <input type="submit" class="button_default" name="save" value="Save"/>
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
    document.forms["resourceForm"].elements["resourceID"].focus();
</script>
<v:javascript formName="resource" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/validator.jsp"></script>
