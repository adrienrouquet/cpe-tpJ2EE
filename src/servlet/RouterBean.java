package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		// On regarde si on est connecté: Si non=>login.jsp, si oui=>logon.jsp
		if(request.getSession(false) == null || request.getSession(false).getAttribute("connected") == null)
			this._url = "/login.jsp";
		else
			this._url = "/logon.jsp";
		
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
