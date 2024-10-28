package domain;

import java.time.LocalDate;
import java.util.Objects;

public class Reserva {
	
	private String Ciudad;
	private String dni;
	private int matricula;
	private String nomParking;
	private String hLlegada;
	private String hSalida;
	private int numPlaza;
	private int precioTotal;
	
	
	
	
	public Reserva() {
		super();
	}



	public Reserva(String Ciudad,String hLlegada, String hSalida) {
		super();
		this.Ciudad = Ciudad;
		this.hLlegada = hLlegada;
		this.hSalida = hSalida;
	}



	public String getDni() {
		return dni;
	}



	public void setDni(String dni) {
		this.dni = dni;
	}



	public int getMatricula() {
		return matricula;
	}



	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}



	public String getNomParking() {
		return nomParking;
	}



	public void setNomParking(String nomParking) {
		this.nomParking = nomParking;
	}



	public String gethLlegada() {
		return hLlegada;
	}



	public void sethLlegada(String hLlegada) {
		this.hLlegada = hLlegada;
	}



	public String gethSalida() {
		return hSalida;
	}



	public void sethSalida(String hSalida) {
		this.hSalida = hSalida;
	}



	public int getNumPlaza() {
		return numPlaza;
	}



	public void setNumPlaza(int numPlaza) {
		this.numPlaza = numPlaza;
	}



	public String getCiudad() {
		return Ciudad;
	}



	public void setCiudad(String ciudad) {
		Ciudad = ciudad;
	}

	


	public int getPrecioTotal() {
		return precioTotal;
	}



	public void setPrecioTotal(int precioTotal) {
		this.precioTotal = precioTotal;
	}



	@Override
	public int hashCode() {
		return Objects.hash(Ciudad, dni, hLlegada, hSalida, matricula, nomParking, numPlaza, precioTotal);
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
		return Objects.equals(Ciudad, other.Ciudad) && Objects.equals(dni, other.dni)
				&& Objects.equals(hLlegada, other.hLlegada) && Objects.equals(hSalida, other.hSalida)
				&& matricula == other.matricula && Objects.equals(nomParking, other.nomParking)
				&& numPlaza == other.numPlaza && precioTotal == other.precioTotal;
	}



	@Override
	public String toString() {
		return "Reserva [Ciudad=" + Ciudad + ", dni=" + dni + ", matricula=" + matricula + ", nomParking=" + nomParking
				+ ", hLlegada=" + hLlegada + ", hSalida=" + hSalida + ", numPlaza=" + numPlaza + ", precioTotal="
				+ precioTotal + "]";
	}


	
	


	
	
	
	
	
	

}