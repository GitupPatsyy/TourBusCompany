/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourbusmassacre_ca1;

import javax.xml.crypto.Data;
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

        //Initialising a bus object b to null
        Buses b = null;

        //Gets the particular instance of the Model class
        Model model = Model.getInstance();
        //Scanner for keyboard input
        Scanner in = new Scanner(System.in);

        int menuOpt; //Allow changing of menu using numbers

        do { //Allowing user to run the do loop while the menuOpt isnt 5
            //Styled for IntelliJ will look awful in netbeans
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
                    BusGateway busGateway = new BusGateway(DatabaseConnection.getInstance().getDbConnection());//Opens the busgateway and gets the instance of the DB Connection
                    try {//try to add the bus through my insert bus method in the bus gateway class
                        busGateway.insertBus(b);
                    } catch (SQLException e) {

                    }
                    break;
                }
                case 2: {
                    s("Viewing bus...\n");
                    viewBus(model); //views the whole bus list that is currently in the database
                    //BusGateway called withing the model for View
                    break;
                }
                case 3: {
                    s("Updating bus...\n");
                    editBuses(in, model);//Calls the edit buses method within the main
                    //Bus Gateway called within the model for Edit
                    break;


                }
                case 4: {
                    s("Deleting bus from list...\n");

                    s("Please enter the ID of the Bus you would like to delete: ");
                    //uses the delete method to delete a bus using the BusID
                    BusGateway busGateway = new BusGateway(DatabaseConnection.getInstance().getDbConnection());
                    //Established connection and the SQL query for delete is wihtin the busGatway
                    try {
                        if (busGateway.removeBus(in.nextInt())) {//If the bus ID is valid it will remove a bus from the DB
                            s("Bus removed successfully");
                        } else {
                            s("Incorrect Bus ID");//if the bus ID is invalid will not delete
                        }
                    } catch (SQLException e) {

                    }
                    break;
                }
                case 5: {
                    s("Exiting....\n");//On case 5 you exit the menu
                    break;
                }
            }
            //Ends the loop when 5 is selected
        } while (menuOpt != 5);
    }

    private static Buses readBuses(Scanner in) {//Creating a bus in the main method
        String regNum, busMake, busModel; //needs a string of reg, make, model datebought and next service
        //int busID;//BusID added for ease of deletion(hopefully)
        int garageID;
        double engineSize; //Engine size needed
        Date dateBought = null, nextService = null;


        s("Enter registration number (00D12345): ");
        regNum = in.next();

        s("Enter bus make: ");
        busMake = in.next();

        s("Enter bus model: ");
        busModel = in.next();

        s("Enter date bought (yyyy-MM-dd) : ");
        String boughtDate = in.next();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");//Creating a new date format for SQL
        try {
            dateBought = df.parse(boughtDate);//Parsing the string to a date


            s("Enter date for next service (yyyy-MM-dd) : ");
            String serviceNext = in.next();
            nextService = df.parse(serviceNext);//Creating new date format for SQL

        } catch (ParseException e) {
            //e.printStackTrace();
            //IF the date is entered incorrectly it starts the input again but then fails
            System.err.println("Invalid date format: " + e.getMessage());
            readBuses(in);
        }

        s("Enter engine size: ");
        engineSize = in.nextDouble();


        s("Enter the Garage ID the Bus is based: ");
        garageID = in.nextInt();
        s("\n");

        Buses b = new Buses(regNum, busMake, busModel, engineSize, dateBought, nextService, garageID);//Adds bus to ArrayList
        //Return object b
        return b;
    }

    //Get string method
    private static String getString(Scanner in, String prompt) {
        s(prompt);
        //Returns whats entered into the String eg - regNum below
        return in.nextLine();
    }

    //Method to call the update details of the bus
    private static void editBuses(Scanner in, Model m) {
        System.out.print("Enter the Bus ID of the Bus you would like to update: ");
        int busID = in.nextInt();
        Buses b;
        //Finds bus by id
        b = m.findBusByID(busID);
        //if the ID isnt null it will run the update
        if (b != null) {
            updateBusDeets(in, b);//Calling the method to edit the information of the bus, using get and sets to get and change the data
            if (m.updateBuses(b)) {
                System.out.println("Bus updated");
            } else {//If the value is null bus will not be able to update
                System.out.println("Bus not updated");
            }
        } else {//IF invalid ID the system will output the message
            System.out.println("Bus not found");
        }
    }

    private static void updateBusDeets(Scanner in, Buses b) {
        //Strings DONT need to be converted down below
        String regNum, busMake, busModel;
        //Strings to be created to parse/change from their format to the getString method
        int garageID;
        double engSize;
        Date nextService;
        Date purchaseDate;

        //Strings For the date and the double and the int
        String engUpdate, garageUpdate, serviceUpdate;

        regNum = getString(in, "");//Empty string because it wasnt starting to edit on reg number
        regNum = getString(in, "Enter new Registration Number: [" + b.getRegNum() + "]");//Gets regNum of bus selected
        busMake = getString(in, "Enter new Bus Make: [" + b.getBusMake() + "]");
        busModel = getString(in, "Enter new Bus Model: [" + b.getBusModel() + "]");
        engUpdate = getString(in, "Enter new Engine Size: [" + b.getEngSize() + "]");
        serviceUpdate = getString(in, "Enter new Service Date(yyyy-mm-dd): [" + b.getNextService() + "]");//Parsed the date to a String and will be Parsed back down below
        garageUpdate = getString(in, "Enter new Garage ID to store a bus: [" + b.getGarageID() + "]");


        if (regNum.length() != 0) {//IF the reg number is not empty it will assign the value from the new input into the reg number
            b.setRegNum(regNum);

        }
        if (busMake.length() != 0) {//If the bus make is not empty it will set the busmake to its new value
            b.setBusMake(busMake);
        }
        if (busModel.length() != 0) {//If the bus model is not empty it will set the value that was input
            b.setBusModel(busModel);
        }
        if (engUpdate.length() != 0) {
            engSize = Double.parseDouble(engUpdate);//Parsing the double from a string back to a double
            b.setEngSize(engSize);//setting engineSize to the value that was entered
        }
        if (serviceUpdate.length() != 0) {
            //Takes the string that was input and parses it to a date
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String newDate = serviceUpdate;

            try {

                Date date = df.parse(newDate);
                b.setNextService(date);//Parses the value that was entered to serviceUpdate back to a Date value

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


        if (garageUpdate.length() != 0)

        {
            garageID = Integer.parseInt(garageUpdate);//Parsing the int from a string
            b.setGarageID(garageID);//Setting the ID to a Int from a string
        }


    }


    private static void viewBus(Model model) {//BusGateway within the model class
//Creates method to view bus
        for (Buses b1 : model.viewBus()) {//Enhanced for loop
            s(b1.toString());//Uses to string method created in buses.
        }

    }

}
