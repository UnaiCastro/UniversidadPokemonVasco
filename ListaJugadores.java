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
	
	public int getSize() {
		return this.lJugadores.size();
	}
	
	
	public void registrar(int numPlayers, int numNPCs, int numPokemon) {
		System.out.println("Ha llegado la llmada a ListaJugadores");
		for(int i=0;i<numNPCs;i++) {
			int num=i+1;
			String nombre="NPC"+" " +num;
			NPC npc= new NPC();
			this.lJugadores.add(npc);
			//System.out.println("Se ha añadido:"+" "+nombre);
			npc.setNombre(nombre);
			npc.añadirPokemons(numPokemon);
		}
		for(int i=0;i<numPlayers;i++) {
			int num=i+1;
			String nombre="Jugador"+" " +num;
			Jugador jugador= new Jugador();
			this.lJugadores.add(jugador);
			//System.out.println("Se ha añadido:"+" "+nombre);
			jugador.setNombre(nombre);
			jugador.añadirPokemons(numPokemon);
		}
		int jug= this.getSize();
		//System.out.println("Hay"+" "+jug+" ");
		
	}
	
}