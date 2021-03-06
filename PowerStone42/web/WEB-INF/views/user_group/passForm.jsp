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
<title><fmt:message key="ca.change_pass"/></title>
<LINK href="<%=request.getContextPath()%>/img/PowerStone.css" type="text/css" rel="stylesheet">
</head>

<body >
<table class="tableFrame" cellspacing="1">
  <tr>
     <td class="tdTitle"><fmt:message key="ca.user_manage"/>&gt;&gt;<fmt:message key="ca.change_pass"/></td>
  </tr>
<tr>
    <td class="tdContent">
      <table class="tableNoBorderCenter"  align="center">
        <tr>
          <td >
<spring:bind path="pass.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">
        <c:forEach var="error" items="${status.errorMessages}">
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>

  <tr>
     <td colspan="2" align="center">
<form method="post" action="<%=request.getContextPath()%>/user_group/change_pass.ca"
    onsubmit="return validateUser(this)" name="userForm">
    <c:if test="${not empty id}">
    <input type="hidden" name="id" value="<c:out value="${id}"/>"/>
    </c:if>

    <c:if test="${not empty groupID}">
    <input type="hidden" name="groupID" value="<c:out value="${groupID}"/>"/>
    </c:if>

    <c:if test="${not empty pageTo}">
    <input type="hidden" name="pageTo" value="<c:out value="${pageTo}"/>"/>
    </c:if>
    <input type="hidden" name="userName" value="userName"/>
    <input type="hidden" name="realName" value="realName"/>
    <input type="hidden" name="email" value="email@sd.fg"/>
<table>
<tr>
    <th><label for="oldPass">
        old password:</label></th>
    <td>
        <input type="password" class="input1" name="oldPass"/>
        <span class="fieldError"><c:out value="${status.errorMessage}"/></span>
    </td>
</tr>
<tr>
    <th><label for="password">
        new password:</label></th>
    <td>
        <input type="password" class="input1" name="password"/>
        <span class="fieldError"><c:out value="${status.errorMessage}"/></span>
    </td>
</tr>
<tr>
    <th><label for="confirmPass">
        confirmPass:</label></th>
    <td>
        <input type="password" class="input1" name="confirmPass"/>
        <span class="fieldError"><c:out value="${status.errorMessage}"/></span>
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
    document.forms["userForm"].elements["oldPass"].focus();
</script>
<v:javascript formName="user" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/validator.jsp"></script>
