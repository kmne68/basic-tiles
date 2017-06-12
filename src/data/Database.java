/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Keith
 */
public class Database {
    
    String dbURL = "jdbc:mysql://localhost:3306/salesdb?useSSL=false";
    String dbUser = "";
    String dbPassword = "";
    boolean loading = false;
    
    public void databaseConnect() {
        
        String sql = "";
        
        try {
            Connection connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
            Statement statement = connection.createStatement();
            sql = "SELECT * FROM customer ORDER BY name";
    //        sql = "SELECT * FROM tile_types";
            ResultSet results = statement.executeQuery(sql);
            results.first();
            System.out.println("DatabaseConnect");
            System.out.println(new String(results.getString(1)) );
            ResultSetMetaData rsmd = results.getMetaData();
            int columns = rsmd.getColumnCount();
            while(results.next()) {
                for(int i = 1; i <= columns; i++) {
                    if(i > 1) System.out.print(", ");
                    String columnValue = results.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                }
                System.out.println("");
            }
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e + " " + sql);
        }
        loading = false;
    }
    
}
