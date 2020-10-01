package com.decathlon_calculator.logic;

import java.util.ArrayList;
import java.util.Collections;

import com.decathlon_calculator.model.Athlete;

/**
 * @author Mindaugas Jaunius
 */
public class PlaceSorter {


    //Calculates the place which athlete holds based on total points score
    public ArrayList<Athlete> sortAthletes(ArrayList<Athlete> athletes) {

        Collections.sort(athletes);

        for (int i=0; i<athletes.size(); i++) {
            if (i>0) {
//            	Would use equals
                if (athletes.get(i-1).getTotalScore() == athletes.get(i).getTotalScore()) {
                    athletes.get(i-1).setPlace(athletes.get(i-1).getPlace()+"-"+(i+1));
                    athletes.get(i).setPlace(athletes.get(i-1).getPlace());
                } else {
                    athletes.get(i).setPlace((i+1)+"");
                }
            } else {
                athletes.get(i).setPlace(i+1+"");
            }
        }

        return athletes;
    }
}
