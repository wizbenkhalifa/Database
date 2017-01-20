import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.List;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Interfaccia {

	protected Shell shell;
	Database d = new Database();
	String dataInizio = null;
	String dataFine = null;
	String cf = null;
	ArrayList<Auto> autoDisp;
	ArrayList<Socio> socio;
	ArrayList<Noleggio> noleggi;
	ArrayList<Auto> elAuto;
	List list_1;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			Interfaccia window = new Interfaccia();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(817, 525);
		shell.setText("Autonoleggio");

		List listNoleggi = new List(shell, SWT.BORDER);
		listNoleggi.setBounds(10, 33, 225, 127);

		Label lblSocio = new Label(shell, SWT.NONE);
		lblSocio.setBounds(10, 166, 40, 15);
		lblSocio.setText("Socio :");

		Combo combo = new Combo(shell, SWT.NONE);

		Label lblDatainizio = new Label(shell, SWT.NONE);
		lblDatainizio.setBounds(10, 207, 61, 15);
		lblDatainizio.setText("DataInizio : ");

		DateTime dateInizio = new DateTime(shell, SWT.BORDER);
		dateInizio.setBounds(72, 201, 80, 24);

		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				listNoleggi.removeAll();
				String dataI = dateInizio.getYear() + "-" + (dateInizio.getMonth() + 1) + "-" + dateInizio.getDay();
				noleggi = new ArrayList<Noleggio>();
				System.out.println(dataI);

				cf = combo.getText();

				try {
					noleggi = Database.elencoNoleggioSocio(dataI, cf);
				} catch (ClassNotFoundException | SQLException e1) {
					MessageDialog.openError(shell, "Errore",
							"Errore nell'esecuzione della query \n " + e1.getMessage());
				}

				for (int i = 0; i < noleggi.size(); i++) {
					listNoleggi.add(noleggi.get(i).getCodiceNoleggio() + " - " + noleggi.get(i).getAuto() + " - "
							+ noleggi.get(i).getInizio() + " - " + noleggi.get(i).getFine() + " - "
							+ noleggi.get(i).getAutoRestituita());
				}
			}
		});
		btnNewButton.setBounds(160, 166, 75, 25);
		btnNewButton.setText("Cerca");

		Combo combo_1 = new Combo(shell, SWT.NONE);

		Label label = new Label(shell, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(250, 10, 2, 467);

		Label lblRicerca = new Label(shell, SWT.NONE);
		lblRicerca.setBounds(10, 6, 133, 21);
		lblRicerca.setText("Ricerca Noleggi");

		Label lblDatainizio_1 = new Label(shell, SWT.NONE);
		lblDatainizio_1.setBounds(269, 250, 66, 15);
		lblDatainizio_1.setText("DataInizio :");

		DateTime dateInizio2 = new DateTime(shell, SWT.BORDER);
		dateInizio2.setBounds(341, 241, 80, 24);

		Label lblDatafine = new Label(shell, SWT.NONE);
		lblDatafine.setBounds(269, 284, 55, 15);
		lblDatafine.setText("DataFine :");

		DateTime dateFine2 = new DateTime(shell, SWT.BORDER);
		dateFine2.setBounds(341, 275, 80, 24);

		Label lblModello = new Label(shell, SWT.NONE);
		lblModello.setBounds(269, 319, 55, 15);
		lblModello.setText("Modello :");

		Combo comboModello = new Combo(shell, SWT.NONE);
		comboModello.setItems(new String[] {});
		comboModello.setBounds(341, 316, 91, 23);

		Label lblInserimento = new Label(shell, SWT.NONE);
		lblInserimento.setBounds(250, 6, 119, 15);
		lblInserimento.setText("Inserisci Noleggio");

		Button btnInserisci = new Button(shell, SWT.NONE);
		btnInserisci.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String dataI = dateInizio2.getYear() + "-" + (dateInizio2.getMonth() + 1) + "-" + dateInizio2.getDay();
				String dataF = dateFine2.getYear() + "-" + (dateFine2.getMonth() + 1) + "-" + dateFine2.getDay();
				try {
					Database.nuovoNoleggio(combo_1.getText(), dataI, dataF,
							autoDisp.get(comboModello.getSelectionIndex()).getTarga());
				} catch (SQLException | ClassNotFoundException e1) {
					MessageDialog.openError(shell, "Errore",
							"Errore nell'esecuzione della query \n " + e1.getMessage());
				}
			}
		});
		btnInserisci.setBounds(260, 385, 75, 25);
		btnInserisci.setText("Inserisci");

		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(269, 351, 80, 15);
		lblNewLabel.setText("codiceFiscale :");

		Button btnElimina = new Button(shell, SWT.NONE);
		btnElimina.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					Database.eliminaNoleggio(elAuto.get(list_1.getSelectionIndex()).getTarga());
					Database.eliminaAuto(elAuto.get(list_1.getSelectionIndex()).getTarga());
					MessageDialog.openInformation(shell, "Info", "Eliminati i noleggi e le auto con targa: "
							+ elAuto.get(list_1.getSelectionIndex()).getTarga());
				} catch (ClassNotFoundException | SQLException e1) {
					MessageDialog.openError(shell, "Errore",
							"Errore nell'esecuzione della query \n " + e1.getMessage());
				}
			}
		});
		btnElimina.setBounds(510, 186, 75, 25);
		btnElimina.setText("Elimina");

		Button btnNewButton_2 = new Button(shell, SWT.NONE);
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date date = Calendar.getInstance().getTime();
				System.out.println(dateFormat.format(date));
				try {
					int costo = Database.updateNoleggio(dateFormat.format(date),
							noleggi.get(listNoleggi.getSelectionIndex()).getCodiceNoleggio());
					MessageDialog.openInformation(shell, "Info", "Auto restituita | Costo noleggio: " + costo);
				} catch (ClassNotFoundException | SQLException e1) {
					MessageDialog.openError(shell, "Errore",
							"Errore nell'esecuzione della query \n " + e1.getMessage());
				}
			}
		});
		btnNewButton_2.setBounds(160, 199, 75, 25);
		btnNewButton_2.setText("Restituisci");

		Label label_2 = new Label(shell, SWT.SEPARATOR | SWT.VERTICAL);
		label_2.setBounds(502, 10, 2, 467);

		Label lblEliminaNoleggio = new Label(shell, SWT.NONE);

		lblEliminaNoleggio.setBounds(510, 10, 127, 21);
		lblEliminaNoleggio.setText("Elimina Noleggio");

		List list = new List(shell, SWT.BORDER);
		list.setBounds(258, 37, 238, 123);

		Label lblAutoDisponibili = new Label(shell, SWT.NONE);
		lblAutoDisponibili.setBounds(250, 22, 107, 15);
		lblAutoDisponibili.setText("Auto disponibili");

		list_1 = new List(shell, SWT.BORDER);
		list_1.setBounds(510, 33, 281, 127);

		combo_1.setBounds(351, 345, 91, 23);

		combo.setBounds(56, 166, 91, 23);

		// Carica le auto resituite e disponibili per il noleggio
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.now();
			System.out.println(dtf.format(localDate)); // 2016/11/16
			autoDisp = Database.elencoAutoDisponibili(dtf.format(localDate));
		} catch (ClassNotFoundException | SQLException e1) {
			MessageDialog.openError(shell, "Errore", "Errore nell'esecuzione della query \n " + e1.getMessage());
		}
		for (int i = 0; i < autoDisp.size(); i++) {
			comboModello.add(autoDisp.get(i).getMarca() + " - " + autoDisp.get(i).getModello());
			list.add(autoDisp.get(i).getMarca() + " - " + autoDisp.get(i).getModello());
		}

		try {
			String dataI = dateInizio.getYear() + "-" + (dateInizio.getMonth() + 1) + "-" + dateInizio.getDay();

			socio = Database.elencoSoci(dataI);
		} catch (ClassNotFoundException | SQLException e1) {
			MessageDialog.openError(shell, "Errore", "Errore nell'esecuzione della query \n " + e1.getMessage());
		}

		for (int i = 0; i < socio.size(); i++) {
			combo.add(socio.get(i).getCf());
			combo_1.add(socio.get(i).getCf());
			// combo_2.add(socio.get(i).getCf());

		}

		try {
			elAuto = Database.elencoAuto();
		} catch (ClassNotFoundException | SQLException e2) {
			MessageDialog.openError(shell, "Errore", "Errore nell'esecuzione della query \n " + e2.getMessage());
		}

		for (int i = 0; i < elAuto.size(); i++) {
			list_1.add(elAuto.get(i).getTarga() + " - " + elAuto.get(i).getMarca() + " - " + elAuto.get(i).getModello()
					+ " - " + elAuto.get(i).getCostoGiornaliero());
		}

	}
}
