package com.StokTakip.Util;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class BasicAuthentication { 
   public static Hashtable<String,String> validUsers = new Hashtable<>();
    
    public static void loggedIn(String mail,String password) {
          
        validUsers.put(mail+password,"authorized");
    }
    
    public static boolean auth(HttpServletRequest req)
                    throws ServletException, IOException {
        String auth = req.getHeader("Authorization");
        if (!allowUser(auth)) {
         return false;
        } else {
          return true;
        }
    }
    
    protected static boolean allowUser(String auth) throws IOException {
         
        if (auth == null) {
            return false;
        }
        String[] credentials=Base64Token.decode(auth);
        if ("authorized".equals(validUsers.get((credentials[0]+credentials[1])))) {
            return true;
        } else {
            return false;
        }
    }
}