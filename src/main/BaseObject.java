package main;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;

public class BaseObject extends JPanel{
	int width, height;
	int x, y;
	BaseObject me;
	Controller c;
	public BaseObject(Controller c) {
		this.c = c;
	}
	
	private Point start,end;
	public class Listener extends MouseAdapter{

        @Override
        public void mousePressed(MouseEvent e) {
        	start = new Point(e.getPoint());
        	if(c.getMode() == 1) {
        		c.hideAll();
        		showPoint();
        		c.selected(me);
        	}
        	System.out.println(e.getPoint());
        }
        @Override
        public void mouseReleased(MouseEvent e) {
        	x = e.getX();
        	y = e.getY();
        }
	}
	public class MotionListener extends MouseMotionAdapter{
		@Override
		public void mouseDragged(MouseEvent e) {
			if(c.getMode() == 1) {
				end = new Point(me.getX()+(e.getX()-start.x),me.getY()+(e.getY()-start.y));
				me.setLocation(end);
			}
		}
	}
	
	boolean visible = false;
	public void showPoint() {
		visible = true;
		repaint();
	}
	
	public void hidePoint() {
		visible = false;
		repaint();
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);	
	}
	public void changeName(String s) {
		
	}
	public void deleteListener() {
		
	}
}
