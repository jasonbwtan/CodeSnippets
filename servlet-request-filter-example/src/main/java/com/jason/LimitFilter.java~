package com.jason;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LimitFilter implements Filter {
    private int limit = 2;
    //private int count;
    private Object lock = new Object();
    private AtomicInteger count = new AtomicInteger();

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
    	System.out.println("In Limit Filter");

    	try {
    		count.incrementAndGet();
            boolean ok;
//            synchronized (lock) {
//                ok = count++ < limit;
//            }
            if (count.get() <= limit) {
                // let the request through and process as usual
                chain.doFilter(request, response);
            } else {
            	response.setContentType("application/json");
            	// Get the printwriter object from response to write the required json object to the output stream      
            	PrintWriter out = response.getWriter();
            	// Assuming your json object is **jsonObject**, perform the following, it will return your json object  
            	out.print("Too many concurrent requests");
            	out.flush();
            	System.out.println("error'ed");
            }
        } finally {
//            synchronized (lock) {
//                count--;
//            }           
        }
    }

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	      // Get init parameter 
	      String testParam = arg0.getInitParameter("testparam"); 

	      //Print the init parameter 
	      System.out.println("Test Param: " + testParam); 
	}
}