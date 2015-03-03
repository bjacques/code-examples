package coderetreat;

import java.awt.Point;

public class PointFactory {

	public static Point createRelativePoint(Point p, int dx, int dy) {
		Point copyOfPoint = new Point(p);
		copyOfPoint.translate(dx, dy);
		return copyOfPoint;
	}
}
