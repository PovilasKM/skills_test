package com.decathlon_calculator.model;

public class Result {
    private String name;
    private double meters;
    private double seconds;
    private int score;

    public Result(){
    }

    public void setMeters(double meters) {
        this.meters = meters;
    }
    public double getMeters() {
        return meters;
    }
    public void setSeconds(double seconds) {
        this.seconds = seconds;
    }
    public double getSeconds() {
        return seconds;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public int getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    @Override
    public String toString() {

        String resultString ="Result [";
        if (meters != 0)
            resultString += "meters=" + meters;
        else if (seconds != 0)
            resultString += "seconds=" + seconds;
        if (score != 0)
            resultString += ", score=" + score;
        resultString += "]";
        return resultString;
    }
}
