package `in`.nowke.koluconvert.ui.meter

import `in`.nowke.koluconvert.BaseTest
import `in`.nowke.koluconvert.R
import `in`.nowke.koluconvert.ui.MainActivity

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Meter fragment tests
 */
@RunWith(AndroidJUnit4::class)
class MeterFragmentTest : BaseTest() {
    @Rule @JvmField
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java, true, true)

    @Before
    fun swipe_to_meter_fragment() {
        swipeViewPagerLeft(R.id.container)
        swipeViewPagerLeft(R.id.container)
    }

    @Test
    fun test_convert_empty() {
        performClick(R.id.buttonConvertMeter)

        // Check converted values match 'zero' values
        checkText(R.id.textmeter_foot, "0.0\' = 0\' 0.0\"")
        checkText(R.id.textmeter_madhur, "0 mk 0.0 ang")
        checkText(R.id.textmeter_payyannur, "0 pk 0.0 ang")
    }

    @Test
    fun test_convert_empty_square_meter() {
        performClick(R.id.radio_meter_type_square)

        // Check converted values match 'zero' values
        checkText(R.id.textmeter_foot, "0.0 sq.ft")
    }

    @Test
    fun test_convert_empty_cubic_meter() {
        performClick(R.id.radio_meter_type_cube)

        // Check converted values match 'zero' values
        checkText(R.id.textmeter_foot, "0.0 cu.ft")
    }

    @Test
    fun test_meter_value_convert() {
        enterText(R.id.edittext_meter, "12.5")

        // Check converted values
        checkText(R.id.textmeter_foot, "41.0\' = 41\' 0.0\"")
        checkText(R.id.textmeter_madhur, "16 mk 19.8 ang")
        checkText(R.id.textmeter_payyannur, "17 pk 13.7 ang")
    }

    @Test
    fun test_meter_with_meter_type_change() {
        enterText(R.id.edittext_meter, "12.5")

        // Set `Square meter` type
        performClick(R.id.radio_meter_type_square)
        checkText(R.id.textmeter_foot, "134.5487 sq.ft")

        // Set `Cubic meter` type
        performClick(R.id.radio_meter_type_cube)
        checkText(R.id.textmeter_foot, "441.0 cu.ft")

        // Set back to `meter` type
        performClick(R.id.radio_meter_type_meter)
        checkText(R.id.textmeter_foot, "41.0\' = 41\' 0.0\"")
        checkText(R.id.textmeter_madhur, "16 mk 19.8 ang")
        checkText(R.id.textmeter_payyannur, "17 pk 13.7 ang")
    }
}