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
	
	public static Image[] elemente = new Image[32];
	
	public static int current_lvl=1,current_room=1,current_player=1,counter_anzeige = 0;
	public static double spieler_preposX=0,spieler_preposY=0;
	
	public static boolean isFirst = true; //erster Aufruf
	public static boolean shop = false,shop_trank = false,shop_mana = false,shop_ruestung1 = false,shop_ruestung2 = false,shop_stiefel = false,anzeige = false;
	public static String text_anzeige;
	
	public Font font = new Font("Lucida Sans Typewriter",Font.PLAIN,8);
	
	public static Raum raum = new Raum();
	public static level level = new level();
	public static spieler spieler;
	public static GegnerRL gegnerRL;
	public static GegnerOU gegnerOU;
	public static Endgegner Boss;
	public static Schuss_Endgegner schuss_endgegner;
	public static Schuss_Spieler schuss_spieler;
	public static int EndgegnerLeben;
	public static int GegnerRLLeben;
	public static int GegnerOULeben;
	
	/**
	 * Konstruktor
	 */
	public Spielfeld(){
		setBounds(200,55,800,480);
		thread.start();
		spieler = new spieler();
		spieler.rechts=true;
		Boss = new Endgegner();
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
		elemente[2] = new ImageIcon("pics/ausgang.png").getImage();
		elemente[3] = new ImageIcon("pics/falle_loch.png").getImage();
		elemente[4] = new ImageIcon("pics/falle_feuer"+current_lvl+".png").getImage();
		elemente[5] = new ImageIcon("pics/falle_speer"+current_lvl+".png").getImage();
		elemente[6] = new ImageIcon("pics/anim.gif").getImage();
		elemente[7] = new ImageIcon("pics/item_trank.png").getImage();
		elemente[8] = new ImageIcon("pics/item_trank2.png").getImage();
		elemente[9] = new ImageIcon("pics/brunnen_rot.png").getImage();
		elemente[14] = new ImageIcon("pics/zepter"+current_lvl+".png").getImage();
		elemente[15] = new ImageIcon("pics/shopguy.png").getImage();
		//Shopelemente
		elemente[16] = new ImageIcon("pics/item_trank.png").getImage();
		elemente[17] = new ImageIcon("pics/item_trank2.png").getImage();
		elemente[18] = new ImageIcon("pics/item_ruestung1.png").getImage();
		elemente[19] = new ImageIcon("pics/item_ruestung2.png").getImage();
		elemente[20] = new ImageIcon("pics/item_stiefel.png").getImage();
		elemente[21] = new ImageIcon("pics/eingang_shop.png").getImage();
		elemente[22] = new ImageIcon("pics/ausgang_shop.png").getImage();
		//Schatzelemente
		elemente[23] = new ImageIcon("pics/truhe_zu.png").getImage();
		elemente[24] = new ImageIcon("pics/gold.png").getImage();
		elemente[27] = new ImageIcon("pics/checkpoint.png").getImage();
		elemente[31] = new ImageIcon("pics/leer.png").getImage();
	}
	
	public void define(){
		raum = new Raum();
		gegnerRL = new GegnerRL();
		gegnerOU = new GegnerOU();
		
		loadImages();
		level.loadLevel(new File("level/level"+current_lvl+"_"+current_room+".lvl"));   //level-datei laden
		schuss_endgegner = new Schuss_Endgegner();
		schuss_spieler = new Schuss_Spieler();
		GegnerOU.leben=GegnerOU.StartLeben;
		GegnerRL.leben=GegnerRL.StartLeben;
		Endgegner.leben=Endgegner.StartLeben;
	}

	public void paintComponent(Graphics g){
		if(isFirst){ //Erstinitialisierung
			define();
			isFirst=false;
		}
		raum.draw(g); //zeichnet den raum
		
		if(propra2013.Gruppe54.spieler.aktiv){
			spieler.draw(g);  //zeichnet den Spieler
		}
		
		if(GegnerRL.leben>0){
			gegnerRL.draw(g);
		}
		if(GegnerOU.leben>0){
			gegnerOU.draw(g);
		}

		if ((Block.Boss_vorhanden==true)&&(Endgegner.leben>0)){
			Boss.draw(g);
		}
		
		//Schuss vom Endgegner wird nur in raum 3 gezeichnet
		if ((current_room==3)&&(Schuss_Endgegner.sichtbar==true)&&(Endgegner.leben>0)){
			if (Schuss_Endgegner.checkPos==false){
				Schuss_Endgegner.checkPos();
			}
			Schuss_Endgegner.bewegung();
			schuss_endgegner.draw(g);
		
		}

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

		if((anzeige)&&(counter_anzeige<=300)){   //Anzeige über dem Spieler wenn etwas eingesammelt wurde
		if((anzeige)&&(counter_anzeige<=300)){
			g.setColor(Color.white);
			g.setFont(font);
			g.drawString(text_anzeige, (int)spieler.x, (int)spieler.y-10);
			counter_anzeige++;
		}
		}
		//Lebensanzeige Endgegner
		if ((current_room==3)&&(Endgegner.leben>0)){
			if(Endgegner.leben>Endgegner.StartLeben/4){
				g.setColor(Color.green);
			} else {
				g.setColor(Color.red);
			}
			g.fill3DRect(Endgegner.StartX, Endgegner.StartY-10,Endgegner.leben/Endgegner.Faktor,3,true);
				
		}
		//Lebensanzeige GegnerOU
		if (GegnerOU.leben>0){
			if(GegnerOU.leben>GegnerOU.StartLeben/4){
				g.setColor(Color.green);
			} else {
				g.setColor(Color.red);
			}
			g.fill3DRect(GegnerOU.StartX, GegnerOU.StartY-10,GegnerOU.leben/GegnerOU.Faktor,3,true);
				
		}
		//Lebensanzeige GegnerRL
		if (GegnerRL.leben>0){
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
			
			if((propra2013.Gruppe54.spieler.leben <= 0)&&(propra2013.Gruppe54.spieler.superleben > 0)){
				propra2013.Gruppe54.spieler.superleben -= 1;
				propra2013.Gruppe54.spieler.leben = 100;
				if(current_room == propra2013.Gruppe54.spieler.check_room){
					level.loadLevel(new File("level/level"+current_lvl+"_"+propra2013.Gruppe54.spieler.check_room+".lvl"));
				} else if(current_room != propra2013.Gruppe54.spieler.check_room){
					current_room = propra2013.Gruppe54.spieler.check_room;
					define();
				}
				spieler.x = propra2013.Gruppe54.spieler.checkpoint.getX();
				spieler.y = propra2013.Gruppe54.spieler.checkpoint.getY();
			} else if((propra2013.Gruppe54.spieler.leben <= 0)&&(propra2013.Gruppe54.spieler.superleben == 0)){
				propra2013.Gruppe54.spieler.aktiv = false;
				Frame.neustart.setVisible(true);
			}
			
			if(counter_anzeige==300){
				anzeige = false;
				counter_anzeige = 0;
			}
			
			if(propra2013.Gruppe54.spieler.aktiv){
			//Steuerung des Spielers
			if((check(1))&&(check(9))&&(check(15))&&(check(16))&&(check(17))
					&&(check(18))&&(check(19))&&(check(20))&&(check(23))){		//prüfen ob Elemente vom Spieler durchschritten werden dürfen
				checkKollision();
				spieler.x += Frame.dx;
				spieler.y += Frame.dy;
				Elemente.beruehrung = false;
			} else if((check(9)==false) | (check(15)==false) | (check(16)==false) | (check(17)==false)
					| (check(18)==false) | (check(19)==false) | (check(20)==false) | (check(23)==false)){	//wenn nicht, dann wird nur die Aktion des Elements ausgeführt, der Spieler geht aber nicht weiter
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
		level.loadLevel(new File("level/level0_0.lvl"));
		GegnerOU.leben=0;
		GegnerRL.leben =0;
		Endgegner.leben=0;
		Schuss_Endgegner.sichtbar=false;
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
	}
	
	//prüft ob die boolean Variablen noch auf "true" gesetzt sind obwohl der Spieler nicht mehr vor dem Item steht
	public static void checkShopItems(){
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
        if((Spielfeld.shop_stiefel)&&(Spielfeld.check(20))){
        	Spielfeld.shop_stiefel = false;
        }
	}
	
	//prüft an 4 Punkten ob sich dort ein Objekt befindet durch das der Spieler nicht laufen darf
	public static boolean check(int ID){
		if((getBlockID(spieler.x+6+Frame.dx,spieler.y+26+Frame.dy)!=ID)&&(getBlockID(spieler.x+26+Frame.dx,spieler.y+26+Frame.dy)!=ID)&&(getBlockID(spieler.x+6+Frame.dx,spieler.y+32+Frame.dy)!=ID)
			&&(getBlockID(spieler.x+26+Frame.dx,spieler.y+32+Frame.dy)!=ID)&&(spieler.x+Frame.dx>0)&&(spieler.y+32+Frame.dy<(Raum.worldHeight*Raum.blockSize)-2)){
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
		return Raum.block[i][j].ID;
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
		return Raum.block[i][j];
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
