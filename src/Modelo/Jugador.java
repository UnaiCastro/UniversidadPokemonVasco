package Modelo;

public class Jugador extends SuperJugador{
	
	public Jugador(String pJugador) {
		this.equipoPokemon = new Equipo();
		this.nombre= pJugador;
	}
	
	public void setNombre(String pJugador) {
		this.nombre=pJugador;
	}
	
	public void setTurno(Boolean pTurno) {
		this.turno=pTurno;
	}

	@Override
	public void atacarN(String pNombre) {
//		System.out.println("Has llegado a jugador el metodo atacarN");
		
	}

//	@Override
//	public void atacarN2Version(String pNombre) {
		// TODO Auto-generated method stub
		
}


