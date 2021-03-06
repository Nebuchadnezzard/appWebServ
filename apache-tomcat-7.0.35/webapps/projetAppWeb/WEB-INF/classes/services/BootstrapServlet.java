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

/**
 * Classe qui d�clare quelle PersistentMediatheque charger
 * ! A utiliser apr�s le d�marrage du serveur
 * @author Jacques COUDERC
 * @since 23/02/2018
 * @version 1.0
 */
@WebServlet ("/boot")
public class BootstrapServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3171475695277487275L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String persistMediaName = request.getParameter("persistMediaName");
		
		HttpSession session = request.getSession();
		Utilisateur user = (Utilisateur) session.getAttribute("user");
		
		if(user.getType() == 3) { // Admin
			out.println("<!doctype html>");
			out.println("<html>");
			out.println("<head><title>Boot</title></head>");
			out.println("<body>");
			if(Mediatheque.getInstance().isSet()) {
				out.println("D&eacute;prendance charg&eacute;e!");
			}
			out.println("<p>Veuillez inserer le nom de l implementation de PersistantMediatheque a utiliser (ex: persistantdata.MediathequeDataJDBC )</p>");
			out.println("<form action=\"boot\" method=\"post\">");
			out.println("<input type=\"text\" name=\"persistMediaName\" placeholder=\"" + persistMediaName + "\">");
			out.println("<input type=\"submit\" name=\"valider\">");
			out.println("</form>");
			if(persistMediaName != null) {
				try {
					
					// Donne l'impl�mentation de PersistentMediatheque
					// � la m�diath�que via le bloc static
					Class.forName(persistMediaName);
					
				} catch (ClassNotFoundException e) {
					out.println("L injection de d�pendance n est pas valide!");
				}
			}
			out.println("</body>");
			out.println("</html>");
		}
		else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}
}
