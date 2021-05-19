package org.sm.redirect301;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class Redirect301Filter implements Filter
{
	public Redirect301Filter()
	{
		
	}
	
	@Override
	public void destroy()
	{
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
	throws IOException, ServletException
	{
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		//Incoming URL
		String url = request.getRequestURL().toString();
		System.out.println("Incomming URL = "+url);
		
		Map<String, String> redirect301Map = DataUtils.getRedirect301Map();
		
		//Find new URL
		String newUrl = redirect301Map.get(url);
		
		if(newUrl != null)
		{
			//Redirect 301
			response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
			response.setHeader("Location", newUrl);
			return;
		}
		chain.doFilter(req, res);
	}
	
	@Override
	public void init(FilterConfig fConfig) throws  ServletException
	{
	}
}
