/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadtracker;

/**
 *
 * @author Roy
 */
public class entry {
    private String Show;
    private String Episode;
    private String ipAddress;
    private String Date; 
    
    // Constructor
    
    public entry(String show, String Episode, String ipAddress, String Date) {
        this.Show = show;
        this.Episode = Episode;
        this.ipAddress = ipAddress;
        this.Date = Date;

    } 
    
    public void output() {
        System.out.println(ipAddress + " " + Show + " " + Episode + " " + Date);
        
    }
    
    public String getString() {
        return (ipAddress + "-" + Show + "-" + Episode + "-" + Date);
    }
   
    public void checkData() {
        if (Show.equals("")) {
            System.out.println("error");
        }
    }
    
}
