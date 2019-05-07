package com.isa.usersengine.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import javax.servlet.ServletException;
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

        HttpServletResponse resp = this.setResponseHeaders(response);

        final StringBuilder builder = new StringBuilder();

        try (BufferedReader reader = request.getReader()) {

            if (reader == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                this.printResponse(resp, "Request body could not be read because it's empty.");
            }

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            Gson gson = new Gson();
            JsonReader jsonReader = new JsonReader(new StringReader(builder.toString()));
            jsonReader.setLenient(true);
            Object data = gson.fromJson(jsonReader, Object.class);


            resp.setStatus(HttpServletResponse.SC_OK);
            this.printResponse(resp, gson.toJson(data));
        } catch (final Exception e) {

            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            this.printResponse(resp, e.toString());
            this.printResponse(resp, e.getMessage());
        }
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        this.printResponse(this.setResponseHeaders(response), "doOptions");
    }

    private HttpServletResponse setResponseHeaders(HttpServletResponse response) {
        response.setHeader("Content-type", "application/json");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with ");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        return response;
    }

    private void printResponse(HttpServletResponse response, String data) throws  IOException{
        PrintWriter out3 = response.getWriter();
        out3.println(data);
        out3.flush();
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
