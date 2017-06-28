package `in`.nowke.koluconvert.ui.meter

import `in`.nowke.koluconvert.R
import `in`.nowke.koluconvert.common.Extensions.inflate

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * FragmentMeter
 */
class FragmentMeter : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = container?.inflate(R.layout.fragment_meter)
        return rootView
    }

    companion object {
        fun newInstance(): FragmentMeter {
            return FragmentMeter()
        }
    }
}