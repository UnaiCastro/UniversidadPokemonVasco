package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;


import Modelo.GestorJuegoPokemon;



public class InterfazJuegoPokemon extends JFrame implements Observer {
	private JFrame frame;
    private JButton startButton;
    private JTextField playersField;
    private JTextField npcsField;
    private JTextField pokemonField;
    private JLabel imageLabel;
    private Controler miControlador;
	
	public InterfazJuegoPokemon () {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inicializar();
		setVisible(true);
		GestorJuegoPokemon.getMiGestorJuegoPokemon().addObserver(this);
	}
	
	private void inicializar() {
		 	frame = new JFrame("Pokemon Battle Arena");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(1080, 1080);


	        JPanel mainPanel = new JPanel(new BorderLayout());

	        // Panel de la imagen
	        JPanel imagePanel = new JPanel();
	        try {
	            JLabel imageLabel = new JLabel();
	            Image image = ImageIO.read(new File("C:/Users/kasme/OneDrive/Escritorio/main.png"));
	            Image scaledImage = image.getScaledInstance(800, 400, Image.SCALE_SMOOTH);
	            ImageIcon icon = new ImageIcon(scaledImage);
	            imageLabel.setIcon(icon);
	            imagePanel.add(imageLabel);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        // Panel de los campos y botón
	        JPanel fieldsPanel = new JPanel();
	        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));

	        JLabel playersLabel = new JLabel("Jugadores:");
	        playersField = new JTextField(10);
	        JPanel playersPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	        playersPanel.add(playersLabel);
	        playersPanel.add(Box.createRigidArea(new Dimension(10, 0)));
	        playersPanel.add(playersField);

	        JLabel npcsLabel = new JLabel("NPCs:");
	        npcsField = new JTextField(10);
	        JPanel npcsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	        npcsPanel.add(npcsLabel);
	        npcsPanel.add(Box.createRigidArea(new Dimension(10, 0)));
	        npcsPanel.add(npcsField);

	        JLabel pokemonLabel = new JLabel("Pokémon:");
	        pokemonField = new JTextField(10);
	        JPanel pokemonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	        pokemonPanel.add(pokemonLabel);
	        pokemonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
	        pokemonPanel.add(pokemonField);

	        startButton = new JButton("Empezar Partida");
	        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	        buttonPanel.add(startButton);
	        this.startButton.addActionListener(getMiControlador());

	        fieldsPanel.add(Box.createVerticalGlue());
	        fieldsPanel.add(playersPanel);
	        fieldsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
	        fieldsPanel.add(npcsPanel);
	        fieldsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
	        fieldsPanel.add(pokemonPanel);
	        fieldsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
	        fieldsPanel.add(buttonPanel);
	        fieldsPanel.add(Box.createVerticalGlue());

	        mainPanel.add(imagePanel, BorderLayout.WEST);
	        mainPanel.add(fieldsPanel, BorderLayout.EAST);

	        frame.add(mainPanel);
	        frame.setVisible(true);
	}
	
	
	public Controler getMiControlador() {
		if (miControlador==null) {
			miControlador=new Controler();
		}
		return miControlador;
	}
	
	
	private class Controler implements ActionListener {
		
		
	    private Controler() {
	    	
	    }
	    
		
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(startButton)){
				System.out.println("Jugadores a Jugar");
				int numPlayers = getPlayers();
				int numNPCs = getNPCs();
				int numPokemon = getPokemon();
				
			
	    // Llamada al modelo para iniciar la partida
				//GestorJuegoPokemon.getMiGestorJuegoPokemon().empieza(numPlayers, numNPCs, numPokemon);
				update (null,null);
	    // Crear la vista de los jugadores
				
			}
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		for (int i = 0; i < 2; i++) {
			InterfazJugador playerView = new InterfazJugador("Jugador" , 7, i+1);
			int numero=i+1;
			System.out.println("Creada pantalla jugador" +" " +numero);
		}
		for (int i = 0; i < 2; i++) {
			InterfazJugador playerView = new InterfazJugador("NPC" , 7, i+1);
			int numero=i+1;
			System.out.println("Creada pantalla NPC" +" " +numero);
		}
		
	}
	//Arriba como todavia no tenemos lo del modelo hacemos pruebas nosotros
    public int getPlayers() {
        return Integer.parseInt(playersField.getText());
    }

    public int getNPCs() {
        return Integer.parseInt(npcsField.getText());
    }

    public int getPokemon() {
        return Integer.parseInt(pokemonField.getText());
    }
	
}
