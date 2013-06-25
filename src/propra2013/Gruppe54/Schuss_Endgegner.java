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
	
	//schaut nach wo der Endgegner steht (nur ein mal)
	public void checkPos(){
		StartX=Endgegner.StartX;
		StartY=Endgegner.StartY;
		checkPos=true;
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
	
	public boolean linksfrei(){
		boolean frei=false;
		for (int i=1;i<16;i++){
			if((Spielfeld.getBlockID(StartX-1, StartY+15+i)!=1)&&(Spielfeld.getBlockID(StartX-1,StartY+15+i)!=2)&&(Spielfeld.getBlockID(StartX-1, StartY+15+i)!=4)&&(Spielfeld.getBlockID(StartX-1, StartY+15+i)!=41)&&(Spielfeld.getBlockID(StartX-1, StartY+15+i)!=42)){
				frei=true;
			} else {
				frei=false;
				break;
			}
		}
		return frei;
		
	}
	public boolean rechtsfrei(){
		boolean frei=false;
		for (int i=1;i<16;i++){
			if((Spielfeld.getBlockID(StartX+32, StartY+15+i)!=1)&&(Spielfeld.getBlockID(StartX+32, StartY+15+i)!=2)&&(Spielfeld.getBlockID(StartX+32, StartY+15+i)!=4)&&(Spielfeld.getBlockID(StartX+32, StartY+15+i)!=41)&&(Spielfeld.getBlockID(StartX+32, StartY+15+i)!=42)){
				frei=true;
			} else {
				frei=false; 
				break;
			}
		}
		return frei;
		
	}
	public boolean obenfrei(){
		boolean frei=false;
		for (int i=1;i<29;i++){
			if((Spielfeld.getBlockID(StartX+1+i, StartY+15)!=1)&&(Spielfeld.getBlockID(StartX+1+i, StartY+15)!=2)&&(Spielfeld.getBlockID(StartX+1+i, StartY+15)!=4)&&(Spielfeld.getBlockID(StartX+1+i, StartY+15)!=41)&&(Spielfeld.getBlockID(StartX+1+i, StartY+15)!=42)){
				frei=true;
			} else {
				frei=false;
				break;
			}
		}
		return frei;
		
	}
	public boolean untenfrei(){
		boolean frei=false;
		for (int i=1;i<29;i++){
			if((Spielfeld.getBlockID(StartX+1+i, StartY+32)!=1)&&(Spielfeld.getBlockID(StartX+1+i, StartY+32)!=2)&&(Spielfeld.getBlockID(StartX+1+i, StartY+32)!=4)&&(Spielfeld.getBlockID(StartX+1+i, StartY+32)!=41)&&(Spielfeld.getBlockID(StartX+1+i, StartY+32)!=42)){
				frei=true;
			} else {
				frei=false; 
				break;
			}
		}
		return frei;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
