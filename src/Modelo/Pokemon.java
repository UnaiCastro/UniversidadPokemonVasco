package Modelo;

import java.util.Random;

public abstract class Pokemon {
	protected int ataque;
	protected int vida;
	protected int defensa;
	protected String tipo;
	protected String nombre;
	
	public Pokemon() {
//		this.nombre=pNombre;
		this.ataque=11+this.randomNumero(1,7);
		this.defensa=3+this.randomNumero(1,4);//random(1-4);
		this.vida=1;//+this.randomNumero(1,20);//+random(1-20);
	}
	
	public abstract String getTipo();
	public int getAtaque() {
		return this.ataque;
	}
	public int getDefensa() {
		return this.defensa;
	}
	
	public int getVida() {
		return this.vida;
	}
	
	public int randomNumero(int pInicial,int pFinal) {
		Random rand = new Random();
        return rand.nextInt(pFinal - pInicial + 1) + pInicial;
	}

	public String getNombre() {
		// TODO Auto-generated method stub
		return this.nombre;
	}

	public void bajarVida(int vida2) {
		if (vida2<=0) {
			this.vida=0;
		}else {
			this.vida=vida2;

		}
		
	}
	
}
