package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;

@WebServlet ("/accueilbiblio")
public class ServiceAccueilBiblio extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title>Accueil</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form method=\"post\" action=\"redirectservice\">");
		out.println("<button type=\"submit\" value=\"ajout\">Emprunt</button>");
		
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}
}
