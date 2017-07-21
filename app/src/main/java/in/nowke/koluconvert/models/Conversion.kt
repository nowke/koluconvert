package `in`.nowke.koluconvert.models

import `in`.nowke.koluconvert.common.Extensions.toFixed

/**
 * Conversion from one unit to another
 */
object Conversion {

    /**
     * Convert the [value] from one unit to another
     *
     * @param value the value to be converted
     * @param from the type of unit from which [value] to be converted
     * @param to the type of unit to which [value] to be converted
     * @return the converted value in Double
     */
    fun convert(value: Double, from: Units, to: Units): Double {
        return value * from.factor / to.factor
    }

    /**
     * Convert the [value] from one unit to another in text format
     *
     * @param value the value to be converted
     * @param from the type of unit from which [value] to be converted
     * @param to the type of unit to which [value] to be converted
     * @return the converted value in String with suffix
     */
    fun convertToText(value: Double, from: Units, to: Units): String {
        val convertedValue: Double = convert(value, from, to)
        return "${convertedValue.toFixed(to.precision)}${to.suffix}"
    }
}