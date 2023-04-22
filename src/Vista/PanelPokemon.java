package Vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import Modelo.GestorJuegoPokemon;

public class PanelPokemon extends JPanel implements Observer{
	private String nombreJugador;
	private int nPokemon;
	public PanelPokemon(String playerName,int pokemon) {
		this.nombreJugador=playerName;
	        setSize(new Dimension(300, 150));
	        setLayout(new BorderLayout());

	        // Agregar información del Pokémon en la parte superior
	        JPanel infoPokemonPanel = new JPanel(new GridLayout(4, 1));
//	        String[] infoPokemon = {"Ataque: 50", "Defensa: 30", "Vida: 100", "Tipo: Agua"};
	        String[] infoPokemon = {"Ataque: "+GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().mirarJugador(this.nombreJugador).getMiEquipo().getPokemon(nPokemon).getAtaque()+"\n"+" Defensa: "+GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().mirarJugador(this.nombreJugador).getMiEquipo().getPokemon(nPokemon).getDefensa()+"\n"+ " Vida: "+GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().mirarJugador(this.nombreJugador).getMiEquipo().getPokemon(nPokemon).getTipo()};

	        for (String info : infoPokemon) {
	            JLabel labelInfo = new JLabel(info);
	            infoPokemonPanel.add(labelInfo);
	        }
	        add(infoPokemonPanel, BorderLayout.NORTH);

	        // Agregar imagen del Pokémon en el centro
	        ImageIcon imagen = new ImageIcon("src/sprites/\" + (rand1.nextInt(11)+1) + \".png");
	        JLabel imagenLabel = new JLabel(imagen);
	        imagenLabel.setHorizontalAlignment(JLabel.CENTER);
	        add(imagenLabel, BorderLayout.CENTER);

	        // Agregar botón y barra de progreso en la parte inferior
	        JPanel botonPanel = new JPanel(new BorderLayout());
	        JButton botonAtacar = new JButton("Atacar");
	        botonPanel.add(botonAtacar, BorderLayout.NORTH);
	        JProgressBar barraVida = new JProgressBar(0, 100);
	        barraVida.setValue(50);
	        barraVida.setStringPainted(true);
	        botonPanel.add(barraVida, BorderLayout.SOUTH);
	        add(botonPanel, BorderLayout.SOUTH);
	    }

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
