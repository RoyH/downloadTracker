/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadtracker;

import java.util.ArrayList;

/**
 *
 * @author Roy
 */
public class entry {
    private String Show;
    private int Episode;
    private String ipAddress;
    private String Date; 
    private String EpisodeName;
    // Constructor
    
    public entry(String show, String Episode, String ipAddress, String Date) {
        this.Show = show;
        try {
            this.Episode =  Integer.parseInt(Episode);
        } catch (Exception e) {
        }
        
        this.ipAddress = ipAddress;
        this.Date = Date;
        this.EpisodeName = "";

    } 
    
    public void output() {
        System.out.println(ipAddress + " " + Show + " " + Episode + " " + Date + " " + EpisodeName);
        
    }
    
    public String getString() {
        return (ipAddress + "-" + Show + "-" + Episode + "-" + Date + "-" + EpisodeName);
    }
   
    public void checkData() {
        if (Show.equals("")) {
            System.out.println("error");
        }
    }
    
    public void resolveName(String[][] lookup) {
        //need to change back to COMPARE STRING!!!
        for (int i = 0; i < lookup[0].length; i++) {
           if((Show.equals(lookup[0][i])) && (Episode == Integer.parseInt(lookup[1][i]))) {
               EpisodeName = lookup[2][i];
               break;
           } else {
               EpisodeName = "Unknown";
           }
           
        }
    }
    
}
