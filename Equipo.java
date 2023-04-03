package Modelo;

import java.util.Iterator;

import javax.swing.JOptionPane;

import java.util.ArrayList;

public class Equipo {
	private ArrayList<Pokemon> lPokemon;

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
			this.lPokemon.add(PokemonFactory.getMiArmaFactory().createPokemon("Normal","Pokemon"+" "+real));
			//System.out.println("Creado Pokemon numero"+" "+real);
		}
		this.comprobarLista();
		
	}//
	public void comprobarLista() {
		Iterator<Pokemon> itr=this.getIterador();
		while (itr.hasNext()) {
			Pokemon act =itr.next();
			System.out.println("Hola soy :"+act.getNombre()+"y soy de tipo "+act.tipo+" y tengo estas caracteristicas: Ataque "+act.ataque+" Defensa: "+act.defensa+" y Vida: "+act.vida);
		}
	}//

	public Pokemon getPokemon(int lugarPokemon) {
		return this.lPokemon.get(lugarPokemon);
	}

	public void bajarVida(int vida, Pokemon pokemon2) {
		Iterator<Pokemon> itr=this.getIterador();
		while (itr.hasNext()) {
			Pokemon act=itr.next();
			if (act.equals(pokemon2)) {
				act.vida=act.vida-vida;
			}
		}
		
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
	
	public void comprobarVictoria() {
		boolean vic=true;
		Iterator<Pokemon> itr=this.getIterador();
		while(itr.hasNext()) {
			Pokemon act= itr.next();
			if (act.getVida()!=0) {
				vic=false;
			}
		}
		if(vic==false) {
			System.out.println("No ha ganado nadie, seguir!!");
		}else {
			System.out.println("Se acabo la partida, Enhorabuena");
			JOptionPane.showMessageDialog(null, "¡Has ganado!");
		}
	}//
}