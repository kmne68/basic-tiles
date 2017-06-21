/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basictiles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

/**
 *
 * @author Kevin Glass
 *
 * Adapted by: Keith Emery
 *
 * From a tutorial at Coke and Code
 *
 * Map comprises the game area. It is a basic grid but will be rewritten to
 * expand as a player moves beyond its bounds.
 */
public class Map {

    private static final int CLEAR = 0;
    private static final int BLOCKED = 1;
    private static final int OBJECT = 2;
    private static final int MAP_WIDTH = 15;        // the number of tiles across
    private static final int MAP_HEIGHT = 15;       // the number of tiles long
    public static final int TILE_SIZE = 20;         // rendered tile size in pixels

    private int[][] blockedTiles = new int[MAP_WIDTH][MAP_HEIGHT];

    private int[][] objectCoordinates = new int[MAP_WIDTH][MAP_HEIGHT];
    //private Object object;
//    Color color = new Color(object.randomRed, object.randomGreen, object.randomBlue);
    private int[][] mapData = new int[MAP_WIDTH][MAP_HEIGHT];

    public Map() {

        // eventually provide an external source (random generator, file, etc.)
        for (int y = 0; y < MAP_HEIGHT; y++) {
            mapData[0][y] = BLOCKED;
            mapData[2][y] = BLOCKED;
            mapData[7][y] = BLOCKED;
            mapData[11][y] = BLOCKED;
            mapData[MAP_WIDTH - 1][y] = BLOCKED;
        }

        for (int x = 0; x < MAP_WIDTH; x++) {
            if ((x > 0) && (x < MAP_WIDTH - 1)) {
                mapData[x][10] = CLEAR;
            }

            if (x > 2) {
                mapData[x][9] = BLOCKED;
            }
            mapData[x][0] = BLOCKED;
            mapData[x][MAP_HEIGHT - 1] = BLOCKED;
        }

        mapData[4][9] = CLEAR;
        mapData[7][5] = CLEAR;
        mapData[7][4] = CLEAR;
        mapData[11][7] = CLEAR;
        
        // produce 10 Object tiles (orange) with random coordinates
        Random randomGenerator = new Random();
        for(int i = 0; i < 10; i++) {
            int randomX = randomGenerator.nextInt(MAP_WIDTH);
            int randomY = randomGenerator.nextInt(MAP_HEIGHT);
            objectCoordinates[randomX][randomY] = OBJECT;
        //    mapData[randomX][randomY] = OBJECT;
        }
        
    //    mapData[2][3] = OBJECT;             // adds a uniquely colored block to the map at the specified location

        //   mapData[object.locationX][object.locationY] = CLEAR;
    }

    /**
     * Render the map by filling the appropriate (blocked) tiles
     *
     * @param g the graphics context
     */
    public void paint(Graphics2D g) {

        //  Original code          
//        for (int x = 0; x < MAP_WIDTH; x++) {
//            for (int y = 0; y < MAP_HEIGHT; y++) {
//                g.setColor(Color.BLACK);
//                if(mapData[x][y] == BLOCKED) {
//                    g.setColor(Color.red);
//                }
//        
        Random randomGenerator = new Random();
        for (int x = 0; x < MAP_WIDTH; x++) {
            for (int y = 0; y < MAP_HEIGHT; y++) {
                g.setColor(Color.BLACK);

                if (mapData[x][y] == BLOCKED) {
                    int randomRed = randomGenerator.nextInt(255);
                    int randomGreen = randomGenerator.nextInt(255);
                    int randomBlue = randomGenerator.nextInt(255);
                    Color color = new Color(randomRed, randomGreen, randomBlue);
                    g.setColor(color);
                }
                if (objectCoordinates[x][y] == OBJECT) {
                    g.setColor(Color.ORANGE);
        //            System.out.println("objectCoordinates = " + objectCoordinates[x][y]);
                }
        //        System.out.println("mapData " + mapData[1][1]);

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
    
    
    public boolean hasObject(float x, float y) {
        
        System.out.println("hasObject objectCoordinates from Map.hasObject = " + x + ", " + y + ", " + objectCoordinates[(int) x][(int)y]);
        return objectCoordinates[(int) x][(int) y] == OBJECT;
     //   return mapData[(int) x][(int) y] == OBJECT;
    }

}
