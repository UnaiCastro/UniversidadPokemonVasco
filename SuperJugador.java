package Modelo;

import java.util.Observable;

public abstract class SuperJugador extends Observable{
	protected Equipo equipoPokemon;
	protected String nombre;
	
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
}
