package com.udacity.sandwichclub.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.udacity.sandwichclub.R;

import java.lang.ref.WeakReference;

public class TabPageAdapter extends FragmentPagerAdapter {
    private Bundle sandwichBundle;
    private WeakReference<Context> context;

    public TabPageAdapter(FragmentManager fm, Bundle sandwichBundle, Context context) {
        super(fm);

        this.sandwichBundle = sandwichBundle;
        this.context = new WeakReference<>(context);
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
        if(context != null) {
            switch (position) {
                case 0:
                    return context.get().getString(R.string.description_tab);
                case 1:
                    return context.get().getString(R.string.additional_info_tab);
                default:
                   return null;
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
