<%@ page contentType="text/html;charset=GBK" language="java"%>
<html>
<head>
<title>用户查询</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<script language="JavaScript" type="text/JavaScript">
function strutsQuery(){
form1.action='<%=request.getContextPath()%>/user_query.do';
form1.submit();
}

</script>
</head>
<body bgcolor="#ffffff" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<form name="form1" action="<%=request.getContextPath()%>/user_query.html" method="POST">
<br>
<table width="500" border="0" align="center" cellpadding="0" cellspacing="0" class="table">
  <tr class="table-title"> 
    <td> <div align="center"><b>用户查询</b></div></td>
  </tr>
  <tr> 
    <td> 
      <table width="100%" border="0" cellspacing="1" cellpadding="1" align=center class="font_9">
	<tr >
          <td bordercolor="#F4F9FF"><div align="right">firstName like:</div></td>
          <td colspan="7" bordercolor="#F4F9FF" class="tr-s"><font style="font-size: 9pt"> 
            <input name="firstName" type="text" class="text" size="25">
            </font></td>
        </tr>
      </table>
    </td>
  </tr>
  <tr> 
    <td height="25" align="center" class="table-title" >
      <input name="button" type="submit" class="button" value="查询[SpringMVC]">
      <input name="button" type="button" onclick="strutsQuery();" class="button" value="查询[Struts]">
    </td>
  </tr>
</table>
</form>
</body>
</html>