/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basictiles;

import data.DatabaseConnection;
import java.sql.SQLException;

/**
 *
 * @author Keith
 */
public class BasicTiles {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {       
        
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.databaseConnect();
        
        Map map = new Map(databaseConnection);
        //Object object = new Object(map);
        
        
        new Game(map);
    }
    
}
