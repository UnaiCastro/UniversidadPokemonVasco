package Vista;

import Modelo.GestorJuegoPokemon;
import Modelo.Jugador;
import Vista.InterfazJuegoPokemon;

public class ProgramPrincipal {
	public static void main(String[] args) {
		GestorJuegoPokemon.getMiGestorJuegoPokemon();
		InterfazJuegoPokemon interfaz = new InterfazJuegoPokemon();
	}
}