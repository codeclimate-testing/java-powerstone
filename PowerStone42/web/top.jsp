<%@page  pageEncoding="GBK"  %>
<%@ taglib uri="http://www.powerstone.org/powerstone/ca" prefix="ca" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>PowerStone</title>
<LINK href="<%=request.getContextPath()%>/img/PowerStone.css" type="text/css" rel="stylesheet">

<SCRIPT LANGUAGE="JavaScript">
<!--
function newWin(url){
  window.sonWin=window.open(url,'newWin','resizable=1,width=720,height=540,scrollbars=0');
  window.sonWin.focus();
}

function MM_CLick(obj, str,fun_no){
	for(i=0; i<divNavBar.children[1].children.length; i++){
		divNavBar.children[1].children[i].children[0].className="divNavBarANotSelected";
	}
	obj.className="divNavBarASelected";
        parent.leftFrame.location.replace(str);
    	parent.mainFrame.location.replace("<%=request.getContextPath()%>/construction.jsp?fun_no="+fun_no);
}
-->
</script>

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
    <TD id="tdLogoRight">
		<DIV id=divLogoRightBar>
		<H3>������</H3>
		<UL>
        <LI><a >��ǰ�û�:<ca:currentUserName></ca:currentUserName></a></LI>
        <LI><a href="<%=request.getContextPath()%>/edit_my_info.ca" target="_blank">������Ϣ</a> </LI>
        <!-- LI><a href="<%=request.getContextPath()%>/" target="_top">������ҳ</a> </LI-->
        <LI><a href="<%=request.getContextPath()%>/priv_manage/refresh.priv" target="_top">ˢ�»���</a> </LI>
        <LI><a href="<%=request.getContextPath()%>/j_acegi_logout" target="_top">ע &nbsp; ��</a> </LI>
		</UL>
		</DIV>
    </TD>
  </TR>
  </TBODY>
</TABLE>

<DIV id=divNavBar>
<H3>���ܵ�����</H3>
<UL>
  <LI id="emptyLI"><a class="divNavBarANotSelected">&nbsp; </a></LI>
  <LI><A href="#" onclick="MM_CLick(this,'<%=request.getContextPath()%>/user_group/list_groups.ca','1');" class="divNavBarASelected">�û�����</A> </LI>
  <LI><A href="#" onclick="MM_CLick(this,'<%=request.getContextPath()%>/role_manage/list_roles.role','2');" class="divNavBarANotSelected">��ɫ����</A> </LI>
  <LI><A href="#" onclick="MM_CLick(this,'<%=request.getContextPath()%>/priv_manage/list_roles.priv','3');" class="divNavBarANotSelected">��ɫ��Ȩ</A> </LI>
  <LI><A href="#" onclick="MM_CLick(this,'<%=request.getContextPath()%>/resource_manage/list_web_modules.resource','4');" class="divNavBarANotSelected">Ȩ��ע��</A> </LI>
  <LI><A href="#" onclick="MM_CLick(this,'<%=request.getContextPath()%>/wf/main.bt','5');" class="divNavBarANotSelected">���̹���</A> </LI>
  <LI><A href="#" onclick="newWin('<%=request.getContextPath()%>/wf/home.fd');" class="divNavBarANotSelected">��������</A> </LI>
  <LI><A href="#" onclick="newWin('<%=request.getContextPath()%>/main.wl');" class="divNavBarANotSelected">�����б�</A> </LI>
</UL>
</DIV>

</DIV>
</DIV>
</DIV>
</body>
</html>
