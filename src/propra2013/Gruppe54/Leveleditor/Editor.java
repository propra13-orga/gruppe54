package propra2013.Gruppe54.Leveleditor;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Editor extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public static String title ="Dungeon Crawler - Leveleditor";
	public static Dimension size = new Dimension(1000,670); 
	public Editorfeld feld = new Editorfeld();
	
	public static JButton open = new JButton("Level laden");
	public static JButton save = new JButton("Level speichern");
	public static JLabel level = new JLabel("Level: "+Editorfeld.current_lvl);
	public static JLabel raum = new JLabel("Raum: "+Editorfeld.current_room);
	public static JLabel info = new JLabel();
	
	public static String auswahl1[] = {"Boden","Mauer1","Mauer2","bewegliche Mauer","Baum1","Baum2","Wasser","Ufer rechts","Ufer links","Ufer oben","Ufer unten","Fackel","Ausgang Dungeon","Ausgang Wald","Eingang Shop","Leer"};
	public static String auswahl2[] = {"Lebenstrank","Manatrank","Supertrank","Pfeile","Schlüssel","Zepter","Herz","Lebensbrunnen","Manabrunnen","Gold1","Gold2","Schatztruhe","QuestNPC","WaldNPC"};
	public static String auswahl3[] = {"Gegner1","Gegner2","Endgegner","Fledermaus","Bewegliche Falle"};
	public static String auswahl4[] = {"Loch","Feuer","Grüner Giftbaum","Speer","Axt","Totenkopf"};
	public static String auswahl5[] = {"Rätsel1","Rätsel2","Rätsel3","Rätsel4","Tor1","Tor2","Tor3"};
	public static JComboBox<?> Raumelemente = new JComboBox<Object>(auswahl1);
	public static JComboBox<?> Items = new JComboBox<Object>(auswahl2);
	public static JComboBox<?> Gegner = new JComboBox<Object>(auswahl3);
	public static JComboBox<?> Fallen = new JComboBox<Object>(auswahl4);
	public static JComboBox<?> QuestRätsel = new JComboBox<Object>(auswahl5);
	
	/**
	 * Konstruktor Frame
	 */
	public Editor(){
		setTitle(title);
		setSize(size);
		setResizable(false);
		setLayout(null); 
		setFocusable(true);
		setLocationRelativeTo(null); //Frame in der Mitte
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Raumelemente.setBounds(25, 50, 150, 22);
		Raumelemente.setVisible(true);
		add(Raumelemente);
		Items.setBounds(200, 50, 150, 22);
		Items.setVisible(true);
		add(Items);
		Gegner.setBounds(375, 50, 150, 22);
		Gegner.setVisible(true);
		add(Gegner);
		Fallen.setBounds(550, 50, 150, 22);
		Fallen.setVisible(true);
		add(Fallen);
		QuestRätsel.setBounds(725, 50, 150, 22);
		QuestRätsel.setVisible(true);
		add(QuestRätsel);
		
		level.setBounds(375, 15, 75, 10);
		raum.setBounds(450,15,75,10);
		info.setFont(new Font("Lucida Sans Typewriter",Font.PLAIN,11));
		info.setText("<html><body>Es können nur bereits bestehende Level bearbeitet und abgespeichert werden.</body></html>");
		info.setBounds(375,5,350,40);
		info.setVisible(false);
		add(info);
		
		open.setBounds(25, 15, 150, 18);
		open.setVisible(true);
		open.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) { //Opendialog
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("level/"));
		        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
		        	String name = chooser.getSelectedFile().getName();		
		        	Editorfeld.raum.loadLevel(new File("level/"+name));		//Pfad festlegen
		        	Editorfeld.current_lvl = Integer.parseInt(String.valueOf(name.charAt(5)));		//in Integer umwandeln
		        	Editorfeld.current_room = Integer.parseInt(String.valueOf(name.charAt(7)));
		        	Editorfeld.loadImages();								//Bilder des jeweiligen Levels laden
		        	feld.checkExistence();									//Prüfen ob auf neuem Spielfeld Gegner vorhanden sind
		        	validate();
		        	repaint();
		        	level.setText("Level: "+Editorfeld.current_lvl);
		        	add(level);
		        	raum.setText("Raum: "+Editorfeld.current_room);
		        	add(raum);
		        }
			}
		});
		open.addMouseListener(new MouseAdapter() {							//MouseOver Effekt um Info anzuzeigen
            @Override
            public void mouseExited(MouseEvent e) {
            	info.setVisible(false);
            	level.setVisible(true);
            	raum.setVisible(true);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            	info.setVisible(true);
            	level.setVisible(false);
            	raum.setVisible(false);
            }
        });
		add(open);
		
		save.setBounds(200, 15, 150, 18);
		save.setVisible(true);
		save.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("level/"));
		        if(chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){//Savedialog
		        	Editorfeld.raum.saveLevel();
		        }
			}
		});
		save.addMouseListener(new MouseAdapter() {								//MouseOver Effekt um Info anzuzeigen
            @Override
            public void mouseExited(MouseEvent e) {
            	info.setVisible(false);
            	level.setVisible(true);
            	raum.setVisible(true);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            	info.setVisible(true);
            	level.setVisible(false);
            	raum.setVisible(false);
            }
        });
		add(save);
		
		//Combobox event
		Raumelemente.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JComboBox<?> selectedChoice = (JComboBox<?>) e.getSource();
				if("Boden".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 0;
				} else if("Mauer1".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 1;
				} else if("Mauer2".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 4;
				} else if("Fackel".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 2;
				} else if("Ausgang Dungeon".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 5;
				} else if("Ausgang Wald".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 40;
				} else if("Eingang Shop".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 26;
				} else if("Leer".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 3;
				} else if("Baum1".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 41;
				} else if("Baum2".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 42;
				} else if("Wasser".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 43;
				} else if("Ufer rechts".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 46;
				} else if("Ufer links".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 47;
				} else if("Ufer oben".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 48;
				} else if("Ufer unten".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 45;
				} else if("bewegliche Mauer".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 51;
				} 
			}
		});
		
		Items.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JComboBox<?> selectedChoice = (JComboBox<?>) e.getSource();
				if("Lebenstrank".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 13;
				} else if("Manatrank".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 14;
				} else if("Zepter".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 18;
				} else if("Lebensbrunnen".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 15;
				} else if("Manabrunnen".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 17;
				} else if("Gold1".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 32;
				} else if("Schatztruhe".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 31;
				} else if("Supertrank".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 16;
				} else if("Herz".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 33;
				} else if("Schlüssel".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 6;
				} else if("Gold2".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 54;
				} else if("Pfeile".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 55;
				} else if("QuestNPC".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 34;
				} else if("WaldNPC".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 20;
				}   
			}
		});
		
		Gegner.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JComboBox<?> selectedChoice = (JComboBox<?>) e.getSource();
				if("Gegner1".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 35;
				} else if("Gegner2".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 36;
				} else if("Endgegner".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 37;
				} else if("Fledermaus".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 39;
				} else if("Bewegliche Falle".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 38;
				}
			}
		});
		
		Fallen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JComboBox<?> selectedChoice = (JComboBox<?>) e.getSource();
				if("Loch".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 7;
				} else if("Feuer".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 8;
				} else if("Grüner Giftbaum".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 10;
				} else if("Speer".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 9;
				} else if("Axt".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 11;
				} else if("Totenkopf".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 12;
				}
			}
		});
		
		QuestRätsel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JComboBox<?> selectedChoice = (JComboBox<?>) e.getSource();
				if("Rätsel1".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 59;
				} else if("Rätsel2".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 60;
				} else if("Rätsel3".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 61;
				} else if("Rätsel4".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 62;
				} else if("Tor1".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 56;
				} else if("Tor2".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 57;
				} else if("Tor3".equals(selectedChoice.getSelectedItem())){
					Editorfeld.current_id = 58;
				}
			}
		});
		
		add(feld);
		validate();
		repaint();
		setVisible(true);
	}
	public static void main(String[] args) {
		Editor editor = new Editor();
	}
}
