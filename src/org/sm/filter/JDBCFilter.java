package org.sm.filter;

import java.io.IOException;
import java.sql.Connection;
import java.util.Collection;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.sm.conn.ConnectionUtils;
import org.sm.utils.MyUtils;


@WebFilter(filterName = "jdbcFilter", urlPatterns = {"/*"})
public class JDBCFilter implements Filter
{
	public JDBCFilter()
	{
	}
	
	@Override
	public void init(FilterConfig fConfig) throws ServletException
	{
	}
	
	@Override
	public void destroy()
	{
	}
	
	private boolean needJDBC(HttpServletRequest request)
	{
		
		/**
		 * Servlet URL-Pattern: /spath/*
		 * 
		 * =>/spath
		 */
		String servletPath = request.getServletPath();
		//=> /abc/mnp
		String pathInfo = request.getPathInfo();
		
		String contextPath = request.getContextPath();
		
		String urlPattern = servletPath;
		
		if(pathInfo != null)
		{
			//=> /spath/*
			urlPattern =servletPath + "/*";
		}
		
		System.out.println("JDBCFilter :: needJDBC ::  servletPath ="+servletPath+"  pathInfo="+pathInfo+"  urlPattern = "+urlPattern+" contextPath="+contextPath );
			
		//Key:servletName
		//Value: ServletRegistration
		Map<String, ? extends ServletRegistration > servletRegistrations = request.getServletContext().getServletRegistrations();
		
		//Collection of all servlet in your webapp
		Collection<? extends ServletRegistration> values = servletRegistrations.values();
		for(ServletRegistration sr : values)
		{
			Collection<String> mappings = sr.getMappings();
			if(mappings.contains(urlPattern))
			{
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		System.out.println("JDBCFilter :: doFilter");
		HttpServletRequest req = (HttpServletRequest) request;
		
		/**
		 * Only open connections for the special request.
		 * (For example, the path to the servlet, JSP,..)
		 * 
		 * Avoid open connection for commons request
		 * (For example: images, css, javascript)
		 */
		
		if(this.needJDBC(req))
		{
			System.out.println("Open connection for: "+ req.getServletPath());
			Connection conn = null;
			try
			{
				//Create a connection
				conn = ConnectionUtils.getConnection();
				//Set auto commit to false.
				conn.setAutoCommit(false);
				
				//Store Connection object in attribute of request.
				MyUtils.storeConnection(request, conn);
				
				/**
				 * Allow request to go forword
				 * (Go to the next filter or target)
				 */
				chain.doFilter(request, response);
			
				//Invoke the commit() method to complete the transaction with the DB.
				conn.commit();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				ConnectionUtils.rollbackQuietly(conn);
				throw new ServletException();
			}
			finally
			{
				ConnectionUtils.closeQuietly(conn);
			}
		}
		else
		{
			//Allow request to go forward
			//(Go to the next filter or target)
			chain.doFilter(request, response);
		}
	}
}
