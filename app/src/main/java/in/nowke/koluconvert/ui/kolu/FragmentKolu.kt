package `in`.nowke.koluconvert.ui.kolu

import `in`.nowke.koluconvert.R
import `in`.nowke.koluconvert.common.Extensions.hideKeyboard
import `in`.nowke.koluconvert.databinding.FragmentKoluBinding

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * FragmentKolu
 */
class FragmentKolu : Fragment() {
    private lateinit var model: KoluViewModel
    private lateinit var binding: FragmentKoluBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProviders.of(activity).get(KoluViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_kolu, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        model = ViewModelProviders.of(activity).get(KoluViewModel::class.java)
        binding.fragment = this
        binding.koluViewModel = model
    }

    fun onClickConvert() {
        model.convert()
        this.hideKeyboard()
    }

    companion object {
        fun newInstance(): FragmentKolu {
            return FragmentKolu()
        }
    }
}
