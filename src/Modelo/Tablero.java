package Modelo;

import javax.swing.JOptionPane;

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

	public void setJugadorAtacante(SuperJugador nombrePlayer) {
		this.jugador1=nombrePlayer;
		
	}//

	public void setAtacantePokemon(Pokemon pPokemon) {
		this.pokemon1=pPokemon;
		
	}//

	public void setDefendsPlayer(SuperJugador pJugador) {
		this.jugador2=pJugador;
		
	}//

	public void setDefensaPokemon(Pokemon pokemon) {
		this.pokemon2=pokemon;
		
	}//
	
	public SuperJugador getJugador1() {
		return this.jugador1;
	}//
	

	public Pokemon getPokemon1() {
		return pokemon1;
	}//


	public Pokemon getPokemon2() {
		return pokemon2;
	}//

	public SuperJugador getJugador2() {
		return jugador2;
	}//


	public void atacar() {
		if ((this.jugador1 != null) && (this.jugador2 != null)){
			int vidaPokeAtacado = this.pokemon2.gestionAtaque(this.pokemon1.getAtaque(), this.pokemon1.getTipo());
			System.out.println("Ya se ha registrado el ataque");
//			int vida= pokemon2.getVida()+ pokemon2.getDefensa()- pokemon1.getAtaque();
//			int i=jugador2.getPosPokemon(pokemon2);
			if (vidaPokeAtacado <= 0) {
				System.out.println("Pokemon Eliminado");
			}
			if (jugador2.equipoPokemon.todosMueertos()) {
				jugador2.setderrotado(true);
				System.out.println("Has muerto "+" "+jugador2.nombre);
			}
			this.pokemon1.bajarEuforia();
//			System.out.println("Antes de atacar tiene de vida :"+jugador2.equipoPokemon.getPokemon(i).getVida());
//			jugador2.bajarVidaPokemon(i,vida);
//			System.out.println("Ahora tiene de vida :"+jugador2.equipoPokemon.getPokemon(i).getVida());
//			jugador2.actuEuforia(i);
//			pokemon2.actuEuforia();
//			if(pokemon1.getEuforia()==pokemon1.getEuforiaMax()) {
//				pokemon1.bajarEuforia();
//			}
//			jugador2.verEuforia(i);
			this.pokemon1.setAtaca(true);
			if (GestorJuegoPokemon.getMiGestorJuegoPokemon().hayGanador()) {
				this.jugador1.setderrotado(false);
				JOptionPane.showMessageDialog(null, "¡Se ha acabado, ganaste!");
				System.exit(0);
			}
			
		}
		this.limpiar();
	}//
	
	public void limpiar() {
		this.jugador1=null;
		this.jugador2=null;
		this.pokemon1=null;
		this.pokemon2=null;
	}
	
}
