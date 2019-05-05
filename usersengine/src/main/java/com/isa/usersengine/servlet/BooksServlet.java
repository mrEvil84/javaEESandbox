package com.isa.usersengine.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
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
                out.flush();

            }
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }


            Gson gson = new Gson();
            JsonReader jsonReader = new JsonReader(new StringReader(builder.toString()));
            jsonReader.setLenient(true);
            Object data = gson.fromJson(jsonReader, Object.class);


            //            response.addHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8080/usersengine/books"); // this fix CORS issue on Chrome
            //            response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8080/usersengine/books");

            //response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            //            response.setHeader("Access-Control-Max-Age", "3600");
            //            response.setHeader("Access-Control-Allow-Headers", "x-requested-with");

            //	    	response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
            //	    	response.setHeader("Access-Control-Allow-Methods", "POST,GET");
            //	    	response.setHeader("Content-Type", "application/json");
            //	    	response.setHeader("Accept", "application/json");
            //	    	response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-type", "application/json");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with ");
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);

            PrintWriter out2 = response.getWriter();
            out2.print(new Gson().toJson(data));
            out2.flush();


        } catch (final Exception e) {

            PrintWriter out3 = response.getWriter();
            response.setHeader("Content-type", "application/json");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with ");
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);

            out3.println(e.toString());
            out3.println(e.getMessage());
            e.printStackTrace();

            out.flush();
            out3.flush();
        }
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        //super.doOptions(req, resp);
        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin", "*"); // this fix CORS issue on Chrome
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);

        PrintWriter out = response.getWriter();
        out.print("doOptions");
        out.flush();

    }



//    private void addCorsHeader(HttpServletResponse response){
//            //TODO: externalize the Allow-Origin
//            response.addHeader("Access-Control-Allow-Origin", "*");
//            response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
//            response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
//            response.addHeader("Access-Control-Max-Age", "1728000");
//        }

//      private void setAccessControlHeaders(HttpServletResponse resp) {
//          resp.setHeader("Access-Control-Allow-Origin", "http://localhost:9000");
//          resp.setHeader("Access-Control-Allow-Methods", "GET");
//      }
}
