<%@page  pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%
    response.setHeader("Pragma","No-cache");
    response.setHeader("Cache-Control","no-cache");
    response.setDateHeader("Expires", 0);
%>
<html>
<head>
<title><fmt:message key="seeFlowMeta.title"/></title>
<LINK href="<%=request.getContextPath()%>/img/PowerStone.css" type="text/css" rel="stylesheet">
<script language="JavaScript">
<!--
var selectedTR;
var deployName;
//调整工作流类型
function changeFlowModule(){
  window.location.replace(
'<%=request.getContextPath()%>/wf/list_bt_to_join.bt?flowMetaID=<c:out value="${workflowMeta.flowMetaID}"/>');
}

//新建部署
function newDeploy(){
  window.location.replace(
'<%=request.getContextPath()%>/wf/edit_flow_deploy.dpl?workflowMetaID=<c:out value="${workflowMeta.flowMetaID}"/>');
}

//编辑部署信息
function editDeploy(){
if(selectedTR!=undefined){
  window.location.replace(
    '<%=request.getContextPath()%>/wf/edit_flow_deploy.dpl?deployID='+selectedTR
    +'&workflowMetaID=<c:out value="${workflowMeta.flowMetaID}"/>');
}else{
    alert('<fmt:message key="deploy.please_select"/>');
}
}

//配置
function deploy(){
  if(selectedTR!=undefined){
    //alert(selectedTR);
    url="<%=request.getContextPath()%>/wf/see_flow_deploy.dpl?flowDeployID="+selectedTR;
    window.open(url,'','');
  }else{
    alert('<fmt:message key="deploy.please_select"/>');
  }
}

//设置截止期限
function setDeadLine(){
  if(selectedTR!=undefined){
    url="<%=request.getContextPath()%>/deploy/set_deadline.do?workflowID="+selectedTR.substring(4);
    var result=
    window.showModalDialog(url,"","dialogWidth=600px;dialogHeight=400px;status=0;help=0");
  }else{
    alert('<fmt:message key="deploy.please_select"/>');
  }
}

//删除部署
function deleteDeploy(){
  if(deployName!=undefined){
    if(confirm('<fmt:message key="deploy.confirm_delete" />'+'['+deployName+']'+'<fmt:message key="deploy.confirm" />')){
      window.location.replace(
      '<%=request.getContextPath()%>/wf/remove_flow_deploy.dpl?flowDeployID='+selectedTR);
    }
  }else{
    alert('<fmt:message key="deploy.please_select"/>');
  }
}

//启动部署
function enableDeploy(){
  if(deployName!=undefined){
    if(confirm('<fmt:message key="deploy.confirm_enable" />'+'['+deployName+']'+'<fmt:message key="deploy.confirm" />')){
     window.location.replace(
     '<%=request.getContextPath()%>/wf/enable_flow_deploy.dpl?flowDeployID='
     +selectedTR);
    }
  }else{
    alert('<fmt:message key="deploy.please_select"/>');
   }
}

//停用部署
function disableDeploy(){
  if(deployName!=undefined){
    if(confirm('<fmt:message key="deploy.confirm_disable" />'+'['+deployName+']'+'<fmt:message key="deploy.confirm" />')){
      window.location.replace(
      '<%=request.getContextPath()%>/wf/disable_flow_deploy.dpl?flowDeployID='
      +selectedTR);
    }
  }else{
    alert('<fmt:message key="deploy.please_select"/>');
   }
}

//监控
function monitor(){
  if(deployName!=undefined){
      url="<%=request.getContextPath()%>/wf/flow_monitor.moni?flowDeployID="+selectedTR;
      window.location.replace(url);
  }else{
    alert('<fmt:message key="deploy.please_select"/>');
   }
}

//换背景色
var oldclassName = null;
var cunObj = null;
function changeBgColor(tempi,tempName){
  selectedTR=tempi.id;
  deployName=tempName;
    if(cunObj != null){
	cunObj.className=oldclassName;
	}
	oldclassName=tempi.className;
    tempi.className="TD4";
	cunObj=tempi;
}

//删除工作流
function deleteFlow(){
  if(confirm('<fmt:message key="fm.confirm_delete"/>')){
     url =
      "<%=request.getContextPath()%>/wf/remove_flow_meta.fm?flowMetaID=<c:out value="${workflowMeta.flowMetaID}"/>";
      window.location.replace(url);
  }
}
-->
</script>
</head>
</head>
<body>
<table class="tableFrame" cellspacing="1">
  <tr>
    <td class="tdTitle">工作流管理&gt;&gt;流程详细信息</td>
  </tr>
  <tr>
   <td class="tdContent">
	<br />

<c:if test="${not empty message}">
<table class="tableNoBorderCenter"  align="center">
    <div class="error">
         <c:out value="${message}" escapeXml="false"/><br />
         <%request.getSession().removeAttribute("message");%>
    </div>
</table>
</c:if>
<c:if test="${not empty refresh}">
<script language="JavaScript">
  parent.leftFrame.location.reload();
</script>
   <%request.getSession().removeAttribute("refresh");%>
</c:if>

<TABLE class="tableList" align="center">

<tr class="TDListTitle">
   <td width="25%" colspan="4">
<a title="查看大图" target="_blank"
  href='<%=request.getContextPath()%>/wf/preview_big.fm?flowMetaID=<c:out value="${workflowMeta.flowMetaID}"/>'
><img src='<%=request.getContextPath()%>/wf/preview_flow_meta.fm?flowMetaID=<c:out value="${workflowMeta.flowMetaID}"/>'
/></a>
   </td>
   <td width="75%">
     <div align="left">
<fmt:message key="flowMeta.process_id"/><c:out value="${workflowMeta.flowProcessID}"/>
     </div>
     <div align="left">
<fmt:message key="flowMeta.process_name"/><c:out value="${workflowMeta.flowFileInUse.flowMetaName}"/>
     </div>
     <div align="left">
<fmt:message key="flowMeta.create_time"/><c:out value="${workflowMeta.flowFileInUse.createdTime}"/>
     </div>
     <div align="left">
<input type="button" value="更新图片" class="button_default"
onclick="window.location.replace('<%=request.getContextPath()%>/wf/preview_image_form.fm?flowMetaID=<c:out value="${workflowMeta.flowMetaID}"/>');"/>
<input type="button" value='<fmt:message key="bt.changeFlowModule"/>' class="button_default"
onclick="changeFlowModule();"/>
<input type="button" value='删除流程' class="button_default"
onclick="deleteFlow();"/>
     </div>
   </td>
</tr>
</TABLE>
<hr width="95%" align="center" size="1" >
   <TABLE class="tableList" align="center">
        <tr class="TDListTitle">
          <td width="25%"><fmt:message key="deploy.name"/></td>
          <td width="25%"><fmt:message key="deploy.create_time"/></td>
          <td width="40%"><fmt:message key="deploy.memo"/></td>
          <td width="10%"><fmt:message key="deploy.current_state"/></td>
        </tr>

<logic:iterate id="theDeploy" name="flowDeploies" indexId="ide"
  type="org.powerstone.workflow.model.FlowDeploy">
  <tr <%if(ide.intValue()%2==0){%>class="TD3"<%}else{%>class="TD2"<%}%> style="cursor:hand"
    onClick="changeBgColor(this,'<%=theDeploy.getFlowDeployName()%>');"
    id='<%=theDeploy.getFlowDeployID()%>'
    ondblclick="deploy();" title='<fmt:message key="deploy.ondblclick"/>'>
    <td><%=theDeploy.getFlowDeployName()%></td>
    <td><%=theDeploy.getCreateTime()%></td>
    <td><%=theDeploy.getMemo()%></td>
    <td><%=theDeploy.getCurrentState()%></td>
  </tr>
</logic:iterate>
    </TABLE>

  <TABLE class="tableList" align="center">
  <tr>
    <td height="25" colspan="6" align="center">
<input type="button" class="button_default" onclick="newDeploy();" value='<fmt:message key="deploy.newDeploy"/>'>
<input type="button" class="button_default" onclick="editDeploy();" value='编辑'>
<input type="button" class="button_default" onclick="deploy();" value='<fmt:message key="deploy.deploy"/>'>
<input type="button" class="button_default" onclick="setDeadLine();" value='<fmt:message key="deploy.setDeadLine"/>'>
<input type="button" class="button_default" onclick="deleteDeploy();" value='<fmt:message key="deploy.deleteDeploy"/>'>
<input type="button" class="button_default" onclick="enableDeploy();" value='<fmt:message key="deploy.enableDeploy"/>'>
<input type="button" class="button_default" onclick="disableDeploy();" value='<fmt:message key="deploy.disableDeploy"/>'>
<input type="button" class="button_default" onclick="monitor();" value='<fmt:message key="deploy.monitor"/>'>
    </td>
  </tr>
  </TABLE>
          </td>
        </tr>
      </table>

</body>
</html>
