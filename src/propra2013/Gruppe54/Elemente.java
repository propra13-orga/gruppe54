package propra2013.Gruppe54;

import java.awt.Point;
import java.io.File;

import javax.swing.JOptionPane;

public class Elemente {
	
	public static boolean beruehrung = false;
	public static int feuer = 0,speer = 0; //es soll nicht jeder Schritt über das Feuer Schaden geben, es soll aber Schaden genommen werden wenn der Spieler stehen bleibt

	
	/**
	 * Elemente.Aufruf - hier wird die Aktion des jeweiligen Elements anhand der ID des Block ausgeführt
	 */
	public static void Aufruf(int ID,Block block,Spieler spieler){
		switch(ID){
		
		case 5://Ausgang - Raumwechsel
			if(Spielfeld.current_room!=3) {  
				if(Spielfeld.schuss_spieler.sichtbar){
					Spielfeld.schuss_spieler.sichtbar = false;
				}
				if(Spielfeld.schuss2_spieler.sichtbar){
					Spielfeld.schuss2_spieler.sichtbar = false;
				}
				Falle.aktiv=false;
				Spielfeld.gegnerKI.leben=0;
				Endgegner.aktiv=false;
				Spielfeld.schuss_endgegner.aktiv=false;
				Spielfeld.gegnerKI.aktiv=false;
				Spielfeld.current_room+=1;
				Spielfeld.level.loadLevel(new File("level/level"+Spielfeld.current_lvl+"_"+Spielfeld.current_room+".lvl"));
				//Spieler auf den Startpunkt des jeweiligen Levels setzen
				spieler.x = Raum.Startpunkt[Spielfeld.current_lvl-1].x;
				spieler.y = Raum.Startpunkt[Spielfeld.current_lvl-1].y;
				Spielfeld.gegnerRL.leben=GegnerRL.StartLeben;
				Spielfeld.gegnerRL.leben=GegnerRL.StartLeben;
				Spielfeld.gegnerRL.setItem = false;
				Spielfeld.gegnerOU.setItem = false;
				Endgegner.setItem = false;
				Endgegner.leben=Endgegner.StartLeben;
				Spielfeld.gegnerKI.leben=Spielfeld.gegnerKI.StartLeben;
				Spielfeld.gegnerKI.StartX=0;
				Spielfeld.gegnerKI.StartY=0;
				Falle.StartX=0;
				Falle.StartY=0;
				Spielfeld.pfeil.aktiv = false;
				Raetsel.reset();
				if(Spielfeld.multiplayer){
					if(Spielfeld.schuss_spieler2.sichtbar){
						Spielfeld.schuss_spieler2.sichtbar = false;
					}
					if(Spielfeld.schuss2_spieler2.sichtbar){
						Spielfeld.schuss2_spieler2.sichtbar = false;
					}
					Spielfeld.client.send(Spielfeld.client.socket.getLocalPort()+";raumwechsel;");
				}
			}
			break;
		
		case 6://Schlüssel
			if(block.Zustand == 0){
				spieler.schluessel += 1;
				block.Zustand = 1;
			} 
			break;
			
		case 7://Falle_Loch - Spieler soll auf den Startpunkt zurück fallen
			spieler.x = Raum.Startpunkt[Spielfeld.current_lvl-1].x;
			spieler.y = Raum.Startpunkt[Spielfeld.current_lvl-1].y;
			block.Zustand = 1;
			break;
		
		case 8://Falle_Feuer
			feuer++;
			if(feuer==10){
				beruehrung = true;		  //da z.B. beim hochlaufen zwei Punkte auf Berührung überprüft werden, soll gespeichert werden,
				if(spieler.ruestung<=0){  //ob bereits eine Falle ausgelöst wurde damit die Punkte nicht doppelt abgezogen werden
					spieler.leben-=1;
					if(Spielfeld.multiplayer){	//Nachricht an den Server schicken
						Spielfeld.client.send(Spielfeld.client.socket.getLocalPort()+";spielerleben;"+spieler.leben+";");
					}
				} else {
					spieler.ruestung-=1;
				}		
				feuer = 0;
			}
			break;
		
		case 9://Falle_Speer
			speer++;
			if(speer==15){
				beruehrung = true;
				if(spieler.ruestung<=0){
					spieler.leben-=1;
					if(Spielfeld.multiplayer){	 //Nachricht an den Server schicken
						Spielfeld.client.send(Spielfeld.client.socket.getLocalPort()+";spielerleben;"+spieler.leben+";");
					}
				} else {
					spieler.ruestung-=1;
				}
				block.Zustand = 1;	//lädt das aktive Bild der Falle
				speer = 0;
			}
			break;
			
		case 10://Falle_Monster
			beruehrung = true;
			Spielfeld.Falle_counter++;
			if((spieler.ruestung<=0)&&(Spielfeld.Falle_counter==5)){
				spieler.leben-=1;
				Spielfeld.Falle_counter = 0;
				if(Spielfeld.multiplayer){ 		//Nachricht an den Server schicken
					Spielfeld.client.send(Spielfeld.client.socket.getLocalPort()+";spielerleben;"+spieler.leben+";");
				}
			} else if(Spielfeld.Falle_counter==5){
				spieler.ruestung-=1;
				Spielfeld.Falle_counter = 0;
			}
			break;
		
		case 11://Falle_Axt
			speer++;
			if(speer==15){
				beruehrung = true;
				if(spieler.ruestung<=0){
					spieler.leben-=2;
					if(Spielfeld.multiplayer){ //Nachricht an den Server schicken
						Spielfeld.client.send(Spielfeld.client.socket.getLocalPort()+";spielerleben;"+spieler.leben+";");
					}
				} else {
					spieler.ruestung-=2;
				}
				block.Zustand = 1;	//lädt das aktive Bild der Falle
				speer = 0;
			}
			break;
		
		case 12://Falle_Totenkopf
			speer++;
			if(speer==15){
				beruehrung = true;
				if(spieler.ruestung<=0){
					spieler.leben-=2;
					if(Spielfeld.multiplayer){   //Nachricht an den Server schicken
						Spielfeld.client.send(Spielfeld.client.socket.getLocalPort()+";spielerleben;"+spieler.leben+";");
					}
				} else {
					spieler.ruestung-=2;
				}
				block.Zustand = 1;	//lädt das aktive Bild der Falle
				speer = 0;
			}
			break;
		
		case 13://Lebenstrank
			if(block.Zustand==0){
			if(spieler.leben == 100){
				spieler.item_trank += 1;
			} else {
				spieler.leben+=40;
				if(spieler.leben>100){
					spieler.leben = 100;
				}
				if(Spielfeld.multiplayer){	//wenn im Netzwerkmodus gespielt wird, Nachricht an den Server schicken
					Spielfeld.client.send(Spielfeld.client.socket.getLocalPort()+";spielerleben;"+spieler.leben+";");
				}
			}
			block.Zustand = 1;
			}
			break;
		
		case 14://Manatrank
			if(block.Zustand==0){
			if(spieler.mana == 100){
				spieler.item_mana += 1;
			} else {
				spieler.mana+=40;
				if(spieler.mana>100){
					spieler.mana = 100;
				}
			}
			block.Zustand = 1;
			}
			break;
			
		case 15://Brunnen Lebensenergie
			if((spieler.leben<100)&&(block.Zustand==0)){	
				spieler.leben = 100;
				if(spieler.leben>100){
					spieler.leben = 100;
				}
				if(Spielfeld.multiplayer){
					Spielfeld.client.send(Spielfeld.client.socket.getLocalPort()+";spielerleben;"+spieler.leben+";");
				}
				block.Zustand = 1;
			}
			break;
		
		case 16://Supertrank
			if(block.Zustand==0){
			if((spieler.mana == 100)&&(spieler.leben == 100)){
				spieler.item_supertrank += 1;
			} else {
				spieler.mana+=40;
				if(spieler.mana>100){
					spieler.mana = 100;
				}
				spieler.leben += 40;
				if(spieler.leben>100){
					spieler.leben = 100;
				}
				if(Spielfeld.multiplayer){
					Spielfeld.client.send(Spielfeld.client.socket.getLocalPort()+";spielerleben;"+spieler.leben+";");
				}
			}
			block.Zustand = 1;
			}
			break;
		
		case 17://Brunnen Mana
			if((spieler.mana<100)&&(block.Zustand==0)){	
				spieler.mana = 100;
				if(spieler.mana>100){
					spieler.mana = 100;
				}
				block.Zustand = 1;
			}
			break;
			
		case 18://zepter
			if(Endgegner.leben<=0){
			spieler.beweglich = false;
			block.ID = 0;
			if(Spielfeld.current_lvl<4){
				Frame.nextLevel.setVisible(true);
				Frame.neustart.setVisible(true);
			} else if(Spielfeld.current_lvl==4){
				JOptionPane.showMessageDialog(null, "Du hast gewonnen!","",JOptionPane.PLAIN_MESSAGE, Frame.Sieger);
			}
			Spielfeld.gegnerRL.leben=0;
			Spielfeld.gegnerRL.leben=0;
			}
			break;
		
		case 19://Checkpoint
			if(block.Zustand == 0){
				spieler.checkpoint = new Point(block.x,block.y);
				spieler.check_room = Spielfeld.current_room;
				block.Zustand = 1;
			}
			break;
		
		case 20://NPC
			Spielerinfo.npc = true;
			break;
		case 21://Item_Shop_Lebenstrank
			Spielfeld.shop_trank = true;
			Spielfeld.preis_shop = true;
			Spielfeld.preis_anzeige = "50 Gold";
			Spielerinfo.info = true;
			Spielerinfo.info_anzeige = "Lebenstrank +40% Energie";
			break;
		
		case 22://Item_Shop_Manatrank
			Spielfeld.shop_mana = true;
			Spielfeld.preis_shop = true;
			Spielfeld.preis_anzeige = "75 Gold";
			Spielerinfo.info = true;
			Spielerinfo.info_anzeige = "Manatrank +40% Mana";
			break;
			
		case 23://Item_Shop_Ruestung1
			Spielfeld.shop_ruestung1 = true;
			Spielfeld.preis_shop = true;
			Spielfeld.preis_anzeige = "150 Gold";
			Spielerinfo.info = true;
			Spielerinfo.info_anzeige = "Schwere Rüstung 100% Rüstung";
			break;
			
		case 24://Item_Shop_Ruestung2
			Spielfeld.shop_ruestung2 = true;
			Spielfeld.preis_shop = true;
			Spielfeld.preis_anzeige = "100 Gold";
			Spielerinfo.info = true;
			Spielerinfo.info_anzeige = "Leichte Rüstung +50% Rüstung";
			break;
		
		case 25://Item_Shop_Stiefel
			Spielfeld.shop_stiefel = true;
			Spielfeld.preis_shop = true;
			Spielfeld.preis_anzeige = "150 Gold";
			Spielerinfo.info = true;
			Spielerinfo.info_anzeige = "Stiefel +10% Speed";
			break;
			
		case 26://Eingang Shop
			if(Spielfeld.schuss_spieler.sichtbar){
   			 Spielfeld.schuss_spieler.sichtbar = false;
   		 	}
			if(Spielfeld.schuss2_spieler.sichtbar){
	   			 Spielfeld.schuss2_spieler.sichtbar = false;
	   		}
			if(Spielfeld.multiplayer){
				if(Spielfeld.schuss_spieler2.sichtbar){
		   			 Spielfeld.schuss_spieler2.sichtbar = false;
		   		}
				if(Spielfeld.schuss2_spieler2.sichtbar){
			   		 Spielfeld.schuss2_spieler2.sichtbar = false;
			   	}
			}
   		 	if(Spielfeld.schuss_endgegner.sichtbar){
   		 	Spielfeld.schuss_endgegner.sichtbar=false;
   		 	}
   		 	if(Falle.aktiv){
   		 		Falle.aktiv = false;
   		 		Falle.status = true;
   		 	} else if(Falle.aktiv == false){
   		 		Falle.status = false;
   		 	}
   		 	Spielfeld.spieler_preposX = Spielfeld.spieler.x;
   		 	Spielfeld.spieler_preposY = Spielfeld.spieler.y;
   		 if(Spielfeld.multiplayer){		
				if(Spielfeld.schuss_spieler2.sichtbar){
					Spielfeld.schuss_spieler2.sichtbar = false;
				}
				if(Spielfeld.schuss2_spieler2.sichtbar){
					Spielfeld.schuss2_spieler2.sichtbar = false;
				}
				//Nachricht an den Server schicken
				Spielfeld.client.send(Spielfeld.client.socket.getLocalPort()+";shopein;"); 
			}
   		 	Spielfeld.showShop();
   		 	Raetsel.reset();
			break;
			
		case 27://Ausgang Shop
			Spielfeld.hideShop();
			Falle.aktiv = Falle.status;
			Spielfeld.pfeil.aktiv = false;
			if(Spielfeld.multiplayer){
				//Nachricht an den Server schicken
				Spielfeld.client.send(Spielfeld.client.socket.getLocalPort()+";shopaus;");
			}
			Raetsel.reset();
			break;
		
		case 28://Item_Shop Axt
			Spielfeld.shop_axt = true;
			Spielfeld.preis_shop = true;
			Spielfeld.preis_anzeige = "500 Gold";
			Spielerinfo.info = true;
			Spielerinfo.info_anzeige = "Axt";
			break;
		
		case 29://Item_Shop_Supertrank
			Spielfeld.shop_supertrank = true;
			Spielfeld.preis_shop = true;
			Spielfeld.preis_anzeige = "100 Gold";
			Spielerinfo.info = true;
			Spielerinfo.info_anzeige = "Supertrank - erhöht sowohl Mana- als auch Lebenspunkte um 40%";
			break;
			
		case 30://Item_Shop_Feuer-Magie
			Spielfeld.shop_schuss2 = true;
			Spielfeld.preis_shop = true;
			Spielfeld.preis_anzeige = "30 Gold";
			Spielerinfo.info = true;
			Spielerinfo.info_anzeige = "Diese Magie ermöglicht es dir die Fledermäuse zu besiegen";
			break;
			
		case 31://Schatztruhe
			int i = (int) (Math.random()*3+1);		//Zufallszahl zwischen 1 und 3 erzeugen
			if((block.Zustand == 0)&&(spieler.schluessel>0)){
				switch(i){
				case 1:
					spieler.gold += 150;
					Spielfeld.text_anzeige = "+150 Gold";
					break;
				case 2:
					spieler.pfeile += 10;
					Spielfeld.text_anzeige = "+10 Pfeile";
					break;
				case 3:
					spieler.item_trank += 1;
					Spielfeld.text_anzeige = "+1 Trank";
					break;
				default:
					spieler.gold += 150;
					Spielfeld.text_anzeige = "+150 Gold";
					break;
				}
				block.Zustand = 1;
				Spielfeld.anzeige = true;
			} else if((spieler.schluessel == 0)&&(block.Zustand == 0)){
				Spielfeld.text_anzeige = "     ???";
				Spielfeld.anzeige = true;
			}
			if(spieler.schluessel != 0){
				spieler.schluessel -= 1;
			}
			break;
		case 32://Gold1
			int j = (int) (Math.random()*3+1);		//Zufallszahl zwischen 1 und 3 erzeugen
			if(block.Zustand == 0){
				switch(j){
				case 1:
					spieler.gold += 50;
					Spielfeld.text_anzeige = "+50 Gold";
					break;
				case 2:
					spieler.gold += 20;
					Spielfeld.text_anzeige = "+20 Gold";
					break;
				case 3:
					spieler.gold += 10;
					Spielfeld.text_anzeige = "+10 Gold";
					break;
				default:
					spieler.gold += 50;
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
		case 34://Quest NPC
			Spielerinfo.questnpc = true;
			break;
		//Ausgang Wald
		case 40:
			if(Spielfeld.current_room!=3) {  
				if(Spielfeld.schuss_spieler.sichtbar){
					Spielfeld.schuss_spieler.sichtbar = false;
				}
				if(Spielfeld.schuss2_spieler.sichtbar){
					Spielfeld.schuss2_spieler.sichtbar = false;
				}
				Raetsel.reset();
				Spielfeld.pfeil.aktiv = false;
				Spielfeld.current_room+=1;
				Falle.aktiv=false;
				Endgegner.aktiv=false;
				Spielfeld.schuss_endgegner.aktiv=false;
				Spielfeld.gegnerKI.aktiv = false;
				Spielfeld.level.loadLevel(new File("level/level"+Spielfeld.current_lvl+"_"+Spielfeld.current_room+".lvl"));
				//Spieler auf den Startpunkt des jeweiligen Levels setzen
				spieler.x = Raum.Startpunkt[Spielfeld.current_lvl-1].x;
				spieler.y = Raum.Startpunkt[Spielfeld.current_lvl-1].y;
				Spielfeld.gegnerRL.leben=GegnerRL.StartLeben;
				Spielfeld.gegnerRL.setItem = false;
				Spielfeld.gegnerOU.setItem = false;
				Endgegner.setItem = false;
				Endgegner.leben=Endgegner.StartLeben;
				Spielfeld.gegnerKI.leben=Spielfeld.gegnerKI.StartLeben;
				Spielfeld.gegnerKI.StartX=0;
				Spielfeld.gegnerKI.StartY=0;
				Falle.StartX=0;
				Falle.StartY=0;
				if(Spielfeld.multiplayer){
					if(Spielfeld.schuss_spieler2.sichtbar){
						Spielfeld.schuss_spieler2.sichtbar = false;
					}
					if(Spielfeld.schuss2_spieler2.sichtbar){
						Spielfeld.schuss2_spieler2.sichtbar = false;
					}
				}
			}
			break;
		case 52://Item_Shop_Pfeile
			Spielfeld.shop_pfeile = true;
			Spielfeld.preis_shop = true;
			Spielfeld.preis_anzeige = "100 Gold";
			Spielerinfo.info = true;
			Spielerinfo.info_anzeige = "10 Pfeile für den Bogen";
			break;
		case 53://Item_Shop_Bogen
			Spielfeld.shop_bogen = true;
			Spielfeld.preis_shop = true;
			Spielfeld.preis_anzeige = "250 Gold";
			Spielerinfo.info = true;
			Spielerinfo.info_anzeige = "Bogen +5 Pfeile";
			break;
		case 54://Gold2
			int k = (int) (Math.random()*3+1);		//Zufallszahl zwischen 1 und 3 erzeugen
			if(block.Zustand == 0){
				switch(k){
				case 1:
					spieler.gold += 60;
					Spielfeld.text_anzeige = "+60 Gold";
					break;
				case 2:
					spieler.gold += 10;
					Spielfeld.text_anzeige = "+10 Gold";
					break;
				case 3:
					spieler.gold += 20;
					Spielfeld.text_anzeige = "+20 Gold";
					break;
				default:
					spieler.gold += 20;
					Spielfeld.text_anzeige = "+20 Gold";
					break;
				}

				block.Zustand = 1;
				Spielfeld.anzeige = true;
			}
			break;
		//Pfeile
		case 55:
			if(block.Zustand == 0){
				block.Zustand = 1;
				spieler.pfeile += 5;
				Spielfeld.text_anzeige = "+5 Pfeile";
				Spielfeld.anzeige = true;
			}
			break;

		default:
			//
			break;
	}
}
	
	public static void main(String[] args) {
		Math.random();
	}

}
