package `in`.nowke.koluconvert.models

import `in`.nowke.koluconvert.common.Extensions.toFixed

/**
 * Conversion
 */
object Conversion {
    fun convert(value: Double, from: Units, to: Units): Double {
        return value * from.factor / to.factor
    }

    fun convertToText(value: Double, from: Units, to: Units): String {
        val convertedValue: Double = convert(value, from, to)
        return "${convertedValue.toFixed(to.precision)}${to.suffix}"
    }
}