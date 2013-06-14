package propra2013.Gruppe54;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Spielerinfo extends JPanel implements Runnable{

	public Thread info = new Thread(this);
	public static boolean npc = false,preis = false,item_vorhanden = false,ruestung_voll = false,speed_voll = false,gold = false;
	public static String preis_anzeige;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Spielerinfo(){
		setBounds(25,545,810,100);
		info.start();
	}
	
	public void paintComponent(Graphics g){
		g.clearRect(0, 0, 800, 75);
		g.drawRoundRect(0, 0, 800, 75, 10, 10);
		g.setFont(new Font("Lucida Sans Typewriter",Font.PLAIN,11));
		g.drawString("Level: "+Spielfeld.current_lvl,8,20);
		g.drawString("Gold: "+Spielfeld.spieler.gold,8,35);
		//Lebensanzeige
		if(spieler.leben>0){
			if(spieler.leben>40){
				g.setColor(Color.green);
			} else {
				g.setColor(Color.red);
			}
			g.fill3DRect(75, 15, (int) (spieler.leben*1.5), 7, true);
		}
	
		if(spieler.superleben > 0){
			if(spieler.superleben == 1){
				g.drawImage(new ImageIcon("pics/herz.png").getImage(), 75, 45, null, null);
			} else if(spieler.superleben == 2){
				g.drawImage(new ImageIcon("pics/herz.png").getImage(), 75, 45, null, null);
				g.drawImage(new ImageIcon("pics/herz.png").getImage(), 90, 45, null, null);
			} else if(spieler.superleben == 3){
				g.drawImage(new ImageIcon("pics/herz.png").getImage(), 75, 45, null, null);
				g.drawImage(new ImageIcon("pics/herz.png").getImage(), 90, 45, null, null);
				g.drawImage(new ImageIcon("pics/herz.png").getImage(), 105, 45, null, null);
			} else if(spieler.superleben == 4){
				g.drawImage(new ImageIcon("pics/herz.png").getImage(), 75, 45, null, null);
				g.drawImage(new ImageIcon("pics/herz.png").getImage(), 90, 45, null, null);
				g.drawImage(new ImageIcon("pics/herz.png").getImage(), 105, 45, null, null);
				g.drawImage(new ImageIcon("pics/herz.png").getImage(), 120, 45, null, null);
			}
		}
		
		//Mana
		if(spieler.mana>0){
			g.setColor(Color.blue);
			g.fill3DRect(75, 25, (int) (spieler.mana*1.5), 7, true);
		}
		//Rüstung
		if(spieler.ruestung>0){
			g.setColor(Color.gray);
			g.fill3DRect(75, 35, (int) (spieler.ruestung*1.5), 7, true);
		}
		//Inventar
		g.setColor(Color.black);
		g.drawString("Inventar: ",250,20);
		if((Spielfeld.spieler.item_mana==0)&&(Spielfeld.spieler.item_trank==0)&&(Spielfeld.spieler.item_supertrank==0)){
			g.drawString("leer",260,40);
		}
		if(Spielfeld.spieler.item_trank>0){
			g.drawImage(new ImageIcon("pics/shop_trank.png").getImage(), 260, 30, null);
			g.drawString(""+Spielfeld.spieler.item_trank, 290, 50);
		}
		if(Spielfeld.spieler.item_mana>0){
			g.drawImage(new ImageIcon("pics/shop_mana.png").getImage(), 305, 30, null);
			g.drawString(""+Spielfeld.spieler.item_mana, 335, 50);
		}
		if(Spielfeld.spieler.item_supertrank>0){
			g.drawImage(new ImageIcon("pics/shop_supertrank.png").getImage(), 350, 30, null);
			g.drawString(""+Spielfeld.spieler.item_supertrank, 380, 50);
		}
		//Gespräch
		if(npc){
			g.drawString("Willkommen im Dungeon Wald. Pass nur auf, es lauern",420,30);
			g.drawString("überall Gefahren!",420,40);
		}
		//Preisanzeige Trank
		if(preis){
			g.drawString(preis_anzeige,420,30);
		}
		if(item_vorhanden){
			g.drawString("Du besitzt dieses Item bereits",420,30);
		}
		if(ruestung_voll){
			g.drawString("Du hast bereits volle Rüstung",420,30);
		}
		if(speed_voll){
			g.drawString("Du hast bereits maximale Geschwindigkeit",420,30);
		}
		if(gold){
			g.drawString("Du hast nicht genug Gold",420,30);
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
