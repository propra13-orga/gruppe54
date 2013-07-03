package propra2013.Gruppe54;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Schuss_Endgegner extends Rectangle {
	
	private static final long serialVersionUID = 1L;
	public boolean checkPos=false;
	public double StartX,StartY;
	public double speed = 0.6;
	public boolean sichtbar=false;
	public boolean aktiv;
	public double nächsterSchrittX=0;
	public double nächsterSchrittY=0;
	private boolean restart=true;
	private int counter_Schritt=0;
	
	public Schuss_Endgegner() {
		setBounds((int)StartX,(int)StartY,32,32);
		sichtbar=true;
		aktiv=false;
	}
	
	//Zeichnet einen Schuss
		public void draw(Graphics g){
			g.drawImage(new ImageIcon("pics/schuss2.png").getImage(), (int)StartX, (int)StartY, 32, 32, null); 
		}
		
		//Setzt die Start Position
		public void setStart(){
			StartX=Endgegner.StartX;
			StartY=Endgegner.StartY;
		}
		
		public void setSchritt(){
			StartX+=nächsterSchrittX*speed;
			StartY+=nächsterSchrittY*speed;
		}
		
		//Flugbahn des Schusses
		public void bewegung(){
			if(restart==true){
				setStart();
				nächsterSchritt();
				restart=false;
			} else
				if(restart==false){
					if (counter_Schritt==2){
					nächsterSchritt();
					setSchritt();
					Kollision();
					kollisionMauer();
					counter_Schritt=0;
					} else 
						if(counter_Schritt<2){
							//setSchritt();
							Kollision();
							kollisionMauer();
							counter_Schritt++;
						}
				}
		}
	
	public void kollisionMauer(){
		if ((Spielfeld.getBlockID(StartX, StartY)==1) || (Spielfeld.getBlockID(StartX+31, StartY)==1)
				|| (Spielfeld.getBlockID(StartX, StartY+31)==1) || (Spielfeld.getBlockID(StartX+31, StartY+31)==1)){
			restart=true;
		} else
			if ((Spielfeld.getBlockID(StartX, StartY)==2) || (Spielfeld.getBlockID(StartX+31, StartY)==2)
					|| (Spielfeld.getBlockID(StartX, StartY+31)==2) || (Spielfeld.getBlockID(StartX+31, StartY+31)==2)){
				restart=true;
			} else
				if ((Spielfeld.getBlockID(StartX, StartY)==4) || (Spielfeld.getBlockID(StartX+31, StartY)==4)
						|| (Spielfeld.getBlockID(StartX, StartY+31)==4) || (Spielfeld.getBlockID(StartX+31, StartY+31)==4)){
					restart=true;
				}else
					if ((Spielfeld.getBlockID(StartX, StartY)==41) || (Spielfeld.getBlockID(StartX+31, StartY)==41)
							|| (Spielfeld.getBlockID(StartX, StartY+31)==41) || (Spielfeld.getBlockID(StartX+31, StartY+31)==41)){
						restart=true;
					}else
						if ((Spielfeld.getBlockID(StartX, StartY)==42) || (Spielfeld.getBlockID(StartX+31, StartY)==42)
								|| (Spielfeld.getBlockID(StartX, StartY+31)==42) || (Spielfeld.getBlockID(StartX+31, StartY+31)==42)){
							restart=true;
						}else
							if ((Spielfeld.getBlockID(StartX, StartY)==56) || (Spielfeld.getBlockID(StartX+31, StartY)==56)
									|| (Spielfeld.getBlockID(StartX, StartY+31)==56) || (Spielfeld.getBlockID(StartX+31, StartY+31)==56)){
								restart=true;
							}else
								if ((Spielfeld.getBlockID(StartX, StartY)==57) || (Spielfeld.getBlockID(StartX+31, StartY)==57)
										|| (Spielfeld.getBlockID(StartX, StartY+31)==57) || (Spielfeld.getBlockID(StartX+31, StartY+31)==57)){
									restart=true;
								}else
									if ((Spielfeld.getBlockID(StartX, StartY)==58) || (Spielfeld.getBlockID(StartX+31, StartY)==58)
											|| (Spielfeld.getBlockID(StartX, StartY+31)==58) || (Spielfeld.getBlockID(StartX+31, StartY+31)==58)){
										restart=true;
									}
	}
	public void Kollision(){
		if((StartX+31 >= Spielfeld.spieler.x)&&(StartX <= Spielfeld.spieler.x+31)  &&
		   (StartY+31 >= Spielfeld.spieler.y)&&(StartY <= Spielfeld.spieler.y+31)){		
			if (Spielfeld.spieler.ruestung>0){
				Spielfeld.spieler.ruestung-=1;
			} else {
				Spielfeld.spieler.leben -= 1;
			}
			restart=true;
		}
	} 

	public int checkFall(){
		
		if ((Spielfeld.spieler.x<StartX)&&(Spielfeld.spieler.y>StartY)){
			return 1;
		} else if ((Spielfeld.spieler.x>StartX)&&(Spielfeld.spieler.y>StartY)){
			return 2;
		} else if ((Spielfeld.spieler.x>StartX)&&(Spielfeld.spieler.y<StartY)){
			return 3;
		} else if ((Spielfeld.spieler.x<StartX)&&(Spielfeld.spieler.y<StartY)){
			return 4;
		} else if ((Spielfeld.spieler.x==StartX)&&(Spielfeld.spieler.y>StartY)){
			return 5;
		} else if ((Spielfeld.spieler.x>StartX)&&(Spielfeld.spieler.y==StartY)){
			return 6;
		} else if ((Spielfeld.spieler.x==StartX)&&(Spielfeld.spieler.y<StartY)){
			return 7;
		} else if ((Spielfeld.spieler.x<StartX)&&(Spielfeld.spieler.y==StartY)){
			return 8;
		} else if ((Spielfeld.spieler.x==StartX)&&(Spielfeld.spieler.y==StartY)){
			return 9;
		} else {
			return -1;
		}
	}
	
	
	//Funktion die den nächsten schritt berechnet
	public void nächsterSchritt(){
		switch(checkFall()){
		case 1:
			nächsterSchrittX=-1;
			nächsterSchrittY=1;
			break;
		case 2:
			nächsterSchrittX=1;
			nächsterSchrittY=1;
		    break;
		case 3:
			nächsterSchrittX=1;
			nächsterSchrittY=-1;
			break;
		case 4:
			nächsterSchrittX=-1;
			nächsterSchrittY=-1;
			break;
		case 5:
			nächsterSchrittY=1;
			nächsterSchrittX=0;
			break;
		case 6:
			nächsterSchrittX=1;
			nächsterSchrittY=0;
			break;
		case 7:
			nächsterSchrittX=0;
			nächsterSchrittY=-1;
			break;
		case 8:
			nächsterSchrittX=-1;
			nächsterSchrittY=0;
			break;
		case 9:
			nächsterSchrittX=0;
			nächsterSchrittY=0;
			break;
		}		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
