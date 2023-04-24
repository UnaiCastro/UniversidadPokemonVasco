package Modelo;

public class Electrico extends Pokemon {
	
	public Electrico(String pNombre, String nombreJug) {
		this.tipo="Electrico";
		this.nombre=pNombre;
		this.nombreJugadorPerteneciente=nombreJug;


	}

	@Override
	public String getTipo() {
		return this.tipo;
	}

	@Override
	protected int mejoraAtaque(String pTipoPoke) {
		int multi=1;
		if (pTipoPoke.equals("Planta")) {
			multi=2;
		}
		return multi;
	}
}
