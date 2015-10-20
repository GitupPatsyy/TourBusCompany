/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourbusmassacre_ca1;

/**
 *
 * @author rorypb
 */
public class Buses {
    private String regNum;
    private String busMake;
    private String busModel;
    private double engSize;
    private String dateBought;
    private String nextService;
    
   //Default Constructor
    public Buses(){
        
    }
    
    public Buses(String regNum, String busMake, String busModel, double engSize, String dateBought, String nextService){
        this.regNum = regNum;
        this.busMake = busMake;
        this.busModel = busModel;
        this.engSize = engSize;
        this.dateBought = dateBought;
        this.nextService = nextService;
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
    public String getDateBought() {
        return dateBought;
    }

    /**
     * @param dateBought the dateBought to set
     */
    public void setDateBought(String dateBought) {
        this.dateBought = dateBought;
    }

    /**
     * @return the nextService
     */
    public String getNextService() {
        return nextService;
    }

    /**
     * @param nextService the nextService to set
     */
    public void setNextService(String nextService) {
        this.nextService = nextService;
    }
    
    public String toString(){
        return "Registration Number: " + regNum+
                "\nBus Make: " + busMake +
                "\nBus Model: " + busModel +
                "\nEngine Size: " + engSize +
                "\nDate Purchased: " + dateBought+
                "\nNext Service: " + nextService;
    }
}

