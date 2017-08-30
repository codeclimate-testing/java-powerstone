<%@page  pageEncoding="GBK"  %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<html>
<head>
<title>
searchUserSon
</title>
<xml id="personData" src="<%=request.getContextPath()%>/generate_person_dict.wl"/>
<script language="javascript">
function tijiao(){
  if(document.form1.person.value==""){
    alert('<fmt:message key="task.require_distribute_to"/>');
    document.form1.person.focus();
    return (false);
  }else{
    document.form1.submit();
  }
}

function searchOrganize(){
  document.all.searchResult.src="<%=request.getContextPath()%>/search_organize.do";
}

function init(){//初始化选择录入框
try{
 var userID = document.all('person');

 userID.xmlSource = personData.xml;
 userID.init = 1;
}catch(e){alert(e.description)}
}
</script>

<style type="text/css">
<!--
-->
.personClass{
	behavior:url(<%=request.getContextPath()%>/htc/choseInput.htc);
}
</style>
</script>
</head>
<body onload="init();">
<form action="<%=request.getContextPath()%>/distribute_task.wl" name="form1">
<tr align="center">
    <td height="25" align="center" class="TD2"  style="border:none; "><fmt:message key="task.persons_to_distribute"/></td>
    <td bgcolor="#FFFFFF">
       <input id='person' indexName='USERID' captionPropty='USERNAME'
         type="text"  class="personClass" vName="userID" value="" />
    </td>
    <td height="25">
    <input type="button" name="b1" value='<fmt:message key="global.ok"/>' onclick="tijiao();"/>
    </td>
    <td height="25">
    <input type="hidden" name="taskID" value='<%=request.getAttribute("taskID")%>' />
<!--
    <input type="button" value="在组织结构中查找人员" onclick="window.searchOrganize();"/>
-->
    </td>
    <td height="25" align="center" class="TD2"
      style="border:none;">&nbsp;<fmt:message key="task.distribute_construct"/>
    </td>
</tr>
</form>

<tr>
    <td>
<iframe width="100%" height="90%" name="searchResult" scrolling="yes"
  src="<%=request.getContextPath()%>/empty.jsp">
</iframe>
    </td>
</tr>
</body>
</html>
