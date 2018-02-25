package com.udacity.sandwichclub.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Reference for ViewPager:
 * https://developer.android.com/training/implementing-navigation/lateral.html#horizontal-paging
 */
public class TabPageAdapter extends FragmentPagerAdapter {
    private Bundle sandwichBundle;
    private List<String> tabNames;

    public TabPageAdapter(FragmentManager fm, Bundle sandwichBundle, List<String> tabNames) {
        super(fm);

        this.sandwichBundle = sandwichBundle;
        this.tabNames = tabNames;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                DetailDescriptionFragment detailDescriptionFragment = new DetailDescriptionFragment();
                detailDescriptionFragment.setArguments(sandwichBundle);
                return detailDescriptionFragment;
            case 1:
                DetailOtherFragment detailOtherFragment = new DetailOtherFragment();
                detailOtherFragment.setArguments(sandwichBundle);
                return detailOtherFragment;
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(tabNames != null && !tabNames.isEmpty()) {
            if(tabNames.size() > position) {
                return tabNames.get(position);
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
