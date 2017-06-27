package `in`.nowke.koluconvert.models

import `in`.nowke.koluconvert.Helpers

/**
 * Created by nowke on 27/06/17.
 */
class MadhurKoluUnit(kolu: Double, angula: Double) : KoluUnit(kolu, angula) {
    override fun toString(): String {
        return "${Helpers.toFixed(kolu, 4)} mk ${Helpers.toFixed(kolu, 1)} ang"
    }
}