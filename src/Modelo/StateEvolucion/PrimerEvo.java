package Modelo.StateEvolucion;
 
public class PrimerEvo implements StateEvolu {
   private int bataque = 5;
   private int bdefensa = 3;
   private int evolucion = 1;
   
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


