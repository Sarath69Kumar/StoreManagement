package org.sm.servletAdOn;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;

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

@WebServlet(urlPatterns = {"/getSelectedFile"})
public class DownloadFileFromDB extends HttpServlet
{
	private static final long serialVersionUID=1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			HttpSession session = request.getSession();
			UserAccount loginedUser = MyUtils.getLoginedUser(session);
			Long userId = loginedUser.getUserId();
			Connection conn = MyUtils.getStoredConnection(request);
			
			Long fileId = Long.parseLong(request.getParameter("fileID"));
			Attachment attachment = DBUtils.downloadFileFromDB(conn, userId, fileId);
			if(attachment == null)
			{
				response.getWriter().write("No data found");
				return;
			}
			// file1.zip, file2.zip
			String fileName = attachment.getFileName();
			System.out.println("File Name : "+fileName);
			
			// abc.txt => text/plain
	        // abc.zip => application/zip
	        // abc.pdf => application/pdf
			String contentType = this.getServletContext().getMimeType(fileName);
			System.out.println("Content Type : "+contentType);
			
			response.setHeader("Content-Type", contentType);
			
			response.setHeader("Content-Length", String.valueOf(attachment.getBlbFile().length()));
			
			response.setHeader("Content-Disposition", "inline;filename=\""+attachment.getFileName()+"\"");
			
			//For big blob data
			Blob fileData = attachment.getBlbFile();
			InputStream is = fileData.getBinaryStream();
			
			byte[] bytes = new byte[1024];
			int bytesRead;
			
			while((bytesRead = is.read(bytes))!= -1)
			{
				//Write image data to response.
				response.getOutputStream().write(bytes, 0, bytesRead);
			}
			is.close();
		}
		catch(Exception e)
		{
			throw new ServletException(e);
		}
	}
}
