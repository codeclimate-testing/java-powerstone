<%@page  pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%
    response.setHeader("Pragma","No-cache");
    response.setHeader("Cache-Control","no-cache");
    response.setDateHeader("Expires", 0);
%>
<html>
<head>
<title>
listBTToJoin
</title>
<LINK href="<%=request.getContextPath()%>/img/PowerStone.css" type="text/css" rel="stylesheet">
<script language="JavaScript">
<!--
var currBTBelangTo=
<c:if test="${!empty flowMeta.businessType}">
  '<c:out value="${flowMeta.businessType.typeID}"/>'
</c:if>
<c:if test="${empty flowMeta.businessType}">
  ''
</c:if>;
var currBTSelect=currBTBelangTo;
//
function submitForm(){
  if(currBTSelect==''){
    alert('<fmt:message key="bt.pls_select_join"/>');
    return;
  }
  if(currBTBelangTo!=(currBTSelect)){
    parent.leftFrame.location.reload();
    form1.submit();
  }else{
    alert('<fmt:message key="bt.no_diffrent"/>');
    return;
  }
}

function cancel(){
  url="<%=request.getContextPath()%>/wf/see_flow_meta.fm?flowMetaID=<c:out value="${flowMeta.flowMetaID}"/>";
  window.location.replace(url);
}


function selectMe(str){
  currBTSelect=str;
}

var oldclassName = null;
var cunObj = null;
function changeColor(tempi){
  if(cunObj != null){
	cunObj.className=oldclassName;
  }
  oldclassName=tempi.className;
  tempi.className="TD4";
  cunObj=tempi;
}
-->
</script>

</head>
<body>
<table class="tableFrame" cellspacing="1">
  <tr>
    <td class="tdTitle">工作流管理&gt;&gt;调整流程业务类型</td>
  </tr>
  <tr>
   <td class="tdContent">
	<br />

<form action="<%=request.getContextPath()%>/wf/update_bt_of_flow.fm" name="form1" method="POST">
<input type="hidden" name="flowMetaID" value='<c:out value="${flowMeta.flowMetaID}"/>'/>

<TABLE class="tableList" align="center">
<c:forEach items="${allBusinessTypes}" var="businessType">
  <tr class="TD2" onclick="changeColor(this)">
    <td>
      <input type="radio" name="btToJoin"
      <c:if test="${!empty flowMeta.businessType}">
        <c:if test="${flowMeta.businessType.typeID == businessType.typeID}">
          checked="checked"
        </c:if>
      </c:if>
      value='<c:out value="${businessType.typeID}"/>'
      onclick="selectMe('<c:out value="${businessType.typeID}"/>')" /></td>
    <td align="left"><c:out value="${businessType.typeName}"/></td>
  </tr>
</c:forEach>

</TABLE>

<TABLE class="tableList" align="center">
  <tr>
    <td height="25" colspan="6" align="center">
<input type="button" class="button_default" onclick="submitForm();"
  value='<fmt:message key="global.ok"/>'>
<input type="button" class="button_default" onclick="cancel();"
  value='Cancel'>
    </td>
  </tr>
</TABLE>

</form>
          </td>
        </tr>
      </table>

</body>
</html>
