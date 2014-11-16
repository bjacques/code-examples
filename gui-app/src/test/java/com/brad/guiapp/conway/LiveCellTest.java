package com.brad.guiapp.conway;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.awt.Point;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.google.common.collect.Sets;

@RunWith(Parameterized.class)
public class LiveCellTest {

	private final int worldDimension = 100;
	
	private static final int x = 10;
	private static final int y = 20;
	private static final Point cell = new Point(x, y);
	
	/*			y-1					7	8	1
	 * 	x-1		x y		x+1			6	.	2
	 * 			y+1 				5	4	3
	 */
	private static final Point neighbourOne = new Point(x+1, y-1);
	private static final Point neighbourTwo = new Point(x+1, y);
	private static final Point neighbourThree = new Point(x+1, y+1);
	private static final Point neighbourFour = new Point(x, y+1);
	private static final Point neighbourFive = new Point(x-1, y+1);
	private static final Point neighbourSix = new Point(x-1, y);
	private static final Point neighbourSeven = new Point(x-1, y-1);
	private static final Point neighbourEight = new Point(x, y-1);

	private Set<Point> seedWorld;

	private Boolean isAliveAfterIncrement;
	
	public LiveCellTest(Set<Point> cells, Boolean expectedState) {
		this.seedWorld = cells;
		this.isAliveAfterIncrement = expectedState;
	}
	
	@Parameters
	public static List<Object[]> neighbouringCellsAndExpectedStateAfterIncrement() {
		return Arrays.asList(new Object[][] {
			{Sets.newHashSet(),
				Boolean.FALSE},
			{Sets.newHashSet(cell),
				Boolean.FALSE},
			{Sets.newHashSet(cell, neighbourOne),
				Boolean.FALSE},
			{Sets.newHashSet(cell, neighbourOne, neighbourTwo),
				Boolean.TRUE},
			{Sets.newHashSet(cell, neighbourOne, neighbourTwo, neighbourThree),
				Boolean.TRUE},
			{Sets.newHashSet(cell, neighbourOne, neighbourTwo, neighbourThree, neighbourFour),
				Boolean.FALSE},	
			{Sets.newHashSet(cell, neighbourOne, neighbourTwo, neighbourThree, neighbourFour, neighbourFive),
				Boolean.FALSE},	
			{Sets.newHashSet(cell, neighbourOne, neighbourTwo, neighbourThree, neighbourFour, neighbourFive, neighbourSix),
				Boolean.FALSE},	
			{Sets.newHashSet(cell, neighbourOne, neighbourTwo, neighbourThree, neighbourFour, neighbourFive, neighbourSix, neighbourSeven),
				Boolean.FALSE},	
			{Sets.newHashSet(cell, neighbourOne, neighbourTwo, neighbourThree, neighbourFour, neighbourFive, neighbourSix, neighbourSeven, neighbourEight),
				Boolean.FALSE},	
		});
	}
			
	@Test
	public void aLiveCell_livesOrDies_dependingOnTheNumberOfNeighbours() {
		ConwayGameOfLifeImpl game = new ConwayGameOfLifeImpl(this.worldDimension, this.seedWorld);
//		assertThat(
//				game.incrementAndGetWorld().contains(cellUnderTest), 
//				is(isAliveAfterIncrement));
	}

}
