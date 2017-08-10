/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;

/**
 *
 * @author Keith
 */
public class FullScreenTest extends JFrame {

    int screenWidth = 0;
    int screenHeight = 0;
    int colorDepth = 0;

    DisplayMode displayMode;

    public FullScreenTest(int width, int height, int depth) {
        this.screenWidth = width;
        this.screenHeight = height;
        this.colorDepth = depth;
        if (screenWidth != 0 && screenHeight != 0 && colorDepth != 0) {
            displayMode = new DisplayMode(
                    width, height, depth, DisplayMode.REFRESH_RATE_UNKNOWN);
        } else {
            displayMode = new DisplayMode(800, 600, 16, DisplayMode.REFRESH_RATE_UNKNOWN);
        }
//        FullScreenTest test = new FullScreenTest();

    }

    private static final long DEMO_TIME = 5000;

    public void run() {
        // method did have DisplayMode as a parameter
        
        setBackground(Color.blue);
        setForeground(Color.white);
        setFont(new Font("Dialog", Font.PLAIN, 24));

        ScreenManager screen = new ScreenManager();
        try {
            screen.setFullScreen(displayMode, this);
            try {
                Thread.sleep(DEMO_TIME);
            } catch (InterruptedException ex) {
                // ignore for now
            }
        } finally {
            screen.restoreScreen();
        }

    }
    
    
    public void paint(Graphics g) {
        g.drawString("Test Screen", 20, 50);
    }

}
