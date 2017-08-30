<%@page  pageEncoding="GBK"  %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<h3>Data Access Failure</h3>
<p>
  <c:out value="${requestScope.exception}"/>|
  <c:out value="${requestScope.exception.message}"/>.
  To see detail,<a href="?" onclick="readMore(); return false">Click here</a>
</p>

<div id="readmore" style="display:none">
<%
Exception ex = (Exception) request.getAttribute("exception");
ex.printStackTrace(new java.io.PrintWriter(out));
%>
</div>

<a href="<c:url value='/'/>">&#171; Home</a>

<script type="text/javascript">
function readMore() {
    var more = document.getElementById("readmore");
    if (more.style.display == "") {
        more.style.display = "none";
    } else {
        more.style.display = "";
    }
}
</script>
