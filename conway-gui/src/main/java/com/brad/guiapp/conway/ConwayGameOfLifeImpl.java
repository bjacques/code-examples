package com.brad.guiapp.conway;

import java.awt.Point;
import java.util.Set;

import coderetreat.ConwayRules;

import com.google.common.collect.Sets;

public class ConwayGameOfLifeImpl implements ConwayGameOfLife {

	private final ConwayRules rules = new ConwayRules();
	private int dimension;
	private Set<Point> world = Sets.newHashSet();
	
	public ConwayGameOfLifeImpl(int dimension, Set<Point> seed) {
		this.dimension = dimension;
		this.world = seed;
	}
	
	public int getWorldDimension() {
		return dimension;
	}

	public Set<Point> incrementAndGetWorld() {
		Set<Point> nextWorld = rules.tick(world);
		world = nextWorld;
		return nextWorld;
	}

}
