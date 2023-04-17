package Modelo;

public class Agua extends Pokemon {
	
	public Agua(String pNombre) {
		
//		this.ataque=11+this.randomNumero(1,7);
//		this.defensa=3+this.randomNumero(1,4);//random(1-4);
//		this.vida=200+this.randomNumero(1,20);//+random(1-20);
//		
		this.nombre=pNombre;
		this.tipo="Agua";
	}

	@Override
	public String getTipo() {
		
		return this.tipo;
	}
	
	
}
