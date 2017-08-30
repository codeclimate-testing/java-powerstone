<%@page  pageEncoding="GBK"  %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">

<link href="<%=request.getContextPath()%>/img/PowerStone.css" rel="stylesheet" type="text/css">
</head>
<body>
<table width="95%" border="0" cellspacing="0" bordercolor="#000000">
  <tr>
    <td width="45%" height="339" valign="top">
      <br>
        <table width="64%" height="184" border="0" align="center" cellspacing="0">
        <tr>
          <td height="47"><fmt:message key="driver.input_param"/><img style='cursor:hand'
 onclick="window.open('<%=request.getContextPath()%>/wf/edit_input_param.fd?driverID=<c:out value="${theDriver.flowDriverID}"/>','','width=250,height=200');" src="<%=request.getContextPath()%>/img/add.gif" width="12" height="12"></td>
        </tr>

<c:forEach var="theInputParam" items="${theDriver.wfDriverInputParams}">
        <tr>
          <td><c:out value="${theInputParam.paramName}"/>(<c:out value="${theInputParam.paramAlias}"/>)
            <img style='cursor:hand'
onclick="deleteInputParam('<c:out value="${theDriver.flowDriverID}"/>','<c:out value="${theInputParam.driverInputParamID}"/>')"
                 src="<%=request.getContextPath()%>/img/delete.gif" width="12"
                 height="12">
          </td>
        </tr>
</c:forEach>

      </table></td>
    <td width="0%" valign="top">
      <img width=2 height=302
        src="<%=request.getContextPath()%>/img/image556.gif"
        v:shapes="_x0000_s1209">
    </td>
    <td width="55%" valign="top">
      <br>
        <table width="94%" height="184" border="0" align="center" cellspacing="0">
        <tr>
          <td width="56%" height="47"><fmt:message key="driver.output_param"/><img style='cursor:hand'
onclick="window.open('<%=request.getContextPath()%>/wf/edit_output_param.fd?driverID=<c:out value="${theDriver.flowDriverID}"/>','','width=250,height=200');" src="<%=request.getContextPath()%>/img/add.gif" width="12" height="12"></td>
        </tr>

<c:forEach items="${theDriver.wfDriverOutputParams}" var="theOutputParam">
        <tr>
          <td><c:out value="${theOutputParam.paramName}"/>(<c:out value="${theOutputParam.paramAlias}"/>)
            <img style='cursor:hand'
onclick="deleteOutputParam('<c:out value="${theDriver.flowDriverID}"/>','<c:out value="${theOutputParam.driverOutputParamID}"/>')"
                 src="<%=request.getContextPath()%>/img/delete.gif" width="12"
                 height="12">
          </td>
        </tr>

          <tr>
            <form name='select_<c:out value="${theOutputParam.driverOutputParamID}"/>' method='POST'
action='<%=request.getContextPath()%>/wf/remove_driver_paramenume.fd?driverID=<c:out value="${theDriver.flowDriverID}"/>'>
          <td><table width="68%" border="0" cellspacing="0">
              <tr>
                <td width="58%" rowspan="2" align="center">

                  <select name="paramEnumeToDel" size="5" multiple="multiple">
<c:forEach items="${theOutputParam.driverOutputParamEnumes}"
  var="theOutputParamEnume">
<option value='<c:out value="${theOutputParamEnume.driverOutputParamEnumeID}"/>'>
   <c:out value="${theOutputParamEnume.driverOutputParamEnumeValue}"/>
</option>
</c:forEach>
                  </select>
                </td>

                <td width="42%" height="37">
                  <img
onclick="window.open('<%=request.getContextPath()%>/wf/edit_param_enume.fd?driverOutputParamID=<c:out value="${theOutputParam.driverOutputParamID}"/>','','width=250,height=200')"
style='cursor:hand' src='<%=request.getContextPath()%>/img/add.gif'
width='12' height='12' ><span class="f12"><fmt:message key="driver.button_add"/></span></td>
              </tr>
              <tr>
                <td><img onclick="if(confirm('<fmt:message key="driver.confirm_delete_enume"/>')){document.select_<c:out value="${theOutputParam.driverOutputParamID}"/>.submit();}" style='cursor:hand' src="<%=request.getContextPath()%>/img/delete.gif"
                  width="12" height="12"><span class="f12"><fmt:message key="driver.button_delete"/></span></td>
              </tr>
            </table></td>
               </form>
          </tr>

</c:forEach>

      </table></td>
  </tr>
</table>
</body>
</html>
<SCRIPT LANGUAGE="JavaScript">
<!--
function deleteInputParam(driverID,paramID){
  if(confirm('<fmt:message key="driver.confirm_delete_input"/>'+paramID)){
  window.location.replace(
  "<%=request.getContextPath()%>/wf/remove_driver_inputparam.fd?driverID="+
  driverID+"&paramID="+paramID);
  }
}

function deleteOutputParam(driverID,paramID){
  if(confirm('<fmt:message key="driver.confirm_delete_output"/>'+paramID)){
  window.location.replace(
  "<%=request.getContextPath()%>/wf/remove_driver_outputparam.fd?driverID="+
  driverID+"&paramID="+paramID);
  }
}
//-->
</SCRIPT>
