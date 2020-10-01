package com.decathlon_calculator.model;

import java.util.ArrayList;

/**
 * @author Mindaugas Jaunius
 */
public class Athlete implements Comparable<Athlete>{

    private String name;
    private ArrayList<Result> results;
    private int totalScore;
    private String place;

    public Athlete() {
        results = new ArrayList<Result>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public void addResult(Result res) {
        results.add(res);
    }

    public void setTotalScore(int score) {
        this.totalScore = score;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPlace() {
        return place;
    }

    @Override
    public String toString() {

        StringBuilder athleteString = new StringBuilder();
        athleteString.append("Athlete [name=" + name + ", results=\n");
        if (getPlace() != null)
            athleteString.append("Place: "+getPlace()+"\n");
        for (Result res : results)
            athleteString.append("\t"+res+"\n");
        if (getTotalScore()!=0)
            athleteString.append("Total: "+getTotalScore()+"\n");
        athleteString.append("]");
        return athleteString.toString();
    }

    @Override
    public int compareTo(Athlete anotherAthlete) throws ClassCastException {
        if (!(anotherAthlete instanceof Athlete))
            throw new ClassCastException("Athlete object expected.");
        int anotherAthleteScore = ((Athlete) anotherAthlete).getTotalScore();

        return  anotherAthleteScore - this.getTotalScore();
    }

}
