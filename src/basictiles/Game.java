/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basictiles;

import java.awt.Canvas;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author Keith
 */
public class Game extends Canvas implements KeyListener {
    
    /**
     * Simple tile-based collision detection
     */
    
    private Image playerSprite;
    private BufferStrategy bufferStrategy;
    
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    
    private Map map;
    private Entity player;
    
    /**
     * Create game and begin game loop
     */
    
    public Game() {
        
        try {
            URL url = Thread.currentThread().getContextClassLoader().getResource("res/sprite.gif");
            if(url == null) {
                System.err.println("Unable to find sprite.");
                System.exit(0);
            }
            playerSprite = ImageIO.read(url);
        } catch (IOException e) {
            System.err.println("Unable to load sprite");
            System.exit(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
