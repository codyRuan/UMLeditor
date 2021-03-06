package line;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class CompositionLine extends BaseLine{
	public CompositionLine(Point front, Point to) {
		super(front,to);
	}
	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(4));
		
		int d = 10, h = 10;
		int dx = to.x - front.x;
		int dy = to.y - front.y;
	    double D = Math.sqrt(dx*dx + dy*dy);
	    double xm = D - d, xn = xm, ym = h, yn = -h, x;
	    double cx, cy, fx, fy;
	    double sin = dy / D, cos = dx / D;

	    x = xm*cos - ym*sin + front.x;
	    ym = xm*sin + ym*cos + front.y;
	    xm = x;

	    x = xn*cos - yn*sin + front.x;
	    yn = xn*sin + yn*cos + front.y;
	    xn = x;
	    cx = (xm + xn) / 2;
	    cy = (ym + yn) /2;
	    fx = 2*cx - to.x;
	    fy = 2*cy - to.y;
	    g2.drawLine(front.x, front.y, (int)fx, (int)fy);
	    g2.drawLine((int)xm, (int)ym, to.x, to.y);
	    g2.drawLine((int)xn, (int)yn, to.x, to.y);
	    g2.drawLine((int)xm, (int)ym, (int)fx, (int)fy);
	    g2.drawLine((int)xn, (int)yn, (int)fx, (int)fy);
	}
}
