<%@page  pageEncoding="GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%
    response.setHeader("Pragma","No-cache");
    response.setHeader("Cache-Control","no-cache");
    response.setDateHeader("Expires", 0);
%>
<html>
<head>
<title>newTasks</title>
<link href="<%=request.getContextPath()%>/img/PowerStone.css" rel="stylesheet" type="text/css">
</head>
<%
  Integer totalPageNum=(Integer)request.getAttribute("totalPages");
  Integer currPageNo=(Integer)request.getAttribute("currPageNo");
  String typeID=(String)request.getAttribute("typeID");
  Integer pageSize=(Integer)request.getAttribute("pageSize");
%>

<body>
<table class="tableFrame" cellspacing="1">
  <tr>
    <td class="tdTitle"><fmt:message key="task.new_tasks"/>(<%=((java.util.List)request.getAttribute("aPageNewTasks")).size()%>)</td>
  </tr>

  <tr>
   <td class="tdContent">
	<br />
<form name="form1" method="POST" action="<%=request.getContextPath()%>/check_out_tasks.wl">
      <TABLE class="tableList" align="center">
        <tr class="TDListTitle">
          <TD align="center" width="4%">&nbsp;</TD>
          <TD width="70%" align="left"><fmt:message key="task.memo"/></TD>
          <TD width="20%"><fmt:message key="task.start_time"/></TD>
          <TD width="6%"><fmt:message key="task.serial"/></TD>
        </tr>
<logic:iterate id="currTask" name="aPageNewTasks" indexId="ide"
    type="org.powerstone.workflow.model.FlowTask">
    <tr <%if(ide.intValue()%2==0){%>class="TD3"<%}else{%>class="TD2"<%}%>
          onClick="changeBgColor(this);">
      <td><input type="checkbox" name="taskID" value='<%=currTask.getTaskID()%>'></td>
      <td align="left">
   <b>
<%=currTask.getFlowNodeBinding().getWorkflowDriver().getFlowDriverName()%>
   </b>
<%=currTask.getPreviewText()%>
      </td>

      <td>
<%String timeStr=currTask.getCreateTime();%>
<%=timeStr!=null&&timeStr.length()>10?timeStr.substring(0,2)+"-"+timeStr.substring(2,4)+"-"+timeStr.substring(4,6)+"&nbsp;"+timeStr.substring(6,8)+":"+timeStr.substring(8,10):""%>
      </td>
      <td align="center">
        <%=(currPageNo.intValue()-1)*pageSize.intValue()+ide.intValue()+1%>
      </td>
    </tr>
  </logic:iterate>
<!--²¹Æë¿ÕÐÐ-->
<%
for(int i=((java.util.List)request.getAttribute("aPageNewTasks")).size();
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

    <TABLE class="tableList" align="center">
  <tr>
    <td height="25" colspan="6" align="center">
<input type="button" name="selectAll" value='<fmt:message key="task.select_all"/>'
class="button_default" onclick="selectAllBox();" />
<input type="button" value='<fmt:message key="task.check"/>' class="button_default"
onclick="checkOutTask();" />
    </td>
  </tr>
    </TABLE>

<hr width="95%" align="center" size="1" >

<div class="divCenter">
<%
if(totalPageNum!=null&&totalPageNum.intValue()>1){
%>
<fmt:message key="task.total"/><font color="#FF0000"><%=totalPageNum%></font><fmt:message key="task.page"/>
<fmt:message key="task.curr_page_num"/><font color="#FF0000"><%=currPageNo%></font><fmt:message key="task.page"/>
<input name="cancle" type="button" class="button_default" value='<fmt:message key="task.first_page"/>' onclick='window.open("<%=request.getContextPath()%>/list_new_tasks.wl?typeID=<%=typeID%>&first=1&currPageNo=1", tgrget="_self");'>&nbsp; &nbsp;
<input name="cancle" type="button" class="button_default" value='<fmt:message key="task.last_page"/>' onclick='window.open("<%=request.getContextPath()%>/list_new_tasks.wl?typeID=<%=typeID%>&last=1&currPageNo=<%=currPageNo.intValue()%>", tgrget="_self");'>&nbsp;
<input name="cancle" type="button" class="button_default" value='<fmt:message key="task.next_page"/>' onclick='window.open("<%=request.getContextPath()%>/list_new_tasks.wl?typeID=<%=typeID%>&next=1&currPageNo=<%=currPageNo.intValue()%>", tgrget="_self");'>&nbsp;
<input name="cancle" type="button" class="button_default" value='<fmt:message key="task.end_page"/>' onclick='window.open("<%=request.getContextPath()%>/list_new_tasks.wl?typeID=<%=typeID%>&end=1&currPageNo=<%=currPageNo.intValue()%>", tgrget="_self");'>&nbsp;
<input name="cancle" type="button" class="button_default" value='<fmt:message key="task.jump_to"/>' onclick="jumpTo()"><fmt:message key="task.the"/><input name="pageTo" class="input1" type="text" size="2" maxlength="3" value="1"><fmt:message key="task.page"/>&nbsp; &nbsp; &nbsp;
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

<script language="JavaScript">
<!--
function chechOutAndExecuteTask(taskID,activityID){
  url="<%=request.getContextPath()%>/checkout_and_executing_task.do?taskID="+
      taskID+"&activityID="+activityID;
  window.open(url,'','resizable=1,width=720,height=540,scrollbars=0');
}

function jumpTo(){
  form1.action="<%=request.getContextPath()%>/list_new_tasks.wl?typeID=<%=typeID%>";
  form1.submit();
}

function checkOutTask(){
  inputItems = document.all.tags("input");
  for (i=0; i<inputItems.length; i++) {
    if(inputItems(i).checked == true){
         //   inputItems(i).checked = "true";
      form1.submit();
      return true;
    }
  }
  alert('<fmt:message key="task.pls_select"/>');
  return false;
}
var select=false;
function selectAllBox(){
  if(select==false){
     inputItems = document.all.tags("input");
     for (i=0; i<inputItems.length; i++) {
       inputItems(i).checked = true;
     }
     select=true;
     form1.selectAll.value='<fmt:message key="task.dis_select"/>';
  }else{
    inputItems = document.all.tags("input");
     for (i=0; i<inputItems.length; i++) {
       inputItems(i).checked = false;
     }
     select=false;
     form1.selectAll.value='<fmt:message key="task.select_all"/>';
  }
}

//»»±³¾°É«
var oldclassName = null;
var cunObj = null;
function changeBgColor(tempi){
    if(cunObj != null){
	cunObj.className=oldclassName;
	}
	oldclassName=tempi.className;
    tempi.className="TD4";
	cunObj=tempi;
}
-->
</script>
