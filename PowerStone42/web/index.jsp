<%@page pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.powerstone.org/powerstone/ca" prefix="ca"%>

<!--如果已经登陆，跳转到主页-->
<ca:hasRightToDo url="/role_manage/**">
  <c:redirect url="/main.jsp"></c:redirect>
</ca:hasRightToDo>

<ca:online>
  <c:redirect url="/hasNoRight.jsp"></c:redirect>
</ca:online>
<ca:notOnline>
  <c:redirect url="/login.jsp"></c:redirect>
</ca:notOnline>

