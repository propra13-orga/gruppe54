package propra2013.Gruppe54;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Frame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static String title ="Dungeon Crawler";
	public static Dimension size = new Dimension(850,670); 
	
	public static Frame frame;
	
    public Spielfeld spielfeld = new Spielfeld();
    public Spielerinfo spielerinfo = new Spielerinfo();
    
    //Menü
    public static JButton enter = new JButton("Spielen");
	public static JButton info = new JButton("Info");
	public static JButton schließen = new JButton("Verlassen");
	public static JButton menü = new JButton("Hauptmenü");
	public static JButton neustart = new JButton("Neustart");
	public static JButton nextLevel = new JButton("Nächstes Level");
	public static JButton spiel_zurueck = new JButton("Zurück zum Spiel");
	public static JButton checkpoint = new JButton("Zum Checkpoint");
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
	public static ImageIcon Sieger = new ImageIcon();
	public static ImageIcon Shopguy = new ImageIcon("pics/shopguy.png");	
	public static int spielerx=0,spielery=0;
	public static double dx=0,dy=0;
	
	//Levelauswahl
	public static String auswahl[] = {"Level1","Level2","Level3","Level4"};
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
		setFocusable(true);
		setLocationRelativeTo(null);				//Frame in der Mitte
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addKeyListener(new KeyHandler());			//KeyListener hinzufügen
		requestFocus();								//Fokus setzen
		
		//Button Hauptmenü, nur im "Spielmodus" sichtbar
		menü.setBounds(25,25,175,20);
		menü.setVisible(false);
		
		spiel_zurueck.setBounds(25,25,175,20);
		spiel_zurueck.setVisible(false);
		
		//Button Neustart, nur im "Spielmodus" sichtbar wenn der Spieler kein Leben mehr hat
		neustart.setBounds(220, 25, 100, 20);
		neustart.setVisible(false);
		
		//Button Checkpoint, nur im "Spielmodus" sichtbar wenn der Spieler kein Leben mehr hat
		checkpoint.setBounds(220, 25, 150, 20);
		checkpoint.setVisible(false);
		
		//Button nächstes Level, nur sichtbar wenn ein Level erfolgreich absolviert wurde
		nextLevel.setBounds(340,25,150,20);
		nextLevel.setVisible(false);
		
		//Label Charakterauswahl
		charakter.setBounds(460,100,150,30);
		charakter.setText("Charakterauswahl:");
		charakter.setVisible(true);
		add(charakter);
		
		//Label für das Charakter-Bild
		charakterBild.setBounds(540, 150, 32, 32);
		charakterBild.setVisible(true);
		charakterBild.setIcon(Figur1_unten);
		image = Figur1_unten.getImage();
		add(charakterBild);
		Sieger =Figur1_unten;
		
		CharakterAuswahl = 1;
		
		//Buttons zur Charakterauswahl
		PfeilRechts.setBounds(600, 155, 50, 20);
		PfeilLinks.setBounds(460, 155, 50, 20);
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
		
		//Combobox event
		levelAuswahl.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JComboBox<?> selectedChoice = (JComboBox<?>) e.getSource();
				
				if("Level1".equals(selectedChoice.getSelectedItem())){				//Listenauswahl: Level1
					if(Spielfeld.current_lvl!=1){
						Spielfeld.current_lvl=1;
						spiel_zurueck.setVisible(false);
						remove(spiel_zurueck);
					}
				} else if("Level2".equals(selectedChoice.getSelectedItem())){		//Level2
					if(Spielfeld.current_lvl!=2){
						Spielfeld.current_lvl=2;
						spiel_zurueck.setVisible(false);
						remove(spiel_zurueck);
					}
				} else if("Level3".equals(selectedChoice.getSelectedItem())){		//Level3
					if(Spielfeld.current_lvl!=3){
						Spielfeld.current_lvl=3;
						spiel_zurueck.setVisible(false);
						remove(spiel_zurueck);
					}
				} else if("Level4".equals(selectedChoice.getSelectedItem())){		//Level4
					if(Spielfeld.current_lvl!=4){
						Spielfeld.current_lvl=4;
						spiel_zurueck.setVisible(false);
						remove(spiel_zurueck);
					}
				}
				
			}
		});
		
		//Button PfeilRechts Click
		PfeilRechts.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				charakterBild.setIcon(Figur2_unten);
				image = Figur2_unten.getImage();
				CharakterAuswahl = 2;
				Sieger =Figur2_unten;
			}
		});
		
		//Button PfeilLinks Click
		PfeilLinks.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				charakterBild.setIcon(Figur1_unten);
				image = Figur1_unten.getImage();
				CharakterAuswahl =1;
				Sieger =Figur1_unten;
			}
		});
		
		//Button Enter Click
		enter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//Buttons ausblenden und entfernen
				spiel_zurueck.setVisible(false);
				enter.setVisible(false);
				info.setVisible(false);
				schließen.setVisible(false);
				charakter.setVisible(false);
				PfeilRechts.setVisible(false);
				PfeilLinks.setVisible(false);
				charakterBild.setVisible(false);
				remove(spiel_zurueck);
				remove(PfeilRechts);
				remove(PfeilLinks);
				remove(charakterBild);
				remove(enter);
				remove(info);
				remove(schließen);
				remove(levelAuswahl);
				remove(charakter);
			
				Spielfeld.current_room=1;
				Spielfeld.isFirst = true;
				//Spielfeld anzeigen	
				spielfeld.define();	
				add(spielfeld);						
				//Spielerinfo anzeigen
				spielerinfo.setVisible(true);
				add(spielerinfo);
				//Button Hauptmenü hinzufügen
				add(menü);
				add(neustart);
				add(nextLevel);
				add(checkpoint);
				menü.setVisible(true);
				Spielfeld.spieler.checkpoint = new Point(Raum.Startpunkt[Spielfeld.current_lvl-1].x,Raum.Startpunkt[Spielfeld.current_lvl-1].y);
				Spielfeld.spieler.check_room = 1;
				Spielfeld.GegnerRL_counter = 0;
				Spielfeld.GegnerOU_counter = 0;
				Spielfeld.Endgegner_counter = 0;
				
				//Spieler auf den Startpunkt des jeweiligen Levels setzen
				Spielfeld.spieler.aktiv = true;
				Spielfeld.spieler.rechts = true;
				Spielfeld.spieler.x = Raum.Startpunkt[Spielfeld.current_lvl-1].getX();
				Spielfeld.spieler.y = Raum.Startpunkt[Spielfeld.current_lvl-1].getY();
				Spielfeld.spieler.beweglich = true;
				Spielfeld.spieler.leben = 100;
				Spielfeld.spieler.mana = 100;
				Spielfeld.shop = false;
				spielfeld.setVisible(true);
			}
		});
		
		//Button Neustart Click
		neustart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(Spielfeld.spieler.superleben >= 1){
					Spielfeld.current_room = Spielfeld.spieler.check_room;
					spielfeld.define();
					Spielfeld.spieler.x = Spielfeld.spieler.checkpoint.getX();
					Spielfeld.spieler.y = Spielfeld.spieler.checkpoint.getY();
					neustart.setVisible(false);
					checkpoint.setVisible(false);
					Spielfeld.shop = false;
					Spielfeld.spieler.leben = 100;
					Spielfeld.spieler.mana = 100;
					Spielfeld.spieler.aktiv = true;
				} else if(Spielfeld.spieler.superleben <= 0){
					//aktuelles Level in Raum 1 neu laden
					Spielfeld.current_room = 1;
					spielfeld.define();
					//Spieler auf den Startpunkt des jeweiligen Levels setzen
					Spielfeld.spieler.x = Raum.Startpunkt[Spielfeld.current_lvl-1].getX();
					Spielfeld.spieler.y = Raum.Startpunkt[Spielfeld.current_lvl-1].getY();
					Spielfeld.spieler.checkpoint = new Point(Raum.Startpunkt[Spielfeld.current_lvl-1].x,Raum.Startpunkt[Spielfeld.current_lvl-1].y);
					Spielfeld.spieler.check_room = Spielfeld.current_room;
					Spielfeld.spieler.aktiv = true;
					Spielfeld.spieler.beweglich = true;
					Spielfeld.spieler.leben = 100;
					Spielfeld.spieler.mana = 100;
					Spielfeld.spieler.superleben = 3;
					neustart.setVisible(false);
					nextLevel.setVisible(false);
					checkpoint.setVisible(false);
					Spielfeld.shop = false;
				}
			}
		});
		
		//Button Checkpoint Click
		checkpoint.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Spielfeld.current_room = Spielfeld.spieler.check_room;
				spielfeld.define();
				Spielfeld.spieler.x = Spielfeld.spieler.checkpoint.getX();
				Spielfeld.spieler.y = Spielfeld.spieler.checkpoint.getY();
				neustart.setVisible(false);
				checkpoint.setVisible(false);
				Spielfeld.shop = false;
				Spielfeld.spieler.leben = 100;
				Spielfeld.spieler.mana = 100;
				Spielfeld.spieler.aktiv = true;
			}
		});
		
		//bei Erreichen des Ziels nächstes Level
		nextLevel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Spielfeld.current_lvl += 1;
				Spielfeld.current_room = 1; 
				Spielfeld.spieler.checkpoint = new Point(Raum.Startpunkt[Spielfeld.current_lvl-1].x,Raum.Startpunkt[Spielfeld.current_lvl-1].y);
				Spielfeld.spieler.check_room = 1;
				Spielfeld.loadImages();
				spielfeld.define();
				Spielfeld.spieler.x = Raum.Startpunkt[Spielfeld.current_lvl-1].getX();
				Spielfeld.spieler.y = Raum.Startpunkt[Spielfeld.current_lvl-1].getY();
				Spielfeld.spieler.beweglich = true;
				nextLevel.setVisible(false);
				neustart.setVisible(false);
			}
		});
		
		// Button Info Click
		info.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			JOptionPane.showMessageDialog(null, "Probier's doch erstmal ohne Infos :)","", JOptionPane.INFORMATION_MESSAGE,Shopguy);
			}
			});
		
		
		//Button Hauptmenü Click
		menü.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				menü.setVisible(false);
				remove(menü);
				neustart.setVisible(false);
				nextLevel.setVisible(false);
				checkpoint.setVisible(false);
				spielfeld.setVisible(false);
				spielerinfo.setVisible(false);
				setLayout(null);
				spiel_zurueck.setVisible(true);
				add(spiel_zurueck);
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
				Spielfeld.spieler.aktiv = false;
				levelAuswahl.setSelectedItem("Level"+Spielfeld.current_lvl);
				Spielfeld.shop = false;
			}
		});
		
		//Button schließen Click
		schließen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		//aus dem Hauptmenü zurück ins derzeitige Spiel
		spiel_zurueck.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				spiel_zurueck.setVisible(false);
				spielfeld.setVisible(true);
				spielerinfo.setVisible(true);
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
				Spielfeld.spieler.beweglich = true;
				Spielfeld.spieler.aktiv = true;
				menü.setVisible(true);
				add(menü);
			}
		});
		validate();
		repaint();
		setVisible(true);
	}//Frame ende

	public static void main(String[] args) {
		Frame frame = new Frame();
	}
	
	}



