package propra2013.Gruppe54;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class GegnerRL extends Rectangle  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @param args
	 */
	public static boolean unten=false; // gibt an, ob der Gegener unten an eine Wand stößt
	public static boolean rechts=false; // gibt an, ob der Gegener rechts an eine Wand stößt  
	public static int leben; 
	public static int StartX;
	public static int StartY;
	public static int x,y;
	public static int StartLeben;
	public static int Faktor;
	public static boolean aktiv;//zur abfrage ob die ID in der Datei steht
	
	/**
	 * @param args
	 */
	
	public GegnerRL() {
		setBounds(StartX,StartY,32,32);
		if (Spielfeld.current_lvl==1){
			StartLeben=100;
			Faktor=3;
		} else 
			if(Spielfeld.current_lvl==2){
				StartLeben=200;
				Faktor=6;
			} else
				if(Spielfeld.current_lvl==3){
					StartLeben=300;
					Faktor=10;
				}
		leben=StartLeben;
		aktiv=false;
	}
	
	
	public  void draw(Graphics g){
	
			
			g.drawImage(new ImageIcon("pics/gegner1_"+Spielfeld.current_lvl+".png").getImage(), StartX, StartY, 32, 32, null); //zeichnet den Gegner 

	
	}
	
	
	
	public static void lauf(){
		//Gegner läuft von rechts nach links
	
        	 if ((rechts==false) &&(Spielfeld.getBlockID(GegnerRL.StartX+24, GegnerRL.StartY+16)!=1)){
        		Kollision2();
        		GegnerRL.StartX+=1;
        	 } else rechts = true;
        	 if ((rechts==true)&& (Spielfeld.getBlockID(GegnerRL.StartX-2, GegnerRL.StartY+16)!=1)){
        		Kollision2();
        		 GegnerRL.StartX-=1;
        	 } else rechts = false;  
		} 
	

//Sagt was passieren soll wenn ein Gegener mit dem Spieler zusammen trifft
public static void Kollision2(){

		if( (GegnerRL.StartX+26 >= Spielfeld.spieler.x)     && 
			(GegnerRL.StartX <= Spielfeld.spieler.x+26)  &&
			(GegnerRL.StartY+26 >= Spielfeld.spieler.y)  &&
			(GegnerRL.StartY <= Spielfeld.spieler.y+26)){		
			
			if (spieler.ruestung>0){
				spieler.ruestung-=1;
			} else spieler.leben -= 1;
			
		}
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
