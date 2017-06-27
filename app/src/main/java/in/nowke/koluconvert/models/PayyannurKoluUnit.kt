package `in`.nowke.koluconvert.models

import `in`.nowke.koluconvert.Helpers

/**
 * Created by nowke on 27/06/17.
 */
class PayyannurKoluUnit(kolu: Double, angula: Double) : KoluUnit(kolu, angula) {
    override fun toString(): String {
        return "${Helpers.toFixed(kolu, 4)} pk ${Helpers.toFixed(kolu, 1)} ang"
    }
}