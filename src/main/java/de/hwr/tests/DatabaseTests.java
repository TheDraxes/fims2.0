package de.hwr.tests;

import de.hwr.fims_backend.data.customerdata.Auftrag;
import de.hwr.fims_backend.dbconnector.DatabaseConnector;
import de.hwr.fims_backend.dbconnector.ResultBoolean;

import java.util.ArrayList;

public class DatabaseTests {
    public static void main(String[] args) {
        DatabaseConnector datenbank = new DatabaseConnector();

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

        //datenbank.insertUserToDatabase("test345", "abcd123");

        ArrayList<Auftrag> auftraege = datenbank.getAuftraegeFromDatabase();
        for(int i = 0; i < auftraege.size(); i++) {
            System.out.println("NiederL = " + auftraege.get(i).getNiederL());
            System.out.println("TelNr = " + auftraege.get(i).getAuftraggeber().getTelefonNr());
            System.out.println("Name = " + auftraege.get(i).getAuftraggeber().getName());
            System.out.println("ZahlD = " + auftraege.get(i).getZahlDatum());
            System.out.println("plu = " + auftraege.get(i).getAuftraggeber().getPlz());
            System.out.println("ort = " + auftraege.get(i).getAuftraggeber().getOrt());
        }
    }
}
