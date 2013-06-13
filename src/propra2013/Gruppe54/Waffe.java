package propra2013.Gruppe54;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Waffe extends Rectangle {

	public static int ID;
	public static boolean angriff = false;
	public static int x;
	public static int y;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static void draw(Graphics g){
		switch(ID){
		case 0:
			if(Spielfeld.spieler.rechts){
				if(angriff == false){
					g.drawImage(new ImageIcon("pics/schwert_"+ID+"_rechts.png").getImage(), (int)Spielfeld.spieler.x+15, (int)Spielfeld.spieler.y-6, 24, 26, null); 
					x = (int)Spielfeld.spieler.x+15;
					y = (int)Spielfeld.spieler.y-6;
				} else if(angriff){
					g.drawImage(new ImageIcon("pics/schwert_"+ID+"_rechts_angriff.png").getImage(), (int)Spielfeld.spieler.x+21, (int)Spielfeld.spieler.y, 24, 26, null); 
					x = (int)Spielfeld.spieler.x+21;
					y = (int)Spielfeld.spieler.y;
				}
			} else if(Spielfeld.spieler.links){
				if(angriff == false){
					g.drawImage(new ImageIcon("pics/schwert_"+ID+"_links.png").getImage(), (int)Spielfeld.spieler.x-7, (int)Spielfeld.spieler.y-6, 24, 26, null);
					x = (int)Spielfeld.spieler.x-7;
					y = (int)Spielfeld.spieler.y-6;
				} else if(angriff){
					g.drawImage(new ImageIcon("pics/schwert_"+ID+"_links_angriff.png").getImage(), (int)Spielfeld.spieler.x-12, (int)Spielfeld.spieler.y, 24, 26, null);
					x = (int)Spielfeld.spieler.x-12;
					y = (int)Spielfeld.spieler.y;
				}
			} else if(Spielfeld.spieler.hoch){
				if(angriff == false){
					g.drawImage(new ImageIcon("pics/schwert_"+ID+"_oben.png").getImage(), (int)Spielfeld.spieler.x, (int)Spielfeld.spieler.y, 24, 26, null);
					x = (int)Spielfeld.spieler.x;
					y = (int)Spielfeld.spieler.y;
				} else if(angriff){
					g.drawImage(new ImageIcon("pics/schwert_"+ID+"_oben_angriff.png").getImage(), (int)Spielfeld.spieler.x, (int)Spielfeld.spieler.y, 24, 26, null);
					x = (int)Spielfeld.spieler.x;
					y = (int)Spielfeld.spieler.y;
				}
			} else if(Spielfeld.spieler.runter){
				if(angriff == false){
					g.drawImage(new ImageIcon("pics/schwert_"+ID+"_unten.png").getImage(), (int)Spielfeld.spieler.x-10, (int)Spielfeld.spieler.y-4, 24, 26, null);
					x = (int)Spielfeld.spieler.x-10;
					y = (int)Spielfeld.spieler.y-4;
				} else if(angriff){
					g.drawImage(new ImageIcon("pics/schwert_"+ID+"_unten_angriff.png").getImage(), (int)Spielfeld.spieler.x-8, (int)Spielfeld.spieler.y+12, 24, 26, null);
					x = (int)Spielfeld.spieler.x-8;
					y = (int)Spielfeld.spieler.y+12;
				}
			}
			break;
		case 1:
			if(Spielfeld.spieler.rechts){
				if(angriff == false){
					g.drawImage(new ImageIcon("pics/axt_0_rechts.png").getImage(), (int)Spielfeld.spieler.x+18, (int)Spielfeld.spieler.y-4, 24, 26, null); 
					x = (int)Spielfeld.spieler.x+15;
					y = (int)Spielfeld.spieler.y-4;
				} else if(angriff){
					g.drawImage(new ImageIcon("pics/axt_0_rechts_angriff.png").getImage(), (int)Spielfeld.spieler.x+21, (int)Spielfeld.spieler.y+1, 24, 26, null); 
					x = (int)Spielfeld.spieler.x+21;
					y = (int)Spielfeld.spieler.y;
				}
			} else if(Spielfeld.spieler.links){
				if(angriff == false){
					g.drawImage(new ImageIcon("pics/axt_0_links.png").getImage(), (int)Spielfeld.spieler.x-10, (int)Spielfeld.spieler.y-4, 24, 26, null);
					x = (int)Spielfeld.spieler.x-7;
					y = (int)Spielfeld.spieler.y-4;
				} else if(angriff){
					g.drawImage(new ImageIcon("pics/axt_0_links_angriff.png").getImage(), (int)Spielfeld.spieler.x-12, (int)Spielfeld.spieler.y, 24, 26, null);
					x = (int)Spielfeld.spieler.x-12;
					y = (int)Spielfeld.spieler.y;
				}
			} else if(Spielfeld.spieler.hoch){
				if(angriff == false){
					g.drawImage(new ImageIcon("pics/axt_0_oben.png").getImage(), (int)Spielfeld.spieler.x, (int)Spielfeld.spieler.y, 24, 26, null);
					x = (int)Spielfeld.spieler.x;
					y = (int)Spielfeld.spieler.y;
				} else if(angriff){
					g.drawImage(new ImageIcon("pics/axt_0_oben_angriff.png").getImage(), (int)Spielfeld.spieler.x, (int)Spielfeld.spieler.y, 24, 26, null);
					x = (int)Spielfeld.spieler.x;
					y = (int)Spielfeld.spieler.y;
				}
			} else if(Spielfeld.spieler.runter){
				if(angriff == false){
					g.drawImage(new ImageIcon("pics/axt_0_unten.png").getImage(), (int)Spielfeld.spieler.x-7, (int)Spielfeld.spieler.y-4, 24, 26, null);
					x = (int)Spielfeld.spieler.x-10;
					y = (int)Spielfeld.spieler.y-4;
				} else if(angriff){
					g.drawImage(new ImageIcon("pics/axt_0_unten_angriff.png").getImage(), (int)Spielfeld.spieler.x-10, (int)Spielfeld.spieler.y+13, 24, 26, null);
					x = (int)Spielfeld.spieler.x-8;
					y = (int)Spielfeld.spieler.y+12;
				}
			}
			break;
			
		case 2:
			if(Spielfeld.spieler.rechts){
				if(angriff == false){
					g.drawImage(new ImageIcon("pics/saebel_0_rechts.png").getImage(), (int)Spielfeld.spieler.x+18, (int)Spielfeld.spieler.y-4, 24, 26, null); 
					x = (int)Spielfeld.spieler.x+15;
					y = (int)Spielfeld.spieler.y-4;
				} else if(angriff){
					g.drawImage(new ImageIcon("pics/saebel_0_rechts_angriff.png").getImage(), (int)Spielfeld.spieler.x+21, (int)Spielfeld.spieler.y+1, 24, 26, null); 
					x = (int)Spielfeld.spieler.x+21;
					y = (int)Spielfeld.spieler.y;
				}
			} else if(Spielfeld.spieler.links){
				if(angriff == false){
					g.drawImage(new ImageIcon("pics/saebel_0_links.png").getImage(), (int)Spielfeld.spieler.x-10, (int)Spielfeld.spieler.y-4, 24, 26, null);
					x = (int)Spielfeld.spieler.x-7;
					y = (int)Spielfeld.spieler.y-4;
				} else if(angriff){
					g.drawImage(new ImageIcon("pics/saebel_0_links_angriff.png").getImage(), (int)Spielfeld.spieler.x-12, (int)Spielfeld.spieler.y, 24, 26, null);
					x = (int)Spielfeld.spieler.x-12;
					y = (int)Spielfeld.spieler.y;
				}
			} else if(Spielfeld.spieler.hoch){
				if(angriff == false){
					g.drawImage(new ImageIcon("pics/saebel_0_oben.png").getImage(), (int)Spielfeld.spieler.x, (int)Spielfeld.spieler.y, 24, 26, null);
					x = (int)Spielfeld.spieler.x;
					y = (int)Spielfeld.spieler.y;
				} else if(angriff){
					g.drawImage(new ImageIcon("pics/saebel_0_oben_angriff.png").getImage(), (int)Spielfeld.spieler.x, (int)Spielfeld.spieler.y, 24, 26, null);
					x = (int)Spielfeld.spieler.x;
					y = (int)Spielfeld.spieler.y;
				}
			} else if(Spielfeld.spieler.runter){
				if(angriff == false){
					g.drawImage(new ImageIcon("pics/saebel_0_unten.png").getImage(), (int)Spielfeld.spieler.x-7, (int)Spielfeld.spieler.y-4, 24, 26, null);
					x = (int)Spielfeld.spieler.x-10;
					y = (int)Spielfeld.spieler.y-4;
				} else if(angriff){
					g.drawImage(new ImageIcon("pics/saebel_0_unten_angriff.png").getImage(), (int)Spielfeld.spieler.x-10, (int)Spielfeld.spieler.y+13, 24, 26, null);
					x = (int)Spielfeld.spieler.x-8;
					y = (int)Spielfeld.spieler.y+12;
				}
			}
			break;
		case 3:
			if(Spielfeld.spieler.rechts){
				if(angriff == false){
					g.drawImage(new ImageIcon("pics/bogen_0_rechts.png").getImage(), (int)Spielfeld.spieler.x+18, (int)Spielfeld.spieler.y-4, 24, 26, null); 
					x = (int)Spielfeld.spieler.x+15;
					y = (int)Spielfeld.spieler.y-4;
				} else if(angriff){
					g.drawImage(new ImageIcon("pics/bogen_0_rechts_angriff.png").getImage(), (int)Spielfeld.spieler.x+21, (int)Spielfeld.spieler.y+1, 24, 26, null); 
					x = (int)Spielfeld.spieler.x+21;
					y = (int)Spielfeld.spieler.y;
				}
			} else if(Spielfeld.spieler.links){
				if(angriff == false){
					g.drawImage(new ImageIcon("pics/bogen_0_links.png").getImage(), (int)Spielfeld.spieler.x-10, (int)Spielfeld.spieler.y-4, 24, 26, null);
					x = (int)Spielfeld.spieler.x-7;
					y = (int)Spielfeld.spieler.y-4;
				} else if(angriff){
					g.drawImage(new ImageIcon("pics/bogen_0_links_angriff.png").getImage(), (int)Spielfeld.spieler.x-12, (int)Spielfeld.spieler.y, 24, 26, null);
					x = (int)Spielfeld.spieler.x-12;
					y = (int)Spielfeld.spieler.y;
				}
			} else if(Spielfeld.spieler.hoch){
				if(angriff == false){
					g.drawImage(new ImageIcon("pics/bogen_0_oben.png").getImage(), (int)Spielfeld.spieler.x, (int)Spielfeld.spieler.y, 24, 26, null);
					x = (int)Spielfeld.spieler.x;
					y = (int)Spielfeld.spieler.y;
				} else if(angriff){
					g.drawImage(new ImageIcon("pics/bogen_0_oben_angriff.png").getImage(), (int)Spielfeld.spieler.x, (int)Spielfeld.spieler.y, 24, 26, null);
					x = (int)Spielfeld.spieler.x;
					y = (int)Spielfeld.spieler.y;
				}
			} else if(Spielfeld.spieler.runter){
				if(angriff == false){
					g.drawImage(new ImageIcon("pics/bogen_0_unten.png").getImage(), (int)Spielfeld.spieler.x-7, (int)Spielfeld.spieler.y-4, 24, 26, null);
					x = (int)Spielfeld.spieler.x-10;
					y = (int)Spielfeld.spieler.y-4;
				} else if(angriff){
					g.drawImage(new ImageIcon("pics/bogen_0_unten_angriff.png").getImage(), (int)Spielfeld.spieler.x-10, (int)Spielfeld.spieler.y+13, 24, 26, null);
					x = (int)Spielfeld.spieler.x-8;
					y = (int)Spielfeld.spieler.y+12;
				}
			}
			break;
		}
	}
	
	public static void Kollision(){
		if((((x+28 >= GegnerRL.StartX)&&(x+28 <= GegnerRL.StartX+32))&&((y+30 >= GegnerRL.StartY)&&(y+30 <= GegnerRL.StartY+32)))||
		   (((x-4 >= GegnerRL.StartX)&&(x-4 <= GegnerRL.StartX+32))&&((y+30 >= GegnerRL.StartY)&&(y+30 <= GegnerRL.StartY+32)))||
		   (((x-4 >= GegnerRL.StartX)&&(x-4 <= GegnerRL.StartX+32))&&((y+4 >= GegnerRL.StartY)&&(y+4 <= GegnerRL.StartY+32)))||
		   (((x+28 >= GegnerRL.StartX)&&(x+28 <= GegnerRL.StartX+32))&&((y+4 >= GegnerRL.StartY)&&(y+4 <= GegnerRL.StartY+32)))){
			switch(ID){
			case 0: GegnerRL.leben -= 10;
				break;
			case 1: GegnerRL.leben -= 15;
			break;
			}
		}
		if((((x+28 >= GegnerOU.StartX)&&(x+28 <= GegnerOU.StartX+32))&&((y+30 >= GegnerOU.StartY)&&(y+30 <= GegnerOU.StartY+32)))||
		   (((x-4 >= GegnerOU.StartX)&&(x-4 <= GegnerOU.StartX+32))&&((y+30 >= GegnerRL.StartY)&&(y+30 <= GegnerOU.StartY+32)))||
		   (((x-4 >= GegnerOU.StartX)&&(x-4 <= GegnerOU.StartX+32))&&((y+4 >= GegnerOU.StartY)&&(y+4 <= GegnerOU.StartY+32)))||
		   (((x+28 >= GegnerOU.StartX)&&(x+28 <= GegnerOU.StartX+32))&&((y+4 >= GegnerOU.StartY)&&(y+4 <= GegnerOU.StartY+32)))){
			switch(ID){
			case 0: GegnerOU.leben -= 10;
				break;
			case 1: GegnerOU.leben -= 15;
			break;
			}
		}
		if((((x+28 >= Endgegner.StartX)&&(x+28 <= Endgegner.StartX+32))&&((y+30 >= Endgegner.StartY)&&(y+30 <= Endgegner.StartY+32)))||
		   (((x-4 >= Endgegner.StartX)&&(x-4 <= Endgegner.StartX+32))&&((y+30 >= Endgegner.StartY)&&(y+30 <= Endgegner.StartY+32)))||
		   (((x-4 >= Endgegner.StartX)&&(x-4 <= Endgegner.StartX+32))&&((y+4 >= Endgegner.StartY)&&(y+4 <= Endgegner.StartY+32)))||
		   (((x+28 >= Endgegner.StartX)&&(x+28 <= Endgegner.StartX+32))&&((y+4 >= Endgegner.StartY)&&(y+4 <= Endgegner.StartY+32)))){
			switch(ID){
			case 0: Endgegner.leben -= 10;
				break;
			case 1: Endgegner.leben -= 15;
			break;
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
