<%@page  pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>角色用户</title>
<LINK href="<%=request.getContextPath()%>/img/PowerStone.css" type="text/css" rel="stylesheet">
<%
    response.setHeader("Pragma","No-cache");
    response.setHeader("Cache-Control","no-cache");
    response.setDateHeader("Expires", 0);

  Integer totalPageNum=(Integer)request.getAttribute("totalPageNum");
  Integer currPageNo=(Integer)request.getAttribute("currPageNo");
  String roleID=(String)request.getAttribute("roleID");
  Integer pageSize=(Integer)request.getAttribute("pageSize");
  Integer roleMembersNum=(Integer)request.getAttribute("roleMembersNum");
%>
<script language="JavaScript">
<!--
function jumpTo(){
  form1.action="<%=request.getContextPath()%>/role_manage/list_role_members.role?roleID=<%=roleID%>";
  form1.submit();
}
function add(){
  url="<%=request.getContextPath()%>/role_manage/add_role_members.role?roleID=<%=roleID%>";
  var reVal = window.open(url,'','resizable=yes,width=800,height=600');

//    url="<%=request.getContextPath()%>/role_manage/add_role_members.role?roleID=<%=roleID%>";
//    window.open(url,target="mainFrame",'resizable=yes,width=800,height=600,scrollbars=yes');
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
  "<%=request.getContextPath()%>/role_manage/remove_role_members.role?pageTo=<%=currPageNo%>&roleID=<%=roleID%>";
  document.form1.target="mainFrame";
  document.form1.submit();
  }
  else{
    alert("您没有选择任何信息");
    return (false);
  }
}

//换背景色
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

</head>
<body>
<table class="tableFrame" cellspacing="1">
  <tr>
    <td class="tdTitle">角色管理&gt;&gt;属于此角色的人员</td>
  </tr>

  <tr>
   <td class="tdContent">
	<br />
<form name="form1" method="POST">
      <TABLE class="tableList" align="center">
        <tr class="TDListTitle">
          <td width="5%" align="center">&nbsp;</td>
          <td width="5%" align="center">序号</td>
          <td width="25%" align="center">用户名</td>
          <td width="25%" align="center">真实姓名</td>
          <td width="40%" align="center">email</td>
        </tr>
<logic:iterate id="currUser" name="aPageMembers" indexId="ide" type="org.powerstone.ca.model.User">
        <tr
         <%if(ide.intValue()%2==0){%>class="TD3"<%}else{%>class="TD2"<%}%>
          onClick="changeBgColor(this);">
          <td><input type="checkbox" value="<%=currUser.getId()%>" name="toDo">&nbsp;</td>
          <td><%=(currPageNo.intValue()-1)*15+ide.intValue()+1%></td>
          <td><%=currUser.getUserName()%></td>
          <td><%=currUser.getRealName()%></td>
          <td><%=currUser.getEmail()%>&nbsp;</td>
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
</tr>
<%
}
%>
    </TABLE>

    <TABLE class="tableList" align="center">
  <tr>
    <td height="25" colspan="6" align="center">
&nbsp;<input type="button" class="button_default" value="添加" onclick="add()" >
&nbsp;<input type="button" class="button_default" value="删除" onclick="remove()" >
    </td>
  </tr>
    </TABLE>

<hr width="95%" align="center" size="1" >

<div class="divCenter">
<%
if(totalPageNum!=null&&totalPageNum.intValue()>1){
%>总计<font color="#FF0000"><%=roleMembersNum%></font>条，
  <fmt:message key="task.total"/><font color="#FF0000"><%=totalPageNum%></font> <fmt:message key="task.page"/>，
  <fmt:message key="task.curr_page_num"/><font color="#FF0000"><%=currPageNo%></font><fmt:message key="task.page"/>
  <input name="cancle" type="button" class="button_default" value='<fmt:message key="task.first_page"/>' onclick='window.open("<%=request.getContextPath()%>/role_manage/list_role_members.role?roleID=<%=roleID%>&first=1&currPageNo=1", tgrget="_self");'>&nbsp; &nbsp;
  <input name="cancle" type="button" class="button_default" value='<fmt:message key="task.last_page"/>' onclick='window.open("<%=request.getContextPath()%>/role_manage/list_role_members.role?roleID=<%=roleID%>&last=1&currPageNo=<%=currPageNo.intValue()%>", tgrget="_self");'>&nbsp;
  <input name="cancle" type="button" class="button_default" value='<fmt:message key="task.next_page"/>' onclick='window.open("<%=request.getContextPath()%>/role_manage/list_role_members.role?roleID=<%=roleID%>&next=1&currPageNo=<%=currPageNo.intValue()%>", tgrget="_self");'>&nbsp;
  <input name="cancle" type="button" class="button_default" value='<fmt:message key="task.end_page"/>' onclick='window.open("<%=request.getContextPath()%>/role_manage/list_role_members.role?roleID=<%=roleID%>&end=1&currPageNo=<%=currPageNo.intValue()%>", tgrget="_self");'>&nbsp;
  <input name="cancle" type="button" class="button_default" value='<fmt:message key="task.jump_to"/>' onclick="jumpTo()"><fmt:message key="task.the"/><input class="input1" name="pageTo" type="text" size="2" maxlength="3" value="1"><fmt:message key="task.page"/>&nbsp; &nbsp; &nbsp;
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
