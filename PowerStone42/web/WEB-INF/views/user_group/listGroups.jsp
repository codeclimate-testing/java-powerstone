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
   if(confirm("ҪΪѡ�еĽڵ㴴���Ӳ��ţ�")){
url="<%=request.getContextPath()%>/user_group/edit_group.ca?parentGroupID="+groupSelected;
window.open(url,target="mainFrame",'');
   }else{
url="<%=request.getContextPath()%>/user_group/edit_group.ca";
window.open(url,target="mainFrame",'');
   }
}else{
  alert('��ѡ��ҪΪ�䴴���Ӳ��ŵĽڵ㣡');
}
}

//ɾ������
function removeGroup() {
  groupSelected=treeIFrame.groupSelected;
  if(groupSelected.length>0){
    if(confirm("���Ӳ��Ž���һͬɾ������������Ա���ᱻɾ�����Ƿ������")){
        url="<%=request.getContextPath()%>/user_group/remove_group.ca?groupID="+groupSelected;
        //alert(url);
        window.location.replace(url);
	parent.mainFrame.location.replace("<%=request.getContextPath()%>/construction.jsp");
    }
  }else{
  	alert('��ѡ��Ҫɾ���Ľڵ㣡');
   }
}

//�༭����
function editGroup() {
  groupSelected=treeIFrame.groupSelected;
if(groupSelected.length>0){
url="<%=request.getContextPath()%>/user_group/edit_group.ca?groupID="+groupSelected;
window.open(url,target="mainFrame",'');
}else{
  	alert('��ѡ��Ҫ�༭�Ĳ��Žڵ㣡');
   }
}
</script>
</head>

<body>
<table class="tableFrame" cellspacing="1">
  <tr>
    <td class="tdTitle">��֯�ṹ��</td>
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
       <input name="cancle" type="button" class="button_default" value="�½�" onclick="createGroup()">
       <input name="cancle" type="button" class="button_default" value="�½��Ӳ���" onclick="bornGroup()">
       <input name="cancle" type="button" class="button_default" value="�޸�" onclick="editGroup()">
       <input name="cancle" type="button" class="button_default" value="ɾ��" onclick="removeGroup()">
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
