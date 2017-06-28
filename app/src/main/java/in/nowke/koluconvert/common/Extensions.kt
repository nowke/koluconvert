package `in`.nowke.koluconvert.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
}