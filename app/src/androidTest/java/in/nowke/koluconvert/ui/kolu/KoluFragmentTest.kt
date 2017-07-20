package `in`.nowke.koluconvert.ui.kolu

import `in`.nowke.koluconvert.R
import `in`.nowke.koluconvert.ui.MainActivity

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * KoluFragmentTest
 */
@RunWith(AndroidJUnit4::class)
class KoluFragmentTest {
    @Rule @JvmField
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java, true, true)

    @Test
    fun test_convert_empty() {
        onView(withId(R.id.buttonConvertKolu))
                .perform(click())

        // Check converted values matches 'zero' values
        onView(withId(R.id.textkolu_meter))
                .check(matches(withText("0.0 m")))
        onView(withId(R.id.textkolu_foot))
                .check(matches(withText("0.0\' = 0\' 0.0\"")))
        onView(withId(R.id.textkolu_kolu))
                .check(matches(withText("0 pk 0.0 ang")))
    }

    @Test
    fun test_convert_empty_payyannur() {
        onView(withId(R.id.radio_kolu_type_payyannur))
                .perform(click())

        // Check converted values match 'zero' values
        onView(withId(R.id.textkolu_meter))
                .check(matches(withText("0.0 m")))
        onView(withId(R.id.textkolu_foot))
                .check(matches(withText("0.0\' = 0\' 0.0\"")))
        onView(withId(R.id.textkolu_kolu))
                .check(matches(withText("0 mk 0.0 ang")))
    }

    @Test
    fun test_kolu_angula_values_convert() {
        onView(withId(R.id.edittext_kolu))
                .perform(typeText("12"), pressImeActionButton())
        onView(withId(R.id.edittext_angula))
                .perform(typeText("6"), closeSoftKeyboard())

        // Check converted values
        onView(withId(R.id.textkolu_meter))
                .check(matches(withText("9.1017 m")))
        onView(withId(R.id.textkolu_foot))
                .check(matches(withText("29.85\' = 29\' 10.2\"")))
        onView(withId(R.id.textkolu_kolu))
                .check(matches(withText("12 pk 19.1 ang")))
    }

    @Test
    fun test_kolu_angula_values_with_change_in_kolu_type() {
        onView(withId(R.id.edittext_kolu))
                .perform(typeText("12"), pressImeActionButton())
        onView(withId(R.id.edittext_angula))
                .perform(typeText("6"), closeSoftKeyboard())
        onView(withId(R.id.radio_kolu_type_payyannur))
                .perform(click())

        // Check converted values
        onView(withId(R.id.textkolu_meter))
                .check(matches(withText("8.7147 m")))
        onView(withId(R.id.textkolu_foot))
                .check(matches(withText("28.58\' = 28\' 7.0\"")))
        onView(withId(R.id.textkolu_kolu))
                .check(matches(withText("11 mk 17.5 ang")))
    }
}