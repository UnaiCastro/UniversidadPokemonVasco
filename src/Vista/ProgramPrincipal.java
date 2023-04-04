package Vista;

import java.awt.EventQueue;
import Modelo.GestorJuegoPokemon;

public class ProgramPrincipal {
	public static void main(String[] args) {
		System.out.println("Bienvenidos A Pokemon");
		GestorJuegoPokemon.getMiGestorJuegoPokemon();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				InterfazJuegoPokemon interfaz = new InterfazJuegoPokemon();
			}
		});
		
	}
}
