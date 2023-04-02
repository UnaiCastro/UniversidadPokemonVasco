package Vista;

import Modelo.GestorJuegoPokemon;

public class ProgramPrincipal {
	public static void main(String[] args) {
		System.out.println("Bienvenidos A Pokemon");
		GestorJuegoPokemon.getMiGestorJuegoPokemon();
		InterfazJuegoPokemon interfaz = new InterfazJuegoPokemon();
		//String player="Player";
		//int pokemon= 1;
		//int jugador=1;
		//InterfazJugador interfazJugador = new InterfazJugador(player,pokemon,jugador);
	}
}
