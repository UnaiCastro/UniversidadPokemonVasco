package Modelo;

import java.util.Observable;
import java.util.Random;

import Modelo.StateEvolucion.StateEvolu;
import Modelo.StateEvolucion.Base;

public abstract class Pokemon extends Observable{
	private int ataque;
	private int vida;
	private int maxHp;
	private int defensa;
	private int euforiaMaxima;
	private int euforiaActual;
	protected String tipo;
	protected String nombre;
	protected Random rand = new Random();
	private boolean muerto=false;
	private boolean haAtacado=false;
	private StateEvolu evoState = (StateEvolu)new Base();
	
	public Pokemon() {
		this.ataque=11+this.randomNumero(1,7);
		this.defensa=3+this.randomNumero(1,4);//random(1-4);
		this.vida=15;//+this.randomNumero(1,20);//+random(1-20);
		this.maxHp=this.vida;
		this.euforiaMaxima = this.rand.nextInt(3) + 4;
		this.euforiaActual = 0;

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
		setChanged();
		this.notifyObservers();
		
	}
	
	public void actuEuforia() {
		if (this.euforiaActual!=this.euforiaMaxima) {
			this.euforiaActual++;
			System.out.println("Este Pokemon ha subido uno de euforia, tiene "+this.euforiaActual);
		} else {
			System.out.println("Euforia Completada");
			this.ataque=this.ataque+100;
			this.defensa=this.defensa+100;
		}
		setChanged();
		this.notifyObservers();
	}
	
	public void bajarEuforia() {
		this.ataque=this.ataque-100;			
		this.defensa=this.defensa-100;
		this.euforiaActual=0;
		setChanged();
		this.notifyObservers();
	}
	
	public int getEuforia() {
		return this.euforiaActual;
	}
	
	public int getEuforiaMax() {
		return this.euforiaMaxima;
	}
	
}
