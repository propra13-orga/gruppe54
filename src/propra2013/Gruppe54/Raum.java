package propra2013.Gruppe54;
import java.awt.Graphics;
import java.awt.Point;

public class Raum {

	public static int worldWidth=25;  //Breite Spielfelds
	public static int worldHeight=15; //Höhe Spielfelds
	public static int blockSize=32;   //Größe eines Blocks
	
	public static Point[] Startpunkt = {new Point(5,185),new Point(5,277),new Point(675,435)};
	public static int anzahl_gegner=0;
	
	
	public static Block[][] block; 
	
	/**
	 * Konstruktor
	 */
	public Raum(){
		define();
	}
	
	//initialisiert die Blöcke und ordnet das Gitter an, oben links ist Block[0,0]
	public void define(){           
		block = new Block[worldHeight][worldWidth];
		
		for(int y=0;y<block.length;y++){
			for(int x=0;x<worldWidth;x++){
				block[y][x] = new Block(x*blockSize,y*blockSize,blockSize,blockSize,0);	// Konstruktor
			}
		}
	}
	
	public void draw(Graphics g){
		for(int y=0;y<block.length;y++){
			for(int x=0;x<worldWidth;x++){
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
