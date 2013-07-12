package propra2013.Gruppe54;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	public static void main(String[] args) {}
	
	/**
	 * KeyHandler
	 */
	@Override
	public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
      //nur bewegen wenn der Spieler aktiv ist
      if((Spielfeld.spieler.aktiv)&&(Spielfeld.spieler.beweglich)){
    	  /**
    	   * Taste A - Bewegung hoch
    	   */
         if ((key == KeyEvent.VK_A)&&(Spielfeld.getBlockID(Spielfeld.spieler.x+1.5, Spielfeld.spieler.y+26)!=1)&&(Spielfeld.getBlockID(Spielfeld.spieler.x+2, Spielfeld.spieler.y+32)!=1)) {
        	Frame.dx = -1*Spielfeld.spieler.speed;
            Spielfeld.spieler.checkShopItems();
            
            Spielfeld.spieler.links = true;
            Spielfeld.spieler.rechts = false;
            Spielfeld.spieler.hoch = false;
            Spielfeld.spieler.runter = false;
            Frame.image = Frame.Figur1_links.getImage();
            Spielfeld.richtung = "links";
            Rätsel.setFalse();
         }
         /**
   	   	  * Taste D - Bewegun rechts
   	      */
         if ((key == KeyEvent.VK_D)&&(Spielfeld.getBlockID(Spielfeld.spieler.x+30, Spielfeld.spieler.y+26)!=1)&&(Spielfeld.getBlockID(Spielfeld.spieler.x+30, Spielfeld.spieler.y+32)!=1)) {
	        Frame.dx = 1*Spielfeld.spieler.speed;
	        Spielfeld.spieler.checkShopItems();
	        
	        Spielfeld.spieler.links = false;
         	Spielfeld.spieler.rechts = true;
         	Spielfeld.spieler.hoch = false;
         	Spielfeld.spieler.runter = false;
         	Frame.image = Frame.Figur1_rechts.getImage();
         	Spielfeld.richtung = "rechts";
         	Rätsel.setFalse();
         }
         /**
   	      * Taste W - Bewegung runter
   	      */
         if ((key == KeyEvent.VK_W)&&(Spielfeld.getBlockID(Spielfeld.spieler.x+6, Spielfeld.spieler.y+26)!=1)&&(Spielfeld.getBlockID(Spielfeld.spieler.x+26, Spielfeld.spieler.y+23)!=1)) {
	        Frame.dy = -1*Spielfeld.spieler.speed;
	        Spielfeld.spieler.checkShopItems();
	        
	        Spielfeld.spieler.links = false;
	        Spielfeld.spieler.rechts = false;
	        Spielfeld.spieler.hoch = true;
	        Spielfeld.spieler.runter = false;
	        Frame.image = Frame.Figur1_oben.getImage();
	        Spielfeld.richtung = "hoch";
	        Rätsel.setFalse();
         }
         /**
   	      * Taste S - Bewegung links
   	      */
         if ((key == KeyEvent.VK_S)&&(Spielfeld.getBlockID(Spielfeld.spieler.x+6, Spielfeld.spieler.y+32)!=1)&&(Spielfeld.getBlockID(Spielfeld.spieler.x+26, Spielfeld.spieler.y+32)!=1)) {
	        Frame.dy = 1*Spielfeld.spieler.speed;
	        Spielfeld.spieler.checkShopItems();
	        
	        Spielfeld.spieler.links = false;
	        Spielfeld.spieler.rechts = false;
	        Spielfeld.spieler.hoch = false;
	        Spielfeld.spieler.runter = true;
	        Frame.image = Frame.Figur1_unten.getImage();
	        Spielfeld.richtung = "runter";
	        Rätsel.setFalse();
         }
         
         /**
   	   	  * Taste Enter - Interaktion mit Objekten und NPCs
   	      */
         if ((key == KeyEvent.VK_ENTER)){			
        	 if(Spielfeld.shop_trank){
        		 if(Spielfeld.spieler.gold >= 50){
    				 Spielfeld.spieler.gold -= 50;
    				 Spielfeld.spieler.item_trank += 1;
    			 } else if(Spielfeld.spieler.gold-50 <= 0){
    				 Spielerinfo.gold = true;
        			 Spielerinfo.info = false;
    			 }
        	 } else if(Spielfeld.shop_mana){
        		 if(Spielfeld.spieler.gold >= 75){
    				 Spielfeld.spieler.gold -= 75;
    				 Spielfeld.spieler.item_mana += 1;
    			 } else if(Spielfeld.spieler.gold-75 <= 0){
    				 Spielerinfo.gold = true;
        			 Spielerinfo.info = false;
    			 }
        	 } else if(Spielfeld.shop_supertrank){
        		 if(Spielfeld.spieler.gold >= 100){
    				 Spielfeld.spieler.gold -= 100;
    				 Spielfeld.spieler.item_supertrank += 1;
    			 } else if(Spielfeld.spieler.gold-100 <= 0){
    				 Spielerinfo.gold = true;
        			 Spielerinfo.info = false;
    			 }
        	 } else if(Spielfeld.shop_ruestung1){
        		 if(Spielfeld.spieler.ruestung == 100){
        			 Spielerinfo.ruestung_voll = true;
        			 Spielerinfo.info = false;
        		 } else if(Spielfeld.spieler.gold >= 150) {
        			 Spielfeld.spieler.ruestung = 100;
        			 Spielfeld.spieler.gold -= 150;
        		 } else if(Spielfeld.spieler.gold-150 <= 0){
        			 Spielerinfo.gold = true;
        			 Spielerinfo.info = false;
        		 }
        	 } else if(Spielfeld.shop_ruestung2){
        		 if(Spielfeld.spieler.ruestung == 100){
        			 Spielerinfo.ruestung_voll = true;
        			 Spielerinfo.info = false;
        		 } else if(Spielfeld.spieler.gold >= 100){
        			 Spielfeld.spieler.ruestung += 50;
        			 Spielfeld.spieler.gold -= 100;
        			 if(Spielfeld.spieler.ruestung >= 100){
        				 Spielfeld.spieler.ruestung = 100;
        			 }
        		 } else if(Spielfeld.spieler.gold-100 <= 0) {
        			 Spielerinfo.gold = true;
        			 Spielerinfo.info = false;
        		 }
        	 } else if(Spielfeld.shop_stiefel){
        		 if(Spielfeld.spieler.speed == 0.55){
        			 Spielerinfo.speed_voll = true;
        			 Spielerinfo.info = false;
        		 } else if(Spielfeld.spieler.gold >= 150){
        			 Spielfeld.spieler.speed = 0.55;
        			 Spielfeld.spieler.gold -= 150;
        		 } else if(Spielfeld.spieler.gold-150 <= 0) {
        			 Spielerinfo.gold = true;
        			 Spielerinfo.info = false;
        		 }
        	 } else if(Spielfeld.shop_axt){
        		 if(Spielfeld.spieler.axt){
        			 Spielerinfo.item_vorhanden = true;
        			 Spielerinfo.info = false;
        		 } else if(Spielfeld.spieler.gold >= 500){
        			 Spielfeld.spieler.axt = true;
        			 Spielfeld.spieler.gold -= 500;
        			 Spielfeld.spieler.waffe = 1;
        		 } else if(Spielfeld.spieler.gold-500 <= 0) {
        			 Spielerinfo.gold = true;
        			 Spielerinfo.info = false;
        		 }
        	 } else if(Spielfeld.shop_schuss2){
        		 if(Spielfeld.spieler.gold >= 30){
    				 Spielfeld.spieler.gold -= 30;
    				 Spielfeld.spieler.Anzahl_Schüssen += 1;
    			 } else if(Spielfeld.spieler.gold-50 <= 0){
    				 Spielerinfo.gold = true;
        			 Spielerinfo.info = false;
    			 }
        	 } else if(Spielfeld.shop_bogen){
        		 if(Spielfeld.spieler.bogen){
        			 Spielerinfo.item_vorhanden = true;
        			 Spielerinfo.info = false;
        		 } else if(Spielfeld.spieler.gold >= 250){
    				 Spielfeld.spieler.gold -= 250;
    				 Spielfeld.spieler.bogen = true;
    				 Spielfeld.spieler.waffe = 2;
    				 Spielfeld.waffe.ID = 2;
    				 Spielfeld.spieler.pfeile += 5;
    			 } else if(Spielfeld.spieler.gold-250 <= 0){
    				 Spielerinfo.gold = true;
        			 Spielerinfo.info = false;
    			 }
        	 } else if(Spielfeld.shop_pfeile){
        		 if(Spielfeld.spieler.gold >= 100){
    				 Spielfeld.spieler.gold -= 100;
    				 Spielfeld.spieler.pfeile += 10;
    			 } else if(Spielfeld.spieler.gold-100 <= 0){
    				 Spielerinfo.gold = true;
        			 Spielerinfo.info = false;
    			 }
        	 }
         }
         /**
   	      * Taste N - Lebenstrank benutzen
   	      */
         if ((key == KeyEvent.VK_N)&&(Spielfeld.spieler.aktiv)&&(Spielfeld.spieler.leben<100)&&(Spielfeld.spieler.item_trank>0)){   //Trank
        	 Spielfeld.spieler.leben += 40;
        	 Spielfeld.spieler.item_trank -= 1;
        	 if(Spielfeld.spieler.leben>100){
        		 Spielfeld.spieler.leben = 100;
        	 }
         }
         /**
          * Taste M - Mana Trank benutzen
          */
         if ((key == KeyEvent.VK_M)&&(Spielfeld.spieler.aktiv)&&(Spielfeld.spieler.mana<100)&&(Spielfeld.spieler.item_mana>0)){		//Mana
        	 Spielfeld.spieler.mana += 40;
        	 Spielfeld.spieler.item_mana -= 1;
        	 if(Spielfeld.spieler.mana>100){
        		 Spielfeld.spieler.mana = 100;
        	 }
         }
         /**
          * Taste J - Supertrank beutzen
          */
         if ((key == KeyEvent.VK_J)&&(Spielfeld.spieler.aktiv)&&((Spielfeld.spieler.mana<100)|(Spielfeld.spieler.leben<100))&&(Spielfeld.spieler.item_supertrank>0)){		//Mana
        	 Spielfeld.spieler.item_supertrank -= 1;
        	 Spielfeld.spieler.mana+=40;
			 if(Spielfeld.spieler.mana>100){
				 Spielfeld.spieler.mana = 100;
			 }
			 Spielfeld.spieler.leben += 40;
			 if(Spielfeld.spieler.leben>100){
				 Spielfeld.spieler.leben = 100;
			 }
         }
         /**
          * Leertaste - Schuss des Spielers
          */
         if ((key == KeyEvent.VK_SPACE)&&(Spielfeld.spieler.aktiv)&&(Spielfeld.spieler.mana>=10)&&(Spielfeld.spieler.xp>=15)){	
        	if(!Spielfeld.schuss_spieler.sichtbar){
	        	Spielfeld.schuss_spieler.sichtbar=true;
	        	Spielfeld.schuss_spieler.setPos=false;
				Spielfeld.schuss_spieler.checkRichtung();
				Spielfeld.spieler.mana -= 10;
				Spielfeld.counter_schuss = 0;
				if(Spielfeld.multiplayer){
					if(Spielfeld.spieler.current_room == Spielfeld.spieler2.current_room){
						Spielfeld.client.send(Spielfeld.client.socket.getLocalPort()+";schuss1;");
					}
				}
        	}
         } 
         /**
          * Taste C - Schuss2 des Spielers
          */
         if ((key == KeyEvent.VK_C)&&(Spielfeld.spieler.aktiv)&&(Spielfeld.spieler.Anzahl_Schüssen>0)&&(Spielfeld.spieler.xp>=75)){	
        	if(!Spielfeld.schuss2_spieler.sichtbar){
	        	Spielfeld.schuss2_spieler.sichtbar=true;
	        	Spielfeld.schuss2_spieler.setPos=false;
				Spielfeld.schuss2_spieler.checkRichtung();
				Spielfeld.spieler.Anzahl_Schüssen -= 1;
				Spielfeld.counter_schuss2 = 0;
				if(Spielfeld.multiplayer){
					if(Spielfeld.spieler.current_room == Spielfeld.spieler2.current_room){
						Spielfeld.client.send(Spielfeld.client.socket.getLocalPort()+";schuss2;");
					}
				}
        	}
         } 
         /**
          * Taste K - kleiner Cheat zu Testzwecken ;D
          */
         if((key == KeyEvent.VK_K)&&(Spielfeld.spieler.aktiv)){ 
        	 Spielfeld.spieler.mana = 100;
        	 Spielfeld.spieler.leben = 100;
        	 Spielfeld.spieler.ruestung = 100;
        	 Spielfeld.spieler.xp+=100;
        	 Spielfeld.spieler.Anzahl_Schüssen=10;
        	 Spielfeld.spieler.pfeile=10;
        	 if(Spielfeld.multiplayer){
				Spielfeld.client.send(Spielfeld.client.socket.getLocalPort()+";spielerleben;"+Spielfeld.spieler.leben+";");
			}
         }
         /**
          * Taste B - Angriff mit der jeweiligen Waffe
          */
         if((key == KeyEvent.VK_B)&&(Spielfeld.spieler.aktiv)){
        	if(Spielfeld.waffe.angriff == false){
        		Spielfeld.waffe.angriff = true;
        	}
        	if((Spielfeld.spieler.waffe == 2)&&(!Spielfeld.pfeil.aktiv)&&(Spielfeld.spieler.pfeile>0)){
        		Spielfeld.spieler.pfeile -= 1;
        		Spielfeld.pfeil.aktiv = true;
        		if(Spielfeld.spieler.rechts){
        			Spielfeld.pfeil.richtung = 0;
        			Spielfeld.pfeil.x = Spielfeld.spieler.x+16;
            		Spielfeld.pfeil.y = Spielfeld.spieler.y+5;
            		if(Spielfeld.multiplayer){
            			Spielfeld.client.send(Spielfeld.client.socket.getLocalPort()+";pfeil;rechts;"+Spielfeld.spieler.x+16+";"+Spielfeld.spieler.y+5+";");
            		}
        		} else if(Spielfeld.spieler.links){
        			Spielfeld.pfeil.richtung = 1;
        			Spielfeld.pfeil.x = Spielfeld.spieler.x;
            		Spielfeld.pfeil.y = Spielfeld.spieler.y+5;
            		if(Spielfeld.multiplayer){
            			Spielfeld.client.send(Spielfeld.client.socket.getLocalPort()+";pfeil;links;"+Spielfeld.spieler.x+";"+Spielfeld.spieler.y+5+";");
            		}
        		} else if(Spielfeld.spieler.hoch){
        			Spielfeld.pfeil.richtung = 2;
        			Spielfeld.pfeil.x = Spielfeld.spieler.x+12;
            		Spielfeld.pfeil.y = Spielfeld.spieler.y+5;
            		if(Spielfeld.multiplayer){
            			Spielfeld.client.send(Spielfeld.client.socket.getLocalPort()+";pfeil;hoch;"+Spielfeld.spieler.x+12+";"+Spielfeld.spieler.y+5+";");
            		}
        		} else if(Spielfeld.spieler.runter){
        			Spielfeld.pfeil.richtung = 3;
        			Spielfeld.pfeil.x = Spielfeld.spieler.x;
            		Spielfeld.pfeil.y = Spielfeld.spieler.y+8;
            		if(Spielfeld.multiplayer){
            			Spielfeld.client.send(Spielfeld.client.socket.getLocalPort()+";pfeil;runter;"+Spielfeld.spieler.x+";"+Spielfeld.spieler.y+8+";");
            		}
        		}
        	}
         }
         /**
          * Taste 1 - Schwert nehmen
          */
         if((key == KeyEvent.VK_1)&&(Spielfeld.spieler.aktiv)){
	       	Spielfeld.spieler.waffe = 0;
	       	Spielfeld.waffe.ID = 0;
	       	if(Spielfeld.multiplayer){
	       		Spielfeld.client.send(Spielfeld.client.socket.getLocalPort()+";waffenwechsel;0;");
	       	}
	     }
         /**
          * Taste 2 - Axt nehmen
          */
         if((key == KeyEvent.VK_2)&&(Spielfeld.spieler.aktiv)){
        	if(Spielfeld.spieler.axt){
        		Spielfeld.spieler.waffe = 1;
        		Spielfeld.waffe.ID = 1;
        		if(Spielfeld.multiplayer){
        			Spielfeld.client.send(Spielfeld.client.socket.getLocalPort()+";waffenwechsel;1;");
        		}
        	}
		 }
         /**
          * Taste 3 - Bogen nehmen
          */
         if((key == KeyEvent.VK_3)&&(Spielfeld.spieler.aktiv)){
         	if(Spielfeld.spieler.bogen){
         		Spielfeld.spieler.waffe = 2;
         		Spielfeld.waffe.ID = 2;
         		if(Spielfeld.multiplayer){
         			Spielfeld.client.send(Spielfeld.client.socket.getLocalPort()+";waffenwechsel;2;");
         		}
         	}
         } 
        /**
         * Taste E - Rätsel betätigen
         */
        if((key == KeyEvent.VK_E)&&(Spielfeld.spieler.aktiv)){
          if(Rätsel.geschafft==false){
        	if (Spielfeld.rätsel1.berührung==true){
        		Spielfeld.rätsel1.aktion();
        		if(Spielfeld.multiplayer){
					Spielfeld.client.send(Spielfeld.client.socket.getLocalPort()+";rätselaktion;1;");
				}
        	}
        	if (Spielfeld.rätsel2.berührung==true){
        		Spielfeld.rätsel2.aktion();
        		if(Spielfeld.multiplayer){
					Spielfeld.client.send(Spielfeld.client.socket.getLocalPort()+";rätselaktion;2;");
				}
        	}
        	if (Spielfeld.rätsel3.berührung==true){
        		Spielfeld.rätsel3.aktion();
        		if(Spielfeld.multiplayer){
					Spielfeld.client.send(Spielfeld.client.socket.getLocalPort()+";rätselaktion;3;");
				}
        	}
        	if (Spielfeld.rätsel4.berührung==true){
        		Spielfeld.rätsel4.aktion();
        		if(Spielfeld.multiplayer){
					Spielfeld.client.send(Spielfeld.client.socket.getLocalPort()+";rätselaktion;4;");
				}
        	}
          } 
        }
      }
	}


	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) {
            Frame.dx = 0;
        }

        if (key == KeyEvent.VK_D) {
            Frame.dx = 0;
        }

        if (key == KeyEvent.VK_W) {
            Frame.dy = 0;
        }

        if (key == KeyEvent.VK_S) {
            Frame.dy = 0;
        }
        if((key == KeyEvent.VK_B)&&(Spielfeld.spieler.aktiv)){
        	Spielfeld.waffe.angriff = false;
        	Spielfeld.counter_angriff = 0;
        }
	}

}
