package `in`.nowke.koluconvert.common

import android.app.Activity
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import java.math.BigDecimal

/**
 * Collection of Extension functions
 */
object Extensions {
    /**
     * Round a Double value to specified digits
     *
     * @param decimals the number of digits to be rounded to
     * @return the rounded Double value
     */
    fun Double.toFixed(decimals: Int): Double {
        return BigDecimal(this).setScale(decimals, BigDecimal.ROUND_HALF_UP).toDouble()
    }

    /**
     * Inflate a layout to a {@link android.view.ViewGroup}
     *
     * @param layoutId ID of the layout
     * @return the inflated {@link android.view.View}
     */
    fun ViewGroup.inflate(layoutId: Int): View {
        return LayoutInflater.from(context).inflate(layoutId, this, false)
    }

    /**
     * Convert String to Double
     *
     * @return Double value
     */
    fun String?.toDoubleFixed(): Double {
        if (this == null) return 0.0
        return try {
            this.toDouble()
        } catch (e: NumberFormatException) {
            0.0
        }
    }

    /**
     * Hide Keyboard (soft input) from the view
     */
    fun Fragment.hideKeyboard() {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view!!.rootView.windowToken, 0)
    }
}