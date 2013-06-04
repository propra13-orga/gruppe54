package propra2013.Gruppe54;

import java.io.File;

public class Elemente {
	
	public static boolean beruehrung = false;
	public static int feuer = 0,speer = 0; //es soll nicht jeder Schritt über das Feuer Schaden geben, es soll aber Schaden genommen werden wenn der Spieler stehen bleibt

	public static void Aufruf(int ID,Block block){
		switch(ID){
		
		case 2://Ausgang
			if(Spielfeld.current_room!=3) {  
				if(Schuss_Spieler.sichtbar){
					Schuss_Spieler.sichtbar = false;
				}
				Spielfeld.current_room+=1;
				Spielfeld.level.loadLevel(new File("level/level"+Spielfeld.current_lvl+"_"+Spielfeld.current_room+".lvl"));
				//Spieler auf den Startpunkt des jeweiligen Levels setzen
				Spielfeld.spieler.x = Raum.Startpunkt[Spielfeld.current_lvl-1].x;
				Spielfeld.spieler.y = Raum.Startpunkt[Spielfeld.current_lvl-1].y;
			}
			break;
			
		case 3://Falle_Loch - Spieler soll auf den Startpunkt zurück fallen
			Spielfeld.spieler.x = Raum.Startpunkt[Spielfeld.current_lvl-1].x;
			Spielfeld.spieler.y = Raum.Startpunkt[Spielfeld.current_lvl-1].y;
			break;
		
		case 4://Falle_Feuer
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
		
		case 5://Falle_Speer
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
			
		case 6://Falle_Monster
			beruehrung = true;
			if(spieler.ruestung<=0){
				spieler.leben-=5;
			} else {
				spieler.ruestung-=5;
			}
			if(Spielfeld.spieler.rechts){
				Spielfeld.spieler.x-=12;
			} else if((Spielfeld.spieler.links)&&(Spielfeld.spieler.rechts==false)){
				Spielfeld.spieler.x+=12;
			} else if((Spielfeld.spieler.hoch)&&(Spielfeld.spieler.links==false)&&(Spielfeld.spieler.rechts==false)){
				Spielfeld.spieler.y+=12;
			} else if((Spielfeld.spieler.runter)&&(Spielfeld.spieler.hoch==false)&&(Spielfeld.spieler.links==false)&&(Spielfeld.spieler.rechts==false)){
				Spielfeld.spieler.y-=12;
			}
			break;
		
		case 7://Lebenstrank
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
		
		case 8://Manatrank
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
			
		case 9://brunnen
			if((spieler.leben<100)&&(block.Zustand==0)){	
				spieler.leben = 100;
				if(spieler.leben>100){
					spieler.leben = 100;
				}
				block.Zustand = 1;
			}
			break;
			
		case 14://zepter
			Spielfeld.spieler.beweglich = false;
			block.ID = 0;
			if(Spielfeld.current_lvl<3){
				Frame.nextLevel.setVisible(true);
			}
			Frame.neustart.setVisible(true);
			break;
		
		case 15://Shopbesitzer
			Spielfeld.shop = true;
			break;
		case 16://Item_Shop_Lebenstrank
			Spielfeld.shop_trank = true;
			Spielerinfo.preis_trank = true;
			break;
		
		case 17://Item_Shop_Manatrank
			Spielfeld.shop_mana = true;
			Spielerinfo.preis_mana = true;
			break;
			
		case 18://Item_Shop_Ruestung1
			Spielfeld.shop_ruestung1 = true;
			Spielerinfo.preis_ruestung1 = true;
			break;
			
		case 19://Item_Shop_Ruestung2
			Spielfeld.shop_ruestung2 = true;
			Spielerinfo.preis_ruestung2 = true;
			break;
		
		case 20://Item_Shop_Stiefel
			Spielfeld.shop_stiefel = true;
			Spielerinfo.preis_stiefel = true;
			break;
			
		case 21://Ausgang Shop
			Spielfeld.hideShop();
			break;
		case 23://Schatztruhe
			if(block.Zustand == 0){
				Spielfeld.spieler.gold += 150;
				block.Zustand = 1;
				Spielfeld.anzeige = true;
				Spielfeld.text_anzeige = "+150 Gold";
			}
		case 24://Gold
			int i = (int) (Math.random()*3+1);		//Zufallszahl zwischen 1 und 3 erzeugen
			if(block.Zustand == 0){
				switch(i){
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

	}

}
