package Modelo;

public class Tablero {
	private Pokemon pokemon1;
	private Pokemon pokemon2;
	private SuperJugador jugador;
	private SuperJugador jugador2;
	private static Tablero mTablero;
	
	private Tablero() {
		this.jugador=null;
		this.jugador2=null;
		this.pokemon1=null;
		this.pokemon2=null;
	}
	
	public Tablero getMiTablero() {
		Tablero mTablero =new Tablero();
		if (Tablero.mTablero==null) {
			Tablero.mTablero=mTablero;
		}
		return Tablero.mTablero;
	}
	public void añadirPokemon(Pokemon pPokemon, SuperJugador pJugador) {
		if (this.jugador!=null) {
			this.jugador2=pJugador;
			this.pokemon2=pPokemon;
			GestorJuegoPokemon.getMiGestorJuegoPokemon().accionarAtaque(this.jugador2,this.pokemon2,this.pokemon1);
		}
	}
}
