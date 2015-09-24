package com.example.sendgrid_java_example;

import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	SendGrid sendgrid = new SendGrid("user", "pass");

    	SendGrid.Email email = new SendGrid.Email();
    	email.addTo("example.com");
    	email.setFrom("example@gmail.com");
    	email.setSubject("Hi Jason. This is our first message together");
    	email.setText("Just trying out sendgrid, what do you think of the response time?\n."
    			+ "This should be a new line. \t.This should be a break.");
    	email.setHtml("This should be a line. <b>This should be bold</b>. This should be a break <br><br>");
    	try {
    	  SendGrid.Response response = sendgrid.send(email);
    	  System.out.println(response.getCode());
    	} catch (SendGridException e) {
    	  System.out.println(e);
    	}
    }
}
