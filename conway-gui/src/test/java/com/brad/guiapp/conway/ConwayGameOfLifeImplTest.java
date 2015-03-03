package com.brad.guiapp.conway;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.awt.Point;
import java.util.Collections;
import java.util.Set;

import org.junit.Test;

public class ConwayGameOfLifeImplTest {

	@Test
	public void emptySeed_returnsEmptyWorld()
	{
		int dimension = 100;
		Set<Point> emptySeed = Collections.emptySet();
		
		ConwayGameOfLife game = new ConwayGameOfLifeImpl(dimension, emptySeed);
		
		assertThat(game.incrementAndGetWorld().size(), is(0));
	}
	
	@Test
	public void liveCellWithZeroNeighbourDies() throws Exception {
		
	}
	
	@Test
	public void liveCellWithOneNeighbourDies() throws Exception {
		
	}
	
	@Test
	public void liveCellWithTwoNeighboursLives() throws Exception {
		
	}

	@Test
	public void liveCellWithThreeNeighboursLives() throws Exception {
		
	}

	@Test
	public void liveCellWithFourNeighboursDies() throws Exception {
		
	}
}