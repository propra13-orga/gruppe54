package propra2013.Gruppe54;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Schuss_Endgegner extends Rectangle {
	
	private static final long serialVersionUID = 1L;
	public static boolean checkPos=false;
	public static int StartX,StartY;
	public static boolean sichtbar=false;
	public static boolean aktiv;
	public static int Fall=0;
	public static int nächsterSchrittX=0;
	public static int nächsterSchrittY=0;
	public static boolean restart=false;

	
	public Schuss_Endgegner() {
		setBounds(StartX,StartY,32,32);
		sichtbar=true;
		aktiv=false;
	}
	
	//Zeichnet einen Schuss
		public void draw(Graphics g){
			g.drawImage(new ImageIcon("pics/schuss2.png").getImage(), StartX, StartY, 32, 32, null); 
		}
		
		//Bewegung nach rechts so lange es geht dann wieder vom Gegner starten
		public static void bewegung(){
			restart=false;
			checkFall();
			nächsterSchritt();
			StartX+=nächsterSchrittX;
			StartY+=nächsterSchrittY;
	        Kollision();
	        if(restart==true){
	       		 StartX=(int)Endgegner.StartX;
	     	     StartY=(int)Endgegner.StartY;
	        }
	        
	        System.out.println(restart);
		}
		
	public static void Kollision(){
		if( (Schuss_Endgegner.StartX+31 >= Spielfeld.spieler.x)&&(Schuss_Endgegner.StartX <= Spielfeld.spieler.x+31)  &&
			(Schuss_Endgegner.StartY+31 >= Spielfeld.spieler.y)&&(Schuss_Endgegner.StartY <= Spielfeld.spieler.y+31)){		
			if (spieler.ruestung>0){
				spieler.ruestung-=25;
			} else {
				spieler.leben -= 25;
			}
			restart=true;
		} 
	} 
	
	//schaut nach wo der Endgegner steht (nur ein mal)
	public static void checkPos(){
		StartX=(int)Endgegner.StartX;
		StartY=(int)Endgegner.StartY;
		checkPos=true;
	}
	public static void checkFall(){
		
		if ((Spielfeld.spieler.x<Schuss_Endgegner.StartX)&&(Spielfeld.spieler.y>Schuss_Endgegner.StartY)){
			Fall=1;
		}
		if ((Spielfeld.spieler.x>Schuss_Endgegner.StartX)&&(Spielfeld.spieler.y>Schuss_Endgegner.StartY)){
			Fall=2;
		}
		if ((Spielfeld.spieler.x>Schuss_Endgegner.StartX)&&(Spielfeld.spieler.y<Schuss_Endgegner.StartY)){
			Fall=3;
		}
		if ((Spielfeld.spieler.x<Schuss_Endgegner.StartX)&&(Spielfeld.spieler.y<Schuss_Endgegner.StartY)){
			Fall=4;
		}
		if ((Spielfeld.spieler.x==Schuss_Endgegner.StartX)&&(Spielfeld.spieler.y>Schuss_Endgegner.StartY)){
			Fall=5;
		}
		if ((Spielfeld.spieler.x>Schuss_Endgegner.StartX)&&(Spielfeld.spieler.y==Schuss_Endgegner.StartY)){
			Fall=6;
		}
		if ((Spielfeld.spieler.x==Schuss_Endgegner.StartX)&&(Spielfeld.spieler.y<Schuss_Endgegner.StartY)){
			Fall=7;
		}
		if ((Spielfeld.spieler.x<Schuss_Endgegner.StartX)&&(Spielfeld.spieler.y==Schuss_Endgegner.StartY)){
			Fall=8;
		}
		if ((Spielfeld.spieler.x==Schuss_Endgegner.StartX)&&(Spielfeld.spieler.y==Schuss_Endgegner.StartY)){
			Fall=9;
		}
	}
	
	
	//Funktion die den nächsten schritt berechnet
	public static void nächsterSchritt(){
		if (Fall==1){
			if(linksfrei()){
			nächsterSchrittX=-1;
			} else{nächsterSchrittX=0;restart=true;}
			if(untenfrei()){
			nächsterSchrittY=1;
			} else {nächsterSchrittY=0; restart=true;}
		} else
		if (Fall==2){
			if (rechtsfrei()){
			nächsterSchrittX=1;
			} else {nächsterSchrittX=0;restart=true;}
			if (untenfrei()){
			nächsterSchrittY=1;
			} else {nächsterSchrittY=0;restart=true;}
		} else
		if (Fall==3){
			if(rechtsfrei()){
			nächsterSchrittX=1;
			} else {nächsterSchrittX=0;restart=true;}
			if(obenfrei()){
			nächsterSchrittY=-1;
			} else {nächsterSchrittY=0;restart=true;}
		} else
		if (Fall==4){
			if(linksfrei()){
			nächsterSchrittX=-1;
			}else {nächsterSchrittX=0;restart=true;}
			if(obenfrei()){
			nächsterSchrittY=-1;
			} else {nächsterSchrittY=0;restart=true;}
		} else
		if (Fall==5){
			if(untenfrei()){
			nächsterSchrittY=1;
			} else {nächsterSchrittY=0;restart=true;}
			nächsterSchrittX=0;
		}else
		if (Fall==6){
			if(rechtsfrei()){
			nächsterSchrittX=1;
			}else {nächsterSchrittX=0;restart=true;}
			nächsterSchrittY=0;
		}else
		if (Fall==7){
			nächsterSchrittX=0;
			if(obenfrei()){
			nächsterSchrittY=-1;
			} else {nächsterSchrittY=0;restart=true;}
		}else
		if (Fall==8){
			if(linksfrei()){
			nächsterSchrittX=-1;
			} else {nächsterSchrittX=0;restart=true;}
			nächsterSchrittY=0;
		}else
		if (Fall==9){
			restart=true;
		}		
	}
	
	public static boolean linksfrei(){
		boolean frei=false;
		for (int i=1;i<16;i++){
			if((Spielfeld.getBlockID(Schuss_Endgegner.StartX-1, Schuss_Endgegner.StartY+15+i)!=1)&&(Spielfeld.getBlockID(Schuss_Endgegner.StartX-1, Schuss_Endgegner.StartY+15+i)!=2)&&(Spielfeld.getBlockID(Schuss_Endgegner.StartX-1, Schuss_Endgegner.StartY+15+i)!=4)&&(Spielfeld.getBlockID(Schuss_Endgegner.StartX-1, Schuss_Endgegner.StartY+15+i)!=41)&&(Spielfeld.getBlockID(Schuss_Endgegner.StartX-1, Schuss_Endgegner.StartY+15+i)!=42)){
				frei=true;
			} else frei=false; break;
		}
		return frei;
		
	}
	public static boolean rechtsfrei(){
		boolean frei=false;
		for (int i=1;i<16;i++){
			if((Spielfeld.getBlockID(Schuss_Endgegner.StartX+32, Schuss_Endgegner.StartY+15+i)!=1)&&(Spielfeld.getBlockID(Schuss_Endgegner.StartX+32, Schuss_Endgegner.StartY+15+i)!=2)&&(Spielfeld.getBlockID(Schuss_Endgegner.StartX+32, Schuss_Endgegner.StartY+15+i)!=4)&&(Spielfeld.getBlockID(Schuss_Endgegner.StartX+32, Schuss_Endgegner.StartY+15+i)!=41)&&(Spielfeld.getBlockID(Schuss_Endgegner.StartX+32, Schuss_Endgegner.StartY+15+i)!=42)){
				frei=true;
			} else frei=false; break;
		}
		return frei;
		
	}
	public static boolean obenfrei(){
		boolean frei=false;
		for (int i=1;i<29;i++){
			if((Spielfeld.getBlockID(Schuss_Endgegner.StartX+1+i, Schuss_Endgegner.StartY+15)!=1)&&(Spielfeld.getBlockID(Schuss_Endgegner.StartX+1+i, Schuss_Endgegner.StartY+15)!=2)&&(Spielfeld.getBlockID(Schuss_Endgegner.StartX+1+i, Schuss_Endgegner.StartY+15)!=4)&&(Spielfeld.getBlockID(Schuss_Endgegner.StartX+1+i, Schuss_Endgegner.StartY+15)!=41)&&(Spielfeld.getBlockID(Schuss_Endgegner.StartX+1+i, Schuss_Endgegner.StartY+15)!=42)){
				frei=true;
			} else frei=false; break;
		}
		return frei;
		
	}
	public static boolean untenfrei(){
		boolean frei=false;
		for (int i=1;i<29;i++){
			if((Spielfeld.getBlockID(Schuss_Endgegner.StartX+1+i, Schuss_Endgegner.StartY+32)!=1)&&(Spielfeld.getBlockID(Schuss_Endgegner.StartX+1+i, Schuss_Endgegner.StartY+32)!=2)&&(Spielfeld.getBlockID(Schuss_Endgegner.StartX+1+i, Schuss_Endgegner.StartY+32)!=4)&&(Spielfeld.getBlockID(Schuss_Endgegner.StartX+1+i, Schuss_Endgegner.StartY+32)!=41)&&(Spielfeld.getBlockID(Schuss_Endgegner.StartX+1+i, Schuss_Endgegner.StartY+32)!=42)){
				frei=true;
			} else frei=false; break;
		}
		return frei;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
