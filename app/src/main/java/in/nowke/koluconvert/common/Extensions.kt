package `in`.nowke.koluconvert.common

import android.app.Activity
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import java.math.BigDecimal

/**
 * Extensions
 */
object Extensions {
    fun Double.toFixed(decimals: Int): Double {
        return BigDecimal(this).setScale(decimals, BigDecimal.ROUND_HALF_UP).toDouble()
    }

    fun ViewGroup.inflate(layoutId: Int): View {
        return LayoutInflater.from(context).inflate(layoutId, this, false)
    }

    fun String?.toDoubleFixed(): Double {
        if (this == null) return 0.0
        return try {
            this.toDouble()
        } catch (e: NumberFormatException) {
            0.0
        }
    }

    fun Fragment.hideKeyboard() {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view!!.rootView.windowToken, 0)
    }
}