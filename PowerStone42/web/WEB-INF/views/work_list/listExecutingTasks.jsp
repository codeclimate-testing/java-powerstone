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
  form1.action="<%=request.getContextPath()%>/list_executing_tasks.wl?typeID=<%=typeID%>";
  form1.submit();
}

//»»±³¾°É«
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
<title>exeTasks</title>
<link href="<%=request.getContextPath()%>/img/PowerStone.css" rel="stylesheet" type="text/css">
</head>
<body>
<table class="tableFrame" cellspacing="1">
  <tr>
    <td class="tdTitle"><fmt:message key="task.exe_tasks"/></td>
  </tr>

  <tr>
   <td class="tdContent">
	<br />
<form name="form1" method="post">
      <TABLE class="tableList" align="center">
        <tr class="TDListTitle">
        <TD align="center" width="2%">&nbsp;</TD>
        <TD align="left" width="60%"><fmt:message key="task.memo"/></TD>
        <TD align="left" width="20%"><fmt:message key="task.flow_belong"/></TD>
        <TD align="center" width="18%"><fmt:message key="task.lock_time"/></TD>
        </tr>

  <logic:iterate id="currTask" name="aPageExeTasks"  indexId="ide"
    type="org.powerstone.workflow.model.FlowTask">
    <tr style='cursor:hand'
      <%if(ide.intValue()%2==0){%>class="TD3"<%}else{%>class="TD2"<%}%>
      onClick="changeBgColor(this,'<%=currTask.getTaskID()%>');" title='<fmt:message key="task.onclick"/>'>
      <td align="center"><%=(currPageNo.intValue()-1)*pageSize.intValue()+ide.intValue()+1%></td>
      <td align="left">
        <b>
<%=currTask.getFlowNodeBinding().getWorkflowDriver().getFlowDriverName()%>
        </b>
<%=currTask.getPreviewText()%>
      </td>

      <td align="left">
<%=currTask.getFlowNodeBinding().getFlowDeploy().getWorkflowMeta().getFlowFileInUse().getFlowMetaName()%>
      </td>

      <td>
<%String timeStr=currTask.getStartTime();%>
<%=timeStr!=null&&timeStr.length()>10?timeStr.substring(0,2)+"-"+timeStr.substring(2,4)+"-"+timeStr.substring(4,6)+"&nbsp;"+timeStr.substring(6,8)+":"+timeStr.substring(8,10):""%>
      </td>
    </tr>
  </logic:iterate>
<!--²¹Æë¿ÕÐÐ-->
<%
for(int i=((java.util.List)request.getAttribute("aPageExeTasks")).size();
    i<pageSize.intValue();i++){
%>
<tr <%if(i%2==0){%>class="TD3"<%}else{%>class="TD2"<%}%>
  >
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
<fmt:message key="task.total"/><font color="#FF0000"><%=totalPageNum%></font> <fmt:message key="task.page"/>£¬
<fmt:message key="task.curr_page_num"/><font color="#FF0000"><%=currPageNo%></font><fmt:message key="task.page"/>
<input name="cancle" type="button" class="button_default" value='<fmt:message key="task.first_page"/>' onclick='window.open("<%=request.getContextPath()%>/list_executing_tasks.wl?typeID=<%=typeID%>&first=1&currPageNo=1", tgrget="_self");'>&nbsp; &nbsp;
<input name="cancle" type="button" class="button_default" value='<fmt:message key="task.last_page"/>' onclick='window.open("<%=request.getContextPath()%>/list_executing_tasks.wl?typeID=<%=typeID%>&last=1&currPageNo=<%=currPageNo.intValue()%>", tgrget="_self");'>&nbsp;
<input name="cancle" type="button" class="button_default" value='<fmt:message key="task.next_page"/>' onclick='window.open("<%=request.getContextPath()%>/list_executing_tasks.wl?typeID=<%=typeID%>&next=1&currPageNo=<%=currPageNo.intValue()%>", tgrget="_self");'>&nbsp;
<input name="cancle" type="button" class="button_default" value='<fmt:message key="task.end_page"/>' onclick='window.open("<%=request.getContextPath()%>/list_executing_tasks.wl?typeID=<%=typeID%>&end=1&currPageNo=<%=currPageNo.intValue()%>", tgrget="_self");'>&nbsp;
<input name="cancle" type="button" class="button_default" value='<fmt:message key="task.jump_to"/>' onclick="jumpTo()"><fmt:message key="task.the"/><input name="pageTo" class="input1" type="text" size="2" maxlength="3" value="1"><fmt:message key="task.page"/>&nbsp; &nbsp; &nbsp;
<%
   }
%>
<br />
</div>

</form>
          </td>
        </tr>
      </table>
</body>
</html>
