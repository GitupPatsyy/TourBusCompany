/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourbusmassacre_ca1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author rorypb
 */
public class TourBusMassacre_CA1 {

    //Method for println
    public static void s(Object s) {
        System.out.println(s);//This is a simple method for system print outs
    }

    public static void main(String[] args) { //Main Method

        Buses b;

        Model model = Model.getInstance();
        Scanner in = new Scanner(System.in);

        int menuOpt; //Allow changing of menu using numbers

        do { //Allowing user to run the do loop while the menuOpt isnt 5
            s("---------------------------------");
            s("|\t1 - Create Bus\t\t\t\t|");
            s("|\t2 - View the Bus list'\t\t|");
            s("|\t3 - Update Bus list\t\t\t|");
            s("|\t4 - Delete from Bus list\t|");
            s("|\t5 - Exit\t\t\t\t\t|\n");
            s("---------------------------------");

            s("What would you like to do?");
            menuOpt = in.nextInt();

            s("Menu number chosen " + menuOpt + "\n");
            //Allows user to change menu
            switch (menuOpt) {
                case 1: {
                    s("Creating bus...\n");
                    b = readBuses(in);
                    model.addBus(b);//uses the add bus method to add bus to the arraylist
                    BusGateway busGateway = new BusGateway(DatabaseConnection.getInstance().getDbConnection());
                    try {
                        busGateway.insertBus(b);
                    } catch (SQLException e) {

                    }
                    break;
                }
                case 2: {
                    s("Viewing bus...\n");
                    viewBus(model); //views the whole bus list
                    break;
                }
                case 3: {
                    s("Updating bus...\n");
                    s("Please select the ID of the Bus to Update: ");

                }
                case 4: {
                    s("Deleting bus from list...\n");
                    deleteBus(in, model); //uses the delete method to delete a bus using the BusID
                }
                case 5: {
                    s("Exiting....\n");
                    break;
                }
            }
            //Ends the loop when 5 is selected
        } while (menuOpt != 5);
    }

    private static Buses readBuses(Scanner in) {//Creating a bus in the main method
        String regNum, busMake, busModel; //needs a string of reg, make, model datebought and next service
        //int busID;//BusID added for ease of deletion(hopefully)
//        int garageID;
        double engineSize; //Engine size needed
        Date dateBought = null, nextService = null;


        s("Enter registration number (00D12345): ");
        regNum = in.next();

        s("Enter bus make: ");
        busMake = in.next();

        s("Enter bus model: ");
        busModel = in.next();

        s("Enter date bought (yyyy/MM/dd) : ");
        String boughtDate = in.next();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dateBought = df.parse(boughtDate);


            s("Enter date for next service (yyyy-MM-dd) : ");
            String serviceNext = in.next();
            nextService = df.parse(serviceNext);

        } catch (ParseException e) {
            //e.printStackTrace();
            System.err.println("Invalid date format: "+ e.getMessage());
            readBuses(in);
        }

        s("Enter engine size: ");
        engineSize = in.nextDouble();


//        s("Enter the Garage ID the Bus is based: ");
//        garageID = in.nextInt();
//        s("\n");

        Buses b = new Buses(regNum, busMake, busModel, engineSize, dateBought, nextService);//Adds bus to ArrayList
        return b;
    }

    private static void viewBus(Model model) {
//Creates method to view bus
        List<Buses> bus = model.getBuses();//Lists buses in the arrary
        for (Buses bs : bus) {
            //for each loop
            s("Bus ID: " + bs.getBusID() + "\n");//RIGHT NOW prints out my toString method which is all the bus information that the user enters
            s("================================================================");
        }
    }

//    private static void updateBus(Scanner in, Model m){
//        s("Enter the ID of the Bus you would like to Update: ");
//        int busID = Integer.parseInt(in.nextInt());
//
//        Buses b;
//
//        b = m.findBusByID(busID);
//        if(b != null) {
//            if (m.updateBus(b)){
//                Buses b = new Buses(busID, regNum, busMake, busModel, engineSize, dateBought, nextService);
//            }
//        }
//
//    }

    private static void deleteBus(Scanner in, Model m) {
        s("Enter the BusID of the bus you would like to delete: ");
        int busID = Integer.parseInt(in.next()); //Search for Bus to delete

        Buses b;

        b = m.findBusByID(busID);
        if (b != null) {
            if (m.removeBus(b)) {
                s("Bus removed");
                s("================================================================\n");
            } else {
                s("Bus removal failed");
                s("================================================================\n");
            }
        } else {
            s("Bus not found");
            s("================================================================\n");
        }
    }
}
