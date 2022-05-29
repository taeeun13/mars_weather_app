package com.example.exercise;
import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.runner.AndroidJUnitRunner;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class FragmentEarthTest extends TestCase {

    @Test
    public void test() {

        // Create a graphical FragmentScenario for the Earth screen
        FragmentScenario<FragmentEarth> earthScenario = FragmentScenario.launchInContainer(FragmentEarth.class);

        //Check all views are displayed
        onView(withId(R.id.EarthDateText)).check(matches(isDisplayed()));
        onView(withId(R.id.EarthMaxTemp)).check(matches(isDisplayed()));
        onView(withId(R.id.EarthMinTemp)).check(matches(isDisplayed()));
        onView(withId(R.id.EarthPressure)).check(matches(isDisplayed()));
        onView(withId(R.id.EarthSunrise)).check(matches(isDisplayed()));
        onView(withId(R.id.EarthSunset)).check(matches(isDisplayed()));
        onView(withId(R.id.EarthUV)).check(matches(isDisplayed()));
        onView(withId(R.id.d0MaxTemp)).check(matches(isDisplayed()));
        onView(withId(R.id.d0MinTemp)).check(matches(isDisplayed()));
        onView(withId(R.id.d1MaxTemp)).check(matches(isDisplayed()));
        onView(withId(R.id.d1MinTemp)).check(matches(isDisplayed()));
        onView(withId(R.id.d2MaxTemp)).check(matches(isDisplayed()));
        onView(withId(R.id.d2MinTemp)).check(matches(isDisplayed()));
        onView(withId(R.id.d3MaxTemp)).check(matches(isDisplayed()));
        onView(withId(R.id.d3MinTemp)).check(matches(isDisplayed()));
        onView(withId(R.id.d4MaxTemp)).check(matches(isDisplayed()));
        onView(withId(R.id.d4MinTemp)).check(matches(isDisplayed()));
        onView(withId(R.id.d5MaxTemp)).check(matches(isDisplayed()));
        onView(withId(R.id.d5MinTemp)).check(matches(isDisplayed()));
        onView(withId(R.id.d6MaxTemp)).check(matches(isDisplayed()));
        onView(withId(R.id.d6MinTemp)).check(matches(isDisplayed()));
        onView(withId(R.id.d0day)).check(matches(isDisplayed()));
        onView(withId(R.id.d1day)).check(matches(isDisplayed()));
        onView(withId(R.id.d2day)).check(matches(isDisplayed()));
        onView(withId(R.id.d3day)).check(matches(isDisplayed()));
        onView(withId(R.id.d4day)).check(matches(isDisplayed()));
        onView(withId(R.id.d5day)).check(matches(isDisplayed()));
        onView(withId(R.id.d6day)).check(matches(isDisplayed()));
        onView(withId(R.id.eBarChart)).check(matches(isDisplayed()));

    }

}
