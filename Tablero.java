package Modelo;

public class Tablero {
	private int pokemon1;
	private int pokemon2;
	private String jugador1;
	private String jugador2;
	private static Tablero mTablero;
	
	private Tablero() {
		
	}
	
	public static Tablero getMiTablero() {
		Tablero mTablero =new Tablero();
		if (Tablero.mTablero==null) {
			Tablero.mTablero=mTablero;
		}
		return Tablero.mTablero;
	}
	public void añadirPokemon(int i,String pPlayer) {
		if (this.jugador1!=null) {
			System.out.println("Vamos a atacar");
			this.jugador2=pPlayer;
			this.pokemon2=i;
			System.out.println("Vamos a Gestor");
			GestorJuegoPokemon.getMiGestorJuegoPokemon().accionarAtaque(jugador2, pokemon2, jugador1, pokemon1);
		} else {
			System.out.println("Esperamos tu eleccion");
			this.jugador1=pPlayer;
			this.pokemon1=i;
		}
	}

	public boolean mirarTurno(String player) {
		return GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarTurno(player);
		
	}
	
}
