package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;

public class Canvas extends JPanel{
	CanvasListener listener;
	CanvasMotionListener Mlistener;
	Controller c;
	private Point start, end;
	
	public Canvas(Controller c) {
		this.c = c;
		this.setBackground(Color.GRAY);
		initialize(); 
	}
	
	private void initialize() {
		listener = new CanvasListener();
		Mlistener = new CanvasMotionListener();
		this.addMouseListener(listener);
        this.addMouseMotionListener(Mlistener);
	}
	
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
	    	c.hideAll();
	    }
	    public void mouseReleased(MouseEvent e) {
	    	end = new Point(e.getPoint());
	    	if(start != end) {
	    		c.selectedAll(start,end);
	    	}
	    }
	}
	private class CanvasMotionListener extends MouseMotionAdapter{
		@Override
		public void mouseDragged(MouseEvent e) {
			
		}
//	    	System.out.println("dragging : "+e.getPoint());
	}
	public void paint(Graphics g) {
		super.paint(g);
		c.drawLine(g);
	}
}
