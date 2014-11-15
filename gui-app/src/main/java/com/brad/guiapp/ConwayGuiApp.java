package com.brad.guiapp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ConwayGuiApp extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private final int PIXEL_WIDTH = 4;

	private int worldDimension;
	
	private int generationCount;
	
	private Set<Point> world = Collections.emptySet();
	
	
	public ConwayGuiApp(int worldDimension) {
		this.worldDimension = worldDimension;
	}

	
	@Override
	public java.awt.Dimension getPreferredSize() {
		return new Dimension(worldDimension * PIXEL_WIDTH, worldDimension * PIXEL_WIDTH);
	};
	
	@Override
	protected void paintComponent(java.awt.Graphics g) {
		super.paintComponent(g);
		Color gColor = g.getColor();
		paintBoard(g);
		paintCells(g);
		g.setColor(gColor);
	};
	
    private void paintCells(Graphics g) {
    	for(Point p : world) {
    		g.fillRect(p.x * PIXEL_WIDTH, p.y * PIXEL_WIDTH, PIXEL_WIDTH, PIXEL_WIDTH);
    	}
	}

	private void paintBoard(Graphics g) {
		g.drawString("Generation: " + generationCount, 0, g.getFontMetrics().getHeight());
		this.setBackground(Color.WHITE);		
	}

	protected void setWorld(Set<Point> gameState) {
		world = gameState;
		generationCount++;
	}
}
