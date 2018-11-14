package Servlets;

import Helpers.DB_Manager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "bmiRechner", urlPatterns = "/bmiRechner")
public class bmiRechner extends HttpServlet {

    private DB_Manager db_manager = new DB_Manager();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("name");
        float userWeight = Float.parseFloat(request.getParameter("weight"));
        float userHeight = Float.parseFloat(request.getParameter("height")) * 0.01f;
        String userDate = request.getParameter("date");

        float bmi = userWeight / (userHeight * userHeight);

        db_manager.addEntry(userName, userWeight, userHeight, bmi, userDate);

        String logEntry =   "Name: " + userName + ";\n" +
                            "Weight: " + userWeight + "kg;\n" +
                            "Height: " + userHeight + "m;\n" +
                            "Date: " + userDate + ";\n" +
                            "BMI: " + bmi + "\n";
        ServletContext sc = getServletContext();
        File logFile = new File(sc.getRealPath("miscellaneous/bmiLog.txt"));
        FileOutputStream fout = new FileOutputStream(logFile);
        fout.write(logEntry.getBytes());
        fout.close();

        String htmlResponse = "<html><h3>Your BMI is " + bmi + "</h3></html>";
        PrintWriter writer = response.getWriter();
        writer.write(htmlResponse);
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("html/bmiForm.html");
        view.forward(request, response);
    }
}
