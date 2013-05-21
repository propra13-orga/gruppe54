package propra2013.Gruppe54;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.*;

public class Frame extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static String title ="Dungeon Crawler";
	public static Dimension size = new Dimension(1000,650); 
	
	public static Frame frame;
	
    public static Spielfeld spielfeld = new Spielfeld();
    //Menü
    public static JButton enter = new JButton("Spielen");
	public static JButton info = new JButton("Info");
	public static JButton schließen = new JButton("Verlassen");
	public static JButton menü = new JButton("Hauptmenü");
	public static JButton neustart = new JButton("Neustart");
	public static JButton nextLevel = new JButton("Nächstes Level");
	//Charakterauswahl
	public static JLabel charakter = new JLabel();
	public static JLabel charakterBild = new JLabel();
	public static JButton PfeilRechts = new JButton();
	public static JButton PfeilLinks = new JButton();
	public static ImageIcon Figur2_rechts = new ImageIcon("pics/Figur2_rechts.png");
	public static ImageIcon Figur2_links = new ImageIcon("pics/Figur2_links.png");
	public static ImageIcon Figur2_unten = new ImageIcon("pics/Figur2_unten.png");
	public static ImageIcon Figur2_oben = new ImageIcon("pics/Figur2_oben.png");
	public static ImageIcon Figur1_rechts = new ImageIcon("pics/Figur1_rechts.png");
	public static ImageIcon Figur1_links = new ImageIcon("pics/Figur1_links.png");
	public static ImageIcon Figur1_unten = new ImageIcon("pics/Figur1_unten.png");
	public static ImageIcon Figur1_oben = new ImageIcon("pics/Figur1_oben.png");
	public static int CharakterAuswahl;
	public static Image image;
	//Lebensanzeige
	public static JLabel leben = new JLabel();
	public static ImageIcon lebensanzeige = new ImageIcon("pics/lebensanzeige.png");
	
	
	public static Timer time;
	
	public static int spielerx=0,spielery=0;
	//Levelauswahl
	public static String auswahl[] = {"Level1","Level2","Level3"};
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static JComboBox levelAuswahl = new JComboBox(auswahl);
	
	/**
	 * Konstruktor
	 */
	public Frame(){
		setTitle(title);
		setSize(size);
		setResizable(false);
		setLayout(null); 
		this.setFocusable(true);
		setLocationRelativeTo(null); //Frame in der Mitte
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//Button Hauptmenü, nur im "Spielmodus" sichtbar
		menü.setBounds(75,25,130,20);
		menü.setVisible(false);
		
		//Button Neustart, nur im "Spielmodus" sichtbar wenn der Spieler kein Leben mehr hat
		neustart.setBounds(220, 25, 100, 20);
		neustart.setVisible(false);
		
		//Button nächstes Level, nur sichtbar wenn ein Level erfolgreich absolviert wurde
		nextLevel.setBounds(340,25,150,20);
		nextLevel.setVisible(false);
		
		//Label für die Lebensanzeige
		leben.setBounds(75,540,Spielfeld.spieler.leben*2,10);
		leben.setIcon(lebensanzeige);
		leben.setVisible(false);
		
		//Label Charakterauswahl
		charakter.setBounds(460,150,150,30);
		charakter.setText("Charakterauswahl:");
		charakter.setVisible(true);
		add(charakter);
		
		//Label für das Charakter-Bild
		charakterBild.setBounds(540, 200, 32, 32);
		charakterBild.setVisible(true);
		charakterBild.setIcon(Figur1_unten);
		image = Figur1_unten.getImage();
		add(charakterBild);
		
		CharakterAuswahl = 1;
		
		//Buttons zur Charakterauswahl
		PfeilRechts.setBounds(600, 205, 50, 20);
		PfeilLinks.setBounds(460, 205, 50, 20);
		PfeilRechts.setVisible(true);
		PfeilLinks.setVisible(true);
		PfeilRechts.setText(">");
		PfeilLinks.setText("<");
		add(PfeilLinks);
		add(PfeilRechts);
		
		//Anzeige des Menüs
		enter.setBounds(250, 100, 150, 30);		//Button Enter
		enter.setVisible(true);
		add(enter);
		
		info.setBounds(250,150,150,30);			//Button Info
		info.setVisible(true);
		add(info);
		
		schließen.setBounds(250, 200, 150, 30); //Button Schließen
		schließen.setVisible(true);
		add(schließen);
		
		levelAuswahl.setBounds(250, 250, 150, 30);	//ComboBox Levelauswahl
		levelAuswahl.setVisible(true);
		add(levelAuswahl);
		
		
		/*
		 * 
		 * 
		 *   KeyEvents - Steuerung der Figur
		 * 
		 * 
		 */
		
		addKeyListener(new KeyListener(){
			@SuppressWarnings("static-access")
			public void keyPressed(KeyEvent e){
		        int key = e.getKeyCode();
		      //nur bewegen wenn der Spieler aktiv ist
		      if((Spielfeld.spieler.aktiv)&&(Spielfeld.spieler.beweglich)){
		    	  //prüfen welche ID die stelle an die gegangen werden soll hat und nur laufen wenn es keine Mauer ist
		    	  //zusätzlich prüfen ob und wenn ja welches Element oder welche Falle dort liegt
		         if ((key == KeyEvent.VK_A)&&(spielfeld.getBlockID(spieler.x+2, spieler.y+26)!=1)&&(spielfeld.getBlockID(spieler.x+2, spieler.y+32)!=1)) {
		        	Elemente.Aufruf(spielfeld.getBlockID(spieler.x+4, spieler.y+28),spielfeld.getBlock(spieler.x+4, spieler.y+28));//unten links
		            spieler.x-=4;
		            spieler.links = true;
		            spieler.rechts = false;
		            spieler.hoch = false;
		            spieler.runter = false;
		            if (CharakterAuswahl==2){
		            	image = Figur2_links.getImage();
		            }else if (CharakterAuswahl ==1){
		            	image = Figur1_links.getImage();
		            }
		         }

		         if ((key == KeyEvent.VK_D)&&(spielfeld.getBlockID(spieler.x+30, spieler.y+26)!=1)&&(spielfeld.getBlockID(spieler.x+30, spieler.y+32)!=1)) {
			        Elemente.Aufruf(spielfeld.getBlockID(spieler.x+28, spieler.y+28),spielfeld.getBlock(spieler.x+28, spieler.y+28));//unten rechts
		         	spieler.x+=4;
		         	spieler.links = false;
		         	spieler.rechts = true;
		         	spieler.hoch = false;
		            spieler.runter = false;
		            if (CharakterAuswahl==2){
		            	image = Figur2_rechts.getImage();
		            } else if (CharakterAuswahl ==1){
		            	image = Figur1_rechts.getImage();
		            }
		         }

		         if ((key == KeyEvent.VK_W)&&(spielfeld.getBlockID(spieler.x+6, spieler.y+23)!=1)&&(spielfeld.getBlockID(spieler.x+26, spieler.y+23)!=1)) {
			        Elemente.Aufruf(spielfeld.getBlockID(spieler.x+8, spieler.y+24),spielfeld.getBlock(spieler.x+8, spieler.y+24));//mitte links
			        if(Elemente.beruehrung == false){ //zweiten Punkt prüfen
			        	Elemente.Aufruf(spielfeld.getBlockID(spieler.x+24, spieler.y+24),spielfeld.getBlock(spieler.x+24, spieler.y+24));//mitte rechts
			        }
			        spieler.y-=4;
		        	Elemente.beruehrung = false;
		        	spieler.links = false;
		         	spieler.rechts = false;
		         	spieler.hoch = true;
		            spieler.runter = false;
		            if (CharakterAuswahl==2){
		            	image = Figur2_oben.getImage();
		            }else if (CharakterAuswahl ==1){
		            	image = Figur1_oben.getImage();
		            }
		         }
		         
		         if ((key == KeyEvent.VK_S)&&(spielfeld.getBlockID(spieler.x+6, spieler.y+36)!=1)&&(spielfeld.getBlockID(spieler.x+26, spieler.y+36)!=1)) {
			        Elemente.Aufruf(spielfeld.getBlockID(spieler.x+8, spieler.y+32),spielfeld.getBlock(spieler.x+8, spieler.y+32));//unten links
			        if(Elemente.beruehrung == false){ //zweiten Punkt prüfen
			        	Elemente.Aufruf(spielfeld.getBlockID(spieler.x+24, spieler.y+32),spielfeld.getBlock(spieler.x+24, spieler.y+32));//unten rechts
			        }
			        spieler.y+=4;
			        Elemente.beruehrung = false;
			        spieler.links = false;
			        spieler.rechts = false;
			        spieler.hoch = false;
			        spieler.runter = true;
			        if (CharakterAuswahl==2){
		            	image = Figur2_unten.getImage();
		            }else if (CharakterAuswahl ==1){
		            	image = Figur1_unten.getImage();
		            }
		         }
	
		      }
			}


			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		
		//Combobox event
		levelAuswahl.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JComboBox<?> selectedChoice = (JComboBox<?>) e.getSource();
				
				if("Level1".equals(selectedChoice.getSelectedItem())){				//Listenauswahl: Level1
					Spielfeld.current_lvl=1;
				} else if("Level2".equals(selectedChoice.getSelectedItem())){		//Level2
					Spielfeld.current_lvl=2;
				} else if("Level3".equals(selectedChoice.getSelectedItem())){		//Level3
					Spielfeld.current_lvl=3;
				}
				
			}
		});
		
		//Button PfeilRechts Click
		PfeilRechts.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				charakterBild.setIcon(Figur2_unten);
				image = Figur2_unten.getImage();
				CharakterAuswahl = 2;
			}
		});
		
		//Button PfeilLinks Click
		PfeilLinks.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				charakterBild.setIcon(Figur1_unten);
				image = Figur1_unten.getImage();
				CharakterAuswahl =1;
			}
		});
		
		//Button Enter Click
		enter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//Buttons ausblenden und entfernen
				enter.setVisible(false);
				info.setVisible(false);
				schließen.setVisible(false);
				charakter.setVisible(false);
				PfeilRechts.setVisible(false);
				PfeilLinks.setVisible(false);
				charakterBild.setVisible(false);
				remove(PfeilRechts);
				remove(PfeilLinks);
				remove(charakterBild);
				remove(enter);
				remove(info);
				remove(schließen);
				remove(levelAuswahl);
				remove(charakter);

				Spielfeld.current_room=1;
				
				//Spielfeld anzeigen	
				Spielfeld.level.loadLevel(new File("level/level"+Spielfeld.current_lvl+"_"+Spielfeld.current_room+".lvl"));    //Level laden
				add(spielfeld);						//hinzufügen
				spielfeld.setVisible(true);	
				//Button Hauptmenü hinzufügen
				add(menü);
				add(neustart);
				add(nextLevel);
				menü.setVisible(true);
				
				add(leben);
				leben.setVisible(true);
				Spielfeld.isFirst = true;
				//Bilder des Levels laden
				Spielfeld.loadImages();
				
				//Spieler auf den Startpunkt des jeweiligen Levels setzen
				spieler.x = Raum.Startpunkt[Spielfeld.current_lvl-1].x;
				spieler.y = Raum.Startpunkt[Spielfeld.current_lvl-1].y;
				spieler.beweglich = true;
			}
		});
		
		//Button Neustart Click
		neustart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//aktuelles Level in Raum 1 neu laden
				Spielfeld.level.loadLevel(new File("level/level"+Spielfeld.current_lvl+"_1.lvl"));
				//Spieler auf den Startpunkt des jeweiligen Levels setzen
				Spielfeld.spieler.x = Raum.Startpunkt[Spielfeld.current_lvl-1].x;
				Spielfeld.spieler.y = Raum.Startpunkt[Spielfeld.current_lvl-1].y;
				Spielfeld.spieler.aktiv = true;
				Spielfeld.spieler.beweglich = true;
				Spielfeld.spieler.leben = 100;
				neustart.setVisible(false);
				nextLevel.setVisible(false);
			}
		});
		
		nextLevel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Spielfeld.current_lvl += 1;
				Spielfeld.current_room = 1; 
				Spielfeld.level.loadLevel(new File("level/level"+Spielfeld.current_lvl+"_"+Spielfeld.current_room+".lvl"));    //Level laden
	            spieler.x = Raum.Startpunkt[Spielfeld.current_lvl-1].x;
				spieler.y = Raum.Startpunkt[Spielfeld.current_lvl-1].y;
				spieler.beweglich = true;
				Spielfeld.loadImages();
				nextLevel.setVisible(false);
				neustart.setVisible(false);
			}
		});
		
		//Button Hauptmenü Click
		menü.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				menü.setVisible(false);
				remove(menü);
				leben.setVisible(false);
				remove(leben);
				neustart.setVisible(false);
				nextLevel.setVisible(false);
				spielfeld.setVisible(false);
				setLayout(null);
				enter.setVisible(true);
				info.setVisible(true);
				schließen.setVisible(true);
				levelAuswahl.setVisible(true);
				charakter.setVisible(true);
				PfeilRechts.setVisible(true);
				PfeilLinks.setVisible(true);
				charakterBild.setVisible(true);
				add(PfeilRechts);
				add(PfeilLinks);
				add(charakterBild);
				add(enter);
				add(info);
				add(schließen);
				add(levelAuswahl);
				add(charakter);
				init();
				levelAuswahl.setSelectedItem("Level"+Spielfeld.current_lvl);
			}
		});
		
		//Button schließen Click
		schließen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		
		init();
	}
	
	/**
	 * Initialisierung
	 */
	public void init(){
		validate();
		repaint();
		setVisible(true);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Frame frame = new Frame();
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	}



