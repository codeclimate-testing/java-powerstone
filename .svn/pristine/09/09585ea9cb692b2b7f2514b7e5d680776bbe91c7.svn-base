<%@page  pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%
    response.setHeader("Pragma","No-cache");
    response.setHeader("Cache-Control","no-cache");
    response.setDateHeader("Expires", 0);
%>
<html>
<head>
<title>worklist</title>
<script language="JavaScript">
<!--
//显示需要指派的任务
function showMyTasksToAssign(bmID){
  strURL=
     "<%=request.getContextPath()%>/list_tasks_to_assign.wl";
  parent.document.all.right.src =strURL;
}

//
function showMyRefusedTasks(){
  strURL="<%=request.getContextPath()%>/list_my_refused_tasks.wl";
  parent.document.all.right.src =strURL;
}

//选中变色
function changeColor(hrefID){
	divHref = document.all.tags("font");
        for (i=0; i<divHref.length; i++) {
          divHref(i).parentNode.parentNode.className = "tdTreeNode";
          if(divHref(i).id==hrefID){
             divHref(i).parentNode.parentNode.className = "tdTreeNodeASelected";
             curChannelID = divHref(i).id;
          }
        }
}
-->
</script>
<link href="<%=request.getContextPath()%>/img/PowerStone.css" rel="stylesheet" type="text/css">
</head>
<body>
<table class="tableFrame" cellspacing="1">
<tr>
    <td class="tdTitle"><fmt:message key="task.worklist"/></td>
</tr>
<tr>
    <td class="tdContent" valign="top">
      <table class="tableNoBorder">
<!--NewTasks-->
  <div class='tdTreeNode' valign="top">
    <img align='absmiddle' src="<%=request.getContextPath()%>/img/spacer.gif" border='0'>
    <img align=absmiddle src="<%=request.getContextPath()%>/img/close.gif" border=0>&nbsp;
<strong><fmt:message key="task.new_tasks"/>(<font color="#FF0000"><c:out value="${myNewTasksNum}"/></font>)</strong>
  </div>
  <c:forEach items="${myNewTasksKinds}" var="businessType1">
  <div class='tdTreeNode' style="cursor:hand" valign="top"> &nbsp;
    <img align='absmiddle' src="<%=request.getContextPath()%>/img/spacer.gif" border='0'>
    <img align='absmiddle' src="<%=request.getContextPath()%>/img/blank.gif" border='0'>&nbsp;
    <a target="right" onclick=changeColor('new_<c:out value="${businessType1.typeID}"/>')
href='<%=request.getContextPath()%>/list_new_tasks.wl?typeID=<c:out value="${businessType1.typeID}"/>'>
    <c:out value="${businessType1.typeName}"/>(<font color="#FF0000" id='new_<c:out value="${businessType1.typeID}"/>'>
<c:out value="${businessType1.tasksNum}"/></font>)
    </a>
  </div>
  </c:forEach>
<!--ExecutingTasks-->
  <div class='tdTreeNode' valign="top">
    <img align='absmiddle' src="<%=request.getContextPath()%>/img/spacer.gif" border='0'>
    <img align='absmiddle' src="<%=request.getContextPath()%>/img/close.gif" border=0>&nbsp;
<strong><fmt:message key="task.exe_tasks"/>(<font color="#FF0000"><c:out value="${myExecutingTasksNum}"/></font>)</strong>
  </div>
  <c:forEach items="${myExecutingTasks}" var="businessType">
  <div class='tdTreeNode' style="cursor:hand" valign="top" > &nbsp;
    <img align='absmiddle' src="<%=request.getContextPath()%>/img/spacer.gif" border='0'>
    <img align='absmiddle' src="<%=request.getContextPath()%>/img/blank.gif" border='0'>&nbsp;
    <a target="right" onclick=changeColor('exe_<c:out value="${businessType.typeID}"/>')
href='<%=request.getContextPath()%>/list_executing_tasks.wl?typeID=<c:out value="${businessType.typeID}"/>'>
<c:out value="${businessType.typeName}"/>(<font color="#FF0000" id='exe_<c:out value="${businessType.typeID}"/>'>
<c:out value="${businessType.tasksNum}"/></font>)
    </a>
  </div>
  </c:forEach>
<!--RefusedTasks-->
<c:if test="${!empty myRefusedTasksNum}">
  <div class='tdTreeNode' style="cursor:hand" valign="top" >
    <img align='absmiddle' src="<%=request.getContextPath()%>/img/spacer.gif" border='0'>
    <img align='absmiddle' src="<%=request.getContextPath()%>/img/close.gif" border='0'>&nbsp;
    <strong onclick="showMyRefusedTasks();" onclick=changeColor('RefusedTasks')>
<fmt:message key="task.refused_tasks"/>(<font color="#FF0000" id="RefusedTasks"><c:out value="${myRefusedTasksNum}"/></font>)
    </strong>
  </div>
</c:if>
<!--TasksToAssign-->
<c:if test="${!empty myTasksToAssignNum}">
  <div class='tdTreeNode' style="cursor:hand" valign="top" >
    <img align='absmiddle' src="<%=request.getContextPath()%>/img/spacer.gif" border='0'>
    <img align='absmiddle' src="<%=request.getContextPath()%>/img/close.gif" border='0'>&nbsp;
<strong onclick="showMyTasksToAssign();" onclick=changeColor('TasksToAssign')>
<fmt:message key="task.tasks_to_assign"/>(<font color="#FF0000" id="TasksToAssign"><c:out value="${myTasksToAssignNum}"/></font>)
</strong>
  </div>
</c:if>
<!--FinishedTasks-->
  <div class='tdTreeNode' valign="top">
    <img align='absmiddle' src="<%=request.getContextPath()%>/img/spacer.gif" border='0'>
    <img align=absmiddle src="<%=request.getContextPath()%>/img/close.gif" border=0>&nbsp;
<strong>
<fmt:message
  key="task.finished_tasks"/>(<font color="#FF0000"><c:out value="${myFinishedTasksNum}"/></font>)
</strong>
  </div>
  <c:forEach items="${myFinishedTasksKinds}" var="businessType">
  <div class='tdTreeNode' style="cursor:hand" valign="top" > &nbsp;
    <img align='absmiddle' src="<%=request.getContextPath()%>/img/spacer.gif" border='0'>
    <img align='absmiddle' src="<%=request.getContextPath()%>/img/blank.gif" border='0'>&nbsp;
    <a target="right"  class="tablelinke" onclick=changeColor('finished_<c:out value="${businessType.typeID}"/>')
href='<%=request.getContextPath()%>/list_finished_tasks.wl?typeID=<c:out value="${businessType.typeID}"/>'>
<c:out value="${businessType.typeName}"/>(<font color="#FF0000" id='finished_<c:out value="${businessType.typeID}"/>'>
<c:out value="${businessType.tasksNum}"/></font>)
    </a>
  </div>
  </c:forEach>

       </table>
   </td>
</tr>

</table>
</body>
</html>
