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
import java.util.logging.Logger;
import javax.sql.ConnectionEvent;
import javax.sql.ConnectionEventListener;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.DataSource;

/**
 *
 * @author Keith
 */
public class ConnectionPoolManager  {
    
    private PrintWriter logWriter;

    String dbURL = "";  // "jdbc:mysql://localhost:3306/salesdb?useSSL=false";
    String dbUser = "";
    String dbPassword = "";
    boolean loading = false;

    Properties prop = new Properties();
    InputStream input = null;
    
    DataSource dataSource;  // not used yet


    
    
    public void databaseConnect() {

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
                Connection connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
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
            }
            loading = false;
            
        }

    }
