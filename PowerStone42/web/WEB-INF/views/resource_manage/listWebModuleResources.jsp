<%@page  pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>��Դ�б�</title>
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
    alert("��û��ѡ���κ���Ϣ");
    return (false);
  }
}

//������ɫ
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
    <td class="tdTitle">Ȩ��ע��&gt;&gt;���ڴ�WEBģ�����Դ</td>
  </tr>
  <tr>
   <td class="tdContent">
     <table class="tableNoBorderCenter" width="95%" align="center">
        <tr>
    	<td align="center">
      	<div align="left">��ԴIDҪΨһ������"function_"��ͷ����Դ����ҲҪΨһ</div>
    	</td>
  	</tr>
     </table>
<form name="form1" method="POST">
      <TABLE class="tableList" align="center">
        <tr class="TDListTitle">
          <td width="3%" align="center">&nbsp;</td>
          <td width="20%" align="left">��Դ����</td>
          <td width="30%" align="left">��ԴID</td>
          <td width="40%" align="left">��Դ·��</td>
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
&nbsp;<input class="button_default" type="button" value="�½�" onclick="add()" >
&nbsp;<input class="button_default" type="button" value="ɾ��" onclick="remove()" >
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
