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
		{"view", "addUser", "addUserSubmit", "editUser", "editUserSubmit", "deleteUserSubmit", "login", "logout"};
	private HttpSession _session;
	
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

	protected void switching(String action, UserBean user, Map<String, String[]> params) {
		if (action == null) {
			action = "view";
		}
		System.out.println(this._actions[4]);
		int actionNb = Arrays.binarySearch(this._actions, action);
		if (actionNb >= 0) {
			user.setAction(action);
		}
		else
		{
			actionNb = 0; 
		}
		
		UserBean newUser = null;
		System.out.println(actionNb);
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
			newUser = new UserBean(Integer.parseInt(params.get("userId")[0]));
			newUser.createUserMap(params);
//			if (!newUser.userExists()) { //NO TIME TO IMPLEMENT
			System.out.println("TEST");
			System.out.println(newUser.getId());
			System.out.println(newUser.getName());
			System.out.println(newUser.getLogin());
			System.out.println(newUser.getPassword());
			if (!newUser.updateRecord()) {
					// ERROR
					System.out.println("Error in updateRecord");
				} else {
					user.setAction("view");
				}
//			}
			break;
		case 5: //deleteUserSubmit
			newUser = new UserBean(Integer.parseInt(params.get("userId")[0]));
			if (!newUser.deleteRecord()) {
				// ERROR
				System.out.println("Error in addRecord");
			} else {
				user.setAction("view");
			}
			break;
		case 6: //login
			user.createUserMap(params);
			if(user.getRecord())
			{
				user.setIsConnected(true);
				_session.setAttribute("userBean", user);
				user.setAction("view");
				
			}
			break;
			
		case 7: //logout
			user = new UserBean();
			_session.invalidate();
			
			break;
		default:
			break;
			
			
		}
	}
	
	public UserBean loadUser()
	{
		UserBean user = (UserBean) _session.getAttribute("userBean");
		if (user == null) {
			user = new UserBean();
		}
		return user;
	}
	
	public void routing(HttpServletRequest request, HttpServletResponse response) {
		_session = request.getSession(true);
		UserBean user = loadUser();
		System.out.println(request.getParameter("action"));
		switching(request.getParameter("action"), user, request.getParameterMap());

		//Reloading the session and user parameters in case we got logged out
		_session = request.getSession(true);
		user = loadUser();
		
		// On regarde si on est connecté: Si non=>login.jsp, si oui=>admin.jsp ou cart.jsp
		if(!user.getIsConnected())
			
			this._url = "/login.jsp";
		else {
			if (user.isAdmin())
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
