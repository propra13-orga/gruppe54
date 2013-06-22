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
	public double spielerx=0,spielery=0;
	public double nächsterSchrittX=0;
	public double nächsterSchrittY=0;
	public boolean restart=false;
	
	public Schuss_Endgegner() {
		setBounds((int)StartX,(int)StartY,32,32);
		sichtbar=true;
		aktiv=false;
	}
	
	//Zeichnet einen Schuss
		public void draw(Graphics g){
			g.drawImage(new ImageIcon("pics/schuss2.png").getImage(), (int)StartX, (int)StartY, 32, 32, null); 
		}
		
		//Bewegung nach rechts so lange es geht dann wieder vom Gegner starten
		public void bewegung(){
			restart=false;
			checkFall();
			nächsterSchritt();
			if (restart==false){
				StartX+=nächsterSchrittX;
				StartY+=nächsterSchrittY;
				Kollision();
			} else if(restart==true){
				StartX=Endgegner.StartX;
				StartY=Endgegner.StartY;
				spielerx = Spielfeld.spieler.x;
				spielery = Spielfeld.spieler.y;
	        }
		}
		
	public void Kollision(){
		if((StartX+31 >= Spielfeld.spieler.x)&&(StartX <= Spielfeld.spieler.x+31)  &&
		   (StartY+31 >= Spielfeld.spieler.y)&&(StartY <= Spielfeld.spieler.y+31)){		
			if (Spielfeld.spieler.ruestung>0){
				Spielfeld.spieler.ruestung-=1;
				restart=true;
			} else {
				Spielfeld.spieler.leben -= 1;
				restart=true;
			}
		}
	} 
	
	//schaut nach wo der Endgegner steht (nur ein mal)
	public void checkPos(){
		StartX=Endgegner.StartX;
		StartY=Endgegner.StartY;
		checkPos=true;
	}
	public int checkFall(){
		
		if ((spielerx<StartX)&&(spielery>StartY)){
			return 1;
		} else if ((spielerx>StartX)&&(spielery>StartY)){
			return 2;
		} else if ((spielerx>StartX)&&(spielery<StartY)){
			return 3;
		} else if ((spielerx<StartX)&&(spielery<StartY)){
			return 4;
		} else if ((spielerx==StartX)&&(spielery>StartY)){
			return 5;
		} else if ((spielerx>StartX)&&(spielery==StartY)){
			return 6;
		} else if ((spielerx==StartX)&&(spielery<StartY)){
			return 7;
		} else if ((spielerx<StartX)&&(spielery==StartY)){
			return 8;
		} else if ((spielerx==StartX)&&(spielery==StartY)){
			return 9;
		} else {
			return -1;
		}
	}
	
	
	//Funktion die den nächsten schritt berechnet
	public void nächsterSchritt(){
		switch(checkFall()){
		case 1:
			if(linksfrei()){
			nächsterSchrittX=-1*speed;
			} else{nächsterSchrittX=0;restart=true;}
			if(untenfrei()){
			nächsterSchrittY=1*speed;
			} else {nächsterSchrittY=0; restart=true;}
			break;
		case 2:
			if (rechtsfrei()){
			nächsterSchrittX=1*speed;
			} else {nächsterSchrittX=0;restart=true;}
			if (untenfrei()){
			nächsterSchrittY=1*speed;
			} else {nächsterSchrittY=0;restart=true;}
		    break;
		case 3:
			if(rechtsfrei()){
			nächsterSchrittX=1*speed;
			} else {nächsterSchrittX=0;restart=true;}
			if(obenfrei()){
			nächsterSchrittY-=1*speed;
			} else {nächsterSchrittY=0;restart=true;}
			break;
		case 4:
			if(linksfrei()){
			nächsterSchrittX-=1*speed;
			}else {nächsterSchrittX=0;restart=true;}
			if(obenfrei()){
			nächsterSchrittY-=1*speed;
			} else {nächsterSchrittY=0;restart=true;}
			break;
		case 5:
			if(untenfrei()){
			nächsterSchrittY=1*speed;
			} else {nächsterSchrittY=0;restart=true;}
			nächsterSchrittX=0;
			break;
		case 6:
			if(rechtsfrei()){
			nächsterSchrittX=1*speed;
			}else {nächsterSchrittX=0;restart=true;}
			nächsterSchrittY=0;
			break;
		case 7:
			nächsterSchrittX=0;
			if(obenfrei()){
			nächsterSchrittY-=1*speed;
			} else {nächsterSchrittY=0;restart=true;}
			break;
		case 8:
			if(linksfrei()){
			nächsterSchrittX-=1*speed;
			} else {nächsterSchrittX=0;restart=true;}
			nächsterSchrittY=0;
			break;
		case 9:
			restart=true;
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
