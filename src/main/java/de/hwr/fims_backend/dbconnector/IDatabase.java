package de.hwr.fims_backend.dbconnector;

public interface IDatabase {

    ResultBoolean pruefeLogin(String benutzername, String passwort);


}
