package propra2013.Gruppe54;
import java.awt.Graphics;

public class Raum {

	public static int worldWidth=25;  //Breite Spielfelds
	public static int worldHeight=15; //Höhe Spielfelds
	public static int blockSize=32;   //Größe eines Blocks
	
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
				block[y][x] = new Block(x*blockSize,y*blockSize,blockSize,blockSize,0);	// Konstruktor Blöcke
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