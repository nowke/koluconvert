package `in`.nowke.koluconvert.ui.meter

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
 * MeterFragmentTest
 */
@RunWith(AndroidJUnit4::class)
class MeterFragmentTest {
    @Rule @JvmField
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java, true, true)

    @Before
    fun swipe_to_meter_fragment() {
        onView(withId(R.id.container))
                .perform(swipeLeft())
        onView(withId(R.id.container))
                .perform(swipeLeft())
    }

    @Test
    fun test_convert_empty() {
        onView(withId(R.id.buttonConvertMeter))
                .perform(click())

        // Check converted values match 'zero' values
        onView(withId(R.id.textmeter_foot))
                .check(matches(withText("0.0\' = 0\' 0.0\"")))
        onView(withId(R.id.textmeter_madhur))
                .check(matches(withText("0 mk 0.0 ang")))
        onView(withId(R.id.textmeter_payyannur))
                .check(matches(withText("0 pk 0.0 ang")))
    }

    @Test
    fun test_convert_empty_square_meter() {
        onView(withId(R.id.radio_meter_type_square))
                .perform(click())

        // Check converted values match 'zero' values
        onView(withId(R.id.textmeter_foot))
                .check(matches(withText("0.0 sq.ft")))
    }

    @Test
    fun test_convert_empty_cubic_meter() {
        onView(withId(R.id.radio_meter_type_cube))
                .perform(click())

        // Check converted values match 'zero' values
        onView(withId(R.id.textmeter_foot))
                .check(matches(withText("0.0 cu.ft")))
    }

    @Test
    fun test_meter_value_convert() {
        onView(withId(R.id.edittext_meter))
                .perform(typeText("12.5"), closeSoftKeyboard())

        // Check converted values
        onView(withId(R.id.textmeter_foot))
                .check(matches(withText("41.0\' = 41\' 0.0\"")))
        onView(withId(R.id.textmeter_madhur))
                .check(matches(withText("16 mk 19.8 ang")))
        onView(withId(R.id.textmeter_payyannur))
                .check(matches(withText("17 pk 13.7 ang")))
    }

    @Test
    fun test_meter_with_meter_type_change() {
        onView(withId(R.id.edittext_meter))
                .perform(typeText("12.5"), closeSoftKeyboard())

        // Set `Square meter` type
        onView(withId(R.id.radio_meter_type_square))
                .perform(click())

        onView(withId(R.id.textmeter_foot))
                .check(matches(withText("134.5487 sq.ft")))

        // Set `Cubic meter` type
        onView(withId(R.id.radio_meter_type_cube))
                .perform(click())
        onView(withId(R.id.textmeter_foot))
                .check(matches(withText("441.0 cu.ft")))

        // Set back to `meter` type
        onView(withId(R.id.radio_meter_type_meter))
                .perform(click())

        onView(withId(R.id.textmeter_foot))
                .check(matches(withText("41.0\' = 41\' 0.0\"")))
        onView(withId(R.id.textmeter_madhur))
                .check(matches(withText("16 mk 19.8 ang")))
        onView(withId(R.id.textmeter_payyannur))
                .check(matches(withText("17 pk 13.7 ang")))
    }
}