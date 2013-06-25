package propra2013.Gruppe54;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Spieler extends Rectangle {

	public double x=Raum.Startpunkt[Spielfeld.current_lvl-1].getX();
	public double y=Raum.Startpunkt[Spielfeld.current_lvl-1].getY(); 
	
	public double speed = 0.5;
	public int gold = 0;
	public int waffe = 0,schluessel = 0,pfeile = 10;
	public double schaden = 5;
	public int item_trank = 0,item_mana = 0,item_supertrank = 0;
	public int leben = 100,superleben = 3,mana = 100,xp = 0,ruestung = 50;
	public boolean aktiv = false,schwert = true,axt = false,bogen = true;
	public Point checkpoint;
	public int check_room = 1;		//Raum wo das letzte Mal der Checkpoint betätigt wurde
	public int Anzahl_Schüssen=9;
	
	public boolean beweglich = false;
	public boolean rechts = false;
	public boolean links = false;
	public boolean hoch = false;
	public boolean runter = false; 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Spieler(){
		setBounds((int)x,(int)y,32,32);
		aktiv = true;		//gibt an ob der Spieler im Spiel ist
		beweglich = true;
	}
	
	public void draw(Graphics g){
		g.drawImage(Frame.image,(int)x,(int)y,32,32,null);
		//g.drawImage(new ImageIcon("pics/spieler"+Spielfeld.current_player+".png").getImage(), x, y, 32, 32, null); //zeichnet den Spieler an (x,y)
	}
	
	//prüft am derzeitigen Standort des Spielers an 4 Punkten ob dieser ein Objekt berührt welches eine Aktion ausführt
	public void checkKollision(){
		Elemente.beruehrung = false;
		Elemente.Aufruf(Spielfeld.getBlockID(x+26+Frame.dx,y+32+Frame.dy),Spielfeld.getBlock(x+26+Frame.dx,y+32+Frame.dy));
        Elemente.Aufruf(Spielfeld.getBlockID(x+6+Frame.dx,y+26+Frame.dy),Spielfeld.getBlock(x+6+Frame.dx, y+26+Frame.dy));
        if(Elemente.beruehrung == false){
        Elemente.Aufruf(Spielfeld.getBlockID(x+26+Frame.dx,y+26+Frame.dy),Spielfeld.getBlock(x+26+Frame.dx,y+26+Frame.dy));
        Elemente.Aufruf(Spielfeld.getBlockID(x+6+Frame.dx,y+32+Frame.dy),Spielfeld.getBlock(x+6+Frame.dx,y+32+Frame.dy));
        }
    }
	
	//prüft ob die boolean Variablen noch auf "true" gesetzt sind obwohl der Spieler nicht mehr vor dem Item steht
	//damit die Anzeige ausgeblendet wird
	public void checkShopItems(){
	   if((Spielfeld.shop_trank)&&(check(21))){
	       	Spielfeld.shop_trank = false;
	   } else if((Spielfeld.shop_mana)&&(check(22))){
	      	Spielfeld.shop_mana = false;
	   } else if((Spielfeld.shop_ruestung1)&&(check(23))){
	       	Spielfeld.shop_ruestung1 = false;
	   } else if((Spielfeld.shop_ruestung2)&&(check(24))){
	       	Spielfeld.shop_ruestung2 = false;
	   } else if((Spielfeld.shop_stiefel)&&(check(25))){
	      	Spielfeld.shop_stiefel = false;
	   } else if((Spielfeld.shop_axt)&&(check(28))){
	       	Spielfeld.shop_axt = false;
	   } else if((Spielfeld.shop_supertrank)&&(check(29))){
	       	Spielfeld.shop_supertrank = false;
	   } else if((Spielfeld.shop_schuss2)&&(check(30))){
	       	Spielfeld.shop_schuss2 = false;
	   } else if((Spielfeld.shop_bogen)&&(check(53))){
	       	Spielfeld.shop_bogen = false;
	   } else if((Spielfeld.shop_pfeile)&&(check(52))){
	       	Spielfeld.shop_pfeile = false;
	   }
	   Spielerinfo.npc = false;
       Spielerinfo.info = false;
       Spielerinfo.ruestung_voll = false;
       Spielerinfo.speed_voll = false;
       Spielerinfo.gold = false;
       Spielerinfo.item_vorhanden = false;
       Spielfeld.preis_shop = false;
	}
		
	//prüft an 4 Punkten ob sich dort ein Objekt befindet durch das der Spieler nicht laufen darf
	public boolean check(int ID){
		if((Spielfeld.getBlockID(x+6+Frame.dx,y+26+Frame.dy)!=ID)&&(Spielfeld.getBlockID(x+26+Frame.dx,y+26+Frame.dy)!=ID)&&(Spielfeld.getBlockID(x+6+Frame.dx,y+32+Frame.dy)!=ID)
			&&(Spielfeld.getBlockID(x+26+Frame.dx,y+32+Frame.dy)!=ID)&&(x+Frame.dx>0)&&(y+32+Frame.dy<(Raum.worldHeight*Raum.blockSize))&&(x+32+Frame.dx<(Raum.worldWidth*Raum.blockSize))
			&&(y+Frame.dy>0)){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
