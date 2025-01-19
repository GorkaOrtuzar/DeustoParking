package domain;

import java.util.ArrayList;
import java.util.List;


public class Contenedora {

	private static List<Usuario> lUsuarios;
	private static List<Reserva> lReservas;
	
	public static List<Usuario> getlUsuarios() {
		return lUsuarios;
	}
	
	public static List<Reserva> getlReservas() {
		return lReservas;
	}

	public static void setlReservas(List<Reserva> lReservas) {
		Contenedora.lReservas = lReservas;
	}

	public static void aniadirUsuario(Usuario u) {
		lUsuarios.add(u);
	}
	
	public static void aniadirReserva(Reserva r) {
		lReservas.add(r);
	}
	
	public static Usuario buscarUsuario(String DNI) {
		boolean enc = false;
		int pos = 0;
		Usuario u = null;
		while (!enc && pos < lUsuarios.size()) {
			u = lUsuarios.get(pos);
			if (u.getDni().equals(DNI)) {
				enc = true;
			} else {
				pos++;
			}
		}
		if (enc) {
			return u;
		} else {
			return null;
		}
	}
	public static List<String> obtenerCombinacionesAparcamiento(int plazasLibres) {
        List<String> resultados = new ArrayList<>();
        generarCombinaciones(plazasLibres, "", resultados);
        return resultados;
    }

    private static void generarCombinaciones(int plazasLibres, String combinacionActual, List<String> resultados) {
        if (plazasLibres == 0) {
            resultados.add(combinacionActual);
            return;
        }
        generarCombinaciones(plazasLibres - 1, combinacionActual+ " |APARCAR| " , resultados); 
        generarCombinaciones(plazasLibres - 1, combinacionActual+ " |NO APARCAR| ", resultados); 
    }

}