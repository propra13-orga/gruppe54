package propra2013.Gruppe54;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Schuss extends Rectangle {
	/* Oberklasse Schuss, von welcher man dann den Schuss des Endgegners und 
	  den Schuss des Spielers ableiten kann*/
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int StartX,StartY;
	public static boolean sichtbar=false;

	/**
	 * @param args
	 */
	
	public Schuss(){
		setBounds(StartX,StartY,32,32);
		sichtbar=true;
		
	}
	
	//Zeichnet einen Schuss
	//muss noch ein anderes Bild eingefügt werden
		public void draw(Graphics g){
			g.drawImage(new ImageIcon("pics/falle_feuer2.png").getImage(), StartX, StartY, 32, 32, null); 
			
		}
		
		//Bewegung nach rechts so lange es geht dann wieder vom Gegner starten
		public static void bewegung(){
		
	        	 if (Spielfeld.getBlockID(Schuss.StartX+24, Schuss.StartY+16)!=1){
	        		 Schuss.StartX+=1;
	        		 Kollision();
	        	 } else 
	        		 StartX=Endgegner.StartX;
	     		     StartY=Endgegner.StartY;
	        	 	 }
		
			
		
		public static void Kollision(){
			//nimmt man den Kommentar weg, dann klappt die Kollision, muss aber auch 
			//anders gehen wenn der Spieler dann auch schießt
			
			/*if( (Schuss_Endgegner.StartX+31 >= Spielfeld.spieler.x)     && 
					(Schuss_Endgegner.StartX <= Spielfeld.spieler.x+31)  &&
					(Schuss_Endgegner.StartY+31 >= Spielfeld.spieler.y)  &&
					(Schuss_Endgegner.StartY <= Spielfeld.spieler.y+31)){		
					
					spieler.leben -= 1;
					
				}*/
		}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
