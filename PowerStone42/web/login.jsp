<%@page  pageEncoding="GBK"  %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>��¼PowerStoneϵͳ</title>
<LINK href="<%=request.getContextPath()%>/img/PowerStone.css" type="text/css" rel=stylesheet>
</head>
<body>
<DIV id=divAll>
<DIV id=divPage>
<DIV id=divMiddle>

<TABLE id="tableLogo">
  <TBODY>
  <TR>
	<TD id="logoImg" align=center>&nbsp;</TD>
	<TD>&nbsp;</TD>
  </TR>
  </TBODY>
</TABLE>
<div style="background-color: black;width:100%;">&nbsp;
</div>
<TABLE border="0" cellspacing="0" cellpadding="5" align="left" width ="100%">
    <TR>
      <TD width="150" nowrap>&nbsp;&nbsp;&nbsp;</TD>
      <TD valign="top" align="left" nowrap>
          <BR>
        <BR>
        <span class="welcome-pageheader">PowerStone�������̨</span><BR>
<form action="<%=request.getContextPath()%>/j_acegi_security_check" method="POST">
<table class="tableNoBorder">
<tr>
   <td align="right">�û���:</td>
   <td align="left">
    <input type="text" class="inputLogin" name="j_username" />
    </td>
</tr>
<tr>
   <td align="right">��&nbsp;&nbsp;��:</td>
   <td align="left">
    <input type="password" class="inputLogin" name="j_password" />
    </td>
</tr>
<tr>
   <td align="right">&nbsp;&nbsp;</td>
   <td align="right" >
   <input type="checkbox" name="_acegi_security_remember_me">��ס��
    <input type="submit" class="button_default" value="�� ¼"/>
    </td>
</tr>
</table>
</form>
      </TD>
      <TD >&nbsp;</TD>
    </TR>
</TABLE>

</DIV>
</DIV>
</DIV>
</body>
</html>
