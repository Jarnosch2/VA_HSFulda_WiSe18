package Servlets;

import Helpers.DB_Manager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "getEntriesForName", urlPatterns = "/getEntriesForName")
public class getEntriesForName extends HttpServlet {

    private DB_Manager db_manager = new DB_Manager();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("name");
        String result = db_manager.getEntry(userName);

        String htmlResponse = "<html>";
        htmlResponse += result;
        htmlResponse += "</html>";
        PrintWriter writer = response.getWriter();
        writer.write(htmlResponse);
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
