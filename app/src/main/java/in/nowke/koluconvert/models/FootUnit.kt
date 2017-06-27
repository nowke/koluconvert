package `in`.nowke.koluconvert.models

/**
 * Created by nowke on 27/06/17.
 */
class FootUnit(var foot: Double = 0.0, var inch: Double = 0.0) {
    var value: Double = 0.0

    init {
        this.value = if (this.inch == 0.0) {
            val decimalValue: Double = this.foot
            this.inch = (this.foot - this.foot.toInt()) * 12
            this.foot = this.foot.toInt().toDouble()
            decimalValue
        } else {
            this.foot.toInt() + (this.inch / 12)
        }
    }
}