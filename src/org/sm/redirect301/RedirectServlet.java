package org.sm.redirect301;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/newURL")
public class RedirectServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	public RedirectServlet()
	{
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		String text = "<h2>Hello<h2>"
						+ "You are in URL : <br/>"
						+ "<h3" + request.getRequestURL() +"</h3>";
		response.getWriter().print(text);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}
