package coderetreat;

import static coderetreat.PointFactory.createRelativePoint;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.Sets;

public class ConwayRules {

	public Set<Point> getAllLocationsThatCanBeAffected(Set<Point> liveCells) {
		Set<Point> points = new HashSet<Point>();
		for(Point cell : liveCells) {
			points.addAll(Sets.newHashSet(
					createRelativePoint(cell, 0, 1),
					createRelativePoint(cell, 0, -1),
					createRelativePoint(cell, 1, 1),
					createRelativePoint(cell, 1, 0),
					createRelativePoint(cell, 1, -1),
					createRelativePoint(cell, -1, 1),
					createRelativePoint(cell, -1, 0),
					createRelativePoint(cell, -1, -1)
					));
		}
		
		return points;
	}

	public int countNeighbours(Set<Point> liveCells, Point liveCellOne) {
		int count = 0;
		
		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++) {
				if(!(i == 0 && j == 0)
						&& liveCells.contains(new Point(liveCellOne.x + i, liveCellOne.y + j))) {
					count++;
				}
			}
		}
		return count;
	}

	public Set<Point> tick(Set<Point> world) {
		
		Set<Point> newWorld = Sets.newHashSet();
		
		for(Point cell : getAllLocationsThatCanBeAffected(world)) {
			
			if(world.contains(cell)
					&& (countNeighbours(world, cell) == 2 || countNeighbours(world, cell) == 3)) {
				newWorld.add(cell);
			}
			else if (countNeighbours(world, cell) == 3) {
				newWorld.add(cell);
			}
		}
		
		return newWorld;
	}
}
