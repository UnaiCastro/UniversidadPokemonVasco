package Vista;

import javax.swing.*;
import javax.xml.soap.Text;

import Modelo.Equipo;
import Modelo.GestorJuegoPokemon;
import Modelo.Tablero;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
    private JButton botonPokemon; 
//    private ArrayList<JButton> lbotonPokemon;
   
    
    
    public InterfazJugador (String playerName, int numPokemon, Equipo pEquipo) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inicializar(playerName, numPokemon, nJugador, pEquipo);
		setVisible(true);
		if (playerName.equals("Jugador")) {
			//Añadir
		}else {
			//Añadir Observer NPC
		}
		//GestorJuegoPokemon.getMiGestorJuegoPokemon().addObserver(this);
	}
    private void inicializar(String playerName, int numPokemon, int nJugador, Equipo pEquipo) {
    	setTitle(playerName);
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
        //System.out.println(numeroAleatorio);
        ImageIcon imagenJugador = new ImageIcon("C:/Users/kasme/OneDrive/Escritorio/Pokemon_Fotos/Jugadores/trainer"+(numeroAleatorio)+".png");
        JLabel labelImagenJugador = new JLabel(imagenJugador);
        labelImagenJugador.setBounds(10, 50, 300, 350);
        panelPrincipal.add(labelImagenJugador);

        // Creamos las imágenes de los pokemons
        int posicionPokemonX = 320;
        int posicionPokemonY = 50;
        
        
        for (int i = 0; i < numPokemon; i++) {  
        	String[] infoPokemon = {"Ataque: "+pEquipo.getPokemon(i).getAtaque(),"Defensa: "+pEquipo.getPokemon(i).getDefensa(), "Vida: "+pEquipo.getPokemon(i).getVida(), "Tipo: "+pEquipo.getPokemon(i).getTipo()};
        	Random rand1 = new Random();
            int minimo1 = 1;
            int maximo1 = 5;
            int numeroAleatorio1 = rand1.nextInt((maximo1 - minimo1) + 1) + minimo1;
            System.out.println(numeroAleatorio1);
            ImageIcon imagenPokemon = new ImageIcon("C:/Users/kasme/OneDrive/Escritorio/Pokemon_Fotos/Pokemons/Pokemon" + (numeroAleatorio1) + ".png");
            JLabel labelImagenPokemon = new JLabel(imagenPokemon);
            labelImagenPokemon.setBounds(posicionPokemonX, posicionPokemonY, 250, 350);
            panelPrincipal.add(labelImagenPokemon);

            // Creamos el botón debajo de la imagen del pokemon
//            String info ="Ataque: "+pEquipo.getPokemon(i).getAtaque()+"\nDefensa: "+pEquipo.getPokemon(i).getDefensa()+"\nVida: "+pEquipo.getPokemon(i).getVida()+"\n Tipo: "+pEquipo.getPokemon(i).getTipo();
//            JLabel informacionLabel = new JLabel(info);
//            informacionLabel.setBounds(posicionPokemonX, posicionPokemonY +10, 300, 30);
//            panelPrincipal.add(informacionLabel);
//          add(informacionLabel);
            this.botonPokemon = new JButton("Ataca!"+" "+(i+1));          
            botonPokemon.setBounds(posicionPokemonX, posicionPokemonY + 350, 250, 30);
            panelPrincipal.add(botonPokemon);
           
//            this.lbotonPokemon.add(botonPokemon);
            this.botonPokemon.addActionListener(getMiControlador());
//          labelImagenPokemon.setText(info);
            
          //Agregar información encima de la foto del pokemon
            JLabel infoPokemonLabel = new JLabel();
            infoPokemonLabel.setBounds(300 + 250*i, 10, 250, 30);
            for(String info1 : infoPokemon) {
                infoPokemonLabel.setText(infoPokemonLabel.getText() + info1 + "\n");
                panelPrincipal.add(infoPokemonLabel);
            }

            posicionPokemonX += 250;
        }

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
			if (arg0.getSource().equals(botonPokemon)){
				String player=getName();
				int i=0;
				boolean puede=Tablero.getMiTablero().mirarTurno(player);
				if (puede==true) {
					Tablero.getMiTablero().añadirPokemon(i,player);

				}else {
					System.out.println("No puedes jugar, no es tu turno");
				}
			}
			
		}
	}

//    private void setImages(String playerName, int numPokemon) {
//        Random random = new Random();
//        int playerImageIndex = random.nextInt(3) + 1;
//        playerImageLabel.setIcon(new ImageIcon("player" + playerImageIndex + ".png"));
//
//        int[] pokemonImageIndices = new int[numPokemon];
//        for (int i = 0; i < numPokemon; i++) {
//            pokemonImageIndices[i] = random.nextInt(3) + 1;
//            pokemonImageLabels[i].setIcon(new ImageIcon("pokemon" + pokemonImageIndices[i] + ".png"));
//        }
//    }
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	public String getName() {
		return this.playerName;
	}
    
}