package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatheque.Utilisateur;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		HttpSession session = req.getSession();
		Utilisateur user = (Utilisateur) session.getAttribute("user");
		
		
		out.println("<!doctype html>");
		out.println("<html>");
		out.println("<head><title>Boot</title></head>");
		out.println("<body>");
		
		// Utilisateur non connecté
		if(user == null) {
			out.println("<p>Vous n etes pas connect&eacute</p>");
			out.println("<p><a href=\"auth\">Se connecter</a></p>");
		}
		else {
			out.println("<p><a href=\"logout\">Deconnection</a></p>");
			
			if(user.getType() == 1) { // Abonné
				out.println("<p><a href=\"emprunt\">Emprunt</a></p>");
				out.println("<p><a href=\"retour\">Retour</a></p>");
			}
			else if(user.getType() == 2) { // bibliothecaire
				out.println("<p><a href=\"ajout\">Ajouter un document</a></p>");
			}
			else if(user.getType() == 3) { // Admin
				out.println("<p><a href=\"boot\">Boot</a></p>");
			}
		}
		out.println("</body>");
		out.println("</html>");
	}
}
