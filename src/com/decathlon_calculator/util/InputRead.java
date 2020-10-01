package com.decathlon_calculator.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Mindaugas Jaunius
 */
public class InputRead {

    public ArrayList<String> getContents(File aFile) {

        ArrayList<String> contents = new ArrayList<String>();

        try {
            BufferedReader input =  new BufferedReader(new FileReader(aFile));
            try {
                String line = null;

                while (( line = input.readLine()) != null){
                    contents.add(line);
                }
            }
            finally {
                input.close();
            }
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

        return contents;
    }
}
