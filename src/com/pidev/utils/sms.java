/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author infoevo
 */
public class sms {
    // Find your Account Sid and Auth Token at twilio.com/console
    public static final String ACCOUNT_SID =
            "AC06acda3059c56385a0e757c661537867";
    public static final String AUTH_TOKEN =
            "3c78a2942f88e3876afb2d6822cb5c27";


    public void send(String s,String x){
         Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
      String y="+216"+x;
        Message message = Message 
                .creator(new PhoneNumber(y), // to
                        new PhoneNumber("+14157631994"), // from
                       ""+s)
                .create();
  System.out.println("aaslema");
        System.out.println(message.getSid());
    }
  
}
