package `in`.nowke.koluconvert.ui

import `in`.nowke.koluconvert.R
import `in`.nowke.koluconvert.ui.common.UnitTabAdapter

import android.os.Build
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var adapter: UnitTabAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setupUI()
    }

    private fun setupUI() {
        adapter = UnitTabAdapter(supportFragmentManager)
        container.adapter = adapter
        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))

        // ViewPager onChange
        container.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {}

            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                val (color, colorDark) = adapter!!.getTabColor(position, positionOffset, this@MainActivity)
                tabs.setBackgroundColor(color)
                toolbar.setBackgroundColor(color)
                appbar.setBackgroundColor(color)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.navigationBarColor = color
                    window.statusBarColor = colorDark
                }
            }
        })
    }
}
