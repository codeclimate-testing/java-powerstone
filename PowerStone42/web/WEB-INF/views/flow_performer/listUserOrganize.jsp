<%@page  pageEncoding="GBK"  %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%
    response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
%>

<script language="JavaScript">
var imgOpen = new Image();
var imgClose = new Image();
var curChannelID = "tiptop-1";
imgOpen.src="<%=request.getContextPath()%>/img/plus.gif";
imgClose.src="<%=request.getContextPath()%>/img/close.gif";

var groupSelected="";

function expandIt(el) {

   var whichImg = (event) ? event.srcElement : false;

    if(whichImg.src ==imgClose.src){
       whichImg.src = imgOpen.src;
    }else{
        whichImg.src =imgClose.src;
    }
    divColl = document.all.tags("table");

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

function changeColor(hrefID,groupID){
    groupSelected=groupID;

    divHref = document.all.tags("font");
    for (i=0; i<divHref.length; i++) {
        divHref(i).size = "";
        divHref(i).color = "#000000";
        if(divHref(i).id==hrefID){
            divHref(i).size = "3";
            divHref(i).color = "red";
            curChannelID = divHref(i).id;
        }
    }
}
</script>
<html>
<link href="<%=request.getContextPath()%>/img/PowerStone.css" rel="stylesheet" type="text/css">
<body leftmargin="0" topmargin="0"  bgcolor="#d6d6d6">
<table width="100%" border="0" cellpadding="0" cellspacing="0"  id=OuttermostContentBodyTable>

<table valign="top" width="100%" border=0 cellpadding=0 cellspacing=0
                  class="LayoutZonesContainer">
<font class="ldh-12-20"><b><fmt:message key="performer.group_list"/></b></font>
            <tr>
              <td >
               <table width="100%" height="415" border=0 cellpadding=2 cellspacing=4
                        class=LayoutZones>
                  <tbody>
                    <tr>
                      <td class=LayoutZoneCenter id=tdZoneCenter  valign="top">

<logic:present name="groupTree">
    <logic:iterate id="node" name="groupTree" indexId="index">
      <bean:write name="node" filter="false"/>
    </logic:iterate>
</logic:present>

<table cellpadding=0 cellspacing=1 class=LayoutWebPartFrame
  id=0-tiptop-1-fengyaohui style="display:block">
  <tbody>
    <tr> <td>&nbsp;&nbsp;&nbsp;&nbsp;
    </td> </tr>
  </tbody>
</table>
 &nbsp; </td>
   </tr>
      </tbody>
                </table>

         </td>
        </tr>

    </tbody>
</table>
            </div>
          </td>
        </tr>
</table>
</body>
</html>
