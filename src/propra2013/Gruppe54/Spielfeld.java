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
	
	public static Image[] elemente = new Image[50];
	
	public static int current_lvl=1,current_room=1,current_player=1,counter_anzeige = 0;
	public static double spieler_preposX=0,spieler_preposY=0;
	
	public static boolean isFirst = true; //erster Aufruf
	public static boolean shop = false,shop_trank = false,shop_mana = false,shop_supertrank = false,shop_ruestung1 = false,shop_ruestung2 = false,shop_stiefel = false,shop_axt = false,anzeige = false;
	public static String text_anzeige;
	public static Raum raum = new Raum();
	public static level level = new level();
	public static spieler spieler;
	public static GegnerRL gegnerRL;
	public static GegnerOU gegnerOU;
	public static Endgegner Boss;
	public static int GegnerRL_counter = 0,GegnerOU_counter = 0,Endgegner_counter = 0;
	public static Schuss_Endgegner schuss_endgegner;
	public static Schuss_Spieler schuss_spieler;
	public static Pfeil pfeil;
	public static int EndgegnerLeben;
	public static int GegnerRLLeben;
	public static int GegnerOULeben;
	public static int counter_angriff;
	public static Falle falle;
	public static Waffe waffe;
	
	/**
	 * Konstruktor
	 */
	public Spielfeld(){
		setBounds(230,55,800,480);
		thread.start();
		spieler = new spieler();
		spieler.runter=true;
	}
	
	/*       
			ID: 0 - Boden   1 - Mauer   2 - Ausgang 
		        3 - Falle_Loch   4 - Falle_Feuer   5 - Falle_Speer  
	            7 - Item_Trank   8 - Item_Trank2   9 - Brunnen
	      		A - Gegner1      B - Gegner2       C - Gegner3
		  		D				 E - Zepter	   	   F - Shopbesitzer						*/
	
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
		elemente[7] = new ImageIcon("pics/falle_loch.png").getImage();
		elemente[8] = new ImageIcon("pics/falle_feuer"+current_lvl+".png").getImage();
		elemente[9] = new ImageIcon("pics/falle_speer"+current_lvl+".png").getImage();
		elemente[10] = new ImageIcon("pics/anim.gif").getImage();
		//11
		//12
		//Items
		elemente[13] = new ImageIcon("pics/item_trank.png").getImage();
		elemente[14] = new ImageIcon("pics/item_trank2.png").getImage();
		elemente[15] = new ImageIcon("pics/brunnen_rot.png").getImage();
		elemente[16] = new ImageIcon("pics/item_trank3.png").getImage();
		//17
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
		elemente[43] = new ImageIcon("pics/wasser1.png").getImage();
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
		waffe = new Waffe();
		loadImages();
		level.loadLevel(new File("level/level"+current_lvl+"_"+current_room+".lvl"));   //level-datei laden
		schuss_endgegner = new Schuss_Endgegner();
		schuss_spieler = new Schuss_Spieler();
		//pfeil = new Pfeil();
		GegnerOU.leben=GegnerOU.StartLeben;
		GegnerRL.leben=GegnerRL.StartLeben;
		Endgegner.leben=Endgegner.StartLeben;
		Spielfeld.GegnerRL_counter = 0;
		Spielfeld.GegnerOU_counter = 0;
		Spielfeld.Endgegner_counter = 0;
		Waffe.ID = spieler.waffe;
	}

	public void paintComponent(Graphics g){
		if(isFirst){ //Erstinitialisierung
			define();
			isFirst=false;
		}
		raum.draw(g); //zeichnet den raum
		if(propra2013.Gruppe54.spieler.aktiv){
			spieler.draw(g);  //zeichnet den Spieler
			//Waffe des Spielers
			if((spieler.schwert)){
				Waffe.ID = spieler.waffe;
				Waffe.draw(g);
			}
			if((Waffe.angriff)&&(Waffe.ID != 3)){
				if(counter_angriff == 0){
					Waffe.Kollision();
				}
				counter_angriff++;
				if(counter_angriff == 10000){
					counter_angriff = 0;
				}
			}
			if((spieler.waffe == 3)&&(Waffe.angriff)){
				Waffe.draw(g);
			}
		}
		if((GegnerRL.leben>0)&&(GegnerRL.aktiv)){
			gegnerRL.draw(g);
		} else if((GegnerRL.leben <= 0)&&(GegnerRL_counter == 0)&&(shop == false)){	//wenn der Gegner besiegt wurde müssen seine Koordinaten auf 0 gesetzt werden
			getBlock(GegnerRL.StartX+16,GegnerRL.StartY+16).ID = 32;				//der counter ist dafür, dass beim besiegen des Gegners nur einmal ein Schatz liegt
			GegnerRL_counter = 1;
			GegnerRL.StartX = 0;
			GegnerRL.StartY = 0;
		}
		
		if((GegnerOU.leben>0)&&(GegnerOU.aktiv)){
			gegnerOU.draw(g);
		} else if((GegnerOU.leben <= 0)&&(GegnerOU_counter == 0)&&(shop == false)){ // "   " 
			getBlock(GegnerOU.StartX+16,GegnerOU.StartY+16).ID = 14;
			GegnerOU_counter = 1;
			GegnerOU.StartX = 0;
			GegnerOU.StartY = 0;
		}
		
		if((Falle.aktiv)&&(Spielfeld.shop == false)){
			falle.draw(g);
		}

		if ((Endgegner.leben>0)&&(Endgegner.aktiv)){
			Boss.draw(g);
		} else if((Endgegner.leben <= 0)&&(Endgegner_counter == 0)&&(shop == false)){ // "   "
			getBlock(Endgegner.StartX+16,Endgegner.StartY+16).ID = 13;
			Endgegner_counter = 1;
			Endgegner.StartX = 0;
			Endgegner.StartY = 0;
		}
		
		//SchussEndgegner wird nur in raum 3 gezeichnet
		if ((current_room==3)&&(Schuss_Endgegner.sichtbar==true)&&(Endgegner.leben>0)&&(Schuss_Endgegner.aktiv)){

			if (Schuss_Endgegner.checkPos==false){
				Schuss_Endgegner.checkPos();
			}
			Schuss_Endgegner.bewegung();
			schuss_endgegner.draw(g);
		
		}
		//Schuss Spieler
		if (Schuss_Spieler.sichtbar==true){
			if (Schuss_Spieler.checkPos==false){
				Schuss_Spieler.checkPos();
			}
			
			Schuss_Spieler.SchussRechts();
			Schuss_Spieler.SchussLinks();
			Schuss_Spieler.SchussOben();
			Schuss_Spieler.SchussUnten();
			
			schuss_spieler.draw(g);
		}

		//Anzeige von Schatzelementen
		if((anzeige)&&(counter_anzeige<=300)&&(propra2013.Gruppe54.spieler.aktiv)){
			g.setColor(Color.white);
			g.setFont(new Font("Lucida Sans Typewriter",Font.PLAIN,8));
			g.drawString(text_anzeige, (int)spieler.x, (int)spieler.y-10);
			counter_anzeige++;
		}
		
		//Lebensanzeige Endgegner
		if ((current_room==3)&&(Endgegner.leben>0)&&(Endgegner.aktiv)){
			if(Endgegner.leben>Endgegner.StartLeben/4){
				g.setColor(Color.green);
			} else {
				g.setColor(Color.red);
			}
			g.fill3DRect(Endgegner.StartX, Endgegner.StartY-10,Endgegner.leben/Endgegner.Faktor,3,true);
		}
		//Lebensanzeige GegnerOU
		if ((GegnerOU.aktiv)&&(GegnerOU.leben>0)){

			if(GegnerOU.leben>GegnerOU.StartLeben/4){
				g.setColor(Color.green);
			} else {
				g.setColor(Color.red);
			}

			g.fill3DRect(GegnerOU.StartX, GegnerOU.StartY-10,GegnerOU.leben/GegnerOU.Faktor,3,true);
				
		}
		//Lebensanzeige GegnerRL

		if ((GegnerRL.leben>0)&&(GegnerRL.aktiv)){

			if(GegnerRL.leben>GegnerOU.StartLeben/4){
				g.setColor(Color.green);
			} else {
				g.setColor(Color.red);
			}

			g.fill3DRect(GegnerRL.StartX, GegnerRL.StartY-10,GegnerRL.leben/GegnerRL.Faktor,3,true);
		}
	}
	
	//Thread
	public void run(){
		while(true){
			validate();
			repaint();
			
			if((propra2013.Gruppe54.spieler.leben <= 0)&&(propra2013.Gruppe54.spieler.superleben >= 1)&&(propra2013.Gruppe54.spieler.aktiv)){
				propra2013.Gruppe54.spieler.aktiv = false;
				anzeige = false;
				Waffe.angriff = false;
				if(propra2013.Gruppe54.spieler.checkpoint.getX() != Raum.Startpunkt[Spielfeld.current_lvl-1].getX()){
					Frame.checkpoint.setVisible(true);
				} else if(propra2013.Gruppe54.spieler.checkpoint.getX() == Raum.Startpunkt[Spielfeld.current_lvl-1].getX()){
					Frame.neustart.setVisible(true);	
				}
				propra2013.Gruppe54.spieler.superleben -= 1;
			} else if((propra2013.Gruppe54.spieler.leben <= 0)&&(propra2013.Gruppe54.spieler.superleben <= 0)&&(propra2013.Gruppe54.spieler.aktiv)){
				propra2013.Gruppe54.spieler.aktiv = false;
				anzeige = false;
				Waffe.angriff = false;
				Frame.neustart.setVisible(true);
			}
			
			if(counter_anzeige==300){  //String über dem Spieler zur Ausgabe eingesammelter Werte
				anzeige = false;
				counter_anzeige = 0;
			}
			
			if(propra2013.Gruppe54.spieler.aktiv){
			//Steuerung des Spielers
			if((check(1))&&(check(15))&&(check(18))&&(check(20))&&(check(21))
					&&(check(22))&&(check(23))&&(check(24))&&(check(25))&&(check(28))&&(check(31))
					&&(check(41))&&(check(42))&&(check(43))&&(check(2))&&(check(4))&&(check(29))){		//prüfen ob Elemente vom Spieler durchschritten werden dürfen
				checkKollision();
				spieler.x += Frame.dx;
				spieler.y += Frame.dy;
				Elemente.beruehrung = false;
			} else if((check(15)==false) | (check(18)==false) | (check(20)==false) | (check(21)==false) | (check(22)==false)
					| (check(23)==false) | (check(24)==false) | (check(25)==false) | (check(28)==false) | (check(31)==false)
					| (check(29)==false)){	//wenn nicht, dann wird nur die Aktion des Elements ausgeführt, der Spieler geht aber nicht weiter
				checkKollision();
				Elemente.beruehrung = false;
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
		Falle.aktiv = false;
		level.loadLevel(new File("level/level0_0.lvl"));
		GegnerOU.leben=0;
		GegnerRL.leben =0;
		Endgegner.leben=0;
		Schuss_Endgegner.sichtbar=false;
		Spielfeld.shop = true;
		spieler.x = 100;
		spieler.y = 150;
	}
	
	//lädt wieder das derzeitige Level auf das Spielfeld
	public static void hideShop(){
		GegnerOU.leben=GegnerOULeben;
		GegnerRL.leben=GegnerRLLeben;
		Endgegner.leben=EndgegnerLeben;
		level.loadLevel(new File("level/level"+current_lvl+"_"+current_room+".lvl"));
		spieler.x = Spielfeld.spieler_preposX;
		spieler.y = Spielfeld.spieler_preposY;
		Schuss_Endgegner.sichtbar=true;
		Spielfeld.shop = false;
	}
	
	//prüft ob die boolean Variablen noch auf "true" gesetzt sind obwohl der Spieler nicht mehr vor dem Item steht
	//damit die Anzeige ausgeblendet wird
	public static void checkShopItems(){
        if((Spielfeld.shop_trank)&&(Spielfeld.check(21))){
        	Spielfeld.shop_trank = false;
        }
        if((Spielfeld.shop_mana)&&(Spielfeld.check(22))){
        	Spielfeld.shop_mana = false;
        }
        if((Spielfeld.shop_ruestung1)&&(Spielfeld.check(23))){
        	Spielfeld.shop_ruestung1 = false;
        }
        if((Spielfeld.shop_ruestung2)&&(Spielfeld.check(24))){
        	Spielfeld.shop_ruestung2 = false;
        }
        if((Spielfeld.shop_stiefel)&&(Spielfeld.check(25))){
        	Spielfeld.shop_stiefel = false;
        }
        if((Spielfeld.shop_axt)&&(Spielfeld.check(28))){
        	Spielfeld.shop_axt = false;
        }
	}
	
	//prüft an 4 Punkten ob sich dort ein Objekt befindet durch das der Spieler nicht laufen darf
	public static boolean check(int ID){
		if((getBlockID(spieler.x+6+Frame.dx,spieler.y+26+Frame.dy)!=ID)&&(getBlockID(spieler.x+26+Frame.dx,spieler.y+26+Frame.dy)!=ID)&&(getBlockID(spieler.x+6+Frame.dx,spieler.y+32+Frame.dy)!=ID)
			&&(getBlockID(spieler.x+26+Frame.dx,spieler.y+32+Frame.dy)!=ID)&&(spieler.x+Frame.dx>0)&&(spieler.y+32+Frame.dy<(Raum.worldHeight*Raum.blockSize))&&(spieler.x+32+Frame.dx<(Raum.worldWidth*Raum.blockSize))
			&&(spieler.y+Frame.dy>0)){
			return true;
		} else {
			return false;
		}
	}
	
	//prüft am derzeitigen Standort des Spielers an 4 Punkten ob dieser ein Objekt berührt welches eine Aktion ausführt
	public static void checkKollision(){
		Elemente.beruehrung = false;
		Elemente.Aufruf(getBlockID(spieler.x+6+Frame.dx, spieler.y+26+Frame.dy),getBlock(spieler.x+6+Frame.dx, spieler.y+26+Frame.dy));
        if(Elemente.beruehrung == false){ //zweiten Punkt prüfen
        	Elemente.Aufruf(getBlockID(spieler.x+26+Frame.dx,spieler.y+26+Frame.dy),getBlock(spieler.x+26+Frame.dx,spieler.y+26+Frame.dy));
        } else if(Elemente.beruehrung == false){ //dritten Punkt prüfen
        	Elemente.Aufruf(getBlockID(spieler.x+6+Frame.dx,spieler.y+34+Frame.dy),getBlock(spieler.x+6+Frame.dx,spieler.y+34+Frame.dy));
        } else if(Elemente.beruehrung == false){ //vierten Punkt prüfen
        	Elemente.Aufruf(getBlockID(spieler.x+26+Frame.dx,spieler.y+34+Frame.dy),getBlock(spieler.x+26+Frame.dx,spieler.y+34+Frame.dy));
        }
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
