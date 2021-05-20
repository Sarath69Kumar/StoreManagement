package org.sm.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.sm.beans.UserAccount;
import org.sm.recaptcha.VerifyUtils;
import org.sm.utils.DBUtils;
import org.sm.utils.MyUtils;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet
{
	private static final long serialVersionUID = 958900029856081978L;
	public LoginServlet()
	{
		super();
		System.out.println("LoginServlet  ::  Constructor");
	}
	
	//Show login page
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		/**
		 * Forword to /WEB-INF/views/loginView.jsp
		 * (User cnnot access directly into jsp pages placed in WEB-INF)
		 */
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
		System.out.println("LoginServlet :: doGet"+dispatcher.toString());
		dispatcher.forward(request, response);
	}
	
	/**
	 * When the user enters userName & password, and click submit
	 * This method will be executed
	 */
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("LoginServlet :: doPost");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String rememberMeStr = request.getParameter("rememberMe");
		boolean remember = "Y".equals(rememberMeStr);
		boolean valid=false;
		
		UserAccount user = null;
		boolean hasError = false;
		String errorString =  null;
		
		if(userName == null || password == null || userName.length() == 0 || password.length() == 0)
		{
			hasError = true;
			errorString = "Required username and password";
		}
		else
		{
			Connection conn = MyUtils.getStoredConnection(request);
			try
			{
				//Find the user in the DB.
				user = DBUtils.findUser(conn, userName, password);
				
				if(user == null)
				{
					hasError = true;
					errorString = "User Name or password invalid";
				}
				else
				{
					String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
					
					System.out.println("gRecaptchaResponse = "+gRecaptchaResponse);
					
					valid = VerifyUtils.verify(gRecaptchaResponse);
					
					if(!valid)
						errorString = "Captcha invalid";
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				hasError = true;
				errorString = e.getMessage();
			}
		}
		
		//if error, forward to /WEB-INF/views/login.jsp
		if(hasError)
		{
			user =  new UserAccount();
			user.setUserName(userName);
			user.setPassword(password);
			
			//Store information in request attribute, before forward
			request.setAttribute("errorString", errorString);
			request.setAttribute("user", user);
			
			//Forward to /WEB-INF/views/login.jsp
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
			dispatcher.forward(request, response);
		}
		
		/*
		 * if captcha is invalid, then this will be executed
		 */
		else if(!valid)
		{
			request.setAttribute("errorString", errorString);
			request.setAttribute("user", user);
			
			//Forward to /WEB-INF/views/login.jsp
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
			dispatcher.forward(request, response);
		}
		
		/**
		 * If no error
		 * Store user info in session
		 */
		else
		{
			HttpSession session = request.getSession();
			MyUtils.storeLoginedUser(session, user);
			
			//If userchecked "Remember me"
			if(remember)
			{
				MyUtils.storeUserCookie(response, user);
			}
			//Else delete cookie
			else
			{
				MyUtils.deleteUserCookie(response);
			}
			
			//Redirect to userInfo page.
			response.sendRedirect(request.getContextPath() + "/userInfo");
		}
	}
}
