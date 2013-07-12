package propra2013.Gruppe54;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Pfeil extends Rectangle{

	private static final long serialVersionUID = 1L;
	public double x,y;
	public double speed = 0.9;
	public boolean aktiv = false;
	public int richtung;

	/**
	 * Konstruktor
	 */
	public Pfeil(){
		setBounds((int)x,(int)y,24,24);
	}
	
	/**
	 * Zeichnet den Schuss auf das Spielfeld an die Position des Spielers
	 * @param g
	 */
	public void draw(Graphics g){
		if(aktiv){
			g.drawImage(new ImageIcon("pics/pfeil"+richtung+".png").getImage(),(int)x,(int)y,24,24,null);
		}
	}
	
	/**
	 * Bewegung des Schusses
	 */
	public void Schuss(){
		Kollision();
		if(aktiv){
			if(richtung == 0){
				x += 1*speed;
			} else if(richtung == 1){
				x -= 1*speed;
			} else if(richtung == 2){
				y -= 1*speed;
			} else if(richtung == 3){
				y += 1*speed;
			}
		}
	}
	
	/**
	 * Check - Prüft ob die Richtung in die geschossen werden soll frei ist
	 * @param ID - die jeweilige ID des Objektes auf das geprüft werden soll 
	 **/
	public boolean check(int ID){
		boolean frei = false;
		switch(richtung){
			case 0:
				if((Spielfeld.getBlockID(x+23,y+13)!=ID)&&(Spielfeld.getBlockID(x+1,y+13)!=ID)){
					frei = true;
				} else { 
					frei = false;
				}
			case 1:
				if((Spielfeld.getBlockID(x+23,y+13)!=ID)&&(Spielfeld.getBlockID(x+1,y+13)!=ID)){
					frei = true;
				} else { 
					frei = false;
				}
				break;
			case 2:
				if((Spielfeld.getBlockID(x+12,y+1)!=ID)&&(Spielfeld.getBlockID(x+12,y+23)!=ID)){
					frei = true;
				} else { 
					frei = false;
				}
				break;
			case 3:
				if((Spielfeld.getBlockID(x+12,y+1)!=ID)&&(Spielfeld.getBlockID(x+12,y+23)!=ID)){
					frei = true;
				} else { 
					frei = false;
				}
				break;
		}
		return frei;
	}
	
	/**
	 * Kollision - bestimmt die jeweilige Aktion wenn der Pfeil auf ein Objekt stößt
	 **/
	public void Kollision(){
			//Endgegner
			if((x+16 >= Endgegner.StartX)&&(x <= Endgegner.StartX+31)&&
			   (y+16 >= Endgegner.StartY)&&(y <= Endgegner.StartY+31)){		
				aktiv = false;
				Endgegner.leben-=25;
				if(Spielfeld.multiplayer){
					Spielfeld.client.send(Spielfeld.client.socket.getLocalPort()+";gegner;4;25;");
				}
			//GegnerRL
			} else if((x+16 >= Spielfeld.gegnerRL.StartX)&&(x <= Spielfeld.gegnerRL.StartX+31)&&
			          (y+16 >= Spielfeld.gegnerRL.StartY)&&(y <= Spielfeld.gegnerRL.StartY+31)){			
				aktiv = false;
				Spielfeld.gegnerRL.leben-=25;
				if(Spielfeld.multiplayer){
					Spielfeld.client.send(Spielfeld.client.socket.getLocalPort()+";gegner;1;25;");
				}
			//Schuss_Endgegner
			}else if((x+16 >= Spielfeld.schuss_endgegner.StartX)&&(x <= Spielfeld.schuss_endgegner.StartX+31)&&
					 (y+16 >= Spielfeld.schuss_endgegner.StartY)&&(y <= Spielfeld.schuss_endgegner.StartY+31)){		
				aktiv = false;	
				Spielfeld.schuss_endgegner.StartX=(int)Endgegner.StartX;
				Spielfeld.schuss_endgegner.StartY=(int)Endgegner.StartY;
			//Bewegliche Falle
			} else if((x+16 >= Falle.StartX)&&(x <= Falle.StartX+31)&&
					  (y+16 >= Falle.StartY)&&(y <= Falle.StartY+31)){		
				aktiv = false;	
			} else if((check(1)==false)|(check(2)==false)|(check(4)==false)|(check(10)==false)|(check(41)==false)|(check(42)==false)|check(51)==false){
				aktiv = false;
			} else if((x<5)|(x>Raum.worldWidth*Raum.blockSize-30)|(y<5)|(y>Raum.worldHeight*Raum.blockSize-30)){
				aktiv = false;
			}
	}

	public static void main(String[] args) {}

}
