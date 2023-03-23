package Modelo;

public class Jugador extends SuperJugador{
	
	public Jugador() {
		this.equipoPokemon = new Equipo();
		this.nombre= "jugador";
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String pNombre) {
		this.nombre=pNombre;
	}
	

}
