package propra2013.Gruppe54;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Spielerinfo extends JPanel implements Runnable{

	public Thread info = new Thread(this);
	public static boolean anzeige = false;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Spielerinfo(){
		setBounds(25,55,160,250);
		info.start();
	}
	
	public void paintComponent(Graphics g){
		g.clearRect(0, 0, 160, 250);
		g.drawRoundRect(0, 0, 158, 158, 10, 10);
		g.drawString("Level: "+Spielfeld.current_lvl,4,30);
		g.drawString("Gold: "+Spielfeld.spieler.gold,4,45);
		if(spieler.leben>40){
			g.setColor(Color.green);
		} else {
			g.setColor(Color.red);
		}
		g.fill3DRect(4, 55, (int) (spieler.leben*1.5), 7, true);
		g.setColor(Color.blue);
		g.fill3DRect(4, 65, (int) (spieler.mana*1.5), 7, true);
		g.setColor(Color.black);
		g.drawString("Inventar: ",4,90);
		if((Spielfeld.spieler.item_mana==0)&&(Spielfeld.spieler.item_trank==0)){
			g.drawString("leer",50,110);
		}
		if(Spielfeld.spieler.item_trank>0){
			g.drawImage(new ImageIcon("pics/shop_trank.png").getImage(), 4, 95, null);
			g.drawString(""+Spielfeld.spieler.item_trank, 34, 115);
		}
		if(Spielfeld.spieler.item_mana>0){
			g.drawImage(new ImageIcon("pics/shop_mana.png").getImage(), 59, 95, null);
			g.drawString(""+Spielfeld.spieler.item_mana, 89, 115);
		}
		if(anzeige){
			g.drawString("Händler: Möchtest du",0,200);
			g.drawString("zum Shop gebracht",0,215);
			g.drawString("werden?",0,230);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		while(true){
			validate();
			repaint();
			
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
