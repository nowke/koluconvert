package `in`.nowke.koluconvert

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.view.View
import org.hamcrest.Matcher


/**
 * BaseTest
 */
open class BaseTest {
    fun findView(viewId: Int): ViewInteraction {
        return onView(withId(viewId))
    }

    fun swipeViewPagerLeft(viewId: Int) {
        findView(viewId).perform(withCustomConstraints(swipeLeft(), isDisplayingAtLeast(85)))
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

    private fun withCustomConstraints(action: ViewAction, constraints: Matcher<View>): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return constraints
            }

            override fun getDescription(): String {
                return action.description
            }

            override fun perform(uiController: UiController, view: View) {
                action.perform(uiController, view)
            }
        }
    }
}