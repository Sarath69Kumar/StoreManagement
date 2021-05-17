package org.sm.servletAdOn;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/uploadToDBResults"})
public class UploadToDBResults extends HttpServlet
{
	public static final long serialVersionUID = 1L;
	
	public UploadToDBResults()
	{
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/uploadToDBResults.jsp");
		dispatcher.forward(request, response);
	}
}
