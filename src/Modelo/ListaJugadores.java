package Modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ListaJugadores {
	private ArrayList<SuperJugador> lJugadores;
	
	public ListaJugadores() {
		this.lJugadores = new ArrayList<SuperJugador>();
	}
	
	private Iterator<SuperJugador> getIterador(){
		return this.lJugadores.iterator();
	}//
	
	public ArrayList<SuperJugador> getMisJugadores(){
		return this.lJugadores;
	}//
	
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
		//System.out.println("Ha llegado la llamada a ListaJugadores");
		for(int i=0;i<numNPCs;i++) {
			int num=i+1;
			String nombre="NPC"+" " +num;
			NPC npc= new NPC(nombre);
			this.lJugadores.add(npc);
			//System.out.println("Se ha añadido:"+" "+npc.getNombre());
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
			//System.out.println("Se ha añadido:"+" "+jugador.getNombre());
			jugador.añadirPokemons(numPokemon);
		}
		
	}//

//	public void decirNombres() {
//		Iterator<SuperJugador> itr = this.getIterador();
//		while (itr.hasNext()) {
//			SuperJugador act = itr.next();
//			//System.out.println("Hola soy : "+act.getNombre());
//		}
//	}

//	public void verPokemons() {
//		Iterator<SuperJugador> itr = this.getIterador();
//		while (itr.hasNext()) {
//			SuperJugador act = itr.next();
//			//System.out.println("Hola soy : "+act.getNombre()+" y tengo "+act.equipoPokemon.getTamanoEquipo());
//		}
//	}
	
	public SuperJugador getSuperJugadorPosicion(int i) {
		return this.lJugadores.get(i);
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
		while(itr.hasNext()) {
			SuperJugador act=itr.next();
			act.setTurno(false);
//		for (int i=0;i<this.lJugadores.size();i++){
//			
//			SuperJugador act=itr.next();
//			if (act.getNombre().equals(pJugador.getNombre())) {
//				this.lJugadores.get(i).setTurno(false);
//				Random rand = new Random();
//				int indice=rand.nextInt(this.getMisJugadores().size());
//				this.lJugadores.get(indice).setTurno(true);
//				System.out.println("Es el turno de "+this.lJugadores.get(indice).getNombre());
//				break;
//			}
		}
		Random rand = new Random();
		int indice=rand.nextInt(this.getMisJugadores().size());
		this.lJugadores.get(indice).setTurno(true);
//		this.lJugadores.stream().forEach()
	}
	
	public SuperJugador getJugadorTurno() {
		Iterator<SuperJugador> itr=this.getIterador();
		SuperJugador aux=null;
		for (int i=0;i<this.lJugadores.size();i++){
			SuperJugador act=itr.next();
			if (act.turno) {
				aux=act;
				
			}
		}
		return aux;
	}

	public boolean hayGanador() {
		return ((int)this.lJugadores.stream().filter(p -> p.getDerrotado()).count() == this.lJugadores.size() - 1);		
	}

	public void mirarBotonCambio(String pJugador) {
		if (this.mirarJugador(pJugador) instanceof Jugador) {//Mirar quien lo ha pulsado
			this.mirarJugador(pJugador).getMiEquipo().ponerPokeAtacados();
			this.cambiarTurno(this.mirarJugador(pJugador));//Cambias Turno
			SuperJugador npc_jug=this.getJugadorTurno();
			while(npc_jug instanceof NPC) {
				npc_jug.atacarN();
				this.cambiarTurno(npc_jug);
				npc_jug=this.getJugadorTurno();
			}

		}else {
			System.out.println("Soy NPC, no puedes darle aqui");
		}
	}
	
}