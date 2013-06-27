package propra2013.Gruppe54.Leveleditor;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import propra2013.Gruppe54.Level;

public class Editorfeld extends JPanel implements Runnable,MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static Raum raum;
	public static Level level = new Level();
	public Thread thread = new Thread(this);
	public static Image[] elemente = new Image[60];
	public static int current_lvl = 1,current_room = 1,current_id = 0;
	public static boolean gegner1 = false,gegner2 = false, endgegner = false, fledermaus = false, falle = false,zepter = false;
	/**
	 * @param args
	 */
	
	public Editorfeld(){
		setBounds(25,80,Raum.worldWidth*Raum.blockSize+2,Raum.worldHeight*Raum.blockSize+2);
		thread.start();
		define();
		addMouseListener(this);
	}
	
	   //Bilder in Array laden
		public static void loadImages(){
			boolean lvl_null = false;
			if(current_lvl == 0){
				current_lvl = 1;
				lvl_null = true;
			}
			elemente[0] = new ImageIcon("pics/boden"+current_lvl+".png").getImage(); 
			elemente[1] = new ImageIcon("pics/mauer"+current_lvl+".png").getImage();
			elemente[2] = new ImageIcon("pics/fackel.gif").getImage();
			elemente[3] = new ImageIcon("pics/leer.png").getImage();
			elemente[4] = new ImageIcon("pics/mauer"+current_lvl+"_2.png").getImage();
			elemente[5] = new ImageIcon("pics/ausgang.png").getImage();
			elemente[6] = new ImageIcon("pics/schlüssel.png").getImage();
			//Fallen
			elemente[7] = new ImageIcon("pics/falle_loch_inaktiv.png").getImage();
			elemente[8] = new ImageIcon("pics/feuer.gif").getImage();
			elemente[9] = new ImageIcon("pics/falle_speer1.png").getImage();
			elemente[10] = new ImageIcon("pics/anim.gif").getImage();
			elemente[11] = new ImageIcon("pics/falle_speer2.png").getImage();
			elemente[12] = new ImageIcon("pics/falle_speer3.png").getImage();
			//Items
			elemente[13] = new ImageIcon("pics/item_trank.png").getImage();
			elemente[14] = new ImageIcon("pics/item_trank2.png").getImage();
			elemente[15] = new ImageIcon("pics/brunnen_rot.png").getImage();
			elemente[16] = new ImageIcon("pics/item_trank3.png").getImage();
			elemente[17] = new ImageIcon("pics/brunnen_blau.png").getImage();
			elemente[18] = new ImageIcon("pics/zepter"+current_lvl+".png").getImage();
			elemente[19] = new ImageIcon("pics/checkpoint.png").getImage();
			elemente[20] = new ImageIcon("pics/npc.png").getImage();
			//Shopelemente
			elemente[21] = new ImageIcon("pics/item_trank.png").getImage();
			elemente[22] = new ImageIcon("pics/item_trank2.png").getImage();
			elemente[23] = new ImageIcon("pics/item_ruestung1.png").getImage();
			elemente[24] = new ImageIcon("pics/item_ruestung2.png").getImage();
			elemente[25] = new ImageIcon("pics/item_stiefel.png").getImage();
			elemente[26] = new ImageIcon("pics/eingang_shop.png").getImage();
			elemente[27] = new ImageIcon("pics/ausgang_shop.png").getImage();
			elemente[28] = new ImageIcon("pics/item_axt.png").getImage();
			elemente[29] = new ImageIcon("pics/item_trank3.png").getImage();
			//30
			//Schatzelemente
			elemente[31] = new ImageIcon("pics/truhe_zu.png").getImage();
			elemente[32] = new ImageIcon("pics/gold1.gif").getImage();
			elemente[33] = new ImageIcon("pics/herz_element.png").getImage();
			//Quest NPC
			elemente[34] = new ImageIcon("pics/questNPC.png").getImage();
			//Gegner
			elemente[35] = new ImageIcon("pics/gegner1_"+current_lvl+".png").getImage();
			elemente[36] = new ImageIcon("pics/gegner2_"+current_lvl+".png").getImage();
			elemente[37] = new ImageIcon("pics/gegner3_"+current_lvl+".png").getImage();
			elemente[38] = new ImageIcon("pics/falle_beweglich_unten_"+current_lvl+".png").getImage();
			elemente[39] = new ImageIcon("pics/GegnerKI_1.png").getImage();
			//40 Ausgang Wald
			elemente[41] = new ImageIcon("pics/baum1.png").getImage();
			elemente[42] = new ImageIcon("pics/baum2.png").getImage();
			elemente[43] = new ImageIcon("pics/wasser.gif").getImage();
			//elemente[44] = new ImageIcon("pics/herz_element.png").getImage();
			elemente[45] = new ImageIcon("pics/ufer_oben.png").getImage();
			elemente[46] = new ImageIcon("pics/ufer_links.png").getImage();
			elemente[47] = new ImageIcon("pics/ufer_rechts.png").getImage();
			elemente[48] = new ImageIcon("pics/ufer_unten.png").getImage();
			elemente[54] = new ImageIcon("pics/gold2.gif").getImage();
			elemente[55] = new ImageIcon("pics/item_pfeile.png").getImage();
			if(lvl_null){
				current_lvl -= 1;
				lvl_null = false;
			}
		}
		
	
	public void define(){
		raum = new Raum();
		loadImages();
		validate();
		repaint();
	}
	
	public void paintComponent(Graphics g){
		raum.draw(g);
	}

	@Override
	public void run() {
		while(true){
			validate();
			repaint();
			try{
				Thread.sleep(10);
			} catch(Exception e){ 
				e.printStackTrace();
			}
		}
	}

	//liefert die ID des Blocks bei gegebenen x,y Koordinaten eines Punktes auf dem Spielfeld
	public static int getBlockID(double x,double y){
		int i,j;
		for(i=0;i<Raum.worldHeight;i++){
			if((y>=i*Raum.blockSize)&(y<=(i+1)*Raum.blockSize)){
				break;
			}
		}
		for(j=0;j<Raum.worldWidth;j++){
			if((x>=j*Raum.blockSize)&(x<=(j+1)*Raum.blockSize)){
				break;
			}
		}
		if(Raum.block[i][j] != null){  //Nullpointerexception abfangen
			return Raum.block[i][j].ID;
		} else {
			return 77;  //sozusagen Fehlercode
		}
	}
		
	//liefert den Block bei gegebenen x,y Koordinaten eines Punktes auf dem Spielfeld
	public static EditorBlock getBlock(double x,double y){
		int i,j;
		for(i=0;i<Raum.worldHeight;i++){
			if((y>=i*Raum.blockSize)&(y<=(i+1)*Raum.blockSize)){
				break;
			}
		}
		for(j=0;j<Raum.worldWidth;j++){
			if((x>=j*Raum.blockSize)&(x<=(j+1)*Raum.blockSize)){
				break;
			}
		}
		if(Raum.block[i][j] != null){  //Nullpointerexception abfangen
			return Raum.block[i][j];
		} else {
			return Raum.block[0][0];
		}
	}
	
	public void checkBool(int ID){
		switch(ID){
			case 18:
				zepter = false;
				break;
			case 35:
				gegner1 = false;
				break;
			case 36:
				gegner2 = false;
				break;
			case 37:
				endgegner = false;
				break;
			case 38:
				falle = false;
				break;
			case 39:
				fledermaus = false;
				break;
		}
	}
	
	public void checkExistence(){
		gegner1 = false;
		gegner2 = false;
		endgegner = false;
		falle = false;
		fledermaus = false;
		zepter = false;
		for(int y=0;y<Raum.worldHeight;y++){
			for(int x=0;x<Raum.worldWidth;x++){
				if(Raum.block[y][x].ID == 35){
					gegner1 = true;
				} else if(Raum.block[y][x].ID == 36){
					gegner2 = true;
				} else if(Raum.block[y][x].ID == 37){
					endgegner = true;
				} else if(Raum.block[y][x].ID == 38){
					falle = true;
				} else if(Raum.block[y][x].ID == 39){
					fledermaus = true;
				} else if(Raum.block[y][x].ID == 18){
					zepter = true;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		switch(current_id){
			case 18:
				if(!zepter){
					checkBool(getBlock(arg0.getX(),arg0.getY()).ID);
					Editorfeld.getBlock(arg0.getX(),arg0.getY()).ID = current_id;
					zepter = true;
				}
				break;
			case 35:
				if(!gegner1){
					checkBool(getBlock(arg0.getX(),arg0.getY()).ID);
					Editorfeld.getBlock(arg0.getX(),arg0.getY()).ID = current_id;
					gegner1 = true;
				}
				break;
			case 36:
				if(!gegner2){
					checkBool(getBlock(arg0.getX(),arg0.getY()).ID);
					Editorfeld.getBlock(arg0.getX(),arg0.getY()).ID = current_id;
					gegner2 = true;
				} 
				break;
			case 37:
				if((!endgegner)&&(current_room == 3)){
					checkBool(getBlock(arg0.getX(),arg0.getY()).ID);
					Editorfeld.getBlock(arg0.getX(),arg0.getY()).ID = current_id;
					endgegner = true;
				} 
				break;
			case 38:
				if(!falle){
					checkBool(getBlock(arg0.getX(),arg0.getY()).ID);
					Editorfeld.getBlock(arg0.getX(),arg0.getY()).ID = current_id;
					falle = true;
				} 
				break;
			case 39:
				if(!fledermaus){
					checkBool(getBlock(arg0.getX(),arg0.getY()).ID);
					Editorfeld.getBlock(arg0.getX(),arg0.getY()).ID = current_id;
					fledermaus = true;
				} 
				break;
			default:
					checkBool(getBlock(arg0.getX(),arg0.getY()).ID);
					Editorfeld.getBlock(arg0.getX(),arg0.getY()).ID = current_id;
				break;
		}
	}
	@Override
	public void mouseEntered(MouseEvent arg0){}
	@Override
	public void mouseExited(MouseEvent arg0){}
	@Override
	public void mousePressed(MouseEvent arg0){}
	@Override
	public void mouseReleased(MouseEvent arg0){}

}
