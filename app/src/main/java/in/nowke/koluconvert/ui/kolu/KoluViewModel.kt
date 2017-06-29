package `in`.nowke.koluconvert.ui.kolu

import `in`.nowke.koluconvert.models.*
import android.arch.lifecycle.ViewModel

/**
 * KoluViewModel
 */
class KoluViewModel : ViewModel() {
    var koluType: Units = Units.MADHUR_KOLU
    var kolu: Double = 0.0
    var angula: Double = 0.0

    fun calcMeters(): String {
        return Conversion.convertToText(getKoluUnit().value, koluType, Units.METER)
    }

    fun calcFoot(): String {
        val footValue: Double = Conversion.convert(getKoluUnit().value, koluType, Units.FOOT)
        return FootUnit(footValue).toString()
    }

    fun calcKolu(): String {
        val koluValue: Double
        if (koluType == Units.MADHUR_KOLU) {
            koluValue = Conversion.convert(getKoluUnit().value, koluType, Units.PAYYANNUR_KOLU)
            return PayyannurKoluUnit(koluValue).toString()
        } else {
            koluValue = Conversion.convert(getKoluUnit().value, koluType, Units.MADHUR_KOLU)
            return MadhurKoluUnit(koluValue).toString()
        }
    }

    private fun getKoluUnit(): KoluUnit {
        return KoluUnit(kolu, angula)
    }
}