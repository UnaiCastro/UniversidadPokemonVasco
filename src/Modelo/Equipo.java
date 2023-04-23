package Modelo;

import java.util.Iterator;
import java.util.Random;

import javax.swing.JOptionPane;

import java.util.ArrayList;

public class Equipo {
	private ArrayList<Pokemon> lPokemon;
	private Random rand = new Random();

	public Equipo() {
		this.lPokemon = new ArrayList<Pokemon>();
	}
	
	public int getTamanoEquipo() {
		return this.lPokemon.size();
	}//
	
	private Iterator<Pokemon> getIterador(){
		return this.lPokemon.iterator();
	}//
	
	public void anadirPokemon(int nPokemon){
		//this.lPokemon.add(pPokemon);
		System.out.println("Hola estas en el Equipo :)");
		for (int i=0;i<nPokemon;i++) {
			int real=i+1;
			this.lPokemon.add(PokemonFactory.getMiArmaFactory().createPokemon(rand.nextInt(4)+1,"Pokemon "+real));
			//System.out.println("Creado Pokemon numero"+" "+real);
		}
		//this.comprobarLista();
		
	}//
	public void comprobarLista() {
		Iterator<Pokemon> itr=this.getIterador();
		while (itr.hasNext()) {
			Pokemon act =itr.next();
			System.out.println("Hola soy :"+act.getNombre()+" y soy de tipo "+act.tipo+" y tengo estas caracteristicas: Ataque "+act.getAtaque()+" Defensa: "+act.getDefensa()+" y Vida: "+act.getVida());
		}
	}//

	public Pokemon getPokemon(int lugarPokemon) {
		return this.lPokemon.get(lugarPokemon);
	}



	public int getPosPoke(Pokemon pokemon2) {
		int ind=0;
		int indF=0;
		while(ind<this.lPokemon.size()) {
			if (this.lPokemon.get(ind).equals(pokemon2)) {
				indF=ind;
			}
			ind++;
		}
		return indF;
	}
	


	public void ponerPokeAtacados() {
		Iterator<Pokemon> itr=this.getIterador();
		while(itr.hasNext()) {
			Pokemon act=itr.next();
			act.setAtaca(false);
		}
		System.out.println("Tus Pokemons pueden atacar de nuevo");
	}

	public void bajarVidaP(int indice,int pVida) {
		Pokemon pPokemon=this.getPokemon(indice);
		pPokemon.bajarVida(pVida);
		
	}
	
	public boolean todosMueertos() {
		return this.lPokemon.stream().allMatch(p -> p.estaMuerto());
	}

	public void actuEuforia(int i) {
		this.getPokemon(i).actuEuforia();
		
	}

	public int getPosPokeObservable(String nombre) {
		int ind=0;
		int indF=0;
		while(ind<this.lPokemon.size()) {
			if (this.lPokemon.get(ind).getNombre().equals(nombre)) {
				indF=ind;
			}
			ind++;
		}
		return indF;
	}
}