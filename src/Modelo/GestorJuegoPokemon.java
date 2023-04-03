package Modelo;

import java.util.ArrayList;
import java.util.Observable;

import Vista.InterfazJuegoPokemon;
import Vista.InterfazJugador;

public class GestorJuegoPokemon extends Observable{
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

	

	public void empieza(int numPlayers, int numNPCs, int numPokemon) {
		this.jugadores.registrar(numPlayers, numNPCs,numPokemon);
		ArrayList<Object> report = new ArrayList<Object>();
		report.add(this.jugadores.getMisJugadores());
		this.setChanged();
		this.notifyObservers(report);
	}//
	
	public void addObserverJuego(InterfazJuegoPokemon interfazJuegoPokemon) {
		this.addObserver(interfazJuegoPokemon);
		
	}//

//	public void accionarAtaque(String jugador2, int pokemon2, String jugador1, int pokemon1) {
//		this.jugadores.buscarYAtacar(jugador2,pokemon2,jugador1,pokemon1);
//		
//	}

	public boolean mirarTurno(String player) {
		return this.jugadores.mirarTurno(player);
	}

	public SuperJugador mirarJugador(String name) {
		return this.jugadores.mirarJugador(name);
	
	}

	public ListaJugadores getLista() {
		return this.jugadores;
	}//

	public void comprobarVictoria(SuperJugador jugador2) {
		this.jugadores.comprobarVictoria(jugador2);
	}//
	
}
