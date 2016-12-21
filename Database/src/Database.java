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

public class Database {
	// ArrayList<Noleggio>
	private static Connection cn;
	private static Statement st;
	private static ResultSet rs;

	public static ArrayList<Noleggio> elencoNoleggioSocio(String dataI, String cf)
			throws SQLException, ClassNotFoundException {
		ArrayList<Noleggio> elenco = new ArrayList<Noleggio>();
		String sql;

		Class.forName("com.mysql.jdbc.Driver");

		// Creo la connessione al database
		cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsharing?user=root&password=");

		sql = "SELECT * FROM noleggi WHERE inizio>='" + dataI + "' AND socio='" + cf + "';";
		// ________________________________query

		st = cn.createStatement(); // creo sempre uno statement sulla
									// connessione
		rs = st.executeQuery(sql); // faccio la query su uno statement
		while (rs.next() == true) {
			Noleggio n = new Noleggio(rs.getString("codiceNoleggio"), rs.getString("auto"), rs.getString("socio"),
					rs.getString("inizio"), rs.getString("fine"), rs.getInt("autoRestituita"));
			elenco.add(n);
		}

		cn.close(); // chiusura connessione

		return elenco;
	}

	public static void nuovoNoleggio(String cf, String dataI, String dataF, String auto)
			throws SQLException, ClassNotFoundException {
		String sql;

		Class.forName("com.mysql.jdbc.Driver");

		cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsharing?user=root&password=");
		sql = "insert into noleggi (codiceNoleggio,auto,socio,inizio,fine,autoRestituita) values (null,'" + auto + "','"
				+ cf + "','" + dataI + "','" + dataF + "','" + 0 + "')";
		System.out.println(sql);

		st = cn.createStatement();

		st.execute(sql);
		cn.close();

	}

	public static ArrayList<Auto> elencoAutoDisponibili() throws ClassNotFoundException, SQLException {
		String sql;
		ArrayList<Auto> auto = new ArrayList<Auto>();

		Class.forName("com.mysql.jdbc.Driver");

		// Creo la connessione al database
		cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsharing?user=root&password=");

		// Inserisco nelle variabili i valori da modificare

		sql = "SELECT * FROM auto INNER JOIN noleggi " + "ON auto.targa = noleggi.auto "
				+ "WHERE noleggi.autoRestituita='1'";
		System.out.println(sql); // stampa la query
		// ________________________________query

		st = cn.createStatement();
		rs = st.executeQuery(sql);
		while(rs.next()){
			auto.add(new Auto(rs.getString("targa"), rs.getString("marca"), rs.getString("modello"), rs.getDouble("costoGiornaliero")));
		}
		cn.close(); 
		return auto;
		
	}

	public static void eliminaNoleggio(String targa) throws ClassNotFoundException, SQLException {
		String sql;
		// ________________________________connessione

		Class.forName("com.mysql.jdbc.Driver");

		cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsharing?user=root&password=");

		sql = "delete from noleggio where auto=" + targa;
		System.out.println(sql); // stampa la query
		// ________________________________query

		st = cn.createStatement();

		st.execute(sql);
		cn.close();

	}

	public static void eliminaAuto(String targa) throws ClassNotFoundException, SQLException {
		String sql;
		Class.forName("com.mysql.jdbc.Driver");

		cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsharing?user=root&password=");

		sql = "delete from auto where targa=" + targa;
		System.out.println(sql); // stampa la query

		st = cn.createStatement();
		st.execute(sql);
		cn.close(); //

	}

	public static void updateDatabase(String Data) throws SQLException, ClassNotFoundException {
		String sql;

		Class.forName("com.mysql.jdbc.Driver");

		cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsharing?user=root&password=");

		sql = "SELECT * FROM auto";
		st = cn.createStatement();
		st.execute(sql);
		cn.close();

	}

}