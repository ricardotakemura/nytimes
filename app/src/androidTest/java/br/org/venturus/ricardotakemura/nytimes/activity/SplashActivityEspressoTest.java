package br.org.venturus.ricardotakemura.nytimes.activity;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.org.venturus.ricardotakemura.nytimes.R;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SplashActivityEspressoTest {

    @Rule
    public ActivityTestRule<SplashActivity> activityTestRule = new ActivityTestRule<>(SplashActivity.class);

    @Test
    public void testViews() {
        onView(ViewMatchers.withId(R.id.splash_image_view)).check(matches(isDisplayed()));
        onView(withId(R.id.logo_image_view)).check(matches(isDisplayed()));
    }
}
