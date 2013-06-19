package propra2013.Gruppe54;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Spielerinfo extends JPanel implements Runnable{

	public Thread thread_info = new Thread(this);
	public static boolean npc = false,info = false,item_vorhanden = false,ruestung_voll = false,speed_voll = false,gold = false;
	public static String preis_anzeige,info_anzeige;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Spielerinfo(){
		setBounds(25,545,810,100);
		thread_info.start();
	}
	
	public void paintComponent(Graphics g){
		g.clearRect(0, 0, 800, 75);
		g.drawRoundRect(0, 0, 800, 75, 10, 10);
		g.setFont(new Font("Lucida Sans Typewriter",Font.PLAIN,11));
		g.drawString("Level: "+Spielfeld.current_lvl,8,20);
		g.drawString("Raum: "+Spielfeld.current_room,8,35);
		g.drawString("Gold: "+Spielfeld.spieler.gold,8,50);
		//Lebensanzeige
		if(Spielfeld.spieler.leben>0){
			if(Spielfeld.spieler.leben>40){
				g.setColor(Color.green);
			} else {
				g.setColor(Color.red);
			}
			g.fill3DRect(75, 15, (int) (Spielfeld.spieler.leben*1.5), 7, true);
		}
	
		if(Spielfeld.spieler.superleben > 0){
			if(Spielfeld.spieler.superleben == 1){
				g.drawImage(new ImageIcon("pics/herz.png").getImage(), 75, 45, null, null);
			} else if(Spielfeld.spieler.superleben == 2){
				g.drawImage(new ImageIcon("pics/herz.png").getImage(), 75, 45, null, null);
				g.drawImage(new ImageIcon("pics/herz.png").getImage(), 90, 45, null, null);
			} else if(Spielfeld.spieler.superleben == 3){
				g.drawImage(new ImageIcon("pics/herz.png").getImage(), 75, 45, null, null);
				g.drawImage(new ImageIcon("pics/herz.png").getImage(), 90, 45, null, null);
				g.drawImage(new ImageIcon("pics/herz.png").getImage(), 105, 45, null, null);
			} else if(Spielfeld.spieler.superleben == 4){
				g.drawImage(new ImageIcon("pics/herz.png").getImage(), 75, 45, null, null);
				g.drawImage(new ImageIcon("pics/herz.png").getImage(), 90, 45, null, null);
				g.drawImage(new ImageIcon("pics/herz.png").getImage(), 105, 45, null, null);
				g.drawImage(new ImageIcon("pics/herz.png").getImage(), 120, 45, null, null);
			}
		}
		
		//Mana
		if(Spielfeld.spieler.mana>0){
			g.setColor(Color.blue);
			g.fill3DRect(75, 25, (int) (Spielfeld.spieler.mana*1.5), 7, true);
		}
		//Rüstung
		if(Spielfeld.spieler.ruestung>0){
			g.setColor(Color.gray);
			g.fill3DRect(75, 35, (int) (Spielfeld.spieler.ruestung*1.5), 7, true);
		}
		//Inventar
		g.setColor(Color.black);
		g.drawString("Inventar: ",250,20);
		g.setFont(new Font("Lucida Sans Typewriter",Font.BOLD,9));
		if(Spielfeld.spieler.item_trank>0){
			g.drawImage(new ImageIcon("pics/shop_trank.png").getImage(), 320, 2, null);
			g.drawString("x"+Spielfeld.spieler.item_trank, 335, 35);
		}
		if(Spielfeld.spieler.item_mana>0){
			g.drawImage(new ImageIcon("pics/shop_mana.png").getImage(), 350, 2, null);
			g.drawString("x"+Spielfeld.spieler.item_mana, 365, 35);
		}
		if(Spielfeld.spieler.item_supertrank>0){
			g.drawImage(new ImageIcon("pics/shop_supertrank.png").getImage(), 380, 2, null);
			g.drawString("x"+Spielfeld.spieler.item_supertrank, 395, 35);
		}
		if(Spielfeld.spieler.ausrüstung <= 1){
			g.drawImage(new ImageIcon("pics/inventar_schwert.png").getImage(), 320, 45, null);
		} else if(Spielfeld.spieler.ausrüstung <= 2){
			g.drawImage(new ImageIcon("pics/inventar_schwert.png").getImage(), 320, 45, null);
			g.drawImage(new ImageIcon("pics/inventar_axt.png").getImage(), 350, 45, null);
		}
		//Gitter für das Inventar zeichnen
		g.setColor(Color.gray);
		g.drawLine(320, 0, 320, 75);
		g.drawLine(350, 0, 350, 75);
		g.drawLine(380, 0, 380, 75);
		g.drawLine(410, 0, 410, 75);
		g.drawLine(440, 0, 440, 75);
		g.drawLine(320, 36, 440, 36);
		g.setFont(new Font("Lucida Sans Typewriter",Font.PLAIN,10));
		g.setColor(Color.black);
		//Gespräch
		if(npc){
			g.drawString("Willkommen im Dungeon Wald. Pass nur auf,",460,30);
			g.drawString("es lauern überall Gefahren!",460,40);
		}
		//Preisanzeige Trank
		if(info){
			g.drawString(info_anzeige,460,30);
		}
		if(item_vorhanden){
			g.drawString("Du besitzt dieses Item bereits",460,30);
		}
		if(ruestung_voll){
			g.drawString("Du hast bereits volle Rüstung",460,30);
		}
		if(speed_voll){
			g.drawString("Du hast bereits maximale Geschwindigkeit",460,30);
		}
		if(gold){
			g.drawString("Du hast nicht genug Gold",460,30);
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
