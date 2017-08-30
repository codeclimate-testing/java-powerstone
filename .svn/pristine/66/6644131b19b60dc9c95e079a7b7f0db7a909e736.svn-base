<%@page  pageEncoding="GBK"  %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<html>
<head>
<title><fmt:message key="driver_main.title"/></title>
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

//
function listDrivers(contextPath){
  document.all.topIframe.src =
     "<%=request.getContextPath()%>/wf/list_drivers.fd?contextPath="+contextPath;
}

-->
</script>
<link href="<%=request.getContextPath()%>/img/PowerStone.css"
  rel="stylesheet" type="text/css">
</head>
<body leftmargin="0" rightmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="100%" height="100%" border="1">
  <tr>
    <td width="20%" valign="top">
	  <table width="100%" border="0">
	     <tr>
          <td height="30">
  	        <b><fmt:message key="driver.catalog"/></b>
		  </td>
        </tr>

<c:if test="${!empty allContextPath}">
  <c:forEach items="${allContextPath}" var="contextPath">

        <tr>
          <td id='<c:out value="${contextPath}"/>'
          style='font-size:12.5px;cursor:hand;'
            onClick="changeColor(this);listDrivers('<c:out value="${contextPath}"/>');">
	        <c:out value="${contextPath}"/>
		  </td>
        </tr>
  </c:forEach>
</c:if>
         <tr>
          <td>
            <img src="<%=request.getContextPath()%>/img/add.gif" width="12" height="12"
          hspace="2" border="0" class="f12">
          <span  style='cursor:hand'
              onclick="window.open('<%=request.getContextPath()%>/wf/edit_driver.fd','','resizable=yes,width=350,height=340,scrollbars=yes');"
              class="f12"><fmt:message key="driver.button_add"/></span>

          </td>
         </tr>
      </table>
	  <br>
    </td>

    <td>
<table border="0" width="100%" height="100%">
  <tr>
    <td height="350">
      <iframe id="topIframe" name="topIframe"
        src="<%=request.getContextPath()%>/wf/list_drivers.fd"
        frameborder="1" height="100%" width="100%" scrolling="yes" marginheight="0" marginwidth="0">
      </iframe>
    </td>
  </tr>
  <tr>
    <td height="100%">        <iframe id="bottomIframe" name="bottomIframe"
      src="<%=request.getContextPath()%>/wf/see_driver_detail.fd" frameborder="1" height="100%"
      width="100%" scrolling="yes" marginheight="0" marginwidth="0">
                </iframe>
    </td>
  </tr>
</table>
</td>
  </tr>
</table>
</body>
</html>
