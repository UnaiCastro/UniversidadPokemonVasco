package Modelo;

public class Agua extends Pokemon {
	
	public Agua(String pNombre) {	
		this.nombre=pNombre;
		this.tipo="Agua";
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
