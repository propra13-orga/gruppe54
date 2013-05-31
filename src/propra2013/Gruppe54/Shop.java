package propra2013.Gruppe54;
import java.awt.Graphics;
import java.awt.Point;

public class Shop {

	public static int shopWidth=10;  //Breite Spielfelds
	public static int shopHeight=10; //Höhe Spielfelds
	
	public static Point Startpunkt = new Point(5,185);
	
	
	public static Block[][] block; 
	
	/**
	 * Konstruktor
	 */
	public Shop(){
		define();
	}
	
	//initialisiert die Blöcke und ordnet das Gitter an, oben links ist Block[0,0]
	public void define(){           
		block = new Block[shopHeight][shopWidth];
		
		for(int y=0;y<block.length;y++){
			for(int x=0;x<shopWidth;x++){
				block[y][x] = new Block(x*Raum.blockSize,y*Raum.blockSize,Raum.blockSize,Raum.blockSize,0);	// Konstruktor
			}
		}
	}
	
	public void draw(Graphics g){
		for(int y=0;y<block.length;y++){
			for(int x=0;x<shopWidth;x++){
				block[y][x].draw(g);	//zeichnet jeden Block
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
