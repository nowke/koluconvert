package `in`.nowke.koluconvert.ui.kolu

import `in`.nowke.koluconvert.common.Extensions.toDoubleFixed
import `in`.nowke.koluconvert.models.*

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField

/**
 * KoluViewModel
 */
class KoluViewModel : ViewModel() {

    var kolu: ObservableField<String> = ObservableField<String>()
    var angula: ObservableField<String> = ObservableField<String>()
    var koluType: ObservableField<Units> = ObservableField<Units>(Units.MADHUR_KOLU)
    var textMeter: ObservableField<String> = ObservableField<String>()
    var textFoot: ObservableField<String> = ObservableField<String>()
    var textKolu: ObservableField<String> = ObservableField<String>()

    fun setKoluType(type: Int) {
        if (type == 1) koluType.set(Units.MADHUR_KOLU)
        else koluType.set(Units.PAYYANNUR_KOLU)
        convert()
    }

    fun convert() {
        textMeter.set(calcMeters())
        textFoot.set(calcFoot())
        textKolu.set(calcKolu())
    }

    private fun calcMeters(): String {
        return Conversion.convertToText(getKoluUnit().value, koluType.get(), Units.METER)
    }

    private fun calcFoot(): String {
        val footValue: Double = Conversion.convert(getKoluUnit().value, koluType.get(), Units.FOOT)
        return FootUnit(footValue).toString()
    }

    private fun calcKolu(): String {
        val koluValue: Double
        if (koluType.get() == Units.MADHUR_KOLU) {
            koluValue = Conversion.convert(getKoluUnit().value, koluType.get(), Units.PAYYANNUR_KOLU)
            return PayyannurKoluUnit(koluValue).toString()
        } else {
            koluValue = Conversion.convert(getKoluUnit().value, koluType.get(), Units.MADHUR_KOLU)
            return MadhurKoluUnit(koluValue).toString()
        }
    }

    private fun getKoluUnit(): KoluUnit {
        return KoluUnit(kolu.get().toDoubleFixed(), angula.get().toDoubleFixed())
    }
}