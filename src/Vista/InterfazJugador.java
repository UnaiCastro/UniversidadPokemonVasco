package Vista;

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
//    private JButton botonPokemon; 
    private JButton btnCambioX;

   
    
    
    public InterfazJugador (String playerName, int numPokemon, Equipo pEquipo) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inicializar(playerName, numPokemon,pEquipo);
		setVisible(true);
		GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().mirarJugador(playerName).addObserver(this);
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

        // Creamos la imagen del jugador
        Random rand = new Random();
        int minimo = 0;
        int maximo = 5;
        int numeroAleatorio = rand.nextInt((maximo - minimo) + 1) + minimo;
        ImageIcon imagenJugador = new ImageIcon("C:/Users/kasme/OneDrive/Escritorio/Pokemon_Fotos/Jugadores/trainer"+(numeroAleatorio)+".png");
//        JLabel labelImagenJugador = new JLabel(imagenJugador);
        this.playerImageLabel.setIcon(imagenJugador);
//        labelImagenJugador.setBounds(10, 50, 300, 350);
        this.playerImageLabel.setBounds(10, 50, 300, 350);
        this.mainPanel.add(this.playerImageLabel);

        // Creamos las imágenes de los pokemons
        int posicionPokemonX = 320;
        int posicionPokemonY = 50;
        
        this.lBotonPokemon= new ArrayList<JButton>();
        for (int i = 0; i < numPokemon; i++) {  
        	String[] infoPokemon = {"Ataque: "+equipoJugador.getPokemon(i).getAtaque(),"Defensa: "+equipoJugador.getPokemon(i).getDefensa(), "Vida: "+equipoJugador.getPokemon(i).getVida(), "Tipo: "+equipoJugador.getPokemon(i).getTipo()};
        	Random rand1 = new Random();
            int minimo1 = 1;
            int maximo1 = 5;
            int numeroAleatorio1 = rand1.nextInt((maximo1 - minimo1) + 1) + minimo1;
            System.out.println(numeroAleatorio1);
            ImageIcon imagenPokemon = new ImageIcon("C:/Users/kasme/OneDrive/Escritorio/Pokemon_Fotos/Pokemons/Pokemon" + (numeroAleatorio1) + ".png");
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
			int ind=0;
			boolean enc=false;
//			System.out.println(" Datos "+ ind+" "+enc+ " "+ GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarTurno(getName()) );
			while(ind<InterfazJugador.this.lBotonPokemon.size()&& !(enc)  ){
				JButton b = (JButton) arg0.getSource();
//				System.out.println(InterfazJugador.this.lBotonPokemon.get(ind).getName());
				if (b.getName().equals(InterfazJugador.this.lBotonPokemon.get(ind).getName()) &&  !(GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(getName()) instanceof Modelo.NPC)&& GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarTurno(getName())) { 	
					
					enc=true;
					System.out.println("Has entrado en lo importante");
					Tablero.getMiTablero().setJugadorAtacante(GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(InterfazJugador.this.playerName));
					Tablero.getMiTablero().setAtacantePokemon(GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(getName()).getMiEquipo().getPokemon(ind));
			        System.out.println("Jugador Atacante " + GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(InterfazJugador.this.playerName).getNombre()+ "  "+GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(getName()).getMiEquipo().getPokemon(ind).getNombre());
					
					
				}else if((GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(getName()) instanceof Modelo.NPC) && (b.getName().equals(InterfazJugador.this.lBotonPokemon.get(ind).getName()))){
					Tablero.getMiTablero().setDefendsPlayer(GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(InterfazJugador.this.playerName));
					Tablero.getMiTablero().setDefensaPokemon(GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(getName()).getMiEquipo().getPokemon(ind));
					System.out.println("Jugador Defensor " + GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(InterfazJugador.this.playerName).getNombre()+ "  "+GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(getName()).getMiEquipo().getPokemon(ind).getNombre());
			        Tablero.getMiTablero().atacar();
			        enc=true;
				}else if (b.getName().equals(InterfazJugador.this.lBotonPokemon.get(ind).getName()) &&  !(GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(getName()) instanceof Modelo.NPC)&& !GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarTurno(getName())) {
					Tablero.getMiTablero().setDefendsPlayer(GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(InterfazJugador.this.playerName));
					Tablero.getMiTablero().setDefensaPokemon(GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(getName()).getMiEquipo().getPokemon(ind));
					System.out.println("Jugador Defensor " + GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(InterfazJugador.this.playerName).getNombre()+ "  "+GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(getName()).getMiEquipo().getPokemon(ind).getNombre());
			        Tablero.getMiTablero().atacar();
				}//
//				else if ((GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(getName()) instanceof Modelo.NPC) && b.getName().equals(InterfazJugador.this.lBotonPokemon.get(ind).getName()) && (Tablero.getMiTablero().getJugador1()==null)) {
//					System.out.println("Selecciona primero un atacante ");
//
//				}
				
				ind++;

			}
			
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			if (arg0.getSource().equals(btnCambioX)) {
				boolean enc=false;
				int iJugador=0;
				while (!enc) {
					Random rand1 = new Random();
		            int minimo1 = 1;
		            int maximo1 = GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().getSize();
		            int numeroAleatorio1 = rand1.nextInt((maximo1 - minimo1) + 1) + minimo1;
		            if (!GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().getSuperJugadorPosicion(numeroAleatorio1-1).getNombre().equals(getName())) {
		            	enc=true;
		            	iJugador=numeroAleatorio1-1;
		            	
		            }
		            
				}
				Random rand1 = new Random();
	            int minimo1 = 1;
	            int maximo1 = InterfazJugador.this.numPokemon;
	            int numeroAleatorio1 = rand1.nextInt((maximo1 - minimo1) + 1) + minimo1;
	            Random rand2 = new Random();
	            int minimo2 = 1;
	            int maximo2 = InterfazJugador.this.numPokemon;
	            int numeroAleatorio2 = rand2.nextInt((maximo2 - minimo2) + 1) + minimo2;
	            if (GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(playerName).getTurno()) {
	            	if (GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().mirarJugador(playerName) instanceof NPC) {
	            		Tablero.getMiTablero().setJugadorAtacante(GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(InterfazJugador.this.playerName));
		            	Tablero.getMiTablero().setAtacantePokemon(GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(InterfazJugador.this.playerName).getMiEquipo().getPokemon(numeroAleatorio1-1));
		            	Tablero.getMiTablero().setDefendsPlayer(GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().getSuperJugadorPosicion(iJugador).getNombre()));
		            	Tablero.getMiTablero().setDefensaPokemon(GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().getSuperJugadorPosicion(iJugador).getNombre()).getMiEquipo().getPokemon(numeroAleatorio2-1));
		            	System.out.println("Jugador Defensor "+Tablero.getMiTablero().getJugador2().getNombre()+" Jugador Atacante "+Tablero.getMiTablero().getJugador1().getNombre()+" Pokemon Defensor "+Tablero.getMiTablero().getPokemon2().getNombre()+" Pokemon Atacante"+Tablero.getMiTablero().getPokemon1().getNombre());
		            	Tablero.getMiTablero().atacar();	
	            	}else {
	            		GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().cambiarTurno(GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().mirarJugador(playerName));
	            	}
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
	            // No es el turno del jugador o NPC, cambiar el color a rojo
	            this.btnCambioX.setBackground(Color.ORANGE);
	            this.btnCambioX.setText("Espera");
	        }
	        
	        for (int i = 0; i < numPokemon; i++) {  
	        	String infoPokemon = ("Ataque: "+equipoJugador.getPokemon(i).getAtaque()+"\n"+" Defensa: "+equipoJugador.getPokemon(i).getDefensa() +"\n"+" Vida: "+equipoJugador.getPokemon(i).getVida()+ "\n" +" Tipo: "+equipoJugador.getPokemon(i).getTipo());
	            
	        	
	                this.listaInfor.get(i).setText(infoPokemon);;	                
	            
	        }
	    }
		
	}
		
	public String getName() {
		return this.playerName;
	}
    	
}