package main;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class GeneralizationLine extends BaseLine{
	public GeneralizationLine(Point front, Point to) {
		super(front,to);
	}
	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(5));
		g2.drawLine(front.x, front.y,
				to.x, to.y);
		
		int d = 10, h = 10;
		int dx = to.x - front.x;
		int dy = to.y - front.y;
	    double D = Math.sqrt(dx*dx + dy*dy);
	    double xm = D - d, xn = xm, ym = h, yn = -h, x;
	    double sin = dy / D, cos = dx / D;

	    x = xm*cos - ym*sin + front.x;
	    ym = xm*sin + ym*cos + front.y;
	    xm = x;

	    x = xn*cos - yn*sin + front.x;
	    yn = xn*sin + yn*cos + front.y;
	    xn = x;
	    g2.drawLine((int)xm, (int)ym, to.x, to.y);
	    g2.drawLine((int)xn, (int)yn, to.x, to.y);
	    g2.drawLine((int)xm, (int)ym, (int)xn, (int)yn);
	}
}
