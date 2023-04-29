package Modelo;

import java.util.ArrayList;
import java.util.Observable;

import Vista.InterfazJuegoPokemon;
//import Vista.InterfazJugador;

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


	public boolean mirarTurno(String player) {
		return this.jugadores.mirarTurno(player);
	}

	public SuperJugador mirarJugador(String name) {
		return this.jugadores.mirarJugador(name);
	
	}

	public ListaJugadores getLista() {
		return this.jugadores;
	}//

	public boolean hayGanador() {
		return this.jugadores.hayGanador();
	}

	public void administraAtaque(int ind, SuperJugador pJugador) {
		if (!pJugador.getDerrotado()) {
			if (this.mirarTurno(pJugador.getNombre())) {
				if (!pJugador.equipoPokemon.getPokemon(ind).estaMuerto()) {
					if (!pJugador.equipoPokemon.getPokemon(ind).getAtaca()) {
						Tablero.getMiTablero().setAtacantePokemon(pJugador.equipoPokemon.getPokemon(ind));
						Tablero.getMiTablero().setJugadorAtacante(pJugador);
						System.out.println("El jugador atacante es "+Tablero.getMiTablero().getJugador1().getNombre()+" y el pokemon es el "+Tablero.getMiTablero().getPokemon1().getNombre());
					}else {
						System.out.println("Este Pokemon ya ha atacado");
					}
				}else {
					System.out.println("Este pokemon esta muerto");
				}
			} else {
				if (!pJugador.equipoPokemon.getPokemon(ind).estaMuerto()) {
					Tablero.getMiTablero().setDefensaPokemon(pJugador.equipoPokemon.getPokemon(ind));
					Tablero.getMiTablero().setDefendsPlayer(pJugador);
					System.out.println("El jugador defensor es "+Tablero.getMiTablero().getJugador2().getNombre()+" y el pokemon es el "+Tablero.getMiTablero().getPokemon2().getNombre());
					Tablero.getMiTablero().atacar();
				}else {
					System.out.println("Este pokemon esta muerto");
				}
			}
		}else {
			System.out.println("Este Jugador esta muerto, selecciona otro");
		}
		
	}
	
}
