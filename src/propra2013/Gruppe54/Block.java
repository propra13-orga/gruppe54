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
	public int counter_gegner40=0;
	public int counter_falle=0;
	
	/**
	 * Konstruktor
	 */
	public Block(int x, int y, int width, int height, int ID){
		setBounds(x,y,width,height);
		this.ID = ID;
		this.Zustand = 0;
	}
	
	//zeichnet den Block, Bild wird anhand der ID geladen
	public void draw(Graphics g){ 
		if((ID==0)|(ID==2)|(ID==5)|(ID==7)|(ID==8)|(ID==10)|(ID==18)|((ID>20)&&(ID<31))|((ID>=40))){      
			g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);  //boden zeichnen
			g.drawImage(Spielfeld.elemente[ID],x,y,width,height,null); //element zeichnen, damit hintergrund richtig angezeigt wird
		} 
		switch(ID){
		case 2:
			g.drawImage(Spielfeld.elemente[1],x,y,width,height,null); 
			g.drawImage(Spielfeld.elemente[ID],x,y,width,height,null);
			break;
		
		case 3:
			g.drawImage(Spielfeld.elemente[ID],x,y,width,height,null);
			break;
				
		case 9: //Falle_speer, wird "einzeln behandelt", da diese zwei Zustände hat (aktiv,inaktiv)
			g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);  //boden zeichnen
			if(Zustand==0){
				g.drawImage(Spielfeld.elemente[ID],x,y,width,height,null);
			} else if(Zustand==1) {
				g.drawImage(new ImageIcon("pics/falle_speer"+Spielfeld.current_lvl+"_aktiv.png").getImage(),x,y,width,height,null);
			}
			break;
		
		case 13://Lebenstrank
			if(Zustand==0){
				g.drawImage(Spielfeld.elemente[0],x,y,width,height,null); 
				g.drawImage(Spielfeld.elemente[ID],x,y,width,height,null);
			} else if(Zustand==1) {
				g.drawImage(Spielfeld.elemente[0],x,y,width,height,null); 
			}
			break;
		
		case 14://Manatrank
			if(Zustand==0){
				g.drawImage(Spielfeld.elemente[0],x,y,width,height,null); 
				g.drawImage(Spielfeld.elemente[ID],x,y,width,height,null);
			} else if(Zustand==1) {
				g.drawImage(Spielfeld.elemente[0],x,y,width,height,null); 
			}
			break;
			
		case 15:	//brunnen 	
			g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);  //boden zeichnen
			if(Zustand==0){
				g.drawImage(Spielfeld.elemente[ID],x,y,width,height,null);
			} else if(Zustand==1) {
				g.drawImage(new ImageIcon("pics/brunnen_leer.png").getImage(),x,y,width,height,null);
			}
			break;
			
		case 16://Supertrank
			if(Zustand==0){
				g.drawImage(Spielfeld.elemente[0],x,y,width,height,null); 
				g.drawImage(Spielfeld.elemente[ID],x,y,width,height,null);
			} else if(Zustand==1) {
				g.drawImage(Spielfeld.elemente[0],x,y,width,height,null); 
			}
			break;
			
		case 19://Checkpoint
			if(Zustand==0){
				g.drawImage(Spielfeld.elemente[0],x,y,width,height,null); 
				g.drawImage(Spielfeld.elemente[ID],x,y,width,height,null);
			} else if(Zustand==1) {
				g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);
				g.drawImage(new ImageIcon("pics/checkpoint_aktiv.png").getImage(),x,y,width,height,null);
			}
			break;
		
		case 20:    //NPC
			g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);  //boden zeichnen
			if(Spielerinfo.npc){		//wenn spieler daneben steht
				Zustand = 1;
			} else {
				Zustand = 0;
			}
			if(Zustand==0){
				g.drawImage(Spielfeld.elemente[ID],x,y,width,height,null);
				Spielerinfo.npc = false;
			} else if(Zustand==1) {
				Spielerinfo.npc = true;
				g.drawImage(new ImageIcon("pics/npc_aktiv.png").getImage(),x,y,width,height,null);
			}
			break;
			
		case 31: //Schatztruhe
			g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);  //boden zeichnen
			if(Zustand==0){
				g.drawImage(Spielfeld.elemente[ID],x,y,width,height,null);
			} else if(Zustand==1) {
				g.drawImage(new ImageIcon("pics/truhe_auf.png").getImage(),x,y,width,height,null);
			}
			break;
		
		case 32: //Gold
			if(Zustand==0){
				g.drawImage(Spielfeld.elemente[0],x,y,width,height,null); 
				g.drawImage(Spielfeld.elemente[ID],x,y,width,height,null);
			} else if(Zustand==1) {
				g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);
			}
			break;
		
		case 33://Herz
			if(Zustand==0){
				g.drawImage(Spielfeld.elemente[0],x,y,width,height,null); 
				g.drawImage(Spielfeld.elemente[ID],x,y,width,height,null);
			} else if(Zustand==1) {
				g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);
			}
			break;
			
		case 35:	//Gegner 1
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
				Spielfeld.gegnerRL.draw(g);
			}
			break;
			
		case 36:	//Gegner 2 
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
				Spielfeld.gegnerOU.draw(g);
			}
			break;
			
		case 37:	//Endgegner
			g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);  //boden zeichnen
			Schuss_Endgegner.aktiv=true;
			Endgegner.aktiv=true;
			if((counter_gegner12==0)&&(Endgegner.leben>0)&&(Endgegner.aktiv)){	
			counter_gegner12=1;
			Endgegner.StartX = x;
			Endgegner.StartY = y;
			} else if ((Endgegner.leben>0)&&(counter_gegner12==1)&&(Endgegner.aktiv)){
				Spielfeld.Boss.draw(g);
			}
			break;
			
		case 38:	//bewegliche Falle
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
				} else if((counter_falle==1)&&(Falle.aktiv)){
					Spielfeld.falle.draw(g);
				}
			break;
			
		case 39:	//GegnerKI
			g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);  //boden zeichnen
			GegnerKI.aktiv=true;
			if((counter_gegner40==0)&&(GegnerKI.leben>0)&&(GegnerKI.aktiv)){	
			counter_gegner40=1;
			GegnerKI.StartX = x;
			GegnerKI.StartY = y;
			} else if((GegnerKI.leben>0)&&(counter_gegner40==1)){
				Spielfeld.gegnerKI.draw(g);
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
