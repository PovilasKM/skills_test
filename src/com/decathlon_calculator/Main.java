package com.decathlon_calculator;

import com.decathlon_calculator.facade.FacadeController;

public class Main {

    public static void main(String[] args) {
        FacadeController c = new FacadeController("Decathlon_input.txt");
        
        c.calculatePoints();
        c.sortByPoints();
        c.printList();
        c.buildXML();
    }
}
