package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatheque.Mediatheque;

@WebServlet("/ajout")
public class AjoutServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7327758115341443234L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException { 
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String nom = request.getParameter("nom");
		String auteur = request.getParameter("auteur");
		String type = request.getParameter("type");
		
		out.println("<!doctype html>");
		out.println("<html>");
		out.println("<head><title>Ajout de documents</title></head>");
		out.println("<body>");
		if(nom != null && auteur != null && type != null) {
			Mediatheque.getInstance().nouveauDocument(Integer.parseInt(type), new Object[] {nom, auteur});
		}
		out.println("<p>Ajouter un document</p>");
		out.println("<form action=\"ajout\" method=\"post\">");
		out.println("<input type=\"text\" name=\"nom\">");
		out.println("<input type=\"text\" name=\"auteur\">");
		out.println("<select name=\"type\">");
		out.println("<option value=\"0\">CD</option>");
		out.println("<option value=\"1\">DVD</option>");
		out.println("<option value=\"2\">Livre</option>");
		out.println("</select>");
		out.println("<input type=\"submit\" value=\"Enregistrer\">");
		out.println("</form>");
		out.println("<p>" + nom + "</p>");
		out.println("<p>" + auteur + "</p>");
		out.println("<p>" + type + "</p>");
		out.println("</body>");
		out.println("</html>");
	}
}
