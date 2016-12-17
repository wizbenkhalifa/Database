
public class Noleggio {
	private String codiceNoleggio;
	private String auto;
	private String socio;
	private String inizio;
	private String fine;
	private int autoRestituita;
	public Noleggio(String codiceNoleggio, String auto, String socio, String inizio, String fine, int autoRestituita) {
		super();
		this.codiceNoleggio = codiceNoleggio;
		this.auto = auto;
		this.socio = socio;
		this.inizio = inizio;
		this.fine = fine;
		this.autoRestituita = autoRestituita;
	}
	public String getCodiceNoleggio() {
		return codiceNoleggio;
	}
	public void setCodiceNoleggio(String codiceNoleggio) {
		this.codiceNoleggio = codiceNoleggio;
	}
	public String getAuto() {
		return auto;
	}
	public void setAuto(String auto) {
		this.auto = auto;
	}
	public String getSocio() {
		return socio;
	}
	public void setSocio(String socio) {
		this.socio = socio;
	}
	public String getInizio() {
		return inizio;
	}
	public void setInizio(String inizio) {
		this.inizio = inizio;
	}
	public String getFine() {
		return fine;
	}
	public void setFine(String fine) {
		this.fine = fine;
	}
	public int getAutoRestituita() {
		return autoRestituita;
	}
	public void setAutoRestituita(int autoRestituita) {
		this.autoRestituita = autoRestituita;
	}
	@Override
	public String toString() {
		return "Socio [codiceNoleggio=" + codiceNoleggio + ", auto=" + auto + ", socio=" + socio + ", inizio=" + inizio
				+ ", fine=" + fine + ", autoRestituita=" + autoRestituita + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((auto == null) ? 0 : auto.hashCode());
		result = prime * result + autoRestituita;
		result = prime * result + ((codiceNoleggio == null) ? 0 : codiceNoleggio.hashCode());
		result = prime * result + ((fine == null) ? 0 : fine.hashCode());
		result = prime * result + ((inizio == null) ? 0 : inizio.hashCode());
		result = prime * result + ((socio == null) ? 0 : socio.hashCode());
		return result;
	}
}
