package propra2013.Gruppe54;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Spielerinfo extends JPanel implements Runnable{

	public Thread info = new Thread(this);
	public static boolean anzeige = false,preis_axt = false,item_vorhanden = false,preis_trank = false,preis_mana = false,preis_ruestung1 = false,preis_ruestung2 = false,preis_stiefel = false,ruestung_voll = false,speed_voll = false,gold = false;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Spielerinfo(){
		setBounds(25,55,160,400);
		info.start();
	}
	
	public void paintComponent(Graphics g){
		g.clearRect(0, 0, 160, 300);
		g.drawRoundRect(0, 0, 158, 200, 10, 10);
		g.drawString("Level: "+Spielfeld.current_lvl,4,30);
		g.drawString("Gold: "+Spielfeld.spieler.gold,4,45);
		//Lebensanzeige
		if(spieler.leben>0){
			if(spieler.leben>40){
				g.setColor(Color.green);
			} else {
				g.setColor(Color.red);
			}
			g.fill3DRect(4, 55, (int) (spieler.leben*1.5), 7, true);
		}
	
		if(spieler.superleben > 0){
			if(spieler.superleben == 1){
				g.drawImage(new ImageIcon("pics/herz.png").getImage(), 24, 145, null, null);
			} else if(spieler.superleben == 2){
				g.drawImage(new ImageIcon("pics/herz.png").getImage(), 24, 145, null, null);
				g.drawImage(new ImageIcon("pics/herz.png").getImage(), 47, 145, null, null);
			} else if(spieler.superleben == 3){
				g.drawImage(new ImageIcon("pics/herz.png").getImage(), 24, 145, null, null);
				g.drawImage(new ImageIcon("pics/herz.png").getImage(), 47, 145, null, null);
				g.drawImage(new ImageIcon("pics/herz.png").getImage(), 70, 145, null, null);
			} else if(spieler.superleben == 4){
				g.drawImage(new ImageIcon("pics/herz.png").getImage(), 24, 145, null, null);
				g.drawImage(new ImageIcon("pics/herz.png").getImage(), 47, 145, null, null);
				g.drawImage(new ImageIcon("pics/herz.png").getImage(), 70, 145, null, null);
				g.drawImage(new ImageIcon("pics/herz.png").getImage(), 93, 145, null, null);
			}
		}
		
		//Mana
		if(spieler.mana>0){
			g.setColor(Color.blue);
			g.fill3DRect(4, 65, (int) (spieler.mana*1.5), 7, true);
		}
		//Rüstung
		if(spieler.ruestung>0){
			g.setColor(Color.gray);
			g.fill3DRect(4, 75, (int) (spieler.ruestung*1.5), 7, true);
		}
		//Inventar
		g.setColor(Color.black);
		g.drawString("Inventar: ",4,100);
		if((Spielfeld.spieler.item_mana==0)&&(Spielfeld.spieler.item_trank==0)){
			g.drawString("leer",50,120);
		}
		if(Spielfeld.spieler.item_trank>0){
			g.drawImage(new ImageIcon("pics/shop_trank.png").getImage(), 4, 105, null);
			g.drawString(""+Spielfeld.spieler.item_trank, 34, 125);
		}
		if(Spielfeld.spieler.item_mana>0){
			g.drawImage(new ImageIcon("pics/shop_mana.png").getImage(), 59, 105, null);
			g.drawString(""+Spielfeld.spieler.item_mana, 89, 125);
		}
		//Gespräch
		if((anzeige)&&(Spielfeld.shop)){
			g.drawString("Händler: Möchtest du",0,250);
			g.drawString("zum Shop gebracht",0,265);
			g.drawString("werden?",0,280);
		}
		//Preisanzeige Trank
		if(preis_trank){
			g.drawString("50 Gold",0,250);
		}
		//Preisanzeige Manatrank
		if(preis_mana){
			g.drawString("75 Gold",0,250);
		}
		//Preisanzeige Manatrank
		if(preis_ruestung1){
			g.drawString("150 Gold",0,250);
			g.drawString("Rüstung 100%",0,265);
		}
		//Preisanzeige Manatrank
		if(preis_ruestung2){
			g.drawString("100 Gold",0,250);
			g.drawString("Rüstung +50%",0,265);
		}
		//Preisanzeige Stiefel
		if(preis_stiefel){
			g.drawString("150 Gold",0,250);
			g.drawString("Geschwindigkeit +10%",0,265);
		}
		//Preisanzeige Stiefel
		if(preis_axt){
			g.drawString("500 Gold",0,250);
		}
		if(item_vorhanden){
			g.drawString("Du besitzt dieses Item",0,250);
			g.drawString("bereits",0,265);
		}
		if(ruestung_voll){
			g.drawString("Du hast bereits volle",0,250);
			g.drawString("Rüstung",0,265);
		}
		if(speed_voll){
			g.drawString("Du hast bereits",0,250);
			g.drawString("maximale Geschwindigkeit",0,265);
		}
		if(gold){
			g.drawString("Du hast nicht genug",0,250);
			g.drawString("Gold",0,265);
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
