/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourbusmassacre_ca1;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * @author rorypb
 */
public class DatabaseConnection {

    //==================================
    //Connection to Database
    //private static final String DATABASE = "daneel"; for college upload
    //private static final String USERNAME = "N00143233";
    //private static final String PASSWORD = "N00143233";
    //private static final String CONN_STRING =
    //          "jdbc:mysql://daneel/N00143233";

    private static final String USERNAME = "rorypb";
    private static final String PASSWORD = "root";
    private static final String CONN_STRING =
            "jdbc:mysql://localhost:8889/CA1-TourBusMassacre";
    //Connection to local server

    //==================================

    private static DatabaseConnection instance = null; //Connections to start as null(closed)
    private Connection dbConnection = null;

    private DatabaseConnection() {//Default empty constructor


    }

    public static DatabaseConnection getInstance() {//Instantiating the connection
        if (instance == null) { //if the instance is null it will start a new db connection
            instance = new DatabaseConnection(); //new DB connection from instance

        }

        return instance; // return the instance


    }

    private boolean openConnection() {//Open connection
        boolean connected = true;
        try { //when connection is open
            dbConnection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            System.out.println("CONNECTED");//when connected is true it will print out CONNECTED

        } catch (SQLException e) { //when connection is not open
            connected = false;
            System.err.println("Error no connection: " + e.getMessage());
        }
        return connected;

    }

    public Connection getDbConnection() {//To get the connection in other classes
        Connection connection = null; //Initialise to null
        if (dbConnection == null) { //If the connection is null
            if (openConnection()) {
                connection = dbConnection; //Set the connectinon to Open
            }
        } else {
            connection = dbConnection;//Else keep it as null

        }
        return connection; //Return an open or closed connection
    }

    public void closeConnection() { //close DB connection
        try {
            if (dbConnection != null) { //If the DB connection is not null
                dbConnection.close();//Close the connection
                dbConnection = null;//Good practice to close the connection when finished
            }
        } catch (SQLException e) {

        }
    }


}
