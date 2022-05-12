package com.example.espressodemo;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class EspressoAppTest {

    @Rule
    public ActivityScenarioRule<MainActivity> rule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void assertTextChanged() {
//        try (ActivityScenario<MainActivity> ignored = ActivityScenario.launch(MainActivity.class)) {
            // type text and then press change text button
            onView(withId(R.id.inputField)).perform(typeText("Hello"), closeSoftKeyboard());
            onView(withId(R.id.changeText)).perform(click());

            // check that the text was changed when the button was clicked
            onView(withId(R.id.inputField)).check(matches(withText("Hello")));
//        }

    }

    @Test
    public void goToSecondActivity() {
//        try (ActivityScenario<MainActivity> ignored = ActivityScenario.launch(MainActivity.class)) {
            onView(withId(R.id.inputField)).perform(typeText("Good Bye"), closeSoftKeyboard());
            onView(withId(R.id.switchActivity)).perform(click());

            // assert the data went over correctly and was displayed
            onView(withId(R.id.resultView)).check(matches(withText("Good Bye")));
//        }
    }
}
