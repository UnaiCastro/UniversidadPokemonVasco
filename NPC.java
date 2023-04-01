package Modelo;

import java.util.Observable;

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

}
