package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OnlineStore extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private RouterBean _router = new RouterBean();

    public OnlineStore() {
        super();       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		
		context.setAttribute("routerBean", this._router);
		
		_router.setRequest(request);
		_router.setResponse(response);
		
		try {
			_router.routing();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		
		context.setAttribute("routerBean", this._router);
		
		_router.setRequest(request);
		_router.setResponse(response);
		
		try {
			_router.routing();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
