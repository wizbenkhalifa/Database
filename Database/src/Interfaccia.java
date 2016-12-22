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
	private Text textCf;
	private Text textCF2;
	Database d = new Database();
	String dataInizio = null;
	String dataFine = null;
	String cf = null;
	ArrayList<Auto> autoDisp;
	ArrayList<Noleggio> noleggi;
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
		shell.setSize(501, 525);
		shell.setText("SWT Application");

		List listNoleggi = new List(shell, SWT.BORDER);
		listNoleggi.setBounds(10, 135, 201, 328);

		Label lblSocio = new Label(shell, SWT.NONE);
		lblSocio.setBounds(10, 36, 40, 15);
		lblSocio.setText("Socio :");

		textCf = new Text(shell, SWT.BORDER);
		textCf.setBounds(54, 30, 157, 21);

		textCF2 = new Text(shell, SWT.BORDER);
		textCF2.setBounds(342, 122, 133, 21);

		Label lblDatainizio = new Label(shell, SWT.NONE);
		lblDatainizio.setBounds(10, 69, 61, 15);
		lblDatainizio.setText("DataInizio : ");

		DateTime dateInizio = new DateTime(shell, SWT.BORDER);
		dateInizio.setBounds(78, 63, 80, 24);

		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String dataI = dateInizio.getYear() + "-" + (dateInizio.getMonth() + 1) + "-" + dateInizio.getDay();
				noleggi = new ArrayList<Noleggio>();
				System.out.println(dataI);
				cf = textCf.getText();
				try {
					noleggi = Database.elencoNoleggioSocio(dataI, cf);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for (int i = 0; i < noleggi.size(); i++) {
					listNoleggi.add(noleggi.get(i).getCodiceNoleggio() + " - " + noleggi.get(i).getAuto() + " - "
							+ noleggi.get(i).getInizio() + " - " + noleggi.get(i).getFine() + " - "
							+ noleggi.get(i).getAutoRestituita());
				}
			}
		});
		btnNewButton.setBounds(10, 101, 75, 25);
		btnNewButton.setText("Invio");

		Label label = new Label(shell, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(250, 10, 2, 467);

		Label lblRicerca = new Label(shell, SWT.NONE);
		lblRicerca.setBounds(78, 10, 55, 15);
		lblRicerca.setText("RICERCA");

		Label lblDatainizio_1 = new Label(shell, SWT.NONE);
		lblDatainizio_1.setBounds(258, 36, 66, 15);
		lblDatainizio_1.setText("DataInizio :");

		DateTime dateInizio2 = new DateTime(shell, SWT.BORDER);
		dateInizio2.setBounds(317, 30, 80, 24);

		Label lblDatafine = new Label(shell, SWT.NONE);
		lblDatafine.setBounds(258, 69, 55, 15);
		lblDatafine.setText("DataFine :");

		DateTime dateFine2 = new DateTime(shell, SWT.BORDER);
		dateFine2.setBounds(317, 60, 80, 24);

		Label lblModello = new Label(shell, SWT.NONE);
		lblModello.setBounds(258, 101, 55, 15);
		lblModello.setText("Modello :");

		Combo comboModello = new Combo(shell, SWT.NONE);
		comboModello.setItems(new String[] {});
		comboModello.setBounds(317, 93, 91, 23);

		Label lblInserimento = new Label(shell, SWT.NONE);
		lblInserimento.setBounds(317, 10, 80, 15);
		lblInserimento.setText("INSERIMENTO");

		Combo comboModello2 = new Combo(shell, SWT.NONE);
		comboModello2.setBounds(319, 232, 91, 23);

		Button btnInserisci = new Button(shell, SWT.NONE);
		btnInserisci.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String dataI = dateInizio2.getYear() + "-" + (dateInizio2.getMonth() + 1) + "-" + dateInizio2.getDay();
				String dataF = dateFine2.getYear() + "-" + (dateFine2.getMonth() + 1) + "-" + dateFine2.getDay();
				try {
					Database.nuovoNoleggio(textCF2.getText(), dataI, dataF, comboModello2.getText());
				} catch (SQLException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnInserisci.setBounds(307, 165, 75, 25);
		btnInserisci.setText("Inserisci");

		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(258, 127, 80, 15);
		lblNewLabel.setText("codiceFiscale :");

		Label label_1 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setBounds(258, 210, 217, 2);

		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(258, 235, 55, 15);
		lblNewLabel_1.setText("Modello :");

		Button btnElimina = new Button(shell, SWT.NONE);
		btnElimina.setBounds(258, 271, 75, 25);
		btnElimina.setText("Elimina");

		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.setBounds(400, 438, 75, 25);
		btnNewButton_1.setText("Aggiorna");
		
		Button btnNewButton_2 = new Button(shell, SWT.NONE);
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date date = Calendar.getInstance().getTime();
				System.out.println(dateFormat.format(date));
				try {
					Database.updateNoleggio(dateFormat.format(date), noleggi.get(listNoleggi.getSelectionIndex()).getCodiceNoleggio());
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(103, 104, 75, 25);
		btnNewButton_2.setText("Restituisci");
		
		//Carica le auto resituite e disponibili per il noleggio
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.now();
			System.out.println(dtf.format(localDate)); //2016/11/16
			autoDisp = Database.elencoAutoDisponibili(dtf.format(localDate));
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(int i=0; i<autoDisp.size(); i++){
			comboModello.add(autoDisp.get(i).getMarca() + " - " + autoDisp.get(i).getModello());
		}
	}
}
