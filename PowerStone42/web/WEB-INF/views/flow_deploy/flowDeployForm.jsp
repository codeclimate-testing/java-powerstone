<%@page  pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://www.springmodules.org/tags/commons-validator" prefix="v" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>FlowDeploy</title>
<LINK href="<%=request.getContextPath()%>/img/PowerStone.css" type="text/css" rel="stylesheet">
</head>

<body >
<table class="tableFrame" cellspacing="1">
  <tr>
     <td class="tdTitle">工作流管理&gt;&gt;工作流部署</td>
  </tr>
<td class="tdContent">
  <table class="tableNoBorderCenter"  align="center">

<spring:bind path="flowDeploy.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">
        <c:forEach var="error" items="${status.errorMessages}">
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>

<form method="post" action="<%=request.getContextPath()%>/wf/edit_flow_deploy.dpl"
    onsubmit="return validateFlowDeploy(this)" name="flowDeployForm">
<spring:bind path="flowDeploy.flowDeployID">
<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>"/>
</spring:bind>
<c:if test="${not empty workflowMetaID}">
    <input type="hidden" name="workflowMetaID" value="<c:out value="${workflowMetaID}"/>"/>
</c:if>

<table class="tableNoBorderCenter" align="center">
<tr>
    <th><label for="flowDeployName">
        FlowDeployName:</label></th>
    <td>
        <spring:bind path="flowDeploy.flowDeployName">
<input type="text" class="input1" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>"/>
        <span class="fieldError"><c:out value="${status.errorMessage}"/></span>
        </spring:bind>
    </td>
</tr>
<tr>
    <th><label for="memo">
        memo:</label></th>
    <td>
        <spring:bind path="flowDeploy.memo">
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
    document.forms["flowDeployForm"].elements["flowDeployName"].focus();
</script>
<v:javascript formName="flowDeploy" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/validator.jsp"></script>
