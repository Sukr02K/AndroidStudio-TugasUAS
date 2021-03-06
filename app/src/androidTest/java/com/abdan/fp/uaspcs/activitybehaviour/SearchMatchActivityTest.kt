package com.abdan.fp.uaspcs.activitybehaviour

import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.abdan.fp.uaspcs.R
import com.abdan.fp.uaspcs.ui.search.match.SearchMatchActivity
import com.abdan.fp.uaspcs.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SearchMatchActivityTest {

    @Rule
    @JvmField var activityRule = ActivityTestRule(SearchMatchActivity::class.java)

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }


    @Test
    fun searchMatchBehaviour() {

        onView(withId(R.id.search_view))
            .check(matches(isDisplayed()))
        onView(withId(R.id.search_view))
            .perform(click())
        onView(isAssignableFrom(EditText::class.java))
            .perform(typeText("Real Madrid vs Barcelona")).perform(pressImeActionButton())


        onView(withId(R.id.rv_search_match))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_search_match))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
        Thread.sleep(2000)
        onView(withId(R.id.rv_search_match))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))

        onView(withId(R.id.search_view))
            .perform(click())
        onView(isAssignableFrom(EditText::class.java))
            .perform(clearText(), typeText("Juventus vs Inter")).perform(pressImeActionButton())

        onView(withId(R.id.rv_search_match))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_search_match))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
        Thread.sleep(2000)
        onView(withId(R.id.rv_search_match))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))

    }
}