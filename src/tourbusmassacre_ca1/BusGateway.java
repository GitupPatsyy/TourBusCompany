package tourbusmassacre_ca1;

import java.sql.*;
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

        boolean success = true;

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
            }
            else {
                System.err.println("No rows changed");
                success = false;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            success = false;
        } finally { //close keys
            if(keys != null){
                keys.close();
            }
            if(stmt != null){ //close statement
                stmt.close();

            }
        }
        return success; //return the bus if created or null if issue


    }


    public boolean removeBus(int busID) throws SQLException {

        String query; //SQL Query
        PreparedStatement stmt; //Statement to update the table
        int numRowsAffected = 0;

        query = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = ?"; //Deleting using the SQL query

        stmt = gConnection.prepareStatement(query);
        stmt.setInt(1, busID);

        numRowsAffected = stmt.executeUpdate();

        return (numRowsAffected == 1);


    }

    public boolean updateBus(Buses buses) throws SQLException{

        String query;
        PreparedStatement stmt;

        int numRowsAffected = 0;

    }
}
