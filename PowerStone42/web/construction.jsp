<%@page pageEncoding="GBK"%>
<html>
<head>
<title>
construction
</title>
<LINK href="<%=request.getContextPath()%>/img/PowerStone.css" type="text/css" rel="stylesheet">
</head>
<body>
<table class="tableFrame" cellspacing="1">
  <tr>
    <td class="tdTitle">&nbsp;PowerStone�������̨�����б�</td>
  </tr>
  <tr>
    <td class="tdContent">
  <TABLE class="tableNoBorderCenter">
    <TR>
      <TD <%if("1".equals(request.getParameter("fun_no"))){%>class="TD3"<%}%> width="5%" valign="middle" align="right" nowrap>
        <span class='construction_sub_heading'>�� �� �� ��</span>
      </TD>
      <TD <%if("1".equals(request.getParameter("fun_no"))){%>class="TD3"<%}%> width="89%" valign="middle" align="left" nowrap>
        <span>�½����š��½��Ӳ��š��޸Ĳ�����Ϣ��ɾ�����š��鿴�����û����½��û���ɾ���û����鿴�������κβ��ŵĲ����û���ת�Ʋ����û�</span>
      </TD>
    </TR>
    <TR>
      <TD <%if("2".equals(request.getParameter("fun_no"))){%>class="TD3"<%}%> width="5%" valign="middle" align="right" nowrap>
        <span class='construction_sub_heading'>�� ɫ �� ��</span>
      </TD>
      <TD <%if("2".equals(request.getParameter("fun_no"))){%>class="TD3"<%}%> width="89%" valign="middle" align="left" nowrap>
        <span>�½���ɫ���޸Ľ�ɫ��Ϣ��ɾ����ɫ���鿴��ɫ�û���ɾ����ɫ�û�</span>
      </TD>
    </TR>
    <TR>
      <TD <%if("3".equals(request.getParameter("fun_no"))){%>class="TD3"<%}%> width="5%" valign="middle" align="right" nowrap>
        <span class='construction_sub_heading'>�� ɫ �� Ȩ��</span>
      </TD>
      <TD <%if("3".equals(request.getParameter("fun_no"))){%>class="TD3"<%}%> width="89%" valign="middle" align="left" nowrap>
        <span>�鿴���޸Ľ�ɫ���е�Ȩ�ޣ��û���������е�Ȩ�����⣬���������ڽ�ɫ��Ȩ�ޣ�</span>
      </TD>
    </TR>
    <TR>
      <TD <%if("4".equals(request.getParameter("fun_no"))){%>class="TD3"<%}%> width="5%" valign="middle" align="right" nowrap>
        <span class='construction_sub_heading'>Ȩ �� ע �᣺</span>
      </TD>
      <TD <%if("4".equals(request.getParameter("fun_no"))){%>class="TD3"<%}%> width="89%" valign="middle" align="left" nowrap>
        <span>��ϵͳ�����׶���������ɾ���ġ���WEBģ��ע�����Ҫ���ʿ��Ƶ���Դ��<font color="red">ϵͳ��ʽ���к���ô����</font></span>
      </TD>
    </TR>
    <TR>
      <TD <%if("5".equals(request.getParameter("fun_no"))){%>class="TD3"<%}%> width="5%" valign="middle" align="right" nowrap>
        <span class='construction_sub_heading'>�� �� �� ��</span>
      </TD>
      <TD <%if("5".equals(request.getParameter("fun_no"))){%>class="TD3"<%}%> width="89%" valign="middle" align="left" nowrap>
        <span>�ϴ����������塢���̲������̼�ء����̷���</span>
      </TD>
    </TR>

    <TR>
      <TD <%if("6".equals(request.getParameter("fun_no"))){%>class="TD3"<%}%> width="5%" valign="middle" align="right" nowrap>
        <span class='construction_sub_heading'>����Ӧ��ע�᣺</span>
      </TD>
      <TD <%if("6".equals(request.getParameter("fun_no"))){%>class="TD3"<%}%> width="89%" valign="middle" align="left" nowrap>
        <span>����ɾ���ġ�������Ӧ��ע����Ϣ�����������</span>
      </TD>
    </TR>

    <TR>
      <TD <%if("7".equals(request.getParameter("fun_no"))){%>class="TD3"<%}%> width="5%" valign="middle" align="right" nowrap>
        <span class='construction_sub_heading'>���������б�</span>
      </TD>
      <TD <%if("7".equals(request.getParameter("fun_no"))){%>class="TD3"<%}%> width="89%" valign="middle" align="left" nowrap>
        <span>��������ҵ����񡢷�������Ӧ��</span>
      </TD>
    </TR>
  </TABLE>
 </TD>
    </TR>
  </TABLE>
</body>
</html>
