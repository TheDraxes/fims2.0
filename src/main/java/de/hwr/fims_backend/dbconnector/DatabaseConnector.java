package de.hwr.fims_backend.dbconnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hwr.fims_backend.controller.LoginController;
import de.hwr.fims_backend.data.TabData;
import de.hwr.fims_backend.data.advertisement.Zeitungsanzeige;
import de.hwr.fims_backend.data.customerdata.Auftrag;
import de.hwr.fims_backend.data.customerdata.Auftraggeber;
import de.hwr.fims_backend.data.customerdata.Verstorbener;


/**
 * @author Darius Mix, Nguyen Tien Dung Otten
 *
 */
public class DatabaseConnector implements IDatabase {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//    static final String DB_URL = "jdbc:mysql://127.0.0.1/fimsdatabase?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
//    static final String USER = "fims";
//    static final String PASS = "fims";
    
    // Lokal
    static final String DB_URL = "jdbc:mysql://localhost/fimsdatabase?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "";
    
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
        	Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //System.out.println("Creating statement...");
            //Statement stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


    @Override
    public ResultBoolean pruefeLogin(String benutzername, String passwort) {
        ResultBoolean result = new ResultBoolean();
        String sql;

        try {
            Statement stmt = conn.createStatement();

			sql = "SELECT * FROM benutzer WHERE Benutzername = '" + benutzername + "' AND Passwort = '" + passwort + "';";
            ResultSet rs = stmt.executeQuery(sql);
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

    // Fetch data to fill in table on SFVMainPage
    @Override
	public ArrayList<TabData> getDataForTable() {
		// TODO Auto-generated method stub
		ArrayList<TabData> tabdataRS = new ArrayList<TabData>();
		String sql;
		
		try {
			Statement stmt = conn.createStatement();
			
			sql = "SELECT ID_auftrag, name, vorname, todesdatum "
					+ "FROM auftraege "
					+ "INNER JOIN verstorbene "
					+ "ON auftraege.verstorbene_ID = verstorbene.ID_verstorbene";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int ID_auftrag = rs.getInt("ID_auftrag");
				String name = rs.getString("name");
				String vorname = rs.getString("vorname");
				Date todesdatum = rs.getDate("todesdatum");
				
				tabdataRS.add(new TabData(ID_auftrag, vorname, name, todesdatum));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tabdataRS;
		
	}
    
    // Covers 'Auftrag' and 'Verstorbener' for testing purposes only, other data currently missing 
	@Override
	public ArrayList<Auftrag> getAuftraegeFromDatabase() {
		ArrayList<Verstorbener> verstorbenerRS = new ArrayList<Verstorbener>();
		ArrayList<Auftraggeber> auftraggeberRS = new ArrayList<Auftraggeber>();
		ArrayList<Auftrag> auftragRS = new ArrayList<Auftrag>();
		
		Statement stmtVerstorbene;
		Statement stmtAuftraggeber;
		Statement stmtAuftrag;
		
		String sql1; // Select Verstorbene
		String sql2; // Select Auftraggeber
		String sql3; // Select Auftraege
		
		try {
			stmtVerstorbene = conn.createStatement();
			stmtAuftraggeber = conn.createStatement();
			stmtAuftrag = conn.createStatement();
			
			sql1 = "SELECT ID_verstorbene, geschlecht, name, vorname, plz, ort_ID, strasse, hausNr, "
					+ "geburtsdatum, geburtsort_ID, todesdatum, todesort_ID, familienstand, anz_sohn, "
					+ "anz_tochter, beruf, konfession, krankenkasse, rentenvers "
					+ "FROM verstorbene;";
			sql2 = "SELECT ID_auftraggeber, geschlecht, name, vorname, plz, ort_ID, strasse, hausNr, beziehungsart, telefonNr "
					+ "FROM auftraggeber;";
			sql3 = "SELECT ID_auftrag, niederlassung, rechnungsdatum, zahlungsdatum "
					+ "FROM auftraege;";
			
			ResultSet rs1 = stmtVerstorbene.executeQuery(sql1);
			ResultSet rs2 = stmtAuftraggeber.executeQuery(sql2);
			ResultSet rs3 = stmtAuftrag.executeQuery(sql3);
			
			// Retrieve columns in 'verstorbene' by name 
			while(rs1.next()) {
				int ID_verstorbene = rs1.getInt("ID_Verstorbene");
//				boolean geschlecht = rs1.getBoolean("geschlecht"); // enum to boolean???
				String name = rs1.getString("name");
				String vorname = rs1.getString("vorname");
				String plz = rs1.getString("plz");				
				
				int ort_ID = rs1.getInt("ort_ID"); 
				String ort_String = "";
				switch(ort_ID) {
					case 1: 
						ort_String = "Schwerin";
						break;
					case 2:
						ort_String = "Berlin";
						break;
					case 3:
						ort_String = "Rostock";
						break;
				}

				String strasse = rs1.getString("strasse");
				String hausNr = rs1.getString("hausNr");
				String beruf = rs1.getString("beruf");
				Date geburtsdatum = rs1.getDate("geburtsdatum");
				
				int geburtsort_ID = rs1.getInt("geburtsort_ID"); 
				String geburtsort_String = "";
				switch(geburtsort_ID) {
					case 1: 
						geburtsort_String = "Schwerin";
						break;
					case 2:
						geburtsort_String = "Berlin";
						break;
					case 3:
						geburtsort_String = "Rostock";
						break;
				}
				
				Date todesdatum = rs1.getDate("todesdatum");
				
				int todesort_ID = rs1.getInt("todesort_ID");
				String todesort_String = "";
				switch(todesort_ID) {
					case 1: 
						todesort_String = "Schwerin";
						break;
					case 2:
						todesort_String = "Berlin";
						break;
					case 3:
						todesort_String = "Rostock";
						break;
				}
				
				String familienstand = rs1.getString("familienstand");
				int anz_sohn = rs1.getInt("anz_sohn");
				int anz_tochter = rs1.getInt("anz_tochter");
				String konfession = rs1.getString("konfession");
				String krankenkasse = rs1.getString("krankenkasse");
				String rentenvers = rs1.getString("rentenvers");
				
				// sex default value
				verstorbenerRS.add(new Verstorbener(false, name, vorname, plz, ort_String, strasse, hausNr, beruf, null, geburtsdatum, geburtsort_String, todesdatum, todesort_String, familienstand, anz_sohn, anz_tochter, konfession, krankenkasse, rentenvers));
			}
			
			// Retrieve column in 'auftraggeber' by name
			while(rs2.next()) {
				int ID_auftraggeber = rs2.getInt("ID_auftraggeber");
//				boolean geschlecht = rs2.getBoolean("geschlecht"); // enum to boolean???
				String name = rs2.getString("name");
				String vorname = rs2.getString("vorname");
				String plz = rs2.getString("plz");				
				
				int ort_ID = rs2.getInt("ort_ID"); 
				String ort_String = "";
				switch(ort_ID) {
					case 1: 
						ort_String = "Schwerin";
						break;
					case 2:
						ort_String = "Berlin";
						break;
					case 3:
						ort_String = "Rostock";
						break;
				}

				String strasse = rs2.getString("strasse");
				String hausNr = rs2.getString("hausNr");
				String beziehungsart = rs2.getString("beziehungsart");
				String telefonNr = rs2.getString("telefonNr");
				// 'beruf' is missing
				
				// sex default value
				auftraggeberRS.add(new Auftraggeber(false, name, vorname, plz, ort_String, strasse, hausNr, null, beziehungsart, telefonNr));
			}
			
			// Retrieve columns in 'auftraege' by name
			int countRows = 0;	
			while(rs3.next()) {
				int ID_auftrag = rs3.getInt("ID_auftrag");
				String niederlassung = rs3.getString("niederlassung");
				Date rechnDatum = rs3.getDate("rechnungsdatum");
				Date zahlDatum = rs3.getDate("zahlungsdatum");
//				System.out.println(countRows);
				countRows++;
				
				// Only works if the relation between 'Auftraege and 'Verstorbene' is 1:1
				// Using default value for auftraggeber row -> 1:1 relation between 'Auftraege' and 'Verstorbene' is also necessary
				auftragRS.add(new Auftrag(ID_auftrag, niederlassung, rechnDatum, zahlDatum, null, null, auftraggeberRS.get(0), null, verstorbenerRS.get(countRows), null));
			}
			stmtVerstorbene.close();
			stmtAuftrag.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		System.out.println(auftragRS.get(1).getNiederL());
//		System.out.println(auftragRS.get(1).getVerstorbener().getName());

				
//		System.out.println(auftragRS.get(1).getNiederL());
//		System.out.println(auftragRS.get(1).getVerstorbener().getName());
		return auftragRS;
	}


	@Override
	public boolean insertAuftragToDatabase(Auftrag auftrag) {
		// TODO Auto-generated method stub
		String sql;

		/*eigenständige Tabellen
		auftrag.getAbholung();
		auftrag.getAngehoerige();
		auftrag.getAnzeigen();
		auftrag.getAuftraggeber();
		auftrag.getAuftragNr();
		auftrag.getTrauerfeier();
		auftrag.getVerstorbener();*/

		/*Auftrag direkt
		auftrag.getNiederL();
		auftrag.getRechnDatum();
		auftrag.getZahlDatum();*/

		try {
			Statement stmt = conn.createStatement();

			sql = "";
			stmt.executeQuery(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}


	@Override
	public boolean insertUserToDatabase(String username, String password) {
		// TODO Auto-generated method stub
		String sql;

		try {
			Statement stmt = conn.createStatement();

			sql = "INSERT INTO `FimsDatabase`.`benutzer` (benutzername, passwort) VALUES ('" + username + "','" + password + "');";
			boolean insert = stmt.execute(sql);
			stmt.close();
			return insert;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public String[] getUniqueKonfessionen() {
		// TODO Auto-generated method stub
		ArrayList<String> result = new ArrayList<String>();
		String sql;

		try {
			Statement stmt = conn.createStatement();

			sql = "SELECT konfession FROM verstorbene GROUP BY konfession;";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				result.add(rs.getString("konfession"));
			}
			stmt.close();

			if(!result.isEmpty()) {
				String[] resultArray = new String[result.size()];
				for(int i = 0; i < result.size(); i++) {
					resultArray[i] = result.get(i);
				}
				return resultArray;
			} else
				return new String[0];
		} catch (SQLException e) {
			e.printStackTrace();
			return new String[0];
		}
	}


	@Override
	public String[] getUniqueFamilienstaende() {
		// TODO Auto-generated method stub
		ArrayList<String> result = new ArrayList<String>();
		String sql;

		try {
			Statement stmt = conn.createStatement();

			sql = "SELECT familienstand FROM verstorbene GROUP BY familienstand;";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				result.add(rs.getString("familienstand"));
			}
			stmt.close();

			if(!result.isEmpty()) {
				String[] resultArray = new String[result.size()];
				for(int i = 0; i < result.size(); i++) {
					resultArray[i] = result.get(i);
				}
				return resultArray;
			} else
				return new String[0];
		} catch (SQLException e) {
			e.printStackTrace();
			return new String[0];
		}
	}


	@Override
	public String[] getUniqueKrankenkassen() {
		// TODO Auto-generated method stub
		ArrayList<String> result = new ArrayList<String>();
		String sql;

		try {
			Statement stmt = conn.createStatement();

			sql = "SELECT krankenkasse FROM verstorbene GROUP BY krankenkasse;";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				result.add(rs.getString("krankenkasse"));
			}
			stmt.close();

			if(!result.isEmpty()) {
				String[] resultArray = new String[result.size()];
				for(int i = 0; i < result.size(); i++) {
					resultArray[i] = result.get(i);
				}
				return resultArray;
			} else
				return new String[0];
		} catch (SQLException e) {
			e.printStackTrace();
			return new String[0];
		}
	}


	@Override
	public String[] getUniqueRentenverischerungen() {
		// TODO Auto-generated method stub
		ArrayList<String> result = new ArrayList<String>();
		String sql;

		try {
			Statement stmt = conn.createStatement();

			sql = "SELECT rentenvers FROM verstorbene GROUP BY rentenvers;";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				result.add(rs.getString("rentenvers"));
			}
			stmt.close();

			if(!result.isEmpty()) {
				String[] resultArray = new String[result.size()];
				for(int i = 0; i < result.size(); i++) {
					resultArray[i] = result.get(i);
				}
				return resultArray;
			} else
				return new String[0];
		} catch (SQLException e) {
			e.printStackTrace();
			return new String[0];
		}
	}


	@Override
	public String[] getUniqueAnzeigenart() {
		// TODO Auto-generated method stub
		ArrayList<String> result = new ArrayList<String>();
		String sql;

		try {
			Statement stmt = conn.createStatement();

			sql = "SELECT anzeigeart FROM zeitungsanzeigen GROUP BY anzeigeart;";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				result.add(rs.getString("anzeigeart"));
			}
			stmt.close();

			if(!result.isEmpty()) {
				String[] resultArray = new String[result.size()];
				for(int i = 0; i < result.size(); i++) {
					resultArray[i] = result.get(i);
				}
				return resultArray;
			} else
				return new String[0];
		} catch (SQLException e) {
			e.printStackTrace();
			return new String[0];
		}
	}


	@Override
	public String[] getUniqueZeitung() {
		// TODO Auto-generated method stub
		ArrayList<String> result = new ArrayList<String>();
		String sql;

		try {
			Statement stmt = conn.createStatement();

			sql = "SELECT zeitung FROM zeitungsanzeigen GROUP BY zeitung;";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				result.add(rs.getString("zeitung"));
			}
			stmt.close();

			if(!result.isEmpty()) {
				String[] resultArray = new String[result.size()];
				for(int i = 0; i < result.size(); i++) {
					resultArray[i] = result.get(i);
				}
				return resultArray;
			} else
				return new String[0];
		} catch (SQLException e) {
			e.printStackTrace();
			return new String[0];
		}
	}


	@Override
	public String[] getUniqueOrte() {
		// TODO Auto-generated method stub
		ArrayList<String> result = new ArrayList<String>();
		String sql;

		try {
			Statement stmt = conn.createStatement();

			sql = "SELECT bezeichnung FROM ort GROUP BY bezeichnung;";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				result.add(rs.getString("bezeichnung"));
			}
			stmt.close();

			if(!result.isEmpty()) {
				String[] resultArray = new String[result.size()];
				for(int i = 0; i < result.size(); i++) {
					resultArray[i] = result.get(i);
				}
				return resultArray;
			} else
				return new String[0];
		} catch (SQLException e) {
			e.printStackTrace();
			return new String[0];
		}
	}
}
