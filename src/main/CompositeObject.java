package main;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JPanel;

public class CompositeObject extends BaseObject{
	int leftX = 1000, leftY = 1000;
	int rightX = 0, rightY = 0;
	ArrayList<BaseObject> allObject;
	public CompositeObject(Controller c) {
		super(c);
		setOpaque(false);
	}
	public CompositeObject(Controller c, ArrayList<BaseObject> list) {
		super(c);
		allObject = new ArrayList(list);
		initialize();
		this.setLayout(null);
		this.setOpaque(false);
		this.setEnabled(true);
		this.setBounds(leftX, leftY, rightX-leftX, rightY-leftY);
		creatPoint = new Point(leftX,leftY);
		for(BaseObject obj : list) {
			obj.x = obj.getX();
			obj.y = obj.getY();
			obj.setLocation(new Point(obj.getX()-leftX, obj.getY()-leftY));
			obj.deleteListener();
			UMLFrame.canvas.remove(obj);
			obj.hidePoint();
			c.removeObj(obj);
			this.add(obj);
		}
		MouseAdapter listener = new Listener();
        MouseMotionAdapter Mlistener = new MotionListener();
		this.addMouseListener(listener);
        this.addMouseMotionListener(Mlistener);
        width = this.getPreferredSize().width;
        height = this.getPreferredSize().height;
        me = this;
        c.hideAll();
        this.showPoint();
	}
	
	private void initialize() {
		for(BaseObject obj : allObject) {
			if(obj.getX() <= leftX) {
				leftX = obj.getX();
			}
			if(obj.getY() <= leftY) {
				leftY = obj.getY();
			}
			if(obj.getX()+obj.getWidth() >= rightX) {
				rightX = obj.getX()+obj.getWidth();
			}
			if(obj.getY()+obj.getHeight() >= rightY) {
				rightY = obj.getY()+obj.getHeight();
			}
		}
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);	
		if(visible) {
			g.fillOval(width/2-4, height-8, 8, 8);
			g.fillOval(width/2-4, 0, 8, 8);
			g.fillOval(0, height/2-4, 8, 8);
			g.fillOval(width-8, height/2-4, 8, 8);
		}
	}
}
