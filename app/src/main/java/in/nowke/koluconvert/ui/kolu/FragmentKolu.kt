package `in`.nowke.koluconvert.ui.kolu

import `in`.nowke.koluconvert.R
import `in`.nowke.koluconvert.common.Extensions.inflate

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * FragmentKolu
 */
class FragmentKolu : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = container?.inflate(R.layout.fragment_kolu)
        return rootView
    }

    companion object {
        fun newInstance(): FragmentKolu {
            return FragmentKolu()
        }
    }
}