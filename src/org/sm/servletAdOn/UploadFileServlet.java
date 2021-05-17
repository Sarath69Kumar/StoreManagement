package org.sm.servletAdOn;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/uploadFile")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,	//2MB
				maxFileSize = 1024 * 1024 * 10,			//10MB
				maxRequestSize = 1024 * 1024 * 50)		//50MB
public class UploadFileServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	public static final String SAVE_DIRECTORY = "UPLOAD_DIR";
	
	public UploadFileServlet()
	{
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/uploadFile.jsp");
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			String description = request.getParameter("description");
			System.out.println("Description: " + description);
			
			//Get absolute path to root directory of web app
			String appPath = request.getServletContext().getRealPath("");
			appPath = appPath.replace('\\', '/');
			
			//The directory to save upload file
			String fullSavePath = null;
			if(appPath.endsWith("/"))
			{
				fullSavePath = appPath + SAVE_DIRECTORY;
			}
			else
			{
				fullSavePath = appPath + "/" + SAVE_DIRECTORY;
			}
			
			//Create the save directory if it does not exists
			File fileSaveDir = new File(fullSavePath);
			if(!fileSaveDir.exists())
			{
				fileSaveDir.mkdir();
			}
			
			//Part list (multi files)
			for(Part part : request.getParts())
			{
				String fileName = extractFileName(part);
				if(fileName != null && fileName.length() > 0)
				{
					String filePath = fullSavePath + File.separator + fileName;
					System.out.println("Write attachment to file : "+filePath);
					//Write to file
					part.write(filePath);
				}
			}
			
			//Upload successfully!
			response.sendRedirect(request.getContextPath() + "/uploadFileResults");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			request.setAttribute("errorMessage", "Error: " + e.getMessage());
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/uploadFile.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	private String extractFileName(Part part)
	{
		//form-data; name="file"; filename = "C:\file1.zip"
		//form-data; name="file"; filename = "C:\Note\file2.zip"
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for(String s : items)
		{
			if(s.trim().startsWith("filename"))
			{
				//C:\file1.zip
				//C:\Note\file2.zip
				String clientFileName = s.substring(s.indexOf("=")+2, s.length() -1);
				clientFileName=clientFileName.replace("\\", "/");
				int i = clientFileName.lastIndexOf('/');
				//file1.zip
				//file2.zip
				return clientFileName.substring(i + 1);
			}
		}
		return null;
	}
}
