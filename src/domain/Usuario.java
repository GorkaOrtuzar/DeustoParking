package domain;

import java.util.Objects;

public class Usuario {
	private String nombre;
	private String apellido;
	private String tlf;
	private String dni;
	private String contrasenia;
	
	public Usuario() {
		super();
	}

	public Usuario(String nombre, String apellido, String tlf, String dni, String contrasenia) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.tlf = tlf;
		this.dni = dni;
		this.contrasenia = contrasenia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTlf() {
		return tlf;
	}

	public void setTlf(String tlf) {
		this.tlf = tlf;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellido, contrasenia, dni, nombre, tlf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(apellido, other.apellido) && Objects.equals(contrasenia, other.contrasenia)
				&& Objects.equals(dni, other.dni) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(tlf, other.tlf);
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", apellido=" + apellido + ", tlf=" + tlf + ", dni=" + dni
				+ ", contrasenia=" + contrasenia + "]";
	}
	
}