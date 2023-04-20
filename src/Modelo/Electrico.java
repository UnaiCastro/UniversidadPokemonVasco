package Modelo;

public class Electrico extends Pokemon {
	
	public Electrico(String pNombre) {
		this.tipo="Electrico";
		this.nombre=pNombre;

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
