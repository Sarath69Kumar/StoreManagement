package org.sm.servletAdOn;
//https://o7planning.org/11067/displaying-image-from-database-with-java-servlet
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sm.beans.UserAccount;
import org.sm.utils.DBUtils;
import org.sm.utils.MyUtils;

///image?id=123
@WebServlet(urlPatterns = {"/image"})
public class DisplayImageServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	public DisplayImageServlet()
	{
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//First get jdbc connection
		Connection conn = MyUtils.getStoredConnection(request);
		//Get parameter
		Long userId = Long.parseLong(request.getParameter("id"));
		try
		{
			//Search image in database
			UserAccount userAcc = DBUtils.findImage(conn, userId);
			if(userAcc == null)
			{
				response.sendRedirect(request.getContextPath()+"images/noimage.jpg");
				return;
			}
			
			String imageFileName = userAcc.getImageFileName();
			System.out.println("Image Name : "+ imageFileName);
			
			//image/jpg
			//image/png
			String contentType = this.getServletContext().getMimeType(imageFileName);
			System.out.println("Content type: "+contentType);
			
			response.setHeader("Content-Type", contentType);
			response.setHeader("Content-Length", String.valueOf(userAcc.getImageData().length));
			response.setHeader("Content-Disposition", "inline;filename=\"" + userAcc.getImageFileName()+ "\"");
			response.getOutputStream().write(userAcc.getImageData());
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		doGet(request,response);
	}
}
