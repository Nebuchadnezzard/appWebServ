package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatheque.Mediatheque;
import mediatheque.Utilisateur;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1270168341954903962L;
	
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String login = request.getParameter("login");
		String pwd = request.getParameter("pwd");
		
		
		if(login == null) {
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Connexion</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<form method=\"post\" action=\"auth\">");
			out.println("<fieldset><legend>Login : </legend><input type=\"text\" name=\"login\"/></fieldset>");
			out.println("<fieldset><legend>Mot de passe : </legend><input type=\"password\" name=\"pwd\"/></fieldset>");
			out.println("<input type=\"submit\" name=\"submit\" value=\"Se connecter\"/>");
			out.println("</form>");
			out.println("</body>");
			out.println("</html>");
		}
		else {
			
			Utilisateur user = Mediatheque.getInstance().getUser(login, pwd);
			if(user == null) {
				// L'utilisateur n'existe pas
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Connexion</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("Identifiant ou mot de passe incorrect"); 
				out.println("<form method=\"post\" action=\"auth\">");
				out.println("<fieldset><legend>Login : </legend><input type=\"text\" name=\"login\"/></fieldset>");
				out.println("<fieldset><legend>Mot de passe : </legend><input type=\"password\" name=\"pwd\"/></fieldset>");
				out.println("<input type=\"submit\" name=\"submit\" value=\"Se connecter\"/>");
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
			}
			else {
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				
				//request.getRequestDispatcher("home").forward(request, response);
				response.sendRedirect("home");
			}
			
		}
	}

}
