package gameCode;

import java.awt.Color;

import javax.swing.JFrame;

import lib.GamePanel;
import lib.Scene;

public class Driver {

	static final double framePause = 1.0/60.0;
	
	public static void main(String[] args) throws InterruptedException{
		JFrame frame = new JFrame("RNGD3D");
        GamePanel gameView = new GamePanel();
        frame.add(gameView);
        gameView.initialize();
        frame.addKeyListener(gameView);
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Can set properties here
        Scene theScene = new MyCool3dScene((double)gameView.getWidth(), 
        		(double)gameView.getHeight());
        theScene.backgroundColor = Color.WHITE;
        gameView.setScene(theScene);
        
        while(true) {
        	gameView.repaint();
        	gameView.update(framePause);
            Thread.sleep((long)(framePause*1000));
        }

	}

}
