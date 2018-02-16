
<%@ page import="java.util.Date" %>

<%
Date today = new Date();

%>

	<html>
		<head>
		</head>
		
		<body>
			<p>Accueil, bonjour <%= request.getParameter("sexe") %> <b><%= request.getParameter("name").toUpperCase() %></b> ! Nous sommes le : <%= today.toString()%></p>
		</body>
	</html>
	