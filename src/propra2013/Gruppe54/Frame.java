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
	public static Dimension size = new Dimension(1000,700); 
	
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
	public static ImageIcon Figur2_rechts = new ImageIcon("pics/Figur2_rechts.png");
	public static ImageIcon Figur2_links = new ImageIcon("pics/Figur2_links.png");
	public static ImageIcon Figur2_unten = new ImageIcon("pics/Figur2_unten.png");
	public static ImageIcon Figur2_oben = new ImageIcon("pics/Figur2_oben.png");
	public static ImageIcon Figur1_rechts = new ImageIcon("pics/Figur1_rechts.png");
	public static ImageIcon Figur1_links = new ImageIcon("pics/Figur1_links.png");
	public static ImageIcon Figur1_unten = new ImageIcon("pics/Figur1_unten.png");
	public static ImageIcon Figur1_oben = new ImageIcon("pics/Figur1_oben.png");
	public static Image image = Figur1_rechts.getImage(),image2 = Figur2_rechts.getImage();
	public static ImageIcon Sieger = new ImageIcon();
	public static ImageIcon Shopguy = new ImageIcon("pics/npc_aktiv.png");	
	public static int spielerx=0,spielery=0;
	public static double dx=0,dy=0;
	public static JCheckBox multiplayer,createServer;
	
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
		
		multiplayer = new JCheckBox("Multiplayer");
		multiplayer.setBounds(550,100,150,20);
	    multiplayer.setSelected(false);
	    multiplayer.setVisible(true);
	    add(multiplayer);
	    
	    createServer = new JCheckBox("Server erstellen");
	    createServer.setBounds(550,125,150,20);
	    createServer.setSelected(false);
	    createServer.setVisible(true);
	    add(createServer);
		
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
	
		//Anzeige des Menüs
		enter.setBounds(375, 100, 150, 30);		//Button Enter
		enter.setVisible(true);
		add(enter);
		
		info.setBounds(375,150,150,30);			//Button Info
		info.setVisible(true);
		add(info);
		
		schließen.setBounds(375, 200, 150, 30); //Button Schließen
		schließen.setVisible(true);
		add(schließen);
		
		levelAuswahl.setBounds(375, 250, 150, 30);	//ComboBox Levelauswahl
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
		
		//Button Enter Click
		enter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if((multiplayer.isSelected())&(!createServer.isSelected())){
					Spielfeld.ip = JOptionPane.showInputDialog(null, "Bitte gebe die IP-Adresse des Rechners an der den Server erstellt hat:");
				}
				//Buttons ausblenden und entfernen
				spiel_zurueck.setVisible(false);
				enter.setVisible(false);
				info.setVisible(false);
				schließen.setVisible(false);
				remove(enter);
				remove(info);
				remove(schließen);
				remove(levelAuswahl);
				Spielfeld.current_room=1;
				
				if(multiplayer.isSelected()){
					Spielfeld.multiplayer = true;
				} else if(!multiplayer.isSelected()){
					Spielfeld.multiplayer = false;
				}
				if(createServer.isSelected()){
					Spielfeld.host = true;
				} else if(!createServer.isSelected()){
					Spielfeld.host = false;
				}
				
				//Spielfeld anzeigen	
				Rätsel.reset();
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
					Rätsel.reset();
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
					if(Spielfeld.multiplayer){
						Spielfeld.client.send(Spielfeld.client.socket.getLocalPort()+";erwacht;");
					}
				} else if(Spielfeld.spieler.superleben <= 0){
					//aktuelles Level in Raum 1 neu laden
					Rätsel.reset();
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
					if(Spielfeld.multiplayer){
						Spielfeld.client.send(Spielfeld.client.socket.getLocalPort()+";erwacht;");
					}
				}
			}
		});
		
		//Button Checkpoint Click
		checkpoint.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Rätsel.reset();
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
				if(Spielfeld.multiplayer){
					Spielfeld.client.send(Spielfeld.client.socket.getLocalPort()+";erwacht;");
				}
			}
		});
		
		//bei Erreichen des Ziels nächstes Level
		nextLevel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Rätsel.reset();
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
				add(enter);
				add(info);
				add(schließen);
				add(levelAuswahl);
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
				remove(enter);
				remove(info);
				remove(schließen);
				remove(levelAuswahl);
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



