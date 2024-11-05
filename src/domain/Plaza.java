package domain;

import java.util.Objects;

public class Plaza {
	private int piso;
	private String seccion;
	private int numPlaza;
	private boolean ocupada;
	
	public Plaza() {
		super();
	}

	public Plaza(int piso, String seccion, int numPlaza, boolean ocupada) {
		super();
		this.piso = piso;
		this.seccion = seccion;
		this.numPlaza = numPlaza;
		this.ocupada = ocupada;
	}

	public int getPiso() {
		return piso;
	}

	public void setPiso(int piso) {
		this.piso = piso;
	}

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public int getNumPlaza() {
		return numPlaza;
	}

	public void setNumPlaza(int numPlaza) {
		this.numPlaza = numPlaza;
	}

	public boolean isOcupada() {
		return ocupada;
	}

	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}

	@Override
	public int hashCode() {
		return Objects.hash(numPlaza, ocupada, piso, seccion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plaza other = (Plaza) obj;
		return numPlaza == other.numPlaza && ocupada == other.ocupada && piso == other.piso
				&& Objects.equals(seccion, other.seccion);
	}

	@Override
	public String toString() {
		return "Plaza [piso=" + piso + ", seccion=" + seccion + ", numPlaza=" + numPlaza + ", ocupada=" + ocupada + "]";
	}
	
	
	
	
}
