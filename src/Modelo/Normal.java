package Modelo;

public class Normal extends Pokemon {
	
	public Normal(String pNombre) {
//		this.ataque=11+this.randomNumero(1,7);
//		this.defensa=3+this.randomNumero(1,4);//random(1-4);
//		this.vida=200+this.randomNumero(1,20);//+random(1-20);
		this.nombre=pNombre;
		this.tipo="Normal";
	}

	@Override
	public String getTipo() {
		return this.tipo;
	}

	@Override
	protected int mejoraAtaque(String pTipoPoke) {
		// TODO Auto-generated method stub
		return 0;
	}
}
