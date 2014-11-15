package com.brad.guiapp.conway;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.awt.Point;
import java.util.Set;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Sets;

public class ConwayTest {

	/*			x,y-1	
	 * 	x-1,y	x,y		x+1,y
	 * 			x,y+1
	 * 
	 */
	private Point liveCellOne = new Point(1,1);
	private Rules rules = new Rules();
	
	@Test
	public void returnAllCellsToCheck_givenLiveCells() throws Exception {
		Set<Point> liveCells = Sets.newHashSet(liveCellOne);
		assertThat(
				rules.getAllCellsToCheck(liveCells), 
				containsInAnyOrder(
						new Point(liveCellOne.x, liveCellOne.y+1),
						new Point(liveCellOne.x, liveCellOne.y),
						new Point(liveCellOne.x, liveCellOne.y-1),
						new Point(liveCellOne.x+1, liveCellOne.y+1),
						new Point(liveCellOne.x+1, liveCellOne.y),
						new Point(liveCellOne.x+1, liveCellOne.y-1),
						new Point(liveCellOne.x-1, liveCellOne.y+1),
						new Point(liveCellOne.x-1, liveCellOne.y),
						new Point(liveCellOne.x-1, liveCellOne.y-1))
						);
	}
	
	@Test
	public void returnsCountOf2WhenNumberOfLiveNeighboursHorizontallyIs2() throws Exception {
		Set<Point> liveCells = Sets.newHashSet(liveCellOne, 
				new Point(liveCellOne.x-1,liveCellOne.y), 
				new Point(liveCellOne.x+1,liveCellOne.y));
		
		assertThat(rules.countNeighbours(liveCells, liveCellOne), is(2));
		
	}
	
	@Test
	public void returnsCountOf2WhenNumberOfLiveNeighboursVerticallyIs2() throws Exception {
		Set<Point> liveCells = Sets.newHashSet(liveCellOne, 
				new Point(liveCellOne.x,liveCellOne.y-1), 
				new Point(liveCellOne.x,liveCellOne.y+1));
		
		assertThat(rules.countNeighbours(liveCells, liveCellOne), is(2));
		
	}
	
	@Test
	public void returnsCountOf4WhenNumberOfLiveNeighboursDiagonallyIs4() throws Exception {
		Set<Point> liveCells = Sets.newHashSet(liveCellOne, 
				new Point(liveCellOne.x-1,liveCellOne.y-1), 
				new Point(liveCellOne.x+1,liveCellOne.y+1),
				new Point(liveCellOne.x+1,liveCellOne.y-1),
				new Point(liveCellOne.x-1,liveCellOne.y+1));
		
		assertThat(rules.countNeighbours(liveCells, liveCellOne), is(4));
	}
	
	@Test
	public void singleCellDies_whenNoNeighbours() throws Exception {
		Set<Point> world = Sets.newHashSet(liveCellOne);
		assertThat(rules.tick(world), Matchers.empty());
	}
	
	@Test
	public void singleCellStayAlive_whenHasTwoNeighbours() throws Exception {
		Set<Point> world = Sets.newHashSet(liveCellOne, 
				new Point(liveCellOne.x,liveCellOne.y-1),
				new Point(liveCellOne.x,liveCellOne.y+1));
		assertThat(rules.tick(world), Matchers.contains(liveCellOne));
	}
	
	@Test
	public void singleCellStayAlive_whenHasThreeNeighbours() throws Exception {
		Set<Point> world = Sets.newHashSet(liveCellOne, 
				new Point(liveCellOne.x-1,liveCellOne.y),
				new Point(liveCellOne.x,liveCellOne.y-1),
				new Point(liveCellOne.x,liveCellOne.y+1));
		assertThat(rules.tick(world), Matchers.contains(liveCellOne));
	}
	
}
