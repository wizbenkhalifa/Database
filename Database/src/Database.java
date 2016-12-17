import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Ha deimetodi per leggere/scrivere e modificare il db amico
 * 
 * @author fabio
 *
 */
public class Database {

	public static ArrayList<Auto> elencoNoleggioSocio() {
		ArrayList<Auto> elenco = new ArrayList<Auto>();
		Connection cn;
		Statement st;
		ResultSet rs;
		String sql;
		// ________________________________connessione
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		} // fine try-catch

		try {
			// Creo la connessione al database
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=");

			sql = "SELECT id, nome, cognome, dataNascita FROM amici;";
			// ________________________________query

			st = cn.createStatement(); // creo sempre uno statement sulla
										// connessione
			rs = st.executeQuery(sql); // faccio la query su uno statement
			while (rs.next() == true) {
				Auto a = new Auto(rs.getString("targa"), rs.getString("marca"), rs.getString("modello"),
						rs.getDouble("costoGiornaliero"));
				elenco.add(a);
			}

			cn.close(); // chiusura connessione
		} catch (SQLException e) {
			System.out.println("errore:" + e.getMessage());
			e.printStackTrace();
		} // fine try-catch

		return elenco;
	}

	
	public static void nuovaAuto(Auto a) {
		Connection cn;
		Statement st;
		String sql;
		// ________________________________connessione
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		} // fine try-catch

		try {
			// Creo la connessione al database
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=");

			String targa = a.getTarga();
			String marca = a.getMarca();
			String modello = a.getModello();
			Double costoG = a.getCostoGiornaliero();
		
			sql = "insert into auto (targa, marca, modello, costoGiornaliero) values ('" + targa + "','" + marca + "','" + modello
					+ "','" + costoG + "')";
			System.out.println(sql);
			// ________________________________query

			st = cn.createStatement(); // creo sempre uno statement sulla
										// connessione
			st.execute(sql); // faccio la query su uno statement
			cn.close(); // chiusura connessione
		} catch (SQLException e) {
			System.out.println("errore:" + e.getMessage());
		} // fine try-catch

	}

	
	public static void modificaAuto(Auto a) {
		Connection cn;
		Statement st;
		String sql;
		// ________________________________connessione
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		} // fine try-catch
		try {
			// Creo la connessione al database
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=");

			// Inserisco nelle variabili i valori da modificare
			String targa = a.getTarga();
			String marca = a.getMarca();
			String modello = a.getModello();
			Double costoG = a.getCostoGiornaliero();

			sql = "update amici set targa='" + targa + "', marca = '" + marca + "', modello='" + modello
					+ "',costoGiornaliero='" +costoG +"'  where targa=" + targa;
			System.out.println(sql); // stampa la query
			// ________________________________query

			st = cn.createStatement(); // creo sempre uno statement sulla
										// connessione
			st.execute(sql); // faccio la query su uno statement
			cn.close(); // chiusura connessione
		} catch (SQLException e) {
			System.out.println("errore:" + e.getMessage());
		} // fine try-catch

	}

	
	public static void eliminaAuto(Auto a) {
		Connection cn;
		Statement st;
		String sql;
		// ________________________________connessione
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		} // fine try-catch
		try {
			// Creo la connessione al database
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=");

			// Recupero l'id. Se volessi fare altri controlli potrei recuperare
			// anche gli altri valori
			String targa = a.getTarga();

			sql = "delete from auto where targa=" + targa;
			System.out.println(sql); // stampa la query
			// ________________________________query

			st = cn.createStatement(); // creo sempre uno statement sulla
										// connessione
			st.execute(sql); // faccio la query su uno statement
			cn.close(); // chiusura connessione
		} catch (SQLException e) {
			System.out.println("errore:" + e.getMessage());
		} // fine try-catch

	}
	
	 
}