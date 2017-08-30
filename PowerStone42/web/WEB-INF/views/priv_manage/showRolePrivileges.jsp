<%@page  pageEncoding="GBK"
 import="java.util.*,org.powerstone.ca.model.*"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>showRolePrivileges</title>
<LINK href="<%=request.getContextPath()%>/img/PowerStone.css" type="text/css" rel="stylesheet">
</head>

<body>
<table class="tableFrame" cellspacing="1">
  <tr>
    <td class="tdTitle">角色授权&gt;&gt;修改角色权限</td>
  </tr>
  <tr>
   <td class="tdContent">
     <table class="tableNoBorderCenter" width="95%" align="center">
        <tr>
    	<td align="center">
      	<div align="right">
        <input type="button" class="button_default" onclick="edit();" value="提 交"/>
      	</div>
    	</td>
  	</tr>
     </table>
<form name="form1" method="POST">
<%
HashMap roleRightsMap=(HashMap)request.getAttribute("roleRightsMap");
List allModules =(List)request.getAttribute("allModules");
for(Iterator it=allModules.iterator();it.hasNext();){
    WebModule theWebModule = (WebModule)it.next();
%>
<TABLE class="tableList" align="center">
    <tr class="TDListTitle">
          <td width="10%" nowrap="nowrap" align="center"><%=theWebModule.getWebModuleName()%></td>
          <td width="30%"  align="center">资源ID</td>
          <td width="30%"  align="center">资源名称</td>
          <td width="30%"  align="center">资源路径</td>
    </tr>
<%  List resources=theWebModule.getResources();
    int ide=0;
    for(Iterator itr=resources.iterator();itr.hasNext();ide++){
      Resource theResource = (Resource)itr.next();
%>
        <tr
         <%if(ide%2==0){%>class="TD3"<%}else{%>class="TD2"<%}%>
          onClick="changeBgColor(this);">
          <td>
<input type="checkbox" id="WebModule_<%=theWebModule.getWebModuleID()%>" name="resourceIDs"
  value="<%=theResource.getId()%>"
  <%if(roleRightsMap.get(theResource.getId())!=null){%>
checked="checked"
  <%}%>>
          </td>
          <td align="left"><%=theResource.getResourceID()%></td>
          <td align="center"><%=theResource.getResourceName()%></td>
          <td align="left"><%=theResource.getActionURL()%></td>
        </tr>
<%
    }
%>
    </TABLE>

    <TABLE class="tableList" align="center">
    <tr>
       <td height="25" colspan="6" align="center">
<input type="button" name="selectAll"
  onclick="selectAllBox(this,'WebModule_<%=theWebModule.getWebModuleID()%>');"
  value="全部选中" class="button_default"/>
       </td>
    </tr>
    </TABLE>
<hr width="95%" align="center" size="1" >
<%
    }
%>
</form>
          </td>
        </tr>
      </table>
</body>
</html>

<SCRIPT LANGUAGE="JavaScript">
<!--
var beSubmit = false;
function edit(){
if(confirm("您确认要修改吗？")){
  if(beSubmit == false){
    document.form1.action =
'<%=request.getContextPath()%>/priv_manage/update_role_privileges.priv?roleID=<%=request.getAttribute("roleID")%>';
    document.form1.target = "mainFrame";
    beSubmit = true;
    document.form1.submit();
  }
}
return false;
}

var select=false;
function selectAllBox(but,webID){
  if(select==false){
     inputItems = document.all.tags("input");
     for (i=0; i<inputItems.length; i++) {
       if(inputItems(i).id==webID){
         inputItems(i).checked = true;
       }
     }
     select=true;
     but.value='取消选中';
  }else{
    inputItems = document.all.tags("input");
     for (i=0; i<inputItems.length; i++) {
       if(inputItems(i).id==webID){
         inputItems(i).checked = false;
       }
     }
     select=false;
     but.value='全部选中';
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
</SCRIPT>
