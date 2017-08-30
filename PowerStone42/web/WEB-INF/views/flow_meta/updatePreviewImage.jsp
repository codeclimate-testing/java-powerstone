<%@page  pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<html>
<head>
<title>
updatePreviewImage
</title>
<LINK href="<%=request.getContextPath()%>/img/PowerStone.css" type="text/css" rel="stylesheet">
</head>
<body >
<table class="tableFrame" cellspacing="1">
  <tr>
     <td class="tdTitle">工作流管理&gt;&gt;上传流程预览图片</td>
  </tr>
<td class="tdContent">
  <table class="tableNoBorderCenter"  align="center">
<c:if test="${not empty status.errorMessages}">
    <div class="error">
        <c:forEach var="error" items="${status.errorMessages}">
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
</c:if>

<form method="post" encType="multipart/form-data"
 action='<%=request.getContextPath()%>/wf/update_preview_image.fm?flowMetaID=<c:out value="${flowMetaID}"/>'>
<table class="tableNoBorderCenter" align="center">
  <tr>
   <th><fmt:message key="deploy.preview_img"/></th>
   <td><input type="file" class="input1" name="image"></td>
  </tr>
  <tr>
    <td></td>
    <td>
      <input type="submit" class="button_default" value='<fmt:message key="global.ok"/>'>
      <input type="button" class="button_default" value="Cancel"
onclick="window.location.replace('<%=request.getContextPath()%>/wf/see_flow_meta.fm?flowMetaID=<c:out value="${flowMetaID}"/>');"/>
    </td>
  </tr>
</table>
</form>

  </table>
 </td>
</table>

</body>
</html>
