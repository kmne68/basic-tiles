/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Logger;
import javax.sql.ConnectionEvent;
import javax.sql.ConnectionEventListener;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.DataSource;

/**
 *
 * @author Keith
 */
public class DatabaseConnection  {
    
    private PrintWriter logWriter;

    String dbURL = "";  // "jdbc:mysql://localhost:3306/salesdb?useSSL=false";
    String dbUser = "";
    String dbPassword = "";
    boolean loading = false;
    
//    Vector connectionPool = new Vector();

    Properties prop = new Properties();
    InputStream input = null;
    
    DataSource dataSource;  // not used yet

    // Connection probably isn't needed for now
/*    public DatabaseConnection() {
        initialize();
    }
    
    
    public DatabaseConnection(String dbName, String url, String user, String password) {
        
        this.dbURL = url;
        this.dbUser = user;
        this.dbPassword = password;
        initialize();        
    }
*/    
    
    public Connection databaseConnect() {
        
        Connection connection = null;

        try {
       //     input = new FileInputStream("C:\\Users\\Keith\\Documents\\NetBeansProjects\\BasicTiles\\src\\config\\properties");
            input = new FileInputStream(".\\src\\config\\properties");

            prop.load(input);
            
         

            dbURL = prop.getProperty("dbURL");
            dbUser = prop.getProperty("dbUser");
            dbPassword = prop.getProperty("dbPassword");
            System.out.println("props: " + dbURL + ", " + dbUser + ", " + dbPassword);
        } catch (IOException e) {
            e.printStackTrace();
        }

            String sql = "";

            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
                Statement statement = connection.createStatement();
                sql = "SELECT * FROM gameobject ORDER BY id";
                //        sql = "SELECT * FROM tile_types";
                ResultSet results = statement.executeQuery(sql);
                results.first();
                System.out.println("DatabaseConnect");
        //        System.out.println(new String(results.getString(4)));
                ResultSetMetaData rsmd = results.getMetaData();
                int columns = rsmd.getColumnCount();
                System.out.println("Columns in gameobject table: " + columns + "\n");
        //        while (results.next()) {
                    for (int i = 1; i <= columns; i++) {
                        if (i >= 1) {
                            //System.out.print(", ");
                        }
                        String columnValue = results.getString(i);
                        System.out.print(rsmd.getColumnName(i) + " " + columnValue + ", ");
                    }
                    System.out.println("");
                    connection.close();
        //        }
            } catch (SQLException e) {
                System.out.println("SQL Error: " + e + " " + sql);
                return null;
            } catch (ClassNotFoundException cnfe) {
                System.err.println("ClassNotFoundException: " + cnfe);
                return null;
            }
            
            return connection;
        //    loading = false;
            
        }
    
    
    

/*
    private void initialize() {
        
        initializeConnectionPool();
    }
*/    

    /*
    private void initializeConnectionPool() {
        
        while(!checkConnectionAvailability()) {
            System.out.println("Connections are available, connecting...");
            connectionPool.addElement(databaseConnect());
        }
        System.out.println("No connections are avaialble. Pool is full.");
    }
*/
    
/*    private synchronized boolean checkConnectionAvailability() {
        
        final int MAX_POOL_SIZE = 5;
        
        if(connectionPool.size() < 5) {
            return false;
        }
        return true;
    }
*/    
/*    
    public synchronized Connection getConnectionFromPool() {
        
        Connection connection = null;
        
        if(connectionPool.size() > 0) {
            connection = (Connection) connectionPool.firstElement();
            connectionPool.removeElementAt(0);
        }
        return connection;            
    }
*/    
 
/*    
    public synchronized void returnConnectionToPool(Connection connection) {
        
        connectionPool.addElement(connection);
    }
*/
}
