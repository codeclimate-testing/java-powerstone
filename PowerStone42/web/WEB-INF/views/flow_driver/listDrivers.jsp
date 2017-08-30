<%@page  pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%
    response.setHeader("Pragma","No-cache");
    response.setHeader("Cache-Control","no-cache");
    response.setDateHeader("Expires", 0);
%>
<script language="JavaScript">
<!--
//换背景色
var oldclassName = null;
var cunObj = null;
function changeBgColor(tempi){
  parent.document.all.bottomIframe.src=
    "<%=request.getContextPath()%>/wf/see_driver_detail.fd?driverID="+tempi.id;

    if(cunObj != null){
	cunObj.className=oldclassName;
	}
	oldclassName=tempi.className;
    tempi.className="TD4";
	cunObj=tempi;
}

function editeDriver(obj){
  driverID=obj.id;
  url=
    "<%=request.getContextPath()%>/wf/edit_driver.fd?driverID="+driverID;
  window.open(url,'','resizable=yes,width=500,height=340,scrollbars=yes');
}
-->
</script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<link href="<%=request.getContextPath()%>/img/PowerStone.css"
  rel="stylesheet" type="text/css">
</head>

<body>
<table class="tableFrame" cellspacing="1">
  <tr>
    <td class="tdTitle">驱动管理&gt;&gt;驱动列表</td>
  </tr>

  <tr>
   <td class="tdContent">
	<br />
<form name="form1" method="post" target="_top"
        action="<%=request.getContextPath()%>/wf/remove_driver.fd" >
    <TABLE class="tableList" align="center">
    <tr class="TDListTitle">
      <td width="4%">&nbsp;</td>
      <td width="15%" align="left"><fmt:message key="driver.name"/></td>
      <td width="15%" align="left"><fmt:message key="driver.memo"/></td>
      <td width="25%" align="left"><fmt:message key="driver.read"/></td>
      <td width="25%" align="left"><fmt:message key="driver.write"/></td>
      <td width="16%" align="left"><fmt:message key="driver.contextPath"/></td>
    </tr>

<logic:iterate id="theDriver" name="allDrivers" indexId="ide"
  type="org.powerstone.workflow.model.WorkflowDriver">
    <tr <%if(ide.intValue()%2==0){%>class="TD3"<%}else{%>class="TD2"<%}%>
          onClick="changeBgColor(this);" style='cursor:hand'
          ondblclick="editeDriver(this)" id="<%=theDriver.getFlowDriverID()%>"
          title='<fmt:message key="driver.ondblclick"/>'>
      <td><input type="checkbox" name="driverToRemove"
        value='<%=theDriver.getFlowDriverID()%>'>
      </td>
      <td align="left"><%=theDriver.getFlowDriverName()%></td>
      <td align="left"><%=theDriver.getMemo()%>&nbsp;</td>
      <td align="left"><%=theDriver.getReadURL()%>&nbsp;</td>
      <td align="left"><%=theDriver.getWriteURL()%>&nbsp;</td>
      <td align="left"><%=theDriver.getContextPath()%>&nbsp;</td>
    </tr>
</logic:iterate>
    </TABLE>

  <TABLE class="tableList" align="center">
  <tr>
    <td height="25" colspan="6" align="center">&nbsp;
<input type="button" class="button_default" value='<fmt:message key="driver.button_add"/>'
onclick="window.open('<%=request.getContextPath()%>/wf/edit_driver.fd<c:if test="${!empty contextPath}">?contextPath=<c:out value="${contextPath}"/></c:if>','','resizable=yes,width=350,height=340,scrollbars=yes');">
&nbsp;
<input type="button" class="button_default" value='<fmt:message key="driver.button_delete"/>'
onclick="if(confirm('<fmt:message key="driver.confirm_delete"/>')){document.form1.submit();}"
>
    </td>
  </tr>
  </TABLE>

<hr width="95%" align="center" size="1" >
   <br />
</form>
          </td>
        </tr>
      </table>

</body>
</html>
