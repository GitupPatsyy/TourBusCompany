/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourbusmassacre_ca1;

import java.util.Date;

/**
 * @author rorypb
 */
public class Buses {
    private int busID;
    private String regNum;
    private String busMake;
    private String busModel;
    private double engSize;
    private Date dateBought;
    private Date nextService;


    //Default Constructor
    public Buses() {

    }

    //Constructor with all the parameters that make up the Object
    public Buses(String regNum, String busMake, String busModel, double engSize, Date dateBought, Date nextService) {

        //this.busID = busID;
        this.regNum = regNum;
        this.busMake = busMake;
        this.busModel = busModel;
        this.engSize = engSize;
        this.dateBought = dateBought;
        this.nextService = nextService;
    }

    /**
     * @return the busID
     */
    public int getBusID() {
        return busID;
    }

    /**
     * @param busID the busID to set
     */
    public void setBusID(int busID) {
        this.busID = busID; //setting as auto key in BusGateway
    }


    /**
     * @return the regNum
     */
    public String getRegNum() {
        return regNum;
    }

    /**
     * @param regNum the regNum to set
     */
    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }

    /**
     * @return the busMake
     */
    public String getBusMake() {
        return busMake;
    }

    /**
     * @param busMake the busMake to set
     */
    public void setBusMake(String busMake) {
        this.busMake = busMake;
    }

    /**
     * @return the busModel
     */
    public String getBusModel() {
        return busModel;
    }

    /**
     * @param busModel the busModel to set
     */
    public void setBusModel(String busModel) {
        this.busModel = busModel;
    }

    /**
     * @return the engSize
     */
    public double getEngSize() {
        return engSize;
    }

    /**
     * @param engSize the engSize to set
     */
    public void setEngSize(double engSize) {
        this.engSize = engSize;
    }

    /**
     * @return the dateBought
     */
    public Date getDateBought() {
        return dateBought;
    }

    /**
     * @param dateBought the dateBought to set
     */
    public void setDateBought(Date dateBought) {
        this.dateBought = dateBought;
    }

    /**
     * @return the nextService
     */
    public Date getNextService() {
        return nextService;
    }

    /**
     * @param nextService the nextService to set
     */
    public void setNextService(Date nextService) {
        this.nextService = nextService;
    }

    public String toString() {
        return "BusID: " + busID +
                "\nRegistration Number: " + regNum +
                "\nBus Make: " + busMake +
                "\nBus Model: " + busModel +
                "\nEngine Size: " + engSize +
                "\nDate Purchased: " + dateBought +
                "\nNext Service: " + nextService;
    }
}

