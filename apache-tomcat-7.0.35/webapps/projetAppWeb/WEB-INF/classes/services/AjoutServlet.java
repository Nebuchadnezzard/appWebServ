package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ajout")
public class AjoutServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7327758115341443234L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException { 
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<!doctype html>");
		out.println("<html>");
		out.println("<head><title>Ajout de documents</title></head>");
		out.println("<body>");
		out.println("<p>Ajouter un document</p>");
		out.println("</body>");
		out.println("</body>");
	}
}
