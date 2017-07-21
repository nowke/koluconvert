package `in`.nowke.koluconvert.models

/**
 * KoluUnit Model
 */
open class KoluUnit(var kolu: Double = 0.0, var angula: Double = 0.0) {
    var value: Double = 0.0

    init {
        this.value = if (this.angula == 0.0) {
            val decimalValue: Double = this.kolu
            this.angula = (this.kolu - this.kolu.toInt()) * 24
            this.kolu = this.kolu.toInt().toDouble()
            decimalValue
        } else {
            this.kolu.toInt() + (this.angula / 24)
        }
    }
}