package `in`.nowke.koluconvert

import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.text.Html
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView

/**
 * Created by nav on 28/6/15.
 */
class FragmentMeter : Fragment() {

    internal lateinit var convertButton: Button
    internal lateinit var meterRadioGroup: RadioGroup
    internal lateinit var textInputMeter: TextInputLayout
    internal lateinit var resultText1: TextView
    internal lateinit var resultText2: TextView
    internal lateinit var resultText3: TextView

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_meter, container, false)
        convertButton = rootView.findViewById<View>(R.id.buttonConvertMeter) as Button
        meterRadioGroup = rootView.findViewById<View>(R.id.meter_radio) as RadioGroup
        textInputMeter = rootView.findViewById<View>(R.id.textinput_meter) as TextInputLayout
        resultText1 = rootView.findViewById<View>(R.id.textMeter1) as TextView
        resultText2 = rootView.findViewById<View>(R.id.textMeter2) as TextView
        resultText3 = rootView.findViewById<View>(R.id.textMeter3) as TextView

        convertButton.setOnClickListener { v -> convertMeter(v) }

        textInputMeter.editText!!.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                textInputMeter.editText!!.clearFocus()
                convertMeter(v)
                return@OnEditorActionListener true
            }
            false
        })


        meterRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio_meter -> METERMODE = MODE_METER
                R.id.radio_sqm -> METERMODE = MODE_SQMTR
                R.id.radio_cum -> METERMODE = MODE_CUMTR
            }
            convertMeter(group.findViewById<View>(checkedId))
        }

        return rootView
    }

    private fun convertMeter(v: View) {
        val editMeterStr = textInputMeter.editText!!.text.toString()

        if (editMeterStr == "" || editMeterStr == ".") {
            return
        }

        val meterValue = java.lang.Double.parseDouble(editMeterStr)

        when (METERMODE) {
            1 -> {
                // Meter
                val feetValueDecimals = meterValue * 3.28
                val feetIntegerPart = feetValueDecimals.toInt()
                val feetDecimalPart = feetValueDecimals - feetIntegerPart
                val inchPartPrec = Helpers.toFixed(feetDecimalPart * 12, 1)
                val feetValueDecimalsPrec = Helpers.toFixed(feetValueDecimals, 2)

                val mkolValueDecimals = 1 / 0.743 * meterValue
                val mkolIntegerPart = mkolValueDecimals.toInt()
                val mkolDecimalPart = mkolValueDecimals - mkolIntegerPart
                val mkangPart = Helpers.toFixed(mkolDecimalPart * 24, 1)

                val pkolValueDecimals = 1 / 0.7114 * meterValue
                val pkolIntegerPart = pkolValueDecimals.toInt()
                val pkolDecimalPart = pkolValueDecimals - pkolIntegerPart
                val pkangPart = Helpers.toFixed(pkolDecimalPart * 24, 1)

                resultText1.text = feetValueDecimalsPrec!!.toString() + "' = " + feetIntegerPart.toString() + "' " + inchPartPrec!!.toString() + "\""
                resultText2.text = Html.fromHtml(mkolIntegerPart.toString() + "<small> mk </small>" + mkangPart!!.toString() + "<small> ang</small>")
                resultText3.text = Html.fromHtml(pkolIntegerPart.toString() + "<small> pk </small>" + pkangPart!!.toString() + "<small> ang</small>")
            }

            2 -> {
                // Square Meter
                val sqmeterValue = Helpers.toFixed(meterValue * 10.7639, 4)
                resultText1.text = Html.fromHtml(sqmeterValue!!.toString() + " <small>sq.ft</small>")
                resultText2.text = ""
                resultText3.text = ""
            }

            3 -> {
                // Cubic Meter
                val cumeterValue = Helpers.toFixed(meterValue * 35.28, 4)
                resultText1.text = Html.fromHtml(cumeterValue!!.toString() + " <small>cu.ft</small>")
                resultText2.text = ""
                resultText3.text = ""
            }
        }
        Helpers.hideKeyboard(activity, textInputMeter.editText as EditText)
    }

    companion object {

        private val MODE_METER = 1
        private val MODE_SQMTR = 2
        private val MODE_CUMTR = 3

        private var METERMODE = MODE_METER
    }
}
