package de.hwr.fims_backend.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.hwr.fims_backend.dbconnector.DatabaseConnector;
import de.hwr.fims_backend.dbconnector.ResultBoolean;

public class LoginController {
	
    public ResultBoolean pruefeLogin(String benutzername, String passwort) {
        String sql = null;
        ResultBoolean result = new ResultBoolean();
        
        sql = "SELECT * FROM benutzer WHERE Benutzername = '" + benutzername + "' AND Passwort = '" + passwort + "';";
        result = DatabaseConnector.dbConnection(sql);
        
        return result;
        
    }

}
