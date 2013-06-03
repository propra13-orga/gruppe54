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
	public static Dimension size = new Dimension(1100,600); 
	
	public static Frame frame;
	
    public static Spielfeld spielfeld = new Spielfeld();
    public static Spielerinfo spielerinfo = new Spielerinfo();
    
    //Menü
    public static JButton enter = new JButton("Spielen");
	public static JButton info = new JButton("Info");
	public static JButton schließen = new JButton("Verlassen");
	public static JButton menü = new JButton("Hauptmenü");
	public static JButton neustart = new JButton("Neustart");
	public static JButton nextLevel = new JButton("Nächstes Level");
	public static JButton shop_zurueck = new JButton("Zurück zum Spiel");
	public static JButton spiel_zurueck = new JButton("Zurück zum Spiel");
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
	//Schussauswahl
	public static JLabel Schuss = new JLabel();
	public static JLabel SchussBild = new JLabel();
	public static JButton PfeilRechts2 = new JButton();
	public static JButton PfeilLinks2 = new JButton();
	public static ImageIcon Schuss1 = new ImageIcon("pics/schuss1.png");
	public static ImageIcon Schuss2 = new ImageIcon("pics/schuss2.png");
	public static ImageIcon Schuss3 = new ImageIcon("pics/schuss3.png");
	public static ImageIcon Schuss4 = new ImageIcon("pics/schuss4.png");
	public static int SchussAuswahl;
	public static Image image2;
	
	//Lebensanzeige
	public static ImageIcon lebensanzeige = new ImageIcon("pics/lebensanzeige.png");
	
	public static int spielerx=0,spielery=0;
	public static double dx=0,dy=0;
	
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
		setFocusable(true);
		setLocationRelativeTo(null); //Frame in der Mitte
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Button Hauptmenü, nur im "Spielmodus" sichtbar
		menü.setBounds(25,25,175,20);
		menü.setVisible(false);
		
		shop_zurueck.setBounds(25,25,175,20);
		shop_zurueck.setVisible(false);
		
		spiel_zurueck.setBounds(25,25,175,20);
		spiel_zurueck.setVisible(false);
		
		//Button Neustart, nur im "Spielmodus" sichtbar wenn der Spieler kein Leben mehr hat
		neustart.setBounds(220, 25, 100, 20);
		neustart.setVisible(false);
		
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
		
		//Label Schussauswahl
		Schuss.setBounds(460,200,150,30);
		Schuss.setText("Fernwaffenauswahl:");
		Schuss.setVisible(true);
		add(Schuss);
		
		//Label für das Schuss-Bild
		SchussBild.setBounds(540, 250, 32, 32);
		SchussBild.setVisible(true);
		SchussBild.setIcon(Schuss1);
		image2 = Schuss1.getImage();
		add(SchussBild);
		
		SchussAuswahl = 1;
		
		//Buttons zur Schussauswahl
		PfeilRechts2.setBounds(600, 255, 50, 20);
		PfeilLinks2.setBounds(460, 255, 50, 20);
		PfeilRechts2.setVisible(true);
		PfeilLinks2.setVisible(true);
		PfeilRechts2.setText(">");
		PfeilLinks2.setText("<");
		add(PfeilLinks2);
		add(PfeilRechts2);
		
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
			public void keyPressed(KeyEvent e){
		        int key = e.getKeyCode();
		      //nur bewegen wenn der Spieler aktiv ist
		      if((spieler.aktiv)&&(Spielfeld.spieler.beweglich)){
		    	  //prüfen welche ID die stelle an die gegangen werden soll hat und nur laufen wenn es keine Mauer ist
		         if ((key == KeyEvent.VK_A)&&(Spielfeld.getBlockID(Spielfeld.spieler.x+1.5, Spielfeld.spieler.y+26)!=1)&&(Spielfeld.getBlockID(Spielfeld.spieler.x+2, Spielfeld.spieler.y+32)!=1)) {
		        	dx = -1*Spielfeld.spieler.speed;
		            
		            if((Spielfeld.shop)&&(Spielfeld.check(15))){
			        	Spielfeld.shop = false;
			        }
		            if((Spielfeld.shop_trank)&&(Spielfeld.check(16))){
			        	Spielfeld.shop_trank = false;
			        }
			        if((Spielfeld.shop_mana)&&(Spielfeld.check(17))){
			        	Spielfeld.shop_mana = false;
			        }
			        if((Spielfeld.shop_ruestung1)&&(Spielfeld.check(18))){
			        	Spielfeld.shop_ruestung1 = false;
			        }
			        if((Spielfeld.shop_ruestung2)&&(Spielfeld.check(19))){
			        	Spielfeld.shop_ruestung2 = false;
			        }
			        
		            
		            Spielfeld.spieler.links = true;
		            Spielfeld.spieler.rechts = false;
		            Spielfeld.spieler.hoch = false;
		            Spielfeld.spieler.runter = false;
		            Spielerinfo.preis_mana = false;
		            Spielerinfo.preis_trank = false;
		            Spielerinfo.preis_ruestung1 = false;
		            Spielerinfo.preis_ruestung2 = false;
		            Spielerinfo.preis_stiefel = false;
		            Spielerinfo.ruestung_voll = false;
		            Spielerinfo.speed_voll = false;
		            Spielerinfo.gold = false;
		            
		            if (CharakterAuswahl==2){
		            	image = Figur2_links.getImage();
		            } else if (CharakterAuswahl ==1){
		            	image = Figur1_links.getImage();
		            }
		         }

		         if ((key == KeyEvent.VK_D)&&(Spielfeld.getBlockID(Spielfeld.spieler.x+30, Spielfeld.spieler.y+26)!=1)&&(Spielfeld.getBlockID(Spielfeld.spieler.x+30, Spielfeld.spieler.y+32)!=1)) {
			        dx = 1*Spielfeld.spieler.speed;
			        
			        if((Spielfeld.shop)&&(Spielfeld.check(15))){
			        	Spielfeld.shop = false;
			        }
			        if((Spielfeld.shop_trank)&&(Spielfeld.check(16))){
			        	Spielfeld.shop_trank = false;
			        }
			        if((Spielfeld.shop_mana)&&(Spielfeld.check(17))){
			        	Spielfeld.shop_mana = false;
			        }
			        if((Spielfeld.shop_ruestung1)&&(Spielfeld.check(18))){
			        	Spielfeld.shop_ruestung1 = false;
			        }
			        if((Spielfeld.shop_ruestung2)&&(Spielfeld.check(19))){
			        	Spielfeld.shop_ruestung2 = false;
			        }
			        
			        
			        Spielfeld.spieler.links = false;
		         	Spielfeld.spieler.rechts = true;
		         	Spielfeld.spieler.hoch = false;
		         	Spielfeld.spieler.runter = false;
		            Spielerinfo.preis_mana = false;
		            Spielerinfo.preis_trank = false;
		            Spielerinfo.preis_ruestung1 = false;
		            Spielerinfo.preis_ruestung2 = false;
		            Spielerinfo.preis_stiefel = false;
		            Spielerinfo.ruestung_voll = false;
		            Spielerinfo.speed_voll = false;
		            Spielerinfo.gold = false;
		            
		            if (CharakterAuswahl==2){
		            	image = Figur2_rechts.getImage();
		            } else if (CharakterAuswahl ==1){
		            	image = Figur1_rechts.getImage();
		            }
		         }

		         if ((key == KeyEvent.VK_W)&&(Spielfeld.getBlockID(Spielfeld.spieler.x+6, Spielfeld.spieler.y+26)!=1)&&(Spielfeld.getBlockID(Spielfeld.spieler.x+26, Spielfeld.spieler.y+23)!=1)) {
			        dy = -1*Spielfeld.spieler.speed;

			        if((Spielfeld.shop)&&(Spielfeld.check(15))){
			        	Spielfeld.shop = false;
			        }
			        if((Spielfeld.shop_trank)&&(Spielfeld.check(16))){
			        	Spielfeld.shop_trank = false;
			        }
			        if((Spielfeld.shop_mana)&&(Spielfeld.check(17))){
			        	Spielfeld.shop_mana = false;
			        }
			        if((Spielfeld.shop_ruestung1)&&(Spielfeld.check(18))){
			        	Spielfeld.shop_ruestung1 = false;
			        }
			        if((Spielfeld.shop_ruestung2)&&(Spielfeld.check(19))){
			        	Spielfeld.shop_ruestung2 = false;
			        }
			        
			        
			        Spielfeld.spieler.links = false;
			        Spielfeld.spieler.rechts = false;
			        Spielfeld.spieler.hoch = true;
			        Spielfeld.spieler.runter = false;
		            Spielerinfo.preis_mana = false;
		            Spielerinfo.preis_trank = false;
		            Spielerinfo.preis_ruestung1 = false;
		            Spielerinfo.preis_ruestung2 = false;
		            Spielerinfo.preis_stiefel = false;
		            Spielerinfo.ruestung_voll = false;
		            Spielerinfo.speed_voll = false;
		            Spielerinfo.gold = false;
		            
		            if (CharakterAuswahl==2){
		            	image = Figur2_oben.getImage();
		            } else if (CharakterAuswahl ==1){
		            	image = Figur1_oben.getImage();
		            }
		         }
		         
		         if ((key == KeyEvent.VK_S)&&(Spielfeld.getBlockID(Spielfeld.spieler.x+6, Spielfeld.spieler.y+32)!=1)&&(Spielfeld.getBlockID(Spielfeld.spieler.x+26, Spielfeld.spieler.y+32)!=1)) {
			        dy = 1*Spielfeld.spieler.speed;
			        			        
			        if((Spielfeld.shop)&&(Spielfeld.check(15))){
			        	Spielfeld.shop = false;
			        }
			        if((Spielfeld.shop_trank)&&(Spielfeld.check(16))){
			        	Spielfeld.shop_trank = false;
			        }
			        if((Spielfeld.shop_mana)&&(Spielfeld.check(17))){
			        	Spielfeld.shop_mana = false;
			        }
			        if((Spielfeld.shop_ruestung1)&&(Spielfeld.check(18))){
			        	Spielfeld.shop_ruestung1 = false;
			        }
			        if((Spielfeld.shop_ruestung2)&&(Spielfeld.check(19))){
			        	Spielfeld.shop_ruestung2 = false;
			        }
			        
			        Spielfeld.spieler.links = false;
			        Spielfeld.spieler.rechts = false;
			        Spielfeld.spieler.hoch = false;
			        Spielfeld.spieler.runter = true;
			        
		            Spielerinfo.preis_mana = false;
		            Spielerinfo.preis_trank = false;
		            Spielerinfo.preis_ruestung1 = false;
		            Spielerinfo.preis_ruestung2 = false;
		            Spielerinfo.preis_stiefel = false;
		            Spielerinfo.ruestung_voll = false;
		            Spielerinfo.speed_voll = false;
		            Spielerinfo.gold = false;
			        
			        if (CharakterAuswahl==2){
		            	image = Figur2_unten.getImage();
		            } else if (CharakterAuswahl ==1){
		            	image = Figur1_unten.getImage();
		            }
		         }
		         
		         if ((key == KeyEvent.VK_ENTER)){			//ruft den Shop auf 
		        	 if(Spielfeld.shop){
		        		 menü.setVisible(false);
		        		 remove(menü);
		        		 Spielerinfo.anzeige = false;
		        		 Spielfeld.spieler_preposX = Spielfeld.spieler.x;
		        		 Spielfeld.spieler_preposY = Spielfeld.spieler.y;
		        		 Spielfeld.showShop();
		        		 add(shop_zurueck);
		        		 shop_zurueck.setVisible(true);
		        	 } else if(Spielfeld.shop_trank){
		        		 if(Spielfeld.spieler.gold >= 50){
		    				 Spielfeld.spieler.gold -= 50;
		    				 Spielfeld.spieler.item_trank += 1;
		    			 } else if(Spielfeld.spieler.gold-50 <= 0){
		    				 Spielerinfo.gold = true;
		        			 Spielerinfo.preis_trank = false;
		    			 }
		        	 } else if(Spielfeld.shop_mana){
		        		 if(Spielfeld.spieler.gold >= 75){
		    				 Spielfeld.spieler.gold -= 75;
		    				 Spielfeld.spieler.item_mana += 1;
		    			 } else if(Spielfeld.spieler.gold-75 <= 0){
		    				 Spielerinfo.gold = true;
		        			 Spielerinfo.preis_mana = false;
		    			 }
		        	 } else if(Spielfeld.shop_ruestung1){
		        		 if(spieler.ruestung == 100){
		        			 Spielerinfo.ruestung_voll = true;
		        			 Spielerinfo.preis_ruestung1 = false;
		        		 } else if(Spielfeld.spieler.gold >= 150) {
		        			 spieler.ruestung = 100;
		        			 Spielfeld.spieler.gold -= 150;
		        		 } else if(Spielfeld.spieler.gold-150 <= 0){
		        			 Spielerinfo.gold = true;
		        			 Spielerinfo.preis_ruestung1 = false;
		        		 }
		        	 } else if(Spielfeld.shop_ruestung2){
		        		 if(spieler.ruestung == 100){
		        			 Spielerinfo.ruestung_voll = true;
		        			 Spielerinfo.preis_ruestung2 = false;
		        		 } else if(Spielfeld.spieler.gold >= 100){
		        			 spieler.ruestung += 50;
		        			 Spielfeld.spieler.gold -= 100;
		        			 if(spieler.ruestung >= 100){
		        				 spieler.ruestung = 100;
		        			 }
		        		 } else if(Spielfeld.spieler.gold-100 <= 0) {
		        			 Spielerinfo.gold = true;
		        			 Spielerinfo.preis_ruestung2 = false;
		        		 }
		        	 } else if(Spielfeld.shop_stiefel){
		        		 if(Spielfeld.spieler.speed == 0.55){
		        			 Spielerinfo.speed_voll = true;
		        			 Spielerinfo.preis_stiefel = false;
		        		 } else if(Spielfeld.spieler.gold >= 150){
		        			 Spielfeld.spieler.speed = 0.55;
		        			 Spielfeld.spieler.gold -= 150;
		        		 } else if(Spielfeld.spieler.gold-150 <= 0) {
		        			 Spielerinfo.gold = true;
		        			 Spielerinfo.preis_stiefel = false;
		        		 }
		        	 }
		         }
		         
		         if ((key == KeyEvent.VK_1)&&(spieler.aktiv)&&(spieler.leben<100)&&(Spielfeld.spieler.item_trank>0)){   //Trank
		        	 spieler.leben += 40;
		        	 Spielfeld.spieler.item_trank -= 1;
		        	 if(spieler.leben>100){
		        		 spieler.leben = 100;
		        	 }
		         }
		         
		         if ((key == KeyEvent.VK_2)&&(spieler.aktiv)&&(spieler.mana<100)&&(Spielfeld.spieler.item_mana>0)){		//Mana
		        	 spieler.mana += 40;
		        	 Spielfeld.spieler.item_mana -= 1;
		        	 if(spieler.mana>100){
		        		 spieler.mana = 100;
		        	 }
		         }
		         //Schuss des Spielers
		         if ((key == KeyEvent.VK_SPACE)&&(spieler.aktiv)&&(spieler.mana>=20)){	
		        	Schuss_Spieler.sichtbar=true;
		        	Schuss_Spieler.checkPos=false;
					Schuss_Spieler.checkRichtung();
					spieler.mana -= 20;
					if(spieler.mana > 100){
						spieler.mana = 100;
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
				int key = e.getKeyCode();

		        if (key == KeyEvent.VK_A) {
		            dx = 0;
		        }

		        if (key == KeyEvent.VK_D) {
		            dx = 0;
		        }

		        if (key == KeyEvent.VK_W) {
		            dy = 0;
		        }

		        if (key == KeyEvent.VK_S) {
		            dy = 0;
		        }
			}
		});

		
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
		//Button PfeilRechts2 Click
		PfeilRechts2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(SchussAuswahl==1){
				SchussBild.setIcon(Schuss2);
				image2 = Schuss2.getImage();
				SchussAuswahl = 2;
				} else
					if(SchussAuswahl==2){
						SchussBild.setIcon(Schuss3);
						image2 = Schuss3.getImage();
						SchussAuswahl = 3;
					} else
						if(SchussAuswahl==3){
							SchussBild.setIcon(Schuss4);
							image2 = Schuss4.getImage();
							SchussAuswahl = 4;
						}
			}
		});
		
		//Button PfeilLinks2 Click
		PfeilLinks2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(SchussAuswahl==4){
				SchussBild.setIcon(Schuss3);
				image2 = Schuss3.getImage();
				SchussAuswahl =3;
				} else
					if(SchussAuswahl==3){
					SchussBild.setIcon(Schuss2);
					image2 = Schuss2.getImage();
					SchussAuswahl =2;
					} else
						if(SchussAuswahl==2){
							SchussBild.setIcon(Schuss1);
							image2 = Schuss1.getImage();
							SchussAuswahl =1;
							} 
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
				Schuss.setVisible(false);
				PfeilRechts2.setVisible(false);
				PfeilLinks2.setVisible(false);
				SchussBild.setVisible(false);
				remove(PfeilRechts2);
				remove(PfeilLinks2);
				remove(SchussBild);
				remove(Schuss);
				

				Spielfeld.current_room=1;
				Spielfeld.isFirst = true;
				Spielfeld.loadImages();		//Bilder des Levels laden
				//Spielfeld anzeigen	
				spielfeld.define();
				spielfeld.setVisible(true);	
				add(spielfeld);						
				//Spielerinfo anzeigen
				spielerinfo.setVisible(true);
				add(spielerinfo);
				//Button Hauptmenü hinzufügen
				add(menü);
				add(neustart);
				add(nextLevel);
				menü.setVisible(true);
				
				//Spieler auf den Startpunkt des jeweiligen Levels setzen
				spieler.aktiv = true;
				Spielfeld.spieler.x = Raum.Startpunkt[Spielfeld.current_lvl-1].getX();
				Spielfeld.spieler.y = Raum.Startpunkt[Spielfeld.current_lvl-1].getY();
				Spielfeld.spieler.beweglich = true;
				spieler.leben = 100;
				spieler.mana = 100;
				Spielfeld.shop = false;
			}
		});
		
		//Button Neustart Click
		neustart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//aktuelles Level in Raum 1 neu laden
				Spielfeld.current_room = 1;
				spielfeld.define();
				//Spieler auf den Startpunkt des jeweiligen Levels setzen
				Spielfeld.spieler.x = Raum.Startpunkt[Spielfeld.current_lvl-1].getX();
				Spielfeld.spieler.y = Raum.Startpunkt[Spielfeld.current_lvl-1].getY();
				spieler.aktiv = true;
				Spielfeld.spieler.beweglich = true;
				spieler.leben = 100;
				spieler.mana = 100;
				neustart.setVisible(false);
				nextLevel.setVisible(false);
				Block.Boss_vorhanden=false;

				Spielfeld.shop = false;

			}
		});
		
		//bei Erreichen des Ziels nächstes Level
		nextLevel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Spielfeld.current_lvl += 1;
				Spielfeld.current_room = 1; 
				spielfeld.define();
				Spielfeld.spieler.x = Raum.Startpunkt[Spielfeld.current_lvl-1].getX();
				Spielfeld.spieler.y = Raum.Startpunkt[Spielfeld.current_lvl-1].getY();
				Spielfeld.spieler.beweglich = true;
				Spielfeld.loadImages();
				Block.Boss_vorhanden=false;
				nextLevel.setVisible(false);
				neustart.setVisible(false);
			}
		});
		
		//Button Hauptmenü Click
		menü.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				menü.setVisible(false);
				remove(menü);
				neustart.setVisible(false);
				nextLevel.setVisible(false);
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
				Schuss.setVisible(true);
				PfeilRechts2.setVisible(true);
				PfeilLinks2.setVisible(true);
				SchussBild.setVisible(true);
				add(PfeilRechts2);
				add(PfeilLinks2);
				add(SchussBild);
				add(Schuss);
				init();
				spieler.aktiv = false;
				
				levelAuswahl.setSelectedItem("Level"+Spielfeld.current_lvl);

				Block.Boss_vorhanden=false;
				Spielfeld.shop = false;

			}
		});
		
		//Button schließen Click
		schließen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		//Shop_Button Zurück zum Spielfeld
		shop_zurueck.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Spielfeld.hideShop();
				shop_zurueck.setVisible(false);
				remove(shop_zurueck);
				add(menü);
				menü.setVisible(true);
				Spielerinfo.anzeige = false;
				Spielerinfo.preis_mana = false;
	            Spielerinfo.preis_trank = false;
	            Spielerinfo.preis_ruestung1 = false;
	            Spielerinfo.preis_ruestung2 = false;
	            Spielerinfo.preis_stiefel = false;
	            Spielerinfo.ruestung_voll = false;
	            Spielerinfo.speed_voll = false;
	            Spielerinfo.gold = false;
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
				remove(shop_zurueck);
				remove(PfeilRechts);
				remove(PfeilLinks);
				remove(charakterBild);
				remove(enter);
				remove(info);
				remove(schließen);
				remove(levelAuswahl);
				remove(charakter);
				Schuss.setVisible(false);
				PfeilRechts2.setVisible(false);
				PfeilLinks2.setVisible(false);
				SchussBild.setVisible(false);
				remove(PfeilRechts2);
				remove(PfeilLinks2);
				remove(SchussBild);
				remove(Schuss);
				Spielfeld.spieler.beweglich = true;
				spieler.aktiv = true;
				menü.setVisible(true);
				add(menü);
				
				if(Spielfeld.current_lvl == 3){
					Block.Boss_vorhanden = true;
				}
				
			}
			
		});
		
		init();
	}//Frame ende
	
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



