<%@page  pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%
  String roleID=(String)request.getAttribute("roleID");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>部门用户</title>
<LINK href="<%=request.getContextPath()%>/img/PowerStone.css" type="text/css" rel="stylesheet">
<script language="JavaScript">
<!--
function addUsersToRole(){
  var toAdd = document.getElementsByName("toDo");
  var flag = false;
  for(var i = 0 ; i < toAdd.length; i ++){
    if(toAdd[i].checked){
      flag = true;
    }
  }
  if(flag == false){
    alert("请至少选择一个用户！");
    return (false);
  }
document.form1.action=
"<%=request.getContextPath()%>/role_manage/add_users_to_role.role?roleID=<%=roleID%>";
document.form1.target="mainFrame";
document.form1.submit();
top.window.close();
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
    <td class="tdTitle">为角色添加人员&gt;&gt;属于此部门的人员</td>
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
<logic:iterate id="currUser" name="groupMembers" indexId="ide" type="org.powerstone.ca.model.User">
        <tr
         <%if(ide.intValue()%2==0){%>class="TD3"<%}else{%>class="TD2"<%}%>
          onClick="changeBgColor(this);">
          <td><input type="checkbox" value="<%=currUser.getId()%>" name="toDo">&nbsp;</td>
          <td><%=ide.intValue()+1%></td>
          <td><%=currUser.getUserName()%></td>
          <td><%=currUser.getRealName()%></td>
          <td><%=currUser.getEmail()%>&nbsp;</td>
        </tr>
</logic:iterate>
  </TABLE>

  <TABLE class="tableList" align="center">
  <tr>
    <td height="25" colspan="6" align="center">
&nbsp;<input type="button" class="button_default" value="确定" onclick="addUsersToRole()" >
    </td>
  </tr>
  </TABLE>
</form>
          </td>
        </tr>
      </table>

</body>
</html>
