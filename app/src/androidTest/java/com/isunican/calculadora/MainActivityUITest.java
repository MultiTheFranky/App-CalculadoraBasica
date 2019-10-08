package com.isunican.calculadora;


import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.Espresso.onData;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
public class MainActivityUITest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void sumaTest() {

        // Type operators
        onView(withId(R.id.txtOperador2)).perform(replaceText("2.0"));
        onView(withId(R.id.txtOperador1)).perform(replaceText("3.0"));

        // Select Operation
        onView(withId(R.id.spnOperacion)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("+"))).perform(click());

        // Call operation
        onView(withId(R.id.btnCalcula)).perform(click(), closeSoftKeyboard());

        // Check behaviour
        onView(withId(R.id.txtResultado)).check(matches(withText("5.0")));
    }
}
