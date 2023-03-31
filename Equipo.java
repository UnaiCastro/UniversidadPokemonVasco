package Modelo;

import java.util.Iterator;
import java.util.ArrayList;

public class Equipo {
	private ArrayList<Pokemon> lPokemon;

	public Equipo() {
		this.lPokemon = new ArrayList<Pokemon>();
	}
	
	public int getTamanoEquipo() {
		return this.lPokemon.size();
	}
	
	private Iterator<Pokemon> getIterador(){
		return this.lPokemon.iterator();
	}
	
	public void anadirPokemon(int nPokemon){
		//this.lPokemon.add(pPokemon);
		//System.out.println("Hola estas en el Equipo :)");
		for (int i=0;i<nPokemon;i++) {
			this.lPokemon.add(PokemonFactory.getMiArmaFactory().createPokemon("Normal"));
			int real=i+1;
			//System.out.println("Creado Pokemon numero"+" "+real);
		}
		this.comprobarLista();
		
	}
	public void comprobarLista() {
		Iterator<Pokemon> itr=this.getIterador();
		int i=0;
		while (itr.hasNext()) {
			Pokemon act =itr.next();
			int real=i+1;
			//System.out.println("Hola soy el pokemon numero "+real+" y soy de tipo "+act.tipo+" y tengo estas caracteristicas: Ataque "+act.ataque+" Defensa: "+act.defensa+" y Vida: "+act.vida);
			i++;
		}
	}

	public Pokemon getPokemon(int lugarPokemon) {
		return this.lPokemon.get(lugarPokemon);
	}

}
