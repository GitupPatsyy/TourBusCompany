/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourbusmassacre_ca1;

import com.intellij.codeHighlighting.Pass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author rorypb
 */
public class DatabaseConnection {

    //==================================
    //Connection to Database
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String CONN_STRING =
            "jdbc:mysql://localhost/CA1-TourBusMassacre";


    //==================================

    private static DatabaseConnection instance = null;
    private Connection dbConnection = null;

    private DatabaseConnection () {//Default empty constructor


    }

    public static DatabaseConnection getInstance() {//Instantiating the connection
        if(instance == null){ //Instance set to null
            instance = new DatabaseConnection(); //new DB connection from instance

        }

        return instance; // return the instance


    }

    private boolean openConnection() {//Open connection
        boolean connected = true;
        try{
            dbConnection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);

        } catch (SQLException e) {
            connected = false;
            System.err.println("Error no connection: " +  e.getMessage());
        }
        return connected;

    }

    public Connection getDbConnection () {
        Connection connection = null;
        if(dbConnection == null){
            if (openConnection()){
                connection = dbConnection;
            }
        } else {
            connection = dbConnection;

        }
        return connection;
    }

    public void closeConnection() {
        try{
            if (dbConnection != null){
                dbConnection.close();
                dbConnection =  null;
            }
        } catch (SQLException e){

        }
    }





}
