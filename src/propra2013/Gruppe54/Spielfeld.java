package propra2013.Gruppe54;
import javax.swing.*;

import java.awt.*;
import java.io.File;

public class Spielfeld extends JPanel implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Thread thread = new Thread(this);
	
	public static Image[] elemente = new Image[55];
	
	public static int current_lvl=1,current_room=1,current_player=1,counter_anzeige = 0;
	public static double spieler_preposX=0,spieler_preposY=0;
	
	public static boolean isFirst = true,weg_verschlossen = true;
	public static boolean preis_shop = false,shop = false,shop_trank = false,shop_mana = false,shop_supertrank = false,shop_ruestung1 = false,shop_ruestung2 = false,shop_stiefel = false,shop_axt = false,anzeige = false;
	public static String text_anzeige,preis_anzeige;
	public static Raum raum;
	public static Level level = new Level();
	public static Spieler spieler;
	public static GegnerRL gegnerRL;
	public static GegnerOU gegnerOU;
	public static Endgegner Boss;
	public static int GegnerKI_counter=0, GegnerRL_counter = 0,GegnerOU_counter = 0,Endgegner_counter = 0,Falle_counter = 0;  //damit das Item, dass man nach besiegen eines Gegner erhält, nur einmal abgelegt wird
	public static GegnerKI gegnerKI;
	public static Schuss_Endgegner schuss_endgegner;
	public static Schuss_Spieler schuss_spieler;
	public static int counter_schuss = 0; //wird auf 1 gesetzt wenn der Spieler schießt, damit die Position des Schusses sich während dem Flug nicht 
										  //weiterhin der Position des Spielers anpasst
	public static int counter_angriff = 0;
	public static Pfeil pfeil;
	public static int EndgegnerLeben;
	public static int GegnerRLLeben;
	public static int GegnerOULeben;
	public static int GegnerKILeben;
	public static Falle falle;
	public static Waffe waffe;
	
	/**
	 * Konstruktor
	 */
	public Spielfeld(){
		setBounds(25,55,800,480);
		thread.start();
		spieler = new Spieler();
		spieler.runter=true;
	}
    //Bilder in Array laden
	public static void loadImages(){
		elemente[0] = new ImageIcon("pics/boden"+current_lvl+".png").getImage(); 
		elemente[1] = new ImageIcon("pics/mauer"+current_lvl+".png").getImage();
		elemente[2] = new ImageIcon("pics/fackel.gif").getImage();
		elemente[3] = new ImageIcon("pics/leer.png").getImage();
		elemente[4] = new ImageIcon("pics/mauer"+current_lvl+"_2.png").getImage();
		elemente[5] = new ImageIcon("pics/ausgang.png").getImage();
		//6
		//Fallen
		elemente[7] = new ImageIcon("pics/falle_loch_inaktiv.png").getImage();
		elemente[8] = new ImageIcon("pics/feuer.gif").getImage();
		elemente[9] = new ImageIcon("pics/falle_speer1.png").getImage();
		elemente[10] = new ImageIcon("pics/anim.gif").getImage();
		elemente[11] = new ImageIcon("pics/falle_speer2.png").getImage();
		elemente[12] = new ImageIcon("pics/falle_speer3.png").getImage();
		//Items
		elemente[13] = new ImageIcon("pics/item_trank.png").getImage();
		elemente[14] = new ImageIcon("pics/item_trank2.png").getImage();
		elemente[15] = new ImageIcon("pics/brunnen_rot.png").getImage();
		elemente[16] = new ImageIcon("pics/item_trank3.png").getImage();
		elemente[17] = new ImageIcon("pics/brunnen_blau.png").getImage();
		elemente[18] = new ImageIcon("pics/zepter"+current_lvl+".png").getImage();
		elemente[19] = new ImageIcon("pics/checkpoint.png").getImage();
		elemente[20] = new ImageIcon("pics/npc.png").getImage();
		//Shopelemente
		elemente[21] = new ImageIcon("pics/item_trank.png").getImage();
		elemente[22] = new ImageIcon("pics/item_trank2.png").getImage();
		elemente[23] = new ImageIcon("pics/item_ruestung1.png").getImage();
		elemente[24] = new ImageIcon("pics/item_ruestung2.png").getImage();
		elemente[25] = new ImageIcon("pics/item_stiefel.png").getImage();
		elemente[26] = new ImageIcon("pics/eingang_shop.png").getImage();
		elemente[27] = new ImageIcon("pics/ausgang_shop.png").getImage();
		elemente[28] = new ImageIcon("pics/item_axt.png").getImage();
		elemente[29] = new ImageIcon("pics/item_trank3.png").getImage();
		//30
		//Schatzelemente
		elemente[31] = new ImageIcon("pics/truhe_zu.png").getImage();
		elemente[32] = new ImageIcon("pics/gold.png").getImage();
		elemente[33] = new ImageIcon("pics/herz_element.png").getImage();
		//34
		//35,36,37,38,39 für bewegliche Fallen bzw Gegner
		//40 Ausgang Wald
		elemente[41] = new ImageIcon("pics/baum1.png").getImage();
		elemente[42] = new ImageIcon("pics/baum2.png").getImage();
		elemente[43] = new ImageIcon("pics/wasser.gif").getImage();
		//elemente[44] = new ImageIcon("pics/herz_element.png").getImage();
		elemente[45] = new ImageIcon("pics/ufer_oben.png").getImage();
		elemente[46] = new ImageIcon("pics/ufer_links.png").getImage();
		elemente[47] = new ImageIcon("pics/ufer_rechts.png").getImage();
		elemente[48] = new ImageIcon("pics/ufer_unten.png").getImage();
	}
	
	public void define(){
		raum = new Raum();
		gegnerRL = new GegnerRL();
		gegnerOU = new GegnerOU();
		falle = new Falle();
		Boss = new Endgegner();
		gegnerKI = new GegnerKI();
		waffe = new Waffe();
		loadImages();
		level.loadLevel(new File("level/level"+current_lvl+"_"+current_room+".lvl"));   //level-datei laden
		schuss_endgegner = new Schuss_Endgegner();
		schuss_spieler = new Schuss_Spieler();
		//pfeil = new Pfeil();
		GegnerOU.leben=GegnerOU.StartLeben;
		GegnerRL.leben=GegnerRL.StartLeben;
		Endgegner.leben=Endgegner.StartLeben;
		GegnerKI.leben=GegnerKI.StartLeben;
		Spielfeld.GegnerRL_counter = 0;	//counter ist 1 wenn der jeweilige Gegner besiegt wurde,
		Spielfeld.GegnerOU_counter = 0;	//damit nur einmal ein Item als Belohnung abgelegt wird
		Spielfeld.Endgegner_counter = 0;
		Spielfeld.GegnerKI_counter = 0;
		Waffe.ID = spieler.waffe;
	}
	
	//Hier nur zeichnen, berechnungen oder sonstiges im Thread ausführen
	public void paintComponent(Graphics g){
		raum.draw(g); //zeichnet den raum
		if(spieler.aktiv){
			spieler.draw(g);  //zeichnet den Spieler
			//Waffe des Spielers
			if((spieler.schwert)){
				Waffe.ID = spieler.waffe;
				Waffe.draw(g);
			}
			if((spieler.waffe == 3)&&(Waffe.angriff)){
				Waffe.draw(g);
			}
			//Anzeige von Schatzelementen
			if((anzeige)&&(counter_anzeige<=300)){
				g.setColor(Color.white);
				if(g.getFont() != new Font("Lucida Sans Typewriter",Font.PLAIN,8)){
					g.setFont(new Font("Lucida Sans Typewriter",Font.PLAIN,8));
				}
				g.drawString(text_anzeige, (int)spieler.x, (int)spieler.y-10);
				counter_anzeige++;
			}
			if(preis_shop){
				g.setColor(Color.white);
				if(g.getFont() != new Font("Lucida Sans Typewriter",Font.PLAIN,8)){
					g.setFont(new Font("Lucida Sans Typewriter",Font.PLAIN,8));
				}
				g.drawString(preis_anzeige, (int)spieler.x, (int)spieler.y-10);
			}
			//Schuss Spieler
			if (schuss_spieler.sichtbar){
				schuss_spieler.draw(g);
			}
		}
		//Gegner1
		if((GegnerRL.leben>0)&&(GegnerRL.aktiv)){
			gegnerRL.draw(g);
			//Lebensanzeige GegnerRL
			if(GegnerRL.leben>GegnerRL.StartLeben/4){
				g.setColor(Color.green);
			} else {
				g.setColor(Color.red);
			}
			g.fill3DRect((int)GegnerRL.StartX, (int)GegnerRL.StartY-10,GegnerRL.leben/GegnerRL.Faktor,3,true);
		}
		//Gegner2
		if((GegnerOU.leben>0)&&(GegnerOU.aktiv)){
			gegnerOU.draw(g);
			//Lebensanzeige GegnerOU
			if(GegnerOU.leben>GegnerOU.StartLeben/4){
				g.setColor(Color.green);
			} else {
				g.setColor(Color.red);
			}
			g.fill3DRect((int)GegnerOU.StartX, (int)GegnerOU.StartY-10,GegnerOU.leben/GegnerOU.Faktor,3,true);
		}
		
		//Falle
		if((Falle.aktiv)&&(Spielfeld.shop == false)){
			falle.draw(g);
		}
		
		//Endgegner
		if ((Endgegner.leben>0)&&(Endgegner.aktiv)&&(current_room == 3)){
			Boss.draw(g);
			//Lebensanzeige Endgegner
			if(Endgegner.leben>Endgegner.StartLeben/4){
				g.setColor(Color.green);
			} else {
				g.setColor(Color.red);
			}
			g.fill3DRect((int)Endgegner.StartX, (int)Endgegner.StartY-10,Endgegner.leben/Endgegner.Faktor,3,true);
			//SchussEndgegner
			if ((Schuss_Endgegner.sichtbar==true)&&(Schuss_Endgegner.aktiv)){
				schuss_endgegner.draw(g);
			}
		}
		//GegnerKI
		if((GegnerKI.leben>0)&&(GegnerKI.aktiv)){
			gegnerKI.draw(g);
			//Lebensanzeige GegnerKI
			if(GegnerKI.leben>GegnerKI.StartLeben/4){
				g.setColor(Color.green);
			} else {
				g.setColor(Color.red);
			}
			g.fill3DRect(GegnerKI.StartX, GegnerKI.StartY-10,GegnerKI.leben/GegnerKI.Faktor,3,true);	
		}
	}
	
	//Thread
	public void run(){
		while(true){
			validate();
			repaint();
			if(isFirst){ //Erstinitialisierung
				define();
				isFirst=false;
			}
			//Wenn der Spieler besiegt wurde
			if((spieler.leben <= 0)&&(spieler.superleben >= 1)&&(spieler.aktiv)){
				spieler.aktiv = false;
				anzeige = false;
				Waffe.angriff = false;
				//Schadensberechnung anhand der XP des Spielers
				if(spieler.xp >= 25){
					spieler.schaden = 10;
				} else if(spieler.xp >= 75){
					spieler.schaden = 12;
				} else if(spieler.xp >= 125){
					spieler.schaden = 15;
				} else if(spieler.xp >= 175){
					spieler.schaden = 17;
				} else if(spieler.xp >= 225){
					spieler.schaden = 20;
				} else if(spieler.xp >= 300){
					spieler.schaden = 25;
				} else if(spieler.xp >= 450){
					spieler.schaden = 35;
				}
				if(spieler.checkpoint.getX() != Raum.Startpunkt[Spielfeld.current_lvl-1].getX()){
					Frame.checkpoint.setVisible(true);
				} else if(spieler.checkpoint.getX() == Raum.Startpunkt[Spielfeld.current_lvl-1].getX()){
					Frame.neustart.setVisible(true);	
				}
				spieler.superleben -= 1;
			} else if((spieler.leben <= 0)&&(spieler.superleben <= 0)&&(spieler.aktiv)){
				spieler.aktiv = false;
				spieler.xp -= 100;
				if(spieler.xp <= 0){
					spieler.xp = 0;
				}
				anzeige = false;
				Waffe.angriff = false;
				Frame.neustart.setVisible(true);
			}
			//Waffe des Spielers
			if((Waffe.angriff)&&(Waffe.ID != 3)){
				if(counter_angriff == 0){
					Waffe.Kollision();
				}
				counter_angriff++;
				if(counter_angriff == 100){
					Waffe.angriff = false;
				}
			}
			//GegnerKI 
			if ((GegnerKI.leben>0)&&(GegnerKI.aktiv)&&(GegnerKI.StartX !=0)&&(GegnerKI.StartY !=0)){
				GegnerKI.lauf();
				GegnerKI.counter_gegnerKI ++;
				GegnerKI.laufen=false;
				if(GegnerKI.counter_gegnerKI==3){
					GegnerKI.laufen=true;
					GegnerKI.counter_gegnerKI=0;
				}
			} else if((GegnerKI.leben <= 0)&&(GegnerKI_counter == 0)&&(shop == false)){ // "   "
				getBlock(GegnerKI.StartX+16,GegnerKI.StartY+16).ID = 13;
				spieler.xp += 10*current_lvl;
				anzeige = true;
				text_anzeige = "+"+10*current_lvl+" XP";
				GegnerKI_counter = 1;
				GegnerKI.StartX = 0;
				GegnerKI.StartY = 0;
			}
			//GegnerRL 
			if ((GegnerRL.aktiv)&&(GegnerRL.leben>0)&&(GegnerRL.StartX !=0)&&(GegnerRL.StartY !=0)){
				GegnerRL.lauf();
			} else if((GegnerRL.leben <= 0)&&(GegnerRL_counter == 0)&&(shop == false)){	//wenn der Gegner besiegt wurde müssen seine Koordinaten auf 0 gesetzt werden
				getBlock(GegnerRL.StartX+16,GegnerRL.StartY+16).ID = 32;				//der counter ist dafür, dass beim besiegen des Gegners nur einmal ein Schatz liegt
				spieler.xp += 15*current_lvl;
				anzeige = true;
				text_anzeige = "+"+15*current_lvl+" XP";
				GegnerRL_counter = 1;
				GegnerRL.StartX = 0;
				GegnerRL.StartY = 0;
			}
			//GegnerOU 
			if ((GegnerOU.aktiv)&&(GegnerOU.leben>0)&&(GegnerOU.StartX !=0)&&(GegnerOU.StartY !=0)){
				GegnerOU.lauf();
			} else if((GegnerOU.leben <= 0)&&(GegnerOU_counter == 0)&&(shop == false)){ // "   " 
				getBlock(GegnerOU.StartX+16,GegnerOU.StartY+16).ID = 14;
				spieler.xp += 15*current_lvl;
				anzeige = true;
				text_anzeige = "+"+15*current_lvl+" XP";
				GegnerOU_counter = 1;
				GegnerOU.StartX = 0;
				GegnerOU.StartY = 0;
				weg_verschlossen = false;
			}
			//Endgegner 
			if ((Endgegner.leben>0)&&(Endgegner.aktiv)&&(Endgegner.StartX !=0)&&(Endgegner.StartY !=0)){
				Endgegner.lauf();
				//Schuss vom Endgegner
				if ((current_room==3)&&(Schuss_Endgegner.sichtbar==true)&&(Endgegner.leben>0)&&(Schuss_Endgegner.aktiv)){
					if (Schuss_Endgegner.checkPos==false){
						Schuss_Endgegner.checkPos();
					}
					Schuss_Endgegner.bewegung();
				}
			} else if((Endgegner.leben <= 0)&&(Endgegner_counter == 0)&&(shop == false)){ // "   "
				getBlock(Endgegner.StartX+16,Endgegner.StartY+16).ID = 13;
				spieler.xp += 25*current_lvl;
				anzeige = true;
				text_anzeige = "+"+25*current_lvl+" XP";
				Endgegner_counter = 1;
				Endgegner.StartX = 0;
				Endgegner.StartY = 0;
			}
			//Falle bewegung
			if((Falle.aktiv)&&(Falle.StartX != 0)&&(Falle.StartY != 0)){
				Falle.bewegung();
			}
			//Counter für den String über dem Spieler zur Ausgabe eingesammelter Werte
			if(counter_anzeige==300){  
				anzeige = false;
				counter_anzeige = 0;
			}
			//Spieler
			if(spieler.aktiv){
			//Steuerung des Spielers
			if((spieler.check(1))&&(spieler.check(15))&&(spieler.check(18))&&(spieler.check(20))&&(spieler.check(21))&&(spieler.check(10))
					&&(spieler.check(22))&&(spieler.check(23))&&(spieler.check(24))&&(spieler.check(25))&&(spieler.check(28))&&(spieler.check(31))
					&&(spieler.check(41))&&(spieler.check(42))&&(spieler.check(43))&&(spieler.check(2))&&(spieler.check(4))&&(spieler.check(29))
					&&(spieler.check(17))&&(spieler.check(51))){//prüfen ob Elemente vom Spieler durchschritten werden dürfen
				spieler.checkKollision();
				spieler.x += Frame.dx;
				spieler.y += Frame.dy;
				Elemente.beruehrung = false;
			} else if((spieler.check(15)==false) | (spieler.check(18)==false) | (spieler.check(20)==false) | (spieler.check(21)==false) | (spieler.check(22)==false)
					| (spieler.check(23)==false) | (spieler.check(24)==false) | (spieler.check(25)==false) | (spieler.check(28)==false) | (spieler.check(31)==false)
					| (spieler.check(29)==false) | (spieler.check(17)==false) | (spieler.check(10)==false)){	//wenn nicht, dann wird nur die Aktion des Elements ausgeführt, der Spieler geht aber nicht weiter
				spieler.checkKollision();
				Elemente.beruehrung = false;
			} else if((spieler.check(51)==false)&&(weg_verschlossen==false)){
				spieler.checkKollision();
				spieler.x += Frame.dx;
				spieler.y += Frame.dy;
				Elemente.beruehrung = false;
			}
			//Schuss vom Spieler
			if((schuss_spieler.sichtbar)){
				if (schuss_spieler.setPos==false){
					schuss_spieler.setPos();
				}
				schuss_spieler.Schuss();
				counter_schuss = 1;
			}
			}
			try{
				Thread.sleep(5);
			} catch(Exception e){ 
				e.printStackTrace();
			}
		  }
		}
	
	//lädt den Shop auf das Spielfeld
	public static void showShop(){
		GegnerOULeben=GegnerOU.leben;
		GegnerRLLeben=GegnerRL.leben;
		EndgegnerLeben=Endgegner.leben;
		GegnerKILeben=GegnerKI.leben;
		level.loadLevel(new File("level/level0_0.lvl"));
		GegnerOU.leben=0;
		GegnerRL.leben =0;
		Endgegner.leben=0;
		GegnerKI.leben=0;
		Falle.aktiv=false;
		Schuss_Endgegner.sichtbar=false;
		Spielfeld.shop = true;
		spieler.x = 235;
		spieler.y = 315;
	}
	
	//lädt wieder das derzeitige Level auf das Spielfeld
	public static void hideShop(){
		GegnerOU.leben=GegnerOULeben;
		GegnerRL.leben=GegnerRLLeben;
		Endgegner.leben=EndgegnerLeben;
		GegnerKI.leben=GegnerKILeben;
		Falle.aktiv=true;
		level.loadLevel(new File("level/level"+current_lvl+"_"+current_room+".lvl"));
		spieler.x = Spielfeld.spieler_preposX;
		spieler.y = Spielfeld.spieler_preposY;
		Schuss_Endgegner.sichtbar=true;
		Spielfeld.shop = false;
	}
	
	//liefert die ID des Blocks bei gegebenen x,y Koordinaten eines Punktes auf dem Spielfeld
	public static int getBlockID(double x,double y){
		int i,j;
		for(i=0;i<Raum.worldHeight;i++){
			if((y>=i*Raum.blockSize)&(y<=(i+1)*Raum.blockSize)){
				break;
			}
		}
		for(j=0;j<Raum.worldWidth;j++){
			if((x>=j*Raum.blockSize)&(x<=(j+1)*Raum.blockSize)){
				break;
			}
		}
		if(Raum.block[i][j] != null){  //Nullpointerexception abfangen
			return Raum.block[i][j].ID;
		} else {
			return 0;
		}
	}
		
	//liefert den Block bei gegebenen x,y Koordinaten eines Punktes auf dem Spielfeld
	public static Block getBlock(double x,double y){
		int i,j;
		for(i=0;i<Raum.worldHeight;i++){
			if((y>=i*Raum.blockSize)&(y<=(i+1)*Raum.blockSize)){
				break;
			}
		}
		for(j=0;j<Raum.worldWidth;j++){
			if((x>=j*Raum.blockSize)&(x<=(j+1)*Raum.blockSize)){
				break;
			}
		}
		if(Raum.block[i][j] != null){  //Nullpointerexception abfangen
			return Raum.block[i][j];
		} else {
			return Raum.block[0][0];
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
