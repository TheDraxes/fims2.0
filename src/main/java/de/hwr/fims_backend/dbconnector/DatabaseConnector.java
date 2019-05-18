package de.hwr.fims_backend.dbconnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hwr.fims_backend.controller.LoginController;
import de.hwr.fims_backend.data.advertisement.Zeitungsanzeige;
import de.hwr.fims_backend.data.customerdata.Auftrag;
import de.hwr.fims_backend.data.customerdata.Verstorbener;


/**
 * @author Darius Mix, Nguyen Tien Dung Otten
 *
 */
public class DatabaseConnector implements IDatabase {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
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

    // Diese Methode ist noch nicht vollständig aber für den Durchstich reichen die Daten
	@Override
	public ArrayList<Auftrag> getAuftraegeFromDatabase() {
		ArrayList<Verstorbener> verstorbenerRS = new ArrayList<Verstorbener>();
		ArrayList<Zeitungsanzeige> anzeigeRS = new ArrayList<Zeitungsanzeige>();
		ArrayList<Auftrag> auftragRS = new ArrayList<Auftrag>();
		
		Statement stmtVerstorbene;
		Statement stmtAuftrag;
		
		String sql1; // Select Verstorbene
		String sql2; // Select Zeitungsanzeige
		String sql3;
		String sql4;
		String sql5;
		String sql6;
		String sql7; // Select Aufträge
		
		try {
			stmtAuftrag = conn.createStatement();
			stmtVerstorbene = conn.createStatement();
			
			sql1 = "SELECT ID_verstorbene, geschlecht, name, vorname, plz, ort_ID, strasse, hausNr, "
					+ "geburtsdatum, geburtsort_ID, todesdatum, todesort_ID, familienstand, anz_sohn, "
					+ "anz_tochter, konfession, krankenkasse, rentenvers FROM verstorbene;";
//			sql2 = "SELECT ID_zeitungsanzeige, auftrag_ID, anzeigeart, zeitung, groesse, preis FROM zeitungsanzeigenn;";
			sql7 = "SELECT ID_auftrag, niederlassung, rechnungsdatum, zahlungsdatum FROM auftraege;";
			
			ResultSet rs1 = stmtVerstorbene.executeQuery(sql1);
//			ResultSet rs2 = stmt.executeQuery(sql2);
			ResultSet rs7 = stmtAuftrag.executeQuery(sql7);
			
			ResultSetMetaData rsmd7 = rs7.getMetaData();
			int columnNumber = rsmd7.getColumnCount();
			System.out.println(columnNumber);
			
			while(rs1.next()) {
				int ID_verstorbene = rs1.getInt("ID_Verstorbene");
//				boolean geschlecht = rs1.getBoolean("geschlecht"); // enum to boolean???
				String name = rs1.getString("name");
				String vorname = rs1.getString("vorname");
				String plz = rs1.getString("plz")
;				int ort_ID = rs1.getInt("ort_ID"); // int to String???
				String strasse = rs1.getString("strasse");
				String hausNr = rs1.getString("hausNr");
				Date geburtsdatum = rs1.getDate("geburtsdatum");
//				int geburtsort_ID = rs1.getInt("geburtsort_ID"); // int to String???
				Date todesdatum = rs1.getDate("todesdatum");
//				int todesort_ID = rs1.getInt("todesort_ID"); // int to String???
				String familienstand = rs1.getString("familienstand");
				int anz_sohn = rs1.getInt("anz_sohn");
				int anz_tochter = rs1.getInt("anz_tochter");
				String konfession = rs1.getString("konfession");
				String krankenkasse = rs1.getString("krankenkasse");
				String rentenvers = rs1.getString("rentenvers");
				
				verstorbenerRS.add(new Verstorbener(false, name, vorname, null, null, null, null, null, null, null, null, null, null, null, anz_sohn, anz_tochter, null, null, null));
			}
				
			int countRows = 0;	
			while(rs7.next()) {
				int ID_auftrag = rs7.getInt("ID_auftrag");
				String niederlassung = rs7.getString("niederlassung");
				Date rechnDatum = rs7.getDate("rechnungsdatum");
				Date zahlDatum = rs7.getDate("zahlungsdatum");
				System.out.println(countRows);
				countRows++;
				
				// Only works if the relation between 'Auftraege 'and 'Verstorbene' is 1:1
				auftragRS.add(new Auftrag(ID_auftrag, niederlassung, rechnDatum, zahlDatum, null, null, null, null, verstorbenerRS.get(countRows), null));
			}
			stmtVerstorbene.close();
			stmtAuftrag.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		System.out.println(auftragRS.get(1).getNiederL());
		System.out.println(auftragRS.get(1).getVerstorbener().getName());
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
