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
	public int counter_falle=0;
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
		if(((ID>1)&&(ID<5))|(ID>5)&&(ID<7)|(ID==14)|((ID>15)&&(ID<22)|(ID>22))){      
			g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);  //boden zeichnen
			g.drawImage(Spielfeld.elemente[ID],x,y,width,height,null); //element zeichnen, damit hintergrund richtig angezeigt wird
		} 
		switch(ID){
		
		case 5: //Falle_speer, wird "einzeln behandelt", da diese zwei Zustände hat (aktiv,inaktiv)
			g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);  //boden zeichnen
			if(Zustand==0){
				g.drawImage(Spielfeld.elemente[ID],x,y,width,height,null);
			} else if(Zustand==1) {
				g.drawImage(new ImageIcon("pics/falle_speer"+Spielfeld.current_lvl+"_aktiv.png").getImage(),x,y,width,height,null);
			}
			break;
		
		case 7://Lebenstrank
			if(Zustand==0){
				g.drawImage(Spielfeld.elemente[0],x,y,width,height,null); 
				g.drawImage(Spielfeld.elemente[ID],x,y,width,height,null);
			} else if(Zustand==1) {
				g.drawImage(Spielfeld.elemente[0],x,y,width,height,null); 
			}
			break;
		
		case 8://Manatrank
			if(Zustand==0){
				g.drawImage(Spielfeld.elemente[0],x,y,width,height,null); 
				g.drawImage(Spielfeld.elemente[ID],x,y,width,height,null);
			} else if(Zustand==1) {
				g.drawImage(Spielfeld.elemente[0],x,y,width,height,null); 
			}
			break;
			
		case 9:	//brunnen 	
			g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);  //boden zeichnen
			if(Zustand==0){
				g.drawImage(Spielfeld.elemente[ID],x,y,width,height,null);
			} else if(Zustand==1) {
				g.drawImage(new ImageIcon("pics/brunnen_leer.png").getImage(),x,y,width,height,null);
			}
			break;
			
		case 10:	//Gegner 1
			g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);  //boden zeichnen
			/*der counter wird auf eins gesetz wenn ein gegner gezeichnet wurde und ab da
			 * soll er nur noch laufen und nicht ständig an den start punkt gezeichnet werden
				*/
			GegnerRL.aktiv=true;
			if((GegnerRL.aktiv)&&(counter_gegner10==0)&&(GegnerRL.leben>0)){
			counter_gegner10=1;
			GegnerRL.StartX = x;
			GegnerRL.StartY = y;
			} else if ((GegnerRL.aktiv)&&(GegnerRL.leben>0)&&(counter_gegner10==1)){
				GegnerRL.lauf();
				Spielfeld.gegnerRL.draw(g);
			}
			break;
			
		case 11:	//Gegner 2 
		g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);  //boden zeichnen
		/*der counter wird auf eins gesetz wenn ein gegner gezeichnet wurde und ab da
		 * soll er nur noch laufen und nicht ständig an den start punkt gezeichnet werden
			*/
		GegnerOU.aktiv=true;
			if((GegnerOU.aktiv)&&(GegnerOU.leben>0)&&(counter_gegner11==0)){	
			counter_gegner11=1;
			GegnerOU.StartX = x;
			GegnerOU.StartY = y;
			} else if ((GegnerOU.aktiv)&&(counter_gegner11==1)&&(GegnerOU.leben>0)){
				GegnerOU.lauf();
				Spielfeld.gegnerOU.draw(g);
			}
			break;
			
		case 12:	//Endgegner
			g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);  //boden zeichnen
			Schuss_Endgegner.aktiv=true;
			Boss_vorhanden=true;
			if((counter_gegner12==0)&&(Endgegner.leben>0)){	
			counter_gegner12=1;
			Endgegner.StartX = x;
			Endgegner.StartY = y;
			} else if ((Endgegner.leben>0)&&(counter_gegner12==1)){
				Endgegner.lauf();
				Spielfeld.Boss.draw(g);
			}
			break;
			
		case 15:    //Shopbesitzer, bei berührung soll gefragt werden ob man den Shop betreten möchte
			g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);  //boden zeichnen
			if(Spielfeld.shop){		//wenn spieler daneben steht
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
			break;
			
		case 22:
			//leer, ID 22 soll nichts anzeigen
			//zur "verkleinerung" des Shops
			break;
			
		case 23: //Schatztruhe
			g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);  //boden zeichnen
			if(Zustand==0){
				g.drawImage(Spielfeld.elemente[ID],x,y,width,height,null);
			} else if(Zustand==1) {
				g.drawImage(new ImageIcon("pics/truhe_auf.png").getImage(),x,y,width,height,null);
			}
			break;
		
		case 24: //Gold
			if(Zustand==0){
				g.drawImage(Spielfeld.elemente[0],x,y,width,height,null); 
				g.drawImage(Spielfeld.elemente[ID],x,y,width,height,null);
			} else if(Zustand==1) {
				g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);
			}
			break;
		case 28:	//bewegliche Falle
			g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);  //boden zeichnen
			/*der counter wird auf eins gesetz wenn ein gegner gezeichnet wurde und ab da
			 * soll er nur noch laufen und nicht ständig an den start punkt gezeichnet werden
				*/
			Falle.aktiv=true;
				if((Falle.aktiv)&&(counter_falle==0)){	
				counter_falle=1;
				Falle.StartX = x;
				Falle.StartY = y;
				Falle.StartPunktX=x;
				Falle.StartPunktY=y;
				} else if ((counter_falle==1)&&(Falle.aktiv)){
					Falle.bewegung();
					Spielfeld.falle.draw(g);
				}
				break;
			
		default:
			g.drawImage(Spielfeld.elemente[ID],x,y,width,height,null);   //ImageArray elemente[], wird in Spielfeld.define() definiert
			break;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
