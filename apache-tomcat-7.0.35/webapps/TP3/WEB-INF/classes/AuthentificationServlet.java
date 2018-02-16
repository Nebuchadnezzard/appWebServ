
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import data.User;

@WebServlet("/authentifier")
public class AuthentificationServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String login = request.getParameter("login");
		String mdp = request.getParameter("mdp");
		
		User user = new User(login, mdp);
		
		HttpSession session = request.getSession(true);
		session.setAttribute("user", user);
		
		Date date = new Date();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<html>");


		out.println("</head>");
		out.println("</body>");
		out.println("<h1> Vous etes co : " + user.getLogin() + "</h1>");
		
		try {

		} catch (Exception e) {
		}
		out.println("<p>On est le : " + date + "</p>");
		out.println("</body>");
		out.println("</html>");
	}

}
