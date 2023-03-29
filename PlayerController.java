package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class PlayerController implements ActionListener{
	private static PlayerController miControlador;
	
    
	public static PlayerController getMiControlador() {
		if (miControlador==null) {
			miControlador=new PlayerController();
		}
		return miControlador;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
}
