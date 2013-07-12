package propra2013.Gruppe54;
import java.awt.Graphics;
import java.awt.Point;

public class Raum {

	public static final int worldWidth=30;  //Breite Spielfelds
	public static final int worldHeight=16; //Höhe Spielfelds
	public static final int blockSize=32;   //Größe eines Blocks
	
	public static Point[] Startpunkt = {new Point(5,185),new Point(5,277),new Point(675,475),new Point(5,280)};
	public static int anzahl_gegner=0;
	
	public static Block[][] block; 
	
	/**
	 * Konstruktor
	 */
	public Raum(){
		define();
	}
	
	/**
	 * initialisiert die Blöcke und ordnet das Gitter an, oben links ist Block[0,0]
	 */
	public void define(){           
		block = new Block[worldHeight][worldWidth];
		
		for(int y=0;y<worldHeight;y++){
			for(int x=0;x<worldWidth;x++){
				block[y][x] = new Block(x*blockSize,y*blockSize,blockSize,blockSize,0);	// Konstruktor
			}
		}
	}
	/**
	 * Zeichnet die Blöcke
	 * @param g
	 */
	public void draw(Graphics g){
		for(int y=0;y<worldHeight;y++){
			for(int x=0;x<worldWidth;x++){
				block[y][x].draw(g);	//zeichnet die Blöcke anhand der ID
			}
		}
	}
	public static void main(String[] args) {}
}
