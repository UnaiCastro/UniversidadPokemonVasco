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
		//System.out.println("Ha llegado la llamada a GestorJuego");
		this.jugadores.registrar(numPlayers, numNPCs,numPokemon);
		//this.jugadores.decirNombres();
		//this.jugadores.verPokemons();
		//int tamaño=this.jugadores.getSize();
		//System.out.println("Tmaño= "+tamaño);
		ArrayList<Object> report = new ArrayList<Object>();
		report.add(this.jugadores.getMisJugadores());
		//report.add(false);
		//int si=report.size();
		//System.out.println("Hay "+si);
		//report.add(this.jugadores.get)
		//report.add(this.jugadores.getSize());
		//report.add(this.jugadores.getJugador().getMiEquipo());
		//report.add(this.jugadores.getJugador().getNombre());
		this.setChanged();
		this.notifyObservers(report);
	}

//	public void addObserver(InterfazJugador interfazJugador) {
//		this.addObserver(interfazJugador);
//		
//	}
	
	public void addObserverJuego(InterfazJuegoPokemon interfazJuegoPokemon) {
		this.addObserver(interfazJuegoPokemon);
		
	}

	public void accionarAtaque(SuperJugador jugador2, Pokemon pokemon2, Pokemon pokemon) {
		this.jugadores.buscarYAtacar(jugador2,pokemon2,pokemon);
		
	}
	
}
