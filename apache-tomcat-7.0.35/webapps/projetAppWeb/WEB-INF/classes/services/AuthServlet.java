package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/authent")
public class AuthServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1270168341954903962L;
	
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Connexion</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form method=\"post\" action=\"redirect\">");
		out.println("<fieldset><legend>Login : </legend><input type=\"text\" name=\"login\"/></fieldset>");
		out.println("<fieldset><legend>Mot de passe : </legend><input type=\"password\" name=\"password\"/></fieldset>");
		out.println("<input type=\"submit\" name=\"submit\" value=\"Se connecter\"/>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}

}
