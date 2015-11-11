/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourbusmassacre_ca1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rorypb
 */
public class Model {

    private static Model instance = null; //Set instance value

    public static synchronized Model getInstance() //Creates instance for the Bus Object
    {
        if (instance == null) {
            instance = new Model();//Creates a new instance because "instance = null"
        }
        return instance;
    }

    private List<Buses> buses; //Creates private array buses from Buses class

    private Model() {
        //Array List for Buses
        this.buses = new ArrayList<Buses>();
        //Adding a Bus for Hardcode Test
        //this.buses.add(new Buses(1, "07TN9999", "Mercedes", "Vito", 2.5, "14/01/2015", "22/12/2015"));

        //this.buses.add(new Buses(2, "141D1234", "Ford", "Bus", 2.2, "01/05/2014", "01/04/2016"));
    }

    public List<Buses> getBuses() //Gets buses from Buses Class
    {
        return new ArrayList<Buses>(this.buses);
    }

    //Method to Add Bus to List

    public void addBus(Buses b) {
        this.buses.add(b);
    }

    public boolean removeBus(Buses b) {
        return this.buses.remove(b);
    }

//    public Buses updateBus(Buses b){
//        return this.buses.set(b);
//    }

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
