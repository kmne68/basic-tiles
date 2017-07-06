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
public class GameObject {

    private Map map;
    private int objectID;
    private int consquenceID;
    private int isActive;
    private int objectType; // natural resource, treasure, building, weapon, etc
    private String objectName;
    private int locationX;
    private int locationY;
    private String description;
    private int isReactive;
    private int mass;
    private int size;
   
    int randomRed;          // for
    int randomGreen;        // testing
    int randomBlue;         // purposes
    
    
    public GameObject(Map map) {        
                
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
    
    
    public int getID() {
        
        return this.objectID;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public int getObjectID() {
        return objectID;
    }

    public void setObjectID(int objectID) {
        this.objectID = objectID;
    }

    public int getConsquenceID() {
        return consquenceID;
    }

    public void setConsquenceID(int consquenceID) {
        this.consquenceID = consquenceID;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public int getObjectType() {
        return objectType;
    }

    public void setObjectType(int objectType) {
        this.objectType = objectType;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public int getLocationX() {
        return locationX;
    }

    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public void setLocationY(int locationY) {
        this.locationY = locationY;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIsReactive() {
        return isReactive;
    }

    public void setIsReactive(int isReactive) {
        this.isReactive = isReactive;
    }

    public int getMass() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
}
