package propra2013.Gruppe54;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Schuss_Endgegner extends Rectangle {
	
	private static final long serialVersionUID = 1L;
	public static boolean checkPos=false;
	public static int StartX,StartY;
	public static boolean sichtbar=false;
	public static boolean aktiv;
	
	public Schuss_Endgegner() {
		setBounds(StartX,StartY,32,32);
		sichtbar=true;
		aktiv=false;
	}
	
	//Zeichnet einen Schuss
	//muss noch ein anderes Bild eingefügt werden
		public void draw(Graphics g){
			g.drawImage(new ImageIcon("pics/schuss2.png").getImage(), StartX, StartY, 32, 32, null); 
		}
		
		//Bewegung nach rechts so lange es geht dann wieder vom Gegner starten
		public static void bewegung(){
			if ((Spielfeld.getBlockID(StartX+24, StartY+16)!=1)&&(Spielfeld.getBlockID(StartX+24, StartY+16)!=41)
					&&(Spielfeld.getBlockID(StartX+24, StartY+16)!=42)&&(Spielfeld.getBlockID(StartX+24, StartY+16)!=43)
					&&(Spielfeld.getBlockID(StartX+24, StartY+16)!=4)){
	        	 StartX+=1;
	        	 Kollision();
	        } else {
	       		 StartX=Endgegner.StartX;
	     	     StartY=Endgegner.StartY;
	        }
		}
		
	public static void Kollision(){
		if( (Schuss_Endgegner.StartX+31 >= Spielfeld.spieler.x)&&(Schuss_Endgegner.StartX <= Spielfeld.spieler.x+31)  &&
			(Schuss_Endgegner.StartY+31 >= Spielfeld.spieler.y)&&(Schuss_Endgegner.StartY <= Spielfeld.spieler.y+31)){		
			if (spieler.ruestung>0){
				spieler.ruestung-=1;
			} else {
				spieler.leben -= 1;
			}
		} 
	} 
	
	//schaut nach wo der Endgegner steht (nur ein mal)
	public static void checkPos(){
		StartX=Endgegner.StartX;
		StartY=Endgegner.StartY;
		checkPos=true;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
