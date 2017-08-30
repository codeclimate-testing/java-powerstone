<%@page  pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%
    response.setHeader("Pragma","No-cache");
    response.setHeader("Cache-Control","no-cache");
    response.setDateHeader("Expires", 0);
%>
<html>
<head>
<title><fmt:message key="bm_main.title"/></title>
<LINK href="<%=request.getContextPath()%>/img/PowerStone.css" type="text/css" rel="stylesheet">
<script language="JavaScript">
<!--
//上传新工作流
function newFlow(){
    parent.document.all.mainFrame.src =
      "<%=request.getContextPath()%>/wf/upload_page.fm";
}

//删除工作流
function deleteFlow(){
  var selectID=treeIFrame.window.selectID;
  var selectName=treeIFrame.window.selectName;
  if(selectID==null||selectID.length==0){
    alert('<fmt:message key="fm.pls_select"/>');
    return;
  }
  if(confirm('<fmt:message key="fm.confirm_delete"/>')){
     parent.document.all.mainFrame.src =
      "<%=request.getContextPath()%>/wf/remove_flow_meta.fm?flowMetaID="+selectID;
  }
}

//新建业务类型
function newModule(){
  parent.document.all.mainFrame.src ="<%=request.getContextPath()%>/wf/edit_bt.bt";
}
-->
</script>
</head>

<body>
<table class="tableFrame" cellspacing="1">
  <tr>
    <td class="tdTitle"><fmt:message key="bt.flows_can_deploy"/></td>
  </tr>
  <tr>
    <td class="tdContent">
      <table class="tableNoBorder">
        <tr>
          <td id="iframeHightID" valign="top" height="400" >
<iframe id="treeIFrame" height="100%" width="100%" border="0" frameborder="0"
  src="<%=request.getContextPath()%>/wf/list_all_bts.bt">
</iframe>
          </td>
        </tr>
        <tr>
          <td valign="top" nowrap>
             <input type="button" class="button_default" value='<fmt:message key="bt.newFlow"/>' onClick="newFlow();">
             <input type="button" class="button_default" value='<fmt:message key="bt.newModule"/>' onClick="newModule();">
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
