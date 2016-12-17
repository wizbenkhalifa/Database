
public class Auto {
	private String targa;
	private String marca;
	private String modello;
	private Double costoGiornaliero;
	public Auto(String targa, String marca, String modello, Double costoGiornaliero) {
		super();
		this.targa = targa;
		this.marca = marca;
		this.modello = modello;
		this.costoGiornaliero = costoGiornaliero;
	}
	public String getTarga() {
		return targa;
	}
	
	public void setTarga(String targa) {
		this.targa = targa;
	}
	
	public String getMarca() {
		return marca;
	}
	
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public String getModello() {
		return modello;
	}
	
	public void setModello(String modello) {
		this.modello = modello;
	}
	
	public Double getCostoGiornaliero() {
		return costoGiornaliero;
	}
	
	public void setCostoGiornaliero(Double costoGiornaliero) {
		this.costoGiornaliero = costoGiornaliero;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((costoGiornaliero == null) ? 0 : costoGiornaliero.hashCode());
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((modello == null) ? 0 : modello.hashCode());
		result = prime * result + ((targa == null) ? 0 : targa.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Auto other = (Auto) obj;
		if (costoGiornaliero == null) {
			if (other.costoGiornaliero != null)
				return false;
		} else if (!costoGiornaliero.equals(other.costoGiornaliero))
			return false;
		if (marca == null) {
			if (other.marca != null)
				return false;
		} else if (!marca.equals(other.marca))
			return false;
		if (modello == null) {
			if (other.modello != null)
				return false;
		} else if (!modello.equals(other.modello))
			return false;
		if (targa == null) {
			if (other.targa != null)
				return false;
		} else if (!targa.equals(other.targa))
			return false;
		return true;
	}
	
	
}
