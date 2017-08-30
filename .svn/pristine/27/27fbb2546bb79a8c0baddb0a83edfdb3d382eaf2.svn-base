<%@page  pageEncoding="GBK"
 import="org.powerstone.workflow.model.*,
 org.powerstone.workflow.util.*,java.util.*"
%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>nodePerformer</title>
<style type="text/css">
<!--
td {
	font-size: 14px;
}
-->
</style>
<script language="JavaScript" type="text/javascript">
function doChoseItem(flag){
	var objSrc = window.event.srcElement;
        //alert(objSrc.tagName);
	if(!flag){
		if(objSrc.tagName != "INPUT" || objSrc.type.toUpperCase() != "RADIO"){
			return;
		}
	}

	var radios = document.getElementsByName("performerLogic");
	for(var i = 0;i < radios.length ; i++){
	tagObjs = getElementsOfThisTr(radios[i], i==0);

	if(!radios[i].checked){
		for(var j = 0; j < tagObjs.length; j++){
			tagObjs[j].disabled = true;
		}
	}else{
		for(var j = 0; j < tagObjs.length; j++){
			tagObjs[j].disabled = "";
		}
	}
	}

}

function getElementsOfThisTr(raObj, flag){// if first TR ,extends to next Tr flag = true
	var inputs = new Array();
	if(flag){
		//var tmpTR = reObj.parentElement.parentElement.parentElment.parentElement.parentElement.nextSibling;
		var tmpTR = raObj.parentElement.parentElement.nextSibling;
		var tmpArrInput = tmpTR.getElementsByTagName("input");
		for(var i = 0; i < tmpArrInput.length; i++){
			inputs.push(tmpArrInput[i]);
		}
		var tmpArrSelect = tmpTR.getElementsByTagName("select");
		for(var i = 0; i < tmpArrSelect.length; i++){
			inputs.push(tmpArrSelect[i]);
		}
	}else{

		var tmpTR = raObj.parentElement.parentElement;
		var tmpArrInput = tmpTR.getElementsByTagName("input");
		for(var i = 0; i < tmpArrInput.length; i++){
			if(tmpArrInput[i].type.toUpperCase() != "RADIO")
			inputs.push(tmpArrInput[i]);
		}
		var tmpArrSelect = tmpTR.getElementsByTagName("select");
		for(var i = 0; i < tmpArrSelect.length; i++){
			inputs.push(tmpArrSelect[i]);
		}
	}
	return inputs;
}

function delete_user(){
   var targetForm =eval("form1");
   if ("object"==typeof(targetForm)){
     if(targetForm.user_items.value==null||targetForm.user_items.value==""){
       alert('<fmt:message key="performer.pls_select_user_to_delete"/>');
       return false;
     }
     else{
       targetForm.action=
       "<%=request.getContextPath()%>/wf/remove_user_performer.pfm";
       targetForm.submit();
     }
   }
}

function delete_role(){
   var targetForm =eval("form1" );
   if ("object"==typeof(targetForm)){
     if(targetForm.role_items.value==null||targetForm.role_items.value==""){
       alert('<fmt:message key="performer.pls_select_role_to_delete"/>');
       return false;
     }
     else{
       targetForm.action=
       "<%=request.getContextPath()%>/wf/remove_role_performer.pfm";
       targetForm.submit();
     }
   }
}
</script>
</head>
<%FlowNodeBinding nodeBinding =
(FlowNodeBinding)request.getAttribute("nodeBinding");
%>
<body onLoad="doChoseItem(true);">
<table width="100%" border="0" cellpadding="0" cellspacing="2"
  onClick="doChoseItem();" style="border:1px solid #CCCCCC">
  <%if(nodeBinding!=null){%>
  <form name="form1"
    action="<%=request.getContextPath()%>/wf/update_performer_logic.pfm"
    method="POST" >
  <input type="hidden" name="nodeBindingID"
    value='<c:out value="${nodeBinding.nodeBindingID}"/>' />

  <tr>
    <td height="35" bgcolor="#EBEBEB">
      <input name="performerLogic" type="radio" value="static"
        <%if(nodeBinding.isStatic()){%>
        checked="checked"
        <%}%>
      >
      <fmt:message key="performer.orgnize"/></td>
  </tr>
  <tr>
    <td bgcolor="#EBEBEB">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr align="center">
          <td width="50%"><fmt:message key="performer.users"/></td>
          <!--td>角色</td-->
        </tr>
        <tr align="center">
          <td>
            <select name="user_items" style="width:200px" size="3" multiple="multiple">
              <c:forEach items="${allUsers}" var="currUser">
                   <option value='<c:out value="${currUser.id}"/>'>
                   <c:out value="${currUser.userName}"/>|<c:out value="${currUser.realName}"/>
                   </option>
              </c:forEach>
            </select>
          </td>
          <!--td>
            <select name="role_items" style="width:200px" size="3">
              <c:forEach items="${allRoles}" var="currRole">
                 <option value='<c:out value="${currRole.roleID}"/>'>
                   * <c:out value="${currRole.roleName}"/>
                 </option>
              </c:forEach>
            </select>
          </td-->
        </tr>
        <tr align="center">
          <td>
            <input type="button" name="Submit1" value='<fmt:message key="performer.add"/>'
onClick="window.open('<%=request.getContextPath()%>/wf/user_organize.pfm?nodeBindingID=<c:out value="${nodeBinding.nodeBindingID}"/>','','');"> &nbsp;
            <input type="button" name="Submit2" value='<fmt:message key="performer.delete"/>' onclick="delete_user();">
          </td>
          <!--td>
            <input type="button" name="Submit3" value="增加"
onClick="window.open('<%=request.getContextPath()%>/wf/list_roles.pfm?nodeBindingID=<c:out value="${nodeBinding.nodeBindingID}"/>','','');"> &nbsp;
            <input type="button" name="Submit4" value="删除" onclick="delete_role();">
          </td-->
        </tr>
      </table></td>
  </tr>

  <tr>
    <td height="35" bgcolor="#EBEBEB">
      <input type="radio" name="performerLogic" value="founder"
        <%if(nodeBinding.isFounder()){%>
        checked="checked"
        <%}%>
    >
      <fmt:message key="performer.starter"/></td>
  </tr>
  <tr>
    <td height="35" bgcolor="#EBEBEB"> <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="21%">
           <input type="radio" name="performerLogic" value="otherPerformer"
             <%if(nodeBinding.isOtherPerformer()){%>
        	checked="checked"
            <%}%>
          >
            <fmt:message key="performer.reletive_performer"/></td>
          <td><table width="450" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td> <select name="other_performer_detail" style="width:150px;">
                      <option
                      <c:if test="${empty nodeBinding.performerDetail}">
               		  selected
                      </c:if> >
                      <fmt:message key="performer.select_node"/>
                      </option>
                      <c:forEach items="${workflowMeta.allActivityNodesList}" var="currNode">
                        <option value='<c:out value="${currNode.nodeID}"/>'
                        <c:if test="${currNode.nodeID eq nodeBinding.performerDetail}">
               		  selected
              		</c:if> >
                   * <c:out value="${currNode.nodeName}"/>
                        </option>
                      </c:forEach>
                    </select> </td>
                </tr>
              </table></td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td height="35" bgcolor="#EBEBEB"> <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="21%">
            <input type="radio" name="performerLogic" value="assign"
            <%if(nodeBinding.isAssign()){%>
        	checked="checked"
            <%}%>
          >
            <fmt:message key="performer.reletive_performer_assign"/></td>
          <td><table width="450" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td> <select name="assign_detail" style="width:150px;">
                      <option
                      <c:if test="${empty nodeBinding.performerDetail}">
               		  selected
                      </c:if> >
                      <fmt:message key="performer.select_node"/>
                      </option>
                      <c:forEach items="${workflowMeta.allActivityNodesList}"
                        var="currNode">
                        <option value='<c:out value="${currNode.nodeID}"/>'
                        <c:if test="${currNode.nodeID eq nodeBinding.performerDetail}">
               		  selected
              		</c:if>
                              >
                        * <c:out value="${currNode.nodeName}"/>
                        </option>
                        </c:forEach>
                    </select> </td>
                </tr>
              </table></td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td height="35" bgcolor="#EBEBEB"> <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="21%">
            <input type="radio" name="performerLogic" value="variable"
            <%if(nodeBinding.isVariable()){%>
        	checked="checked"
            <%}%>
          >
            <fmt:message key="performer.runtime_get"/></td>
<%
HashMap dataFields=null;
WorkflowMeta workflowMeta=(WorkflowMeta)request.getAttribute("workflowMeta");
if(workflowMeta!=null){
  dataFields=workflowMeta.getDataFields();
}
if(dataFields!=null){
%>
          <td><table width="450" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td> <select name="variable_detail" style="width:150px;">
                    <option
                      <%if(nodeBinding.getPerformerDetail()==null){%>
               		  selected
                      <%}%>>
                      <fmt:message key="performer.select_var"/>
                    </option>
<%for(Iterator it=dataFields.keySet().iterator();it.hasNext();){
  FlowDataField aDataField =(FlowDataField)dataFields.get((String)it.next());
%>
                       <option value='<%=aDataField.getFieldID()%>'
<%if(aDataField.getFieldID().equals(nodeBinding.getPerformerDetail())){%>
               		 selected
<%}%>
 			>
                       * <%=aDataField.getFieldName()%>
                       </option>
<%}%>
                    </select> </td>
                </tr>
              </table>
            </td>
<%}%>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td height="35" bgcolor="#EBEBEB">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="21%"><input type="radio" name="performerLogic" value="rule"
            <%if(nodeBinding.isRule()){%>
        	checked="checked"
            <%}%>
          >
            <fmt:message key="performer.logic"/></td>
          <td><table width="450" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td> <input type="text" style="width:250px;" name="rule_detail"
                     value='<%=nodeBinding.isRule()?nodeBinding.getPerformerDetail():""%>'>
                  </td>
                </tr>
              </table></td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td align="center">&nbsp;</td>
  </tr>
  <tr>
    <td height="40" align="center"><input type="submit" name="Submit5"
      value='<fmt:message key="global.ok"/>'>
      &nbsp; <input type="reset" name="Submit6" value='<fmt:message key="global.reset"/>'></td>
  </tr>
</form>
<%}%>
</table>
</body>
</html>
