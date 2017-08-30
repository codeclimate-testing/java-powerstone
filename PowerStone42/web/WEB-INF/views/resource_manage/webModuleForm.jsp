<%@page  pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://www.springmodules.org/tags/commons-validator" prefix="v" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%
    response.setHeader("Pragma","No-cache");
    response.setHeader("Cache-Control","no-cache");
    response.setDateHeader("Expires", 0);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>新建WEB模块</title>
<LINK href="<%=request.getContextPath()%>/img/PowerStone.css" type="text/css" rel="stylesheet">
</head>

<body >
<table class="tableFrame" cellspacing="1">
  <tr>
     <td class="tdTitle">权限注册&gt;&gt;新建WEB模块</td>
  </tr>
<td class="tdContent">
  <table class="tableNoBorderCenter"  align="center">

<spring:bind path="webModule.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">
        <c:forEach var="error" items="${status.errorMessages}">
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>

<form method="post"
  action="<%=request.getContextPath()%>/resource_manage/edit_web_module.resource"
    onsubmit="return validateWebModule(this)" name="webModuleForm">
    <spring:bind path="webModule.webModuleID">
        <input type="hidden" name="webModuleID" value="<c:out value="${status.value}"/>"/>
    </spring:bind>

<table class="tableNoBorderCenter" align="center">
<tr>
    <th><label for="roleName">
        WebModule Name:</label></th>
    <td>
        <spring:bind path="webModule.webModuleName">
        <input type="text" class="input1" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>"/>
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
    document.forms["webModuleForm"].elements["webModuleName"].focus();
</script>
<v:javascript formName="webModule" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/validator.jsp"></script>
