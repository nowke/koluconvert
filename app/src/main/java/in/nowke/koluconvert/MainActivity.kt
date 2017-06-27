package `in`.nowke.koluconvert

import android.os.Build
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBarActivity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ScrollView


class MainActivity : AppCompatActivity() {

    internal lateinit var toolbar: Toolbar
    internal lateinit var tabLayout: TabLayout
    internal lateinit var viewPager: ViewPager
    internal lateinit var adapter: MyPagerAdapter

    internal var fragmentKolu: FragmentKolu? = null
    internal var fragmentMeter: FragmentMeter? = null
    internal var fragmentFeet: FragmentFeet? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = MyPagerAdapter(supportFragmentManager)
        toolbar = findViewById(R.id.toolbar) as Toolbar
        toolbar.setTitleTextColor(resources.getColor(R.color.colorWhite))
        setSupportActionBar(toolbar)
        tabLayout = findViewById(R.id.tabs) as TabLayout
        viewPager = findViewById(R.id.viewpager) as ViewPager
        viewPager.adapter = adapter
        tabLayout.setTabsFromPagerAdapter(adapter)
        tabLayout.setupWithViewPager(viewPager)
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))


        tabLayout.setOnTabSelectedListener(object : TabLayout.ViewPagerOnTabSelectedListener(viewPager) {
            override fun onTabSelected(tab: TabLayout.Tab) {
                super.onTabSelected(tab)
                when (tab.position) {
                    0 -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            window.statusBarColor = resources.getColor(R.color.colorPrimaryDark)
                        }
                        toolbar.setBackgroundColor(resources.getColor(R.color.colorPrimary))
                        tabLayout.setBackgroundColor(resources.getColor(R.color.colorPrimary))
                    }
                    1 -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            window.statusBarColor = resources.getColor(R.color.material_orange_700)
                        }
                        toolbar.setBackgroundColor(resources.getColor(R.color.material_orange_500))
                        tabLayout.setBackgroundColor(resources.getColor(R.color.material_orange_500))
                    }
                    2 -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            window.statusBarColor = resources.getColor(R.color.material_blue_700)
                        }
                        toolbar.setBackgroundColor(resources.getColor(R.color.material_blue_500))
                        tabLayout.setBackgroundColor(resources.getColor(R.color.material_blue_500))
                    }
                    else -> {
                    }
                }
            }
        })


    }


}
