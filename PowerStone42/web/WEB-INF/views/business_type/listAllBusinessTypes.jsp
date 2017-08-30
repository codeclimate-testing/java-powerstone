<%@page  pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<head>
<title>WorkList</title>
<LINK href="<%=request.getContextPath()%>/img/PowerStone.css" type="text/css" rel="stylesheet">
<script language="JavaScript">
<!--
var imgOpen = new Image();
var imgClose = new Image();
var selectID;
var selectName;
imgOpen.src="<%=request.getContextPath()%>/img/plus.gif";
imgClose.src="<%=request.getContextPath()%>/img/close.gif";
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
            if (whichEl.style.display == "none"&&divColl(i).id.indexOf(el)==0) {
                whichEl.style.display = "block";
            }
            else {
            	whichEl.style.display = "none";
            }
        }
    }
}

//选中变色
function changeColor(hrefID){
	divHref = document.all.tags("font");
        for (i=0; i<divHref.length; i++) {
          if(divHref(i).id != "divID"){//其它类型不变色
            divHref(i).parentNode.parentNode.className = "tdTreeNode";
          }
          if(divHref(i).id==hrefID){
             divHref(i).parentNode.parentNode.className = "tdTreeNodeASelected";
             curChannelID = divHref(i).id;
          }
        }
}

//选中变色
function changeSonColor(hrefID,flowMetaID){
  selectID=flowMetaID;
	divHref = document.all.tags("font");
        for (i=0; i<divHref.length; i++) {
          if(divHref(i).id != "divID"){//其它类型不变色
            divHref(i).parentNode.parentNode.className = "tdTreeNode";
          }
          if(divHref(i).id==hrefID){
             divHref(i).parentNode.parentNode.className = "tdTreeNodeASelected";
             curChannelID = divHref(i).id;
          }
        }
parent.parent.document.all.mainFrame.src=
    "<%=request.getContextPath()%>/wf/see_flow_meta.fm?flowMetaID="+flowMetaID;
}
//-->
</script>
</head>

<body >
<table width="100%" height="100%">
  <tr>
    <td valign="top">

<c:forEach var="businessType" items="${allBusinessTypes}" varStatus="ide">
  <div class='tdTreeNode' valign="top" id='<c:out value="${ide.count}"/>' style="display:block;cursor:hand">
    <img align='absmiddle' src="<%=request.getContextPath()%>/img/spacer.gif" border='0'>
<c:if test="${businessType.flowMetasNum > 0}">
    <img align='absmiddle' src="<%=request.getContextPath()%>/img/plus.gif" border='0'
      onClick="expandIt('<c:out value="${ide.count}"/>'); return false" id='<c:out value="${ide.count}"/>'>&nbsp;
</c:if>
<c:if test="${businessType.flowMetasNum==0}">
    <img align=absmiddle src="<%=request.getContextPath()%>/img/blank.gif" border=0>&nbsp;
</c:if>
<a href='<%=request.getContextPath()%>/wf/edit_bt.bt?typeID=<c:out value="${businessType.typeID}"/>'
        target="mainFrame" onclick="changeColor('<c:out value="${ide.count}"/>')">
        <font style='font-weight: bold;' id='<c:out value="${ide.count}"/>'>
             <c:out value="${businessType.typeName}"/>(<c:out value="${businessType.flowMetasNum}"/>)
        </font>
</a>
  </div>

  <c:forEach items="${businessType.workflowMetas}" var="flowMeta" varStatus="idee">
  <div class='tdTreeNode' style="display:none;cursor:hand"
    valign="top" id='<c:out value="${ide.count}"/>-<c:out value="${idee.count}"/>'> &nbsp;
    <img align='absmiddle' src="<%=request.getContextPath()%>/img/spacer.gif" border='0'>
    <img align='absmiddle' src="<%=request.getContextPath()%>/img/blank.gif" border='0'>&nbsp;
      <a onclick="changeSonColor('<c:out value="${ide.count}"/>-<c:out value="${idee.count}"/>','<c:out value="${flowMeta.flowMetaID}"/>')">
        <font id='<c:out value="${ide.count}"/>-<c:out value="${idee.count}"/>'>
          <c:out value="${flowMeta.flowFileInUse.flowMetaName}"/>
        </font>
      </a>
  </div>
  </c:forEach>
</c:forEach>

<!--The others-->
<c:if test="${!empty flowMetasNoBusinessType}">
<div style="display:block;cursor:hand">
    <img align='absmiddle' src="<%=request.getContextPath()%>/img/spacer.gif" border='0'>
    <img align='absmiddle' src="<%=request.getContextPath()%>/img/plus.gif" border='0'
      onClick="expandIt('other-'); return false" id='divID'>&nbsp;
  <font style='font-weight: bold;' id="divID">The others</font>
</div>

  <c:forEach items="${flowMetasNoBusinessType}" var="flowMeta" varStatus="idee">
<div class='tdTreeNode' valign="top" id='other-<c:out value="${idee.count}"/>' style="display:none;cursor:hand"> &nbsp;
    <img align='absmiddle' src="<%=request.getContextPath()%>/img/spacer.gif" border='0'>
    <img align='absmiddle' src="<%=request.getContextPath()%>/img/blank.gif" border='0'>&nbsp;
<a href="#" onclick="changeSonColor('other-<c:out value="${idee.count}"/>','<c:out value="${flowMeta.flowMetaID}"/>')">
        <font id='other-<c:out value="${idee.count}"/>'>
          <c:out value="${flowMeta.flowFileInUse.flowMetaName}"/>
        </font>
</a>
</div>
  </c:forEach>
</c:if>

   </td>
  </tr>
</table>
</body>
</html>
