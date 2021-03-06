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
 * BaseTest class
 */
open class BaseTest {
    /**
     * find the view by Id - espresso helper
     *
     * @param viewId the Id of the view
     * @return {@link ViewInteraction} object
     */
    fun findView(viewId: Int): ViewInteraction {
        return onView(withId(viewId))
    }

    /**
     * Swipe the view pager towards left - espresso helper
     *
     * @param viewId the Id of the view
     */
    fun swipeViewPagerLeft(viewId: Int) {
        findView(viewId).perform(withCustomConstraints(swipeLeft(), isDisplayingAtLeast(85)))
    }

    /**
     * Click on the view
     *
     * @param viewId the Id of the view to be clicked - espresso helper
     */
    fun performClick(viewId: Int) {
        findView(viewId).perform(click())
    }

    /**
     * Type [text] on the view - espresso helper
     *
     * @param viewId the Id of the view
     * @param text the text to be typed in the view
     */
    fun enterText(viewId: Int, text: String) {
        findView(viewId).perform(typeText(text), closeSoftKeyboard())
    }

    /**
     * Check if [text] matches the view's text
     *
     * @param viewId the Id of the view
     * @param text the text to be matched against view's text
     */
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