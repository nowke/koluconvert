package `in`.nowke.koluconvert.ui.kolu

import `in`.nowke.koluconvert.BaseTest
import `in`.nowke.koluconvert.R
import `in`.nowke.koluconvert.ui.MainActivity

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Kolu fragment tests
 */
@RunWith(AndroidJUnit4::class)
class KoluFragmentTest : BaseTest() {
    @Rule @JvmField
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java, true, true)

    @Test
    fun test_convert_empty() {
        performClick(R.id.buttonConvertKolu)

        // Check converted values matches 'zero' values
        checkText(R.id.textkolu_meter, "0.0 m")
        checkText(R.id.textkolu_foot, "0.0\' = 0\' 0.0\"")
        checkText(R.id.textkolu_kolu, "0 pk 0.0 ang")
    }

    @Test
    fun test_convert_empty_payyannur() {
        performClick(R.id.radio_kolu_type_payyannur)

        // Check converted values match 'zero' values
        checkText(R.id.textkolu_meter, "0.0 m")
        checkText(R.id.textkolu_foot, "0.0\' = 0\' 0.0\"")
        checkText(R.id.textkolu_kolu, "0 mk 0.0 ang")
    }

    @Test
    fun test_kolu_angula_values_convert() {
        enterText(R.id.edittext_kolu, "12")
        enterText(R.id.edittext_angula, "6")

        // Check converted values
        checkText(R.id.textkolu_meter, "9.1017 m")
        checkText(R.id.textkolu_foot, "29.85\' = 29\' 10.2\"")
        checkText(R.id.textkolu_kolu, "12 pk 19.1 ang")
    }

    @Test
    fun test_kolu_angula_values_with_change_in_kolu_type() {
        enterText(R.id.edittext_kolu, "12")
        enterText(R.id.edittext_angula, "6")
        performClick(R.id.radio_kolu_type_payyannur)

        // Check converted values
        checkText(R.id.textkolu_meter, "8.7147 m")
        checkText(R.id.textkolu_foot, "28.58\' = 28\' 7.0\"")
        checkText(R.id.textkolu_kolu, "11 mk 17.5 ang")
    }
}