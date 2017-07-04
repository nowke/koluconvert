package `in`.nowke.koluconvert.ui.common

import `in`.nowke.koluconvert.R
import `in`.nowke.koluconvert.ui.foot.FootFragment
import `in`.nowke.koluconvert.ui.kolu.KoluFragment
import `in`.nowke.koluconvert.ui.meter.MeterFragment

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.ColorUtils

/**
 * UnitTabAdapter
 */
class UnitTabAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    data class ColorSet(val color: Int, val colorDark: Int)

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

    fun getTabColor(position: Int, positionOffset: Float, context: Context): ColorSet {
        val color1: Int = ContextCompat.getColor(context, R.color.colorPrimary)
        val color1Dark: Int = ContextCompat.getColor(context, R.color.colorPrimaryDark)
        val color2: Int = ContextCompat.getColor(context, R.color.colorOrangePrimary)
        val color2Dark: Int = ContextCompat.getColor(context, R.color.colorOrangePrimaryDark)
        val color3: Int = ContextCompat.getColor(context, R.color.colorBluePrimary)
        val color3Dark: Int = ContextCompat.getColor(context, R.color.colorBluePrimaryDark)

        when (position) {
            0 -> {
                return ColorSet(
                        ColorUtils.blendARGB(color1, color2, positionOffset),
                        ColorUtils.blendARGB(color1Dark, color2Dark, positionOffset)
                )
            }
            1 -> {
                return ColorSet(
                        ColorUtils.blendARGB(color2, color3, positionOffset),
                        ColorUtils.blendARGB(color2Dark, color3Dark, positionOffset)
                )
            }
            else -> {
                return ColorSet(color3, color3Dark)
            }
        }
    }
}