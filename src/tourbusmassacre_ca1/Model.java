/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourbusmassacre_ca1;

import com.mysql.jdbc.Connection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author rorypb
 */
public class Model {

    private ArrayList<Buses> buses;
    private BusGateway busGateway;

    private static Model instance = null; //Set instance value

    public static synchronized Model getInstance() //Creates instance for the Bus Object
    {
        if (instance == null) {
            instance = new Model();//Creates a new instance because "instance = null"
        }
        return instance;
    }

    //Creates private array buses from Buses class

    private Model() {
        //For viewing in the database

        busGateway = new BusGateway(DatabaseConnection.getInstance().getDbConnection());//Establishes connection to DB and busGateway
        try {
            this.buses = busGateway.viewBus();//Views arraylist from BusGatway
        } catch (SQLException e) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, e);
        }


    }

    //Method to view bus
    public ArrayList<Buses> viewBus() { //Gets buses from Buses Class


        try {
            this.buses = busGateway.viewBus();//Instead of calling the busGateway in my main method
        } catch (SQLException e) {

        }
        return this.buses;//returns the Arraylist of buses
    }

    //Method to edit bus
    public boolean updateBuses(Buses b) {
        boolean updated = false;

        try {
            updated = this.busGateway.updateBus(b);//For establishing DB connection
        } catch (SQLException e) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, e);
        }
        return updated;//return the boolean
    }


    //Method to Add Bus to List

    public void addBus(Buses b) {//Actual add method is in the Main method
        this.buses.add(b);
    }


    //Method to view bus by ID
    public Buses findBusByID(int BusID) { //Method to find a Bus by BusID
        Buses b = new Buses(); //creating a new bus
        int i = 0;
        boolean found = false; //setting the bounds to false so that when true it will return found!
        while (i < this.buses.size() && !found) //loop to run until you find a bus
        {
            b = this.buses.get(i);
            if (b.getBusID() == BusID) //if a busID from the array matches the new in BusID the bus wull be found on the next line
            {
                found = true;
            } else {
                i++;
            }
        }
        if (!found) { //if no busID is found it will return nothing and will not delete
            b = null;
        }
        return b;
    }
}
