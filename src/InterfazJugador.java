package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Modelo.Equipo;
import Modelo.GestorJuegoPokemon;
import Modelo.Jugador;
import Modelo.NPC;
import Modelo.Pokemon;
import Modelo.SuperJugador;
import Modelo.Tablero;


public class InterfazJugador extends JFrame implements Observer {
	private ArrayList<JButton> lBotonPokemon=null;
	private String playerName;
	private Equipo equipoJugador;
	private int numPokemon;
    private JPanel mainPanel= new JPanel();
    private JLabel playerImageLabel=new JLabel();
    private JLabel[] pokemonImageLabels;
    private JPanel panelPokemon;
    private ArrayList<JLabel> listaInfor=new ArrayList<JLabel>();
    private ControlerJugador miControlador; 
    private JButton btnCambioX;
    Random rand1 = new Random();

   
    
    
    public InterfazJugador (String playerName, int numPokemon, Equipo pEquipo) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inicializar(playerName, numPokemon,pEquipo);
		setVisible(true);
		GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().mirarJugador(playerName).addObserver(this);
		for (int i=0;i<numPokemon;i++) {
			GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().mirarJugador(playerName).getMiEquipo().getPokemon(i).addObserver(this);
		}
	}
    private void inicializar(String playerName, int numPokemon, Equipo pEquipo) {
    	this.numPokemon=numPokemon;
    	this.playerName=playerName;
    	this.equipoJugador=pEquipo;
    	setTitle(playerName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Creamos el panel principal
        this.mainPanel.setLayout(null);
        getContentPane().add(this.mainPanel);

        // Creamos el botón en la esquina superior izquierda
        this.btnCambioX = new JButton();
        btnCambioX.setBounds(10, 10, 100, 30);
        boolean k=GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarTurno(playerName);
        if (k) {
        	btnCambioX.setBackground(Color.GREEN);
        	btnCambioX.setText("Tu Turno");
          } else {
        	  btnCambioX.setBackground(Color.YELLOW);
        	  btnCambioX.setText("Espera");
          }
        this.mainPanel.add(btnCambioX);
        btnCambioX.addMouseListener(getMiControlador());

        //Imagen Jugador
        ImageIcon imagenJugador = new ImageIcon("src/sprites/trainer"+(rand1.nextInt(6))+".png");
        this.playerImageLabel.setIcon(imagenJugador);
        this.playerImageLabel.setBounds(10, 50, 300, 350);
        this.mainPanel.add(this.playerImageLabel);

        // Creamos las imágenes de los pokemons
        int posicionPokemonX = 320;
        int posicionPokemonY = 50;
        
        this.lBotonPokemon= new ArrayList<JButton>();
        for (int i = 0; i < numPokemon; i++) {  
        	String[] infoPokemon = {"Ataque: "+equipoJugador.getPokemon(i).getAtaque()," Defensa: "+equipoJugador.getPokemon(i).getDefensa(), " Vida: "+equipoJugador.getPokemon(i).getVida(), " Tipo: "+equipoJugador.getPokemon(i).getTipo()};
            ImageIcon imagenPokemon = new ImageIcon("src/sprites/" + (rand1.nextInt(11)+1) + ".png");
            JLabel labelImagenPokemon = new JLabel(imagenPokemon);
            labelImagenPokemon.setBounds(posicionPokemonX, posicionPokemonY, 250, 350);
            this.mainPanel.add(labelImagenPokemon);
                      
            JButton atacarPoke = new JButton("Ataca!"); 
            atacarPoke.setName("Boton" +(i+1));
            this.lBotonPokemon.add(atacarPoke);
            atacarPoke.setBounds(posicionPokemonX, posicionPokemonY + 350, 250, 30);
            this.mainPanel.add(atacarPoke);
            atacarPoke.addActionListener(getMiControlador());
            
          //Agregar información encima de la foto del pokemon
            JLabel infoPokemonLabel = new JLabel();
            infoPokemonLabel.setBounds(300 + 250*i, 50, 250, 50);
            this.listaInfor.add(infoPokemonLabel);
            for(String info1 : infoPokemon) {
                infoPokemonLabel.setText(infoPokemonLabel.getText() + info1 + "\n");
                this.mainPanel.add(infoPokemonLabel);
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
	
	
	private class ControlerJugador implements ActionListener,  MouseListener {
		
	    private ControlerJugador() {
	    	
	    }

		@Override
		public void actionPerformed(ActionEvent arg0) {
//			int ind=0;
//			boolean enc=false;
//			while(ind<InterfazJugador.this.lBotonPokemon.size()&& !(enc)){
//				JButton b = (JButton) arg0.getSource();
////				System.out.println(InterfazJugador.this.lBotonPokemon.get(ind).getName());
//				if (b.getName().equals(InterfazJugador.this.lBotonPokemon.get(ind).getName()) &&  !(GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(getName()) instanceof Modelo.NPC)&& GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarTurno(getName())&& !(GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(getName()).getDerrotado())) { 	
//					if(!GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(getName()).getMiEquipo().getPokemon(ind).estaMuerto()) {
//						if (!GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().mirarJugador(playerName).getMiEquipo().getPokemon(ind).getAtaca()) {
//							enc=true;
////							System.out.println("Has entrado en lo importante");
//							Tablero.getMiTablero().setJugadorAtacante(GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(InterfazJugador.this.playerName));
//							Tablero.getMiTablero().setAtacantePokemon(GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(getName()).getMiEquipo().getPokemon(ind));
//							System.out.println("Jugador Atacante " + GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(InterfazJugador.this.playerName).getNombre()+ "  "+GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(getName()).getMiEquipo().getPokemon(ind).getNombre());
//							GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().mirarJugador(playerName).getMiEquipo().getPokemon(ind).setAtaca(true);
//						}else {
//							System.out.println("Ya ha atacado");
//						}
//							
//					}else {
//						System.out.println("Este Pokemon ha muerto, elige otro");
//					}
//					
//				}else if((GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(getName()) instanceof Modelo.NPC) && (b.getName().equals(InterfazJugador.this.lBotonPokemon.get(ind).getName()))){
//					if (GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(getName()).getMiEquipo().getPokemon(ind).getVida()!=0) {
//					Tablero.getMiTablero().setDefendsPlayer(GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(InterfazJugador.this.playerName));
//					Tablero.getMiTablero().setDefensaPokemon(GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(getName()).getMiEquipo().getPokemon(ind));
//					System.out.println("Jugador Defensor " + GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(InterfazJugador.this.playerName).getNombre()+ "  "+GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(getName()).getMiEquipo().getPokemon(ind).getNombre());
//			        Tablero.getMiTablero().atacar();
//			        enc=true;
//					}else {
//						System.out.println("Este Pokemon ya le has derrotado, busca otro");
//					}
//				}else if (b.getName().equals(InterfazJugador.this.lBotonPokemon.get(ind).getName()) &&  !(GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(getName()) instanceof Modelo.NPC)&& !GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarTurno(getName())) {
//					Tablero.getMiTablero().setDefendsPlayer(GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(InterfazJugador.this.playerName));
//					Tablero.getMiTablero().setDefensaPokemon(GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(getName()).getMiEquipo().getPokemon(ind));
//					System.out.println("Jugador Defensor " + GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(InterfazJugador.this.playerName).getNombre()+ "  "+GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(getName()).getMiEquipo().getPokemon(ind).getNombre());
//			        Tablero.getMiTablero().atacar();
//				}
//				ind++;
//			}
			int ind=0;
			boolean enc=false;
			while(ind<InterfazJugador.this.lBotonPokemon.size()&&!(enc)) {
				JButton b = (JButton) arg0.getSource();
				if (b.getName().equals(InterfazJugador.this.lBotonPokemon.get(ind).getName())){
					GestorJuegoPokemon.getMiGestorJuegoPokemon().administraAtaque(ind,GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(getName()));
					enc=true;
				}
			}
		}//

		@Override
		public void mouseClicked(MouseEvent arg0) {
			if (arg0.getSource().equals(btnCambioX)) {//Mirar si se ha pulsado
				if (GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().mirarJugador(getName()) instanceof Jugador) {//Mirar quien lo ha pulsado
					GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().mirarJugador(getName()).getMiEquipo().ponerPokeAtacados();
					GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().cambiarTurno(GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().mirarJugador(playerName));//Cambias Turno
					if (GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().mirarJugador(GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().getJugadorTurno().getNombre()) instanceof NPC) {
						GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().mirarJugador(GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().getJugadorTurno().getNombre()).atacarN();
						GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().cambiarTurno(GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().mirarJugador(GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().getJugadorTurno().getNombre()));
						SuperJugador npc=GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().getJugadorTurno();
							while(npc instanceof NPC) {
								npc.atacarN();
								GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().cambiarTurno(npc);
								npc=GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().getJugadorTurno();
							}
					}else {
						System.out.println("Pulsa los botones correspondientes");
					}
				}else {
					System.out.println("Soy NPC, no puedes darle aqui");
				}
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
		if (o instanceof Jugador || o instanceof NPC) {
	        // Obtener el objeto jugador o NPC que envió la actualización
	        Object jugadorNPC = o;
	        boolean turno = false;
	        
	        if (jugadorNPC instanceof Jugador) {
	            // Obtener el turno del jugador
	            turno = ((Jugador) jugadorNPC).getTurno();
	        } else if (jugadorNPC instanceof NPC) {
	            // Obtener el turno del NPC
	            turno = ((NPC) jugadorNPC).getTurno();
	        }
	        
	        // Cambiar el color del botón según el turno
	        if (turno) {
	            // Es el turno del jugador o NPC, cambiar el color a verde
	            this.btnCambioX.setBackground(Color.GREEN);
	            this.btnCambioX.setText("Tu Turno");
	        } else {
	            // No es el turno del jugador o NPC, cambiar el color a amarillo
	            this.btnCambioX.setBackground(Color.YELLOW);
	            this.btnCambioX.setText("Espera");
	        }
	        
//	        for (int i = 0; i < numPokemon; i++) {  
//	        	String infoPokemon = ("Ataque: "+equipoJugador.getPokemon(i).getAtaque()+"\n"+" Defensa: "+equipoJugador.getPokemon(i).getDefensa() +"\n"+" Vida: "+equipoJugador.getPokemon(i).getVida()+ "\n" +" Tipo: "+equipoJugador.getPokemon(i).getTipo());
//	            
//	        	
//	                this.listaInfor.get(i).setText(infoPokemon);	                
//	            
//	        }
	    }
		if (o instanceof Pokemon) {
			for (int i = 0; i < numPokemon; i++) {  
	        	String infoPokemon = ("Ataque: "+equipoJugador.getPokemon(i).getAtaque()+"\n"+" Defensa: "+equipoJugador.getPokemon(i).getDefensa() +"\n"+" Vida: "+equipoJugador.getPokemon(i).getVida()+ "\n" +" Tipo: "+equipoJugador.getPokemon(i).getTipo());
	            
	        	
	                this.listaInfor.get(i).setText(infoPokemon);	                
	            
	        }
		}
		
	}
		
	public String getName() {
		return this.playerName;
	}
    	
}