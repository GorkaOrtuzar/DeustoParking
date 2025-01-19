package domain;

import java.util.Objects;

public class Plaza {
	private String piso;
	private String seccion;
	private int numPlaza;
	private boolean ocupada;
	private boolean minusvalido;
	
	public Plaza() {
		super();
	}

	public Plaza(String piso, String seccion, int numPlaza, boolean ocupada, boolean minusvalido) {
		super();
		this.piso = piso;
		this.seccion = seccion;
		this.numPlaza = numPlaza;
		this.ocupada = ocupada;
		this.minusvalido = minusvalido;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
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

	public boolean isMinusvalido() {
		return minusvalido;
	}

	public void setMinusvalido(boolean minusvalido) {
		this.minusvalido = minusvalido;
	}

	@Override
	public int hashCode() {
		return Objects.hash(minusvalido, numPlaza, ocupada, piso, seccion);
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
		return minusvalido == other.minusvalido && numPlaza == other.numPlaza && ocupada == other.ocupada
				&& Objects.equals(piso, other.piso) && Objects.equals(seccion, other.seccion);
	}

	@Override
	public String toString() {
		return "Plaza [piso=" + piso + ", seccion=" + seccion + ", numPlaza=" + numPlaza + ", ocupada=" + ocupada
				+ ", minusvalido=" + minusvalido + "]";
	}

}