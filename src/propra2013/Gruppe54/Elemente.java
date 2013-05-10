package propra2013.Gruppe54;

public class Elemente {

	public static void Aufruf(int ID,Block block){
		if(ID < 6){
			Falle(ID);
		} else {
			Item(ID,block);
		}
	}
	
	public static void Falle(int ID){
		if(ID == 3){  		   //Falle_Loch
			spieler.x=100;
			spieler.y=40;
		} else if(ID == 4){    //Falle_Feuer
			spieler.leben-=1;
		} else if(ID == 5){    //Falle_Speer
			spieler.leben-=20;
		}
	}
	
	public static void Item(int ID,Block block){
		if(ID == 6){           //Zaubertrank
			spieler.leben = 100;
			block.ID=0;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
