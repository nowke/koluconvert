package `in`.nowke.koluconvert.ui.meter

import `in`.nowke.koluconvert.common.Extensions.toDoubleFixed
import `in`.nowke.koluconvert.models.*

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField

/**
 * MeterViewModel
 */
class MeterViewModel : ViewModel() {
    var meter: ObservableField<String> = ObservableField<String>()
    var meterType: ObservableField<Units> = ObservableField<Units>(Units.METER)
    var textFoot: ObservableField<String> = ObservableField<String>()
    var textMadhur: ObservableField<String> = ObservableField<String>()
    var textPayyannur: ObservableField<String> = ObservableField<String>()

    /**
     * Sets meter type
     *
     * [type] values are:
     * 1 - **Meter**, 2 - **Square meter**, 3 - **Cubic meter**
     *
     * @param type the type value
     */
    fun setMeterType(type: Int) {
        if (type == 1) meterType.set(Units.METER)
        else if (type == 2) meterType.set(Units.SQUARE_METER)
        else meterType.set(Units.CUBIC_METER)
        convert()
    }

    fun convert() {
        textFoot.set(calcFoot())
        textMadhur.set(calcMadhur())
        textPayyannur.set(calcPayyannur())
    }

    private fun calcFoot(): String {
        when (meterType.get()) {
            Units.METER -> {
                val footValue: Double = Conversion.convert(getMeterUnit(), Units.METER, Units.FOOT)
                return FootUnit(footValue).toString()
            }
            Units.SQUARE_METER -> {
                return Conversion.convertToText(getMeterUnit(), Units.SQUARE_METER, Units.SQUARE_FEET)
            }
            Units.CUBIC_METER -> {
                return Conversion.convertToText(getMeterUnit(), Units.CUBIC_METER, Units.CUBIC_FEET)
            }
            else -> {
                return ""
            }
        }
    }

    private fun calcMadhur(): String {
        val koluValue: Double = Conversion.convert(getMeterUnit(), meterType.get(), Units.MADHUR_KOLU)
        return MadhurKoluUnit(koluValue).toString()
    }

    private fun calcPayyannur(): String {
        val koluValue: Double = Conversion.convert(getMeterUnit(), meterType.get(), Units.PAYYANNUR_KOLU)
        return PayyannurKoluUnit(koluValue).toString()
    }

    private fun getMeterUnit(): Double {
        return meter.get().toDoubleFixed()
    }
}