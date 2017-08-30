<%@page  pageEncoding="GBK"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>List WebModules</title>
<LINK href="<%=request.getContextPath()%>/img/PowerStone.css" type="text/css" rel="stylesheet">
<script language="JavaScript">
var webModuleSelected="";
//新建
function create() {
url="<%=request.getContextPath()%>/resource_manage/edit_web_module.resource";
window.open(url,target="mainFrame",'');
}

//删除
function remove() {
  webModuleSelected=treeIFrame.webModuleSelected;
  if(webModuleSelected.length>0){
    if(confirm("要删除选中的模块吗？")){
url="<%=request.getContextPath()%>/resource_manage/remove_web_module.resource?webModuleID="+webModuleSelected;
        window.location.replace(url);
	parent.mainFrame.location.replace("<%=request.getContextPath()%>/construction.jsp");
    }
  }else{
  	alert('请选中要删除的WEB模块！');
   }
}

//修改
function edit() {
  webModuleSelected=treeIFrame.webModuleSelected;
if(webModuleSelected.length>0){
url="<%=request.getContextPath()%>/resource_manage/edit_web_module.resource?webModuleID="+webModuleSelected;
window.open(url,target="mainFrame",'');
}else{
  	alert('请选中要修改的WEB模块！');
   }
}
</script>
</head>

<body>
<table class="tableFrame" cellspacing="1">
  <tr>
    <td class="tdTitle">WEB模块列表</td>
  </tr>
  <tr>
    <td class="tdContent">
      <table class="tableNoBorder">
       <tr>
          <td id="iframeHightID" valign="top" height="400" >
           <iframe id="treeIFrame" height="100%" width="100%" border="0" frameborder="0"
             src="<%=request.getContextPath()%>/resource_manage/list_web_modules_tree.resource">
           </iframe>
           </td>
        </tr>
        <tr>
          <td valign="top" nowrap>
            <input name="cancle" type="button" class="button_default" value="新建" onclick="create()">
            <input name="cancle" type="button" class="button_default" value="修改" onclick="edit()">
            <input name="cancle" type="button" class="button_default" value="删除" onclick="remove()">
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
