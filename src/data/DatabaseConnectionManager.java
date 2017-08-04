/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import basictiles.Entity;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;
import javax.sql.DataSource;

/**
 *
 * @author Keith
 */
public class DatabaseConnectionManager { //implements Connection {

    private java.sql.Connection connection = null;
    private ResultSet rs;
    private DataSource datasource;
    
    Vector connectionPool = new Vector();

    String dbURL = "";  // "jdbc:mysql://localhost:3306/salesdb?useSSL=false";
    String dbUser = "";
    String dbPassword = "";
    boolean loading = false;

    Properties prop = new Properties();
    InputStream input = null;

    DataSource dataSource;  // not used yet

    // Impleent connection pool in anticipation of threads
    public DatabaseConnectionManager() {
        initialize();
    }
    
    
    public DatabaseConnectionManager(String dbName, String url, String user, String password) {
        
        this.dbURL = url;
        this.dbUser = user;
        this.dbPassword = password;
        initialize();
    }     
    
    
    private void initialize() {
        
        initializeConnectionPool();
    }
    
    
    private void initializeConnectionPool() {
        
        while(!checkConnectionAvailability()) {
            System.out.println("Connections are available, connecting...");
            connectionPool.addElement(databaseConnect());
        }
        System.out.println("No connections are avaialble. Pool is full.");
    }

    private synchronized boolean checkConnectionAvailability() {
        
        final int MAX_POOL_SIZE = 5;
        
        if(connectionPool.size() < 5) {
            return false;
        }
        return true;
    }
    
 
    public synchronized Connection getConnectionFromPool() {
        
        Connection connection = null;
        
        if(connectionPool.size() > 0) {
            connection = (Connection) connectionPool.firstElement();
            connectionPool.removeElementAt(0);
        }
        return connection;            
    }
    
   
    public synchronized void returnConnectionToPool(Connection connection) {
        
        connectionPool.addElement(connection);
    }

    
    
    public Connection databaseConnect() {

        Connection connection = null;
        
        try {
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
            System.out.println("Connection: " + connection);
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
            return null;
        } catch (ClassNotFoundException e) {
            System.err.println("ClassNotFoundException: " + e);
            return null;
        }
        
        return connection;
    }
    
    
    public void addEntity() throws SQLException, ClassNotFoundException {

        try {
            input = new FileInputStream(".\\src\\config\\properties");

            prop.load(input);

            dbURL = prop.getProperty("dbURL");
            dbUser = prop.getProperty("dbUser");
            dbPassword = prop.getProperty("dbPassword");
            System.out.println("props: " + dbURL + ", " + dbUser + ", " + dbPassword);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            input = new FileInputStream(".\\src\\config\\properties");

            prop.load(input);

            dbURL = prop.getProperty("dbURL");
            dbUser = prop.getProperty("dbUser");
            dbPassword = prop.getProperty("dbPassword");
            System.out.println("props: " + dbURL + ", " + dbUser + ", " + dbPassword);

            //Class.forName("com.ms.jdbc.odbc.JdbcOdbcDriver");
            //String url = "jdbc:odbc:JDBCdsn";
            connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT id, objectType,  objectName from gameobject");
            while (rs.next()) {
                //Column names: 
                System.out.println(rs.getInt("id") + " " + rs.getString("objectType") + " " + rs.getString("objectName"));
            }
            if (connection != null) {
                connection.close();
            }
            connection = null;
        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
        } catch (SQLException ex) {
            throw ex;
        } catch (IOException ex) {
            System.out.println("Unable to read file.");
        }
    }
}