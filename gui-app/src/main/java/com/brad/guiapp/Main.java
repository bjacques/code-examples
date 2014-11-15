package com.brad.guiapp;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.Timer;

import com.brad.guiapp.conway.ConwayGameOfLife;
import com.brad.guiapp.conway.ConwayGameOfLifeImpl;

public class Main {

	public static void main( String[] args )
    {
		int worldDimension = 100;
		
		Set<Point> seedWorld = new HashSet<>();
		seedWorld.add(new Point(10,20));
		
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
        new Timer(1000, new ActionListener() {
        	
            public void actionPerformed(ActionEvent e) {
            	app.setWorld(game.incrementAndGetWorld());
                app.repaint();
            }

        }).start();
	}
}
