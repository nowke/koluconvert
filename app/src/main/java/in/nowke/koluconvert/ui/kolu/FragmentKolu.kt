package `in`.nowke.koluconvert.ui.kolu

import `in`.nowke.koluconvert.R
import `in`.nowke.koluconvert.common.Extensions.inflate
import `in`.nowke.koluconvert.models.Units
import android.arch.lifecycle.ViewModelProviders

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import kotlinx.android.synthetic.main.fragment_kolu.*

/**
 * FragmentKolu
 */
class FragmentKolu : Fragment() {
    private lateinit var model: KoluViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProviders.of(activity).get(KoluViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = container?.inflate(R.layout.fragment_kolu)
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textinput_kolu.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) { }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                model.kolu = try { p0.toString().toDouble() } catch (e: NumberFormatException) { 0.0 }
            }
        })
        textinput_angula.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) { }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                model.angula = try { p0.toString().toDouble() } catch (e: NumberFormatException) { 0.0 }
            }
        })
        radio_kolu_type.setOnCheckedChangeListener { group, checkedId ->
            when (radio_kolu_type.checkedRadioButtonId) {
                radio_kolu_type_madhur.id -> model.koluType = Units.MADHUR_KOLU
                radio_kolu_type_payyannur.id -> model.koluType = Units.PAYYANNUR_KOLU
            }
        }
        buttonConvertKolu.setOnClickListener {
            textkolu_meter.text = model.calcMeters()
            textkolu_foot.text = model.calcFoot()
            textkolu_kolu.text = model.calcKolu()
        }
    }

    companion object {
        fun newInstance(): FragmentKolu {
            return FragmentKolu()
        }
    }
}
