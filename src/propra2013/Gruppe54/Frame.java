package propra2013.Gruppe54;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

public class Frame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static String title ="Dungeon Crawler";
	public static Dimension size = new Dimension(1000,650); 
	
	public static String auswahl[] = {"Level1","Level2","Level3"};
	
	public static Frame frame;
	
    public static Spielfeld spielfeld = new Spielfeld();
    
    public static JButton enter = new JButton("Spielen");
	public static JButton info = new JButton("Info");
	public static JButton schließen = new JButton("Verlassen");
	public static JButton menü = new JButton("Hauptmenü");
	
	@SuppressWarnings("unchecked")
	public static JComboBox levelAuswahl = new JComboBox(auswahl);
	
	/**
	 * Konstruktor
	 */
	public Frame(){
		setTitle(title);
		setSize(size);
		setResizable(false);
		setLayout(null); 
		setLocationRelativeTo(null); //Frame in der Mitte
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//Button Hauptmenü, nur im "Spielmodus" sichtbar
		menü.setBounds(75,25,130,20);
		menü.setVisible(false);
		
		//Anzeige des Menüs
		enter.setBounds(400, 100, 150, 30);		//Button Enter
		enter.setVisible(true);
		add(enter);
		
		info.setBounds(400,150,150,30);			//Button Info
		info.setVisible(true);
		add(info);
		
		schließen.setBounds(400, 200, 150, 30); //Button Schließen
		schließen.setVisible(true);
		add(schließen);
		
		levelAuswahl.setBounds(400, 250, 150, 30);	//ComboBox Levelauswahl
		levelAuswahl.setVisible(true);
		add(levelAuswahl);
		
		//Combobox event
		levelAuswahl.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JComboBox<?> selectedChoice = (JComboBox<?>) e.getSource();
				
				if("Level1".equals(selectedChoice.getSelectedItem())){				//Listenauswahl: Level1
					Spielfeld.current_lvl=1;
					Spielfeld.current_room=1;
				} else if("Level2".equals(selectedChoice.getSelectedItem())){		//Level2
					Spielfeld.current_lvl=2;
					Spielfeld.current_room=1;
				} else if("Level3".equals(selectedChoice.getSelectedItem())){		//Level3
					Spielfeld.current_lvl=3;
					Spielfeld.current_room=1;
				}
				
			}
		});
		
		//Button Enter Click
		enter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//Buttons ausblenden und entfernen
				enter.setVisible(false);
				info.setVisible(false);
				schließen.setVisible(false);
				remove(enter);
				remove(info);
				remove(schließen);
				remove(levelAuswahl);
			
				//Spielfeld anzeigen	
				Spielfeld.level.loadLevel(new File("level/level"+Spielfeld.current_lvl+"_"+Spielfeld.current_room+".lvl"));    //Level laden
				add(spielfeld);						//hinzufügen
				spielfeld.setVisible(true);	
				//Button Hauptmenü hinzufügen
				add(menü);
				menü.setVisible(true);
			}
		});
		
		//Button Hauptmenü Click
		menü.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				menü.setVisible(false);
				remove(menü);
				spielfeld.setVisible(false);
				setLayout(null);
				
				enter.setVisible(true);
				info.setVisible(true);
				schließen.setVisible(true);
				add(enter);
				add(info);
				add(schließen);
				add(levelAuswahl);
				init();
			}
		});
		
		//Button schließen Click
		schließen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		init();
	}

	
	/**
	 * Initialisierung
	 */
	public void init(){
		validate();
		repaint();
		setVisible(true);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Frame frame = new Frame();
		
	}


}
