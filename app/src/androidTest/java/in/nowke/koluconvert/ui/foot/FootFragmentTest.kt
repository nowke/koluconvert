package `in`.nowke.koluconvert.ui.foot

import `in`.nowke.koluconvert.R
import `in`.nowke.koluconvert.ui.MainActivity

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * FootFragmentTest
 */
@RunWith(AndroidJUnit4::class)
class FootFragmentTest {

    @Rule @JvmField
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java, true, true)

    @Before
    fun swipe_to_foot_fragment() {
        onView(withId(R.id.container))
                .perform(swipeLeft())
    }

    @Test
    fun test_convert_empty() {
        onView(withId(R.id.buttonConvertFoot))
                .perform(click())

        // Check converted values match 'zero' values
        onView(withId(R.id.textfoot_meter))
                .check(matches(withText("0.0 m")))
        onView(withId(R.id.textfoot_madhur))
                .check(matches(withText("0 mk 0.0 ang")))
        onView(withId(R.id.textfoot_payyannur))
                .check(matches(withText("0 pk 0.0 ang")))
    }

    @Test
    fun test_convert_empty_square_feet() {
        onView(withId(R.id.radio_foot_type_square))
                .perform(click())

        // Check converted values match 'zero' values
        onView(withId(R.id.textfoot_meter))
                .check(matches(withText("0.0 m²")))
    }

    @Test
    fun test_convert_empty_cubic_feet() {
        onView(withId(R.id.radio_foot_type_cube))
                .perform(click())

        // Check converted values match 'zero' values
        onView(withId(R.id.textfoot_meter))
                .check(matches(withText("0.0 m³")))
    }

    @Test
    fun test_foot_inch_value_convert() {
        onView(withId(R.id.edittext_foot))
                .perform(typeText("12"), pressImeActionButton())
        onView(withId(R.id.edittext_inch))
                .perform(typeText("6"), closeSoftKeyboard())

        // Check converted values
        onView(withId(R.id.textfoot_meter))
                .check(matches(withText("3.811 m")))
        onView(withId(R.id.textfoot_madhur))
                .check(matches(withText("5 mk 3.1 ang")))
        onView(withId(R.id.textfoot_payyannur))
                .check(matches(withText("5 pk 8.6 ang")))
    }

    @Test
    fun test_foot_with_foot_type_change() {
        onView(withId(R.id.edittext_foot))
                .perform(typeText("12"), closeSoftKeyboard())

        // Check converted values
        onView(withId(R.id.textfoot_meter))
                .check(matches(withText("3.6585 m")))
        onView(withId(R.id.textfoot_madhur))
                .check(matches(withText("4 mk 22.2 ang")))
        onView(withId(R.id.textfoot_payyannur))
                .check(matches(withText("5 pk 3.4 ang")))

        // Set `Square Foot` type
        onView(withId(R.id.radio_foot_type_square))
                .perform(click())

        onView(withId(R.id.textfoot_meter))
                .check(matches(withText("1.1148 m²")))

        // Set `Cubic Foot` type
        onView(withId(R.id.radio_foot_type_cube))
                .perform(click())
        onView(withId(R.id.textfoot_meter))
                .check(matches(withText("0.3401 m³")))

        // Set back to `Foot` type - should clear foot value
        onView(withId(R.id.radio_foot_type_foot))
                .perform(click())
        onView(withId(R.id.edittext_foot))
                .check(matches(withText("")))
        onView(withId(R.id.edittext_inch))
                .check(matches(withText("")))

    }
}