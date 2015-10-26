/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourbusmassacre_ca1;

import java.util.Scanner;

/**
 *
 * @author rorypb
 */
public class TourBusMassacre_CA1 {
    
    //Method for println
    public static void s(Object s) {
        System.out.println(s);
    }

    public static void main(String[] args) {
        
        
        Model model = Model.getInstance();
        Scanner in = new Scanner(System.in);
        
        int menuOpt; //Allow changing of menu using numbers
        
        do { //Allowing user to run the do loop while the menuOpt isnt 5
            s("1 - Create Bus");
            s("2 - View the Bus list");
            s("3 - Update Bus list");
            s("4 - Delete from Bus list");
            s("5 - Print Bus at Index0");
            s("6 - Print Bus at Index1");
            s("7 - Exit\n");
            
            s("What would you like to do?");
            menuOpt = in.nextInt();

            s("Menu number chosen " + menuOpt + "\n");
            //Allows user to change menu
            switch (menuOpt) {
                case 1: {
                    s("Creating bus...\n");
                    break;
                }
                case 2: {
                    s("Viewing bus...\n");
                    break;
                }
                case 3: {
                    s("Updating bus...\n");
                }
                case 4: {
                    s("Deleting bus from list...\n");
                }
                case 5: {
                    s(model.getBuses().get(0));//Prints out HardCoded Bus from Model
                }
                case 6: {
                    s(model.getBuses().get(1));//Prints out HardCoded Bus from Model
                }
                case 7: {
                    s("Exiting....");
                    break;
                }
            }
          //Ends the loop when 5 is selected
        } while (menuOpt != 7);
    }

}
