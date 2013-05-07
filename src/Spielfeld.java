import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class Spielfeld extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private Timer timer;
	private Player player;
	
	public Spielfeld(){
        addKeyListener(new TAdapter()); //TAdapter ist unten deklariert
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true); //Bildschirm flackern vermeiden

        player = new Player(); //Player ist in einer seperaten Klasse definiert

        timer = new Timer(5, this); //die 5 steht für 5 ms
        timer.start();
	}
	
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
       
    }

    
    public void actionPerformed(ActionEvent e) {
        player.move();
        repaint();  
    }
    
    private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }
    }
    

}
