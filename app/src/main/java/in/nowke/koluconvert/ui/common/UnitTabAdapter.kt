package `in`.nowke.koluconvert.ui.common

import `in`.nowke.koluconvert.ui.foot.FragmentFoot
import `in`.nowke.koluconvert.ui.kolu.FragmentKolu
import `in`.nowke.koluconvert.ui.meter.FragmentMeter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * UnitTabAdapter
 */
class UnitTabAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return FragmentKolu.newInstance()
            1 -> return FragmentFoot.newInstance()
            2 -> return FragmentMeter.newInstance()
        }
        return null
    }

    override fun getCount(): Int {
        return 3
    }
}