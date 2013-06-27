package propra2013.Gruppe54;
import java.awt.*;

import javax.swing.ImageIcon;

public class Block extends Rectangle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    int ID,Zustand=0;				//0 - inaktiv, 1 - aktiv

	public boolean gegner1 = false,gegner2 = false,endgegner = false,falle = false,gegnerKI = false;	
									//damit die Gegner nur einmal auf dem initialisierten Block spawnen
									//und von da an ihre Wege gehen und nicht immer wieder auf dem Block neu gezeichnet werden
	
	/**
	 * Konstruktor
	 * @param x,y Koordinaten des Blocks
	 * @param width,height Blocksize
	 * @param ID - Art des Objekts das der Block darstellen soll
	 */
	public Block(int x, int y, int width, int height, int ID){
		setBounds(x,y,width,height);
		this.ID = ID;
		this.Zustand = 0;
	}
	
	/**
	 * zeichnet den Block, Bild wird anhand der ID geladen
	 */
	public void draw(Graphics g){ 
		if((ID==0)|(ID==2)|(ID==5)|(ID==8)|(ID==10)|(ID==18)|((ID>20)&&(ID<31))|((ID>=40))){      
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
			
		case 6://Schlüssel
			if(Zustand==0){
				g.drawImage(Spielfeld.elemente[0],x,y,width,height,null); 
				g.drawImage(Spielfeld.elemente[ID],x,y,width,height,null);
			} else if(Zustand==1) {
				g.drawImage(Spielfeld.elemente[0],x,y,width,height,null); 
			}
			break;
		
		case 7: //Loch
			g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);  //boden zeichnen
			if(Zustand==0){
				g.drawImage(Spielfeld.elemente[ID],x,y,width,height,null);
			} else if(Zustand==1) {
				g.drawImage(new ImageIcon("pics/falle_loch.png").getImage(),x,y,width,height,null);
			}
			break;
		
		case 9: //Falle_speer, wird "einzeln behandelt", da diese zwei Zustände hat (aktiv,inaktiv)
			g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);  //boden zeichnen
			if(Zustand==0){
				g.drawImage(Spielfeld.elemente[ID],x,y,width,height,null);
			} else if(Zustand==1) {
				g.drawImage(new ImageIcon("pics/falle_speer1_aktiv.png").getImage(),x,y,width,height,null);
			}
			break;
		
		case 11:
			g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);  //boden zeichnen
			if(Zustand==0){
				g.drawImage(Spielfeld.elemente[ID],x,y,width,height,null);
			} else if(Zustand==1) {
				g.drawImage(new ImageIcon("pics/falle_speer2_aktiv.png").getImage(),x,y,width,height,null);
			}
			break;
		
		case 12: 
			g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);  //boden zeichnen
			if(Zustand==0){
				g.drawImage(Spielfeld.elemente[ID],x,y,width,height,null);
			} else if(Zustand==1) {
				g.drawImage(new ImageIcon("pics/falle_speer3_aktiv.png").getImage(),x,y,width,height,null);
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
			
		case 15:	//brunnen lebensenergie
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
			
		case 17:	//brunnen mana
			g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);  //boden zeichnen
			if(Zustand==0){
				g.drawImage(Spielfeld.elemente[ID],x,y,width,height,null);
			} else if(Zustand==1) {
				g.drawImage(new ImageIcon("pics/brunnen_leer.png").getImage(),x,y,width,height,null);
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
		case 34:    //Quest NPC
			g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);  //boden zeichnen
			if(Spielerinfo.questnpc){		//wenn spieler daneben steht
				Zustand = 1;
			} else {
				Zustand = 0;
			}
			if(Zustand==0){
				g.drawImage(Spielfeld.elemente[ID],x,y,width,height,null);
				Spielerinfo.questnpc = false;
			} else if(Zustand==1) {
				Spielerinfo.questnpc = true;
				g.drawImage(Spielfeld.elemente[ID],x,y,width,height,null);
			}
			break;
			
		case 35:	//Gegner 1
			g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);  //boden zeichnen
			/*der counter wird auf eins gesetz wenn ein gegner gezeichnet wurde und ab da
			 * soll er nur noch laufen und nicht ständig an den start punkt gezeichnet werden
				*/
			Spielfeld.gegnerRL.aktiv=true;
			if((Spielfeld.gegnerRL.aktiv)&&(!gegner1)){
				gegner1 = true;
				Spielfeld.gegnerRL.StartX = x;
				Spielfeld.gegnerRL.StartY = y;
				Spielfeld.gegnerRL.leben = GegnerRL.StartLeben;
			} else if ((Spielfeld.gegnerRL.aktiv)&&(Spielfeld.gegnerRL.leben>0)&&(gegner1)){
				Spielfeld.gegnerRL.draw(g);
			}
			break;
			
		case 36:	//Gegner 2 
		g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);  //boden zeichnen
		/*der counter wird auf eins gesetz wenn ein gegner gezeichnet wurde und ab da
		 * soll er nur noch laufen und nicht ständig an den start punkt gezeichnet werden
			*/
			Spielfeld.gegnerOU.aktiv=true;
			if((Spielfeld.gegnerOU.aktiv)&&(!gegner2)){	
				gegner2 = true;
				Spielfeld.gegnerOU.StartX = x;
				Spielfeld.gegnerOU.StartY = y;
				Spielfeld.gegnerOU.leben = GegnerOU.StartLeben;
				Spielfeld.weg_verschlossen = true;
			} else if ((Spielfeld.gegnerOU.aktiv)&&(gegner2)&&(Spielfeld.gegnerOU.leben>0)){
				Spielfeld.gegnerOU.draw(g);
			}
			break;
			
		case 37:	//Endgegner
			g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);  //boden zeichnen
			Spielfeld.schuss_endgegner.aktiv=true;
			Endgegner.aktiv=true;
			if((!endgegner)&&(Endgegner.leben>0)&&(Endgegner.aktiv)){	
			endgegner = true;
			Endgegner.StartX = x;
			Endgegner.StartY = y;
			} else if ((Endgegner.leben>0)&&(endgegner)&&(Endgegner.aktiv)){
				Spielfeld.Boss.draw(g);
			}
			break;
			
		case 38:	//bewegliche Falle
			g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);  //boden zeichnen
			/*der counter wird auf eins gesetz wenn ein gegner gezeichnet wurde und ab da
			 * soll er nur noch laufen und nicht ständig an den start punkt gezeichnet werden
				*/
			Falle.aktiv=true;
				if((Falle.aktiv)&&(!falle)){	
				falle = true;
				Falle.StartX = x;
				Falle.StartY = y;
				Falle.StartPunktX=x;
				Falle.StartPunktY=y;
				} else if((falle)&&(Falle.aktiv)){
					Spielfeld.falle.draw(g);
				}
			break;
			
		case 39:	//GegnerKI
			g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);  //boden zeichnen
			Spielfeld.gegnerKI.aktiv=true;
			if((!gegnerKI)&&(Spielfeld.gegnerKI.leben>0)&&(Spielfeld.gegnerKI.aktiv)){	
			gegnerKI = true;
			Spielfeld.gegnerKI.StartX = x;
			Spielfeld.gegnerKI.StartY = y;
			} else if((Spielfeld.gegnerKI.leben>0)&&(gegnerKI)){
				Spielfeld.gegnerKI.draw(g);
			}
			break;
		
		//verschlossener Weg, geht auf wenn man Gegner2 besiegt hat, ist also so konzipiert auch nur auf diesen zu reagieren
		case 51:
			if(Spielfeld.weg_verschlossen){
				g.drawImage(Spielfeld.elemente[1],x,y,width,height,null); //Mauer
			} else if(Spielfeld.weg_verschlossen == false){
				ID = 0; //Boden
			}
			break;
		
		case 54: //Gold2
			if(Zustand==0){
				g.drawImage(Spielfeld.elemente[0],x,y,width,height,null); 
				g.drawImage(Spielfeld.elemente[ID],x,y,width,height,null);
			} else if(Zustand==1) {
				g.drawImage(Spielfeld.elemente[0],x,y,width,height,null);
			}
			break;
			
		case 55: //Pfeile
			if(Zustand==0){
				g.drawImage(Spielfeld.elemente[0],x,y,width,height,null); 
				g.drawImage(Spielfeld.elemente[ID],x,y,width,height,null);
			} else if(Zustand==1) {
				g.drawImage(Spielfeld.elemente[0],x,y,width,height,null); 
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
