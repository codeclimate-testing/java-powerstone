<%@page  pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>资源列表</title>
<LINK href="<%=request.getContextPath()%>/img/PowerStone.css" type="text/css" rel="stylesheet">
<%  response.setHeader("Pragma","No-cache");
    response.setHeader("Cache-Control","no-cache");
    response.setDateHeader("Expires", 0);

    String webModuleID=(String)request.getAttribute("webModuleID");
%>
<script language="JavaScript">
<!--
function add(){
 url="<%=request.getContextPath()%>/resource_manage/edit_resource.resource?webModuleID=<%=webModuleID%>";
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
  "<%=request.getContextPath()%>/resource_manage/remove_resource.resource?webModuleID=<%=webModuleID%>";
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
    <td class="tdTitle">权限注册&gt;&gt;属于此WEB模块的资源</td>
  </tr>
  <tr>
   <td class="tdContent">
     <table class="tableNoBorderCenter" width="95%" align="center">
        <tr>
    	<td align="center">
      	<div align="left">资源ID要唯一，并以"function_"开头，资源名称也要唯一</div>
    	</td>
  	</tr>
     </table>
<form name="form1" method="POST">
      <TABLE class="tableList" align="center">
        <tr class="TDListTitle">
          <td width="3%" align="center">&nbsp;</td>
          <td width="20%" align="left">资源名称</td>
          <td width="30%" align="left">资源ID</td>
          <td width="40%" align="left">资源路径</td>
        </tr>
<logic:iterate id="currResource" name="resources" indexId="ide"
    type="org.powerstone.ca.model.Resource">
        <tr
         <%if(ide.intValue()%2==0){%>class="TD3"<%}else{%>class="TD2"<%}%>
          onClick="changeBgColor(this);">
          <td><input type="checkbox" value="<%=currResource.getId()%>" name="toDo">&nbsp;</td>
          <td align="left">
<a href='<%=request.getContextPath()%>/resource_manage/edit_resource.resource?webModuleID=<%=webModuleID%>&rsID=<%=currResource.getId()%>'>
          <%=currResource.getResourceName()%></a>
          </td>
          <td align="left">
              <%=currResource.getResourceID()%>
          </td>
          <td align="left"><%=currResource.getActionURL()%></td>
        </tr>

</logic:iterate>
    </TABLE>

    <TABLE class="tableList" align="center">
  <tr>
    <td height="25" colspan="6" align="center">
&nbsp;<input class="button_default" type="button" value="新建" onclick="add()" >
&nbsp;<input class="button_default" type="button" value="删除" onclick="remove()" >
    </td>
  </tr>
      </TABLE>
<hr width="95%" align="center" size="1" >
</form>
          </td>
        </tr>
      </table>

</body>
</html>
