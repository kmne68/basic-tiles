/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basictiles;

import data.DatabaseConnectionManager;
import java.awt.Graphics2D;
import java.awt.Image;
import java.sql.SQLException;

/**
 *
 * @author Keith
 * 
 * Add the player entity to the map.
 * Floating point numbers make for smooth movement on the grid
 */
public class Entity {
    
    private DatabaseConnectionManager databaseConnection = new DatabaseConnectionManager();
    
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

    
    /**
     * 
     * @param dx
     * @param dy
     * @return 
     */
    public boolean move(float dx, float dy) {

        float newX = playerX + dx;
        float newY = playerY + dy;
        
        // Collision?
        if(validLocation(newX, newY)) {
            // if not update location and calculate new facing angle
            playerX = newX;
            playerY = newY;
            playerAngle = (float)(Math.atan2(dy, dx) - (Math.PI / 2));
            return true;
        }
        // if invalid location
        return false;
    }
    

    
    /**
     * 
     * @param nX
     * @param ny
     * @return 
     */
    public boolean validLocation(float nX, float ny) {
        
        boolean result = false;
        
        if(map.blocked(nX - playerSize, ny - playerSize)) {
            return false;
        }
        if(map.blocked(nX + playerSize, ny - playerSize)) {
            return false;
        }
        if(map.blocked(nX - playerSize , ny + playerSize)) {
            return false;
        }
        if(map.blocked(nX + playerSize, ny + playerSize)) {
            return false;
        }
        
        // If we've made it to here, the way is clear               
        return true;
    }
    
    
        public boolean checkForObject(float nX, float ny) { //throws SQLException, ClassNotFoundException {
        
    //    if(map.hasObject(nX - playerSize, ny - playerSize)) {
        if(map.hasObject(this.playerX - playerSize, this.playerY - playerSize) == 2) {
            
   //         map.updateMap();
     //       databaseConnection.addEntity();
            map.updateMap(this.playerX - playerSize, this.playerY - playerSize);
            return true;
        }
    //    if(map.hasObject(nX + playerSize, ny - playerSize)) {
        if(map.hasObject(this.playerX + playerSize , this.playerY - playerSize) == 2) {
    //        map.updateMap();
    
      //      databaseConnection.addEntity();
            map.updateMap(this.playerX + playerSize, this.playerY - playerSize);
            return true;
        }
   //     if(map.hasObject(nX - playerSize , ny + playerSize)) {
        if(map.hasObject(this.playerX - playerSize, this.playerY + playerSize) == 2) {
    //        map.updateMap();
    
      //      databaseConnection.addEntity();
            map.updateMap(this.playerX - playerSize, this.playerY + playerSize);
            return true;
        }
    //    if(map.hasObject(nX + playerSize, ny + playerSize)) {
        if(map.hasObject(this.playerX + playerSize, this.playerY + playerSize) == 2) {
    //        map.updateMap();
    
        //    databaseConnection.addEntity();
            map.updateMap(this.playerX + playerSize, this.playerY + playerSize);
            return true;
        }
        
        // no object was detected  
        System.out.println("No object was detected from Entity.checkForObject.");
        return false;
    }
    
    
    void paint(Graphics2D g) {

        // calcualte proper position to paint the sprite
        int xPaint = (int) (map.TILE_SIZE * playerX);
        int yPaint = (int) (map.TILE_SIZE * playerY);
        
        // calculate proper facing angle for the sprite
        g.rotate(playerAngle, xPaint, yPaint);
        g.drawImage(spriteImage, (int) (xPaint -16), (int) (yPaint - 16), null);
        g.rotate(-playerAngle, xPaint, yPaint);
    }
    
}
