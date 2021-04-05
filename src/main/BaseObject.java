package main;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JPanel;

public class BaseObject extends JPanel{
	int width, height;
	int x, y;
	Point creatPoint;
	private final ArrayList<Point> directionList = new ArrayList<>();
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
        	updateProperties();
        	switch(c.getMode()) {
        	case 1:
        		c.hideAll();
        		showPoint();
        		c.selected(me);
        		break;
        	case 2 | 3 | 4:
        		start = me.getNearPoint(e.getPoint());
        	}
        }
        @Override
        public void mouseReleased(MouseEvent e) {
        	x = e.getX();
        	y = e.getY();
        	BaseObject toObj = c.getEnteredObj();
        	
        	switch(c.getMode()) {
        	case 2:
        		if(toObj != null && toObj != me) {
            		end = toObj.getNearPoint(e.getPoint());
            		BaseLine bl = new BaseLine(start,end);
            		c.addLine(bl);
            	}
        		break;
        	case 3:
        		break;
        	case 4:
        		break;
        	}
        }
        @Override
        public void mouseEntered(MouseEvent e) {
			System.out.println("line point to");
			c.entered(me);
		}
        @Override
        public void mouseExited(MouseEvent e) {
        	c.entered(null);
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
	public void updateProperties() {
		directionList.add(new Point(x+width,y+height/2));//east
		directionList.add(new Point(x,y+height/2));//west
		directionList.add(new Point(x+width/2,y+height));//south
		directionList.add(new Point(x+width/2,y));//north
	}
	public Point getNearPoint(Point realsePoint) {
		double min = 10000;
		Point p = new Point();
		for(Point point : directionList) {
			if(Math.hypot(realsePoint.x-point.x,realsePoint.y-point.y) < min){
				min = Math.hypot(realsePoint.x-point.x,realsePoint.y-point.y);
				p = point;
			}
		}
		return p;
	}
}
