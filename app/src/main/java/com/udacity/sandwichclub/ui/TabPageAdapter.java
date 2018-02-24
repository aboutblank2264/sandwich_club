package com.udacity.sandwichclub.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabPageAdapter extends FragmentPagerAdapter {
    private Bundle sandwichBundle;

    public TabPageAdapter(FragmentManager fm, Bundle sandwichBundle) {
        super(fm);

        this.sandwichBundle = sandwichBundle;
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
    public int getCount() {
        return 2;
    }
}
