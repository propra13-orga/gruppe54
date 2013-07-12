package propra2013.Gruppe54;
import java.io.File;
import java.util.*;

import javax.swing.JOptionPane;

public class Level {
	
	/**
	 * Prüfen ob die einzulesende Level-Datei die richtige Größe hat
	 * @param loadPath, der Pfad zur Leveldatei
	 * @return true oder false
	 */
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
			return false;
		}
		if(zeichen == Raum.worldHeight*Raum.worldWidth){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Lädt die Leveldatei in das Array block[][]
	 * @param loadPath, der Pfad zur Leveldatei
	 */
	public void loadLevel(File loadPath){
		if(checkFile(loadPath)){
		try{
			Scanner loadScanner = new Scanner(loadPath);
			while(loadScanner.hasNext()){     //solange noch Zeichen vorhanden sind 
				for(int y=0;y<Raum.block.length;y++){  
					for(int x=0;x<Raum.block[0].length;x++){ 
						Raum.block[y][x].ID = Integer.parseInt(loadScanner.next(),16);
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
	
	public static void main(String[] args) {}

}
