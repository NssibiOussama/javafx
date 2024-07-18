/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Mahdi
 */
public class database {

    private final String URL = "jdbc:mysql://127.0.0.1:3306/emploidutemps_db";
    private final String userName = "root";
    private final String passWord = "";
    private Connection connection;
    private static database instance;

    private database() {
        try {
            connection = DriverManager.getConnection(URL, userName, passWord);
        } catch (SQLException ex) {
            System.out.println("Error : " + ex.getMessage());
        }
    }

    public static database getInstance() {
        if (instance == null) {
            instance = new database();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

}
