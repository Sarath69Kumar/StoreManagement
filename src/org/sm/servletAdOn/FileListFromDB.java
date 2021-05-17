package org.sm.servletAdOn;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.sm.beans.Attachment;
import org.sm.beans.UserAccount;
import org.sm.utils.DBUtils;
import org.sm.utils.MyUtils;

@WebServlet(urlPatterns = {"/fileListFromDB"})
public class FileListFromDB extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	public FileListFromDB()
	{
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Connection conn = MyUtils.getStoredConnection(request);
		String errorString = null;
		List<Attachment> list = null;
		try
		{
			HttpSession session = request.getSession();
			UserAccount loginedUser = MyUtils.getLoginedUser(session);
			Long userId = loginedUser.getUserId();
			list = DBUtils.getAllAttachmentFromDB(conn, userId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			request.setAttribute("errorString", "Error : Login to See files. Exception err = "+e.getMessage());
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
			dispatcher.forward(request, response);
		}
		request.setAttribute("errorString", errorString);
		request.setAttribute("dbFileList", list);
		
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/showFilesList.jsp");
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}
