/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basictiles;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Kevin Glass
 * 
 * Adapted by: Keith Emery
 * 
 * From a tutorial at Coke and Code
 * 
 * Map comprises the game area. It is a basic grid but will be rewritten
 * to expand as a player moves beyond its bounds.
 */

public class Map {
    
    private static final int CLEAR = 0;
    private static final int BLOCKED = 1;
    private static final int TILE_WIDTH = 15;
    private static final int TILE_HEIGHT = 15;   
    public static final int TILE_SIZE = 20; // rendered tile size in pixels
    
    private int[][] mapData = new int[TILE_WIDTH][TILE_HEIGHT];
    
    public Map() {
        
        // eventually provide an external source (random generator, file, etc.)
        for(int y = 0; y < TILE_HEIGHT; y++) {
            mapData[0][y] = BLOCKED;    
            mapData[2][y] = BLOCKED;     
            mapData[7][y] = BLOCKED;
            mapData[11][y] = BLOCKED;
            mapData[TILE_WIDTH - 1][y] = BLOCKED;
        }
        
        for(int x = 0; x < TILE_WIDTH; x++) {
            if((x > 0) && (x < TILE_WIDTH - 1)) {
                mapData[x][10] = CLEAR;
            }
            
            if(x > 2) {
                mapData[x][9] = BLOCKED;
            }
            mapData[x][0] = BLOCKED;
            mapData[x][TILE_HEIGHT - 1] = BLOCKED;
        }
        
        mapData[4][9] = CLEAR;
        mapData[7][5] = CLEAR;
        mapData[7][4] = CLEAR;
        mapData[11][7] = CLEAR;
    }
    
    /**
     * Render the map by filling the appropriate (blocked) tiles
     * 
     * @param g the graphics context
     */
    
    
    public void paint(Graphics2D g) {
        
        for (int x = 0; x < TILE_WIDTH; x++) {
            for (int y = 0; y < TILE_HEIGHT; y++) {
                g.setColor(Color.BLACK);
                if(mapData[x][y] == BLOCKED) {
                    g.setColor(Color.red);
                }
                
                // add an outline to the map
                g.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                g.setColor(g.getColor().darker());
                g.drawRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
    }
    
    /**
     * check whether a location is 
     * 
     * @param x The x position we're checking
     * @param y The y position we're checking
     * @return True if the location is blocked
     */
    
    public boolean blocked(float x, float y) {
        // check the cell by rounding the floating values, then determine
        // whether it is blocked and return the result
        return mapData[(int) x][(int) y] == BLOCKED;
    }
    
}
