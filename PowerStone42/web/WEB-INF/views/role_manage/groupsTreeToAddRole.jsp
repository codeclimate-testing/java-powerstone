<%@page  pageEncoding="GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title></title>
<LINK href="<%=request.getContextPath()%>/img/PowerStone.css" type="text/css" rel="stylesheet">
<script language="JavaScript">
var imgOpen = new Image();
var imgClose = new Image();
var curChannelID = "tiptop-1";
imgOpen.src="<%=request.getContextPath()%>/img/plus.gif";
imgClose.src="<%=request.getContextPath()%>/img/close.gif";

var groupSelected="";

//展开
function expandIt(el) {
	var whichImg = (event) ? event.srcElement : false;

	if(whichImg.src ==imgClose.src){
		whichImg.src = imgOpen.src;
	}else{
		whichImg.src =imgClose.src;
	}
	divColl = document.all.tags("div");
	for (i=0; i<divColl.length; i++) {
		if(divColl(i).id.indexOf(el)==0&&divColl(i).id!=el){
			whichEl = divColl(i);
			if (whichEl.style.display == "none"&&divColl(i).id.indexOf(el)==0&&divColl(i).id.length<el.length+4) {
				whichEl.style.display = "block";
			}
			else {
				whichEl.style.display = "none";
			}
		}
	}

	divImg = document.all.tags("img");
	for (i=0; i<divImg.length; i++) {
		if(divImg(i).id.indexOf(el+'-')==0&&(divImg(i).src == imgOpen.src||divImg(i).src == imgClose.src)){
			divImg(i).src = imgOpen.src;
		}
	}
}

//选中变色
function changeColor(hrefID,groupID){
	groupSelected=groupID;
	divHref = document.all.tags("font");
        for (i=0; i<divHref.length; i++) {
          divHref(i).parentNode.parentNode.className = "tdTreeNode";
          if(divHref(i).id==hrefID){
             divHref(i).parentNode.parentNode.className = "tdTreeNodeASelected";
             curChannelID = divHref(i).id;
          }
        }
        if(!hrefID || hrefID == ""){
          docuemnt.all.divID.className = "tdTreeNodeASelected";
        }
}
</script>
</head>

<body >
<table width="100%" height="100%">
  <tr>
    <td valign="top">

  <logic:present name="groupTree">
                   <logic:iterate id="node" name="groupTree" indexId="index">
                        <bean:write name="node" filter="false"/>
                   </logic:iterate>
  </logic:present>

<div id="divID" onclick=changeColor('','')>
          <img src="<%=request.getContextPath()%>/img/spacer.gif" border=0 >
            <a href="<%=request.getContextPath()%>/role_manage/group_members_to_add_role.role?roleID=<%=request.getAttribute("roleID")%>"
              target="tree-right" >
              <font style='font-weight: bold;'>不属于任何部门的用户</font>
            </a>
</div>
</td>
  </tr>
</table>
</body>
</html>
