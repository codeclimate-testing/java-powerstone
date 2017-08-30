<%@page  pageEncoding="GBK"  %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title><fmt:message key="ca.group_members"/></title>
<LINK href="<%=request.getContextPath()%>/img/PowerStone.css" type="text/css" rel="stylesheet">
<%
    response.setHeader("Pragma","No-cache");
    response.setHeader("Cache-Control","no-cache");
    response.setDateHeader("Expires", 0);

  Integer totalPageNum=(Integer)request.getAttribute("totalPageNum");
  Integer currPageNo=(Integer)request.getAttribute("currPageNo");
  String groupID=(String)request.getAttribute("groupID");
  String groupName=(String)request.getAttribute("groupName");
  Integer pageSize=(Integer)request.getAttribute("pageSize");
  Integer groupMembersNum=(Integer)request.getAttribute("groupMembersNum");
%>
<script language="JavaScript">
<!--
function jumpTo(){
  form1.action="<%=request.getContextPath()%>/user_group/list_group_members.ca?groupID=<%=groupID%>";
  form1.submit();
}

function createUser(){
    url="<%=request.getContextPath()%>/user_group/edit_user.ca?groupID=<%=groupID%>&pageTo=<%=currPageNo%>";
    window.open(url,target="mainFrame",'resizable=yes,width=800,height=600,scrollbars=yes');
}

var num = 0;
function count(){
  var toDo = document.all("toDo");
  if(toDo == null){
    return num;
  }
  else if(toDo && toDo.checked){
    num++;
    return num;
  }
  else if(toDo[0]){
    for(i=0; i<toDo.length; i++){
      if(toDo[i].checked){
        num++;
        return num;
      }
    }
  }
}

function remove(){
  if(count()>0){
  document.form1.action=
  "<%=request.getContextPath()%>/user_group/remove_group_members.ca?pageTo=<%=currPageNo%>&groupID=<%=groupID%>";
  document.form1.target="mainFrame";
  document.form1.submit();
  }
  else{
    alert('<fmt:message key="ca.nothing_select"/>');
    return (false);
  }
}

//changeBgColor
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
//-->
</SCRIPT>

</head>
<body>
<table class="tableFrame" cellspacing="1">
  <tr>
    <td class="tdTitle"><fmt:message key="ca.user_manage"/>&gt;&gt;<fmt:message key="ca.group_members"/>:<%=groupName%>	</td>
  </tr>
  <tr>
   <td class="tdContent">
	<br />
  <form name="form1" method="POST">
    <TABLE class="tableList" align="center">
        <tr class="TDListTitle">
          <td width="5%" align="center">&nbsp;</td>
          <td width="25%" align="left"><fmt:message key="ca.user_name"/></td>
          <td width="25%" align="left"><fmt:message key="ca.real_name"/></td>
          <td width="30%" align="left"><fmt:message key="ca.email"/></td>
          <td width="10%" align="left"><fmt:message key="ca.memo"/></td>
          <td width="5%" align="center"><fmt:message key="ca.serial_number"/></td>
        </tr>

<logic:iterate id="currUser" name="aPageMembers" indexId="ide" type="org.powerstone.ca.model.User">
        <tr
         <%if(ide.intValue()%2==0){%>class="TD3"<%}else{%>class="TD2"<%}%>
          onClick="changeBgColor(this);">
          <td align="center"><input type="checkbox" value="<%=currUser.getId()%>" name="toDo">&nbsp;</td>
          <td align="left">
            <a href='<%=request.getContextPath()%>/user_group/edit_user.ca?id=<%=currUser.getId()%>&groupID=<%=groupID%>&pageTo=<%=currPageNo%>'>
              <%=currUser.getUserName()%></a>
          </td>
          <td align="left"><%=currUser.getRealName()%></td>
          <td align="left"><%=currUser.getEmail()%></td>
          <td align="left"><%=currUser.getMemo()%></td>
          <td align="center"><%=(currPageNo.intValue()-1)*pageSize.intValue()+ide.intValue()+1%></td>
        </tr>
</logic:iterate>
<!--补齐空行-->
<%
for(int i=((java.util.List)request.getAttribute("aPageMembers")).size();i<pageSize.intValue();i++){
%>
<tr <%if(i%2==0){%>class="TD3"<%}else{%>class="TD2"<%}%>
  >
	<td >&nbsp;</td>
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

    <TABLE class="tableList" align="center">
  <tr>
    <td height="25" colspan="6" align="center">
      <input type="button" class="button_default" value='<fmt:message key="ca.new_user"/>' onclick="createUser()" >
&nbsp;<input type="button" class="button_default" value='<fmt:message key="ca.remove_user"/>' onclick="remove()" >
    </td>
  </tr>
  </table>


  <hr width="95%" align="center" size="1" >
<div class="divCenter">
<%
if(totalPageNum!=null&&totalPageNum.intValue()>1){
%><fmt:message key="task.total"/><font color="#FF0000"><%=groupMembersNum%></font>&nbsp;
  <fmt:message key="task.total"/><font color="#FF0000"><%=totalPageNum%></font> <fmt:message key="task.page"/>，
  <fmt:message key="task.curr_page_num"/><font color="#FF0000"><%=currPageNo%></font><fmt:message key="task.page"/>
  <input name="cancle" type="button" class="button_default" value='<fmt:message key="task.first_page"/>' onclick='window.open("<%=request.getContextPath()%>/user_group/list_group_members.ca?groupID=<%=groupID%>&first=1&currPageNo=1", tgrget="_self");'>&nbsp; &nbsp;
  <input name="cancle" type="button" class="button_default" value='<fmt:message key="task.last_page"/>' onclick='window.open("<%=request.getContextPath()%>/user_group/list_group_members.ca?groupID=<%=groupID%>&last=1&currPageNo=<%=currPageNo.intValue()%>", tgrget="_self");'>&nbsp;
  <input name="cancle" type="button" class="button_default" value='<fmt:message key="task.next_page"/>' onclick='window.open("<%=request.getContextPath()%>/user_group/list_group_members.ca?groupID=<%=groupID%>&next=1&currPageNo=<%=currPageNo.intValue()%>", tgrget="_self");'>&nbsp;
  <input name="cancle" type="button" class="button_default" value='<fmt:message key="task.end_page"/>' onclick='window.open("<%=request.getContextPath()%>/user_group/list_group_members.ca?groupID=<%=groupID%>&end=1&currPageNo=<%=currPageNo.intValue()%>", tgrget="_self");'>&nbsp;
  <input name="cancle" type="button" class="button_default" value='<fmt:message key="task.jump_to"/>' onclick="jumpTo()"><fmt:message key="task.the"/><input name="pageTo"  class="input1" type="text" size="2" maxlength="3" value="1"><fmt:message key="task.page"/>&nbsp; &nbsp; &nbsp;
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
