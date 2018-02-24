package com.udacity.sandwichclub.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.sandwichclub.R;

import java.util.List;


public class DetailOtherFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_detail_other, container, false);

        Bundle sandwichBundle = getArguments();

        TextView alsoKnownAsTv = rootView.findViewById(R.id.also_known_tv);
        alsoKnownAsTv.setText(unknownIfEmpty(formatList(sandwichBundle.getStringArrayList(getString(R.string.alsoKnownAs)))));

        TextView placeOfOriginTv = rootView.findViewById(R.id.origin_tv);
        placeOfOriginTv.setText(unknownIfEmpty(sandwichBundle.getString(getString(R.string.placeOfOrigin))));

        TextView ingredientsTv = rootView.findViewById(R.id.ingredients_tv);
        ingredientsTv.setText(unknownIfEmpty(formatList(sandwichBundle.getStringArrayList(getString(R.string.ingredients)))));

        return rootView;
    }

    private String unknownIfEmpty(String input) {
        if(input == null || input.isEmpty()) {
            return "Unknown";
        }
        return input;
    }

    private String formatList(List<String> list) {
        if(list != null) {
            String toString = list.toString();

            return toString.substring(1, toString.length() - 1);
        }
        return "";
    }
}
