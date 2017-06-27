package `in`.nowke.koluconvert.common

import java.math.BigDecimal

/**
 * Extensions
 */
object Extensions {
    fun Double.toFixed(decimals: Int): Double {
        return BigDecimal(this).setScale(decimals, BigDecimal.ROUND_HALF_UP).toDouble()
    }
}