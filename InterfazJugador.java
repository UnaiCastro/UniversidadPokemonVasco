package Vista;

import javax.swing.*;

import Modelo.GestorJuegoPokemon;

import java.awt.*;
import java.util.Random;

public class InterfazJugador extends JFrame {
	private String playerName;
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JLabel playerImageLabel;
    private JLabel[] pokemonImageLabels;
    private int numPokemon;
    private int nJugador;
    
    
    public InterfazJugador (String playerName, int numPokemon, int nJugador) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inicializar(this.playerName, this.numPokemon, this.nJugador);
		setVisible(true);
		GestorJuegoPokemon.getMiGestorJuegoPokemon().addObserver(this);
	}
    private void inicializar(String playerName, int numPokemon, int nJugador) {
    	setTitle("Pantalla de jugador"+nJugador);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setPreferredSize(new Dimension(300*(numPokemon)+300, 500));
        
     // Botón "Jugar"
        //JButton jButton1 = new javax.swing.JButton();
        //jButton1.setText("Jugar");
        //jButton1.setBounds(20, 390, 300, 50);
        //Container jPanel1 = null;
		//jPanel1.add(jButton1);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        titleLabel = new JLabel(playerName+ " " + nJugador);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        playerImageLabel = new JLabel();
        playerImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(playerImageLabel, BorderLayout.WEST);

        JPanel pokemonPanel = new JPanel(new GridLayout(0, 1));
        pokemonImageLabels = new JLabel[numPokemon];
        for (int i = 0; i < numPokemon; i++) {
            pokemonImageLabels[i] = new JLabel();
            pokemonImageLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
            pokemonPanel.add(pokemonImageLabels[i]);
        }
        mainPanel.add(pokemonPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);

        setImages(playerName, numPokemon);
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
    
}