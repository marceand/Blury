package marceme.com.blury.scoreboard;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import marceme.com.blury.R;
import marceme.com.blury.adapter.ViewPagerAdapter;
import timber.log.Timber;

/**
 * @author Marcelino Yax-marce7j@gmail.com-Android Developer
 *         Created on 9/24/2017.
 */

public class MainScoreboardFragment extends Fragment {


    public static MainScoreboardFragment newInstance() {
        return new MainScoreboardFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Timber.e("on create main board");
        View view = inflater.inflate(R.layout.fragment_main_scoreboard, container, false);
        setupToolbar(view);
        bindTabWithViewPager(view);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.e("on destroy main board");
    }

    private void setupToolbar(View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar_scoreboard);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void bindTabWithViewPager(View view) {
        TabLayout tabLayout = setupTabs(view);
        final ViewPager viewPager = setupViewPager(view, tabLayout.getTabCount());
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Timber.i("on tab selected "+tab.getPosition());
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Timber.i("on tab unselected "+tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Timber.i("on tab reselected "+tab.getPosition());
            }
        });
    }

    private TabLayout setupTabs(View view) {
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_scoreboard);
        tabLayout.addTab(tabLayout.newTab().setText("Scoreboard"));
        tabLayout.addTab(tabLayout.newTab().setText("Today Pts "));
        tabLayout.addTab(tabLayout.newTab().setText("Prizes"));
        tabLayout.addTab(tabLayout.newTab().setText("Products"));
        return tabLayout;
    }

    private ViewPager setupViewPager(View view, int tabCount) {
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager_scoreboard);
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), tabCount);
        viewPager.setAdapter(pagerAdapter);
        return viewPager;
    }
}
