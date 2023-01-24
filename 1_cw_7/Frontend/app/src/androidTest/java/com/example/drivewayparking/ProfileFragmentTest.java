package com.example.drivewayparking;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.StringEndsWith.endsWith;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.drivewayparking.Activity.LoginActivity;
import com.example.drivewayparking.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ProfileFragmentTest {

    @Rule
    public ActivityScenarioRule<LoginActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void profileOnCreateTest() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.email_username),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.email_username),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("vsadvani@iastate.edu"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.password_login),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("hesoyam5756"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.login_button), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.profile), withContentDescription("Profile"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_navigation),
                                        0),
                                3),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
//        onView(withId(R.id.print_email_2)).check(matches(withText(endsWith("vsadvani@iastate.edu"))));
//        onView(withId(R.id.show_password_2)).check(matches(withText(endsWith("Hello123"))));
//        onView(withId(R.id.user_phone_2)).check(matches(withText(endsWith("5157158486"))));

    }
    @Test
    public void profileEmailTest(){
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.email_username),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.email_username),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("vsadvani@iastate.edu"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.password_login),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("hesoyam5756"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.login_button), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.profile), withContentDescription("Profile"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_navigation),
                                        0),
                                3),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());
        onView(withId(R.id.print_email_2)).check(matches(withText(endsWith("vsadvani@iastate.edu"))));
        onView(withId(R.id.logout_2)).perform(click());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
    }

    @Test
    public void profilePasswordTest(){
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.email_username),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.email_username),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("vsadvani@iastate.edu"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.password_login),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("hesoyam5756"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.login_button), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.profile), withContentDescription("Profile"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_navigation),
                                        0),
                                3),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());
        onView(withId(R.id.password_toggle_2)).perform(click());
        onView(withId(R.id.show_password_2)).check(matches(withText(endsWith("hesoyam5756"))));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
    }
    @Test
    public void profileNumberTest(){
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.email_username),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.email_username),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("vsadvani@iastate.edu"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.password_login),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("hesoyam5756"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.login_button), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.profile), withContentDescription("Profile"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_navigation),
                                        0),
                                3),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());
        onView(withId(R.id.user_phone_2)).check(matches(withText(endsWith("5157158486"))));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
    }


@Test
public void testOnResume(){
    ViewInteraction appCompatEditText = onView(
            allOf(withId(R.id.email_username),
                    childAtPosition(
                            childAtPosition(
                                    withId(android.R.id.content),
                                    0),
                            1),
                    isDisplayed()));
    appCompatEditText.perform(replaceText("vsadvani@iastate.edu"), closeSoftKeyboard());

    ViewInteraction appCompatEditText2 = onView(
            allOf(withId(R.id.password_login),
                    childAtPosition(
                            childAtPosition(
                                    withId(android.R.id.content),
                                    0),
                            2),
                    isDisplayed()));
    appCompatEditText2.perform(replaceText("hesoyam5756"), closeSoftKeyboard());

    ViewInteraction materialButton = onView(
            allOf(withId(R.id.login_button), withText("Login"),
                    childAtPosition(
                            childAtPosition(
                                    withId(android.R.id.content),
                                    0),
                            4),
                    isDisplayed()));
    materialButton.perform(click());

    ViewInteraction bottomNavigationItemView = onView(
            allOf(withId(R.id.profile), withContentDescription("Profile"),
                    childAtPosition(
                            childAtPosition(
                                    withId(R.id.bottom_navigation),
                                    0),
                            3),
                    isDisplayed()));
    bottomNavigationItemView.perform(click());

    ViewInteraction bottomNavigationItemView2 = onView(
            allOf(withId(R.id.find), withContentDescription("Find"),
                    childAtPosition(
                            childAtPosition(
                                    withId(R.id.bottom_navigation),
                                    0),
                            0),
                    isDisplayed()));
    bottomNavigationItemView2.perform(click());

    ViewInteraction bottomNavigationItemView3 = onView(
            allOf(withId(R.id.profile), withContentDescription("Profile"),
                    childAtPosition(
                            childAtPosition(
                                    withId(R.id.bottom_navigation),
                                    0),
                            3),
                    isDisplayed()));
    bottomNavigationItemView3.perform(click());
    try {
        Thread.sleep(500);
    } catch (InterruptedException e) {
    }
}

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
