
<%@ page import="java.util.Date" %>

<%
Date today = new Date();
int nb1 = Integer.parseInt(request.getParameter("nb1"));
int nb2 = Integer.parseInt(request.getParameter("nb2"));
String op = request.getParameter("op");
%>

	<html>
		<head>
		</head>
		
		<body>
			<p><% if(op == "plus" || op == "-" || op == "*") { %>
				<%=  nb1 %> <%=op%>   <%= nb2 %>  =
				<% if(op == "plus") { %>
				<%=  nb1 + nb2 %>
				<% }%>
				<% if(op == "-") { %>
				<%=  nb1 - nb2 %>
				<% }%>
				<% if(op == "*") { %>
				<%=  nb1 * nb2 %>
				<% }%>
			<% } %>
			</p>
		</body>
	</html>
	