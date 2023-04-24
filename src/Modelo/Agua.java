package Modelo;

public class Agua extends Pokemon {
	
	public Agua(String pNombre, String nombreJug) {	
		this.nombre=pNombre;
		this.tipo="Agua";
		this.nombreJugadorPerteneciente=nombreJug;

	}

	@Override
	public String getTipo() {
		
		return this.tipo;
	}

	@Override
	protected int mejoraAtaque(String pTipoPoke) {
		int multi=1;
		if (pTipoPoke.equals("Electrico")) {
			multi=2;
		}
		return multi;
	}
	
	
}
