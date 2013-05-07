package propra2013.Gruppe54;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Spielfeld extends JPanel implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Thread thread = new Thread(this);
	
	public static Image[] elemente = new Image[5];
	
	public static int current_lvl=1,current_room=1;
	
	public static boolean isFirst = true; //erster Aufruf
	
	public static Raum raum = new Raum();
	public static level level = new level();
	
	/**
	 * Konstruktor
	 */
	public Spielfeld(){
		setBounds(75,55,800,800);
		thread.start();
	}
	
	//liefert die ID des Blocks bei gegebenen x,y Koordinaten eines Punktes auf dem Spielfeld
	public static int getBlockID(int x,int y){
		int i,j;
		for(i=0;i<Raum.worldHeight;i++){
			if((y>=i*Raum.blockSize)&(y<=(i+1)*Raum.blockSize)){
				break;
			}
		}
		for(j=0;j<Raum.worldWidth;j++){
			if((x>=j*Raum.blockSize)&(x<=(j+1)*Raum.blockSize)){
				break;
			}
		}
		return Raum.block[i][j].ID;
	}
	
	//Bilder in Array laden     ID: 0 - Boden   1 - Mauer 
	public void define(){
		raum = new Raum();
		elemente[0] = new ImageIcon("pics/boden.png").getImage(); 
		elemente[1] = new ImageIcon("pics/mauer.png").getImage(); 
		level.loadLevel(new File("level/level"+current_lvl+"_"+current_room+".lvl"));   //level-datei laden
	}
	
	public void paintComponent(Graphics g){
		if(isFirst){ //Erstinitialisierung
			define();
			isFirst=false;
		}
		raum.draw(g); //zeichnet den raum
	}
	
	public void run(){
		while(true){
			
			repaint();
			
			try{
				Thread.sleep(1);
			} catch(Exception e){ 
				e.printStackTrace();
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
