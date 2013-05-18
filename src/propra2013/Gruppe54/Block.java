package propra2013.Gruppe54;
import java.awt.*;
import java.sql.Blob;

public class Block extends Rectangle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    int ID;
    public static GegnerRL gegnerRL;
	public static GegnerOU gegnerOU;
	public int counter_gegner10=0;
	public int counter_gegner11=0;

	
	/**
	 * Konstruktor
	 */
	public Block(int x, int y, int width, int height, int ID){
		setBounds(x,y,width,height);
		this.ID = ID;
	}
	
	//zeichnet den Block, Bild wird anhand der ID geladen
	public void draw(Graphics g){ 
		if((ID>1)&&(ID<=9)){      // ID größer als 1, d.h. elemente sind Fallen oder Items
			g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);  //boden zeichnen
			g.drawImage(Spielfeld.elemente[ID],x,y,width,height,null); //element zeichnen, damit hintergrund richtig angezeigt wird
		} else if((ID==10)){		//Gegner 1
			g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);  //boden zeichnen
			/*der counter wird auf eins gesetz wenn ein gegner gezeichnet wurde und ab da
			 * soll er nur noch laufen und nicht ständig an den start punkt gezeichnet werden
				*/
			if(counter_gegner10==0){
			gegnerRL = new GegnerRL();
			counter_gegner10=1;
			GegnerRL.StartX = x;
			GegnerRL.StartY = y;
			
			if((gegnerRL.aktiv)){
				gegnerRL.draw(g);  //zeichnet den Gegner
			}
			if(Raum.anzahl_gegner<3){
				Raum.anzahl_gegner++;
				}
			} else if (counter_gegner10==1){
				GegnerRL.lauf();
				gegnerRL.draw(g);
			}
			
			//Raum.Startpunkt_GegnerX[Raum.anzahl_gegner] = x;
			//Raum.Startpunkt_GegnerY[Raum.anzahl_gegner] = y;
			
			
		} else if((ID==11)){		//Gegner 2 
		g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);  //boden zeichnen
		
		/*der counter wird auf eins gesetz wenn ein gegner gezeichnet wurde und ab da
		 * soll er nur noch laufen und nicht ständig an den start punkt gezeichnet werden
			*/
			if(counter_gegner11==0){	
			gegnerOU = new GegnerOU();
			counter_gegner11=1;
			GegnerOU.StartX = x;
			GegnerOU.StartY = y;
			
			if((gegnerOU.aktiv)){
				gegnerOU.draw(g);  //zeichnet den Gegner
			}
			if(Raum.anzahl_gegner<3){
				Raum.anzahl_gegner++;
				}
			} else if (counter_gegner11==1){
				GegnerOU.lauf();
				gegnerOU.draw(g);
			}
			
			//Raum.Startpunkt_GegnerX[Raum.anzahl_gegner] = x;
			//Raum.Startpunkt_GegnerY[Raum.anzahl_gegner] = y;
				
		} else if((ID==12)){		//Gegner 3
			g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);  //boden zeichnen
			
			//Fehlt noch
			}
			
		 else {
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
