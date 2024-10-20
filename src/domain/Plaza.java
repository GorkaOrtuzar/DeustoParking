package domain;

import java.util.Objects;

public class Plaza {
	private int piso;
	private String seccion;
	private int numPlaza;
	
	public Plaza() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Plaza(int piso, String seccion, int numPlaza) {
		super();
		this.piso = piso;
		this.seccion = seccion;
		this.numPlaza = numPlaza;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(numPlaza, piso, seccion);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plaza other = (Plaza) obj;
		return numPlaza == other.numPlaza && piso == other.piso && Objects.equals(seccion, other.seccion);
	}

	@Override
	public String toString() {
		return "Plaza [piso=" + piso + ", seccion=" + seccion + ", numPlaza=" + numPlaza + "]";
	}
	
	
	
}
