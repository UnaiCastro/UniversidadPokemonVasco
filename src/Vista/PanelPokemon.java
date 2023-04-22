package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

import Modelo.GestorJuegoPokemon;

public class PanelPokemon extends JPanel implements Observer{
	private int nPokemon;
	private int posicionX;
	private String nJugador;
	private JProgressBar euforiaPB;
	private JButton botonDeAtaque;
	private JTextArea texto;
	private JLabel fotoPoke;
	private Random rand1=new Random();
	
	
	public PanelPokemon(int posicionX,int pPokemon, String nombreJugador) {
		setBackground(Color.WHITE);
		this.nPokemon=pPokemon;
		this.posicionX=posicionX;
		this.nJugador=nombreJugador;
		setLayout(new BorderLayout(0, 0));
//		add(getTexto(), "North");
//		add(getLabel(), "Center");
//		add(getPanelBar(), "South");
		GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().mirarJugador(nombreJugador).getMiEquipo().getPokemon(pPokemon).addObserver(this);
		inicializar();
	}
	

	private void inicializar() {
    	String[] infoPokemon = {" Ataque: "+Integer.toString(GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().mirarJugador(nJugador).getMiEquipo().getPokemon(this.nPokemon).getAtaque())+"\n"+" Defensa: "+Integer.toString(GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().mirarJugador(nJugador).getMiEquipo().getPokemon(this.nPokemon).getDefensa())+"\n"+ " Vida: "+Integer.toString(GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().mirarJugador(nJugador).getMiEquipo().getPokemon(this.nPokemon).getVida())+"\n"+ " Tipo: "+GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().mirarJugador(nJugador).getMiEquipo().getPokemon(this.nPokemon).getTipo()};
//    	setLayout(new BorderLayout(0, 0));
//		add(getTexto(), "North");
//		add(getLabel(), "Center");
//		add(getPanelBar(), "South");
    	
    	//Texto
    	this.texto= new JTextArea();
		this.texto.setBounds(posicionX, 20, 200, 70);
		for(String info1 : infoPokemon) {
        	this.texto.setText(this.texto.getText() + info1 + "\n");    
        }
		this.texto.setBackground(Color.WHITE);
		
		//Imagen
		ImageIcon imagenPokemon = new ImageIcon("src/sprites/" + (rand1.nextInt(11)+1) + ".png");
		this.fotoPoke=new JLabel(imagenPokemon);
		this.fotoPoke.setBounds(this.posicionX, -75, 200, 500);
		this.fotoPoke.setBackground(Color.WHITE);
		
		//Boton
		this.botonDeAtaque= new JButton("Ataca!"); 
		botonDeAtaque.setName("Boton" +(this.nJugador+1));
		this.botonDeAtaque.setBounds(this.posicionX, -75 + 350, 200, 30);
//		this.botonDeAtaque.addActionListener(getMiControlador());
		
		//Euforia
		this.euforiaPB= new JProgressBar();
		this.euforiaPB.setString("Euforia");
		this.euforiaPB.setForeground(new Color(255, 99, 71));
		this.euforiaPB.setStringPainted(true);
		this.euforiaPB.setBounds(this.posicionX, -75 + 400, 200, 30);
		int euforia=GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().mirarJugador(this.nJugador).getMiEquipo().getPokemon(this.nPokemon).getEuforia();
		int euforiaMax=GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().mirarJugador(this.nJugador).getMiEquipo().getPokemon(this.nPokemon).getEuforiaMax();
		int carga=(int) (100.0F*Float.parseFloat(Integer.toString(euforia)) / Integer.parseInt(Integer.toString(euforiaMax)));
		this.euforiaPB.setValue(carga);
		
		setVisible(true);
		
	}


	private Component getPanelBar() {
		// TODO Auto-generated method stub
		return null;
	}


	private JLabel getLabel() {
		// TODO Auto-generated method stub
		return null;
	}


	private JTextArea getTexto() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
