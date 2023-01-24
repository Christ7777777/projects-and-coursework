package com.example.drivewayparking;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.drivewayparking.Activity.LoginActivity;

import org.hamcrest.Matcher;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(AndroidJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DisputeTesting {

    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule = new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void A_RenterLogin() {
        onView(withId(R.id.email_username)).perform(typeText("xavier02@iastate.edu"), closeSoftKeyboard());
        onView(withId(R.id.password_login)).perform(typeText("coms309"), closeSoftKeyboard());
        onView(withId(R.id.login_button)).perform(click());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
    }

    @Test
    public void B_CreateDispute(){
        onView(withId(R.id.email_username)).perform(typeText("vsadvani@iastate.edu"), closeSoftKeyboard());
        onView(withId(R.id.password_login)).perform(typeText("hesoyam5756"), closeSoftKeyboard());
        onView(withId(R.id.login_button)).perform(click());

        onView(withId(R.id.reserved)).perform(click());
        onView(withText("Completed")).perform(click());

        onView(withId(R.id.recyclerView_bookings_completed)).perform(RecyclerViewActions.actionOnItemAtPosition(0, new ViewAction() {
            @Override
            public String getDescription() {
                return null;
            }

            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public void perform(UiController uiController, View view) {
                View v = view.findViewById(R.id.textView_raise_issue);
                v.performClick();
            }
        }));

        onView(withId(R.id.disputeMessage)).perform(typeText("Example Message"), closeSoftKeyboard());
        onView(withId(R.id.create)).perform(click());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
    }

    @Test
    public void C_AdminLogin() {
        onView(withId(R.id.email_username)).perform(typeText("admin1@drivewayparking.com"), closeSoftKeyboard());
        onView(withId(R.id.password_login)).perform(typeText("Admin1"), closeSoftKeyboard());
        onView(withId(R.id.login_button)).perform(click());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
    }

    @Test
    public void D_ViewDisputeList() {
        onView(withId(R.id.email_username)).perform(typeText("admin1@drivewayparking.com"), closeSoftKeyboard());
        onView(withId(R.id.password_login)).perform(typeText("Admin1"), closeSoftKeyboard());
        onView(withId(R.id.login_button)).perform(click());

        onView(withId(R.id.reserved)).perform(click());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
    }

    @Test
    public void E_ViewSpecificDispute() {
        onView(withId(R.id.email_username)).perform(typeText("admin1@drivewayparking.com"), closeSoftKeyboard());
        onView(withId(R.id.password_login)).perform(typeText("Admin1"), closeSoftKeyboard());
        onView(withId(R.id.login_button)).perform(click());

        onView(withId(R.id.reserved)).perform(click());

        onView(withId(R.id.dispute_recyclerView)).perform(actionOnItemAtPosition(0, click()));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
    }

    /*
    @Test
    public void F_ResolveDispute() {
        onView(withId(R.id.email_username)).perform(typeText("admin1@drivewayparking.com"), closeSoftKeyboard());
        onView(withId(R.id.password_login)).perform(typeText("Admin1"), closeSoftKeyboard());
        onView(withId(R.id.login_button)).perform(click());
        
        onView(withId(R.id.reserved)).perform(click());

        onView(withId(R.id.dispute_recyclerView)).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.Resolve)).perform(click());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
    }
    */
}
