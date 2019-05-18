package de.hwr.fims_backend.dbconnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hwr.fims_backend.controller.LoginController;
import de.hwr.fims_backend.data.advertisement.Zeitungsanzeige;
import de.hwr.fims_backend.data.customerdata.Auftrag;


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


	@Override
	public ArrayList<Auftrag> getAuftraegeFromDatabase() {
		ArrayList<Zeitungsanzeige> anzeigeRS = new ArrayList<Zeitungsanzeige>();
		ArrayList<Auftrag> auftragRS = new ArrayList<Auftrag>();
		Statement stmt;
		
		String sql1;
		String sql2;
		String sql3;
		String sql4;
		String sql5;
		String sql6;
		String sql7;
		
		try {
			stmt = conn.createStatement();
			sql1 = "SELECT ID_zeitungsanzeige, auftrag_ID, anzeigeart, zeitung, groesse, preis FROM zeitungsanzeigenn;";
			sql7 = "SELECT ID_auftrag, auftraggeber_ID, niederlassung, rechnungsdatum, zahlungsdatum, verstorbene_ID FROM auftraege;";
			
//			ResultSet rs1 = stmtAuftrag.executeQuery(sql1);
			ResultSet rs7 = stmt.executeQuery(sql7);
			
//			while(rs1.next()) {
//				String anzeigeart = rs1.getString("anzeigeart");
//				String zeitung = rs1.getString("zeitung");
//				String groesse = rs1.getString("groesse");
//				double preis = rs1.getDouble("preis");
//				
//				anzeigeRS.add(new Zeitungsanzeige(preis, anzeigeart, groesse, zeitung));
//			}
			
			int countAnzeige = 0;
			while(rs7.next()) {
				int ID_auftrag = rs7.getInt("ID_auftrag");
				String niederlassung = rs7.getString("niederlassung");
				Date rechnDatum = rs7.getDate("rechnungsdatum");
				Date zahlDatum = rs7.getDate("zahlungsdatum");
				
				auftragRS.add(new Auftrag(ID_auftrag, niederlassung, rechnDatum, zahlDatum, null, null, null, null, null, null));
				countAnzeige = countAnzeige++;
			}
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(auftragRS.get(1).getNiederL());
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
