package Modelo;

import Vista.InterfazJuegoPokemon;
import Vista.InterfazJugador;

public class GestorJuegoPokemon {
	private static GestorJuegoPokemon miGestorJuego;
	private ListaJugadores jugadores;
	
	private GestorJuegoPokemon() {
		this.jugadores = new ListaJugadores();
	}
	
	public static GestorJuegoPokemon getMiGestorJuegoPokemon() {
		if(miGestorJuego == null) {
			miGestorJuego = new GestorJuegoPokemon();
		}
		return miGestorJuego;
	}

	public void addObserver(InterfazJuegoPokemon interfazJuegoPokemon) {
		// TODO Auto-generated method stub
		
	}

	//public void empieza(int numPlayers, int numNPCs, int numPokemon) {
		//this.jugadores.registrar(numPlayers, numNPCs,numPokemon);
		
	//}

	public void addObserver(InterfazJugador interfazJugador) {
		// TODO Auto-generated method stub
		
	}
}
