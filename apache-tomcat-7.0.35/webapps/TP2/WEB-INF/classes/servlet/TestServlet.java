package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import accesBD.AccesBDOracle;

@WebServlet("/bonjour")
public class TestServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		Date date = new Date();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>");

		String title = "bonjour jeff";

		out.println("<title>" + title + "</title>");
		out.println("</head>");
		out.println("<body bgcolor=\"white\">");

		out.println("<h1>" + title + " big boss" + "</h1>");
		try {
			out.println(AccesBDOracle.tousPilotes());
		}
		catch(Exception e){}
		out.println("<p>On est le : " + date + "</p>");
		out.println("</body>");
		out.println("</html>");
	}

}
