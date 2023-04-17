package Modelo;

import java.util.Random;

public class NPC extends SuperJugador{
	
	public NPC(String pNombre) {
		this.equipoPokemon = new Equipo();
		this.nombre= pNombre;
	}
	
	public void setNombre(String pJugador) {
		this.nombre=pJugador;
	}
	
	public void setTurno(Boolean pTurno) {
		this.turno=pTurno;
	}

	
	public void atacarN(String pNombre) {
		System.out.println("Ataca " +this.nombre);
		for (int i=0;i<this.equipoPokemon.getTamanoEquipo();i++) {
			boolean enc=false;
			int iJugador=0;
			while (!enc) {
				Random rand1 = new Random();
				int minimo1 = 1;
				int maximo1 = GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().getSize();
				int numeroAleatorio1 = rand1.nextInt((maximo1 - minimo1) + 1) + minimo1;
				if (!GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().getSuperJugadorPosicion(numeroAleatorio1-1).getNombre().equals(pNombre)) {
					enc=true;
					iJugador=numeroAleatorio1-1;
        	
				}
			}
			Random rand2 = new Random();
			int minimo2 = 1;
			int maximo2 = this.equipoPokemon.getTamanoEquipo();
			int numeroAleatorio2 = rand2.nextInt((maximo2 - minimo2) + 1) + minimo2;
			Tablero.getMiTablero().setJugadorAtacante(this);
			Tablero.getMiTablero().setAtacantePokemon(this.getPokemon(i));
			Tablero.getMiTablero().setDefendsPlayer(GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().getSuperJugadorPosicion(iJugador));
			Tablero.getMiTablero().setDefensaPokemon(GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().getSuperJugadorPosicion(iJugador).getPokemon(numeroAleatorio2-1));
			System.out.println("Jugador Defensor "+Tablero.getMiTablero().getJugador2().getNombre()+" Jugador Atacante "+Tablero.getMiTablero().getJugador1().getNombre()+" Pokemon Defensor "+Tablero.getMiTablero().getPokemon2().getNombre()+" Pokemon Atacante"+Tablero.getMiTablero().getPokemon1().getNombre());
			Tablero.getMiTablero().atacar();
		}
	}
	

}
