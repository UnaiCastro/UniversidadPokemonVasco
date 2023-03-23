package Modelo;

import java.util.ArrayList;
import java.util.Iterator;


public class ListaJugadores {
	private ArrayList<SuperJugador> lJugadores;
	
	public ListaJugadores() {
		this.lJugadores = new ArrayList<SuperJugador>();
	}
	
	private Iterator<SuperJugador> getIterador(){
		return this.lJugadores.iterator();
	}
	
	public Jugador getJugador() {
		Iterator<SuperJugador> itr = this.getIterador();
		while (itr.hasNext()) {
			SuperJugador act = itr.next();
			if (act instanceof Jugador) {
				return (Jugador) act;
			}
		}
		return null;
	}
	
	public NPC getNPC() {
		Iterator<SuperJugador> itr = this.getIterador();
		while (itr.hasNext()) {
			SuperJugador act = itr.next();
			if (act instanceof NPC) {
				return (NPC) act;
			}
		}
		return null;
	}

}