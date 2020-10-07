package com.decathlon_calculator.tests;

import com.decathlon_calculator.logic.PlaceSorter;
import com.decathlon_calculator.model.Athlete;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PlaceSorterTest {

    PlaceSorter placeSorter;
    ArrayList<Athlete> athletesBeforeSort;
    ArrayList<Athlete> athletesAfterSort;

    @Before
    public void setUp() throws Exception {
        placeSorter = new PlaceSorter();
        athletesBeforeSort = new ArrayList<Athlete>();
        athletesAfterSort = new ArrayList<Athlete>();

        Athlete athlete1 = new Athlete();
        athlete1.setName("Test Athlete 1");
        athlete1.setTotalScore(-1);
        athletesBeforeSort.add(athlete1);

        Athlete athlete2 = new Athlete();
        athlete2.setName("Test Athlete 2");
        athlete2.setTotalScore(0);
        athletesBeforeSort.add(athlete2);

        Athlete athlete3 = new Athlete();
        athlete3.setName("Test Athlete 3");
        athlete3.setTotalScore(1);
        athletesBeforeSort.add(athlete3);

        Athlete athlete4 = new Athlete();
        athlete4.setName("Test Athlete 4");
        athlete4.setTotalScore(1);
        athletesBeforeSort.add(athlete4);

        Athlete athlete5 = new Athlete();
        athlete5.setName("Test Athlete 5");
        athlete5.setTotalScore(2);
        athletesBeforeSort.add(athlete5);

        athletesAfterSort = placeSorter.sortAthletes(athletesBeforeSort);
    }

    @After
    public void tearDown() throws Exception {
        placeSorter = null;
        athletesBeforeSort = null;
        athletesAfterSort = null;
    }

    //first place
    @Test
    public void testSortAthletes1() throws Exception {
    	
        assertEquals("Test Athlete 5", athletesAfterSort.get(0).getName());
    }

    //last place
    @Test
    public void testSortAthletes2() throws Exception {
        assertEquals("Test Athlete 1", athletesAfterSort.get(4).getName());
    }

    //shared place
    @Test
    public void testSortAthletes3() throws Exception {
        assertEquals("2-3", athletesAfterSort.get(1).getPlace());
        assertEquals("2-3", athletesAfterSort.get(2).getPlace());
    }

    //normal place
    @Test
    public void testSortAthletes4() throws Exception {
        assertEquals("4", athletesAfterSort.get(3).getPlace());
        assertEquals("Test Athlete 2", athletesAfterSort.get(3).getName());
    }
}
