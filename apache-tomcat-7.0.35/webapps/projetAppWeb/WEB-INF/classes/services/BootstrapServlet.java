package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Classe qui déclare quelle PersistentMediatheque charger
 * ! A utiliser après le démarrage du serveur
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
		
		out.println("<!doctype html>");
		out.println("<html>");
		out.println("<head><title>Boot</title></head>");
		out.println("<body>");
		out.println("<p>" + request.getRequestURI() + "</p>");
		out.println("<p>Veuillez inserer le nom de l implementation de PersistantMediatheque a utiliser (ex: persistantData.MediathequeDataJDBC )</p>");
		out.println("<form action=\"boot\" method=\"post\">");
		out.println("<input type=\"text\" name=\"persistMediaName\" placeholder=\"" + persistMediaName + "\">");
		out.println("<input type=\"submit\" name=\"valider\">");
		out.println("</form>");
		if(persistMediaName != null) {
			try {
				
				// Donne l'implémentation de PersistentMediatheque
				// à la médiathèque via le bloc static
				Class.forName(persistMediaName);
				
			} catch (ClassNotFoundException e) {
				out.println("L injection de dépendance n est pas valide!");
			}
		}
		out.println("</body>");
		out.println("</html>");
		
	}
}
