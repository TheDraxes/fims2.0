package de.hwr.fims_backend.dbconnector;

import java.util.ArrayList;

import de.hwr.fims_backend.data.customerdata.Auftrag;

public interface IDatabase {

    ResultBoolean pruefeLogin(String benutzername, String passwort);
    
    public ArrayList<Auftrag> getAuftraegeFromDatabase();
    public boolean insertAuftragToDatabase(Auftrag auftrag);
    public boolean insertUserToDatabase(String username, String password);
    
    
    /**
     * 
     * Funktionen um die dropdowns zu füllen
     */
    public String[] getUniqueKonfessionen();
    public String[] getUniqueFamilienstaende();
    public String[] getUniqueKrankenkassen();
    public String[] getUniqueRentenverischerungen();
    //Art der Zeitungsanzeige
    public String[] getUniqueAnzeigenart();
    //Zeitungen
    public String[] getUniqueZeitung();

}
