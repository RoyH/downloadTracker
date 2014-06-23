/*
 * DownloadLogfile Parser, Roy H.
 * Finished Parsing, Initialised the objects. Next thing to is to do is populate them. and store them
 */
package downloadtracker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Roy H
 */
public class DownloadTracker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        //Code section to read in from stored data

        //initialize components
        entryCollection collection = new entryCollection();
        collection = readFile(collection);
        collection.output();
        storeData(collection);
        //Code section to output data
        loadData(collection);

    }

    private static entryCollection readFile(entryCollection collection) throws IOException {

        File dir = new File(".");
        File fin = new File(dir.getCanonicalPath() + File.separator + "radiolitopia.com-May-2014");

        FileInputStream fis = new FileInputStream(fin);

        //Construct BufferedReader from InputStreamReader
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        String line = null;
        while ((line = br.readLine()) != null) {
            //valid file check
            boolean realDownload = true;


            // Initiallise local vars
            String exFN = "";
            String ipAddress = "";
            String timeStamp = "";
            String prefix = "";
            String epNum = "";
            //GET IP Address
            int subLine = line.indexOf(" - - ");
            ipAddress = line.substring(0, subLine);

            //GET timestamp
            int time = line.indexOf("[");
            timeStamp = line.substring(time + 1, time + 12);

            //GET filename

            if (line.contains("GET") && !(line.contains("robots"))) {
                int filenamestart = line.indexOf("GET");
                int filenameend = line.indexOf("HTTP");
                String filename = line.substring(filenamestart + 3, filenameend);
                //Now split into parts and parse files

                if (line.contains("mp3")) {
                    exFN = filename.substring(filename.lastIndexOf("/") + 1, filename.lastIndexOf("mp3") - 1);

                } else {
                    realDownload = false;
                }

                // finally split into prefix + episode number
                try {
                    if (line.contains("_")) {
                        prefix = exFN.substring(0, exFN.indexOf("_"));
                        epNum = exFN.substring(exFN.indexOf("_") + 1);

                        //System.out.println(prefix + " " + epNum);
                    } else {
                        realDownload = false;
                    }
                } catch (Exception e) {
                }


            } else {
                realDownload = false;
            }

            //Do some checks on the data to clean it up
            if ((prefix.equals("")) || (epNum.equals(""))) {
                realDownload = false;
            }

            //Save data to file now,

            //Print to console code, for debugging purposes
            if (realDownload == true) {
                //System.out.println(ipAddress + " " + prefix + " " + epNum + " " + timeStamp);
                collection.addEntry(new entry(prefix, epNum, ipAddress, timeStamp));
            }






        }

        br.close();
        return collection;
    }

    private static void storeData(entryCollection collection) throws FileNotFoundException {
        //Initialises printer
        PrintWriter out = new PrintWriter("CollectionData.txt");

        ArrayList<entry> output = collection.returnArray();
        //outputs data in format ip*name*epnum*date to make easier to parse back
        for (entry en : output) {
           
            out.println(en.getString());
        }
        out.close();

    }

    private static void loadData(entryCollection collection) throws IOException {
        File dir = new File(".");
        File fin = new File(dir.getCanonicalPath() + File.separator + "CollectionData.txt");

        FileInputStream fis = new FileInputStream(fin);

        //Construct BufferedReader from InputStreamReader
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        String line = null;
        while ((line = br.readLine()) != null) {
            //start parsing the data. 
            String[] cache = new String[4];
            try {
                 cache = line.split("-");
            } catch (Exception e) {
            }
            
                    
            String ipAddress = cache[0];
            String Show = cache[1];
            String epNum = cache[2];
            String timeStamp = cache[3];
            
           
            System.out.println(ipAddress + " " + Show + " " + epNum + " " + timeStamp);
        }

        br.close();
        
    }
}
