/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Window;
import javax.swing.JFrame;

/**
 *
 * @author Keith
 * 
 * From Developing Games in Java by David Brackeen
 * 
 */
public class ScreenManager {
    
    private GraphicsDevice device;
    
    public ScreenManager() {
        
        GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        device = environment.getDefaultScreenDevice();
    }
    
    /**
     * Enter full screen mode and change hte display mode
     */
    public void setFullScreen(DisplayMode displayMode, JFrame window) {
        window.setUndecorated(true);
        window.setResizable(false);
        
        device.setFullScreenWindow(window);
        if(displayMode != null && device.isDisplayChangeSupported()) {
            try {
                device.setDisplayMode(displayMode);
            }
            catch (IllegalArgumentException ex) {
                // ignore
            }
        }
    }
    
    
    /**
     * 
     * Return window in full screen mode 
     * 
     */
    public Window getFullScreenWindow() {
        return device.getFullScreenWindow();
    }
    
    
    /**
     * 
     * Restore the screen's display mode
     * 
     */
    public void restoreScreen() {
        Window window = device.getFullScreenWindow();
        if(window != null) {
            window.dispose();
        }
        device.setFullScreenWindow(null);
    }
}
