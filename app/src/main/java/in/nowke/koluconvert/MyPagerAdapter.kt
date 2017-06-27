package `in`.nowke.koluconvert

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Created by nav on 28/6/15.
 */
class MyPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 ->
                // Kolu > meter fragment activity
                return FragmentKolu()
            1 ->
                // Feet, sq.ft, cb.ft fragment activity
                return FragmentFeet()
            2 ->
                // Meter > kolu, feet fragment activity
                return FragmentMeter()
        }

        return null
    }


    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence {
        when (position) {
            0 -> return "Kolu"
            1 -> return "Feet"
            2 -> return "Meter"
            else -> return ""
        }
    }
}
