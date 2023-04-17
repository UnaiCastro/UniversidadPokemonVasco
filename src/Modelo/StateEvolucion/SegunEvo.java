package Modelo.StateEvolucion;
 
public class SegunEvo implements StateEvolu {
	private int bataque = 7;
	private int bdefensa = 5;
	private int evolucion = 2;
   
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