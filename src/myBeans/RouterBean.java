package myBeans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import myBeans.CartBean;
import myBeans.UserBean;

public class RouterBean {

	private HttpServletRequest _request;
	private HttpServletResponse _response;
	private String _url = "";
	private String _error = "";
	
	private ArrayList<String> _actions = new ArrayList<String>();		
	
	public RouterBean()
	{
		_actions.add("view");
		_actions.add("addUser");
		_actions.add("addUserSubmit");
		_actions.add("editUser");
		_actions.add("editUserSubmit");
		_actions.add("deleteUserSubmit");
		_actions.add("login");
		_actions.add("logout");
		_actions.add("editPassword");
		_actions.add("editPasswordSubmit");
		_actions.add("editCartSubmit");
		
	}
	
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
	
	protected void switching(UserBean user) throws NamingException {
		String action = _request.getParameter("action");
		Map<String, String[]> params = _request.getParameterMap();
		
		
		if (action == null) {
			action = "view";
		}		
		
		int actionNb = _actions.indexOf(action);
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
					System.out.println("Error in RouterBean.addRecord");
				} else {
					user.setAction("view");
				}
			}
			break;
		case 4: //editUserSubmit
			System.out.println("SWITCHING: case 4: //editUserSubmit");
			newUser = new UserBean(Integer.parseInt(_request.getParameter("userId")));
			newUser.createUserMap(params);
//			if (!newUser.userExists()) { //NO TIME TO IMPLEMENT
			if (!newUser.updateRecord()) {
					// ERROR
					System.out.println("Error in RouterBean.updateRecord");
					user.setAction("editUser");
				} else {
					user.setAction("view");
				}
//			}
			break;
		case 5: //deleteUserSubmit
			System.out.println("SWITCHING: case 5: //deleteUserSubmit");
			newUser = new UserBean(Integer.parseInt(_request.getParameter("userId")));
			if (!newUser.deleteRecord()) {
				// ERROR
				System.out.println("Error in RouterBean.addRecord");
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
				_session.setAttribute("cartBean", new CartBean());
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
			_url = "/login.jsp";
			break;
		case 10: //editCartSubmit
			System.out.println("SWITCHING: case 10: //editCartSubmit");
			CartBean cart = (CartBean) _session.getAttribute("cartBean");
			cart.updateCart(Integer.parseInt(_request.getParameter("productId")), Integer.parseInt(_request.getParameter("productQuantity")));
			user.setAction("view");
			break;
		default:
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
	
	public void routing() throws NamingException {
		_session = _request.getSession(true);
		UserBean user = loadUser();
//		System.out.println(_request.getParameter("action"));
		switching(user);
		
		//Reloading user in case of a logout
		user = loadUser();
		
		// On regarde si on est connect�: Si non=>login.jsp, si oui=>admin.jsp ou cart.jsp
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
