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
import Modelo.Tablero;


public class InterfazJugador extends JFrame implements Observer {
	private ArrayList<JButton> lBotonPokemon=null;
	private String playerName;
	private int numPokemon;
	private int nJugador;
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JLabel playerImageLabel;
    private JLabel[] pokemonImageLabels;
    private JPanel panelPokemon;
    private ControlerJugador miControlador;
    private JButton botonPokemon; 
//    private ArrayList<PokePanel> cpList = new ArrayList<>();
    private JButton btnSwitchX;

   
    
    
    public InterfazJugador (String playerName, int numPokemon, Equipo pEquipo) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inicializar(playerName, numPokemon,pEquipo);
		setVisible(true);
		if (playerName.equals("Jugador")) {
			//Añadir
		}else {
			//Añadir Observer NPC
		}
		//GestorJuegoPokemon.getMiGestorJuegoPokemon().addObserver(this);
	}
    private void inicializar(String playerName, int numPokemon, Equipo pEquipo) {
    	this.numPokemon=numPokemon;
    	this.playerName=playerName;
    	setTitle(playerName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Creamos el panel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);
        getContentPane().add(panelPrincipal);

        // Creamos el botón en la esquina superior izquierda
        this.btnSwitchX = new JButton("Turno");
        btnSwitchX.setBounds(10, 10, 100, 30);
        panelPrincipal.add(btnSwitchX);
        btnSwitchX.addMouseListener(getMiControlador());

        // Creamos la imagen del jugador
        Random rand = new Random();
        int minimo = 0;
        int maximo = 5;
        int numeroAleatorio = rand.nextInt((maximo - minimo) + 1) + minimo;
        ImageIcon imagenJugador = new ImageIcon("src/Sprites/trainer"+(numeroAleatorio)+".png");
        JLabel labelImagenJugador = new JLabel(imagenJugador);
        labelImagenJugador.setBounds(10, 50, 300, 350);
        panelPrincipal.add(labelImagenJugador);

        // Creamos las imágenes de los pokemons
        int posicionPokemonX = 320;
        int posicionPokemonY = 50;
        
        this.lBotonPokemon= new ArrayList<JButton>();
        for (int i = 0; i < numPokemon; i++) {  
        	String[] infoPokemon = {"Ataque: "+pEquipo.getPokemon(i).getAtaque(),"Defensa: "+pEquipo.getPokemon(i).getDefensa(), "Vida: "+pEquipo.getPokemon(i).getVida(), "Tipo: "+pEquipo.getPokemon(i).getTipo()};
        	Random rand1 = new Random();
            int minimo1 = 1;
            int maximo1 = 5;
            int numeroAleatorio1 = rand1.nextInt((maximo1 - minimo1) + 1) + minimo1;
            System.out.println(numeroAleatorio1);
            ImageIcon imagenPokemon = new ImageIcon("src/Sprites/Pokemon" + (numeroAleatorio1) + ".png");
            JLabel labelImagenPokemon = new JLabel(imagenPokemon);
            labelImagenPokemon.setBounds(posicionPokemonX, posicionPokemonY, 250, 350);
            panelPrincipal.add(labelImagenPokemon);
            
//            JPanel panelPokemon = new JPanel();
//            panelPokemon.setBounds(posicionPokemonX, posicionPokemonY, 250, 350);
//            panelPrincipal.add(panelPokemon);
//            PokePanel cp = new PokePanel(this.playerName,i);
//            this.getCardsPanel().add(cp);
//            this.cpList.add(cp);
//            
            JButton atacarPoke = new JButton("Ataca!"+" "+(i+1)); 
            atacarPoke.setName("Boton" +(i+1));
            this.lBotonPokemon.add(atacarPoke);
            atacarPoke.setBounds(posicionPokemonX, posicionPokemonY + 350, 250, 30);
            panelPrincipal.add(atacarPoke);
            atacarPoke.addActionListener(getMiControlador());
            
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
	
	
	private class ControlerJugador implements ActionListener,  MouseListener {
		
	    private ControlerJugador() {
	    	
	    }

		@Override
		public void actionPerformed(ActionEvent arg0) {
			int ind=0;
			boolean enc=false;
			System.out.println(" Datos "+ ind+" "+enc+ " "+ GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarTurno(getName()) );
			while(ind<InterfazJugador.this.lBotonPokemon.size()&& !(enc)  ){
				JButton b = (JButton) arg0.getSource();
				System.out.println(InterfazJugador.this.lBotonPokemon.get(ind).getName());
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
				}
				
				ind++;

			}
			
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			if (arg0.getSource().equals(btnSwitchX)) {
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
	            Tablero.getMiTablero().setJugadorAtacante(GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(InterfazJugador.this.playerName));
				Tablero.getMiTablero().setAtacantePokemon(GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(InterfazJugador.this.playerName).getMiEquipo().getPokemon(numeroAleatorio1-1));
				Tablero.getMiTablero().setDefendsPlayer(GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().getSuperJugadorPosicion(iJugador).getNombre()));
				Tablero.getMiTablero().setDefensaPokemon(GestorJuegoPokemon.getMiGestorJuegoPokemon().mirarJugador(GestorJuegoPokemon.getMiGestorJuegoPokemon().getLista().getSuperJugadorPosicion(iJugador).getNombre()).getMiEquipo().getPokemon(numeroAleatorio2-1));
				System.out.println("Jugador Defensor "+Tablero.getMiTablero().getJugador2().getNombre()+" Jugador Atacante "+Tablero.getMiTablero().getJugador1().getNombre()+" Pokemon Defensor "+Tablero.getMiTablero().getPokemon2().getNombre()+" Pokemon Atacante"+Tablero.getMiTablero().getPokemon1().getNombre());
				Tablero.getMiTablero().atacar();
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
//	public ArrayList<JButton> getArray(){
//		return this.lbotonPokemon;
//	}


	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
//	public int sizePoke() {
//		return this.lbotonPokemon.size();
//	}
	
	public String getName() {
		return this.playerName;
	}
    
//	public int getPosicionBoton(JButton botonPokemon) {
//		int ind=0;
//		for (int i=0;i<this.lbotonPokemon.size();i++) {
//			if (this.lbotonPokemon.get(i).equals(botonPokemon)) {
//				ind=i;;
//			}
//		}
//		return ind;
//	}
	 public JPanel getCardsPanel() {
		 /*  93 */     if (this.panelPokemon == null) {
		 /*  94 */       this.panelPokemon = new JPanel();
		 /*  95 */       this.panelPokemon.setBackground(Color.LIGHT_GRAY);
		 /*     */     } 
		 /*  97 */     return this.panelPokemon;
		 /*     */   }
}
