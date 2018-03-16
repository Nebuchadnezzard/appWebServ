package services;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet ("/redirect")
public class RedirectServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9144090750483154167L;
	private String login;
	private String password;
	
	public RedirectServlet() {
		this.login = null;
		this.password = null;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pwd = request.getParameter("password");
        Utilisateur user = Mediatheque.getUser(login, pwd);
        
        
        
        if(user == null) {
        	response.sendRedirect("/authent");
        }
        else if(user.getType() == 1) {
        	response.sendRedirect("/accueilbiblio");
        }
        else {
        	response.sendRedirect("/accueilutil");
        }
       
     // le prof a explique qu'il fallait mieux ne pas utiliser de jsp et de tout mettre dans les servlets
        /*
        RequestDispatcher dispatcher = null;
         
         
        if(this.login.equalsIgnoreCase(login) && this.password.equalsIgnoreCase(pwd)){
        // faire dispatcher en fonction du type util
            dispatcher = request.getRequestDispatcher("/accueil.jsp");
        }
        else{
            dispatcher = request.getRequestDispatcher("/login.jsp");
        }
        dispatcher.forward(request, response);
        */
        
         
    }
}
