package marceme.com.blury.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import marceme.com.blury.scoreboard.PrizesFragment;
import marceme.com.blury.scoreboard.ProductsFragment;
import marceme.com.blury.scoreboard.ScoreboardFragment;
import marceme.com.blury.scoreboard.TodayPtsFragment;

/**
 * @author Marcelino Yax-marce7j@gmail.com-Android Developer
 *         Created on 9/24/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private int numberOfTabs;

    public ViewPagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return ScoreboardFragment.newInstance();
            case 1:
                return TodayPtsFragment.newInstance();
            case 2:
                return PrizesFragment.newInstance();
            case 3:
                return ProductsFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return this.numberOfTabs;
    }
}
