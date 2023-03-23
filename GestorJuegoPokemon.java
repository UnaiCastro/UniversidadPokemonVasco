package Modelo;

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
}
