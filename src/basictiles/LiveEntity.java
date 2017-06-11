/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basictiles;

import java.awt.Image;

/**
 *
 * @author Keith
 */
public class LiveEntity extends Entity {
    
    Image image;
    Map map;
    float x, y;
    
    public LiveEntity(Image image, Map map, float x, float y) {
        
        super(image, map, x, y);
        this.image = image;
        this.map = map;
        this.x = x;
        this.y = y;
    }
}
