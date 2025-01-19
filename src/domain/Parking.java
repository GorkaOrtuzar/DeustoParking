package domain;

import java.util.Objects;

public class Parking {
	private String parking;
	private Float precioHora;
	private int plazasLibres;
	
	public Parking() {
		super();
	}
	
	public Parking(String parking) {
		super();
		this.parking = parking;
	}
	
	public Parking(String parking, Float precioHora, int plazasLibres) {
		super();
		this.parking = parking;
		this.precioHora = precioHora;
		this.plazasLibres = plazasLibres;
	}

	public String getParking() {
		return parking;
	}

	public void setParking(String parking) {
		this.parking = parking;
	}

	public Float getPrecioHora() {
		return precioHora;
	}

	public void setPrecioHora(Float precioHora) {
		this.precioHora = precioHora;
	}

	public int getPlazasLibres() {
		return plazasLibres;
	}

	public void setPlazasLibres(int plazasLibres) {
		this.plazasLibres = plazasLibres;
	}

	@Override
	public int hashCode() {
		return Objects.hash(parking, plazasLibres, precioHora);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parking other = (Parking) obj;
		return Objects.equals(parking, other.parking) && plazasLibres == other.plazasLibres
				&& Objects.equals(precioHora, other.precioHora);
	}

	@Override
	public String toString() {
		return "Parking [Parking=" + parking + ", PrecioHora=" + precioHora + ", PlazasLibres=" + plazasLibres + "]";
	}
	
}