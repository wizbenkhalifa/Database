import java.sql.Connection;
import java.sql.Date;
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
	//ArrayList<Noleggio>

	public static ArrayList<Noleggio> elencoNoleggioSocio(String dataInizio, String dataFine, String cf) {
		ArrayList<Noleggio> elenco = new ArrayList<Noleggio>();
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
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsharing?user=root&password=");

			sql = "SELECT * FROM noleggio,socio WHERE noleggio.inizio>='"+dataInizio+"' AND socio.cf='"+ cf + "';";
			// ________________________________query

			st = cn.createStatement(); // creo sempre uno statement sulla
										// connessione
			rs = st.executeQuery(sql); // faccio la query su uno statement
			while (rs.next() == true) {
				Noleggio n  = new Noleggio(rs.getString("codiceNoleggio"), rs.getString("auto"), rs.getString("socio"), rs.getString("inizio"),
						rs.getString("fine"), rs.getInt("autoRestituita"));
				elenco.add(n);
			}
			

			cn.close(); // chiusura connessione
		} catch (SQLException e) {
			System.out.println("errore:" + e.getMessage());
			e.printStackTrace();
		} // fine try-catch

		return elenco;
	}

	
	public static void nuovoNoleggio(String cf, java.util.Date dataI, java.util.Date dataF, String auto) {
		Connection cn;
		Statement st;
		String sql;
		Calendar data = Calendar.getInstance();
		data.getTime();
		String sqlDataI = data.get(Calendar.YEAR) + "-" + data.get(Calendar.MONTH) + "-"
				+ data.get(Calendar.DAY_OF_MONTH); //2016-12-20
		String sqlDataF = data.get(Calendar.YEAR) + "-" + data.get(Calendar.MONTH) + "-"
				+ data.get(Calendar.DAY_OF_MONTH); //2016-12-20
		// ________________________________connessione
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		} // fine try-catch
		
		

		try {
			// Creo la connessione al database
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsharing?user=root&password=");
		
			sql = "insert into noleggi (codiceNoleggio,auto,socio,inizio,fine,autoRestituita) values (null,'" + auto + "','" + cf
					+ "','" + sqlDataI + "','"+sqlDataF+"','"+0+"')";
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

	
	public static void elencoAutoDisponibili(String dataInizio) {
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
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsharing?user=root&password=");

			// Inserisco nelle variabili i valori da modificare

			sql = "SELECT targa FROM auto,noleggio "
					+ "INNER JOIN noleggio "
					+ "ON auto.targa = noleggio.auto "
					+ "WHERE noleggio.fine <'" + dataInizio + "' AND noleggio.autoRestituita='1'";
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

	
	public static void eliminaNoleggio(String targa) {
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
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsharing?user=root&password=");

			// Recupero l'id. Se volessi fare altri controlli potrei recuperare
			// anche gli altri valori

			sql = "delete from noleggio where auto=" + targa;
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
	
	public static void eliminaAuto(String targa) {
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
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsharing?user=root&password=");

			// Recupero l'id. Se volessi fare altri controlli potrei recuperare
			// anche gli altri valori

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
	
	public static void updateDatabase(String Data) throws SQLException, ClassNotFoundException{
		Connection cn;
		Statement st;
		String sql;
		// ________________________________connessione
		
		Class.forName("com.mysql.jdbc.Driver");
		
		
		// Creo la connessione al database
		cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsharing?user=root&password=");

		// Recupero l'id. Se volessi fare altri controlli potrei recuperare
		// anche gli altri valori

		sql = "SELECT * FROM auto";
		st = cn.createStatement(); // creo sempre uno statement sulla
									// connessione
		st.execute(sql); // faccio la query su uno statement
		cn.close(); // chiusura connessione
		
	}
	
	 
}