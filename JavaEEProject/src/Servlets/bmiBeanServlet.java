package Servlets;

import Helpers.DB_Manager;
import JavaBeans.calcBMIBean;

import javax.ejb.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "bmiBeanServlet", urlPatterns = "/bmiBeanServlet")
public class bmiBeanServlet extends HttpServlet {
    private DB_Manager db_manager = new DB_Manager();

    @EJB
    private calcBMIBean calcBean;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("name");
        float userWeight = Float.parseFloat(request.getParameter("weight"));
        float userHeight = Float.parseFloat(request.getParameter("height")) * 0.01f;
        String userDate = request.getParameter("date");

        float bmi = calcBean.calcBMI(userWeight, userHeight);

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
