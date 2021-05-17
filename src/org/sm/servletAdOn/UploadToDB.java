package org.sm.servletAdOn;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.sm.beans.UserAccount;
import org.sm.utils.DBUtils;
import org.sm.utils.MyUtils;

@WebServlet(urlPatterns = {"/uploadToDB"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024* 2,
				maxFileSize = 1024 * 1024 * 10,
				maxRequestSize = 1024 * 1024 * 50)
public class UploadToDB extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/uploadToDB.jsp");
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		try
		{
			HttpSession session = request.getSession();
			UserAccount loginedUser = MyUtils.getLoginedUser(session);
			Long userId = loginedUser.getUserId();
			
			Connection conn = MyUtils.getStoredConnection(request);
			String description = request.getParameter("description");
			
			
			//part list (multi files)
			for(Part part : request.getParts())
			{
				String fileName = extractFileName(part);
				if(fileName != null && fileName.length() > 0)
				{
					//File data
					InputStream is = part.getInputStream();
					//Write to file
					DBUtils.uploadFilesToDB(conn, userId, fileName, is, description);
				}
			}
			
			//Upload successfully
			response.sendRedirect(request.getContextPath()+"/uploadToDBResults");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			request.setAttribute("errorString", "Error : Login to upload files. Exception err = "+e.getMessage());
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
			dispatcher.forward(request, response);
		}
		
	}
	
	private String extractFileName(Part part)
	{
		String contentDisp=part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for(String s : items)
		{
			if(s.trim().startsWith("filename"));
			String clientFileName = s.substring(s.indexOf("=")+2, s.length() - 1);
			clientFileName = clientFileName.replace("\\", "/");
			int i = clientFileName.lastIndexOf('/');
			return clientFileName.substring(i +1);
		}
		return null;
	}
}
