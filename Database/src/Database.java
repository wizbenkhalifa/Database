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
import java.util.concurrent.TimeUnit;

public class Database {
	// ArrayList<Noleggio>
	private static Connection cn;
	private static Statement st;
	private static ResultSet rs;
	
	
	public static ArrayList<Noleggio> elencoNoleggi()
			throws SQLException, ClassNotFoundException {
		ArrayList<Noleggio> elencoN = new ArrayList<Noleggio>();
		String sql;

		Class.forName("com.mysql.jdbc.Driver");

		// Creo la connessione al database
		cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsharing?user=root&password=");

		sql = "SELECT * FROM noleggi";
		// ________________________________query

		st = cn.createStatement(); 
		rs = st.executeQuery(sql); 
		while (rs.next() == true) {
			Noleggio n = new Noleggio(rs.getString("codiceNoleggio"), rs.getString("auto"), rs.getString("socio"),
					rs.getString("inizio"), rs.getString("fine"), rs.getInt("autoRestituita"));
			elencoN.add(n);
		}
		
		cn.close(); // chiusura connessione

		return elencoN;
	}

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

	public static ArrayList<Auto> elencoAutoDisponibili(String dataI) throws ClassNotFoundException, SQLException {
		String sql;
		ArrayList<Auto> auto = new ArrayList<Auto>();

		Class.forName("com.mysql.jdbc.Driver");

		cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsharing?user=root&password=");

		sql = "SELECT * FROM auto INNER JOIN noleggi ON auto.targa=noleggi.auto WHERE auto.targa NOT IN(SELECT targa FROM auto INNER JOIN noleggi "
				+ "ON auto.targa = noleggi.auto " + "WHERE noleggi.inizio<='" + dataI + "' AND noleggi.fine>='" + dataI
				+ "') GROUP BY auto.targa;";
		System.out.println(sql);
		st = cn.createStatement();
		rs = st.executeQuery(sql);
		while (rs.next()) {
			auto.add(new Auto(rs.getString("targa"), rs.getString("marca"), rs.getString("modello"),
					rs.getDouble("costoGiornaliero")));
		}
		cn.close();
		return auto;

	}
	
	public static ArrayList<Socio> elencoSoci(String dataI) throws ClassNotFoundException, SQLException {
		String sql;
		ArrayList<Socio> socio = new ArrayList<Socio>();

		Class.forName("com.mysql.jdbc.Driver");

		cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsharing?user=root&password=");

		sql = "SELECT * FROM soci ";
		System.out.println(sql);
		st = cn.createStatement();
		rs = st.executeQuery(sql);
		while (rs.next()) {
			socio.add(new Socio(rs.getString("cf"), rs.getString("cognome"), rs.getString("nome"), rs.getString("indirizzo"),rs.getString("telefono")));
		}
		cn.close();
		return socio;

	}
	
	
	

	public static void eliminaNoleggio(String targa) throws ClassNotFoundException, SQLException {
		String sql;
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

	public static int updateNoleggio(String dataF, String indice) throws SQLException, ClassNotFoundException {
		String sql;
		String fine = null, inizio = null;
		double costoGiornaliero = 0;
		boolean rest;
		Class.forName("com.mysql.jdbc.Driver");
		cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsharing?user=root&password=");
		st = cn.createStatement();
		rs = st.executeQuery(
				"SELECT noleggi.fine,noleggi.inizio,auto.costoGiornaliero, noleggi.autoRestituita FROM auto INNER JOIN noleggi ON auto.targa = noleggi.auto WHERE codiceNoleggio ='"
						+ indice + "';");
		while (rs.next()) {
			fine = rs.getString("fine");
			inizio = rs.getString("inizio");
			costoGiornaliero = rs.getDouble("costoGiornaliero");
			rest = rs.getBoolean("autoRestituita");
		}
		if (rs.getBoolean("autoRestituita")) {
			
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
			long diff = 0, daysDiff1 = 0, daysDiff2 = 0, costoNoleggio = 0;
			try {
				java.util.Date date1 = myFormat.parse(inizio);
				java.util.Date date2 = myFormat.parse(fine);
				diff = date1.getTime() - date2.getTime();
				daysDiff1 = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
				date2 = myFormat.parse(dataF);
				diff = date1.getTime() - date2.getTime();
				daysDiff2 = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
				if (daysDiff2 < 0) {
					daysDiff1 = Math.abs(daysDiff1) - Math.abs(daysDiff2);
					costoNoleggio = Math.abs(daysDiff1) * ((int) (costoGiornaliero));
					System.out.println(inizio + " - " + fine + " - " + costoNoleggio + " - " + Math.abs(daysDiff1)
							+ " - " + daysDiff2);
				} else {
					costoNoleggio = Math.abs(daysDiff1) * ((int) costoGiornaliero) + (Math.abs(daysDiff2) * 25);
					System.out.println(inizio + " - " + fine + " - " + costoGiornaliero + " - " + Math.abs(daysDiff1)
							+ " - " + costoNoleggio);
				}
				st = cn.createStatement();
				st.executeQuery("UPDATE noleggi SET autoRestituita = '1', fine='" + date2 + "' WHERE codiceNoleggio = '"
						+ indice + "';");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			cn.close();
			return (int) costoNoleggio;
		} else {
			System.out.println("Auto gia restituita");
			cn.close();
			return -1;
		}

	}

}