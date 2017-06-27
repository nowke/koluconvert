package `in`.nowke.koluconvert.models

import `in`.nowke.koluconvert.common.Extensions.toFixed

/**
 * MadhurKoluUnit
 */
class MadhurKoluUnit(kolu: Double, angula: Double = 0.0) : KoluUnit(kolu, angula) {
    override fun toString(): String {
        return "${kolu.toInt()} mk ${angula.toFixed(1)} ang"
    }
}