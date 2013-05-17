package propra2013.Gruppe54;
import java.io.File;
import java.util.*;

public class level {
	
	public void loadLevel(File loadPath){
		
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
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
