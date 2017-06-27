package `in`.nowke.koluconvert

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.*

/**
 * Created by nav on 28/6/15.
 */
class FragmentKolu : Fragment() {
    internal lateinit var convert: Button
    internal lateinit var koluTextInput: TextInputLayout
    internal lateinit var angulaTextInput: TextInputLayout
    internal lateinit var koluTypeRadio: RadioGroup
    internal lateinit var resultText: TextView
    internal lateinit var resultTextFeet: TextView
    internal lateinit var resultTextKolu: TextView

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_kolu, container, false)
        koluTextInput = rootView.findViewById<View>(R.id.textinput_kolu) as TextInputLayout
        angulaTextInput = rootView.findViewById<View>(R.id.textinput_angula) as TextInputLayout
        koluTypeRadio = rootView.findViewById<View>(R.id.madhur_payyannur_radio) as RadioGroup
        resultText = rootView.findViewById<View>(R.id.textKolu) as TextView
        resultTextFeet = rootView.findViewById<View>(R.id.textKolu2) as TextView
        resultTextKolu = rootView.findViewById<View>(R.id.textKolu3) as TextView

        convert = rootView.findViewById<View>(R.id.buttonConvertKolu) as Button
        convert.setOnClickListener { v -> convertKolu(v) }

        angulaTextInput.editText!!.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                angulaTextInput.editText!!.clearFocus()
                koluTextInput.editText!!.clearFocus()
                convertKolu(v)
                return@OnEditorActionListener true
            }
            false
        })

        koluTypeRadio.setOnCheckedChangeListener { group, checkedId -> convertKolu(group.findViewById<View>(checkedId)) }


        return rootView
    }

    private fun convertKolu(v: View) {
        // KOLU_FACTOR : Madhur Kolu = 0.743, Payyannur kolu = 0.7114
        var KOLU_FACTOR: Double?
        val MADHUR_KOLU_FACTOR = 0.743
        val PAYYANNUR_KOLU_FACTOR = 0.7114

        // Default KOLU_FACTOR set to Madhur Kolu
        KOLU_FACTOR = MADHUR_KOLU_FACTOR

        // Get the value from Kolu and Angula EditText
        var koluValStr = koluTextInput.editText!!.text.toString()
        var angulaValStr = angulaTextInput.editText!!.text.toString()

        if (koluValStr == "" && (angulaValStr == "" || angulaValStr == ".")) {
            return
        }

        // Set Kolu/Angula to zero if they're not specified
        if (koluValStr == "" || koluValStr == ".") {
            koluValStr = "0"
        }

        if (angulaValStr == "" || angulaValStr == ".") {
            angulaValStr = "0"
        }


        // Convert strings to numbers
        val koluVal = Integer.parseInt(koluValStr)
        val angulaVal = java.lang.Double.parseDouble(angulaValStr)

        // Notify and break if Angula is >= 24
        if (angulaVal >= 24) {
            Helpers.hideKeyboard(activity, koluTextInput.editText as EditText)
            Helpers.hideKeyboard(activity, angulaTextInput.editText as EditText)
            Snackbar.make(v, "Angula can't be 24 or more", Snackbar.LENGTH_LONG).show()
            return
        }

        when (koluTypeRadio.checkedRadioButtonId) {
            R.id.radio_madhur -> KOLU_FACTOR = MADHUR_KOLU_FACTOR

            R.id.radio_payyannur -> KOLU_FACTOR = PAYYANNUR_KOLU_FACTOR
        }

        // Calculate the Stuffs
        val angulaDecimals = angulaVal / 24.0
        val koluDecimals = koluVal + angulaDecimals

        val finalMeters = koluDecimals * KOLU_FACTOR
        val precFinalMeters = Helpers.toFixed(finalMeters, 4)

        val feetValueDecimals = finalMeters * 3.28
        val feetIntegerPart = feetValueDecimals.toInt()
        val feetDecimalPart = feetValueDecimals - feetIntegerPart
        val inchPartPrec = Helpers.toFixed(feetDecimalPart * 12, 1)
        val feetValueDecimalsPrec = Helpers.toFixed(feetValueDecimals, 2)

        val mkolValueDecimals = 1 / 0.743 * finalMeters
        val mkolIntegerPart = mkolValueDecimals.toInt()
        val mkolDecimalPart = mkolValueDecimals - mkolIntegerPart
        val mkangPart = Helpers.toFixed(mkolDecimalPart * 24, 1)

        val pkolValueDecimals = 1 / 0.7114 * finalMeters
        val pkolIntegerPart = pkolValueDecimals.toInt()
        val pkolDecimalPart = pkolValueDecimals - pkolIntegerPart
        val pkangPart = Helpers.toFixed(pkolDecimalPart * 24, 1)

        resultText.text = Html.fromHtml(precFinalMeters.toString() + "<small> m</small>")
        resultTextFeet.text = Html.fromHtml(feetValueDecimalsPrec.toString() + "' = " + feetIntegerPart.toString() + "' " + inchPartPrec.toString() + "\"")

        if (KOLU_FACTOR == MADHUR_KOLU_FACTOR) {
            resultTextKolu.text = Html.fromHtml(pkolIntegerPart.toString() + "<small> pk </small>" + pkangPart.toString() + "<small> ang</small>")
        } else if (KOLU_FACTOR == PAYYANNUR_KOLU_FACTOR) {
            resultTextKolu.text = Html.fromHtml(mkolIntegerPart.toString() + "<small> mk </small>" + mkangPart.toString() + "<small> ang</small>")
        }

        Helpers.hideKeyboard(activity, koluTextInput.editText as EditText)
        Helpers.hideKeyboard(activity, angulaTextInput.editText as EditText)
    }

}
