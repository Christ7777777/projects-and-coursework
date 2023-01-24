package com.example.drivewayparking;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.not;



import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.runner.AndroidJUnit4;
import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.drivewayparking.Activity.LoginActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4ClassRunner.class)
public class LoginTest {
    private View decorView;

    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule = new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void testLoginAsRenter() {
        onView(withId(R.id.email_username)).perform(typeText("vsadvani@iastate.edu"), closeSoftKeyboard());
        onView(withId(R.id.password_login)).perform(typeText("hesoyam5756"), closeSoftKeyboard());
        onView(withId(R.id.login_button)).perform(click());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
    }

    @Test
    public void testLoginHost() {
        onView(withId(R.id.email_username)).perform(typeText("ltimko@iastate.edu"), closeSoftKeyboard());
        onView(withId(R.id.password_login)).perform(typeText("DuckingMad123"), closeSoftKeyboard());
        onView(withId(R.id.checkbox_usertype)).perform(click());
        onView(withId(R.id.login_button)).perform(click());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
    }

    @Test
    public void testInvalidCredentials(){
        onView(withId(R.id.email_username)).perform(typeText("vsadvani@iastate.edu"), closeSoftKeyboard());
        onView(withId(R.id.password_login)).perform(typeText("Hello123"), closeSoftKeyboard());
        onView(withId(R.id.login_button)).perform(click());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
    }

    @Test
    public void testUserDoesNotExist(){
        onView(withId(R.id.email_username)).perform(typeText("lindsay@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.password_login)).perform(typeText("hesoyam5756"), closeSoftKeyboard());
        onView(withId(R.id.login_button)).perform(click());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }


    }

    @Test
    public void createNewAccount(){
        onView(withId(R.id.signUpQuestion)).perform(click());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
    }

    @Test
    public void testEmptyEmail(){
        onView(withId(R.id.email_username)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.password_login)).perform(typeText("Hello123"), closeSoftKeyboard());
        onView(withId(R.id.login_button)).perform(click());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
    }

    @Test
    public void testPasswordEmpty(){
        onView(withId(R.id.email_username)).perform(typeText("vsadvani@iastate.edu"), closeSoftKeyboard());
        onView(withId(R.id.password_login)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.login_button)).perform(click());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
    }


}
