package propra2013.Gruppe54;

import java.awt.Point;
import java.io.File;

import javax.swing.JOptionPane;

public class Elemente {
	
	public static boolean beruehrung = false;
	public static int feuer = 0,speer = 0; //es soll nicht jeder Schritt über das Feuer Schaden geben, es soll aber Schaden genommen werden wenn der Spieler stehen bleibt

	public static void Aufruf(int ID,Block block){
		switch(ID){
		
		case 5://Ausgang
			if(Spielfeld.current_room!=3) {  
				if(Schuss_Spieler.sichtbar){
					Schuss_Spieler.sichtbar = false;
				}
				
				Falle.aktiv=false;
				GegnerKI.leben=0;
				Endgegner.aktiv=false;
				Schuss_Endgegner.aktiv=false;
				GegnerKI.aktiv=false;
				Spielfeld.current_room+=1;
				Spielfeld.level.loadLevel(new File("level/level"+Spielfeld.current_lvl+"_"+Spielfeld.current_room+".lvl"));
				//Spieler auf den Startpunkt des jeweiligen Levels setzen
				Spielfeld.spieler.x = Raum.Startpunkt[Spielfeld.current_lvl-1].x;
				Spielfeld.spieler.y = Raum.Startpunkt[Spielfeld.current_lvl-1].y;
				GegnerOU.leben=GegnerOU.StartLeben;
				GegnerRL.leben=GegnerRL.StartLeben;
				Spielfeld.GegnerRL_counter = 0;
				Spielfeld.GegnerOU_counter = 0;
				Spielfeld.Endgegner_counter = 0;
				Endgegner.leben=Endgegner.StartLeben;
				GegnerKI.leben=GegnerKI.StartLeben;
				GegnerKI.StartX=0;
				GegnerKI.StartY=0;
				Falle.StartX=0;
				Falle.StartY=0;
			}
			break;
			
		case 7://Falle_Loch - Spieler soll auf den Startpunkt zurück fallen
			Spielfeld.spieler.x = Raum.Startpunkt[Spielfeld.current_lvl-1].x;
			Spielfeld.spieler.y = Raum.Startpunkt[Spielfeld.current_lvl-1].y;
			break;
		
		case 8://Falle_Feuer
			feuer++;
			if(feuer==5){
				beruehrung = true;		  //da z.B. beim hochlaufen zwei Punkte auf Berührung überprüft werden, soll gespeichert werden,
				if(spieler.ruestung<=0){  //ob bereits eine Falle ausgelöst wurde damit die Punkte nicht doppelt abgezogen werden
					spieler.leben-=1;
				} else {
					spieler.ruestung-=1;
				}		
				feuer = 0;
			}
			break;
		
		case 9://Falle_Speer
			speer++;
			if(speer==5){
				beruehrung = true;
				if(spieler.ruestung<=0){
					spieler.leben-=2;
				} else {
					spieler.ruestung-=2;
				}
				block.Zustand = 1;	//lädt das aktive Bild der Falle
				speer = 0;
			}
			break;
			
		case 10://Falle_Monster
			beruehrung = true;
			if(spieler.ruestung<=0){
				spieler.leben-=5;
			} else {
				spieler.ruestung-=5;
			}
			if((Spielfeld.spieler.rechts)&&(Spielfeld.check((int)Spielfeld.spieler.x-12+32))){
				Spielfeld.spieler.x-=12;
			} else if((Spielfeld.spieler.links)&&(Spielfeld.check((int)Spielfeld.spieler.x+12+32))){
				Spielfeld.spieler.x+=12;
			} else if((Spielfeld.spieler.hoch)&&(Spielfeld.check((int)Spielfeld.spieler.y+12+32))){
				Spielfeld.spieler.y+=12;
			} else if((Spielfeld.spieler.runter)&&(Spielfeld.check((int)Spielfeld.spieler.y-12+32))){
				Spielfeld.spieler.y-=12;
			}
			break;
		
		case 13://Lebenstrank
			if(block.Zustand==0){
			if(spieler.leben == 100){
				Spielfeld.spieler.item_trank += 1;
			} else {
				spieler.leben+=40;
				if(spieler.leben>100){
					spieler.leben = 100;
				}
			}
			block.Zustand = 1;
			}
			break;
		
		case 14://Manatrank
			if(block.Zustand==0){
			if(spieler.mana == 100){
				Spielfeld.spieler.item_mana += 1;
			} else {
				spieler.mana+=40;
				if(spieler.mana>100){
					spieler.mana = 100;
				}
			}
			block.Zustand = 1;
			}
			break;
			
		case 15://brunnen
			if((spieler.leben<100)&&(block.Zustand==0)){	
				spieler.leben = 100;
				if(spieler.leben>100){
					spieler.leben = 100;
				}
				block.Zustand = 1;
			}
			break;
		
		case 16://Supertrank
			if(block.Zustand==0){
			if((spieler.mana == 100)&&(spieler.leben == 100)){
				Spielfeld.spieler.item_supertrank += 1;
			} else {
				spieler.mana+=40;
				if(spieler.mana>100){
					spieler.mana = 100;
				}
				spieler.leben += 40;
				if(spieler.leben>100){
					spieler.leben = 100;
				}
			}
			block.Zustand = 1;
			}
			break;
			
		case 18://zepter
			if(Endgegner.leben<=0){
			Spielfeld.spieler.beweglich = false;
			block.ID = 0;
			if(Spielfeld.current_lvl<4){
				Frame.nextLevel.setVisible(true);
				Frame.neustart.setVisible(true);
			} else if(Spielfeld.current_lvl==4){
				JOptionPane.showMessageDialog(null, "Du hast gewonnen!","",JOptionPane.PLAIN_MESSAGE, Frame.Sieger);
			}
			GegnerRL.leben=0;
			GegnerOU.leben=0;

			}
			break;
		
		case 19://Checkpoint
			if(block.Zustand == 0){
				spieler.checkpoint = new Point(block.x,block.y);
				spieler.check_room = Spielfeld.current_room;
				block.Zustand = 1;
			}
			break;
		
		case 20://Shopbesitzer
			Spielerinfo.npc = true;
			break;
		case 21://Item_Shop_Lebenstrank
			Spielfeld.shop_trank = true;
			Spielerinfo.preis = true;
			Spielerinfo.preis_anzeige = "50 Gold";
			break;
		
		case 22://Item_Shop_Manatrank
			Spielfeld.shop_mana = true;
			Spielerinfo.preis = true;
			Spielerinfo.preis_anzeige = "75 Gold";
			break;
			
		case 23://Item_Shop_Ruestung1
			Spielfeld.shop_ruestung1 = true;
			Spielerinfo.preis = true;
			Spielerinfo.preis_anzeige = "150 Gold\n100% Rüstung";
			break;
			
		case 24://Item_Shop_Ruestung2
			Spielfeld.shop_ruestung2 = true;
			Spielerinfo.preis = true;
			Spielerinfo.preis_anzeige = "100 Gold\n+50% Rüstung";
			break;
		
		case 25://Item_Shop_Stiefel
			Spielfeld.shop_stiefel = true;
			Spielerinfo.preis = true;
			Spielerinfo.preis_anzeige = "150 Gold\n+10% Speed";
			break;
			
		case 26://Eingang Shop
			if(Schuss_Spieler.sichtbar){
   			 Schuss_Spieler.sichtbar = false;
   		 	}
   		 	if(Schuss_Endgegner.sichtbar){
   			 Schuss_Endgegner.sichtbar=false;
   		 	}
   		 	if(Falle.aktiv){
   		 		Falle.aktiv = false;
   		 		Falle.status = true;
   		 	} else if(Falle.aktiv == false){
   		 		Falle.status = false;
   		 	}
   		 	Spielerinfo.npc = false;
   		 	Spielfeld.spieler_preposX = Spielfeld.spieler.x;
   		 	Spielfeld.spieler_preposY = Spielfeld.spieler.y;
   		 	Spielfeld.showShop();
			break;
			
		case 27://Ausgang Shop
			Spielfeld.hideShop();
			Falle.aktiv = Falle.status;
			break;
		
		case 28://Item_Shop Axt
			Spielfeld.shop_axt = true;
			Spielerinfo.preis = true;
			Spielerinfo.preis_anzeige = "500 Gold";
			break;
		
		case 29://Item_Shop_Supertrank
			Spielfeld.shop_supertrank = true;
			Spielerinfo.preis = true;
			Spielerinfo.preis_anzeige = "100 Gold +40% Mana\n+40% Lebenspunkte";
			break;
			
		case 31://Schatztruhe
			int i = (int) (Math.random()*3+1);		//Zufallszahl zwischen 1 und 3 erzeugen
			if(block.Zustand == 0){
				switch(i){
				case 1:
					Spielfeld.spieler.gold += 150;
					Spielfeld.text_anzeige = "+150 Gold";
					break;
				case 2:
					Spielfeld.spieler.gold += 80;
					Spielfeld.text_anzeige = "+80 Gold";
					break;
				case 3:
					Spielfeld.spieler.gold += 120;
					Spielfeld.text_anzeige = "+120 Gold";
					break;
				default:
					Spielfeld.spieler.gold += 150;
					Spielfeld.text_anzeige = "+150 Gold";
					break;
				}

				block.Zustand = 1;
				Spielfeld.anzeige = true;
			}
			break;
		case 32://Gold
			int j = (int) (Math.random()*3+1);		//Zufallszahl zwischen 1 und 3 erzeugen
			if(block.Zustand == 0){
				switch(j){
				case 1:
					Spielfeld.spieler.gold += 50;
					Spielfeld.text_anzeige = "+50 Gold";
					break;
				case 2:
					Spielfeld.spieler.gold += 20;
					Spielfeld.text_anzeige = "+20 Gold";
					break;
				case 3:
					Spielfeld.spieler.gold += 10;
					Spielfeld.text_anzeige = "+10 Gold";
					break;
				default:
					Spielfeld.spieler.gold += 50;
					Spielfeld.text_anzeige = "+50 Gold";
					break;
				}

				block.Zustand = 1;
				Spielfeld.anzeige = true;
			}
			break;
			
		case 33://Herz
			if(block.Zustand == 0){
				spieler.superleben += 1;
				block.Zustand = 1;
			}
			break;
		
		case 40:
			if(Spielfeld.current_room!=3) {  
				if(Schuss_Spieler.sichtbar){
					Schuss_Spieler.sichtbar = false;
				}
				Spielfeld.current_room+=1;
				Falle.aktiv=false;
				Endgegner.aktiv=false;
				Schuss_Endgegner.aktiv=false;
				Spielfeld.level.loadLevel(new File("level/level"+Spielfeld.current_lvl+"_"+Spielfeld.current_room+".lvl"));
				//Spieler auf den Startpunkt des jeweiligen Levels setzen
				Spielfeld.spieler.x = Raum.Startpunkt[Spielfeld.current_lvl-1].x;
				Spielfeld.spieler.y = Raum.Startpunkt[Spielfeld.current_lvl-1].y;
				GegnerOU.leben=GegnerOU.StartLeben;
				GegnerRL.leben=GegnerRL.StartLeben;
				Spielfeld.GegnerRL_counter = 0;
				Spielfeld.GegnerOU_counter = 0;
				Spielfeld.Endgegner_counter = 0;
				Endgegner.leben=Endgegner.StartLeben;
			}
			break;
		
		default:
			//
			break;
	}
}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Math.random();
	}

}
