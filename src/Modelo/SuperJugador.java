package Modelo;

import java.util.Observable;

public abstract class SuperJugador extends Observable{
	protected Equipo equipoPokemon;
	protected String nombre;
	protected boolean turno;
	private boolean derrotado = false;
	
	public Equipo getMiEquipo() {
		return this.equipoPokemon;
	}
	
	public boolean getDerrotado() {
		return this.derrotado;
	}
	
	public void aņadirPokemons(int numPokemons)
	{
		this.equipoPokemon.anadirPokemon(numPokemons);
		
	}//
	
	public void setNombre(String pNombre) {
		this.nombre=pNombre;
	}//
	
	public String getNombre() {
		return this.nombre;
	}//
	
	public void setTurno(boolean pBoolean) {
		this.turno=pBoolean;
		this.setChanged();
		this.notifyObservers();
	}//

	public Pokemon getPokemon(int ind) {
		return this.equipoPokemon.getPokemon(ind);
	}//


	public int getPosPokemon(Pokemon pokemon2) {
		int ind=this.equipoPokemon.getPosPoke(pokemon2);
		return ind;
	}//
	
	public boolean getTurno() {
		return this.turno;
	}//
	
	public void bajarVidaPokemon(int indice,int pVida){
		this.equipoPokemon.bajarVidaP(indice,pVida);
		if (this.equipoPokemon.todosMueertos()) {
			this.setderrotado(true);
			System.out.println("Has muerto "+this.derrotado+" "+this.nombre);
		}
		setChanged();
		this.notifyObservers();
	}
	
	public abstract void atacarN(String pNombre);
	
	
	
	public void setderrotado(Boolean pDerrotado) {
		this.derrotado=pDerrotado;
	}
	
}
