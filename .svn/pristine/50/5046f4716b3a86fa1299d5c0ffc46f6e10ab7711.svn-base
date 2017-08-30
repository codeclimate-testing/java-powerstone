<%@page  pageEncoding="GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%
    response.setHeader("Pragma","No-cache");
    response.setHeader("Cache-Control","no-cache");
    response.setDateHeader("Expires", 0);

  Integer totalPageNum=(Integer)request.getAttribute("totalPageNum");
  Integer currPageNo=(Integer)request.getAttribute("currPageNo");
  String typeID=(String)request.getAttribute("typeID");
  Integer pageSize=(Integer)request.getAttribute("pageSize");
%>
<script language="JavaScript">
<!--
function jumpTo(){
  form1.action="<%=request.getContextPath()%>/list_finished_tasks.wl?typeID=<%=typeID%>";
  form1.submit();
}
-->
</script>
<html>
<head>
<title>finishedTasks</title>
<link href="<%=request.getContextPath()%>/img/PowerStone.css" rel="stylesheet" type="text/css">
</head>
<body>
<table class="tableFrame" cellspacing="1">
  <tr>
    <td class="tdTitle"><fmt:message key="task.finished_tasks"/></td>
  </tr>

  <tr>
   <td class="tdContent">
	<br />
<form name="form1" method="post">
<TABLE class="tableList" align="center">
     <tr class="TDListTitle">
       <TD width="5%" align="center">&nbsp;</TD>
       <TD width="55%" align="left"><fmt:message key="task.memo"/></TD>
       <TD width="20%" align="center"><fmt:message key="task.flow_belong"/></TD>
       <TD width="18%" align="center"><fmt:message key="task.finish_time"/></TD>
       <TD width="2%" align="center">&nbsp;</TD>
     </tr>

  <logic:iterate id="currTask" name="aPageFinishedTasks"  indexId="ide"
    type="org.powerstone.workflow.model.FlowTask">
    <tr <%if(ide.intValue()%2==0){%>class="TD3"<%}else{%>class="TD2"<%}%>>
      <td align="center">
           <%=(currPageNo.intValue()-1)*pageSize.intValue()+ide.intValue()+1%>
      </td>
      <td align="left">
        <b>
<%=currTask.getFlowNodeBinding().getWorkflowDriver().getFlowDriverName()%>
        </b>
<%=currTask.getPreviewText()%>
      </td>

      <td align="center">
<%=currTask.getFlowNodeBinding().getFlowDeploy().getWorkflowMeta().getFlowFileInUse().getFlowMetaName()%>
      </td>

      <td align="center">
<%String timeStr=currTask.getOverTime();%>
<%=timeStr!=null&&timeStr.length()>10?timeStr.substring(0,2)+"-"+timeStr.substring(2,4)+"-"+timeStr.substring(4,6)+"&nbsp;"+timeStr.substring(6,8)+":"+timeStr.substring(8,10):""%>
      </td>
      <td align="center">
        <input type="button" name="taskID" class="button_default" value="取回任务"
        onclick="if(confirm('您确认要取回任务吗？')){window.location.replace('<%=request.getContextPath()%>/get_back_task.wl?taskID=<%=currTask.getTaskID()%>');}" >
      </td>
    </tr>
  </logic:iterate>
<!--补齐空行-->
<%
for(int i=((java.util.List)request.getAttribute("aPageFinishedTasks")).size();
    i<pageSize.intValue();i++){
%>
<tr <%if(i%2==0){%>class="TD3"<%}else{%>class="TD2"<%}%>
  >
	<td >&nbsp;</td>
	<td >&nbsp;</td>
	<td >&nbsp;</td>
	<td >&nbsp;</td>
        <td >&nbsp;</td>
</tr>
<%
}
%>
    </TABLE>

<hr width="95%" align="center" size="1" >

<div class="divCenter">
<%
if(totalPageNum!=null&&totalPageNum.intValue()>1){
%>
<fmt:message key="task.total"/><font color="#FF0000"><%=totalPageNum%></font> <fmt:message key="task.page"/>，
<fmt:message key="task.curr_page_num"/><font color="#FF0000"><%=currPageNo%></font><fmt:message key="task.page"/>
<input name="cancle" type="button" class="button_default" value='<fmt:message key="task.first_page"/>' onclick='window.open("<%=request.getContextPath()%>/list_finished_tasks.wl?typeID=<%=typeID%>&first=1&currPageNo=1", tgrget="_self");'>&nbsp; &nbsp;
<input name="cancle" type="button" class="button_default" value='<fmt:message key="task.last_page"/>' onclick='window.open("<%=request.getContextPath()%>/list_finished_tasks.wl?typeID=<%=typeID%>&last=1&currPageNo=<%=currPageNo.intValue()%>", tgrget="_self");'>&nbsp;
<input name="cancle" type="button" class="button_default" value='<fmt:message key="task.next_page"/>' onclick='window.open("<%=request.getContextPath()%>/list_finished_tasks.wl?typeID=<%=typeID%>&next=1&currPageNo=<%=currPageNo.intValue()%>", tgrget="_self");'>&nbsp;
<input name="cancle" type="button" class="button_default" value='<fmt:message key="task.end_page"/>' onclick='window.open("<%=request.getContextPath()%>/list_finished_tasks.wl?typeID=<%=typeID%>&end=1&currPageNo=<%=currPageNo.intValue()%>", tgrget="_self");'>&nbsp;
<input name="cancle" type="button" class="button_default" value='<fmt:message key="task.jump_to"/>' onclick="jumpTo()"><fmt:message key="task.the"/><input name="pageTo" class="input1" type="text" size="2" maxlength="3" value="1"><fmt:message key="task.page"/>&nbsp; &nbsp; &nbsp;
	    </td>
<%
   }
%>
</div>
   <br />
</form>
          </td>
        </tr>
      </table>

</body>
</html>
