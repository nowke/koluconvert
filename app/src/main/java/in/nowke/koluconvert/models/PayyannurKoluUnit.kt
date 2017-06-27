package `in`.nowke.koluconvert.models

import `in`.nowke.koluconvert.common.Extensions.toFixed

/**
 * PayyannurKoluUnit
 */
class PayyannurKoluUnit(kolu: Double, angula: Double = 0.0) : KoluUnit(kolu, angula) {
    override fun toString(): String {
        return "${kolu.toInt()} pk ${angula.toFixed(1)} ang"
    }
}