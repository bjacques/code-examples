package coderetreat;

import static coderetreat.PointFactory.createRelativePoint;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
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
	private Point cell = new Point(5,5);
	private ConwayRules rules = new ConwayRules();
	
	@Test
	public void returnsAllPossibleCellPositionsThatCanChange_whenWorldTicks_givenLiveCells() throws Exception {
		Set<Point> liveCells = Sets.newHashSet(cell);
		assertThat(
				rules.getAllLocationsThatCanBeAffected(liveCells), 
				containsInAnyOrder(
						createRelativePoint(cell, 0, 1),
						createRelativePoint(cell, 0, -1),
						createRelativePoint(cell, 1, 1),
						createRelativePoint(cell, 1, 0),
						createRelativePoint(cell, 1, -1),
						createRelativePoint(cell, -1, 1),
						createRelativePoint(cell, -1, 0),
						createRelativePoint(cell, -1, -1)
						));
		
		assertThat(
				rules.getAllLocationsThatCanBeAffected(liveCells),
				not(hasItem(cell)));
	}
	
	@Test
	public void countsTwoNeighbours_whenTwoNeighboursExistHorizontally() throws Exception {
		Set<Point> liveCells = Sets.newHashSet(
				cell,
				createRelativePoint(cell, -1, 0),
				createRelativePoint(cell, 1, 0)
				);
		assertThat(rules.countNeighbours(liveCells, cell), is(2));
		
	}
	
	@Test
	public void countsTwoNeighbours_whenTwoNeighboursExistVertically() throws Exception {
		Set<Point> liveCells = Sets.newHashSet(
				cell,
				createRelativePoint(cell, 0, -1),
				createRelativePoint(cell, 0, 1)
				);
		assertThat(rules.countNeighbours(liveCells, cell), is(2));
		
	}
	
	@Test
	public void countsFourNeighbours_whenFourNeighboursExistDiagonally() throws Exception {
		Set<Point> liveCells = Sets.newHashSet(
				cell,
				createRelativePoint(cell, -1, -1),
				createRelativePoint(cell, 1, 1),
				createRelativePoint(cell, 1, -1),
				createRelativePoint(cell, -1, 1)
				);
		assertThat(rules.countNeighbours(liveCells, cell), is(4));
	}
	
	@Test
	public void cellDies_whenNoNeighbours() throws Exception {
		Set<Point> world = Sets.newHashSet(cell);
		assertThat(rules.tick(world), empty());
	}
	
	@Test
	public void cellStaysAlive_whenHasTwoNeighbours() throws Exception {
		Set<Point> world = Sets.newHashSet(
				cell, 
				createRelativePoint(cell, 0, -1),
				createRelativePoint(cell, 0, 1)
				);
		assertThat(rules.tick(world), hasItem(cell));
	}
	
	@Test
	public void cellStaysAlive_whenHasThreeNeighbours() throws Exception {
		Set<Point> world = Sets.newHashSet(
				cell, 
				createRelativePoint(cell, -1, 0),
				createRelativePoint(cell, 0, -1),
				createRelativePoint(cell, 0, 1)
				);
		assertThat(rules.tick(world), hasItem(cell));
	}
	
	@Test
	public void cellDies_whenLessThan_twoNeighbours() throws Exception {
		Set<Point> world = Sets.newHashSet(
				cell,
				createRelativePoint(cell, 1, 0)
				);
		assertThat(rules.tick(world), not(hasItem(cell)));
	}
	
	@Test
	public void cellDies_whenMoreThan_threeNeighbours() throws Exception {
		Set<Point> world = Sets.newHashSet(
				cell,
				createRelativePoint(cell, 1, 0),
				createRelativePoint(cell, 1, 1),
				createRelativePoint(cell, -1, 0),
				createRelativePoint(cell, 0, 1)
				);
		
		assertThat(rules.tick(world), not(hasItem(cell)));
	}
	
	@Test
	public void cellIsBorn_whenExactly_threeNeighbours() throws Exception {
		Set<Point> world = Sets.newHashSet(
				createRelativePoint(cell, 1, 0),
				createRelativePoint(cell, 1, 1),
				createRelativePoint(cell, -1, 0)
				);
		
		assertThat(rules.tick(world), hasItem(cell));
	}
	
	@Test
	public void noCellsDie_whenBlockOfFourCells() throws Exception {
		Set<Point> world = Sets.newHashSet(
				cell,
				createRelativePoint(cell, 0, 1),
				createRelativePoint(cell, 1, 0),
				createRelativePoint(cell, 1, 1)
				);
		
		assertThat(rules.tick(world).size(), is(4));
	}
	
	@Test
	public void threeHorizontalCells_becomeThreeVerticalCells() throws Exception {
		Set<Point> world = Sets.newHashSet(
				cell,
				createRelativePoint(cell, 0, -1),
				createRelativePoint(cell, 0, 1)
				);
		
		assertThat(
				rules.tick(world), 
				containsInAnyOrder(
						cell,
						createRelativePoint(cell, -1, 0),
						createRelativePoint(cell, 1, 0))
				);
	}
}
