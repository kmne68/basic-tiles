/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basictiles;

import java.util.Random;

/**
 *
 * @author Keith
 */
public class Object {

    Map map;
    int objectID;
    int consquenceID;
    int isActive;
    int objectType; // natural resource, treasure, building, weapon, etc
    String objectName;
    int locationX;
    int locationY;
    String description;
    int isReactive;
   
    int randomRed;          // for
    int randomGreen;        // testing
    int randomBlue;         // purposes
    
    
    public Object(Map map) {        
                
        this.map = map;
        Random randomGenerator = new Random();
        this.locationX = randomGenerator.nextInt(15);
        this.locationY = randomGenerator.nextInt(15);
        int randomRed = randomGenerator.nextInt(255);
        int randomGreen = randomGenerator.nextInt(255);
        int randomBlue = randomGenerator.nextInt(255);

        System.out.println("X, Y, R, G, B: " + locationX + ", " + locationY + ", "  + randomRed + ", "  + randomGreen + ", "  + randomBlue);
        
        }

    public int getRandomRed() {
        return randomRed;
    }

    public int getRandomGreen() {
        return randomGreen;
    }

    public int getRandomBlue() {
        return randomBlue;
    }
    
    
    
    public int hasConsequences() {            
            
        return this.consquenceID;
    }
    
}
