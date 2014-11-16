package com.brad.guiapp;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.Timer;

import com.brad.guiapp.conway.ConwayGameOfLife;
import com.brad.guiapp.conway.ConwayGameOfLifeImpl;
import com.google.common.collect.Sets;

public class Main {

	public static void main( String[] args )
    {
		int worldDimension = 100;
		
		Set<Point> seedWorld =
                Sets.newHashSet(
                        new Point(46, 44),
                        new Point(47, 44),
                        new Point(48, 44),
                        new Point(52, 44),
                        new Point(53, 44),
                        new Point(54, 44),
                        new Point(44, 46),
                        new Point(49, 46),
                        new Point(51, 46),
                        new Point(56, 46),
                        new Point(44, 47),
                        new Point(49, 47),
                        new Point(51, 47),
                        new Point(56, 47),
                        new Point(44, 48),
                        new Point(49, 48),
                        new Point(51, 48),
                        new Point(56, 48),
                        new Point(46, 49),
                        new Point(47, 49),
                        new Point(48, 49),
                        new Point(52, 49),
                        new Point(53, 49),
                        new Point(54, 49),
                        new Point(46, 51),
                        new Point(47, 51),
                        new Point(48, 51),
                        new Point(52, 51),
                        new Point(53, 51),
                        new Point(54, 51),
                        new Point(44, 52),
                        new Point(49, 52),
                        new Point(51, 52),
                        new Point(56, 52),
                        new Point(44, 53),
                        new Point(49, 53),
                        new Point(51, 53),
                        new Point(56, 53),
                        new Point(44, 54),
                        new Point(49, 54),
                        new Point(51, 54),
                        new Point(56, 54),
                        new Point(46, 56),
                        new Point(47, 56),
                        new Point(48, 56),
                        new Point(52, 56),
                        new Point(53, 56),
                        new Point(54, 56)
                );
		
    	ConwayGameOfLifeImpl game = new ConwayGameOfLifeImpl(worldDimension, seedWorld);
    	
    	createAndStartGui(game, worldDimension);
    }

	private static void createAndStartGui(final ConwayGameOfLife game, final int worldDimension)
	{
		final ConwayGuiApp app = new ConwayGuiApp(worldDimension);
		JFrame frame = new JFrame();
    	frame.getContentPane().add(app);
    	frame.pack();    	
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        new Timer(500, new ActionListener() {
        	
            public void actionPerformed(ActionEvent e) {
            	app.setWorld(game.incrementAndGetWorld());
                app.repaint();
            }

        }).start();
	}
}
