package org.sm.redirect301;

import java.util.HashMap;
import java.util.Map;

public class DataUtils 
{
	private static Map<String, String> redirect301Map;
	
	public static Map<String, String> getRedirect301Map()
	{
		if(redirect301Map == null)
		{
			redirect301Map = new HashMap<String, String>();
			
			redirect301Map.put("http://localhost:8080/StoreManagement/oldURL",
					"http://localhost:8080/StoreManagement/newURL");
			
			redirect301Map.put("http://localhost:8080/StoreManagement/sweetHome", 
					"http://localhost:8080/StoreManagement");
		}
		return redirect301Map;
	}
}
