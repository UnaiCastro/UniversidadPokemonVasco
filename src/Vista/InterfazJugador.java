package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.Timer;

import Modelo.Equipo;
import Modelo.GestorJuegoPokemon;
import Modelo.Jugador;
import Modelo.NPC;
import Modelo.Pokemon;
import Modelo.SuperJugador;
import Modelo.Tablero;
//import Vista.PanelPokemon;



public class InterfazJugador extends JFrame implements Observer {
	private JFrame frame;
	private ArrayList<JButton> lBotonPokemon=null;
	private String playerName;
	private Equipo equipoJugador;
	private int numPokemon;
    private JPanel mainPanel= new JPanel();
    private JLabel playerImageLabel=new JLabel();
    private ArrayList<JLabel> pokemonImageLabels=new ArrayList<JLabel>();
//    private JPanel panelPokemon;
    private ArrayList<JTextArea> listaInfor=new ArrayList<JTextArea>();
    private ArrayList<JProgressBar> lEuforiaBoton=null;
    private ControlerJugador miControlador; 
    private JButton btnCambioX;
    private ArrayList<JProgressBar> listaVidaBar=new ArrayList<>();;
    Random rand1 = new Random();
//    private ArrayList<JLabel> lPokemonPanel=new ArrayList<JLabel>();
//    private JPanel panelIzquierdo;
//    private ArrayList<PanelPokemon> panelesPokemon = new ArrayList<PanelPokemon>();
//    private JPanel cardsPanel;
//    private ArrayList<JLabel> labelAux= new ArrayList<JLabel>();

   
    
    
    public InterfazJugador (String playerName, int numPokemon, Equipo pEquipo) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inicializar(playerName, numPokemon,pEquipo);
		setVisible(true);
		GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().mirarJugador(playerName).addObserver(this);
		for (int i=0;i<numPokemon;i++) {
			GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(playerName).getMiEquipo().getPokemon(i).addObserver(this);
		}
	}

    
    
    private void inicializar(String playerName, int numPokemon, Equipo pEquipo) {
    	this.numPokemon=numPokemon;
    	this.playerName=playerName;
    	this.equipoJugador=pEquipo;
    	this.frame=new JFrame(playerName);
    	setTitle(playerName);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(true);
        

         //Creamos el panel principal
        this.mainPanel.setLayout(null);
        this.mainPanel.setBackground(Color.WHITE);
        getContentPane().add(this.mainPanel);

        // Creamos el botón en la esquina superior izquierda
        this.btnCambioX = new JButton();
        btnCambioX.setBounds(10, 10, 100, 30);
        boolean k=GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarTurno(playerName);
        if (k) {
        	btnCambioX.setBackground(Color.GREEN);
        	btnCambioX.setText("Tu Turno");
          } else {
        	  btnCambioX.setBackground(Color.ORANGE);
        	  btnCambioX.setText("Espera");
          }
        this.mainPanel.add(btnCambioX);
        btnCambioX.addMouseListener(getMiControlador());

        //Imagen Jugador
        this.playerImageLabel.setIcon(new ImageIcon("src/sprites/trainer"+(rand1.nextInt(6))+".png"));
        this.playerImageLabel.setBounds(10, 0, 200, 350);
        this.mainPanel.add(this.playerImageLabel);

        // Creamos las imágenes de los pokemons
        int posicionPokemonX = 150;
        int posicionPokemonY = -75;
        
        this.lBotonPokemon= new ArrayList<JButton>();
        this.lEuforiaBoton= new ArrayList<JProgressBar>();
        for (int i = 0; i < numPokemon; i++) {  
        	String[] infoPokemon = {" Ataque: "+Integer.toString(equipoJugador.getPokemon(i).getAtaque())+"\n"+" Defensa: "+Integer.toString(equipoJugador.getPokemon(i).getDefensa())+"\n"+ " Vida: "+Integer.toString(equipoJugador.getPokemon(i).getVida())+"\n"+ " Tipo: "+equipoJugador.getPokemon(i).getTipo()};
            
        	//Imagen Pokemon
        	ImageIcon imagenPokemon = new ImageIcon();
            if (equipoJugador.getPokemon(i).getTipo().equals("Fuego")) {
            	imagenPokemon=new ImageIcon("src/sprites/Fire/0charmander.png");
            }else if  (equipoJugador.getPokemon(i).getTipo().equals("Agua")) {
            	imagenPokemon=new ImageIcon("src/sprites/Water/0squirtle.png");
            } else if (equipoJugador.getPokemon(i).getTipo().equals("Planta")) {
            	imagenPokemon=new ImageIcon("src/sprites/0bulbasaur.png");
            } else {
            	imagenPokemon=new ImageIcon("src/sprites/Electrico/0pikachu.png");
            }  
            JLabel labelImagenPokemon = new JLabel(imagenPokemon);
            labelImagenPokemon.setBackground(Color.WHITE);
            labelImagenPokemon.setBounds(posicionPokemonX + 30*i, posicionPokemonY+200, 150, 150);
            labelImagenPokemon.setOpaque(true);
            this.pokemonImageLabels.add(labelImagenPokemon);
            this.mainPanel.add(labelImagenPokemon);
            
            //Boton Ataque
            JButton atacarPoke = new JButton("Ataca!"); 
            atacarPoke.setName("Boton" +(i+1));
            this.lBotonPokemon.add(atacarPoke);
            atacarPoke.setBounds(posicionPokemonX + 30*i+40, posicionPokemonY + 360, 70, 20);
            this.mainPanel.add(atacarPoke);
            atacarPoke.addActionListener(getMiControlador());
            
            //Euforia
            JProgressBar estadoEuforia = new JProgressBar();
            estadoEuforia.setString("Euforia");
            estadoEuforia.setForeground(Color.yellow);
            estadoEuforia.setStringPainted(true);
            this.lEuforiaBoton.add(estadoEuforia);
            estadoEuforia.setBounds(posicionPokemonX + 30*i, posicionPokemonY + 389, 150, 15);
            this.mainPanel.add(estadoEuforia);
            int euforia=GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(getName()).getMiEquipo().getPokemon(i).getEuforia();
            int euforiaMax=GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(getName()).getMiEquipo().getPokemon(i).getEuforiaMax();
            int carga=(int) (100.0F*Float.parseFloat(Integer.toString(euforia)) / Integer.parseInt(Integer.toString(euforiaMax)));
            estadoEuforia.setValue(carga);
				
			
            
            //Barra Vida
            JProgressBar progressBar = new JProgressBar();
            progressBar.setStringPainted(true);
            progressBar.setString("Vida");
            progressBar.setBorderPainted(false);
            progressBar.setValue(100);
            progressBar.setBounds(posicionPokemonX + 30*i, posicionPokemonY + 410, 150, 15);
            progressBar.setForeground(new Color(138, 226, 52));
            this.mainPanel.add(progressBar);
            this.listaVidaBar.add(progressBar);
            
            
            
          //Imagen Corazon
//            progressBar.setForeground(new Color(138, 226, 52));
            ImageIcon corazoncito= new ImageIcon("src/Sprites/Corazon.png");
            JLabel corazon= new JLabel (corazoncito);
            corazon.setBounds(posicionPokemonX-25+32*i, posicionPokemonY + 410, 18, 15);
            this.mainPanel.add(corazon);

          //Imagen Rayo
            ImageIcon rayito= new ImageIcon("src/Sprites/rayo.png");
            JLabel rayo= new JLabel (rayito);
            rayo.setBounds(posicionPokemonX-25+32*i, posicionPokemonY + 389, 18, 15);
            rayo.setOpaque(true);
            this.mainPanel.add(rayo);
            
            
          //Agregar información encima de la foto del pokemon
            JTextArea infoPokemonTextArea = new JTextArea();
            infoPokemonTextArea.setBounds(posicionPokemonX + 30*i+40, 20, 150, 70);
            this.listaInfor.add(infoPokemonTextArea);
            for(String info1 : infoPokemon) {
            	infoPokemonTextArea.setText(infoPokemonTextArea.getText() + info1 + "\n");
                this.mainPanel.add(infoPokemonTextArea);
            }

            
            posicionPokemonX += 150;
        }
        setSize(200 + numPokemon * 165, 415);
        setLocationRelativeTo(null);
        setVisible(true);
        
    }

  
        


        
    
    
    public ControlerJugador getMiControlador() {
		if (miControlador==null) {
			miControlador=new ControlerJugador();
		}
		return miControlador;
	}
	
	
	private class ControlerJugador implements ActionListener,  MouseListener {
		
	    private ControlerJugador() {
	    	
	    }

		@Override
		public void actionPerformed(ActionEvent arg0) {
			int ind=0;
			boolean enc=false;
			while(ind<InterfazJugador.this.lBotonPokemon.size()&&!(enc)) {
				JButton b = (JButton) arg0.getSource();
				if (b.getName().equals(InterfazJugador.this.lBotonPokemon.get(ind).getName())){
					GestorJuegoPokemon.getMiGestorJuegoPokemon().administraAtaque(ind,GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(getName()));
					enc=true;
				}
				ind++;
			}
		}//

		@Override
		public void mouseClicked(MouseEvent arg0) {
			if (arg0.getSource().equals(btnCambioX)) {//Mirar si se ha pulsado
				GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().mirarBotonCambio(getName());
			}
		}
			
		@Override
		public void mouseEntered(MouseEvent arg0) {}
		

		@Override
		public void mouseExited(MouseEvent arg0) {}
			
		@Override
		public void mousePressed(MouseEvent arg0) {}
			
		@Override
		public void mouseReleased(MouseEvent arg0) {}
			
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof SuperJugador) {
			String[] update = (String[])arg;
	        System.out.println(o);
	        // Cambiar el color del botón según el turno
	        System.out.println(update[0]);
	        if (!update[0].equals("0")) {
	        	
	            // Es el turno del jugador o NPC, cambiar el color a verde
	            this.btnCambioX.setBackground(Color.GREEN);
	            this.btnCambioX.setText("Tu Turno");
	        } else {
	            // No es el turno del jugador o NPC, cambiar el color a amarillo
	            this.btnCambioX.setBackground(Color.ORANGE);
	            this.btnCambioX.setText("Espera");
	        }
	        

	    }
		if (o instanceof Pokemon) {			
			int i=GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(playerName).getMiEquipo().getPosPokeObservable(((Pokemon) o).getNombre()) ; 

			//	        	String infoPokemon = ("Ataque: "+equipoJugador.getPokemon(i).getAtaque()+"\n" +"Defensa: "+equipoJugador.getPokemon(i).getDefensa()+"\n"+" Vida: "+equipoJugador.getPokemon(i).getVida()+ "\n" +" Tipo: "+equipoJugador.getPokemon(i).getTipo());
//	            this.listaInfor.get(i).setText(infoPokemon);
//	            //Euforia
//	            int euforia=GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().mirarJugador(getName()).getMiEquipo().getPokemon(i).getEuforia();
//	            int euforiaMax=GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().mirarJugador(getName()).getMiEquipo().getPokemon(i).getEuforiaMax();
//	            int carga=(int) (100.0F*Float.parseFloat(Integer.toString(euforia)) / Integer.parseInt(Integer.toString(euforiaMax)));
//	            this.lEuforiaBoton.get(i).setValue(carga);
//	            //Vida
//	            int vida= (int)(100.0F * GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(playerName).getMiEquipo().getPokemon(i).getVida()/ GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(playerName).getMiEquipo().getPokemon(i).getVidaMax());
//	            this.listaVidaBar.get(i).setValue(vida);
			String[] rr = (String[])arg;
//			int vidaPorcentaje = (int)(100.0F * Float.parseFloat(rr[2]) / Integer.parseInt(rr[3]));
//			this.listaVidaBar.get(i).setValue(vidaPorcentaje);
//			
//			if (vidaPorcentaje <= 50) {
//				this.listaVidaBar.get(i).setForeground(Color.ORANGE);
//				ImageIcon imagenPokemon;
//				if (equipoJugador.getPokemon(i).getTipo().equals("Fuego")) {
//	            	imagenPokemon=new ImageIcon("src/sprites/Fire/1charmeleon.png");
//	            	this.pokemonImageLabels.get(i).setIcon(imagenPokemon);
//	            }else if  (equipoJugador.getPokemon(i).getTipo().equals("Agua")) {
//		           	imagenPokemon=new ImageIcon("src/sprites/Water/1wartortle.png");
//		           	this.pokemonImageLabels.get(i).setIcon(imagenPokemon);
//		        } else if (equipoJugador.getPokemon(i).getTipo().equals("Planta")) {
//		           	imagenPokemon=new ImageIcon("src/sprites/1ivysaur.png");
//		           	this.pokemonImageLabels.get(i).setIcon(imagenPokemon);
//	            } else {
//	            	imagenPokemon=new ImageIcon("src/sprites/Electrico/1raichu.png");
//	            	this.pokemonImageLabels.get(i).setIcon(imagenPokemon);
//		        }  
//			}
//			if (vidaPorcentaje <= 15) {
//				this.listaVidaBar.get(i).setForeground(Color.RED);
//				ImageIcon imagenPokemon;
//				if (equipoJugador.getPokemon(i).getTipo().equals("Fuego")) {
//		           	imagenPokemon=new ImageIcon("src/sprites/Fire/2charizard.png");
//		           	this.pokemonImageLabels.get(i).setIcon(imagenPokemon);
//		        }else if  (equipoJugador.getPokemon(i).getTipo().equals("Agua")) {
//		           	imagenPokemon=new ImageIcon("src/sprites/Water/2blastoise.png");
//		           	this.pokemonImageLabels.get(i).setIcon(imagenPokemon);
//	            } else if (equipoJugador.getPokemon(i).getTipo().equals("Planta")) {
//	            	imagenPokemon=new ImageIcon("src/sprites/2venusaur.png");
//	            	this.pokemonImageLabels.get(i).setIcon(imagenPokemon);
//		        } else {
//		           	imagenPokemon=new ImageIcon("src/sprites/Electrico/2raichu2.png");
//		           	this.pokemonImageLabels.get(i).setIcon(imagenPokemon);
//		        }  
//			}
			int euforiaPorcentaje = (int)(100.0F * Float.parseFloat(rr[7]) / Integer.parseInt(rr[6]));
			this.lEuforiaBoton.get(i).setValue(euforiaPorcentaje);
			if (Integer.parseInt((String)rr[2]) >= 1) {       
				if (euforiaPorcentaje == 100) {
					this.listaInfor.get(i).setForeground(new Color(255, 99, 71));
			    } else {   
			    	this.listaInfor.get(i).setForeground(Color.BLACK);
				}
			}else {		       
				this.listaInfor.get(i).setForeground(Color.WHITE);
//				this.listaInfor.get(i).setText("\n\nEste pokemon ha muerto");
				this.lBotonPokemon.get(i).setForeground(Color.WHITE);
				this.lEuforiaBoton.get(i).setForeground(Color.WHITE);
				this.listaVidaBar.get(i).setForeground(Color.WHITE);
				this.pokemonImageLabels.get(i).setForeground(Color.RED);
				this.lBotonPokemon.get(i).setBackground(Color.WHITE);
				this.lEuforiaBoton.get(i).setBackground(Color.WHITE);
				this.listaVidaBar.get(i).setBackground(Color.WHITE);
				this.pokemonImageLabels.get(i).setBackground(Color.RED);
			} 
			this.listaInfor.get(i).setText("Ataque: " + rr[0] + "\nDefensa: " + rr[1] + "\nVida: " + rr[2] + "\nTipo: " + rr[4]);        	
		
			if (rr[8].equals("true")) {
				this.btnCambioX.setBackground(Color.RED);
				this.btnCambioX.setText("Derrotado");
			}
			ActionListener listener= new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String[] rr = (String[])arg;
					
					int vidaPorcentaje = (int)(100.0F * Float.parseFloat(rr[2]) / Integer.parseInt(rr[3]));
					listaVidaBar.get(i).setValue(vidaPorcentaje);
					if (vidaPorcentaje <= 50) {
						listaVidaBar.get(i).setForeground(Color.ORANGE);
						ImageIcon imagenPokemon;
						if (equipoJugador.getPokemon(i).getTipo().equals("Fuego")) {
			            	imagenPokemon=new ImageIcon("src/sprites/Fire/1charmeleon.png");
			            	pokemonImageLabels.get(i).setIcon(imagenPokemon);
			            }else if  (equipoJugador.getPokemon(i).getTipo().equals("Agua")) {
				           	imagenPokemon=new ImageIcon("src/sprites/Water/1wartortle.png");
				           	pokemonImageLabels.get(i).setIcon(imagenPokemon);
				        } else if (equipoJugador.getPokemon(i).getTipo().equals("Planta")) {
				           	imagenPokemon=new ImageIcon("src/sprites/1ivysaur.png");
				           	pokemonImageLabels.get(i).setIcon(imagenPokemon);
			            } else {
			            	imagenPokemon=new ImageIcon("src/sprites/Electrico/1raichu.png");
			            	pokemonImageLabels.get(i).setIcon(imagenPokemon);
				        }  
					}
					if (vidaPorcentaje <= 15) {
						listaVidaBar.get(i).setForeground(Color.RED);
						ImageIcon imagenPokemon;
						if (equipoJugador.getPokemon(i).getTipo().equals("Fuego")) {
				           	imagenPokemon=new ImageIcon("src/sprites/Fire/2charizard.png");
				           	pokemonImageLabels.get(i).setIcon(imagenPokemon);
				        }else if  (equipoJugador.getPokemon(i).getTipo().equals("Agua")) {
				           	imagenPokemon=new ImageIcon("src/sprites/Water/2blastoise.png");
				           	pokemonImageLabels.get(i).setIcon(imagenPokemon);
			            } else if (equipoJugador.getPokemon(i).getTipo().equals("Planta")) {
			            	imagenPokemon=new ImageIcon("src/sprites/2venusaur.png");
			            	pokemonImageLabels.get(i).setIcon(imagenPokemon);
				        } else {
				           	imagenPokemon=new ImageIcon("src/sprites/Electrico/2raichu2.png");
				           	pokemonImageLabels.get(i).setIcon(imagenPokemon);
				        }  
					}
					
				}
				
			};
			Timer timer= new Timer(2000, listener);
			timer.setRepeats(false);
			timer.start();
		}
	}
		
		
	
		
	public String getName() {
		return this.playerName;
	}
    	
}