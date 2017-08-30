<%@page pageEncoding="GBK"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>list groups</title>
<LINK href="<%=request.getContextPath()%>/img/PowerStone.css" type="text/css" rel="stylesheet">
<script language="JavaScript">
var groupSelected="";
//
function createGroup() {
url="<%=request.getContextPath()%>/user_group/edit_group.ca";
window.open(url,target="mainFrame",'');
}

//
function bornGroup(){
  groupSelected=treeIFrame.groupSelected;
//  alert(treeIFrame.groupSelected);
var url;
if(groupSelected.length>0){
   if(confirm("要为选中的节点创建子部门？")){
url="<%=request.getContextPath()%>/user_group/edit_group.ca?parentGroupID="+groupSelected;
window.open(url,target="mainFrame",'');
   }else{
url="<%=request.getContextPath()%>/user_group/edit_group.ca";
window.open(url,target="mainFrame",'');
   }
}else{
  alert('请选中要为其创建子部门的节点！');
}
}

//删除部门
function removeGroup() {
  groupSelected=treeIFrame.groupSelected;
  if(groupSelected.length>0){
    if(confirm("其子部门将被一同删除，部门内人员不会被删除，是否继续？")){
        url="<%=request.getContextPath()%>/user_group/remove_group.ca?groupID="+groupSelected;
        //alert(url);
        window.location.replace(url);
	parent.mainFrame.location.replace("<%=request.getContextPath()%>/construction.jsp");
    }
  }else{
  	alert('请选中要删除的节点！');
   }
}

//编辑部门
function editGroup() {
  groupSelected=treeIFrame.groupSelected;
if(groupSelected.length>0){
url="<%=request.getContextPath()%>/user_group/edit_group.ca?groupID="+groupSelected;
window.open(url,target="mainFrame",'');
}else{
  	alert('请选中要编辑的部门节点！');
   }
}
</script>
</head>

<body>
<table class="tableFrame" cellspacing="1">
  <tr>
    <td class="tdTitle">组织结构树</td>
  </tr>
  <tr>
    <td class="tdContent">
      <table class="tableNoBorder">
        <tr>
          <td id="iframeHightID" valign="top" height="400" >
            <iframe id="treeIFrame" height="100%" width="100%" border="0" frameborder="0"
             src="<%=request.getContextPath()%>/user_group/list_groups_tree.ca">
            </iframe>
          </td>
        </tr>
        <tr>
          <td valign="top" nowrap>
       <input name="cancle" type="button" class="button_default" value="新建" onclick="createGroup()">
       <input name="cancle" type="button" class="button_default" value="新建子部门" onclick="bornGroup()">
       <input name="cancle" type="button" class="button_default" value="修改" onclick="editGroup()">
       <input name="cancle" type="button" class="button_default" value="删除" onclick="removeGroup()">
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
