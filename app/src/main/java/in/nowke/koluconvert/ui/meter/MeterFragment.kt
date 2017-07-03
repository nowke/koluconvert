package `in`.nowke.koluconvert.ui.meter

import `in`.nowke.koluconvert.R
import `in`.nowke.koluconvert.common.Extensions.hideKeyboard
import `in`.nowke.koluconvert.databinding.FragmentMeterBinding

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * MeterFragment
 */
class MeterFragment : Fragment() {
    private lateinit var model: MeterViewModel
    private lateinit var binding: FragmentMeterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProviders.of(activity).get(MeterViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_meter, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        model = ViewModelProviders.of(activity).get(MeterViewModel::class.java)
        binding.fragment = this
        binding.meterViewModel = model
    }

    fun onClickConvert() {
        model.convert()
        this.hideKeyboard()
    }

    companion object {
        fun newInstance(): MeterFragment {
            return MeterFragment()
        }
    }
}