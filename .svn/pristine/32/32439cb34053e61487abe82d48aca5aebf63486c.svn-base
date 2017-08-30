<%@page pageEncoding="GBK"
import="org.powerstone.workflow.model.*,org.powerstone.workflow.util.*,java.util.*"
%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
  <%
    response.setHeader("Pragma","No-cache");
    response.setHeader("Cache-Control","no-cache");
    response.setDateHeader("Expires", 0);

    WorkflowNode workflowNode=
         (WorkflowNode)request.getAttribute("flowNode");

    FlowNodeBinding flowNodeBinding=
    (FlowNodeBinding)request.getAttribute("flowNodeBinding");

    FlowDeploy flowDeploy=
    (FlowDeploy)request.getAttribute("flowDeploy");

    HashMap dataFields=(HashMap)request.getAttribute("dataFields");
  %>

<html>
<head>
<title>seeFlowNodeBinding</title>
<script language="JavaScript">
<!--
//选择表单
function SelectForm(formValue){
  window.location.replace(
   "<%=request.getContextPath()%>/wf/home_to_deploy.fd"
   +"?flowDeployID=<%=flowDeploy.getFlowDeployID()%>"
   +"&flowNodeID=<%=workflowNode.getNodeID()%>&currentdriverID="+formValue
  );
}

function changeOutParamBinding(selectComp){
  var driverIDMatrix=Object();
  var driverNameMatrix=Object();

  <%if(flowNodeBinding!=null&&flowNodeBinding.getWorkflowDriver()!=null
       ){
    WorkflowDriver wfDriver=flowNodeBinding.getWorkflowDriver();

    int driverMatrix_Index=-1;
     for(Iterator it=wfDriver.getWfDriverOutputParams().iterator();
          it.hasNext();){
            WFDriverOutputParam driverOutputParam=
            (WFDriverOutputParam)it.next();

         if(driverOutputParam.getDriverOutputParamEnumes().size()>0){
           driverMatrix_Index++;
  %>
      driverIDMatrix[<%=driverMatrix_Index%>]=
      ["<%=driverOutputParam.getDriverOutputParamID()%>"
  <%
            for(Iterator itt=driverOutputParam.getDriverOutputParamEnumes().iterator();
                itt.hasNext();){
              WFDriverOutputParamEnume driverOutputParamEnume=
                  (WFDriverOutputParamEnume)itt.next();
  %>
      ,"<%=driverOutputParamEnume.getDriverOutputParamEnumeID()%>"
  <%
            }
  %>
      ];
      driverNameMatrix[<%=driverMatrix_Index%>]=
      ["<%=driverOutputParam.getDriverOutputParamID()%>"
  <%
            for(Iterator itt=driverOutputParam.getDriverOutputParamEnumes().iterator();
                itt.hasNext();){
              WFDriverOutputParamEnume driverOutputParamEnume=
                  (WFDriverOutputParamEnume)itt.next();
  %>
      ,"<%=driverOutputParamEnume.getDriverOutputParamEnumeValue()%>"
  <%
            }
  %>
      ];
  <%
         }
      }
  %>
    driverIDMatrix.length=<%=driverMatrix_Index+1%>;
    driverNameMatrix.length=<%=driverMatrix_Index+1%>;
  <%
    }
  %>
  //alert(selectComp.value);
  for(i=0;i<document.all.length;i++){
    if(document.all[i].name!=null
       &&document.all[i].name.indexOf(selectComp.name+'_ENUME')>=0
       ){
      strHtml="<select name="+document.all[i].name+" size=1 >"+
              "<option selected value=''><fmt:message key="deploy.select_param"/></option>";

       for(kk=0;kk<driverIDMatrix.length;kk++){

         if(driverIDMatrix[kk][0]==selectComp.value){
           for(g=1;g<driverIDMatrix[kk].length;g++){
             strHtml+=("<option value="+driverIDMatrix[kk][g]+">"
                       +driverNameMatrix[kk][g]+"</option>");
           }
           break;
         }
       }
          strHtml+="</select>";

       document.all[i].outerHTML=strHtml;
    }
  }
}

function enableFounder(){
  document.assignForm.action=
         "<%=request.getContextPath()%>/wf/enable_founder.dpl";
  document.assignForm.submit();
}

function enableOtherPerformer(){
  document.assignForm.action=
         "<%=request.getContextPath()%>/wf/enable_other_performer.dpl";
  document.assignForm.submit();
}

function enableAssign(){
  document.assignForm.action=
         "<%=request.getContextPath()%>/wf/enable_assign.dpl";
  document.assignForm.submit();
}

function enableVariable(){
  document.assignForm.action=
         "<%=request.getContextPath()%>/wf/enable_variable.dpl";
  document.assignForm.submit();
}

function enableRule(){
  document.assignForm.action=
         "<%=request.getContextPath()%>/wf/enable_rule.dpl";
  document.assignForm.submit();
}

function enableStatic(){
  document.assignForm.action=
         "<%=request.getContextPath()%>/wf/enable_static.dpl";
  document.assignForm.submit();
}

function updateOtherPerformer(){
  document.assignForm.action=
         "<%=request.getContextPath()%>/wf/update_other_performer.dpl";
  document.assignForm.submit();
}

function updateAssign(){
  document.assignForm.action=
         "<%=request.getContextPath()%>/wf/update_assign.dpl";
  document.assignForm.submit();
}

function updateVariable(){
  document.assignForm.action=
         "<%=request.getContextPath()%>/wf/update_variable.dpl";
  document.assignForm.submit();
}

function updateRule(){
  document.assignForm.action=
         "<%=request.getContextPath()%>/wf/update_rule.dpl";
  document.assignForm.submit();
}
-->
</script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<form action="<%=request.getContextPath()%>/wf/update_flow_node_param.dpl"
  method="POST">

  <input type="hidden" name="flowDeployID"
          value="<%=flowDeploy.getFlowDeployID()%>">
  <input type="hidden" name="flowNodeID"
          value="<%=workflowNode.getNodeID()%>">

  <input type="hidden" name="flowNodeBindingID"
  value='<%=flowNodeBinding!=null?flowNodeBinding.getNodeBindingID().toString():""%>'
  >


<table width="100%" border="0" align="center">
  <tr>
    <td align="right"><fmt:message key="deploy.select_driver"/></td>
	<td align="left">
        <input type="text"
value="<%=flowNodeBinding!=null
          ?flowNodeBinding.getWorkflowDriver().getFlowDriverName()
          :""
       %>"
          readonly="true" />
        <input type="hidden" name="mainForm"
value="<%=flowNodeBinding!=null
             ?flowNodeBinding.getWorkflowDriver().getFlowDriverID().toString()
             :""
       %>">
		<input type="button" name="selectForm" value='<fmt:message key="deploy.select_button"/>' onClick="SelectForm(mainForm.value)">
		<font color="#FF0000">*</font>
        </td>
  </tr>
  <br/>
  <%  if(workflowNode!=null){
        String[]variablesIN=workflowNode.getVariableToProcessIN();
        if(variablesIN!=null&&dataFields!=null){
  %>
  <tr><td align="center"><fmt:message key="deploy.input_param"/></td>
    <td>&nbsp;</td>&nbsp;<td></td></tr>
  <%
        for(int i=0;i<variablesIN.length;i++){
          String VariableID=variablesIN[i];
          FlowDataField dataField=(FlowDataField)dataFields.get(VariableID);
          if(dataField!=null){
  %>
     <tr>
        <td align="right">
          <%=dataField.getFieldName()!=null&&
             !dataField.getFieldName().trim().equals("")
             ?dataField.getFieldName()
             :dataField.getFieldID()%>：
        </td>
        <td align="left">
	  <select name="IN_<%=dataField.getFieldID()%>" size="1">
            <option selected value=""><fmt:message key="deploy.select_param"/></option>
  <%if(flowNodeBinding!=null&&
       flowNodeBinding.getWorkflowDriver()!=null){
         WorkflowDriver wfDriver=flowNodeBinding.getWorkflowDriver();
        for(Iterator it=wfDriver.getWfDriverInputParams().iterator();it.hasNext();){
            WFDriverInputParam driverInputParam=(WFDriverInputParam)it.next();
  %>
    	        <option value="<%=driverInputParam.getDriverInputParamID()%>"
  <%
            if(driverInputParam.equals(
            flowNodeBinding.findDriverInputParamByNodeParamID(dataField.getFieldID()))){
  %>
                       selected
  <%        }
  %>
                ><%=driverInputParam.getParamAlias()%></option>
  <%
        }
    }
  %>
          </select>
        </td>
      </tr>
  <%
                 }
            }
    }
  %>
<br/>

  <%  String[]variablesOUT=workflowNode.getVariableToProcessOUT();
      if(variablesOUT!=null){
  %>
      <tr><td align="center"><fmt:message key="deploy.output_param"/>(<%=variablesOUT.length%>)</td>
        <td>&nbsp;</td>&nbsp;<td></td></tr>
  <%
        for(int i=0;i<variablesOUT.length;i++){
          String VariableID=variablesOUT[i];
          FlowDataField dataField=(FlowDataField)dataFields.get(VariableID);

          if(dataField!=null){
  %>
     <tr>
        <td align="right">
          <%=dataField.getFieldName()!=null&&
             !dataField.getFieldName().trim().equals("")
             ?dataField.getFieldName()
             :dataField.getFieldID()%>：
        </td>
	<td align="left">
	  <select name="OUT_<%=dataField.getFieldID()%>" size="1"
           onchange="changeOutParamBinding(this)" >
            <option selected value=""><fmt:message key="deploy.select_param"/></option>
  <%if(flowNodeBinding!=null&&
       flowNodeBinding.getWorkflowDriver()!=null){
         WorkflowDriver wfDriver=flowNodeBinding.getWorkflowDriver();
        for(Iterator it=wfDriver.getWfDriverOutputParams().iterator();it.hasNext();){
            WFDriverOutputParam driverOutputParam=(WFDriverOutputParam)it.next();
  %>
    	        <option value="<%=driverOutputParam.getDriverOutputParamID()%>"
  <%
            if(driverOutputParam.equals(
                flowNodeBinding.findDriverOutputParamByNodeParamID(dataField.getFieldID())
                                      )
              ){
  %>
                       selected
  <%        }
  %>
                 ><%=driverOutputParam.getParamAlias()%></option>
  <%
        }
    }
  %>
         </select>
       </td>
  </tr>

  <%/////////////////参数枚举值绑定
      String[]paramEnumes=dataField.getValueEnumerations();
      if(paramEnumes!=null&&paramEnumes.length>0){
        for(int j=0;j<paramEnumes.length;j++){
          String paramEnume=paramEnumes[j];
          /////////////////////////////
  %>
    <tr>
        <td align="right">
          |---<%=paramEnume%>：
        </td>
	<td align="left">
        <select name="OUT_<%=dataField.getFieldID()%>_ENUME_<%=paramEnume%>" size="1">
          <option selected value=""><fmt:message key="deploy.select_param_enume"/></option>

  <%
  if(flowNodeBinding!=null&&
       flowNodeBinding.findNodeOutputParamBindingByNodeParamID(dataField.getFieldID())!=null
       &&flowNodeBinding.findDriverOutputParamByNodeParamID(dataField.getFieldID())!=null){
         FlowNodeOutputParamBinding nodeOutputParamBinding=
            flowNodeBinding.findNodeOutputParamBindingByNodeParamID(dataField.getFieldID());
        WFDriverOutputParam driverOutputParam =
            flowNodeBinding.findDriverOutputParamByNodeParamID(dataField.getFieldID());
        for(Iterator it2=driverOutputParam.getDriverOutputParamEnumes().iterator();
            it2.hasNext();){
            WFDriverOutputParamEnume driverOutputParamEnume=
                 (WFDriverOutputParamEnume)it2.next();
  %>
        <option value="<%=driverOutputParamEnume.getDriverOutputParamEnumeID()%>"
  <%///////////////////////////
      if(driverOutputParamEnume.equals(
          nodeOutputParamBinding.findDriverOutputParamEnumeByNodeParamEnume(paramEnume))){
  %>
         selected
  <%
      }
  %>
         >
          <%=driverOutputParamEnume.getDriverOutputParamEnumeValue()%>
        </option>
  <%
         }
       }
  %>
        </select>
        </td>
  </tr>

  <%    }
      }
            }
        }
    }
   }
  %>
  <tr>
    <td colspan="2" align="center">
        <input type="submit" value='<fmt:message key="global.ok"/>' >

        <input type="reset" name="cancle" value='<fmt:message key="global.reset"/>'>
    </td>
  </tr>
</table>
</form>
</body>
</html>
