package de.hwr.fims_backend.dbconnector;

import java.sql.*;

import de.hwr.fims_backend.controller.LoginController;

public class DatabaseConnector {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/fimsdatabase?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    static final String USER = "root";
    static final String PASS = "";

    public static ResultBoolean dbConnection(String sql) {
    	ResultBoolean result = new ResultBoolean();
    	Connection conn;
    	Statement stmt;
    	 try {
             System.out.println("Connecting to database...");
             conn = DriverManager.getConnection(DB_URL,USER,PASS);
             
             stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);
             
             if(rs.next()) {
                 result.setSuccessful(true);
             } else {
                 result.setSuccessful(false);
                 result.setMessage("Keine Einträge gefunden!");
             }

         } catch (SQLException e) {
             e.printStackTrace();
         }
		return result;
    }

}
