package Modelo;

import java.util.Random;

public class NPC extends SuperJugador{
	private Random rand1 = new Random();
	public NPC(String pNombre) {
		this.equipoPokemon = new Equipo();
		this.nombre= pNombre;
	}
	
//	public void setNombre(String pJugador) {
//		this.nombre=pJugador;
//	}
//	
//	public void setTurno(Boolean pTurno) {
//		this.turno=pTurno;
//	}

	
	public void atacarN() {
		System.out.println("Ataca " +this.nombre);
		for (int i=0;i<this.equipoPokemon.getTamanoEquipo();i++) {
			Tablero.getMiTablero().setJugadorAtacante(this);
			if (!this.getPokemon(i).estaMuerto()) {
				Tablero.getMiTablero().setAtacantePokemon(this.getPokemon(i));
				boolean enc=false;
				int iJugador=0;
				while (!enc) {
					int minimo1 = 1;
					int maximo1 = GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().getSize();
					int numeroAleatorio1 = rand1.nextInt((maximo1 - minimo1) + 1) + minimo1;
					if (!GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().getSuperJugadorPosicion(numeroAleatorio1-1).getNombre().equals(this.nombre)&& !GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().getSuperJugadorPosicion(numeroAleatorio1-1).getDerrotado()) {
						enc=true;
						iJugador=numeroAleatorio1-1;
						Tablero.getMiTablero().setDefendsPlayer(GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().getSuperJugadorPosicion(iJugador));
        	
					}
				}
				boolean enc1=false;
				int iPokemon=0;
				while (!enc1) {
					int minimo2 = 1;
					int maximo2 = GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().getSuperJugadorPosicion(iJugador).equipoPokemon.getTamanoEquipo();
					int numeroAleatorio2 = rand1.nextInt((maximo2 - minimo2) + 1) + minimo2;
					if (!GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().getSuperJugadorPosicion(iJugador).getNombre().equals(this.nombre)&& !GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().getSuperJugadorPosicion(iJugador).getPokemon(numeroAleatorio2-1).estaMuerto()) {
						enc1=true;
						iPokemon=numeroAleatorio2-1;
						Tablero.getMiTablero().setDefensaPokemon(GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().getSuperJugadorPosicion(iJugador).getPokemon(iPokemon));
						System.out.println("Jugador Defensor "+Tablero.getMiTablero().getJugador2().getNombre()+" Jugador Atacante "+Tablero.getMiTablero().getJugador1().getNombre()+" Pokemon Defensor "+Tablero.getMiTablero().getPokemon2().getNombre()+" Pokemon Atacante"+Tablero.getMiTablero().getPokemon1().getNombre());
						Tablero.getMiTablero().atacar();
					}
				}
			}else {
				System.out.println("Esta muerto, al siguiente");
			}
		}
	}

}
