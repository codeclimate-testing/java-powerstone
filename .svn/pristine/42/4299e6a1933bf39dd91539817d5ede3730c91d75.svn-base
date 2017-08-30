<%@page  pageEncoding="GBK"
import="java.util.*,org.powerstone.workflow.model.*"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
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
function changeBgColor(tempi,taskID){
  url="<%=request.getContextPath()%>/show_executing_task.wl?taskID="+taskID;
  window.sonWin=
     window.open(url,'task_window','resizable=1,width=720,height=540,scrollbars=0');
  window.sonWin.focus();

  if(cunObj != null){
	cunObj.className=oldclassName;
  }
	oldclassName=tempi.className;
    tempi.className="TD4";
	cunObj=tempi;
}
-->
</script>
<html>
<head>
<title>listMyRefusedTasks</title>
<link href="<%=request.getContextPath()%>/img/PowerStone.css" rel="stylesheet" type="text/css">
</head>
<body>
<table class="tableFrame" cellspacing="1">
  <tr>
    <td class="tdTitle"><fmt:message key="task.tasks_to_assign"/></td>
  </tr>

  <tr>
   <td class="tdContent">
	<br />
<TABLE class="tableList" align="center">
     <tr class="TDListTitle">
     	<TD width="4%">&nbsp;</TD>
     	<TD width="30%" align="left">任务描述</TD>
     	<TD width="16%" align="left">所在流程</TD>
     	<TD width="40%" align="left">驳回理由</TD>
     	<TD width="10%">驳回人</TD>
     </tr>

  <logic:iterate id="currTask" name="myRefusedTasks"  indexId="ide"
    type="org.powerstone.workflow.model.FlowTask">

    <tr <%if(ide.intValue()%2==0){%>class="TD3"<%}else{%>class="TD2"<%}%>
      style='cursor:hand' title='<fmt:message key="task.onclick"/>'
      onClick="changeBgColor(this,'<%=currTask.getTaskID()%>');">
      <td align="center"><%=ide.intValue()+1%></td>
      <td align="left">
        <b>
<%=currTask.getFlowNodeBinding().getWorkflowDriver().getFlowDriverName()%>
        </b>
      </td>

      <td align="left">
<%=currTask.getFlowNodeBinding().getFlowDeploy().getWorkflowMeta().getFlowFileInUse().getFlowMetaName()%>
      </td>
<%
List taskRefuses=currTask.getTaskRefuses();
FlowTaskRefuse ftr=null;
if(taskRefuses.size()>0){
  ftr=(FlowTaskRefuse)taskRefuses.get(0);
}
%>
      <td align="left">
<%=ftr!=null?ftr.getRefuseFor():"error:no refuse info!"%>
      </td>

      <td align="center">
<%=ftr!=null?ftr.getRefuseUser():"error:no refuse info!"%>
      </td>
    </tr>
  </logic:iterate>
</TABLE>

<hr width="95%" align="center" size="1" >
   <br />

          </td>
        </tr>
      </table>

</body>
</html>
