import java.awt.*;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class Player {
	private int x;
	private int y;
	private int dx;
	private int dy;

	
	private Image image;
	
	public Player(){
		ImageIcon im = new ImageIcon("Pic/bild.jpg");
		image = im.getImage();
		x=0; //Start Position des Spielers
		y=0;
	}
	
	public void move(){
	if (x>0 && x<750 && y>0 && y<550){
		x= x+dx;
		y= y+dy;
	}
	if (x<=0){
		y=y+dy;
			if (dx>0){
				x= x+dx;
			}
	
	}
	
	if (x>=750){
		y=y+dy;
		if (dx<0){
			x =x+dx;
		}
	}
	if (y<=0){
		x=x+dx;
		if (dy>0){
			y= y+dy;
		}
	}
	if (y>=550){
		x=x+dx;
		if (dy<0){
			y=y+dy;
		}
	}
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	
	public Image getImage(){
		return image;
	}
	
	public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        
         if (key == KeyEvent.VK_A) {
            dx = -1;
         
        }

        if ((key == KeyEvent.VK_D))  {
            dx = 1;
        
        }

        if (key == KeyEvent.VK_W) {
            dy = -1;
        }

        if (key == KeyEvent.VK_S) {
            dy= 1;
        }
	}
   
	

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) {
            dx = 0;
           
        }

        if (key == KeyEvent.VK_D) {
            dx = 0;
        }

        if (key == KeyEvent.VK_W) {
            dy = 0;
        }

        if (key == KeyEvent.VK_S) {
            dy = 0;
        }
    }


}
