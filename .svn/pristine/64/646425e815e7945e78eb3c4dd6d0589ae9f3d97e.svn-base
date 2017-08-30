<%@page  pageEncoding="GBK"%>
<%@ taglib uri="/WEB-INF/spring-commons-validator.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/spring.tld" prefix="spring" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<title><fmt:message key="userForm.title"/></title>

<spring:bind path="user.*">
    <%if(status.getErrorMessages()!=null){%>
    <div class="error">
        <logic:iterate id="error" name="status" property="errorMessages">
            <%=error%><br />
        </logic:iterate>
    </div>
    <%}%>
</spring:bind>

<p>Please fill in user's information below:</p>
  
<form method="post" action="<%=request.getContextPath()%>/editUser.html" 
    onsubmit="return validateUser(this)" name="userForm">
    
<%if(request.getParameter("id")!=null){%>
<spring:bind path="user.id">
<input type="hidden" name="id" value="<%=status.getValue()!=null?status.getValue():""%>"/>
</spring:bind>
<%}%>

<%if(request.getParameter("from")!=null){%>
<input type="hidden" name="from" value="<%=request.getParameter("from")%>"/>
<%}%>

<table class="detail">
<tr>
    <th><label for="firstName"><fmt:message key="user.firstName"/>:</label></th>
    <td>
        <spring:bind path="user.firstName">
        <input type="text" name="firstName" id="firstName" value="<%=status.getValue()!=null?status.getValue():""%>"/>
        <span class="fieldError"><%=status.getErrorMessage()%></span>
        </spring:bind>
    </td>
</tr>
<tr>
    <th><label for="firstName" class="required">* <fmt:message key="user.lastName"/>:</label></th>
    <td>
        <spring:bind path="user.lastName">
        <input type="text" name="lastName" id="lastName" value="<%=status.getValue()!=null?status.getValue():""%>"/>
        <span class="fieldError"><%=status.getErrorMessage()%></span>
        </spring:bind>
    </td>
</tr>
<tr>
    <td></td>
    <td>
        <input type="submit" class="button" name="save" value="Save"/>
      <%if(request.getParameter("id")!=null){%>
        <input type="submit" class="button" name="delete" value="Delete"/>
      <%}%>
      	<input type="submit" class="button" name="cancel" value="Cancel" onclick="bCancel=true"/>
    </td>
</tr>
</table>
</form>

<script type="text/javascript">
    document.forms["userForm"].elements["firstName"].focus();
</script>

<html:javascript formName="user" staticJavascript="true" xhtml="true" cdata="false"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/validator.jsp"></script>