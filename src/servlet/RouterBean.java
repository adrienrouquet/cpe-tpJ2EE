package servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class RouterBean {

	private String _url = "";
	private String _error = "";
	private String[] _actions =
		{"view", "addUser", "addUserSubmit", "editUser", "editUserSubmit", "deleteUserSubmit"};
	
	public String getUrl() {
		return this._url;
	}

	public void setUrl(String url) {
		this._url = url;
	}
	
	public String getError() {
		return this._error;
	}
	
	public void setError(String error) {
		this._error = error;
	}
	
	public String[] getActions() {
		return _actions;
	}

	public void setActions(String actions[]) {
		this._actions = actions;
	}

	protected void switching(String action, UserBean user, Map<String, String> params) {
		int actionNb = Arrays.binarySearch(this._actions, action);
		if (actionNb >= 0) {
			user.setAction(action);
		}
		
		UserBean newUser = null;
		
		switch (actionNb) {
		case 2: //addUserSubmit
			newUser = new UserBean();
			newUser.createUserMap(params);
			if (!newUser.userExists()) {
				if(!newUser.addRecord()) {
					// ERROR
				} else {
					user.setAction("view");
				}
			}
			break;
		case 4: //editUserSubmit
			newUser = new UserBean(Integer.parseInt(params.get("userId")));
			newUser.createUserMap(params);
			if (!newUser.userExists()) {
				if (!newUser.updateRecord()) {
					// ERROR
				} else {
					user.setAction("view");
				}
			}
			break;
		case 5: //deleteUserSubmit
			newUser = new UserBean(Integer.parseInt(params.get("userId")));
			if (!newUser.deleteRecord()) {
				// ERROR
			} else {
				user.setAction("view");
			}
		default:
			break;
		}
	}
	
	public void routing(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		UserBean user = (UserBean) session.getAttribute("userBean");
		
		if (user == null) {
//			System.out.println("USER NULL");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			user = new UserBean(username, password);
			user.isUserValid();
		}
		
		switching(request.getParameter("action"), user, request.getParameterMap());
		
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
