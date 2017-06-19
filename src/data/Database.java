/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *
 * @author Keith
 */
public class Database {

    String dbURL = "";  // "jdbc:mysql://localhost:3306/salesdb?useSSL=false";
    String dbUser = "";
    String dbPassword = "";
    boolean loading = false;

    Properties prop = new Properties();
    InputStream input = null;

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
                sql = "SELECT * FROM game_objects ORDER BY id";
                //        sql = "SELECT * FROM tile_types";
                ResultSet results = statement.executeQuery(sql);
                results.first();
                System.out.println("DatabaseConnect");
                System.out.println(new String(results.getString(4)));
                ResultSetMetaData rsmd = results.getMetaData();
                int columns = rsmd.getColumnCount();
                while (results.next()) {
                    for (int i = 1; i <= columns; i++) {
                        if (i > 1) {
                            System.out.print(", ");
                        }
                        String columnValue = results.getString(i);
                        System.out.print(columnValue + " " + rsmd.getColumnName(i));
                    }
                    System.out.println("");
                }
            } catch (SQLException e) {
                System.out.println("SQL Error: " + e + " " + sql);
            }
            loading = false;
        }

    }
