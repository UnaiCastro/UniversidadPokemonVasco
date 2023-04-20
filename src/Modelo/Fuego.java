package Modelo;

public class Fuego extends Pokemon{
	
	public Fuego(String pNombre) {
//		this.ataque=11+this.randomNumero(1,7);
//		this.defensa=3+this.randomNumero(1,4);//random(1-4);
//		this.vida=200+this.randomNumero(1,20);//+random(1-20);
		this.tipo="Fuego";
		this.nombre=pNombre;

	}

	@Override
	public String getTipo() {
		return this.tipo;
	}

	@Override
	protected int mejoraAtaque(String pTipoPoke) {
		int multi=1;
		if (pTipoPoke.equals("Agua")) {
			multi=2;
		}
		return multi;
	}
}
