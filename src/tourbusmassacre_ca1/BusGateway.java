package tourbusmassacre_ca1;

import java.sql.*;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rorypb on 09/11/2015.
 */
public class BusGateway {

    //Should match the column names in your DB
    private static final String TABLE_NAME = "Bus";
    private static final String COLUMN_ID = "busID";
    private static final String COLUMN_REG = "regNum";
    private static final String COLUMN_MAKE = "busMake";
    private static final String COLUMN_MODEL = "busModel";
    private static final String COLUMN_SIZE = "engineSize";
    private static final String COLUMN_BOUGHT = "dateBought";
    private static final String COLUMN_SERVICE = "dateNextService";
    private static final String COLUMN_GARAGE = "garageID";

    private Connection gConnection;

    public BusGateway(Connection connection) {
        gConnection = connection;
    }

    //Inserting the bus into your DB table
    //empty bus obect for when entering in values
    public boolean insertBus(Buses bus) throws SQLException {

        boolean success = true; //set so success is defaulted to true

        String query; //Query for SQL commands to communicate with DB
        PreparedStatement stmt = null; //Statement for executing the SQL query

        ResultSet keys = null; //Auto Key

        int numRowsAffected = 0;
        int id;


        //Required fields to use the INSERT COMMAND SQL
        query = "INSERT INTO " + TABLE_NAME + " (" +
                COLUMN_REG + ", " +
                COLUMN_MAKE + ", " +
                COLUMN_MODEL + ", " +
                COLUMN_SIZE + ", " +
                COLUMN_BOUGHT + ", " +
                COLUMN_SERVICE + ", " +
                COLUMN_GARAGE +
                ") VALUES (?,?,?,?,?,?,?)";


        try {
            stmt = gConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, bus.getRegNum());
            stmt.setString(2, bus.getBusMake());
            stmt.setString(3, bus.getBusModel());
            stmt.setDouble(4, bus.getEngSize());
            stmt.setDate(5, new Date(bus.getDateBought().getTime())); //Java date format needs to Changed to the same format as SQL
            stmt.setDate(6, new Date(bus.getNextService().getTime()));
            stmt.setInt(7, bus.getGarageID());

            numRowsAffected = stmt.executeUpdate();//Update the table

            if (numRowsAffected == 1) {
                // if one row was inserted, retrieve the id assigned to that row and create a Bus to return
                keys = stmt.getGeneratedKeys();//Auto Key
                keys.next();

                id = keys.getInt(1);

                bus.setBusID(id);//Setting id
            } else {
                System.err.println("No rows changed");
                success = false; //Change success if no rows are changed
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            success = false;
        } finally { //close keys
            if (keys != null) {
                keys.close();
            }
            if (stmt != null) { //close statement
                stmt.close();
            //Goo practivce to close kets
            }
        }
        return success; //return the bus if created or null if issue


    }


    public boolean removeBus(int busID) throws SQLException {

        String query; //SQL Query
        PreparedStatement stmt; //Statement to update the table
        int numRowsAffected = 0;

        query = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = ?"; //Deleting using the SQL query

        stmt = gConnection.prepareStatement(query);//Puts the SQL Query into the statement
        stmt.setInt(1, busID);

        numRowsAffected = stmt.executeUpdate();//Actually executes the SQL statement

        return (numRowsAffected == 1); //Return that rows are changed


    }

    public boolean updateBus(Buses buses) throws SQLException {

        String query;//SQL statemtn
        PreparedStatement stmt;//Stmt to update the table

        int numRowsAffected = 0; //Variable to change when rows are affected

        // the required SQL INSERT statement with place holders for the values to be inserted into the database
        query = "UPDATE " + TABLE_NAME + " SET " +
                COLUMN_REG + " = ?, " +
                COLUMN_MAKE + " = ?, " +
                COLUMN_MODEL + " = ?, " +
                COLUMN_SIZE + " = ?, " +
                COLUMN_BOUGHT + " = ?, " +
                COLUMN_SERVICE + " = ?, " +
                COLUMN_GARAGE + " = ? " +
                " WHERE " + COLUMN_ID + " = ?" ;

        stmt = gConnection.prepareStatement(query);
        stmt.setString(1, buses.getRegNum());
        stmt.setString(2, buses.getBusMake());
        stmt.setString(3, buses.getBusModel());
        stmt.setDouble(4, buses.getEngSize());
        stmt.setDate(5, new Date(buses.getDateBought().getTime()));
        stmt.setDate(6, new Date((buses.getNextService().getTime())));
        stmt.setInt(7, buses.getGarageID());
        stmt.setInt(8, buses.getBusID());//BusID needs to be there to allow the update to happen. I had this left out initially

        numRowsAffected = stmt.executeUpdate();//Execute the update statement

        return (numRowsAffected == 1); //Return that the rows have been affected


    }

    public ArrayList<Buses> viewBus() throws SQLException {

        String query; //SQL query goes in the string
        Statement stmt; //Used for executing SQL

        ResultSet resultSet; //Represeents the result of SQL query
        ArrayList<Buses> buses; //Arraylist containing the bus objects

        //Parameters to create a new bus
        String regNum;
        String busMake;
        String busModel;
        double engineSize;
        int busID;
        int garageID;
        Date dateBought = null;
        Date nextService = null;

        //Object created in result of query
        Buses buses1;

        query = "SELECT * FROM " + TABLE_NAME; //SQL select statement
        stmt = this.gConnection.createStatement();
        resultSet = stmt.executeQuery(query);

        //New empty array list for the extracted date to be input into to
        buses = new ArrayList<Buses>();
        while (resultSet.next()) {
            busID = resultSet.getInt(COLUMN_ID);
            regNum = resultSet.getString(COLUMN_REG);
            busMake = resultSet.getString(COLUMN_MAKE);
            busModel = resultSet.getString(COLUMN_MODEL);
            engineSize = resultSet.getDouble(COLUMN_SIZE);
            dateBought = resultSet.getDate(COLUMN_BOUGHT);
            nextService = resultSet.getDate(COLUMN_SERVICE);
            garageID = resultSet.getInt(COLUMN_GARAGE);
//            if (resultSet.wasNull()){
//                garageID = -1;
//            }

            buses1 = new Buses(busID, regNum, busMake, busModel, engineSize, dateBought, nextService, garageID);
            buses.add(buses1);//Adds the buses to the empty arrrylist


        }
        return buses; //returns buses


    }

}
