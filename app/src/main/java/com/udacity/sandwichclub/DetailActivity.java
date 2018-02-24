package com.udacity.sandwichclub;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.ui.TabPageAdapter;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private ViewPager viewPager;
    private TabPageAdapter tabPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
            return;
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];

        Sandwich sandwich = JsonUtils.parseSandwichJson(json);

        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());

        //Create ViewPager
        tabPageAdapter = new TabPageAdapter(getSupportFragmentManager(), createSandwichBundle(sandwich));

        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(tabPageAdapter);
        viewPager.setCurrentItem(0);

    }

    private TextView makeTabText(String title) {
        TextView textView = new TextView(this);
        textView.setAllCaps(true);
        textView.setLayoutParams(
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT, Gravity.CENTER_VERTICAL));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setTextColor(Color.WHITE);
        textView.setText(title);

        return textView;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        super.onCreateOptionsMenu(menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(this.getClass().getSimpleName(), "Option menu1" + item.getItemId());
        switch (item.getItemId()) {
            case R.id.back_menu:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private Bundle createSandwichBundle(Sandwich sandwich) {
        Bundle bundle = new Bundle();

        bundle.putString(getString(R.string.description), sandwich.getDescription());

        bundle.putStringArrayList(getString(R.string.alsoKnownAs), (ArrayList<String>)sandwich.getAlsoKnownAs());

        bundle.putString(getString(R.string.placeOfOrigin), sandwich.getPlaceOfOrigin());

        bundle.putStringArrayList(getString(R.string.ingredients), (ArrayList<String>)sandwich.getIngredients());

        return bundle;
    }
}
