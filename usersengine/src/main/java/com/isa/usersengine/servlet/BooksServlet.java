package com.isa.usersengine.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.isa.usersengine.domain.User;


@WebServlet(name="BooksServlet", urlPatterns = {"/books"})
public class BooksServlet extends HttpServlet {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {

    	User user = new User();
    	user.setId(1L);
    	user.setLogin("pikej");
    	user.setName("piotr");
    	user.setPassword("secret-password");
      	
    	

    	
    	response.setContentType("application/json");
    	response.setHeader("Access-Control-Allow-Origin", "*"); // this fix CORS issue on Chrome
    	response.setCharacterEncoding("UTF-8");
    	response.setStatus(HttpServletResponse.SC_OK);
    	
    	PrintWriter out = response.getWriter();
    	out.print(new Gson().toJson(user));
    	out.flush();
    }
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		PrintWriter out = response.getWriter();
		
		final StringBuilder builder = new StringBuilder();
	    try (BufferedReader reader = request.getReader()) {
	        if (reader == null) {
	            out.println("Request body could not be read because it's empty.");
	           
	        }
	        String line;
	        while ((line = reader.readLine()) != null) {
	            builder.append(line);
	        }
	        
	        Object data = new Gson().fromJson(builder.toString(), Object.class);
	        	        
	        
	    	response.setContentType("application/json");
	    	response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8080/usersengine/books"); // this fix CORS issue on Chrome
//	    	response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
//	    	response.setHeader("Access-Control-Allow-Methods", "POST,GET");
//	    	response.setHeader("Content-Type", "application/json");
//	    	response.setHeader("Accept", "application/json");
//	    	response.setCharacterEncoding("UTF-8");
	    	response.setStatus(HttpServletResponse.SC_OK);
	    	

	    	out.print(new Gson().toJson(data));
	    	out.flush();
	        
	    } catch (final Exception e) {
	        out.println("Could not obtain the saml request body from the http request");
	        out.flush();
	    }
	}
	
	 private void addCorsHeader(HttpServletResponse response){
	        //TODO: externalize the Allow-Origin
	        response.addHeader("Access-Control-Allow-Origin", "*");
	        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
	        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
	        response.addHeader("Access-Control-Max-Age", "1728000");
	    }
	 
	  private void setAccessControlHeaders(HttpServletResponse resp) {
	      resp.setHeader("Access-Control-Allow-Origin", "http://localhost:9000");
	      resp.setHeader("Access-Control-Allow-Methods", "GET");
	  }
}
