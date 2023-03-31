package Vista;

import javax.swing.*;

import Modelo.GestorJuegoPokemon;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class InterfazJugador extends JFrame implements Observer {
	private String playerName;
	private int numPokemon;
	private int nJugador;
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JLabel playerImageLabel;
    private JLabel[] pokemonImageLabels;
    private ControlerJugador miControlador;

   
    
    
    public InterfazJugador (String playerName, int numPokemon, int nJugador) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inicializar(playerName, numPokemon, nJugador);
		setVisible(true);
		//GestorJuegoPokemon.getMiGestorJuegoPokemon().addObserver(this);
	}
    private void inicializar(String playerName, int numPokemon, int nJugador) {
    	setTitle(playerName + " - Jugador " + nJugador);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Creamos el panel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);
        getContentPane().add(panelPrincipal);

        // Creamos el botón en la esquina superior izquierda
        JButton boton = new JButton("Turno");
        boton.setBounds(10, 10, 100, 30);
        panelPrincipal.add(boton);

        // Creamos la imagen del jugador
        Random rand = new Random();
        int minimo = 0;
        int maximo = 5;
        int numeroAleatorio = rand.nextInt((maximo - minimo) + 1) + minimo;
        System.out.println(numeroAleatorio);
        ImageIcon imagenJugador = new ImageIcon("C:/Users/kasme/OneDrive/Escritorio/Pokemon_Fotos/Jugadores/trainer"+(numeroAleatorio)+".png");
        JLabel labelImagenJugador = new JLabel(imagenJugador);
        labelImagenJugador.setBounds(10, 50, 300, 350);
        panelPrincipal.add(labelImagenJugador);

        // Creamos las imágenes de los pokemons
        int posicionPokemonX = 320;
        int posicionPokemonY = 50;
        Random rand1 = new Random();
        int minimo1 = 1;
        int maximo1 = 5;
        int numeroAleatorio1 = rand.nextInt((maximo1 - minimo1) + 1) + minimo1;
        System.out.println(numeroAleatorio1);
        for (int i = 0; i < numPokemon; i++) {        	
            ImageIcon imagenPokemon = new ImageIcon("C:/Users/kasme/OneDrive/Escritorio/Pokemon_Fotos/Jugadores/Pokemon" + (numeroAleatorio1) + ".png");
            JLabel labelImagenPokemon = new JLabel(imagenPokemon);
            labelImagenPokemon.setBounds(posicionPokemonX, posicionPokemonY, 250, 350);
            panelPrincipal.add(labelImagenPokemon);

            // Creamos el botón debajo de la imagen del pokemon
            JButton botonPokemon = new JButton("Botón " + (i + 1));
            botonPokemon.setBounds(posicionPokemonX, posicionPokemonY + 350, 250, 30);
            panelPrincipal.add(botonPokemon);

            posicionPokemonX += 250;
        }

        // Creamos los botones debajo de las imágenes de los pokemons
        JButton boton1 = new JButton("Botón 1");
        boton1.setBounds(320, 410, 100, 30);
        panelPrincipal.add(boton1);

        JButton boton2 = new JButton("Botón 2");
        boton2.setBounds(430, 410, 100, 30);
        panelPrincipal.add(boton2);

        JButton boton3 = new JButton("Botón 3");
        boton3.setBounds(540, 410, 100, 30);
        panelPrincipal.add(boton3);

        setSize(320 + numPokemon * 250, 540);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public ControlerJugador getMiControlador() {
		if (miControlador==null) {
			miControlador=new ControlerJugador();
		}
		return miControlador;
	}
	
	
	private class ControlerJugador implements ActionListener {
		
		
	    private ControlerJugador() {
	    	
	    }

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}

    private void setImages(String playerName, int numPokemon) {
        Random random = new Random();
        int playerImageIndex = random.nextInt(3) + 1;
        playerImageLabel.setIcon(new ImageIcon("player" + playerImageIndex + ".png"));

        int[] pokemonImageIndices = new int[numPokemon];
        for (int i = 0; i < numPokemon; i++) {
            pokemonImageIndices[i] = random.nextInt(3) + 1;
            pokemonImageLabels[i].setIcon(new ImageIcon("pokemon" + pokemonImageIndices[i] + ".png"));
        }
    }
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
    
}