<!--%@page pageEncoding="GBK"%-->
<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%  response.setHeader("Pragma","No-cache");
    response.setHeader("Cache-Control","no-cache");
    response.setDateHeader("Expires", 0);
%>
<html>
<head>
<title><fmt:message key="showExecutingTask.title"/></title>
<link href="<%=request.getContextPath()%>/img/PowerStone.css" rel="stylesheet" type="text/css">
</head>
<body onkeydown="doHotKey()">

<c:if test="${!empty flowDriver}">
  <TABLE cellspacing="1" height="100%">
  <TR>
<TD nowrap width="10%" height=30 align=right><fmt:message key="task.memo"/>:</TD>
<TD nowrap width="80%" height=30 align=left><c:out value="${flowDriver.memo}"/>&nbsp;</TD>
<TD nowrap width="10%" align="right">
<input type="button" class="button_default" value='<fmt:message key="task.abort"/>' onclick="abortTask();"/>
<input type="button" class="button_default" value="驳回任务" onclick="refuseTask();"/>
<c:if test="${!empty distributeTask}">
<input type="button" class="button_default" value='<fmt:message key="task.distribute"/>' onclick="distributeTask();"/>
</c:if>
</TD>
   </TR>
<TR height="1%">
   <TD class="TD0" nowrap width="100%" colspan="3">
     <hr width="100%" align="center" size="1" >
   </TD>
</TR>
   <TR align=middle valign="top" >
   <TD class="TD0" nowrap width="100%" colspan="3">
<iframe id="displayIframe" name="displayIframe"
src='<c:out value="${flowDriver.contextPath}"/>/<c:out value="${flowDriver.readURL}"/>?<c:out value="${taskIDParamName}"/>=<c:out value="${taskID}"/>'
 frameborder="0" height="100%" width="100%" scrolling="auto"  marginheight="0" marginwidth="0">
 </iframe>
   </TD>
   </TR>
<tr valign=bottom><td colspan=2 height=10></td></tr>

  </TABLE>
</c:if>
</body>
</html>
<script language="JavaScript">
<!--
var closeBY="";//由谁关闭窗口

window.moveTo((screen.width-720)/2,(screen.height-640)/2);
//完成任务
function submitTask(){
//  if(confirm('任务一旦提交将不能再修改，是否继续？')){
//    closeBY="submit";
//    form1.submit();
      opener.parent.location.reload();
      window.close();
//  }
}

function doHotKey(){
	keyCode = window.event.keyCode;
	if(window.event.ctrlKey && window.event.keyCode==13){
//	submitTask();
	}
}

function refuseTask(){//驳回
  closeBY="rufuse";
  window.location.href='<%=request.getContextPath()%>/list_tasks_to_refuse.wl?taskID=<c:out value="${taskID}"/>';
}

function abortTask(){
  if(confirm('<fmt:message key="task.confirm_abort"/>')){
    closeBY="abort";
    window.location.href='<%=request.getContextPath()%>/abort_task.wl?taskID=<c:out value="${taskID}"/>';
  }
}
//分配任务
function distributeTask(){
  closeBY="distribute";
  window.location.href=
  '<%=request.getContextPath()%>/search_to_distribute.wl?taskID=<c:out value="${taskID}"/>';
}
-->
</script>
