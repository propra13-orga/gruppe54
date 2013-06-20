package propra2013.Gruppe54.Leveleditor;

import java.awt.*;

public class EditorBlock extends Rectangle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    int ID;
	
	/**
	 * Konstruktor
	 */
	public EditorBlock(int x, int y, int width, int height, int ID){
		setBounds(x,y,width,height);
		this.ID = ID;
	}
	
	//zeichnet den Block, Bild wird anhand der ID geladen
	public void draw(Graphics g){ 
		if(ID == 2){	//Fackel, einzige Objekt hinter dem eine Mauer liegt
			g.drawImage(Editorfeld.elemente[1],x,y,width,height,null); 
			g.drawImage(Editorfeld.elemente[ID],x,y,width,height,null);
		} else if(ID == 55){
			g.drawRect(x,y,32,32);
		} else if(ID == 51){ //verschlossener Weg
			g.drawImage(Editorfeld.elemente[1],x,y,width,height,null);
		} else {
			g.drawImage(Editorfeld.elemente[0],x,y,width,height,null); 
			g.drawImage(Editorfeld.elemente[ID],x,y,width,height,null);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

