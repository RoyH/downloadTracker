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
public class entryCollection {

    private ArrayList<entry> list = new ArrayList();

    public entryCollection() {
    }
    
    public void addEntry(entry e) {
        this.list.add(e);
    }
    
    public void output (){
        for (entry as:list) {
            as.output();
        }
    
    }
    
    public ArrayList<entry> returnArray() {
        return list;
    }
}
