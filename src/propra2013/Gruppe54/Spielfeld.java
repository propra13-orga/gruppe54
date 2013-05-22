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
	
	public static Image[] elemente = new Image[12];
	
	public static int current_lvl=1,current_room=1,current_player=1;
	
	public static boolean isFirst = true; //erster Aufruf
	
	public static Raum raum = new Raum();
	public static level level = new level();
	public static spieler spieler;

	
	/**
	 * Konstruktor
	 */
	public Spielfeld(){
		setBounds(75,55,800,480);
		thread.start();
	}
	
	//Bilder in Array laden     
		//	ID: 0 - Boden   1 - Mauer   2 - Ausgang 
		//      3 - Falle_Loch   4 - Falle_Feuer   5 - Falle_Speer  
	    //      7 - Item_Trank   8 - Item_Trank2
    
	public static void loadImages(){
		elemente[0] = new ImageIcon("pics/boden"+current_lvl+".png").getImage(); 
		elemente[1] = new ImageIcon("pics/mauer"+current_lvl+".png").getImage(); 
		elemente[2] = new ImageIcon("pics/ausgang.png").getImage();
		elemente[3] = new ImageIcon("pics/falle_loch.png").getImage();
		elemente[4] = new ImageIcon("pics/falle_feuer"+current_lvl+".png").getImage();
		elemente[5] = new ImageIcon("pics/falle_speer"+current_lvl+".png").getImage();
		elemente[6] = new ImageIcon("pics/anim.gif").getImage();
		elemente[7] = new ImageIcon("pics/item_trank.png").getImage();
		elemente[8] = new ImageIcon("pics/item_trank2.png").getImage();
		elemente[9] = new ImageIcon("pics/zepter"+current_lvl+".png").getImage();
	}
	
	public void define(){
		raum = new Raum();
		spieler = new spieler();

		
		Frame.leben.setText("Leben:   "+spieler.leben+"%");
		
		loadImages();

		level.loadLevel(new File("level/level"+current_lvl+"_"+current_room+".lvl"));   //level-datei laden
	}
	
	public void paintComponent(Graphics g){
		if(isFirst){ //Erstinitialisierung
			define();
			isFirst=false;
		}
		raum.draw(g); //zeichnet den raum

	
		if(spieler.aktiv){
			spieler.draw(g);  //zeichnet den Spieler
		}
	}
	
	//Thread
	public void run(){
		while(true){
			validate();
			repaint();

			if((spieler.leben <= 0)&&(spieler.aktiv)){
				spieler.aktiv = false;
				Frame.neustart.setVisible(true);
			} if((spieler.leben > 30)&&(spieler.aktiv)) {
				Frame.lebensanzeige = new ImageIcon("pics/lebensanzeige.png");
				Frame.leben.setSize(spieler.leben*2, 10);
				Frame.leben.setIcon(Frame.lebensanzeige);
			} else {
				Frame.lebensanzeige = new ImageIcon("pics/lebensanzeige_rot.png");
				Frame.leben.setSize(spieler.leben*2, 10);
				Frame.leben.setIcon(Frame.lebensanzeige);
			}
			
			if((getBlockID(spieler.x+6+Frame.dx,spieler.y+26+Frame.dy)!=1)&&(getBlockID(spieler.x+26+Frame.dx,spieler.y+26+Frame.dy)!=1)
					&&(getBlockID(spieler.x+6+Frame.dx,spieler.y+34+Frame.dy)!=1)&&(getBlockID(spieler.x+26+Frame.dx,spieler.y+34+Frame.dy)!=1)){
				
				Elemente.Aufruf(getBlockID(spieler.x+6+Frame.dx, spieler.y+26+Frame.dy),getBlock(spieler.x+6+Frame.dx, spieler.y+26+Frame.dy));
		        if(Elemente.beruehrung == false){ //zweiten Punkt prüfen
		        	Elemente.Aufruf(getBlockID(spieler.x+26+Frame.dx,spieler.y+26+Frame.dy),getBlock(spieler.x+26+Frame.dx,spieler.y+26+Frame.dy));
		        } else if(Elemente.beruehrung == false){ //zweiten Punkt prüfen
		        	Elemente.Aufruf(getBlockID(spieler.x+6+Frame.dx,spieler.y+34+Frame.dy),getBlock(spieler.x+6+Frame.dx,spieler.y+34+Frame.dy));
		        } else if(Elemente.beruehrung == false){ //zweiten Punkt prüfen
		        	Elemente.Aufruf(getBlockID(spieler.x+26+Frame.dx,spieler.y+34+Frame.dy),getBlock(spieler.x+26+Frame.dx,spieler.y+34+Frame.dy));
		        }
				spieler.x += Frame.dx;
				spieler.y += Frame.dy;
				Elemente.beruehrung = false;
			}
			
			try{
				Thread.sleep(5);
			} catch(Exception e){ 
				e.printStackTrace();
			}
		}
	}
	
	//liefert die ID des Blocks bei gegebenen x,y Koordinaten eines Punktes auf dem Spielfeld
	public static int getBlockID(double x,double y){
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
		
	//liefert den Block bei gegebenen x,y Koordinaten eines Punktes auf dem Spielfeld
	public static Block getBlock(double x,double y){
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
		return Raum.block[i][j];
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
