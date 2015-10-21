/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourbusmassacre_ca1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rorypb
 */
public class Model {

    private static Model instance = null;

    public static synchronized Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }
    
    private List<Buses> buses;
    private Model(){
        //Array List for Buses
        this.buses = new ArrayList<Buses>();
        //Adding a Bus for Hardcode Test
        this.buses.add(new Buses("07TN9999", "Mercedes", "Vito", 2.5, "14/01/2015", "22/12/2015"));
    }
        
        public List<Buses> getBuses()
        {
            return new ArrayList<Buses>(this.buses);
        }
        //Method to Add Bus to List
        public void addBus(Buses b){
            this.buses.add(b);
        }
        public Buses findBusByReg(String regNum){
            Buses b = null;
            int i =0;
            boolean found = false;
            while (i < this.buses.size() && !found) {                
                b = this.buses.get(i);
                if (b.getRegNum() == regNum) {
                    found = true;
                } else {
                    i++;
                }
            }
            if(!found){
                b = null;
            }
            return b;
        }
    }


