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
		<H3>导航栏</H3>
		<UL>
        <LI><a >当前用户:<ca:currentUserName></ca:currentUserName></a></LI>
        <LI><a href="<%=request.getContextPath()%>/edit_my_info.ca" target="_blank">个人信息</a> </LI>
        <!-- LI><a href="<%=request.getContextPath()%>/" target="_top">返回主页</a> </LI-->
        <LI><a href="<%=request.getContextPath()%>/priv_manage/refresh.priv" target="_top">刷新缓存</a> </LI>
        <LI><a href="<%=request.getContextPath()%>/j_acegi_logout" target="_top">注 &nbsp; 销</a> </LI>
		</UL>
		</DIV>
    </TD>
  </TR>
  </TBODY>
</TABLE>

<DIV id=divNavBar>
<H3>功能导航栏</H3>
<UL>
  <LI id="emptyLI"><a class="divNavBarANotSelected">&nbsp; </a></LI>
  <LI><A href="#" onclick="MM_CLick(this,'<%=request.getContextPath()%>/user_group/list_groups.ca','1');" class="divNavBarASelected">用户管理</A> </LI>
  <LI><A href="#" onclick="MM_CLick(this,'<%=request.getContextPath()%>/role_manage/list_roles.role','2');" class="divNavBarANotSelected">角色管理</A> </LI>
  <LI><A href="#" onclick="MM_CLick(this,'<%=request.getContextPath()%>/priv_manage/list_roles.priv','3');" class="divNavBarANotSelected">角色授权</A> </LI>
  <LI><A href="#" onclick="MM_CLick(this,'<%=request.getContextPath()%>/resource_manage/list_web_modules.resource','4');" class="divNavBarANotSelected">权限注册</A> </LI>
  <LI><A href="#" onclick="MM_CLick(this,'<%=request.getContextPath()%>/wf/main.bt','5');" class="divNavBarANotSelected">流程管理</A> </LI>
  <LI><A href="#" onclick="newWin('<%=request.getContextPath()%>/wf/home.fd');" class="divNavBarANotSelected">驱动管理</A> </LI>
  <LI><A href="#" onclick="newWin('<%=request.getContextPath()%>/main.wl');" class="divNavBarANotSelected">任务列表</A> </LI>
</UL>
</DIV>

</DIV>
</DIV>
</DIV>
</body>
</html>
