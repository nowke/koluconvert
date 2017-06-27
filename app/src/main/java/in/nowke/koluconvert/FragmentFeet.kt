package `in`.nowke.koluconvert

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.text.Html
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.*

/**
 * Created by nav on 28/6/15.
 */
class FragmentFeet : Fragment() {

    internal lateinit var convert: Button
    internal lateinit var radioFeet: RadioGroup
    internal lateinit var textInputFeet: TextInputLayout
    internal lateinit var textInputInch: TextInputLayout
    internal lateinit var resultText: TextView
    internal lateinit var resultText2: TextView
    internal lateinit var resultText3: TextView
    internal var scrollView: ScrollView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_feet, container, false)
        textInputFeet = rootView.findViewById<View>(R.id.textinput_feet) as TextInputLayout
        textInputInch = rootView.findViewById<View>(R.id.textinput_inches) as TextInputLayout
        radioFeet = rootView.findViewById<View>(R.id.feet_radio) as RadioGroup
        convert = rootView.findViewById<View>(R.id.buttonConvertFeet) as Button
        resultText = rootView.findViewById<View>(R.id.textFeet) as TextView
        resultText2 = rootView.findViewById<View>(R.id.textFeet2) as TextView
        resultText3 = rootView.findViewById<View>(R.id.textFeet3) as TextView

        convert.setOnClickListener { v -> convertFeet(v) }

        textInputInch.editText!!.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                textInputFeet.editText!!.clearFocus()
                textInputInch.editText!!.clearFocus()
                convertFeet(v)
                return@OnEditorActionListener true
            }
            false
        })

        radioFeet.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio_feet -> {
                    FEETMODE = MODE_FOOT
                    textInputInch.visibility = View.VISIBLE
                }

                R.id.radio_sqft -> {
                    FEETMODE = MODE_SQFT
                    textInputInch.visibility = View.INVISIBLE
                }
                R.id.radio_cuft -> {
                    FEETMODE = MODE_CUFT
                    textInputInch.visibility = View.INVISIBLE
                }
            }
            convertFeet(group.findViewById<View>(checkedId))
        }


        return rootView
    }


    private fun convertFeet(v: View) {
        var editFeetStr = textInputFeet.editText!!.text.toString().trim { it <= ' ' }
        var editInchStr = textInputInch.editText!!.text.toString().trim { it <= ' ' }

        if ((editFeetStr == "" || editFeetStr == ".") && (editInchStr == "" || editInchStr == ".")) {
            return
        }

        if (editFeetStr == "" || editFeetStr == ".") {
            editFeetStr = "0"
        }

        if (editInchStr == "" || editInchStr == ".") {
            editInchStr = "0"
        }

        val feetValue = java.lang.Double.parseDouble(editFeetStr)
        val inchValue = java.lang.Double.parseDouble(editInchStr)

        val resFeet: String

        if (inchValue >= 12 && textInputInch.visibility == View.VISIBLE) {
            Helpers.hideKeyboard(activity, textInputInch.editText as EditText)
            Helpers.hideKeyboard(activity, textInputFeet.editText as EditText)
            Snackbar.make(v, "Inch can't be 12 or more", Snackbar.LENGTH_LONG).show()
            return
        }
        when (FEETMODE) {
            1 -> {
                // Feet
                val inchDecimals = inchValue / 12.0
                val feetDecimals = feetValue + inchDecimals
                val meterValue = feetDecimals / 3.28

                val meterValuePrec = Helpers.toFixed(meterValue, 4)

                val mkolValueDecimals = 1 / 0.743 * meterValuePrec!!
                val mkolIntegerPart = mkolValueDecimals.toInt()
                val mkolDecimalPart = mkolValueDecimals - mkolIntegerPart
                val mkangPart = Helpers.toFixed(mkolDecimalPart * 24, 1)

                val pkolValueDecimals = 1 / 0.7114 * meterValuePrec
                val pkolIntegerPart = pkolValueDecimals.toInt()
                val pkolDecimalPart = pkolValueDecimals - pkolIntegerPart
                val pkangPart = Helpers.toFixed(pkolDecimalPart * 24, 1)


                resultText.text = Html.fromHtml(meterValuePrec.toString() + "<small> m</small>")
                resultText2.text = Html.fromHtml(mkolIntegerPart.toString() + "<small> mk </small>" + mkangPart!!.toString() + "<small> ang</small>")
                resultText3.text = Html.fromHtml(pkolIntegerPart.toString() + "<small> pk </small>" + pkangPart!!.toString() + "<small> ang</small>")
            }

            2 -> {
                // Square Feet
                val squareMeter = 1 / 10.7639 * feetValue
                val squareMeterPrec = Helpers.toFixed(squareMeter, 4)
                resultText.text = Html.fromHtml(squareMeterPrec.toString() + "<small> m²</small")
                resultText2.text = ""
                resultText3.text = ""
            }

            3 -> {
                // Cubic Feet
                val cubicMeter = 1 / 35.28 * feetValue
                val cubicMeterPrec = Helpers.toFixed(cubicMeter, 4)
                resultText.text = Html.fromHtml(cubicMeterPrec.toString() + "<small> m³</small>")
                resultText2.text = ""
                resultText3.text = ""
            }
            else -> {
            }
        }
        Helpers.hideKeyboard(activity, textInputFeet.editText as EditText)
        Helpers.hideKeyboard(activity, textInputInch.editText as EditText)
    }

    companion object {

        private val MODE_FOOT = 1
        private val MODE_SQFT = 2
        private val MODE_CUFT = 3

        private var FEETMODE = MODE_FOOT
    }
}
