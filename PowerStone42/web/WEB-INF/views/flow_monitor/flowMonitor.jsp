<%@page  pageEncoding="GBK"  %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<html>
<head>
<title>Flow Monitor</title>
<script language="JavaScript">
<!--
//显示进程详细信息
function showProcDetail(flowProcID){
  if(flowProcID!=undefined){
      url="<%=request.getContextPath()%>/wf/flow_detail.moni?flowProcID="
          +flowProcID.id;
//      alert(url);
      window.open(url,'','resizable=1,width=720,height=540,scrollbars=1');
  }else{
    alert('<fmt:message key="monitor.pls_select_proc"/>');
  }
}

//换背景色
var oldclassName = null;
var cunObj = null;
function changeBgColor(tempi){
    if(cunObj != null){
	cunObj.className=oldclassName;
	}
	oldclassName=tempi.className;
    tempi.className="TD4";
	cunObj=tempi;
}
-->
</script>
<LINK href="<%=request.getContextPath()%>/img/PowerStone.css" type="text/css" rel="stylesheet">
</head>
<body>
<table class="tableFrame" cellspacing="1">
  <tr>
    <td class="tdTitle">流程监控&gt;&gt;此流程处于活动状态的进程列表</td>
  </tr>

  <tr>
   <td class="tdContent">
	<br />

<TABLE class="tableList" align="center">
  <tr class="TDListTitle">
    <td width="65%"><fmt:message key="monitor.proc_state"/></td>
    <td width="20%"><fmt:message key="monitor.start_time"/></td>
    <td width="10%"><fmt:message key="monitor.starter"/></td>
    <TD width="5%">&nbsp;</TD>
  </tr>

<logic:iterate id="currProc" name="activeFlowProcs" indexId="index"
     type="org.powerstone.workflow.model.FlowProc" scope="request">
  <tr <%if(index.intValue()%2==0){%>class="TD3"<%}else{%>class="TD2"<%}%>
      onClick="changeBgColor(this);"
      style="cursor:hand;" id="<%=currProc.getFlowProcID()%>"
      ondblclick="showProcDetail(this)"
      title='<fmt:message key="monitor.ondblclick"/>'
  >
    <td align="left">
      &nbsp;<%=currProc.getPreviewText()%>
    </td>
    <td height="25">&nbsp;<%=currProc.getStartTime()%></td>
    <td height="25">&nbsp;<%=currProc.getStarterUserID()%></td>
    <td align="center" class="TD1">
        <%=Integer.parseInt(index.toString())+1%>
    </td>
  </tr>
</logic:iterate>
    </TABLE>

<hr width="95%" align="center" size="1" >
     <br />
</form>
          </td>
        </tr>
      </table>

</body>
</html>
