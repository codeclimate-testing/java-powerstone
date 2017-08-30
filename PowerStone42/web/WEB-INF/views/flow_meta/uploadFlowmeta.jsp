<%@page  pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<html>
<head>
<title>Upload Workflow</title>
<LINK href="<%=request.getContextPath()%>/img/PowerStone.css" type="text/css" rel="stylesheet">
</head>
<body >
<table class="tableFrame" cellspacing="1">
  <tr>
     <td class="tdTitle">工作流管理&gt;&gt;上传工作流</td>
  </tr>
<td class="tdContent">
  <table class="tableNoBorderCenter"  align="center">
<c:if test="${not empty message}">
    <div class="error">
         <c:out value="${message}" escapeXml="false"/><br />
    </div>
</c:if>
<form action="<%=request.getContextPath()%>/wf/upload_flow_file.fm"
  method="post" encType="multipart/form-data">
  <tr>
    <th><fmt:message key="deploy.upload_file"/></th>
    <td><input type="file" class="input1" name="flow"></td>
  </tr>
  <tr>
    <th><fmt:message key="deploy.preview_img"/></th>
    <td><input type="file" class="input1" name="image"><br>
  </tr>
  <tr>
    <td></td>
    <td>
      <input type="submit" class="button_default" value='<fmt:message key="global.ok"/>'>
      <input type="button" class="button_default" value="Cancel" onclick="history.back(-1)"/>
    </td>
  </tr>
</table>
</form>

  </table>
 </td>
</table>

</body>
</html>
