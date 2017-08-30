<%@page  pageEncoding="GBK"%>
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
//»»±³¾°É«
var oldclassName = null;
var cunObj = null;
function changeBgColor(tempi,taskID){
  url="<%=request.getContextPath()%>/search_to_assign.wl?taskID="+taskID;
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
<title>listTasksToAssign</title>
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
     	<TD width="2%" align="center">&nbsp;</TD>
     	<TD width="60%" align="center"><fmt:message key="task.memo"/></TD>
     	<TD width="20%" align="center"><fmt:message key="task.flow_belong"/></TD>
     	<TD width="18%" align="center"><fmt:message key="task.start_time"/></TD>
     </tr>

  <logic:iterate id="currTask" name="tasksToAssign"  indexId="ide"
    type="org.powerstone.workflow.model.FlowTask">
    <tr <%if(ide.intValue()%2==0){%>class="TD3"<%}else{%>class="TD2"<%}%>
      style='cursor:hand' title='<fmt:message key="task.onclick"/>'
      onClick="changeBgColor(this,'<%=currTask.getTaskID()%>');">
      <td align="center">
           <%=ide.intValue()+1%>
      </td>
      <td>
        <b>
<%=currTask.getFlowNodeBinding().getWorkflowDriver().getFlowDriverName()%>
        </b>
<%=currTask.getPreviewText()%>
      </td>

      <td align="center">
<%=currTask.getFlowNodeBinding().getFlowDeploy().getWorkflowMeta().getFlowFileInUse().getFlowMetaName()%>
      </td>

      <td align="center">
<%String timeStr=currTask.getCreateTime();%>
<%=timeStr!=null&&timeStr.length()>10?timeStr.substring(0,2)+"-"+timeStr.substring(2,4)+"-"+timeStr.substring(4,6)+"&nbsp;"+timeStr.substring(6,8)+":"+timeStr.substring(8,10):""%>
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
