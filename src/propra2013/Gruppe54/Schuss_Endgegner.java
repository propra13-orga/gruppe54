package propra2013.Gruppe54;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Schuss_Endgegner extends Schuss {
/* Unterklasse von Schuss*/
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static boolean checkPos=false;


	public Schuss_Endgegner() {
		super();
	}
	
	//schaut nach wo der Endgegner steht (nur ein mal)
	public static void checkPos(){
		StartX=Endgegner.StartX;
		StartY=Endgegner.StartY;
		checkPos=true;
	}
	
	//Kollision des Schusses mit dem Spieler
	//Problem: er üerschreibt die methode aus Schuss nicht
	//sondern erstellt eine neue die natürlich dann bei bewegung nicht aufgerufen wird
	
	public static void Kollision(){

		if( (Schuss_Endgegner.StartX+31 >= Spielfeld.spieler.x)     && 
			(Schuss_Endgegner.StartX <= Spielfeld.spieler.x+31)  &&
			(Schuss_Endgegner.StartY+31 >= Spielfeld.spieler.y)  &&
			(Schuss_Endgegner.StartY <= Spielfeld.spieler.y+31)){		
			
			spieler.leben -= 1;
			
		}
}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
