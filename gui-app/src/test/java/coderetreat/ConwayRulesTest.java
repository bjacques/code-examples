package coderetreat;

import static coderetreat.PointFactory.createRelativePoint;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.awt.Point;
import java.util.Set;

import org.junit.Test;

import com.google.common.collect.Sets;

public class ConwayRulesTest {

	/*			x,y-1	
	 * 	x-1,y	x,y		x+1,y
	 * 			x,y+1
	 * 
	 */
	private Point cell = new Point(1,1);
	private ConwayRules rules = new ConwayRules();
	
	@Test
	public void returnAllCellsToCheck_givenLiveCells() throws Exception {
		Set<Point> liveCells = Sets.newHashSet(cell);
		assertThat(
				rules.getAllCellsToCheck(liveCells), 
				containsInAnyOrder(
						new Point(cell.x, cell.y+1),
						new Point(cell.x, cell.y),
						new Point(cell.x, cell.y-1),
						new Point(cell.x+1, cell.y+1),
						new Point(cell.x+1, cell.y),
						new Point(cell.x+1, cell.y-1),
						new Point(cell.x-1, cell.y+1),
						new Point(cell.x-1, cell.y),
						new Point(cell.x-1, cell.y-1))
						);
	}
	
	@Test
	public void returnsCountOf2WhenNumberOfLiveNeighboursHorizontallyIs2() throws Exception {
		Set<Point> liveCells = Sets.newHashSet(cell, 
				new Point(cell.x-1,cell.y), 
				new Point(cell.x+1,cell.y));
		
		assertThat(rules.countNeighbours(liveCells, cell), is(2));
		
	}
	
	@Test
	public void returnsCountOf2WhenNumberOfLiveNeighboursVerticallyIs2() throws Exception {
		Set<Point> liveCells = Sets.newHashSet(cell, 
				new Point(cell.x,cell.y-1), 
				new Point(cell.x,cell.y+1));
		
		assertThat(rules.countNeighbours(liveCells, cell), is(2));
		
	}
	
	@Test
	public void returnsCountOf4WhenNumberOfLiveNeighboursDiagonallyIs4() throws Exception {
		Set<Point> liveCells = Sets.newHashSet(cell, 
				new Point(cell.x-1,cell.y-1), 
				new Point(cell.x+1,cell.y+1),
				new Point(cell.x+1,cell.y-1),
				new Point(cell.x-1,cell.y+1));
		
		assertThat(rules.countNeighbours(liveCells, cell), is(4));
	}
	
	@Test
	public void singleCellDies_whenNoNeighbours() throws Exception {
		Set<Point> world = Sets.newHashSet(cell);
		assertThat(rules.tick(world), empty());
	}
	
	@Test
	public void singleCellStayAlive_whenHasTwoNeighbours() throws Exception {
		Set<Point> world = Sets.newHashSet(cell, 
				new Point(cell.x,cell.y-1),
				new Point(cell.x,cell.y+1));
		assertThat(rules.tick(world), hasItem(cell));
	}
	
	@Test
	public void singleCellStayAlive_whenHasThreeNeighbours() throws Exception {
		Set<Point> world = Sets.newHashSet(
				cell, 
				createRelativePoint(cell, -1, 0),
				createRelativePoint(cell, 0, -1),
				createRelativePoint(cell, 0, 1));

		assertThat(rules.tick(world), hasItem(cell));
	}
	
}
