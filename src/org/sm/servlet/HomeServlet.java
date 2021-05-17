package org.sm.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	public HomeServlet()
	{
		super();
		System.out.println("HomeServlet :: Constructor");
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		/**
		 * Forword to /WEB-INF/views/home.jsp
		 * (User can not access directly into jsp pages placed in WEB-INF)
		 */
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/homeView.jsp");
		System.out.println("HomeServlet  ::  doGet  :: getServletContext= "+getServletContext()+ "dispatcher = "+dispatcher);
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("HomeServlet  :: doPost");
		doGet(request, response);
	}
}
