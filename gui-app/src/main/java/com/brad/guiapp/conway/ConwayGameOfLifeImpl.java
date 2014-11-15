package com.brad.guiapp.conway;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

public class ConwayGameOfLifeImpl implements ConwayGameOfLife {

	private int dimension;
	private Set<Point> world = new HashSet<>();
	
	public ConwayGameOfLifeImpl(int dimension, Set<Point> seed) {
		this.dimension = dimension;
		this.world = seed;
	}
	
	public int getWorldDimension() {
		return dimension;
	}

	@Override
	public java.util.Set<Point> incrementAndGetWorld() {
		return world;
	}

}
