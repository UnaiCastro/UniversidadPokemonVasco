package Modelo;

import java.util.Observable;

public abstract class SuperJugador extends Observable{
	protected Equipo equipoPokemon;
	protected String nombre;
	protected boolean turno;
	private boolean defeated = false;
	
	//public abstract void aņadirPokemons(String pNombre, int numPokemon);
	public Equipo getMiEquipo() {
		return this.equipoPokemon;
	}
	
	public void aņadirPokemons(int numPokemons)
	{
		//this.aņadirNombre(pNombre);
		System.out.println("Creando pokemons de:"+this.nombre);
		this.equipoPokemon.anadirPokemon(numPokemons);
		
	}
	
	public void setNombre(String pNombre) {
		this.nombre=pNombre;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public Pokemon getPokemon(int ind) {
		return this.equipoPokemon.getPokemon(ind);
	}

	public void bajarVidaPokemon(int vida, Pokemon pokemon2) {
		this.equipoPokemon.bajarVida(vida,pokemon2);
		
	}

	public int getPosPokemon(Pokemon pokemon2) {
		int ind=this.equipoPokemon.getPosPoke(pokemon2);
		return ind;
	}
}
