<%@page  pageEncoding="GBK"  %>
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
<title><fmt:message key="ca.new_user"/></title>
<LINK href="<%=request.getContextPath()%>/img/PowerStone.css" type="text/css" rel="stylesheet">
</head>

<body >
<table class="tableFrame" cellspacing="1">
  <tr>
     <td class="tdTitle"><fmt:message key="ca.user_manage"/>&gt;&gt;<fmt:message key="ca.new_user"/></td>
  </tr>
<tr>
    <td class="tdContent">
      <table class="tableNoBorderCenter"  align="center">
        <tr>
          <td >
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
     <td colspan="2" align="center">
<form method="post" action="<%=request.getContextPath()%>/user_group/edit_user.ca"
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
<table class="tableNoBorderCenter" align="center">
<tr>
    <th align="right"><label for="userName">
        UserName:</label></th>
    <td>
        <spring:bind path="user.userName">
        <input type="text" class="input1" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>"/>
        <span class="fieldError"><c:out value="${status.errorMessage}"/></span>
        </spring:bind>
    </td>
</tr>
<c:if test="${empty id}">
<tr>
    <th align="right"><label for="password">
        password:</label></th>
    <td>
        <spring:bind path="user.password">
        <input type="password" class="input1" name="<c:out value="${status.expression}"/>"
        value="<c:out value="${status.value}"/>"/>
        <span class="fieldError"><c:out value="${status.errorMessage}"/></span>
        </spring:bind>
    </td>
</tr>
<tr>
    <th align="right"><label for="confirmPass">
        confirmPass:</label></th>
    <td>
        <spring:bind path="user.confirmPass">
        <input type="password" class="input1" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>"/>
        <span class="fieldError"><c:out value="${status.errorMessage}"/></span>
        </spring:bind>
    </td>
</tr>
</c:if>

<c:if test="${not empty id}">
    <spring:bind path="user.password">
        <input type="hidden" name="<c:out value="${status.expression}"/>"
        value="<c:out value="${status.value}"/>"/>
    </spring:bind>
    <spring:bind path="user.confirmPass">
        <input type="hidden" id="confirmPass" name="confirmPass"
        value="<c:out value="${status.value}"/>"/>
    </spring:bind>
</c:if>

<tr>
    <th align="right"><label for="realName">
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
    <th align="right"><label for="email">
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
    <td align="center" colspan="2">
  <input type="submit" class="button_default" name="save" value='<fmt:message key="global.ok"/>'/>
  <input type="submit" class="button_default" name="cancel" value='<fmt:message key="global.cancel"/>'/>

<c:if test="${not empty id}">
<input type="button" class="button_default" value='<fmt:message key="ca.change_pass"/>'
onclick="window.location.replace('<%=request.getContextPath()%>/user_group/change_pass.ca?id=<c:out value="${id}"/>&groupID=<c:out value="${groupID}"/>&pageTo=<c:out value="${pageTo}"/>');"/>
<input type="button" class="button_default" value='<fmt:message key="ca.change_user_priv"/>'
onclick="window.location.replace('<%=request.getContextPath()%>/priv_manage/show_user_privileges.priv?userID=<c:out value="${id}"/>&groupID=<c:out value="${groupID}"/>&pageTo=<c:out value="${pageTo}"/>');"/>
</c:if>

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
    document.forms["userForm"].elements["userName"].focus();
</script>
<v:javascript formName="user" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/validator.jsp"></script>
