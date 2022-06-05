package com.example.exercise;
import androidx.fragment.app.testing.FragmentScenario;
import androidx.lifecycle.Lifecycle;
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
public class FragmentTest extends TestCase {

    // Create a graphical FragmentScenario for the Earth screen

    @Test
    public void testEarthOnView() {
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

    @Test
    public void testMarsOnView() {
        FragmentScenario<FragmentMars> earthScenario = FragmentScenario.launchInContainer(FragmentMars.class);
        //Check all views are displayed
        onView(withId(R.id.MarsDateText)).check(matches(isDisplayed()));
        onView(withId(R.id.MarsMaxTemp)).check(matches(isDisplayed()));
        onView(withId(R.id.MarsMinTemp)).check(matches(isDisplayed()));
        onView(withId(R.id.MarsPressure)).check(matches(isDisplayed()));
        onView(withId(R.id.MarsSunrise)).check(matches(isDisplayed()));
        onView(withId(R.id.MarsSunset)).check(matches(isDisplayed()));
        onView(withId(R.id.MarsUV)).check(matches(isDisplayed()));
        onView(withId(R.id.md0MaxTemp)).check(matches(isDisplayed()));
        onView(withId(R.id.md0MinTemp)).check(matches(isDisplayed()));
        onView(withId(R.id.md1MaxTemp)).check(matches(isDisplayed()));
        onView(withId(R.id.md1MinTemp)).check(matches(isDisplayed()));
        onView(withId(R.id.md2MaxTemp)).check(matches(isDisplayed()));
        onView(withId(R.id.md2MinTemp)).check(matches(isDisplayed()));
        onView(withId(R.id.md3MaxTemp)).check(matches(isDisplayed()));
        onView(withId(R.id.md3MinTemp)).check(matches(isDisplayed()));
        onView(withId(R.id.md4MaxTemp)).check(matches(isDisplayed()));
        onView(withId(R.id.md4MinTemp)).check(matches(isDisplayed()));
        onView(withId(R.id.md5MaxTemp)).check(matches(isDisplayed()));
        onView(withId(R.id.md5MinTemp)).check(matches(isDisplayed()));
        onView(withId(R.id.md6MaxTemp)).check(matches(isDisplayed()));
        onView(withId(R.id.md6MinTemp)).check(matches(isDisplayed()));
        onView(withId(R.id.md0Date)).check(matches(isDisplayed()));
        onView(withId(R.id.md1Date)).check(matches(isDisplayed()));
        onView(withId(R.id.md2Date)).check(matches(isDisplayed()));
        onView(withId(R.id.md3Date)).check(matches(isDisplayed()));
        onView(withId(R.id.md4Date)).check(matches(isDisplayed()));
        onView(withId(R.id.md5Date)).check(matches(isDisplayed()));
        onView(withId(R.id.md6Date)).check(matches(isDisplayed()));
        onView(withId(R.id.mBarChart)).check(matches(isDisplayed()));
        onView(withId(R.id.infoBtn)).check(matches(isDisplayed()));
    }

    //recreate fragment test
    @Test
    public void testRecreateFragmentEarth() {
        FragmentScenario<FragmentEarth> earthScenario = FragmentScenario.launchInContainer(FragmentEarth.class);
        earthScenario.recreate();
    }

    @Test
    public void testRecreateFragmentMars() {
        FragmentScenario<FragmentMars> marsScenario = FragmentScenario.launchInContainer(FragmentMars.class);
        marsScenario.recreate();
    }

    //move statement test
    @Test
    public void testMoveStateFragmentEarth() {
        FragmentScenario<FragmentEarth> earthScenario = FragmentScenario.launchInContainer(FragmentEarth.class);
        earthScenario.moveToState(Lifecycle.State.RESUMED);
    }

    @Test
    public void testMoveStateFragmentMars() {
        FragmentScenario<FragmentEarth> earthScenario = FragmentScenario.launchInContainer(FragmentEarth.class);
        earthScenario.moveToState(Lifecycle.State.RESUMED);
    }

    @Test
    public void testDialogFragment() {
        FragmentScenario<FragmentMars> marsScenario = FragmentScenario.launchInContainer(FragmentMars.class);
        onView(withId(R.id.infoBtn)).perform(click());
    }



}
