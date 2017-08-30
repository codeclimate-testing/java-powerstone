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
<title><fmt:message key="ca.new_group"/></title>
<LINK href="<%=request.getContextPath()%>/img/PowerStone.css" type="text/css" rel="stylesheet">
</head>

<body >
<table class="tableFrame" cellspacing="1">
  <tr>
     <td class="tdTitle"><fmt:message key="ca.user_manage"/>&gt;&gt;<fmt:message key="ca.new_group"/></td>
  </tr>
<td class="tdContent">
  <table class="tableNoBorderCenter"  align="center">

<spring:bind path="group.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">
        <c:forEach var="error" items="${status.errorMessages}">
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>

<form method="post" action="<%=request.getContextPath()%>/user_group/edit_group.ca"
    onsubmit="return validateGroup(this)" name="groupForm">
    <spring:bind path="group.groupID">
<input type="hidden" name='<c:out value="${status.expression}"/>' value="<c:out value="${status.value}"/>"/>
    </spring:bind>

    <c:if test="${not empty parentGroupID}">
    <input type="hidden" name="parentGroupID" value="<c:out value="${parentGroupID}"/>"/>
    </c:if>

<table class="tableNoBorderCenter" align="center">
<tr>
    <th><label for="groupName">
        GroupName:</label></th>
    <td>
        <spring:bind path="group.groupName">
        <input type="text" class="input1" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>"/>
        <span class="fieldError"><c:out value="${status.errorMessage}"/></span>
        </spring:bind>
    </td>
</tr>
<tr>
    <th><label for="memo">
        memo:</label></th>
    <td>
        <spring:bind path="group.memo">
        <input type="text" class="input1" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>"/>
        <span class="fieldError"><c:out value="${status.errorMessage}"/></span>
        </spring:bind>
    </td>
</tr>

<tr>
    <td></td>
    <td>
  <input type="submit" class="button_default" name="save" value='<fmt:message key="global.ok"/>'/>
  <input type="submit" class="button_default" name="cancel" value='<fmt:message key="global.cancel"/>'/>
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
    document.forms["groupForm"].elements["groupName"].focus();
</script>
<v:javascript formName="group" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/validator.jsp"></script>
