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
	CanvasListener listener;
	CanvasMotionListener Mlistener;
	Controller c;
	private Point start, end;
	
	public Canvas(Controller c) {
		this.c = c;
		this.setBackground(Color.GRAY);
		setLayout(null);
		initialize(); 
	}
	
	private void initialize() {
		listener = new CanvasListener();
		Mlistener = new CanvasMotionListener();
		this.addMouseListener(listener);
        this.addMouseMotionListener(Mlistener);
	}
	
	private boolean dragging = false;
	private class CanvasListener extends MouseAdapter{
	    public void mouseClicked(MouseEvent e) {
	    	switch(c.getMode()) {
	    	case 1:
	    		c.hideAll();
	    		c.selected(null);
	    		break;
	    	case 5:
		    	ClassDiagram cd = new ClassDiagram(c);
		    	cd.setEnabled(true);
		    	cd.setBounds(e.getPoint().x,e.getPoint().y, cd.getPreferredSize().width, cd.getPreferredSize().height);
		    	cd.creatPoint = e.getPoint();
		    	c.addObj(cd);
		        UMLFrame.canvas.add(cd);
		        UMLFrame.canvas.updateUI();
		        break;
	    	case 6:
	    		UseCaseDiagram usd = new UseCaseDiagram(c);
	    		usd.setEnabled(true);
	    		usd.setBounds(e.getPoint().x,e.getPoint().y, usd.getPreferredSize().width, usd.getPreferredSize().height);
	    		usd.creatPoint = e.getPoint();
	    		c.addObj(usd);
	    		UMLFrame.canvas.add(usd);
		        UMLFrame.canvas.updateUI();
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
