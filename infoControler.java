package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class infoControler implements ActionListener {
	private static infoControler miControlador;
	private int tamanoPokemon;
	private int tamanoJugador;
	private int tamanoNPC;
	private int milisegundos;
	
	
	public static infoControler getMiInfoControler() {
		if (miControlador==null) {
			miControlador=new infoControler();
		}
		return miControlador;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
	
	public int getTamanoPokemon() {
		return this.tamanoPokemon;
	}
	
	public int getTamanoJugador(){
		return this.tamanoJugador;
	}
	
	public int getTamanoNPC(){
		return this.tamanoNPC;
	}
	
	public int getMilisegundos(){
		return this.milisegundos;
		
	
		
	

}
}
