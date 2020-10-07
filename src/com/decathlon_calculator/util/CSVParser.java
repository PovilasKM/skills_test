package com.decathlon_calculator.util;

import java.util.ArrayList;
import java.util.regex.Pattern;

import com.decathlon_calculator.model.Athlete;
import com.decathlon_calculator.model.Result;

public class CSVParser {

    private final String VALUE_SEPARATOR = ";";
    private final String[] EVENTS = {"100m", "Long Jump", "Shot Put", "High Jump", "400m",
            "110 Hurdles", "Discus Throw", "Pole Vault", "Javelin Throw", "1500m"};

    public ArrayList<Athlete>
    getAthletesList(ArrayList<String> athletesInLines){

        ArrayList<Athlete> athletes = new ArrayList<Athlete>();

        for (String line : athletesInLines) {
            athletes.add(getAthleteFromLine(line));
        }

        return athletes;
    }

    private Athlete getAthleteFromLine(String line){

        String[] x = Pattern.compile(VALUE_SEPARATOR).split(line);

        Athlete athlete = new Athlete();

        try {
            for (int i=0; i<x.length; i++) {
                if (i==0)
                    athlete.setName(x[i]);
                else
                    athlete.addResult(parseResult(x[i], i));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        return athlete;
    }

    private Result parseResult(String line, int n) {
        Result res = new Result();
        res.setName(EVENTS[n-1]);

        try {
            if (n==10) {    // track event
                res.setSeconds(Double.valueOf(line.substring(0, 1)) * 60 // minutes into seconds
                        + Double.valueOf(line.substring(2)));
            } else if (n==1 || n==5 || n==6) {  // track event
                res.setSeconds(Double.valueOf(line));
            } else {  // field event
                res.setMeters(Double.valueOf(line));
            }


        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }


        return res;
    }
}
