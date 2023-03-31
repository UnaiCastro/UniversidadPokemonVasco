package Modelo;

public abstract class SuperJugador {
	protected Equipo equipoPokemon;
	protected String nombre;
	
	//public abstract void a�adirPokemons(String pNombre, int numPokemon);
	public Equipo getMiEquipo() {
		return this.equipoPokemon;
	}
	
	public void a�adirPokemons(int numPokemons)
	{
		//this.a�adirNombre(pNombre);
		//System.out.println("Creando pokemons de:"+this.nombre);
		this.equipoPokemon.anadirPokemon(numPokemons);
		
	}
	
	public void setNombre(String pNombre) {
		this.nombre=pNombre;
	}
	
	public String getNombre() {
		return this.nombre;
	}
}
