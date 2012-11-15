package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class RouterBean {

	private String url = "";

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public void routing(HttpServletRequest request, HttpServletResponse response) {
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
