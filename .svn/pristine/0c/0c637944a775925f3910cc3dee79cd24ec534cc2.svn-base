<%@page  pageEncoding="GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title><fmt:message key="ca.users_no_group"/></title>
<LINK href="<%=request.getContextPath()%>/img/PowerStone.css" type="text/css" rel="stylesheet">
<script language="JavaScript">
<!--
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

function move(){
  if(count()>0){
     url="<%=request.getContextPath()%>/user_group/add_users_to_group.ca";
     document.form1.action=url;
     document.form1.target="mainFrame";
     document.form1.submit();
  }else{
    alert('<fmt:message key="error.ca.select_to_move"/>');
    form1.groupTree.value='init';
    return (false);
  }
}

//change bgcolor
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
  <tr class="tdTitle">
     <td><fmt:message key="ca.user_manage"/>&gt;&gt;<fmt:message key="ca.users_no_group"/></td>
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
<logic:iterate id="currUser" name="usersHaveNoGroup"  indexId="ide"
    type="org.powerstone.ca.model.User">
        <tr <%if(ide.intValue()%2==0){%>class="TD3"<%}else{%>class="TD2"<%}%>
          onClick="changeBgColor(this);">
          <td align="center"><input type="checkbox" value="<%=currUser.getId()%>" name="toDo">&nbsp;</td>
          <td align="left">
            <a href='<%=request.getContextPath()%>/user_group/edit_user.ca?id=<%=currUser.getId()%>'>
              <%=currUser.getUserName()%></a>
          </td>
          <td align="left"><%=currUser.getRealName()%></td>
          <td align="left"><%=currUser.getEmail()%></td>
          <td align="left"><%=currUser.getMemo()%></td>
          <td align="center"><%=ide.intValue()+1%></td>
        </tr>
</logic:iterate>

  <tr>
    <td height="25" colspan="6" align="center"><fmt:message key="ca.move_user_to"/>
      <select id="groupTree" class="button_default" name="groupID" onchange="move();">
        <option value="init" selected="selected" ><fmt:message key="ca.option_group"/></option>
<%=(String)request.getAttribute("optionsTree")%>
      </select>
    </td>
  </tr>
  </TABLE>
</form>

  </td>
  </tr>
</table>
</body>
</html>
