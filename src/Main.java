import javax.swing.*;

public class Main extends JFrame{

	
	private static final long serialVersionUID = 1L;

		public Main() {

	        add(new Spielfeld());

	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setSize(800, 600);
	        setLocationRelativeTo(null);
	        setTitle("Dungeon Crawler");
	        setResizable(false);
	        setVisible(true);
	        setLayout(null);
	    }

	    public static void main(String[] args) {
	        new Main();
	    }
}
