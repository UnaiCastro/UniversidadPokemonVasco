package Modelo;

import java.util.Observable;
import java.util.Random;

import Modelo.StateEvolucion.StateEvolu;
import Modelo.StateEvolucion.Base;
import Modelo.StateEvolucion.Euforia;
import Modelo.StateEvolucion.PrimerEvo;
import Modelo.StateEvolucion.SegunEvo;

public abstract class Pokemon extends Observable{
	protected String nombreJugadorPerteneciente;
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
		this.vida=30;//+this.randomNumero(1,20);//+random(1-20);
		this.maxHp=this.vida;
		this.euforiaMaxima = this.rand.nextInt(3) + 4;
		this.euforiaActual = 0;
	}
	
	public String getTipo() {
		return this.tipo;
	}
	public int getAtaque() {
		return this.ataque+ this.evoState.boostAtaque();
	}
	public int getDefensa() {
		return this.defensa+this.evoState.boostDefensa();
	}
	
	public int getVida() {
		return this.vida;
	}
	
	public int getVidaMax() {
		return this.maxHp;
	}
	
	public boolean getAtaca() {
		return this.haAtacado;
	}
	
	public StateEvolu getState() {
		return this.evoState;
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
//		this.notifyObservers(new String[] { String.valueOf(Integer.toString(this.getAtaque())),String.valueOf(Integer.toString(this.getDefensa())), String.valueOf(Integer.toString(this.vida)),String.valueOf(Integer.toString(this.maxHp)), this.tipo.toString(), Integer.toString(this.evoState.evolucion()),Integer.toString(this.euforiaMaxima), Integer.toString(this.euforiaActual)});

		this.notifyObservers(new String[] { String.valueOf(Integer.toString(this.getAtaque())),String.valueOf(Integer.toString(this.getDefensa())), String.valueOf(Integer.toString(this.vida)),String.valueOf(Integer.toString(this.maxHp)), this.tipo.toString(), Integer.toString(this.evoState.evolucion()),Integer.toString(this.euforiaMaxima), Integer.toString(this.euforiaActual),Boolean.toString(GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().mirarJugador(nombreJugadorPerteneciente).getDerrotado())});
	}
	
	public void bajarEuforia() {
		if (this.evoState instanceof Euforia) {
			this.euforiaActual = 0;
			revisionEuforiaEvolu();
			setChanged();		
//			this.notifyObservers(new String[] { String.valueOf(Integer.toString(this.getAtaque())),String.valueOf(Integer.toString(this.getDefensa())), String.valueOf(Integer.toString(this.vida)),String.valueOf(Integer.toString(this.maxHp)), this.tipo.toString(), Integer.toString(this.evoState.evolucion()),Integer.toString(this.euforiaMaxima), Integer.toString(this.euforiaActual)});
			this.notifyObservers(new String[] { String.valueOf(Integer.toString(this.getAtaque())),String.valueOf(Integer.toString(this.getDefensa())), String.valueOf(Integer.toString(this.vida)),String.valueOf(Integer.toString(this.maxHp)), this.tipo.toString(), Integer.toString(this.evoState.evolucion()),Integer.toString(this.euforiaMaxima), Integer.toString(this.euforiaActual),Boolean.toString(GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().mirarJugador(nombreJugadorPerteneciente).getDerrotado())});

//			this.notifyObservers(new String[] { String.valueOf(Integer.toString(this.getAtaque())),String.valueOf(Integer.toString(this.getDefensa())), String.valueOf(Integer.toString(this.vida)),String.valueOf(Integer.toString(this.maxHp)), this.tipo.toString(), Integer.toString(this.evoState.evolucion()),Integer.toString(this.euforiaMaxima), Integer.toString(this.euforiaActual)});
		}
	}
	
	public int getEuforia() {
		return this.euforiaActual;
	}
	
	public int getEuforiaMax() {
		return this.euforiaMaxima;
	}

	public int gestionAtaque(int valorAtaque, String pTipoPoke) {
		System.out.println(+this.evoState.boostAtaque()+" "+this.evoState.boostDefensa()+" "+this.evoState.evolucion());
		int multiplicador = mejoraAtaque(pTipoPoke);
		System.out.println(+multiplicador);
		int daño = multiplicador * valorAtaque - this.getDefensa();
		System.out.println(+daño);
		if (daño < 0) {
			daño = 0;
		}
		this.vida -= daño;
		System.out.println(+this.vida);
		if (this.vida <= 0) {
			this.setMuerto(true);
			this.vida=0;
		}
		if (this.euforiaActual < this.euforiaMaxima) {
			this.euforiaActual++;
			System.out.println("Este Pokemon ha subido uno de euforia, tiene "+this.euforiaActual);
		} else {
			      
			this.euforiaActual = this.euforiaMaxima;
		} 
		if (GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().mirarJugador(nombreJugadorPerteneciente).equipoPokemon.todosMueertos()) {
			GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().mirarJugador(nombreJugadorPerteneciente).setderrotado(true);
			System.out.println("Has muerto "+" "+GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().mirarJugador(nombreJugadorPerteneciente).nombre);
		}
		revisionEuforiaEvolu();
		setChanged();
		this.notifyObservers(new String[] { String.valueOf(Integer.toString(this.getAtaque())),String.valueOf(Integer.toString(this.getDefensa())), String.valueOf(Integer.toString(this.vida)),String.valueOf(Integer.toString(this.maxHp)), this.tipo.toString(), Integer.toString(this.evoState.evolucion()),Integer.toString(this.euforiaMaxima), Integer.toString(this.euforiaActual),Boolean.toString(GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().mirarJugador(nombreJugadorPerteneciente).getDerrotado())});

//		this.notifyObservers(new String[] { String.valueOf(Integer.toString(this.getAtaque())),String.valueOf(Integer.toString(this.getDefensa())), String.valueOf(Integer.toString(this.vida)),String.valueOf(Integer.toString(this.maxHp)), this.tipo.toString(), Integer.toString(this.evoState.evolucion()),Integer.toString(this.euforiaMaxima), Integer.toString(this.euforiaActual)});
		return this.vida;
	}
	
	private void revisionEuforiaEvolu() {
		if (this.vida > this.maxHp / 2) {
			cambioState((StateEvolu)new Base());
		}
		if (this.vida <= this.maxHp / 2) {
			cambioState((StateEvolu)new PrimerEvo());
		}
		if (this.vida <= this.maxHp / 4) {
			cambioState((StateEvolu)new SegunEvo());
		}
		if (this.euforiaActual == this.euforiaMaxima) {
			cambioState((StateEvolu)new Euforia());
		}
	}
	
	private void cambioState(StateEvolu pState) {
		this.evoState = pState;
	}

	protected abstract int mejoraAtaque(String pTipoPoke);
	
}
