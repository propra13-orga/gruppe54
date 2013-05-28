package propra2013.Gruppe54;
import java.awt.*;

import javax.swing.ImageIcon;

public class Block extends Rectangle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
 
    int ID,Zustand=0;				//0 - inaktiv, 1 - aktiv

	public int counter_gegner10=0;
	public int counter_gegner11=0;
	public int counter_gegner12=0;
	public static boolean Boss_vorhanden=false;

	
	/**
	 * Konstruktor
	 */
	public Block(int x, int y, int width, int height, int ID){
		setBounds(x,y,width,height);
		this.ID = ID;
	}
	
	//zeichnet den Block, Bild wird anhand der ID geladen
	public void draw(Graphics g){ 
		if(((ID>1)&&(ID<5))|(ID>5)&&(ID<8)|(ID==9)){      // ID größer als 1, d.h. elemente sind Fallen oder Items
			g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);  //boden zeichnen
			g.drawImage(Spielfeld.elemente[ID],x,y,width,height,null); //element zeichnen, damit hintergrund richtig angezeigt wird
		} else if(ID==5){ 	//Falle_speer, wird "einzeln behandelt", da diese zwei Zustände hat (aktiv,inaktiv)
			g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);  //boden zeichnen
			if(Zustand==0){
				g.drawImage(Spielfeld.elemente[ID],x,y,width,height,null);
			} else if(Zustand==1) {
				g.drawImage(new ImageIcon("pics/falle_speer"+Spielfeld.current_lvl+"_aktiv.png").getImage(),x,y,width,height,null);
			}
		} else if(ID==8){ 	
			g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);  //boden zeichnen
			if(Zustand==0){
				g.drawImage(Spielfeld.elemente[ID],x,y,width,height,null);
			} else if(Zustand==1) {
				g.drawImage(new ImageIcon("pics/brunnen_leer.png").getImage(),x,y,width,height,null);
			}
		} else if((ID==10)){		//Gegner 1
			g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);  //boden zeichnen
			
			
			/*der counter wird auf eins gesetz wenn ein gegner gezeichnet wurde und ab da
			 * soll er nur noch laufen und nicht ständig an den start punkt gezeichnet werden
				*/
			if(counter_gegner10==0){
			counter_gegner10=1;
			GegnerRL.StartX = x;
			GegnerRL.StartY = y;
			
			
			} else if (counter_gegner10==1){
				GegnerRL.lauf();
				Spielfeld.gegnerRL.draw(g);
			}
			
		} else if((ID==11)){		//Gegner 2 
		g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);  //boden zeichnen
		
		/*der counter wird auf eins gesetz wenn ein gegner gezeichnet wurde und ab da
		 * soll er nur noch laufen und nicht ständig an den start punkt gezeichnet werden
			*/
			if(counter_gegner11==0){	
			counter_gegner11=1;
			GegnerOU.StartX = x;
			GegnerOU.StartY = y;
			
		
			} else if (counter_gegner11==1){
				GegnerOU.lauf();
				Spielfeld.gegnerOU.draw(g);
			}
			
		} else if((ID==12)){		//Endgegner
			g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);  //boden zeichnen
			Boss_vorhanden=true;
			if(counter_gegner12==0){	
				counter_gegner12=1;
				Endgegner.StartX = x;
				Endgegner.StartY = y;
				

				} else if (counter_gegner12==1){
					Endgegner.lauf();
					Spielfeld.Boss.draw(g);
					//Spielfeld.gegnerKreis.draw(g);
				}

			
		} else if(ID==15){ 	  //Shopbesitzer, bei berührung soll gefragt werden ob man den Shop betreten möchte
			g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);  //boden zeichnen
			if(Spielfeld.shop){
				Zustand = 1;
			} else {
				Zustand = 0;
			}
			if(Zustand==0){
				g.drawImage(Spielfeld.elemente[ID],x,y,width,height,null);
				Spielerinfo.anzeige = false;
			} else if(Zustand==1) {
				Spielerinfo.anzeige = true;
				g.drawImage(new ImageIcon("pics/shopguy_aktiv.png").getImage(),x,y,width,height,null);
			}
		} else {
			g.drawImage(Spielfeld.elemente[ID],x,y,width,height,null);   //ImageArray elemente[], wird in Spielfeld.define() definiert
		 }
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
