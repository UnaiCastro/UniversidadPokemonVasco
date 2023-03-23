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
	
	public void anadirArma(Pokemon pPokemon){
		this.lPokemon.add(pPokemon);
	}

	public Pokemon getPokemon(int lugarPokemon) {
		return this.lPokemon.get(lugarPokemon);
	}

}
