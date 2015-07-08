package in.nowke.koluconvert;

import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;


public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    MyPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MyPagerAdapter(getSupportFragmentManager());
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        setSupportActionBar(toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        tabLayout.setTabsFromPagerAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
                switch (tab.getPosition()) {
                    case 0:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
                        }
                        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        tabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        break;
                    case 1:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            getWindow().setStatusBarColor(getResources().getColor(R.color.material_purple_700));
                        }
                        toolbar.setBackgroundColor(getResources().getColor(R.color.material_purple_500));
                        tabLayout.setBackgroundColor(getResources().getColor(R.color.material_purple_500));
                        break;
                    case 2:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            getWindow().setStatusBarColor(getResources().getColor(R.color.material_blue_700));
                        }
                        toolbar.setBackgroundColor(getResources().getColor(R.color.material_blue_500));
                        tabLayout.setBackgroundColor(getResources().getColor(R.color.material_blue_500));
                        break;
                    default:
                        break;
                }
            }
        });


    }


}
