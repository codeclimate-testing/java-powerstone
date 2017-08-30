<%@page  pageEncoding="GBK"  %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%
    response.setHeader("Pragma","No-cache");
    response.setHeader("Cache-Control","no-cache");
    response.setDateHeader("Expires", 0);
%>
<html>
<head>
<title>config</title>
<script language="JavaScript">
<!--
//改变文字颜色
function changeColor(trObj){
  for(i=0; i<tdID.length; i++){
    tdID[i].style.color="#000000";
  }
  trObj.style.color="#0000FF";
}

//工作流节点
function deployNode(nodeID){
  //confirm(nodeID);
  document.all.displayIframe.src =
     '<%=request.getContextPath()%>/wf/see_flow_node_binding.dpl?flowDeployID=<c:out value="${flowDeployID}"/>&flowNodeID='+nodeID;
  document.all.performerIframe.src =
     '<%=request.getContextPath()%>/wf/see_node_performer.pfm?flowDeployID=<c:out value="${flowDeployID}"/>&flowNodeID='+nodeID;
}
-->
</script>
</head>

<body leftmargin="0" rightmargin="0" topmargin="0"  marginwidth="0" marginheight="0">
<table width="100%" height="100%" border="0" cellspacing="1" bgcolor="#EBEBEB">
  <tr>
    <td width="20%" valign="top">
	  <table width="100%" border="0">
	     <tr>
          <td height="30">
  	        <b><fmt:message key="deploy.all_nodes"/></b>
		  </td>
        </tr>

<c:forEach items="${workflowMeta.allActivityNodesList}" var="theNode">
        <tr>
          <td id="tdID" style='font-size:12.5px;cursor:hand;'
onClick="changeColor(this);deployNode('<c:out value="${theNode.nodeID}"/>');">
	<c:out value="${theNode.nodeName}"/>
          </td>
        </tr>
</c:forEach>

      </table>
	  <br>
    </td>
    <td>
		<table border="0" width="100%" height="100%">
		  <tr>
		    <td height="50%">
	<iframe id="displayIframe" name="displayIframe"
src='<%=request.getContextPath()%>/wf/see_flow_node_binding.dpl'
        	frameborder="0" height="100%" width="100%" scrolling="AUTO"
        	marginheight="1" marginwidth="0">
        </iframe>
                    </td>
		  </tr>
		  <tr>
		    <td height="50%">
        <iframe id="performerIframe" name="performerIframe"
src='<%=request.getContextPath()%>/wf/see_node_performer.pfm'
          frameborder="0" height="100%" width="100%" scrolling="AUTO"
          marginheight="0" marginwidth="0">
        </iframe>
                    </td>
		  </tr>
		</table>
	</td>
  </tr>
</table>
</body>
</html>
