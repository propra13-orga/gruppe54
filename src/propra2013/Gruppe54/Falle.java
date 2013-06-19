package propra2013.Gruppe54;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Falle extends Rectangle {
	//zunächst eine falle die nach unten schießt
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param args
	 */
	public static double StartX;
	public static double StartY;
	public static double speed = 0.5;
	public static int Schaden; //Schaden der die Falle anrichtet
	public static boolean unten;
	public static boolean aktiv;
	public static boolean status = false;
	public static double StartPunktX;
	public static double StartPunktY;
	
	public Falle(){
		setBounds((int)StartX,(int)StartY,32,32);
		if (Spielfeld.current_lvl==1){
			Schaden=1;
		} else if(Spielfeld.current_lvl==2){
			Schaden=2;
		} else if (Spielfeld.current_lvl==3){
			Schaden=3;
		}
		aktiv=false;
	}

	public void draw(Graphics g){
		if((Falle.StartX!=0)&&(Falle.StartY!=0)){
		g.drawImage(new ImageIcon("pics/falle_beweglich_unten_"+Spielfeld.current_lvl+".png").getImage(), (int)StartX, (int)StartY, 32, 32, null);
	}}

	
	public static void bewegung(){
		if ((Spielfeld.getBlockID(Falle.StartX+16, Falle.StartY+32)!=1)&&(Spielfeld.getBlockID(Falle.StartX+16, Falle.StartY+32)!=4)){
       	 Falle.StartY+=1*speed;
       	 Kollision();
       	 } else if((Spielfeld.getBlockID(Falle.StartX+16, Falle.StartY+32)==1)|(Spielfeld.getBlockID(Falle.StartX+16, Falle.StartY+32)==4)){
       		 Falle.StartX=Falle.StartPunktX;
    		 Falle.StartY=Falle.StartPunktY;
       	 }
		
	}
	
	public static void Kollision(){

		if((Falle.StartX+31 >= Spielfeld.spieler.x)&&(Falle.StartX <= Spielfeld.spieler.x+31)  &&
			(Falle.StartY+31 >= Spielfeld.spieler.y)&&(Falle.StartY <= Spielfeld.spieler.y+31)){		
			if (Spieler.ruestung>0){
				Spieler.ruestung-=Schaden;
			} else {
				Spieler.leben -= Schaden;
			}	
		}	
	}
		
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
