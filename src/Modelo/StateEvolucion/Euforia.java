package Modelo.StateEvolucion;

public class Euforia implements StateEvolu {
	private int boosAttaque = 100;
 	private int boostDefensa = 100;
 	private int evolucion = -1;
  
 
@Override
	public int boostAtaque() {
		return this.boosAttaque;
	}

@Override
	public int boostDefensa() {
		return this.boostDefensa;
	}

@Override
	public int evolucion() {	
		return this.evolucion;
	}
}