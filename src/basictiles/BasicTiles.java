/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basictiles;

import data.DatabaseConnectionManager;
import java.sql.SQLException;
import threadmanagement.ThreadPool;
import threadmanagement.ThreadPoolTest;

/**
 *
 * @author Keith
 */
public class BasicTiles {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {       
        
        DatabaseConnectionManager databaseConnection = new DatabaseConnectionManager();
        databaseConnection.databaseConnect();
        
        Map map = new Map(databaseConnection);
        //Object object = new Object(map);
        
        ThreadPoolTest tpt = new ThreadPoolTest(8, 4);
        
        new Game(map);
        
    }
}
