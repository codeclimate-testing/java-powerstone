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
<title>My Information</title>
<LINK href="<%=request.getContextPath()%>/img/PowerStone.css" type="text/css" rel="stylesheet">
</head>

<body >
<table class="tableFrame" cellspacing="1">
<tr>
    <td class="tdContent">
      <table class="tableNoBorderCenter"  align="center">
        <tr>
          <td>
<spring:bind path="user.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">
        <c:forEach var="error" items="${status.errorMessages}">
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>
<tr>
     <td colspan="2">

<form method="post" action="<%=request.getContextPath()%>/edit_my_info.ca"
    onsubmit="return validateUser(this)" name="userForm">
<table>
<tr>
    <th><label for="userName">
        UserName:</label></th>
    <td>
        <spring:bind path="user.userName">
        <input readonly="readonly" disabled="disabled" type="text" class="input1" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>"/>
        <span class="fieldError"><c:out value="${status.errorMessage}"/></span>
        </spring:bind>
    </td>
</tr>
    <input type="hidden" name="password"value="1234567"/>
    <input type="hidden" name="confirmPass" value="1234567"/>
<tr>
    <th><label for="realName">
        realName:</label></th>
    <td>
        <spring:bind path="user.realName">
        <input type="text" class="input1" name="<c:out value="${status.expression}"/>"
        value="<c:out value="${status.value}"/>"/>
        <span class="fieldError"><c:out value="${status.errorMessage}"/></span>
        </spring:bind>
    </td>
</tr>
<tr>
    <th><label for="email">
        email:</label></th>
    <td>
        <spring:bind path="user.email">
        <input type="text" class="input1" name="<c:out value="${status.expression}"/>"
        value="<c:out value="${status.value}"/>"/>
        <span class="fieldError"><c:out value="${status.errorMessage}"/></span>
        </spring:bind>
    </td>
</tr>

<tr>
    <td></td>
    <td>
        <input type="submit" class="button_default" name="save" value='<fmt:message key="global.ok"/>'/>
        <input type="button" class="button_default" name="cancel" value='<fmt:message key="global.cancel"/>' onclick="window.close();"/>
        <input type="button" class="button_default" value='<fmt:message key="ca.change_pass"/>'
onclick="window.location.replace('<%=request.getContextPath()%>/change_my_pass.ca');"/>
    </td>
</tr>
</table>
</form>
          </td>
        </tr>
                  </td>
               </tr>
              </table>
           </td>
        </tr>
  </table>
</body>
</html>

<script type="text/javascript">
    document.forms["userForm"].elements["realName"].focus();
</script>
<v:javascript formName="user" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/validator.jsp"></script>
