<%@page  pageEncoding="GBK"
import="java.util.*,org.powerstone.workflow.model.*"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<script language="JavaScript">
<!--
//
function submitForm(){
  if(document.form1.refusedFor.value==""){
     alert("请填写驳回理由！");
     document.form1.refusedFor.focus();
     return (false);
  }
  if(confirm('你真的要驳回选中的任务吗？')){
    form1.submit();
  }
}
-->
</script>

<html>
<head>
<title>任务驳回</title>
<link href="<%=request.getContextPath()%>/img/PowerStone.css" rel="stylesheet" type="text/css">
</head>

<body>
<table class="tableFrame" cellspacing="1">
  <tr>
    <td class="tdTitle">可以驳回的任务列表</td>
  </tr>

  <tr>
   <td class="tdContent">
	<br />
<form name="form1" method="post"
action='<%=request.getContextPath()%>/refuse_task.wl?taskID=<c:out value="${taskID}"/>' >
<TABLE class="tableList" align="center">

<%if(((java.util.List)request.getAttribute("tasksToRefuse")).size()>0){%>
     <tr class="TDListTitle">
     	<TD width="4%">&nbsp;</TD>
     	<TD width="66%">任务描述</TD>
     	<TD width="20%">提交时间</TD>
     	<TD width="10%">任务提交者</TD>
     </tr>
<logic:iterate id="currTask" name="tasksToRefuse" indexId="ide"
    type="org.powerstone.workflow.model.FlowTask">
    <tr <%if(ide.intValue()%2==0){%>class="TD3"<%}else{%>class="TD2"<%}%>>
      <td>
        <input type="checkbox" name="tasksToRefuse"
        value='<%=currTask.getTaskID()%>'>
      </td>
      <td>
        <b>
<%=currTask.getFlowNodeBinding().getWorkflowDriver().getFlowDriverName()%>
        </b>
<%=currTask.getPreviewText()%>
      </td>

      <td align="center">
<%String timeStr=currTask.getOverTime();%>
<%=timeStr!=null&&timeStr.length()>10?timeStr.substring(0,2)+"-"+timeStr.substring(2,4)+"-"+timeStr.substring(4,6)+"&nbsp;"+timeStr.substring(6,8)+":"+timeStr.substring(8,10):""%>
      </td>
      <td align="center">
<%=((FlowTaskUser)currTask.getTaskUsers().get(0)).getUserID()%>
      </td>
    </tr>
</logic:iterate>
    </TABLE>

    <TABLE class="tableList" align="center">
  <tr>
    <td height="25" colspan="6" align="center">
任务驳回原因：<br /><textarea cols="20" rows="5" name="refusedFor"></textarea>
    </td>
  </tr>
  <tr>
    <td height="25" colspan="6" align="center">
<input type="button" value="驳回任务" class="button_default" onclick="submitForm();" />
<input type="button" value="取消" class="button_default" onclick="window.history.back();" />
    </td>
  </tr>
<%}else{%>
  <tr class="TD3">
     	<TD width="100%" colspan="4">没有可以驳回的任务&nbsp;<input type="button" value="返回" onclick="history.back(-1)" /></TD>
  </tr>
<%}%>
    </TABLE>
   <br />
</form>
          </td>
        </tr>
      </table>
</body>
</html>
