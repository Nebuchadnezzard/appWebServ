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
import mediatheque.Mediatheque;
import mediatheque.Utilisateur;

@WebServlet("/retour")
public class RetourServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4824139845551106579L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		Utilisateur user = (Utilisateur) session.getAttribute("user");
		
		String docARendre = request.getParameter("Retour");
		
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
			
			if(docARendre != null) {
				out.println("<p>" +docARendre + "</p>");
				Document doc = Mediatheque.getInstance().getDocument(Integer.parseInt(docARendre));
				Mediatheque.getInstance().retour(doc);
				out.println("<p>Document rendu</p>");
			}
			
			out.println("<p>Documents emprunt&eacute;s</p>");
			out.println("<form action=\"retour\" method=\"post\">");
			out.println("<table>");
			out.println("<tr>");
			out.println("<th>N°</th>");
			out.println("<th>Titre</th>");
			out.println("<th>Auteur</th>");
			out.println("<th>Type</th>");
			out.println("<th>Rendre</th>");
			out.println("</tr>");
			for(Document d : docs) {
				Object[] objAffiche = d.affiche();
				if(objAffiche[5].equals(user.getIdUtil())) {
					out.println("<tr>");
					out.println("<td>" + objAffiche[0] + "</td>");
					out.println("<td>" + objAffiche[1] + "</td>");
					out.println("<td>" + objAffiche[2] + "</td>");
					out.println("<td>" + objAffiche[3] + "</td>");
					out.println("<td><input type=\"submit\" name=\"Retour\" value=\""+ objAffiche[0] + "\"></td>");
					out.println("</tr>");
				}
			}
			out.println("</table>");
			out.println("</form>");
		}
		
		out.println("</body>");
		out.println("</html>");
	}
}
