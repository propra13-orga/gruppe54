package propra2013.Gruppe54;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Waffe extends Rectangle {

	public int ID;
	public boolean angriff = false;
	public int x;
	public int y;
	public Spieler spieler;

	private static final long serialVersionUID = 1L;
	
	/**
	 * Konstruktor
	 * @param spieler, derjenige Spieler der die Waffe hält
	 */
	public Waffe(Spieler spieler){
		this.spieler = spieler;
	}
	
	/**
	 * Zeichnet die jeweilige Waffe an der Stelle des jeweiligen Spielers
	 * @param g
	 */
	public void draw(Graphics g){
		switch(ID){
		case 0:
			if(spieler.rechts){
				if(angriff == false){
					g.drawImage(new ImageIcon("pics/schwert_"+ID+"_rechts.png").getImage(), (int)spieler.x+15, (int)spieler.y-6, 24, 26, null); 
					x = (int)Spielfeld.spieler.x+15;
					y = (int)Spielfeld.spieler.y-6;
				} else if(angriff){
					g.drawImage(new ImageIcon("pics/schwert_"+ID+"_rechts_angriff.png").getImage(), (int)spieler.x+21, (int)spieler.y, 24, 26, null); 
					x = (int)Spielfeld.spieler.x+21;
					y = (int)Spielfeld.spieler.y;
				}
			} else if(spieler.links){
				if(angriff == false){
					g.drawImage(new ImageIcon("pics/schwert_"+ID+"_links.png").getImage(), (int)spieler.x-7, (int)spieler.y-6, 24, 26, null);
					x = (int)spieler.x-7;
					y = (int)spieler.y-6;
				} else if(angriff){
					g.drawImage(new ImageIcon("pics/schwert_"+ID+"_links_angriff.png").getImage(), (int)spieler.x-12, (int)spieler.y, 24, 26, null);
					x = (int)spieler.x-12;
					y = (int)spieler.y;
				}
			} else if(spieler.hoch){
				if(angriff == false){
					g.drawImage(new ImageIcon("pics/schwert_"+ID+"_oben.png").getImage(), (int)spieler.x+12, (int)spieler.y-5, 24, 26, null);
					x = (int)spieler.x;
					y = (int)spieler.y;
				} else if(angriff){
					g.drawImage(new ImageIcon("pics/schwert_"+ID+"_oben_angriff.png").getImage(), (int)spieler.x, (int)spieler.y, 24, 26, null);
					x = (int)spieler.x;
					y = (int)spieler.y;
				}
			} else if(spieler.runter){
				if(angriff == false){
					g.drawImage(new ImageIcon("pics/schwert_"+ID+"_unten.png").getImage(), (int)spieler.x-10, (int)spieler.y-4, 24, 26, null);
					x = (int)spieler.x-10;
					y = (int)spieler.y-4;
				} else if(angriff){
					g.drawImage(new ImageIcon("pics/schwert_"+ID+"_unten_angriff.png").getImage(), (int)spieler.x-8, (int)spieler.y+12, 24, 26, null);
					x = (int)spieler.x-8;
					y = (int)spieler.y+12;
				}
			}
			break;
		case 1:
			if(spieler.rechts){
				if(angriff == false){
					g.drawImage(new ImageIcon("pics/axt_0_rechts.png").getImage(), (int)spieler.x+18, (int)spieler.y-4, 24, 26, null); 
					x = (int)spieler.x+18;
					y = (int)spieler.y-4;
				} else if(angriff){
					g.drawImage(new ImageIcon("pics/axt_0_rechts_angriff.png").getImage(), (int)spieler.x+21, (int)spieler.y+1, 24, 26, null); 
					x = (int)spieler.x+21;
					y = (int)spieler.y;
				}
			} else if(spieler.links){
				if(angriff == false){
					g.drawImage(new ImageIcon("pics/axt_0_links.png").getImage(), (int)spieler.x-10, (int)spieler.y-4, 24, 26, null);
					x = (int)spieler.x-10;
					y = (int)spieler.y-4;
				} else if(angriff){
					g.drawImage(new ImageIcon("pics/axt_0_links_angriff.png").getImage(), (int)spieler.x-12, (int)spieler.y, 24, 26, null);
					x = (int)spieler.x-12;
					y = (int)spieler.y;
				}
			} else if(spieler.hoch){
				if(angriff == false){
					g.drawImage(new ImageIcon("pics/axt_0_oben.png").getImage(), (int)spieler.x+15, (int)spieler.y-7, 24, 26, null);
					x = (int)spieler.x+15;
					y = (int)spieler.y-7;
				} else if(angriff){
					g.drawImage(new ImageIcon("pics/axt_0_oben_angriff.png").getImage(), (int)spieler.x, (int)spieler.y, 24, 26, null);
					x = (int)spieler.x;
					y = (int)spieler.y;
				}
			} else if(spieler.runter){
				if(angriff == false){
					g.drawImage(new ImageIcon("pics/axt_0_unten.png").getImage(), (int)spieler.x-7, (int)spieler.y-4, 24, 26, null);
					x = (int)spieler.x-7;
					y = (int)spieler.y-4;
				} else if(angriff){
					g.drawImage(new ImageIcon("pics/axt_0_unten_angriff.png").getImage(), (int)spieler.x-10, (int)spieler.y+13, 24, 26, null);
					x = (int)spieler.x-10;
					y = (int)spieler.y+13;
				}
			}
			break;
		case 2:
			if(spieler.rechts){
				g.drawImage(new ImageIcon("pics/bogen_rechts.png").getImage(), (int)spieler.x+14, (int)spieler.y, 24, 26, null); 
				x = (int)spieler.x+14;
				y = (int)spieler.y;
			} else if(spieler.links){
				g.drawImage(new ImageIcon("pics/bogen_links.png").getImage(), (int)spieler.x-3, (int)spieler.y+1, 24, 26, null);
				x = (int)spieler.x-3;
				y = (int)spieler.y+1;
			} else if(spieler.hoch){
				g.drawImage(new ImageIcon("pics/bogen_oben.png").getImage(), (int)spieler.x+6, (int)spieler.y-2, 24, 26, null);
				x = (int)spieler.x+6;
				y = (int)spieler.y-2;
			} else if(spieler.runter){
				g.drawImage(new ImageIcon("pics/bogen_unten.png").getImage(), (int)spieler.x-5, (int)spieler.y, 24, 26, null);
				x = (int)spieler.x-5;
				y = (int)spieler.y;
			}
			break;
		}
	}
	
	/**
	 * Kollision, bestimmt ob die Waffe einen Gegner getroffen hat und bestimmt den Schaden
	 */
	public void Kollision(){
		if((((x+28 >= Spielfeld.gegnerRL.StartX)&&(x+28 <= Spielfeld.gegnerRL.StartX+32))&&((y+30 >= Spielfeld.gegnerRL.StartY)&&(y+30 <= Spielfeld.gegnerRL.StartY+32)))||
		   (((x-4 >= Spielfeld.gegnerRL.StartX)&&(x-4 <= Spielfeld.gegnerRL.StartX+32))&&((y+30 >= Spielfeld.gegnerRL.StartY)&&(y+30 <= Spielfeld.gegnerRL.StartY+32)))||
		   (((x-4 >= Spielfeld.gegnerRL.StartX)&&(x-4 <= Spielfeld.gegnerRL.StartX+32))&&((y+4 >= Spielfeld.gegnerRL.StartY)&&(y+4 <= Spielfeld.gegnerRL.StartY+32)))||
		   (((x+28 >= Spielfeld.gegnerRL.StartX)&&(x+28 <= Spielfeld.gegnerRL.StartX+32))&&((y+4 >= Spielfeld.gegnerRL.StartY)&&(y+4 <= Spielfeld.gegnerRL.StartY+32)))){
			switch(ID){
			case 0: 
				Spielfeld.gegnerRL.leben -= spieler.schaden;
				if(Spielfeld.multiplayer){
					Spielfeld.client.send(Spielfeld.client.socket.getLocalPort()+";gegner;1;"+spieler.schaden);
				}
				break;
			case 1: 
				Spielfeld.gegnerRL.leben -= spieler.schaden*1.2;
				if(Spielfeld.multiplayer){
					Spielfeld.client.send(Spielfeld.client.socket.getLocalPort()+";gegner;1;"+spieler.schaden*1.2);
				}
				break;
			}
		}
		if((((x+28 >= Endgegner.StartX)&&(x+28 <= Endgegner.StartX+32))&&((y+30 >= Endgegner.StartY)&&(y+30 <= Endgegner.StartY+32)))||
		   (((x-4 >= Endgegner.StartX)&&(x-4 <= Endgegner.StartX+32))&&((y+30 >= Endgegner.StartY)&&(y+30 <= Endgegner.StartY+32)))||
		   (((x-4 >= Endgegner.StartX)&&(x-4 <= Endgegner.StartX+32))&&((y+4 >= Endgegner.StartY)&&(y+4 <= Endgegner.StartY+32)))||
		   (((x+28 >= Endgegner.StartX)&&(x+28 <= Endgegner.StartX+32))&&((y+4 >= Endgegner.StartY)&&(y+4 <= Endgegner.StartY+32)))){
			switch(ID){
			case 0: 
				Endgegner.leben -= spieler.schaden;
				if(Spielfeld.multiplayer){
					Spielfeld.client.send(Spielfeld.client.socket.getLocalPort()+";gegner;4;"+spieler.schaden);
				}
				break;
			case 1: 
				Endgegner.leben -= spieler.schaden*1.2;
				if(Spielfeld.multiplayer){
					Spielfeld.client.send(Spielfeld.client.socket.getLocalPort()+";gegner;4;"+spieler.schaden*1.2);
				}
			break;
			}
		}
	}
	
	public static void main(String[] args) {}

}
