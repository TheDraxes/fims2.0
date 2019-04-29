package de.hwr.tests;

import de.hwr.fims_backend.controller.LoginController;
import de.hwr.fims_backend.dbconnector.DatabaseConnector;
import de.hwr.fims_backend.dbconnector.ResultBoolean;

public class DatabaseTests {
    public static void main(String[] args) {
       LoginController datenbank = new LoginController();

        /*String[] testKonfession = datenbank.getUniqueKonfessionen();

        for(int i = 0; i < testKonfession.length; i++) {
            System.out.println(testKonfession[i]);
        }*/

        /*String[] testFamilienstand = datenbank.getUniqueFamilienstaende();

        for(int i = 0; i < testFamilienstand.length; i++) {
            System.out.println(testFamilienstand[i]);
        }*/

        /*String[] testOrte = datenbank.getUniqueOrte();

        for(int i = 0; i < testOrte.length; i++) {
            System.out.println(testOrte[i]);
        }*/

        datenbank.insertUserToDatabase("test345", "abcd123");
    }
}
