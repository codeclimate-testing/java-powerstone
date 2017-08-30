<%@page  pageEncoding="GBK"  %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<html>
<head>
<title>listDriversToBind</title>
<script language="JavaScript">
<!--
//改变文字颜色
function changeColor(trObj){
  divTD = document.all.tags("td");
  for(i=0; i<divTD.length; i++){
    divTD[i].style.color="#000000";
  }
  trObj.style.color="#0000FF";
}

<%
String flowDeployID =(String) request.getAttribute("flowDeployID");
String flowNodeID = (String)request.getAttribute("flowNodeID");
String currentdriverID =(String) request.getAttribute("currentdriverID");
%>
function listDrivers(contextPath){
  document.all.topIframe.src =
     "<%=request.getContextPath()%>/wf/list_drivers_to_deploy.fd?currentdriverID=<%=currentdriverID%>&flowDeployID=<%=flowDeployID%>&flowNodeID=<%=flowNodeID%>&contextPath="+contextPath;
}

-->
</script>
<link href="<%=request.getContextPath()%>/img/PowerStone.css"
  rel="stylesheet" type="text/css">
</head>
<body leftmargin="0" rightmargin="0" topmargin="0" marginwidth="0"
  marginheight="0">
<table width="100%" height="100%" border="1">
  <tr>
    <td width="20%" valign="top">
	  <table width="100%" border="0">
	     <tr>
          <td height="30">
  	        <b><fmt:message key="driver.catalog"/></b>
		  </td>
        </tr>
        <c:forEach items="${allContextPath}" var="contextPath">
        <tr>
          <td id='<c:out value="${contextPath}"/>'
          style='font-size:12.5px;cursor:hand;'
            onClick="changeColor(this);listDrivers('<c:out value="${contextPath}"/>');">
	        <c:out value="${contextPath}"/>
		  </td>
        </tr>
        </c:forEach>
      </table>
	  <br>
    </td>
    <td>
<table border="0" width="100%" height="100%">
  <tr>
    <td>
      <iframe id="topIframe" name="topIframe"
        src="<%=request.getContextPath()%>/wf/list_drivers_to_deploy.fd?currentdriverID=<%=currentdriverID%>&flowDeployID=<%=flowDeployID%>&flowNodeID=<%=flowNodeID%>"
        frameborder="0" height="100%" width="100%" scrolling="yes" marginheight="0" marginwidth="0">
      </iframe>
    </td>
  </tr>
</table>
</td>
  </tr>
</table>
</body>
</html>
