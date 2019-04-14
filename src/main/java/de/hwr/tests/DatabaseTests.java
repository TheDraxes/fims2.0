package de.hwr.tests;

import de.hwr.fims_backend.dbconnector.DatabaseConnector;
import de.hwr.fims_backend.dbconnector.ResultBoolean;

public class DatabaseTests {
    public static void main(String[] args) {
        DatabaseConnector datenbank = new DatabaseConnector();

        if(datenbank.pruefeLogin("admin", "12345").isSuccessful()) {
            System.out.println("Login erfolgreich!");
        } else
            System.out.println("Falsche Logindaten!");

        if(datenbank.pruefeLogin("admin", "512142").isSuccessful()) {
            System.out.println("Login erfolgreich!");
        } else
            System.out.println("Falsche Logindaten!");
    }
}
