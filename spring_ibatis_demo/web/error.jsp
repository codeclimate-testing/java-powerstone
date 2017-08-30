<%@ page language="java" isErrorPage="true"%>

<head>
<title>Doh!</title>
</head>

An Error has occurred in this application.

<%if (exception != null) {	%>
<pre><%exception.printStackTrace(new java.io.PrintWriter(out));			%>
</pre>
<%} else if (request.getAttribute("exception") != null) //from Spring
			{
				exception = (Exception) request.getAttribute("exception");
%>
				<pre><%exception.printStackTrace(new java.io.PrintWriter(out));%>
				</pre>
<%
			} else {%>
Please check your log files for further information.
<%}

		%>
