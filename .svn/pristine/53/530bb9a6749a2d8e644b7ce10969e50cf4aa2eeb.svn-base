<%@page  pageEncoding="GBK"
import="java.util.*,org.powerstone.workflow.model.*"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<script language="javascript" src="/WEB_REC/ims/scripts/check.js">
</script>
<SCRIPT language="JavaScript" >
<!--
function removeFlowProc(){
  if(confirm('<fmt:message key="monitor.confirm_delete_proc"/>')){
      window.location.replace(
      '<%=request.getContextPath()%>/monitor/remove_proc.do?flowProcID=<%=request.getParameter("flowProcID")%>');
  }
}
//-->
</SCRIPT>
<title><fmt:message key="monitor_flowDetail.title"/></title>
<link href="<%=request.getContextPath()%>/img/PowerStone.css" rel="stylesheet" type="text/css">
</head>
<body>
<table width="100%" border="1" cellspacing="0" cellpadding="0">
<logic:present name="freeTasks">
<%
List freeTasks=(List)request.getAttribute("freeTasks");
%>
  <tr>
    <td rowspan="<%=freeTasks.size()+2%>" nowrap="nowrap"
      width="10"><fmt:message key="task.new_tasks"/></td>
    <td bgcolor="#95CDDE" align="center"><fmt:message key="task.memo"/></td>
    <td bgcolor="#95CDDE" align="center"><fmt:message key="task.start_time"/></td>
    <td bgcolor="#95CDDE" align="center"><fmt:message key="monitor.persons_can_check"/></td>
  </tr>
<%for(Iterator it=freeTasks.iterator();it.hasNext();){
  FlowTask ft=(FlowTask)it.next();
%>
  <tr>
    <td id="<%=ft.getTaskID()%>" align="center"><%=ft.getFlowNodeBinding().getWorkflowDriver().getFlowDriverName()%></td>
    <td align="center"><%=ft.getCreateTime()%></td>
    <td align="center">
<%if(ft.getNewTasks().size()>0){
    for(Iterator it2=ft.getNewTasks().iterator();it2.hasNext();){
      NewTask nt=(NewTask)it2.next();
%>
<%=nt.getTaskCandidateUserID()%>|
<%  }
  }
%>
    </td>
  </tr>
<%}%>
<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
</logic:present>

<logic:present name="executingTasks">
<%
List executingTasks=(List)request.getAttribute("executingTasks");
%>
  <tr>
    <td rowspan="<%=executingTasks.size()+2%>" nowrap="nowrap"
      width="10"><fmt:message key="task.exe_tasks"/></td>
    <td bgcolor="#95CDDE" align="center"><fmt:message key="task.memo"/></td>
    <td bgcolor="#95CDDE" align="center"><fmt:message key="task.lock_time"/></td>
    <td bgcolor="#95CDDE" align="center"><fmt:message key="monitor.task_owner"/></td>
  </tr>
<%
for(Iterator it=executingTasks.iterator();it.hasNext();){
  FlowTask ft=(FlowTask)it.next();
%>
  <tr>
    <td id="<%=ft.getTaskID()%>" align="center"><%=ft.getFlowNodeBinding().getWorkflowDriver().getFlowDriverName()%></td>
    <td align="center"><%=ft.getStartTime()%></td>
    <td align="center">
<%if(ft.getTaskUsers().size()>0){
    for(Iterator it2=ft.getTaskUsers().iterator();it2.hasNext();){
      FlowTaskUser taskOwner=(FlowTaskUser)it2.next();
%>
        <%=taskOwner.getUserID()%>&nbsp;
<%  }
  }
%>
    </td>
  </tr>
<%}
%>
<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
</logic:present>

<logic:present name="finishedTasks">
<%
List finishedTasks=(List)request.getAttribute("finishedTasks");
%>
 <tr>
    <td rowspan="<%=finishedTasks.size()+2%>" nowrap="nowrap"
      width="10"><fmt:message key="task.finished_tasks"/></td>
    <td bgcolor="#95CDDE" align="center"><fmt:message key="task.memo"/></td>
    <td bgcolor="#95CDDE" align="center"><fmt:message key="task.finish_time"/></td>
    <td bgcolor="#95CDDE" align="center"><fmt:message key="monitor_task_finisher"/></td>
  </tr>
<%
for(Iterator it=finishedTasks.iterator();it.hasNext();){
  FlowTask ft=(FlowTask)it.next();
%>
  <tr>
    <td id="<%=ft.getTaskID()%>" align="center"><%=ft.getFlowNodeBinding().getWorkflowDriver().getFlowDriverName()%></td>
    <td align="center"><%=ft.getOverTime()%></td>
    <td align="center"><%=((FlowTaskUser)ft.getTaskUsers().get(0)).getUserID()%></td>
  </tr>
<%}
%>
<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
</logic:present>
</table>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center">
    <input type="button" onclick="removeFlowProc();" value='<fmt:message key="monitor_delete_proc"/>'>
    </td>
  </tr>
</table>
</body>
</html>
