package propra2013.Gruppe54.Leveleditor;
import java.awt.Graphics;
import java.awt.Point;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import propra2013.Gruppe54.Spielfeld;

public class Raum {

	public static int worldWidth=30;  //Breite Spielfelds
	public static int worldHeight=16; //Höhe Spielfelds
	public static int blockSize=32;   //Größe eines Blocks
	
	public static Point[] Startpunkt = {new Point(5,185),new Point(5,277),new Point(675,435),new Point(5,280)};
	public static int anzahl_gegner=0;
	
	public static EditorBlock[][] block; 
	public FileWriter writer;
	
	/**
	 * Konstruktor
	 */
	public Raum(){
		define();
	}
	
	//initialisiert die Blöcke und ordnet das Gitter an, oben links ist Block[0,0]
	public void define(){           
		block = new EditorBlock[worldHeight][worldWidth];
		
		for(int y=0;y<worldHeight;y++){
			for(int x=0;x<worldWidth;x++){
				block[y][x] = new EditorBlock(x*blockSize,y*blockSize,blockSize,blockSize,50);	// Konstruktor
			}
		}
	}
	
	//zeichnet die Blöcke anhand der ID
	public void draw(Graphics g){
		for(int y=0;y<worldHeight;y++){
			for(int x=0;x<worldWidth;x++){
				block[y][x].draw(g);
			}
		}
	}
	
	public void saveLevel(){
		File datei = new File("level/level"+Editorfeld.current_lvl+"_"+Editorfeld.current_room+".lvl");
		try {
			writer = new FileWriter(datei);
			for(int y=0;y<Raum.worldHeight;y++){
				for(int x=0;x<Raum.worldWidth;x++){
					writer.write(Integer.toHexString(block[y][x].ID)+" ");
				}
				writer.write(System.getProperty("line.separator"));
			}
			writer.flush(); //Stream in Datei schreiben
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//lädt die Textdatei aus, interpretiert die eingelesene Zahl als Hexadezimal und parsed den Wert zu Integer
	//eingelesene IDs werden in Array geladen
	public void loadLevel(File loadPath){
		if(checkFile(loadPath)){
		try{
			Scanner loadScanner = new Scanner(loadPath);
			while(loadScanner.hasNext()){     //solange noch Zeichen vorhanden sind 
				
				for(int y=0;y<block.length;y++){  
					for(int x=0;x<Raum.block[0].length;x++){ 
						block[y][x].ID = Integer.parseInt(loadScanner.next(),16);
					}
				}
				
			}
			loadScanner.close();
		} catch(Exception e) { 
			e.printStackTrace();
		}
		} else if(checkFile(loadPath) == false) {
			JOptionPane.showMessageDialog(null, "Es ist leider ein Fehler beim einlesen der Datei zu Level "+Spielfeld.current_lvl+", Raum "+Spielfeld.current_room+" aufgetreten.\nDas Programm schließt sich nun, versuche es dann einfach nochmal.","",JOptionPane.PLAIN_MESSAGE, null);
			System.exit(0);
		}
	}
	//prüfen ob die einzulesende Level-Datei die richtige Größe hat
	public boolean checkFile(File loadPath){
		int zeichen = 0;
		try{
			Scanner loadScanner = new Scanner(loadPath);
			while(loadScanner.hasNext()){     //solange noch Zeichen vorhanden sind 
				zeichen ++;
				loadScanner.next();
			}
			loadScanner.close();
		} catch(Exception e) { 
			e.printStackTrace();
		}
		if(zeichen == Raum.worldHeight*Raum.worldWidth){
			return true;
		} else {
			return false;
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
