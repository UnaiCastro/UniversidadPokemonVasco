package Modelo;

public class Tablero {
	private Pokemon pokemon1;
	private Pokemon pokemon2;
	private SuperJugador jugador1;
	private SuperJugador jugador2;
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
//	public void añadirPokemon(int i,String pPlayer) {
//		if (this.jugador1!=null) {
//			System.out.println("Vamos a atacar");
//			this.jugador2=pPlayer;
//			this.pokemon2=i;
//			System.out.println("Vamos a Gestor");
//			GestorJuegoPokemon.getMiGestorJuegoPokemon().accionarAtaque(jugador2, pokemon2, jugador1, pokemon1);
//		} else {
//			System.out.println("Esperamos tu eleccion");
//			this.jugador1=pPlayer;
//			this.pokemon1=i;
//		}
//	}

	public boolean mirarTurno(String player) {
		return GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarTurno(player);
		
	}

	public void setJugadorAtacante(SuperJugador nombrePlayer) {
		this.jugador1=nombrePlayer;
		
	}

	public void setAtacantePokemon(Pokemon pPokemon) {
		this.pokemon1=pPokemon;
		
	}

	public void setDefendsPlayer(SuperJugador pJugador) {
		this.jugador2=pJugador;
		
	}

	public void setDefensaPokemon(Pokemon pokemon) {
		this.pokemon2=pokemon;
		
	}
	
	public SuperJugador getJugador1() {
		return this.jugador1;
	}
	

	public Pokemon getPokemon1() {
		return pokemon1;
	}


	public Pokemon getPokemon2() {
		return pokemon2;
	}

	public SuperJugador getJugador2() {
		return jugador2;
	}


	public void atacar() {
		if ((this.jugador1 != null) && (this.jugador2 != null) && (this.pokemon1 != null) && (this.pokemon2 != null)){
			int vida= pokemon2.getVida()+pokemon2.getDefensa()-pokemon1.getAtaque();
//			System.out.println(" "+pokemon2.getVida()+" "+pokemon2.getDefensa()+" "+pokemon1.getAtaque()+" "+vida);
			int i=jugador2.getPosPokemon(pokemon2);
			System.out.println("Antes de atacar tiene de vida :"+jugador2.equipoPokemon.getPokemon(i).getVida());
			jugador2.equipoPokemon.getPokemon(i).bajarVida(vida);
			System.out.println("Ahora tiene de vida :"+jugador2.equipoPokemon.getPokemon(i).getVida());
//			this.cambiarTurno(jugador1);
			GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().cambiarTurno(jugador1);
		}
		
	}
	
//	public void cambiarTurno(SuperJugador pJugador) {
//		GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().cambiarTurno(pJugador);
//	}
	
}
