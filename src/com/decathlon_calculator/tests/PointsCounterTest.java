package com.decathlon_calculator.tests;

import com.decathlon_calculator.logic.PointsCounter;
import com.decathlon_calculator.model.Athlete;
import com.decathlon_calculator.model.Result;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * @author Mindaugas Jaunius
 */
public class PointsCounterTest {

    private ArrayList<Athlete> athletesBeforeCount;
    private ArrayList<Athlete> athletesAfterCount;
    private PointsCounter pointsCounter;
    private PointsCounter pointsCounter1;


    //real data of Ashton Eaton taken from http://en.wikipedia.org/wiki/Athletics_at_the_2012_Summer_Olympics_%E2%80%93_Men%27s_decathlon
    @Before
    public void setUp() throws Exception {
        athletesBeforeCount = new ArrayList<Athlete>();
        athletesAfterCount = new ArrayList<Athlete>();
        pointsCounter = new PointsCounter();

        Athlete athlete1 = new Athlete();
        athlete1.setName("Test Athlete 1");
        Result res1 = new Result();
        res1.setSeconds(10.35);
        athlete1.addResult(res1);
        Result res2 = new Result();
        res2.setMeters(8.03);
        athlete1.addResult(res2);
        Result res3 = new Result();
        res3.setMeters(14.66);
        athlete1.addResult(res3);
        Result res4 = new Result();
        res4.setMeters(2.05);
        athlete1.addResult(res4);
        Result res5 = new Result();
        res5.setSeconds(46.90);
        athlete1.addResult(res5);
        Result res6 = new Result();
        res6.setSeconds(13.56);
        athlete1.addResult(res6);
        Result res7 = new Result();
        res7.setMeters(42.53);
        athlete1.addResult(res7);
        Result res8 = new Result();
        res8.setMeters(5.20);
        athlete1.addResult(res8);
        Result res9 = new Result();
        res9.setMeters(61.96);
        athlete1.addResult(res9);
        Result res10 = new Result();
        res10.setSeconds(4 * 60 + 33.59);
        athlete1.addResult(res10);

        athletesBeforeCount.add(athlete1);

        athletesAfterCount = pointsCounter.countTotalScores(athletesBeforeCount);

    }

    @After
    public void tearDown() throws Exception {
        athletesAfterCount = null;
        athletesBeforeCount = null;
        pointsCounter = null;
    }

    @Test
    public void testCountTotalScores1() throws Exception {
        assertEquals(8869, athletesBeforeCount.get(0).getTotalScore());
    }

    @Test
    public void testCountTotalScores2() throws Exception {
        assertEquals(8869, athletesAfterCount.get(0).getTotalScore());
    }

    @Test
    public void testGetTotalScore1() throws Exception {
        assertEquals(1011, athletesAfterCount.get(0).getResults().get(0).getScore());
        assertEquals(1068, athletesAfterCount.get(0).getResults().get(1).getScore());
        assertEquals(769, athletesAfterCount.get(0).getResults().get(2).getScore());
        assertEquals(850, athletesAfterCount.get(0).getResults().get(3).getScore());
        assertEquals(963, athletesAfterCount.get(0).getResults().get(4).getScore());
        assertEquals(1032, athletesAfterCount.get(0).getResults().get(5).getScore());
        assertEquals(716, athletesAfterCount.get(0).getResults().get(6).getScore());
        assertEquals(972, athletesAfterCount.get(0).getResults().get(7).getScore());
        assertEquals(767, athletesAfterCount.get(0).getResults().get(8).getScore());
        assertEquals(721, athletesAfterCount.get(0).getResults().get(9).getScore());
    }
}
