package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import Modelo.GestorJuegoPokemon;
import Vista.InterfazJuegoPokemon;
import Vista.InterfazJugador;


public class Controler implements ActionListener {
	private static Controler miControlador;
	
    private JButton startButton;
	public static Controler getMiControlador() {
		if (miControlador==null) {
			miControlador=new Controler();
		}
		return miControlador;
	}
	//public Controler(InterfazJuegoPokemon view, GestorJuegoPokemon model) {
        //this.view = view;
        //this.model = model;*//

        
	//}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(startButton)){
			System.out.println("Jugadores a Jugar");
			int numPlayers = InterfazJuegoPokemon.getPlayers();
			int numNPCs = InterfazJuegoPokemon.getNPCs();
			int numPokemon = InterfazJuegoPokemon.getPokemon();
			String jugador = "jugador";
			String NPC = "NPC";
		
    // Llamada al modelo para iniciar la partida
			GestorJuegoPokemon.getMiGestorJuegoPokemon().empieza(numPlayers, numNPCs, numPokemon);

    // Crear la vista de los jugadores
			for (int i = 0; i < numPlayers; i++) {
				InterfazJugador playerView = new InterfazJugador(jugador , numPokemon, i+1);
				PlayerController playerController = new PlayerController();
				System.out.println("Creada pantalla jugador"+i+1);
			}
			for (int i = 0; i < numNPCs; i++) {
				InterfazJugador playerView = new InterfazJugador(NPC , numPokemon, i+1);
				PlayerController playerController = new PlayerController();
				System.out.println("Creada pantalla NPC"+i+1);
			}
		}
	}
		
}
	
	


