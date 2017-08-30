<%@page pageEncoding="GBK"%>
<%@ taglib uri="http://www.powerstone.org/powerstone/ca" prefix="ca"%>
<html>
<head>
<title>
index
</title>
<LINK href="<%=request.getContextPath()%>/img/PowerStone.css" type="text/css" rel=stylesheet>
</head>
<body bgcolor="#ffffff">
<ca:online>
<div>
  Welcome,<span class="welcome-pageheader"><ca:currentRealName></ca:currentRealName></span>!
</div>
</ca:online>

<TABLE border="0" cellspacing="0" cellpadding="5" align="left" width ="100%">
    <TR>
      <TD width="150" nowrap>&nbsp;&nbsp;&nbsp;</TD>

<ca:notOnline>
      <TD valign="top" align="left" nowrap>
          <BR>
        <BR>
        <span class="welcome-pageheader">PowerStone管理控制台</span><BR>
<form action="<%=request.getContextPath()%>/login.do" method="POST">
<table class="tableNoBorder">
<tr>
   <td align="right">用户名:</td>
   <td align="left">
    <input type="text" class="inputLogin" name="userID" />
    </td>
</tr>
<tr>
   <td align="right">密&nbsp;&nbsp;码:</td>
   <td align="left">
    <input type="password" class="inputLogin" name="pass" />
    </td>
</tr>
<tr>
   <td align="right">&nbsp;&nbsp;</td>
   <td align="right" >
    <input type="submit" class="button_default" value="登 录"/>
    </td>
</tr>
</table>
</form>
      </TD>
</ca:notOnline>
<TD >&nbsp;</TD>
</TR>

<TR >
<TD >&nbsp;</TD>
<TD>
<a href="<%=request.getContextPath()%>/dreambike/edit_order.do">填写订单</a>
</TD><TD >&nbsp;</TD>
</TR>

</TABLE>
</body>
</html>
