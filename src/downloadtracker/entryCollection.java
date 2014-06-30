/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadtracker;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Roy
 */
public class entryCollection {

    public ArrayList<entry> list = new ArrayList();

    public entryCollection() {
    }

    public void addEntry(entry e) {
        this.list.add(e);
    }

    public void output() {
        for (entry as : list) {
            as.output();
        }

    }

    public ArrayList<entry> returnArray() {
        return list;
    }

    public void resolve(String[][] lookup) {
        for (entry en : list) {
            en.resolveName(lookup);
        }
    }

    public void removeDuplicates() {



        //Alternate method

        //create cache list
        ArrayList<entry> cache = new ArrayList();
        //initialise with 1 variable
        cache.add(list.get(0));

        for (int i = 0; i < list.size(); i++) {
            boolean found = false;

            for (int j = 0; j < cache.size(); j++) {


                String temp1 = list.get(i).getString();
                String temp2 = cache.get(j).getString();


                if (temp1.equals(temp2)) {
                    found = true;
                    break;
                }

                if ((j == cache.size() - 1) && (found == false)) {
                    cache.add(list.get(i));
                }

            }


        }
        list.clear();
        list = cache;



    }

    public void resolveDate() {
        for (entry en : list) {
            en.parseDate();
        }

    }
    
    
    public int returnStatsShow (Date startDate, Date endDate, String Show, int Episode){
        int num = 0;  
        for (entry en : list) {
            if(Show.equals(en.returnShow()) && (Episode == en.returnEp())) {
                num = num + 1;
            }
                
        }
          
        return num;
          
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
        
        
        
    }
}
