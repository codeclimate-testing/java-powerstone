<%@page  pageEncoding="GBK"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>list roles</title>
<LINK href="<%=request.getContextPath()%>/img/PowerStone.css" type="text/css" rel="stylesheet">
<script language="JavaScript">
var roleSelected="";
//新建
function createRole() {
url="<%=request.getContextPath()%>/role_manage/edit_role.role";
window.open(url,target="mainFrame",'');
}

//删除
function removeRole() {
  roleSelected=treeIFrame.roleSelected;
  if(roleSelected.length>0){
    if(confirm("要删除选中的角色吗？")){
        url="<%=request.getContextPath()%>/role_manage/remove_role.role?roleID="+roleSelected;
        window.location.replace(url);
	parent.mainFrame.location.replace("<%=request.getContextPath()%>/construction.jsp");
    }
  }else{
  	alert('请选中要删除的节点！');
   }
}

//修改
function editRole() {
  roleSelected=treeIFrame.roleSelected;
if(roleSelected.length>0){
url="<%=request.getContextPath()%>/role_manage/edit_role.role?roleID="+roleSelected;
window.open(url,target="mainFrame",'');
}else{
  	alert('请选中要修改的角色节点！');
   }
}
</script>
</head>

<body>
<table class="tableFrame" cellspacing="1">
  <tr>
    <td class="tdTitle">角色列表</td>
  </tr>
  <tr>
    <td class="tdContent">
      <table class="tableNoBorder">
        <tr>
          <td id="iframeHightID" valign="top" height="400" >
            <iframe id="treeIFrame" height="100%" width="100%" border="0" frameborder="0"
             src="<%=request.getContextPath()%>/role_manage/list_roles_tree.role">
            </iframe>
          </td>
        </tr>
        <tr>
          <td valign="top" nowrap>
       <input name="cancle" type="button" class="button_default" value="新建" onclick="createRole()">
       <input name="cancle" type="button" class="button_default" value="修改" onclick="editRole()">
       <input name="cancle" type="button" class="button_default" value="删除" onclick="removeRole()">
          </td>
        </tr>
      </table>
      </td>
  </tr>
</table>

</body>
</html>
<script>
iframeHightID.height=document.body.offsetHeight-60;
</script>
