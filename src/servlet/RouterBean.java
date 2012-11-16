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

	private HttpServletRequest _request;
	private HttpServletResponse _response;
	private String _url = "";
	private String _error = "";
	private String[] _actions = {
			"view",
			"addUser",
			"addUserSubmit",
			"editUser",
			"editUserSubmit",
			"deleteUserSubmit",
			"login",
			"logout"
			};
	
	private HttpSession _session;
	
	public HttpServletRequest getRequest() {
		return _request;
	}

	public void setRequest(HttpServletRequest request) {
		this._request = request;
	}

	public HttpServletResponse getResponse() {
		return _response;
	}

	public void setResponse(HttpServletResponse response) {
		this._response = response;
	}

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

	protected void switching(UserBean user) {
		String action = _request.getParameter("action");
		Map<String, String[]> params = _request.getParameterMap();
		
		if (action == null) {
			action = "view";
		}
		
		System.out.println("SWITCHING: " + action);
		
		int actionNb = Arrays.binarySearch(this._actions, action);
		if (actionNb >= 0) {
			user.setAction(action);
		} else {
			actionNb = -1;
		}
		
		UserBean newUser = null;
		
		switch (actionNb) {
		case 2: //addUserSubmit
			System.out.println("SWITCHING: case 2: //addUserSubmit");
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
			System.out.println("SWITCHING: case 4: //editUserSubmit");
			newUser = new UserBean(Integer.parseInt(params.get("userId")[0]));
			newUser.createUserMap(params);
//			if (!newUser.userExists()) { //NO TIME TO IMPLEMENT
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
			System.out.println("SWITCHING: case 5: //deleteUserSubmit");
			newUser = new UserBean(Integer.parseInt(params.get("userId")[0]));
			if (!newUser.deleteRecord()) {
				// ERROR
				System.out.println("Error in addRecord");
			} else {
				user.setAction("view");
			}
			break;
		case 6: //login
			System.out.println("SWITCHING: case 6: //login");
			user.createUserMap(params);
			if(user.getRecord())
			{
				user.setIsConnected(true);
				_session.setAttribute("userBean", user);
				user.setAction("view");
				
			}
			break;
		case 7: //logout
			System.out.println("SWITCHING: case 7: //logout");
			user = new UserBean();
			_session.invalidate();
			//Reloading the session and user parameters in case we got logged out
			_session = _request.getSession(true);
			user = loadUser();
			break;
		default:
			user.setAction("view");
			break;
		}
	}
	
	public UserBean loadUser() {
		UserBean user = (UserBean) _session.getAttribute("userBean");
		if (user == null) {
			user = new UserBean();
		}
		return user;
	}
	
	public void routing() {
		_session = _request.getSession(true);
		UserBean user = loadUser();
//		System.out.println(_request.getParameter("action"));
		switching(user);

		// On regarde si on est connecté: Si non=>login.jsp, si oui=>admin.jsp ou cart.jsp
		if(!user.getIsConnected())
			this._url = "/login.jsp";
		else {
			if (user.isAdmin())
				this._url = "/admin.jsp";
			else
				this._url = "/cart.jsp";
		}
		
		RequestDispatcher dispatch = this._request.getRequestDispatcher("/index.jsp");
		try {
			dispatch.forward(_request, _response);
		} catch (ServletException e) {
			System.err.println("Error in routing: " + e.getMessage());
			//e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error in routing: " + e.getMessage());
			//e.printStackTrace();
		}
	}

}
