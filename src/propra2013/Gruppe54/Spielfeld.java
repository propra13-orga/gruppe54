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
	
	public static Image[] elemente = new Image[16];
	
	public static int current_lvl=1,current_room=1,current_player=1;
	
	public static boolean isFirst = true; //erster Aufruf
	public static boolean shop = false;
	
	public static Raum raum = new Raum();
	public static level level = new level();
	public static spieler spieler;
	public static GegnerRL gegnerRL;
	public static GegnerOU gegnerOU;
	public static Endgegner Boss;
	public static Schuss_Endgegner schuss_endgegner;
	
	/**
	 * Konstruktor
	 */
	public Spielfeld(){
		setBounds(200,55,800,480);
		thread.start();
		spieler = new spieler();
	}
	
	/*       
			ID: 0 - Boden   1 - Mauer   2 - Ausgang 
		        3 - Falle_Loch   4 - Falle_Feuer   5 - Falle_Speer  
	            7 - Item_Trank   8 - Brunnen       9 - Zepter
	      		A - Gegner1      B - Gegner2       C - Gegner3
		  		. . . 			 F - Shopbesitzer						*/
	
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
		elemente[8] = new ImageIcon("pics/brunnen_rot.png").getImage();
		elemente[9] = new ImageIcon("pics/zepter"+current_lvl+".png").getImage();
		elemente[15] = new ImageIcon("pics/shopguy.png").getImage();
	}
	
	public void define(){
		raum = new Raum();
		gegnerRL = new GegnerRL();
		gegnerOU = new GegnerOU();
		Boss = new Endgegner(0);

		loadImages();
		level.loadLevel(new File("level/level"+current_lvl+"_"+current_room+".lvl"));   //level-datei laden
		schuss_endgegner = new Schuss_Endgegner();
		
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
		if(GegnerRL.aktiv){
			gegnerRL.draw(g);
		}
		if(GegnerOU.aktiv){
			gegnerOU.draw(g);
		}

		if ((Block.Boss_vorhanden==true)&&(Endgegner.aktiv)){
			Boss.draw(g);
		}
		//Schuss wird nur in room 3 gezeichnet
		if ((current_room==3)&&(Schuss.sichtbar==true)){
			if (Schuss_Endgegner.checkPos==false){
				Schuss_Endgegner.checkPos();
			}
			Schuss.bewegung();
			schuss_endgegner.draw(g);
		
		}
	}
	
	//Thread
	public void run(){
		while(true){
			validate();
			repaint();

			//Button Neustart anzeigen wenn der Spieler keine Lebenspunkte mehr hat
			if((propra2013.Gruppe54.spieler.leben <= 0)&&(propra2013.Gruppe54.spieler.aktiv)){
				propra2013.Gruppe54.spieler.aktiv = false;
				Frame.neustart.setVisible(true);
			}
			
			if(propra2013.Gruppe54.spieler.aktiv){
			
			//Steuerung des Spielers
			if((check(1))&&(check(8))&&(check(15))){		//Mauer, Brunnen und der Shopbesitzer dürfen nicht durchlaufen werden
				checkKollision();
				spieler.x += Frame.dx;
				spieler.y += Frame.dy;
				Elemente.beruehrung = false;
			} else if((check(8)==false)){   //Brunnen soll berührt aber nicht durschritten werden, also wird das Element nur aufgerufen aber der Spieler läuft nicht
				checkKollision();
			} else if((check(15)==false)){  //Shopbesitzer soll auch nur berührt werden und dann seine Aktion ausführen
				checkKollision();
			}
			}
			
			try{
				Thread.sleep(5);
			} catch(Exception e){ 
				e.printStackTrace();
			}
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
