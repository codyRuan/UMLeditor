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
	final ArrayList<Integer> group = new ArrayList<>();
	final ArrayList<BaseObject> myteems = new ArrayList<>();
	private final ArrayList<Point> directionList = new ArrayList<>();
	final ArrayList<MyPair> allLineList = new ArrayList<>();
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
        		if(!me.group.isEmpty()) {
        			for(BaseObject b : me.myteems) {
						b.showPoint();
					}
        		}
        		else {
        			showPoint();
        		}
        		c.selected(me);
        		break;
        	case 2:
        	case 3:
        	case 4:
        		start = me.getNearPoint(e.getPoint());
        		start = new Point(start.x+me.getX(), start.y+me.getY());
        		break;
        	default:
        		break;
        	}
        }
        @Override
        public void mouseReleased(MouseEvent e) {
        	x = e.getX();
        	y = e.getY();
        	BaseObject toObj = c.getEnteredObj();
        	if(c.getMode() != 1 && c.getMode() != 5 && c.getMode() != 6) {
        		if(toObj != null && toObj != me) {
        			end = new Point(e.getX()-(toObj.getX()-me.getX()),
        					e.getY()-(toObj.getY()-me.getY()));
            		end = toObj.getNearPoint(end);
            		end = new Point(end.x+toObj.getX(),end.y+toObj.getY());
        			BaseLine bl = new BaseLine();
		        	switch(c.getMode()) {
		        	case 2:		        		
	            		bl = new AssociationLine(start,end);	              	
		        		break;
		        	case 3:
		        		bl = new GeneralizationLine(start,end);
		        		break;
		        	case 4:
		        		bl = new BaseLine(start,end);
		        		break;
		        	}
		        	c.addLine(bl);
            		MyPair pair = new MyPair(bl,true);
            		me.allLineList.add(pair);
            		pair = new MyPair(bl,false);
            		toObj.allLineList.add(pair);
            		UMLFrame.canvas.repaint();
        		}
        	}
        }
		@Override
        public void mouseEntered(MouseEvent e) {
        	updateProperties();
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
				if(!me.group.isEmpty()) {
					for(BaseObject b : me.myteems) {
						b.move(e, start);
					}
				}
				else
					move(e, start);
				UMLFrame.canvas.repaint();
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
	public void move(MouseEvent e, Point start) {
		end = new Point(me.getX()+(e.getX()-start.x),me.getY()+(e.getY()-start.y));
		me.setLocation(end);
		// move line
		for(MyPair pair : allLineList) {
			c.lineList.remove(pair.first);
			if(pair.second)
				pair.first.front 
				= new Point(pair.first.front.x+(e.getX()-start.x),
						pair.first.front.y+(e.getY()-start.y));
			else {
				pair.first.to = new Point(pair.first.to.x+(e.getX()-start.x),pair.first.to.y+(e.getY()-start.y));
			}
			c.lineList.add(pair.first);
		}
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
