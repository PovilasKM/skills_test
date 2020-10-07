package com.decathlon_calculator.logic;

import java.util.ArrayList;

import com.decathlon_calculator.model.Athlete;
import com.decathlon_calculator.model.Result;

public class PointsCounter {


    //official formulae parameter values taken from http://en.wikipedia.org/wiki/Decathlon
    private FormulaeParameter[] params = {
            new FormulaeParameter(25.4347, 18, 1.81),
            new FormulaeParameter(0.14354, 220, 1.4),
            new FormulaeParameter(51.39, 1.5, 1.05),
            new FormulaeParameter(0.8465, 75, 1.42),
            new FormulaeParameter(1.53775, 82, 1.81),
            new FormulaeParameter(5.74352, 28.5, 1.92),
            new FormulaeParameter(12.91, 4, 1.1),
            new FormulaeParameter(0.2797, 100, 1.35),
            new FormulaeParameter(10.14, 7, 1.08),
            new FormulaeParameter(0.03768, 480, 1.85)
    };


    //official track event formula Points = INT(A(B — P)C) taken from http://en.wikipedia.org/wiki/Decathlon
    private int getTrackEventPointScore(double result, FormulaeParameter param) {
        return (int) (param.getA()*Math.pow((param.getB()-result), param.getC()));
    }

    //official field event formula Points = INT(A(P — B)C) taken from http://en.wikipedia.org/wiki/Decathlon
    private int getFieldEventPointScore(double result, FormulaeParameter param) {
        return (int) (param.getA()*Math.pow((result-param.getB()), param.getC()));
    }

    public ArrayList<Athlete> countTotalScores(ArrayList<Athlete> athletes) {

        for (int i=0; i<athletes.size(); i++) {
            getTotalScore(athletes.get(i));
        }

        return athletes;
    }

    private Athlete getTotalScore(Athlete athlete) {

        int score = 0;
        int tempScore = 0;

        for (int i=0; i<athlete.getResults().size(); i++) {
            Result res = athlete.getResults().get(i);
            FormulaeParameter param = params[i];
            if (res.getSeconds() != 0) {
                tempScore = getTrackEventPointScore(res.getSeconds(), param);
            } else if (i==1 || i==3 || i==7) {
                tempScore = getFieldEventPointScore(res.getMeters() * 100, param);
            } else {
                tempScore = getFieldEventPointScore(res.getMeters(), param);
            }
            athlete.getResults().get(i).setScore(tempScore);
            score += tempScore;
        }

        athlete.setTotalScore(score);

        return athlete;
    }
}
