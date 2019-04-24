package de.hwr.fims_backend.dbconnector;

import java.sql.*;

public class DatabaseConnector implements IDatabase {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/fimsdatabase?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    static final String USER = "root";
    static final String PASS = "fims1234";

    Connection conn;

    public DatabaseConnector() {
        try {
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //System.out.println("Creating statement...");
            //Statement stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public ResultBoolean pruefeLogin(String benutzername, String passwort) {
        ResultBoolean result = new ResultBoolean();
        String abfrage = null;

        try {
            Statement stmt = conn.createStatement();

            abfrage = "SELECT * FROM benutzer WHERE Benutzername = '" + benutzername + "' AND Passwort = '" + passwort + "';";
            ResultSet rs = stmt.executeQuery(abfrage);
            if(rs.next()) {
                result.setSuccessful(true);
            } else {
                result.setSuccessful(false);
                result.setMessage("Falsche Logindaten!");
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            result.setMessage("Syntaxfehler!");
        }
        return result;
    }
}
