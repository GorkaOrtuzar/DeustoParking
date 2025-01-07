package domain;

import java.util.Date;
import java.util.Objects;

public class Reserva {
	
	private String ciudad;
	private String dni;
	private String matricula;
	private String nomParking;
	private Date hLlegada;
	private Date hSalida;
	private int numPlaza;
	private String seccion;
	private float precioTotal;
	
	public Reserva() {
		super();
	}

	public Reserva(String ciudad,Date hLlegada, Date hSalida) {
		super();
		this.ciudad = ciudad;
		this.hLlegada = hLlegada;
		this.hSalida = hSalida;
	}
	
	public Reserva(String ciudad, Date hLlegada, Date hSalida, String nomParking, int numPlaza, float precioTotal) {
		this.ciudad = ciudad;
		this.hLlegada = hLlegada;
		this.hSalida = hSalida;
		this.nomParking = nomParking;
		this.numPlaza = numPlaza;
		this.precioTotal = precioTotal;
	}
	
	public Reserva(String ciudad, String hLlegada, String hSalida, String nomParking, int numPlaza, float precioTotal) {
		this.ciudad = ciudad;
		this.hLlegada = Utilidades.stringToDate(hLlegada);
		this.hSalida = Utilidades.stringToDate(hSalida);
		this.nomParking = nomParking;
		this.numPlaza = numPlaza;
		this.precioTotal = precioTotal;
	}
	
	public Reserva(String nomParking, int numPlaza, float precioTotal) {
		super();
		this.nomParking = nomParking;
		this.numPlaza = numPlaza;
		this.precioTotal = precioTotal;
	}

	public Reserva(String ciudad, String dni, String matricula, String nomParking, String hLlegada, String hSalida,
			int numPlaza, String seccion, float precioTotal) {
		super();
		this.ciudad = ciudad;
		this.dni = dni;
		this.matricula = matricula;
		this.nomParking = nomParking;
		this.hLlegada = Utilidades.stringToDate(hLlegada);
		this.hSalida = Utilidades.stringToDate(hSalida);
		this.numPlaza = numPlaza;
		this.seccion = seccion;
		this.precioTotal = precioTotal;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNomParking() {
		return nomParking;
	}

	public void setNomParking(String nomParking) {
		this.nomParking = nomParking;
	}

	public Date gethLlegada() {
		return hLlegada;
	}

	public void sethLlegada(Date hLlegada) {
		this.hLlegada = hLlegada;
	}

	public Date gethSalida() {
		return hSalida;
	}

	public void sethSalida(Date hSalida) {
		this.hSalida = hSalida;
	}

	public int getNumPlaza() {
		return numPlaza;
	}

	public void setNumPlaza(int numPlaza) {
		this.numPlaza = numPlaza;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public float getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(float precioTotal) {
		this.precioTotal = precioTotal;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ciudad, dni, hLlegada, hSalida, matricula, nomParking, numPlaza, precioTotal, seccion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reserva other = (Reserva) obj;
		return Objects.equals(ciudad, other.ciudad) && Objects.equals(dni, other.dni)
				&& Objects.equals(hLlegada, other.hLlegada) && Objects.equals(hSalida, other.hSalida)
				&& Objects.equals(matricula, other.matricula) && Objects.equals(nomParking, other.nomParking)
				&& numPlaza == other.numPlaza
				&& Float.floatToIntBits(precioTotal) == Float.floatToIntBits(other.precioTotal)
				&& Objects.equals(seccion, other.seccion);
	}

	@Override
	public String toString() {
		return "Reserva [ciudad=" + ciudad + ", dni=" + dni + ", matricula=" + matricula + ", nomParking=" + nomParking
				+ ", hLlegada=" + hLlegada + ", hSalida=" + hSalida + ", numPlaza=" + numPlaza + ", seccion=" + seccion
				+ ", precioTotal=" + precioTotal + "]";
	}

}