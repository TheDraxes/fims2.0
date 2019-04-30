package de.hwr.fims_backend.dbconnector;

import java.sql.*;
import java.util.ArrayList;

import de.hwr.fims_backend.data.customerdata.Auftrag;
import de.hwr.fims_backend.data.customerdata.Auftraggeber;
import de.hwr.fims_backend.data.customerdata.Verstorbener;
import de.hwr.fims_backend.data.services.Abholung;

public class DatabaseConnector implements IDatabase {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/fimsdatabase?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    static final String USER = "root";
    static final String PASS = "fims1234";

    Connection conn;

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
		// TODO Auto-generated method stub
		String sql;
		ArrayList<Auftrag> auftraege = new ArrayList<Auftrag>();

		try {
			Statement stmt = conn.createStatement();

			sql = "SELECT * FROM auftraege";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Auftrag auftrag = new Auftrag();
				auftrag.setAuftragNr(rs.getInt("ID_Auftrag"));
				auftrag.setNiederL(rs.getString("niederlassung"));
				auftrag.setRechnDatum(rs.getDate("rechnungsdatum"));
				auftrag.setZahlDatum(rs.getDate("zahlungsdatum"));
				auftrag.setAuftraggeber(getAuftraggeberById(rs.getInt("auftraggeber_id")));
				auftrag.setVerstorbener(getVerstorbeneById(rs.getInt("verstorbene_id")));
				auftrag.setAbholung(getAbholungByAuftrag_Id((int) auftrag.getAuftragNr()));
				auftrag.setTrauerfeier(null);
				auftrag.setAnzeigen(null); //mehrere
				auftrag.setAngehoerige(null); //mehrere
				auftraege.add(auftrag);
			}
			stmt.close();
			return auftraege;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private Abholung getAbholungByAuftrag_Id(int auftrag_id) {
		String sql;
		Abholung abholung = new Abholung();

		try {
			Statement stmt = conn.createStatement();

			sql = "SELECT * FROM abholungen WHERE auftrag_ID = " + auftrag_id + ";";
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				abholung.setAbhDat(rs.getDate("datum"));
				if(rs.getShort("geschaeftszeiten") == 1) {
					abholung.setGeschZeit(true);
				} else
					abholung.setGeschZeit(false);
				abholung.setOrt("abholungsort");
			}
			stmt.close();
			return abholung;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private Auftraggeber getAuftraggeberById(int auftraggeber_id) {
		String sql;
		Auftraggeber auftraggeber = new Auftraggeber();

		try {
			Statement stmt = conn.createStatement();

			sql = "SELECT * FROM auftraggeber WHERE ID_auftraggeber = " + auftraggeber_id + ";";
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				if(rs.getString("geschlecht").equals("m")) {
					auftraggeber.setGeschlecht(true);
				} else
					auftraggeber.setGeschlecht(false);
				auftraggeber.setName(rs.getString("name"));
				auftraggeber.setVorname(rs.getString("vorname"));
				auftraggeber.setPlz(rs.getString("plz"));
				auftraggeber.setOrt(getOrtById(rs.getInt("ort_ID")));
				auftraggeber.setStrasse(rs.getString("strasse"));
				auftraggeber.setHausNr(rs.getString("hausNr"));
				auftraggeber.setBezArt("beziehungsart");
				auftraggeber.setTelefonNr(rs.getString("telefonNr"));
			}
			stmt.close();
			return auftraggeber;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private Verstorbener getVerstorbeneById(int verstorbene_id) {
		String sql;
		Verstorbener verstorbener = new Verstorbener();

		try {
			Statement stmt = conn.createStatement();

			sql = "SELECT * FROM verstorbene WHERE ID_verstorbene = " + verstorbene_id + ";";
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				if(rs.getString("geschlecht").equals("m")) {
					verstorbener.setGeschlecht(true);
				} else
					verstorbener.setGeschlecht(false);
				verstorbener.setName(rs.getString("name"));
				verstorbener.setVorname(rs.getString("vorname"));
				verstorbener.setPlz(rs.getString("plz"));
				verstorbener.setOrt(getOrtById(rs.getInt("ort_ID")));
				verstorbener.setStrasse(rs.getString("strasse"));
				verstorbener.setHausNr(rs.getString("hausNr"));
				verstorbener.setGebDatum(rs.getDate("geburtsdatum"));
				verstorbener.setGebOrt(getOrtById(rs.getInt("geburtsort_ID")));
				verstorbener.setTodDatum(rs.getDate("todesdatum"));
				verstorbener.setTodOrt(getOrtById(rs.getInt("todesort_ID"));
				verstorbener.setFamStand(rs.getString("familienstand"));
				verstorbener.setAnzSohn(rs.getInt("anz_sohn"));
				verstorbener.setAnzTocht(rs.getInt("anz_tochter"));
				verstorbener.setBeruf(rs.getString("beruf"));
				verstorbener.setKonfes(rs.getString("konfession"));
				verstorbener.setKrankKas(rs.getString("krankenkasse"));
				verstorbener.setRenteVers(rs.getString("rentenvers"));
			}
			stmt.close();
			return verstorbener;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private String getOrtById(int ort_id) {
		String sql;
		String ort;

		try {
			Statement stmt = conn.createStatement();

			sql = "SELECT bezeichnung FROM ort WHERE ID_ort = " + ort_id + ";";
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				return rs.getString("bezeichnung");
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
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
