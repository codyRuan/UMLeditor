package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Canvas extends JPanel{
	Controller c;
	private Point start, end;
	private static Canvas instance = null;
	
	private Canvas() {
		this.setBackground(Color.GRAY);
		setLayout(null);
		initialize(); 
	}
	public static Canvas getInstance() {
		if(instance == null) {
			instance = new Canvas();
		}
		return instance;
	}
	
	private void initialize() {
		this.c = Controller.getInstance();
		this.addMouseListener(new CanvasListener());
		this.addMouseMotionListener(new CanvasMotionListener());
	}
	
	private boolean dragging = false;
	private class CanvasListener extends MouseAdapter{
	    public void mouseClicked(MouseEvent e) {
	    	switch(c.getMode()) {
	    	case 1:
	    		c.hideAll();
	    		c.selected(null);
	    		System.out.println("set selected to null");
	    		break;
	    	case 5:
	    		BaseObject cd = new ClassDiagram();
		    	cd.setEnabled(true);
		    	cd.setBounds(e.getPoint().x,e.getPoint().y, cd.getPreferredSize().width, cd.getPreferredSize().height);
		    	cd.creatPoint = e.getPoint();
		    	c.addObj(cd);
		    	Canvas.getInstance().add(cd);
		    	Canvas.getInstance().updateUI();
		        break;
	    	case 6:
	    		BaseObject usd = new UseCaseDiagram();
	    		usd.setEnabled(true);
	    		usd.setBounds(e.getPoint().x,e.getPoint().y, usd.getPreferredSize().width, usd.getPreferredSize().height);
	    		usd.creatPoint = e.getPoint();
	    		c.addObj(usd);
	    		Canvas.getInstance().add(usd);
	    		Canvas.getInstance().updateUI();
		        break;
	    	}
	    }
	    public void mousePressed(MouseEvent e) {
	    	start = new Point(e.getPoint());
	    	if(c.getMode() == 1) {
	    		dragging = true;
	    		c.hideAll();
	    	}
	    }
	    public void mouseReleased(MouseEvent e) {
	    	end = new Point(e.getPoint());
	    	if(start != end && c.getMode() == 1) {
	    		c.selectedAll(start,end);
	    	}
	    	dragging = false;
	    	repaint();
	    }
	}
	private class CanvasMotionListener extends MouseMotionAdapter{
		@Override
		public void mouseDragged(MouseEvent e) {
			if(c.getMode() == 1) {
				end = new Point(e.getPoint());
				repaint();
			}
		}
//	    	System.out.println("dragging : "+e.getPoint());
	}
	public void paint(Graphics g) {
		super.paint(g);
		c.drawLine(g);
		Graphics2D g2 = (Graphics2D) g;
		Stroke dashed =  new  BasicStroke ( 3 , BasicStroke . CAP_BUTT , BasicStroke . JOIN_BEVEL ,  0 ,  new  float [ ] { 9 } ,  0 ) ;
		g2.setStroke(dashed);
		g2.setColor(Color.lightGray);
		if(dragging) {
			int lx = Math.min(start.x, end.x);
			int ly = Math.min(start.y, end.y);
			int rx = Math.max(start.x, end.x);
			int ry = Math.max(start.y, end.y);
			int w = Math.abs(start.x-end.x);
			int h = Math.abs(start.y-end.y);
			g2.drawLine(lx, ly, lx+w, ly);
			g2.drawLine(lx, ly, lx, ly+h);
			g2.drawLine(lx+w, ly, rx, ry);
			g2.drawLine(lx, ly+h, rx, ry);
		}
	}
}
