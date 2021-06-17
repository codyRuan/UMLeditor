package line;

import java.awt.Graphics;
import java.awt.Point;

public class BaseLine {
	Point front, to;
	public BaseLine() {
		
	}
	public BaseLine(Point front, Point to) {
		this.front = front;
		this.to = to;
	}
	public void draw(Graphics g) {
//		g.drawLine(front.x, front.y,
//				to.x, to.y);
	}
	public Point getFront() {
		return front;
	}
	public Point getTo() {
		return to;
	}
	public void setFront(Point f) {
		this.front = f;
	}
	public void setTo(Point t) {
		this.to = t;
	}
}
