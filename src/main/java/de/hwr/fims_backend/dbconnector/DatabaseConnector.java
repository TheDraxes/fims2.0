package de.hwr.fims_backend.dbconnector;

import java.sql.*;
import java.util.ArrayList;


import de.hwr.fims_backend.controller.LoginController;
import de.hwr.fims_backend.data.customerdata.Auftrag;


public class DatabaseConnector implements IDatabase {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/fimsdatabase?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    static final String USER = "root";
    static final String PASS = "fims1234";

    Connection conn;
    
    public ResultBoolean dbConnection(String sql) {
    	ResultBoolean result = new ResultBoolean();
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


	@Override
	public ArrayList<Auftrag> getAuftraegeFromDatabase() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean insertAuftragToDatabase(Auftrag auftrag) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean insertUserToDatabase(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public String[] getUniqueKonfessionen() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String[] getUniqueFamilienstaende() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String[] getUniqueKrankenkassen() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String[] getUniqueRentenverischerungen() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String[] getUniqueAnzeigenart() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String[] getUniqueZeitung() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String[] getUniqueOrte() {
		// TODO Auto-generated method stub
		return null;
	}
}
