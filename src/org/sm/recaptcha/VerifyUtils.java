package org.sm.recaptcha;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;

public class VerifyUtils 
{
	public static final String SITE_VERIFY_URL =  "https://www.google.com/recaptcha/api/siteverify";
	
	public static boolean verify(String gRecaptchaResponse)
	{
		if(gRecaptchaResponse == null || gRecaptchaResponse.length() == 0)
		{
			return false;
		}
		try
		{
			URL verifyURL = new URL(SITE_VERIFY_URL);
			
			//Open a Connection to URL above.
			HttpsURLConnection htpUrlConn = (HttpsURLConnection) verifyURL.openConnection();
			
			//Add the Header information to the Request to prepare send to the server.
			htpUrlConn.setRequestMethod("POST");
			htpUrlConn.setRequestProperty("User-Agent", "Mozilla/5.0");
			htpUrlConn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			
			//Data will be sent to the server
			String postParams = "secret="+MyConstants.SECRET_KEY+"&response="+gRecaptchaResponse;
			
			//Send request
			htpUrlConn.setDoOutput(true);
			
			//Get the output stream of connection
			//Writen data in the stream, which means to send data to server.
			OutputStream outStream = htpUrlConn.getOutputStream();
			outStream.write(postParams.getBytes());
			
			outStream.flush();
			outStream.close();
			
			//Response code return from server
			int responseCode = htpUrlConn.getResponseCode();
			System.out.println("responseCode = "+responseCode);
			
			//Get the input Stream of connection to read data sent from the server
			InputStream is = htpUrlConn.getInputStream();
			
			JsonReader jsonReader = Json.createReader(is);
			JsonObject jsonObject = jsonReader.readObject();
			jsonReader.close();
			
			//==>{"success":true}
			System.out.println("Response: "+jsonObject);
			
			boolean success = jsonObject.getBoolean("success");
			return success;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
