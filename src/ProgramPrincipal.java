package Vista;

import Modelo.GestorJuegoPokemon;
import Modelo.Jugador;
import Vista.InterfazJuegoPokemon;
import Vista.InterfazJugador;

public class ProgramPrincipal {
	public static void main(String[] args) {
		System.out.println("Bienvenidos A Pokemon");
		GestorJuegoPokemon.getMiGestorJuegoPokemon();
		InterfazJuegoPokemon interfaz = new InterfazJuegoPokemon();
		
	}
}