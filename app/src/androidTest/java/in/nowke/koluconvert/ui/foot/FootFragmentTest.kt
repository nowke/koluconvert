package `in`.nowke.koluconvert.ui.foot

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
 * Foot fragment tests
 */
@RunWith(AndroidJUnit4::class)
class FootFragmentTest : BaseTest() {

    @Rule @JvmField
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java, true, true)

    @Before
    fun swipe_to_foot_fragment() {
        swipeViewPagerLeft(R.id.container)
    }

    @Test
    fun test_convert_empty() {
        performClick(R.id.buttonConvertFoot)

        // Check converted values matches 'zero' values
        checkText(R.id.textfoot_meter, "0.0 m")
        checkText(R.id.textfoot_madhur, "0 mk 0.0 ang")
        checkText(R.id.textfoot_payyannur, "0 pk 0.0 ang")
    }

    @Test
    fun test_convert_empty_square_feet() {
        performClick(R.id.radio_foot_type_square)

        // Check converted values match 'zero' values
        checkText(R.id.textfoot_meter, "0.0 m²")
    }

    @Test
    fun test_convert_empty_cubic_feet() {
        performClick(R.id.radio_foot_type_cube)

        // Check converted values match 'zero' values
        checkText(R.id.textfoot_meter, "0.0 m³")
    }

    @Test
    fun test_foot_inch_value_convert() {
        enterText(R.id.edittext_foot, "12")
        enterText(R.id.edittext_inch, "6")

        // Check converted values
        checkText(R.id.textfoot_meter, "3.811 m")
        checkText(R.id.textfoot_madhur, "5 mk 3.1 ang")
        checkText(R.id.textfoot_payyannur, "5 pk 8.6 ang")
    }

    @Test
    fun test_foot_with_foot_type_change() {
        enterText(R.id.edittext_foot, "12")

        // Check converted values
        checkText(R.id.textfoot_meter, "3.6585 m")
        checkText(R.id.textfoot_madhur, "4 mk 22.2 ang")
        checkText(R.id.textfoot_payyannur, "5 pk 3.4 ang")

        // Set `Square Foot` type
        performClick(R.id.radio_foot_type_square)
        checkText(R.id.textfoot_meter, "1.1148 m²")

        // Set `Cubic Foot` type
        performClick(R.id.radio_foot_type_cube)
        checkText(R.id.textfoot_meter, "0.3401 m³")

        // Set back to `Foot` type - should clear foot value
        performClick(R.id.radio_foot_type_foot)
        checkText(R.id.edittext_foot, "")
        checkText(R.id.edittext_inch, "")
    }
}