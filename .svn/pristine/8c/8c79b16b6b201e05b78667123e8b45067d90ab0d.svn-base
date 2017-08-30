<%@page  pageEncoding="GBK"  %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<html>

<head>
<title><fmt:message key="newFlowDeploy.title"/></title>
<script language="JavaScript">
<!--
function subform(){
    if(document.deployform.deployName.value==""){
     alert('<fmt:message key="deploy.require_name"/>');
     document.deployform.deployName.focus();
     return (false);
    }
    document.deployform.submit();
}
//-->
</script>
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
  <form
action='<%=request.getContextPath()%>/wf/create_flow_deploy.dpl?workflowMetaID=<c:out value="${workflowMetaID}"/>'
    name="deployform" method="POST" target="target1">
<table width="100%" border="0" align="center">
  <tr>
    <td align="right" width="30%"><fmt:message key="deploy.name"/></td>
	<td align="left" width="70%">
        <input type="text" name="deployName">
        <font color="#FF0000">*</font>
    </td>
  </tr>
  <tr>
    <td align="right"><fmt:message key="deploy.memo"/></td>
	<td align="left">
        <textarea name="memo" rows="10" cols="60"></textarea>
    </td>
  </tr>
  <tr>
    <td colspan="2" align="center">
        <input type="button" value='<fmt:message key="global.ok"/>'
          onClick="subform()">

        <input type="button" name="cancel" value='<fmt:message key="global.cancel"/>' onClick="window.close();">
    </td>
  </tr>
</table>
</form>
<iframe name="target1" id="target1" width="0" height="0" frameborder="0">
</iframe>
</body>
</html>
