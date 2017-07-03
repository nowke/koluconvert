package `in`.nowke.koluconvert.ui.foot

import `in`.nowke.koluconvert.common.Extensions.toDoubleFixed
import `in`.nowke.koluconvert.models.*
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField

/**
 * FootViewModel
 */
class FootViewModel : ViewModel() {
    var foot: ObservableField<String> = ObservableField<String>()
    var inch: ObservableField<String> = ObservableField<String>()
    var footType: ObservableField<Units> = ObservableField<Units>(Units.FOOT)
    var textMeter: ObservableField<String> = ObservableField<String>()
    var textMadhur: ObservableField<String> = ObservableField<String>()
    var textPayyannur: ObservableField<String> = ObservableField<String>()

    fun setFootType(type: Int) {
        if (type == 1) {
            footType.set(Units.FOOT)
            foot.set("")
        }
        else if (type == 2) footType.set(Units.SQUARE_FEET)
        else footType.set(Units.CUBIC_FEET)
        convert()
    }

    fun convert() {
        textMeter.set(calcMeters())
        textMadhur.set(calcMadhur())
        textPayyannur.set(calcPayyannur())
    }

    private fun calcMeters(): String {
        when(footType.get()) {
            Units.FOOT -> {
                return Conversion.convertToText(getFootUnit().value, Units.FOOT, Units.METER)
            }
            Units.SQUARE_FEET -> {
                return Conversion.convertToText(foot.get().toDoubleFixed(), Units.SQUARE_FEET, Units.SQUARE_METER)
            }
            Units.CUBIC_FEET -> {
                return Conversion.convertToText(foot.get().toDoubleFixed(), Units.CUBIC_FEET, Units.CUBIC_METER)
            }
            else -> {
                return ""
            }
        }

    }

    private fun calcMadhur(): String {
        val koluValue: Double = Conversion.convert(getFootUnit().value, footType.get(), Units.MADHUR_KOLU)
        return PayyannurKoluUnit(koluValue).toString()
    }

    private fun calcPayyannur(): String {
        val koluValue: Double = Conversion.convert(getFootUnit().value, footType.get(), Units.PAYYANNUR_KOLU)
        return MadhurKoluUnit(koluValue).toString()
    }

    private fun getFootUnit(): FootUnit {
        return FootUnit(foot.get().toDoubleFixed(), inch.get().toDoubleFixed())
    }
}