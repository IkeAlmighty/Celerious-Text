package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Dictionary creates a very large HashMap upon creation. Hopefully, this HashMap is not too large:
 * For each four letter combination in the english language ( total), an entry is created.
 * Any word that starts with those letters is placed in that entry. With a load factor of 0.75, 
 * this means that it takes a minute for a Dictionary object to be contructed, so it is not recommended 
 * to use the object in time intensive sections of the program. In general, it is recommended to use it 
 * at the beginning of the program.
 */
public class Dictionary{

    private HashMap<String, ArrayList<String>> lookupTable;
    public Dictionary(String filename){
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader(filename));
            createLookupTable(reader);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    private void createLookupTable(BufferedReader reader) throws IOException{
        lookupTable = new HashMap<String, ArrayList<String>>();
        
        for(String word = reader.readLine(); word != null; word = reader.readLine()){
            
            StringBuilder lookupPrefix = new StringBuilder();
            for(int i = 0; i < word.length() && i < 4; i++){
                lookupPrefix.append(word.charAt(i));
                // System.out.println(lookupTable.get(lookupPrefix.toString()));
                if(lookupTable.get(lookupPrefix.toString()) == null){
                    ArrayList<String> bucket = new ArrayList<String>();
                    bucket.add(word);
                    lookupTable.put(lookupPrefix.toString(), bucket);
                } else {
                    lookupTable.get(lookupPrefix.toString()).add(word);
                }
                


                // System.out.println(lookupPrefix + ": " + lookupTable.get(lookupPrefix.toString()));
            }
        }
    }

    public ArrayList<String> lookup(String str){
        ArrayList<String> matches = lookupTable.get(str);
        if(matches != null){ return matches;}
        return new ArrayList<String>();
    }
}