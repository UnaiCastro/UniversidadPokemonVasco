package Modelo.StateEvolucion;
 
public class Base implements StateEvolu {
	private int bataque = 0;
	private int bdefensa = 0;
	private int evolucion = 0;
   
	public int boostAtaque() {
		return this.bataque;
	}

	public int boostDefensa() {
		return this.bdefensa;
	}
   
	public int evolucion() {
		return this.evolucion;
	}
}


