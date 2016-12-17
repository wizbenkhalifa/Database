import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.List;
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
	private Text text;
	private Text text_1;
	public elencoNoleggioSocio socio = new elencoNoleggioSocio();
	

	/**
	 * Launch the application.
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
		
		List list = new List(shell, SWT.BORDER);
		list.setBounds(10, 166, 201, 297);
		
		Label lblSocio = new Label(shell, SWT.NONE);
		lblSocio.setBounds(10, 36, 40, 15);
		lblSocio.setText("Socio :");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(54, 30, 157, 21);
		
		Label lblDatainizio = new Label(shell, SWT.NONE);
		lblDatainizio.setBounds(10, 69, 61, 15);
		lblDatainizio.setText("DataInizio : ");
		
		DateTime dateTime = new DateTime(shell, SWT.BORDER);
		dateTime.setBounds(77, 60, 80, 24);
		
		Label lblData = new Label(shell, SWT.NONE);
		lblData.setBounds(10, 101, 55, 15);
		lblData.setText("DataFine :");
		
		DateTime dateTime_1 = new DateTime(shell, SWT.BORDER);
		dateTime_1.setBounds(77, 92, 80, 24);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				
			}
		});
		btnNewButton.setBounds(10, 122, 75, 25);
		btnNewButton.setText("Invio");
		
		Label label = new Label(shell, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(250, 10, 2, 467);
		
		Label lblRicerca = new Label(shell, SWT.NONE);
		lblRicerca.setBounds(78, 10, 55, 15);
		lblRicerca.setText("RICERCA");
		
		Label lblDatainizio_1 = new Label(shell, SWT.NONE);
		lblDatainizio_1.setBounds(258, 36, 66, 15);
		lblDatainizio_1.setText("DataInizio :");
		
		DateTime dateTime_2 = new DateTime(shell, SWT.BORDER);
		dateTime_2.setBounds(317, 30, 80, 24);
		
		Label lblDatafine = new Label(shell, SWT.NONE);
		lblDatafine.setBounds(258, 69, 55, 15);
		lblDatafine.setText("DataFine :");
		
		DateTime dateTime_3 = new DateTime(shell, SWT.BORDER);
		dateTime_3.setBounds(317, 60, 80, 24);
		
		Label lblModello = new Label(shell, SWT.NONE);
		lblModello.setBounds(258, 101, 55, 15);
		lblModello.setText("Modello :");
		
		Combo combo = new Combo(shell, SWT.NONE);
		combo.setBounds(317, 93, 91, 23);
		
		Label lblInserimento = new Label(shell, SWT.NONE);
		lblInserimento.setBounds(317, 10, 80, 15);
		lblInserimento.setText("INSERIMENTO");
		
		Button btnInserisci = new Button(shell, SWT.NONE);
		btnInserisci.setBounds(307, 165, 75, 25);
		btnInserisci.setText("Inserisci");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(258, 127, 80, 15);
		lblNewLabel.setText("codiceFiscale :");
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(342, 122, 133, 21);
		
		Label label_1 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setBounds(258, 210, 217, 2);
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(258, 235, 55, 15);
		lblNewLabel_1.setText("Modello :");
		
		Button btnElimina = new Button(shell, SWT.NONE);
		btnElimina.setBounds(258, 271, 75, 25);
		btnElimina.setText("Elimina");
		
		Combo combo_1 = new Combo(shell, SWT.NONE);
		combo_1.setBounds(319, 232, 91, 23);
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.setBounds(400, 438, 75, 25);
		btnNewButton_1.setText("Aggiorna");

	}
}
