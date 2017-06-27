package `in`.nowke.koluconvert.models

/**
 * Created by nowke on 27/06/17.
 */
object Conversion {
    fun convert(value: Double, from: Units, to: Units): Double {
        return value * from.factor / to.factor
    }
}