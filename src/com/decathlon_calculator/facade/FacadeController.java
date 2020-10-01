package com.decathlon_calculator.facade;

import java.io.File;
import java.util.ArrayList;

import com.decathlon_calculator.logic.PlaceSorter;
import com.decathlon_calculator.model.Athlete;
import com.decathlon_calculator.util.InputRead;
import com.decathlon_calculator.logic.PointsCounter;
import com.decathlon_calculator.util.XMLBuilder;
import com.decathlon_calculator.util.CSVParser;

/**
 * @author Mindaugas Jaunius
 *
 * This class acts as a bridge between application GUI and logic
 */
public class FacadeController {

    private File inputResultFile;
    private ArrayList<Athlete> athletes;

    public FacadeController(String fileName) {
        inputResultFile = new File(fileName);
        initializeAthletesArrayList();
    }

//		Private method, only in this class
    public void initializeAthletesArrayList() {
        InputRead input = new InputRead();
        CSVParser csv = new CSVParser();
//      Check inputResultFile exists?
        athletes = csv.getAthletesList(input.getContents(inputResultFile));
    }

//	Private method
    public ArrayList<Athlete> getAthletes() {
        return athletes;
    }

// 	Keep public    
    public void calculatePoints() {
        new PointsCounter().countTotalScores(getAthletes());
    }

    public void sortByPoints() {
        new PlaceSorter().sortAthletes(getAthletes());
    }

    public void buildXML() {
        new XMLBuilder(getAthletes());
    }

    public void printList() {
        for (Athlete athlete : getAthletes()) {
            System.out.println(athlete);
        }
    }
}
