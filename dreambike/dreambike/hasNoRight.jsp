<%@page  pageEncoding="GBK"  %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<html>
<head>
<title>
error page
</title>
<LINK href="<%=request.getContextPath()%>/img/PowerStone.css" type="text/css" rel="stylesheet">
</head>

<body >
<table width="100%"  class="TABLE1">
  <tr>
    <td width="407" class="TD1">
      <fmt:message key="error.hasNoRight"/>
    </td>
  </tr>
  <tr>
    <TD  height=23 colspan="2"></TD>
  </tr>
</table>
</body>
</html>
