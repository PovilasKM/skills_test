package com.decathlon_calculator.logic;

/**
 * @author Mindaugas Jaunius
 *
 * This class represents parameter object whichs values is used in formulae to calculate points.
 */
public class FormulaeParameter {

    private double a;
    private double b;
    private double c;

    public FormulaeParameter(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }
}
