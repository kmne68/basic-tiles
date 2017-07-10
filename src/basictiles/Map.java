/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basictiles;

import data.DatabaseConnection;
import java.awt.Color;
import java.awt.Graphics2D;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    
    private DatabaseConnection connection; // = new DatabaseConnection();

    private static final int CLEAR = 0;
    private static final int BLOCKED = 1;
    private static final int OBJECT = 2;
    private static final int LOOTED = 3;
    private static final int MAP_WIDTH = 15;        // the number of tiles across
    private static final int MAP_HEIGHT = 15;       // the number of tiles long
    public static final int TILE_SIZE = 20;         // rendered tile size in pixels

    private int[][] blockedTiles = new int[MAP_WIDTH][MAP_HEIGHT];

    private int[][] objectCoordinates = new int[MAP_WIDTH][MAP_HEIGHT];
    //private GameObject object;
//    Color color = new Color(object.randomRed, object.randomGreen, object.randomBlue);
    private int[][] mapData = new int[MAP_WIDTH][MAP_HEIGHT];

    public Map(DatabaseConnection connection) {
        
        this.connection = connection;
    
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
        
   /*     mapData[6][3] = OBJECT;
        mapData[6][6] = OBJECT;
        mapData[10][4] = OBJECT;
        */
        
        // produce 10 GameObject tiles (orange) with random coordinates
        Random randomGenerator = new Random();
        for(int i = 0; i < 10; i++) {
            int randomX = randomGenerator.nextInt(MAP_WIDTH);
            int randomY = randomGenerator.nextInt(MAP_HEIGHT);
       //     objectCoordinates[randomX][randomY] = OBJECT;
            mapData[randomX][randomY] = OBJECT;
            System.out.println("randomX = " + randomX + ", " + "randomY = " + randomY);
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
        
        for(int x = 0; x < MAP_WIDTH; x++) {
            for(int y = 0; y < MAP_HEIGHT; y++) {
                if(mapData[x][y] == LOOTED) {
                    System.out.println("top of paint, block value = " + mapData[x][y]);
                }
            }            
        }

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

    /*            if (mapData[x][y] == BLOCKED) {
                    int randomRed = randomGenerator.nextInt(255);
                    int randomGreen = randomGenerator.nextInt(255);
                    int randomBlue = randomGenerator.nextInt(255);
                    Color color = new Color(randomRed, randomGreen, randomBlue);
                    g.setColor(color);
                }
*/
                if(mapData[x][y] == BLOCKED) {
                    g.setColor(Color.GREEN);
                }
                
                
             //   if (objectCoordinates[x][y] == OBJECT) {
                if (mapData[x][y] == OBJECT) {    
                //    mapData[x][y] = OBJECT;
                    g.setColor(Color.ORANGE);
        //            System.out.println("objectCoordinates = " + objectCoordinates[x][y]);
                }
        //        System.out.println("mapData " + mapData[1][1]);
        
                if(mapData[x][y] == LOOTED) {
                    
                    g.setColor(Color.MAGENTA);
                }
                
        for(int i = 0; i < MAP_WIDTH; i++) {
            for(int j = 0; j < MAP_HEIGHT; j++) {
                if(mapData[x][y] == LOOTED) {
                    System.out.println("bottom of paint, block value = " + mapData[i][j]);
                }
            }            
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
    
    
    public int hasObject(float x, float y) {
    //    boolean tempBool = false;
    //    tempBool = objectCoordinates[(int) x][(int) y] == OBJECT;
    //    tempBool = (mapData[(int) x][(int) y] == OBJECT);
     //   System.out.println("objectCoordinates from Map.hasObject = " + x + ", " + y + ", " + objectCoordinates[(int) x][(int)y] + ", " + tempBool);
     //   return objectCoordinates[(int) x][(int) y] == OBJECT;
        if (mapData[(int) x][(int) y] == OBJECT)
        return OBJECT;
        else
        return 0;
    }
    
/*    
    public void updateMap() {
        
        System.out.println("From inside updateMap()");
        Boolean loading = true;

        // statusMessageLabel.setText("");
        String sql = "";
        try {
         //   connection = new DatabaseConnection(); //DriverManager.getConnection(dbURL, dbUser, dbPwd);
         //   Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dragon?useSSL=false", "", "");
            Statement s = connection.createStatement();
            sql = "SELECT * FROM gameObject ORDER BY id";
            System.out.println("SQL statement " + sql);
            ResultSet rs = s.executeQuery(sql);

            rs.last();
            // statusMessageLabel.setText("GameObject " + rs.getRow());
            System.out.println("GameObject " + rs.getRow());

            // create a customer object for every customer in the result set
            rs.first(); // returns us to the top of the result set
            do {
                System.out.println("From inside do loop");
                GameObject gameObject = new GameObject(this);
                gameObject.setObjectID(rs.getInt("ID"));
                gameObject.setConsquenceID(rs.getInt("consequenceID"));
                gameObject.setDescription(rs.getString("description"));
                gameObject.setIsActive(rs.getInt("isActive"));
                gameObject.setIsReactive(rs.getInt("isReactive"));
                gameObject.setLocationX(rs.getInt("locationX"));
                gameObject.setLocationY(rs.getInt("locationY"));
                gameObject.setMass(rs.getInt("mass"));
                gameObject.setSize(rs.getInt("size"));
                gameObject.setObjectType(rs.getInt("objectType"));
                gameObject.setObjectName(rs.getString("objectName"));
                System.out.println("ID" + rs.getString("description"));
            } while (rs.next());
            rs.close();
            connection.close();
        } catch (SQLException e) {
          //  statusMessageLabel.setText("SQL Error: " + e + " " + sql);
          System.out.println("Sql error " + e);
        }  */
          /*
          catch (Exception e) {
           // statusMessageLabel.setText("General Err0r");
           System.out.println("General error from Map.java " + e);
        } */
//        loading = false;
//    }
    
    
    
    /***
     * updateMap(int, int) updates the map after an entity interacts with it or
     * an object on it
     */
    public void updateMap(float xLocation, float yLocation) {
        
        mapData[(int) xLocation][(int) yLocation] = LOOTED;
        
        System.out.println("Block color = " + mapData[(int) xLocation][(int) yLocation]);
    }
}
