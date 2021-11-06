package com.StokTakip.Util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;


public class Base64Token {

	
	public static String encode(String email,String password) {
		String  auth= email + ":" + password;
		String token = Base64.getEncoder().encodeToString(auth.getBytes());
		String authHeader = "Basic " + token;
		return authHeader ;
	}
	
	public static String[] decode(String token) {
		String[] result=new String[2];
		result=token.split("=");
		result=result[0].split(" ");
		System.out.println(result[1]);
		byte[] decodedImg = Base64.getDecoder().decode(result[1].getBytes(StandardCharsets.UTF_8));
		String decodedString= new String(decodedImg);
		System.out.println("decoded:"+decodedString);
		result=decodedString.split(":");
		System.out.println(result[0]);
		System.out.println(result[1]);
		return result;
		
	}
	
	
	
	
}
