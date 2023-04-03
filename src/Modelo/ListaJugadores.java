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
	
	public ArrayList<SuperJugador> getMisJugadores(){
		return this.lJugadores;
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
			NPC npc= new NPC(nombre);
			this.lJugadores.add(npc);
			System.out.println("Se ha añadido:"+" "+npc.getNombre());
			//npc.setNombre(nombre);
			npc.añadirPokemons(numPokemon);
		}
		for(int i=0;i<numPlayers;i++) {
			int num=i+1;
			String nombre="Jugador"+" " +num;
			Jugador jugador= new Jugador(nombre);
			this.lJugadores.add(jugador);
			if (jugador.getNombre().equals("Jugador 1")){
				Boolean pTurno=true;
				jugador.setTurno(pTurno);
			}					
			System.out.println("Se ha añadido:"+" "+jugador.getNombre());
			jugador.añadirPokemons(numPokemon);
		}
		
	}

	public void decirNombres() {
		Iterator<SuperJugador> itr = this.getIterador();
		while (itr.hasNext()) {
			SuperJugador act = itr.next();
			//System.out.println("Hola soy : "+act.getNombre());
		}
	}

	public void verPokemons() {
		Iterator<SuperJugador> itr = this.getIterador();
		while (itr.hasNext()) {
			SuperJugador act = itr.next();
			//System.out.println("Hola soy : "+act.getNombre()+" y tengo "+act.equipoPokemon.getTamanoEquipo());
		}
	}
	
	public SuperJugador getSuperJugadorPosicion(int i) {
		return this.lJugadores.get(i);
	}

	public void buscarYAtacar(String jugador2, int pokemon2, String jugador1, int pokemon1) {
		//HACER
		
	}

	public boolean mirarTurno(String player) {
		Iterator<SuperJugador> itr=this.getIterador();
		SuperJugador aux=null;
		while(itr.hasNext()) {
			SuperJugador act=itr.next();
			if (act.getNombre().equals(player)){
				
				aux=act;
				
			}
		}
		return aux.turno;
		
	}

	public SuperJugador mirarJugador(String name) {
		Iterator<SuperJugador> itr=this.getIterador();
		SuperJugador aux=null;
		while(itr.hasNext()) {
			SuperJugador act=itr.next();
			if (act.getNombre().equals(name)){
				
				aux=act;
				
			}
		}
		return aux;
		
	}

	public void cambiarTurno(SuperJugador pJugador) {
		Iterator<SuperJugador> itr=this.getIterador();
		int ind=0;
		for (int i=0;i<this.lJugadores.size();i++){
			SuperJugador act=itr.next();
			if (act.getNombre().equals(pJugador.getNombre())) {
				ind=i;
				this.lJugadores.get(i).setTurno(false);
				break;
			}
		}
		ind=ind+1;
		if (ind==this.lJugadores.size()) {
			ind=0;
			this.lJugadores.get(ind).setTurno(true);
		}else {
			this.lJugadores.get(ind).setTurno(true);
		}
	}
	
}