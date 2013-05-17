package propra2013.Gruppe54;
import java.awt.*;
import java.sql.Blob;

public class Block extends Rectangle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    int ID;
	
	/**
	 * Konstruktor
	 */
	public Block(int x, int y, int width, int height, int ID){
		setBounds(x,y,width,height);
		this.ID = ID;
	}
	
	//zeichnet den Block, Bild wird anhand der ID geladen
	public void draw(Graphics g){ 
		if((ID>1)&&(ID<8)){      // ID größer als 1, d.h. elemente sind Fallen oder Items
			g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);  //boden zeichnen
			g.drawImage(Spielfeld.elemente[ID],x,y,width,height,null); //element zeichnen, damit hintergrund richtig angezeigt wird
		} else if(ID==10){		//Gegner 1
			g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);  //boden zeichnen
			
			Raum.Startpunkt_GegnerX[Raum.anzahl_gegner] = x;
			Raum.Startpunkt_GegnerY[Raum.anzahl_gegner] = y;
			
			if(Raum.anzahl_gegner<3){
			Raum.anzahl_gegner++;
			}
			
		} else if(ID==11){		//Gegner 2
			g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);  //boden zeichnen
			
			Raum.Startpunkt_GegnerX[Raum.anzahl_gegner] = x;
			Raum.Startpunkt_GegnerY[Raum.anzahl_gegner] = y;

			if(Raum.anzahl_gegner<3){
			Raum.anzahl_gegner++;
			}		
		} else if(ID==12){		//Gegner 3
			g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);  //boden zeichnen
			
			Raum.Startpunkt_GegnerX[Raum.anzahl_gegner] = x;
			Raum.Startpunkt_GegnerY[Raum.anzahl_gegner] = y;

			if(Raum.anzahl_gegner<3){
			Raum.anzahl_gegner++;
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
