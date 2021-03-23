package com.trottling.Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class SMSCallApi {

	public String sendSMS(String mobileNumber, String message){
		String postData="";
		String retval = "";
		try{
		
		message = message.replace("\n", " ");
		postData ="{\"msg\":\""+message+"\",\"mobileNo\":\""+mobileNumber+"\"}"; 
		URL url = new URL("http://bss.apsfl.co.in/coms/sendSMS/");
		HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
		urlconnection.setRequestMethod("POST");
		urlconnection.setRequestProperty("content-type", "application/json");
		urlconnection.setRequestProperty("authorization", "Basic YWRtaW46YWRtaW4=");
		urlconnection.setDoOutput(true);
		OutputStreamWriter out = new OutputStreamWriter(urlconnection.getOutputStream());
		out.write(postData);
		out.close();
		BufferedReader in = new BufferedReader( new InputStreamReader(urlconnection.getInputStream()));
		String decodedString;
		while ((decodedString = in.readLine()) != null) {
			retval += decodedString;
		}
		System.out.println("sms to "+mobileNumber+" msg "+message+"-"+retval);
		in.close();
		}
		catch (Exception e) {
			System.out.println("Error in sendSMS "+e.getMessage());
			System.out.println(postData);
		}
		
		return retval;
	}
	
}
