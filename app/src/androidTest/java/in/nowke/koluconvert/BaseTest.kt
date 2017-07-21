package `in`.nowke.koluconvert

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions.typeText


/**
 * BaseTest
 */
open class BaseTest {
    fun findView(viewId: Int): ViewInteraction {
        return onView(withId(viewId))
    }

    fun swipeViewPagerLeft(viewId: Int) {
        findView(viewId).perform(swipeLeft())
    }

    fun performClick(viewId: Int) {
        findView(viewId).perform(click())
    }

    fun enterText(viewId: Int, text: String) {
        findView(viewId).perform(typeText(text), closeSoftKeyboard())
    }

    fun checkText(viewId: Int, text: String) {
        findView(viewId).check(matches(withText(text)))
    }
}