package Modelo;

import java.util.Random;

import Modelo.StateEvolucion.StateEvolu;
import Modelo.StateEvolucion.Base;

public abstract class Pokemon {
	private int ataque;
	protected int vida;
	private int defensa;
	protected String tipo;
	protected String nombre;
	private boolean muerto=false;
	private boolean haAtacado=false;
	private StateEvolu evoState = (StateEvolu)new Base();
	
	public Pokemon() {
		this.ataque=11+this.randomNumero(1,7);
		this.defensa=3+this.randomNumero(1,4);//random(1-4);
		this.vida=200;//+this.randomNumero(1,20);//+random(1-20);
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
	
	public boolean getAtaca() {
		return this.haAtacado;
	}
	
	public void setAtaca(boolean pBool) {
		this.haAtacado=pBool;
	}
	
	public boolean estaMuerto() {
		return this.muerto;
	}
	
	public void setMuerto(Boolean pMuerto) {
		this.muerto=pMuerto;
	}
	
	public int randomNumero(int pInicial,int pFinal) {
		Random rand = new Random();
        return rand.nextInt(pFinal - pInicial + 1) + pInicial;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void bajarVida(int vida2) {
		if (vida2<=0) {
			this.vida=0;
			this.setMuerto(true);
		}else {
			this.vida=vida2;

		}
		
	}
	
}
