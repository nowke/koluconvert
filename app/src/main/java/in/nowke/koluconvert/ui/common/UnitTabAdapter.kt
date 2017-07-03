package `in`.nowke.koluconvert.ui.common

import `in`.nowke.koluconvert.ui.foot.FootFragment
import `in`.nowke.koluconvert.ui.kolu.KoluFragment
import `in`.nowke.koluconvert.ui.meter.MeterFragment

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * UnitTabAdapter
 */
class UnitTabAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return KoluFragment.newInstance()
            1 -> return FootFragment.newInstance()
            2 -> return MeterFragment.newInstance()
        }
        return null
    }

    override fun getCount(): Int {
        return 3
    }
}