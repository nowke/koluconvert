package `in`.nowke.koluconvert.ui.foot

import `in`.nowke.koluconvert.R
import `in`.nowke.koluconvert.common.Extensions.hideKeyboard
import `in`.nowke.koluconvert.databinding.FragmentFootBinding

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * FootFragment
 */
class FootFragment : Fragment() {

    private lateinit var model: FootViewModel
    private lateinit var binding: FragmentFootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProviders.of(activity).get(FootViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_foot, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        model = ViewModelProviders.of(activity).get(FootViewModel::class.java)
        binding.fragment = this
        binding.footViewModel = model
    }

    fun onClickConvert() {
        model.convert()
        this.hideKeyboard()
    }

    companion object {
        fun newInstance(): FootFragment {
            return FootFragment()
        }
    }
}