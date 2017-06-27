package `in`.nowke.koluconvert

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

import java.math.BigDecimal

/**
 * Created by nav on 28/6/15.
 */
object Helpers {

    fun toFixed(value: Double?, no_of_decimals: Int?): Double {
        // Rounds up 'value' to 'digits' no.of decimals
        return BigDecimal(value!!).setScale(no_of_decimals!!, BigDecimal.ROUND_HALF_UP).toDouble()
    }

    fun hideKeyboard(context: Context, editText: EditText) {
        // Hides the Keyboard input from 'editText'
        val imm = context.getSystemService(
                Context.INPUT_METHOD_SERVICE) as InputMethodManager

        imm.hideSoftInputFromWindow(editText.windowToken, 0)
    }
}
