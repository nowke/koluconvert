package in.nowke.koluconvert;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by nav on 28/6/15.
 */
public class MyPagerAdapter extends FragmentStatePagerAdapter{

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                // Kolu > meter fragment activity
                return new FragmentKolu();
            case 1:
                // Feet, sq.ft, cb.ft fragment activity
                return new FragmentFeet();
            case 2:
                // Meter > kolu, feet fragment activity
                return new FragmentMeter();
        }

        return null;
    }


    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position) {
            case 0:
                return "Kolu";
            case 1:
                return "Feet";
            case 2:
                return "Meter";
            default:
                return "";
        }
    }
}
