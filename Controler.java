package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controler implements ActionListener {
	private static Controler miControlador;
	
	
	public static Controler getMiControlador() {
		if (miControlador==null) {
			miControlador=new Controler();
		}
		return miControlador;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// AQUI SE LLAMA AL MODELO
		
	}

}
