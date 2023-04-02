package Modelo;

public class Planta extends Pokemon {
	
	public Planta(String pNombre) {
//		this.ataque=11+this.randomNumero(1,7);
//		this.defensa=3+this.randomNumero(1,4);//random(1-4);
//		this.vida=200+this.randomNumero(1,20);//+random(1-20);
		this.tipo="Planta";
	}

	@Override
	public String getTipo() {
		return this.tipo;
	}
}
