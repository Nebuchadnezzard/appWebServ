package services;

@WebServlet ("/redirect")
public class RedirectServlet extends HttpServlet{
	
	private String login;
	private String password;
	
	public RedirectServlet() {
		this.login = null;
		this.password = null;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pwd = request.getParameter("password");
         
        RequestDispatcher dispatcher = null;
         
         
        if(this.login.equalsIgnoreCase(login) && this.password.equalsIgnoreCase(pwd)){
        // faire dispatcher en fonction du type util
            dispatcher = request.getRequestDispatcher("/accueil.jsp");
        }
        else{
            dispatcher = request.getRequestDispatcher("/login.jsp");
        }
        dispatcher.forward(request, response);
        }
         
    }
}
