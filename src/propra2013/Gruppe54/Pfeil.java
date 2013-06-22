package propra2013.Gruppe54;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Pfeil extends Rectangle{

	public static int x,y;
	public static boolean aktiv = false;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Pfeil(){
		setBounds(x,y,16,16);
	}
	
	public void draw(Graphics g){
		if(aktiv){
			g.drawImage(new ImageIcon("pics/pfeil.png").getImage(),x,y,16,16,null);
		}
	}
	
	public static void Schuss(){
		if(Spielfeld.spieler.rechts){
			x += 4;
		} else if(Spielfeld.spieler.links){
			x -= 4;
		} else if(Spielfeld.spieler.hoch){
			y -= 4;
		} else if(Spielfeld.spieler.runter){
			y += 4;
		}
		Kollision();
	}
	
	public static boolean check(int ID){
		if((Spielfeld.getBlockID(x,y+16)!=ID)&& //unten links
			(Spielfeld.getBlockID(x+16,y+16)!=ID)&& //unten rechts
			(Spielfeld.getBlockID(x+16,y)!=ID)&& //oben rechts
			(Spielfeld.getBlockID(x,y)!=ID)){ //oben links
			return true;
		} else { 
			return false;
		}
	}
	
	public static void Kollision(){
			//Endgegner
			if((x+16 >= Endgegner.StartX)&&(x <= Endgegner.StartX+31)&&
			   (y+16 >= Endgegner.StartY)&&(y <= Endgegner.StartY+31)){		
				aktiv=false;
				Endgegner.leben-=25;
			//GegnerRL
			} else if((x+16 >= Spielfeld.gegnerRL.StartX)&&(x <= Spielfeld.gegnerRL.StartX+31)&&
			          (y+16 >= Spielfeld.gegnerRL.StartY)&&(y <= Spielfeld.gegnerRL.StartY+31)){			
				aktiv=false;
				Spielfeld.gegnerRL.leben-=25;
			//GegnerOU
			} else if((x+16 >= Spielfeld.gegnerOU.StartX)&&(x <= Spielfeld.gegnerOU.StartX+31)&&
	        		  (y+16 >= Spielfeld.gegnerOU.StartY)&&(y <= Spielfeld.gegnerOU.StartY+31)){		
					aktiv=false;	
					Spielfeld.gegnerOU.leben-=25;
			//Schuss_Endgegner
			}else if((x+16 >= Spielfeld.schuss_endgegner.StartX)&&(x <= Spielfeld.schuss_endgegner.StartX+31)&&
					 (y+16 >= Spielfeld.schuss_endgegner.StartY)&&(y <= Spielfeld.schuss_endgegner.StartY+31)){		
				aktiv=false;	
				Spielfeld.schuss_endgegner.StartX=(int)Endgegner.StartX;
				Spielfeld.schuss_endgegner.StartY=(int)Endgegner.StartY;
			//Bewegliche Falle
			} else if((x+16 >= Falle.StartX)&&(x <= Falle.StartX+31)&&
					  (y+16 >= Falle.StartY)&&(y <= Falle.StartY+31)){		
				aktiv=false;	
			}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
