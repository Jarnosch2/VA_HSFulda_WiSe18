package Helpers;

import java.sql.*;
import javax.sql.*;

public class DB_Manager {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String CONN_STRING = "jdbc:mysql://localhost/VerteilteAnwendungen";

    private static Connection conn;

    private String getEntryString = "SELECT * FROM bmi_entries WHERE name = ?";
    private String addEntryString = "INSERT INTO bmi_entries (name, weight, height, bmi, date) values (?,?,?,?,?)";

    public DB_Manager() {
        try{
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);

            if(conn.isClosed()){
               System.err.println("Connection to databse failed!");
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public String getEntry(String inputName) {
        try {
            PreparedStatement stmt = conn.prepareStatement(getEntryString);
            stmt.setString(1, inputName);
            ResultSet result = stmt.executeQuery();
            String answerString = "";
            while(result.next()) {
                answerString += "Name: " + result.getString("name") +
                                "; Weight: " + result.getFloat("weight") +
                                "; Height: " + result.getFloat("height") +
                                "; BMI: " + result.getFloat("bmi") +
                                "; Date: " + result.getString("date") + "<br>";
            }
            return answerString;
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }

        return "";
    }

    public void addEntry(String name, float weight, float height, float bmi, String date) {
        try {
            PreparedStatement stmt = conn.prepareStatement(addEntryString);
            stmt.setString(1, name);
            stmt.setFloat(2, weight);
            stmt.setFloat(3, height);
            stmt.setFloat(4, bmi);
            stmt.setString(5, date);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
