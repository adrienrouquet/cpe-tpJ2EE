package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RouterBean {

	private String _url = "";
	private String _error = "";

	public String getUrl() {
		return this._url;
	}

	public void setUrl(String url) {
		this._url = url;
	}
	
	public String getError() {
		return _error;
	}
	
	public void setError(String _error) {
		this._error = _error;
	}
	
	public void routing(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		UserBean user = (UserBean) session.getAttribute("userBean");
		
		if (user == null) {
			System.out.println("USER NULL");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			user = new UserBean();
			user.setName(username);
			user.setName(password);
		}
		// On regarde si on est connecté: Si non=>login.jsp, si oui=>admin.jsp ou cart.jsp
		if(!user.getIsConnected())
			this._url = "/login.jsp";
		else {
			if (user.isUserAdmin())
				this._url = "/admin.jsp";
			else
				this._url = "/cart.jsp";
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher("/index.jsp");
		try {
			dispatch.forward(request, response);
		} catch (ServletException e) {
			System.err.println("Error in routing: " + e.getMessage());
			//e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error in routing: " + e.getMessage());
			//e.printStackTrace();
		}
	}

}
