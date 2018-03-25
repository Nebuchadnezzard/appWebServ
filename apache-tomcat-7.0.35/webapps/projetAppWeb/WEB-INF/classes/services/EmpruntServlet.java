package services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatheque.Document;
import mediatheque.EmpruntException;
import mediatheque.Mediatheque;
import mediatheque.Utilisateur;

@WebServlet("/emprunt")
public class EmpruntServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8919597883191559441L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		Utilisateur user = (Utilisateur) session.getAttribute("user");
		
		String docAEmprunter = request.getParameter("Emprunt");
		
		out.println("<!doctype html>");
		out.println("<html>");
		out.println("<head><title>Boot</title></head>");
		out.println("<body>");
		
		if(user == null) {
			out.println("<p>Vous n etes pas connect&eacute</p>");
			out.println("<p><a href=\"auth\">Se connecter</a></p>");
		}
		else {
			List<Document> docs = Mediatheque.getInstance().tousLesDocuments();
			if(docAEmprunter != null) {
				try {
					Document doc = Mediatheque.getInstance().getDocument(Integer.parseInt(docAEmprunter));
					Mediatheque.getInstance().emprunt(doc, user);
					out.println("<p>Document emprunte</p>");
				} catch (NumberFormatException | EmpruntException e) {
					out.println("<p>Document deja emprunte</p>");
				}
			}
			
			out.println("<p>Cliquez sur le numero à droite pour emprunter le document</p>");
			out.println("<table>");
			out.println("<tr>");
			out.println("<th>N°</th>");
			out.println("<th>Titre</th>");
			out.println("<th>Auteur</th>");
			out.println("<th>Type</th>");
			out.println("<th>Emprunt</th>");
			out.println("</tr>");
			
			out.println("<form action=\"emprunt\" method=\"post\">");
			for(Document d : docs) {
				out.println("<tr>");
				Object[] docAffiche = d.affiche();
				out.println("<td>" + docAffiche[0] + "</td>");
				out.println("<td>" + docAffiche[1] + "</td>");
				out.println("<td>" + docAffiche[2] + "</td>");
				out.println("<td>" + docAffiche[3] + "</td>");
				out.println("<td>" + docAffiche[4] + "</td>");
				
				// Bouton emprunter uniquement si le doc n'est pas deja emprunté
				if(docAffiche[4].equals("Aucun")) {
					out.println("<td><input type=\"submit\" name=\"Emprunt\" value=\""+ docAffiche[0] + "\"></td>");
				}
				
				out.println("</tr>");
			}
			out.println("</form>");
			
			out.println("</table>");
		}
		
		out.println("</body>");
		out.println("</html>");
	}

}
