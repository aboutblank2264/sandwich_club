package com.udacity.sandwichclub.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.sandwichclub.R;

public class DetailDescriptionFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_detail_description, container, false);

        Bundle sandwichBundle = getArguments();

        TextView descriptionTv = rootView.findViewById(R.id.description_tv);
        descriptionTv.setText(sandwichBundle.getString(getString(R.string.description)));

        return rootView;
    }
}
