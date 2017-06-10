/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basictiles;

import java.awt.Graphics2D;
import java.awt.Image;

/**
 *
 * @author Keith
 * 
 * Add the player entity to the map.
 * Floating point numbers make for smooth movement on the grid
 */
public class Entity {
    
    private float playerX;
    private float playerY;
    private float playerAngle;
    private float playerSize = 0.3f;
    private Image spriteImage;
    private Map map;

    /**
     * 
     * @param image
     * @param map
     * @param x
     * @param y 
     */
    Entity(Image image, Map map, float x, float y) {
        
        this.spriteImage = image;
        this.map = map;
        this.playerX = x;
        this.playerY = y;
    }

    void paint(Graphics2D g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void move(double d, float f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
